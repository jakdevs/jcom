package com.jakdev.jcom.eth;

import com.jakdev.jcom.action.ReaderAction;
import com.jakdev.jcom.Communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
public class EthernetCommunicator implements Communicator, Runnable {

    private final Socket socket = new Socket();
    private OutputStream output;
    private PrintWriter writer;
    private BufferedReader reader;
    private final ReaderAction action;
    @Getter
    private boolean connected;
    private final String host;
    private final Integer port;
    private final int timeout;
    private String lastValue;

    @Override
    public void connect() throws IOException {
        this.socket.connect(new InetSocketAddress(this.host, this.port), this.timeout);
        this.output = this.socket.getOutputStream();
        this.writer = new PrintWriter(this.output, true);
        InputStream input = this.socket.getInputStream();
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.connected = Boolean.TRUE;
        this.listen();
    }

    @Override
    public void disconnect() throws IOException {
        this.socket.close();
        this.connected = false;
    }

    @Override
    public void write(String value) {
        this.writer.println(value);
    }

    @Override
    public void write(byte[] value) throws IOException {
        this.output.write(value);
    }

    private void listen() {
        Thread thread = new Thread(this, "Indicator Reader");
        thread.start();
    }

    public void run() {
        while (this.connected) {
            try {
                var value = this.reader.readLine();
                if (!Objects.equals(lastValue, value) && value != null) {
                    this.action.doTask(value);
                    this.lastValue = value;
                }
            } catch (IOException ignore) {

            }
        }

    }

}
