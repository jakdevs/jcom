/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakdev.jcom.exception;

/**
 *
 * @author edgar
 */
public class EventListenerNotSetException extends Exception {

    public EventListenerNotSetException() {
    }

    public EventListenerNotSetException(String message) {
        super(message);
    }

    public EventListenerNotSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventListenerNotSetException(Throwable cause) {
        super(cause);
    }

}
