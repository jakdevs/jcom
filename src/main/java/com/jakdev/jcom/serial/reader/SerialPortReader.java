package com.jakdev.jcom.serial.reader;

import com.jakdev.jcom.action.ReaderAction;
import com.jakdev.jcom.serial.SerialCommunicator;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SerialPortReader implements SerialPortEventListener {

    protected final SerialCommunicator communicator;
    protected final StringBuilder stringBuilder;
    protected final ReaderAction action;

    public SerialPortReader(SerialCommunicator communicator, ReaderAction action) {
        this.communicator = communicator;
        this.stringBuilder = new StringBuilder();
        this.action = action;
    }

    public static SerialPortReader getInstance(SerialCommunicator communicator, ReaderAction action) {

        return new SerialPortReader(communicator, action);
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {
            try {
                byte[] buffer = communicator.readBytes();
                if (buffer != null) {
                    this.action.doTask(new String(buffer));
                }
            } catch (SerialPortException | NullPointerException ex) {
                log.error("Error on Serial reader event", ex);
            }
        }
    }
}
