package com.jakdev.jcom;

import com.jakdev.jcom.exception.EventListenerNotSetException;
import jssc.SerialPortException;

import java.io.IOException;

public interface Communicator {

    public static final int SERIAL_TYPE = 1;
    public static final int ETHERNET_TYPE = 2;

    public void connect() throws IOException, SerialPortException, EventListenerNotSetException;
    public void disconnect() throws IOException, SerialPortException;
    public void write(String value) throws SerialPortException;
    public void write(byte[] value) throws IOException, SerialPortException;
    public boolean isConnected();

}
