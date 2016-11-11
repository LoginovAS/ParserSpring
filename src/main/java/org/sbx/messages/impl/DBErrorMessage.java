package org.sbx.messages.impl;

import org.sbx.messages.LoggingMessenger;

/**
 * Created by aloginov on 11.11.16.
 */
public enum DBErrorMessage implements LoggingMessenger {

    CANNOT_SAVE_DATA {
        public String getMessage() {
            return "Data cannot be saved by the reason:";
        }
    },

    CANNOT_CLOSE_SESSION {
        public String getMessage() {
            return "Session cannot be closed.";
        }
    },

    CANNOT_CREATE_SESSION {
        public String getMessage() {
            return "Session cannot be created.";
        }
    }
}
