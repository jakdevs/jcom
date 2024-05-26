package com.jakdev.jcom.serial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jssc.SerialPortList;
import lombok.Getter;

@Getter
public final class PortData {

    private static final List<String> availablePorts = new ArrayList<>();
    private static final List<Integer> baudRate = new ArrayList<>();
    private static final List<PortConfigItem> parities = new ArrayList<>();
    private static final List<Integer> dataBits = new ArrayList<>();
    private static final List<PortConfigItem> stopBits = new ArrayList<>();

    public static PortConfigItem findParity(Integer value) {
        if (value != null) {
            for (PortConfigItem p : PortData.parities) {
                if (p.getId() == value) {
                    return p;
                }
            }
        }
        return null;
    }

    public static PortConfigItem findStopBits(Integer value) {
        if (value != null) {
            for (PortConfigItem s : PortData.stopBits) {
                if (s.getId() == value) {
                    return s;
                }
            }
        }
        return null;
    }

    static {
        baudRate.add(300);
        baudRate.add(600);
        baudRate.add(1200);
        baudRate.add(2400);
        baudRate.add(4800);
        baudRate.add(9600);
        baudRate.add(19200);
        baudRate.add(38400);
        dataBits.add(5);
        dataBits.add(6);
        dataBits.add(7);
        dataBits.add(8);
        parities.add(new PortConfigItem(0, "None"));
        parities.add(new PortConfigItem(1, "Odd"));
        parities.add(new PortConfigItem(2, "Even"));
        parities.add(new PortConfigItem(3, "Mark"));
        parities.add(new PortConfigItem(4, "Space"));
        stopBits.add(new PortConfigItem(1, "1"));
        stopBits.add(new PortConfigItem(2, "1.5"));
        stopBits.add(new PortConfigItem(3, "2"));
        searchForPorts();
    }

    private static void searchForPorts() {
        String[] ports = SerialPortList.getPortNames();
        Collections.addAll(availablePorts, ports);
    }
}
