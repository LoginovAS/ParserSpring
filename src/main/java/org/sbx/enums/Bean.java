package org.sbx.enums;

/**
 * Created by aloginov on 03.11.16.
 */
public enum Bean {

    ESK363_RECORD_BO("esk363RecordBO"),

    ESK363_FILE_BO("esk363FileBO"),

    DATA_MANAGER("dataManager");

    private String beanName;

    Bean(String beanName){
        this.beanName = beanName;
    }

    public String toString(){
        return beanName;
    }
}
