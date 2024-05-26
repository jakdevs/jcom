package com.jakdev.jcom.serial.reader;

import com.jakdev.jcom.action.ReaderAction;
import com.jakdev.jcom.serial.SerialCommunicator;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BufferedSerialPortReader extends SerialPortReader {

    public static BufferedSerialPortReader getInstance(SerialCommunicator communicator, ReaderAction action) {
        return new BufferedSerialPortReader(communicator, action);
    }

    public BufferedSerialPortReader(SerialCommunicator communicator, ReaderAction action) {
        super(communicator, action);
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {
            try {
                byte[] buffer = communicator.readBytes();
                if (buffer != null) {
                    for (byte b : buffer) {
                        if (!(b == 13 || b == 10)) {
                            stringBuilder.append((char) b);
                        } else if (!stringBuilder.isEmpty()) {
                            action.doTask(this.stringBuilder.toString());
                            stringBuilder.setLength(0);
                        }
                    }
                }
            } catch (SerialPortException | NullPointerException ex) {
                log.error("Error en Serial reader event", ex);
            }
        }
    }

}
