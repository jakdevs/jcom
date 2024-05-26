package com.jakdev.jcom.action;

@FunctionalInterface
public interface ReaderAction {

    public static final String PATTERN = "[^-?\\d.]";

    public void doTask(String value);

}
