package com.jakdev.jcom.serial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class PortConfigItem implements Serializable {

    private final int id;
    private final String description;

}
