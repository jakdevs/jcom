package com.jakdev.jcom.serial.action;

import com.jakdev.jcom.action.ReaderAction;

public class StdOutSerialReaderAction implements ReaderAction {

    @Override
    public void doTask(String as) {
        System.out.println(as);
    }
}
