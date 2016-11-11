package org.sbx.messages.impl;

import org.sbx.messages.LoggingMessenger;

/**
 * Created by aloginov on 11.11.16.
 */
public enum ApplicationErrorMessage implements LoggingMessenger {
    DATA_RECEIVE_ERROR {
        public String getMessage() {
            return "Cannot receive data from DB";
        }
    }
}
