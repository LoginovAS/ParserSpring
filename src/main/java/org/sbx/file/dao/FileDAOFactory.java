package org.sbx.file.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by aloginov on 01.11.16.
 */
public class FileDAOFactory {
    private static final Logger logger = LogManager.getLogger(FileDAOFactory.class);

    private FileDAO fileDAO;
    private String fileDAOName;

    public static class Builder{
        private String fileDAOName;
        private FileDAO fileDAO;

        public Builder setFileDAOName(String fileDAOName){
            this.fileDAOName = fileDAOName;
            return this;
        }

        public Builder makeFileDAO(){
            Class clazz = null;
            try {
                clazz = Class.forName(fileDAOName);
                this.fileDAO = (FileDAO) clazz.newInstance();
            } catch (ClassNotFoundException ex){
                logger.error(ex);
            } catch (IllegalAccessException ex){
                logger.error(ex);
            } catch (InstantiationException ex){
                logger.error(ex);
            }

            return this;
        }

        public FileDAOFactory build(){
            return new FileDAOFactory(this);
        }
    }

    private FileDAOFactory(Builder builder) {
        this.fileDAOName = builder.fileDAOName;
        this.fileDAO = builder.fileDAO;
    }

    public FileDAO getFileDAO(){
        return fileDAO;
    }
}
