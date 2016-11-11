package org.sbx.messages.impl;

import org.sbx.messages.LoggingMessenger;

/**
 * Created by aloginov on 01.11.16.
 */
public enum FileErrorMessage implements LoggingMessenger {

    CANNOT_OPEN_FILE {
        public String getMessage(){
            return "File {} cannot be opened in {} mode.";
        }
    },

    FILE_DOES_NOT_EXIST {
        public String getMessage(){
            return "File {} does not exist.";
        }
    },

    CANNOT_CREATE_FILE {
        public String getMessage(){
            return "File {} cannot be created.";
        }
    };

    public String toString(){
        return getMessage();
    }
}
