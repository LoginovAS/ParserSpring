package org.sbx.enums;

/**
 * Created by aloginov on 11.11.16.
 */
public enum Page {

    INDEX("/index.jsp"),

    GET_RECORDS("/getRecords.jsp");

    private String pageName;

    Page(String pageName){
        this.pageName = pageName;
    }

    private String getPageName(){
        return pageName;
    }

    public String toString(){
        return this.getPageName();
    }
}
