/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakdev.jcom.serial;

import com.jakdev.jcom.serial.reader.BufferedSerialPortReader;
import com.jakdev.jcom.serial.reader.SerialPortReader;
import com.jakdev.jcom.Communicator;
import com.jakdev.jcom.action.ReaderAction;
import jssc.SerialPort;
import jssc.SerialPortException;
import lombok.Getter;

public class SerialCommunicator implements Communicator {

    public final static int SPACE = 32;
    public final static int DASH = 45;
    public final static byte LINE_FEED = 10;
    public final static byte CARRIAGE_RETURN = 13;

    private SerialPort serialPort;
    @Getter
    private boolean connected = false;
    private final PortConfiguration portConfig;
    @Getter
    private final SerialPortReader serialPortReader;

    public SerialCommunicator(PortConfiguration portConfig, ReaderAction action) {
        this.portConfig = portConfig;
        this.serialPortReader = new BufferedSerialPortReader(this, action);
    }

    @Override
    public void connect() throws SerialPortException {
        this.serialPort = new SerialPort(this.portConfig.getName());
        this.serialPort.openPort();
        this.serialPort.setParams(this.portConfig.getBaudRate(), this.portConfig.getDataBits(), this.portConfig.getStopBits().getId(), this.portConfig.getParity().getId());
        this.serialPort.addEventListener(this.serialPortReader);
        this.connected = true;
    }

    @Override
    public void disconnect() throws SerialPortException {
        this.serialPort.closePort();
        this.connected = false;
    }

    private void writeData(String data) throws SerialPortException {
        this.serialPort.writeString(data);
        this.serialPort.writeByte(CARRIAGE_RETURN);
        this.serialPort.writeByte(LINE_FEED);
    }

    @Override
    public void write(String message) throws SerialPortException {
        this.writeData(message);
    }

    @Override
    public void write(byte[] stream) throws SerialPortException {
        this.serialPort.writeBytes(stream);
    }

    public byte[] readBytes() throws SerialPortException {
        return this.serialPort.readBytes();
    }

}
