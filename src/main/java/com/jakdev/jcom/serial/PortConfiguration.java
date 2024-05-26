package com.jakdev.jcom.serial;

import lombok.Getter;

@Getter
public class PortConfiguration {

    private final String name;
    private final Integer baudRate;
    private final PortConfigItem parity;
    private final Integer dataBits;
    private final PortConfigItem stopBits;

    public PortConfiguration(String name, Integer baudRate, PortConfigItem parity, Integer dataBits, PortConfigItem stopBits) {
        this.name = name;
        this.baudRate = baudRate;
        this.parity = parity;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
    }
}
