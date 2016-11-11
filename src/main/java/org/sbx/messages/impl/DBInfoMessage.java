package org.sbx.messages.impl;

import org.sbx.messages.LoggingMessenger;

/**
 * Created by aloginov on 11.11.16.
 */
public enum DBInfoMessage implements LoggingMessenger {

    DATA_SAVED {
        public String getMessage() {
            return "Data successfully saved.";
        }
    },

    SESSION_CLOSED {
        public String getMessage() {
            return "Session closed.";
        }
    },

    SESSION_OPENED {
        public String getMessage() {
            return "DB session opened.";
        }
    }
}
