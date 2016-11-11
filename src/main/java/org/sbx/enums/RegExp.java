package org.sbx.enums;

/**
 * Created by aloginov on 01.11.16.
 */
public enum RegExp {
    EXECUTION_LISTENER {
        public String getRegExp() {
            return "((\\d{2})\\.(\\d{2})\\.(\\d{4})\\s+(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3}))\\s+" +
                    "(.+)\\s+(INFO)\\s+(com.\\w+.batch.listener.ExecutionListener -)\\s+(\\d+)";
        }
    };

    public abstract String getRegExp();

    public String toString(){
        return getRegExp();
    }
}
