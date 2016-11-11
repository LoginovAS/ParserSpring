package org.sbx.exceptions;

import org.sbx.messages.LoggingMessenger;

import java.io.IOException;

/**
 * Created by aloginov on 01.11.16.
 */
public class FileException extends IOException{

    private LoggingMessenger loggingMessenger;

    public FileException(){
        super();
    }

    public FileException(String msg){
        super(msg);
    }

    public FileException(LoggingMessenger loggingMessenger){
        this.loggingMessenger = loggingMessenger;
    }

    public String getErrorMessage(){
        return loggingMessenger.getMessage();
    }

}
