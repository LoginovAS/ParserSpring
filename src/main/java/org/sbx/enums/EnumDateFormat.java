package org.sbx.enums;

/**
 * Created by aloginov on 01.11.16.
 */
public enum EnumDateFormat {

    DATABASE {
        public String getFormat() {
            return "yyyy-MM-dd HH:mm:ss";
        }
    },

    INPUT {
        public String getFormat() {
            return "MM.dd.yyyy HH:mm:ss";
        }
    };

    public abstract String getFormat();

}
