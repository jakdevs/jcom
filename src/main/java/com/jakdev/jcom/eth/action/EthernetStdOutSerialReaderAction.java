package com.jakdev.jcom.eth.action;

import com.jakdev.jcom.action.ReaderAction;

public class EthernetStdOutSerialReaderAction implements ReaderAction {

    @Override
    public void doTask(String as) {
        System.out.println(as);
    }

}
