package org.sbx.file.bo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.exceptions.FileException;
import org.sbx.file.bo.FileBO;
import org.sbx.file.dao.FileDAO;

import java.io.File;
import java.util.List;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363FileBO implements FileBO {

    private static final Logger logger = LogManager.getLogger(ESK363FileBO.class);

    /*
     * The the field esk363FileDAO will be initialized by Spring. It described in beans/ESK363File.xml
     */
    private FileDAO esk363FileDAO;

    public ESK363FileBO(){

    }

    public void setEsk363FileDAO(FileDAO esk363FileDAO){
        this.esk363FileDAO = esk363FileDAO;
    }

    public void load(File file) {
        /**
         * The method calls load(File file) method of File DAO to load data into the ArrayList.
         */
        try {
            getEsk363FileDAO().load(file);
        } catch (FileException ex){
            logger.error(ex);
        }

    }

    public List<File> getFiles(){
        /**
         * The method returns the list of files received from File DAO.
         */
        return getEsk363FileDAO().getFiles();
    }

    public List<String> getData(){
        /**
         * The method returns the list of records received from File DAO in String format.
         */
        return getEsk363FileDAO().getData();
    }

    public FileDAO getEsk363FileDAO() {
        return esk363FileDAO;
    }
}
