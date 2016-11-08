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

    private FileDAO esk363FileDAO;

    public ESK363FileBO(){

    }
/*
    private void setEsk363FileDAO(){
        this.esk363FileDAO = new FileDAOFactory.Builder()
                                                .setFileDAOName(DAOClass.ESK363.getClassName())
                                                .makeFileDAO()
                                                .build()
                                                .getFileDAO();
    }
*/
    public void setEsk363FileDAO(FileDAO esk363FileDAO){
        this.esk363FileDAO = esk363FileDAO;
    }

    public void load(File file) {

        // setEsk363FileDAO();

        try {
            getEsk363FileDAO().load(file);
        } catch (FileException ex){
            logger.error(ex);
        }

    }

    public List<File> getFiles(){
        return getEsk363FileDAO().getFiles();
    }

    public List<String> getData(){
        return getEsk363FileDAO().getData();
    }

    public FileDAO getEsk363FileDAO() {
        return esk363FileDAO;
    }
}
