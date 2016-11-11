package org.sbx.messages.impl;

import org.sbx.messages.LoggingMessenger;

/**
 * Created by aloginov on 11.11.16.
 */
public enum ApplicationInfoMessage implements LoggingMessenger {

    DATA_RECEIVED {
        public String getMessage(){
            return "Data received from DB";
        }
    }
}
