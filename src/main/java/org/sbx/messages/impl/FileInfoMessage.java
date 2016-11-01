package org.sbx.messages.impl;

import org.sbx.messages.LoggingMessenger;

/**
 * Created by aloginov on 01.11.16.
 */
public enum FileInfoMessage implements LoggingMessenger{

    FILE_OPENED {
        public String getMessage(){
            return "File {} opened in {} mode.";
        }
    },

    FILE_CREATEAD {
        public String getMessage(){
            return "File {} has been successfully created.";
        }
    },

    FILE_EXISTS {
        public String getMessage() {
            return "File {} exists.";
        }
    },

    READ_MODE {
        public String getMessage() {
            return "read";
        }
    },

    WRITE_MODE {
        public String getMessage() {
            return "write";
        }
    },

    WILL_CREATE_FILE {
        public String getMessage(){
            return "File will be created.";
        }
    };

    public String toString(){
        return getMessage();
    }

}
