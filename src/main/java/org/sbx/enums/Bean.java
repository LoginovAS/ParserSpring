package org.sbx.enums;

/**
 * Created by aloginov on 03.11.16.
 */
public enum Bean {
    /**
     *  Enum Bean is used for beans initialization in classes via the context.getBean() construction instead of
     * String constants and class names.
     */
    ESK363_RECORD_BO("esk363RecordBO"),

    ESK363_FILE_BO("esk363FileBO");

    private String beanName;

    Bean(String beanName){
        this.beanName = beanName;
    }

    public String toString(){
        return beanName;
    }
}
