package org.sbx.messages.impl;

import org.sbx.messages.LoggingMessenger;

/**
 * Created by aloginov on 01.11.16.
 */
public enum ApplicationDebugMessage implements LoggingMessenger {

    CANNOT_PARSE_DATE {
        public String getMessage(){
            return "Cannot parse date from given string '{}'";
        }
    };

    public String toString(){
        return getMessage();
    }
}
