package org.sbx.file.dao;

/**
 * Created by aloginov on 01.11.16.
 */
public enum DAOClass {
    /**
     * The list of paths to file DAO classes. Can be used for future development.
     */

    ESK363("org.sbx.file.dao.impl.ESK363FileDAO");

    private String className;

    DAOClass(String className){
        this.className = className;
    }
    public String getClassName(){
        return className;
    }
}
