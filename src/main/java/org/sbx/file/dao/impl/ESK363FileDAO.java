package org.sbx.file.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.exceptions.FileException;
import org.sbx.file.dao.FileDAO;
import org.sbx.messages.impl.FileErrorMessage;
import org.sbx.messages.impl.FileInfoMessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363FileDAO implements FileDAO {

    private static final Logger logger = LogManager.getLogger(ESK363FileDAO.class);

    private File file;
    private File directory;
    private Scanner scanner;

    List<String> list = new ArrayList();

    public File getDirectory(){
        return directory;
    }

    public void setDirectory(String directory){
        this.directory = new File(directory);
    }

    public List<File> getFiles(){
        List<File> fileList = new ArrayList<File>();
        if (directory.isDirectory())
            for (File file: directory.listFiles())
                fileList.add(file);

        return fileList;
    }

    public void load(File file) throws FileException{
        initFile(file);
        openFileForInput();

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }

        this.close();
    }

    private void initFile(File file) throws FileException {
        this.file = file;
        if (!isFileExists())
            throw new FileException(FileErrorMessage.FILE_DOES_NOT_EXIST);
    }

    private void openFileForInput(){

        boolean success = false;

        if (!isFileExists()) {
            logger.error(FileErrorMessage.FILE_DOES_NOT_EXIST.getMessage(), file.getAbsolutePath());
        } else {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                this.scanner = new Scanner(inputStream);

                if (this.scanner != null) {
                    success = true;
                }
            } catch (IOException ex){
                logger.error(ex);
            } finally {
                if (success)
                    logger.debug(FileInfoMessage.FILE_OPENED.getMessage(), file.getAbsolutePath(), FileInfoMessage.READ_MODE);
                else
                    logger.debug(FileErrorMessage.CANNOT_OPEN_FILE.getMessage(), file.getAbsolutePath(), FileInfoMessage.READ_MODE);
            }
        }
    }

    private void close(){
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }

    public List<String> getData(){
        return list;
    }

    private boolean isFileExists() {
        boolean f = false;
        if (this.file != null)
            if (this.file.exists())
                f = true;

        return f;
    }
}
