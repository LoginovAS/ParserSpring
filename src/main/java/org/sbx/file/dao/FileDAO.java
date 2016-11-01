package org.sbx.file.dao;

import org.sbx.exceptions.FileException;

import java.io.File;
import java.util.List;

/**
 * Created by aloginov on 01.11.16.
 */
public interface FileDAO {

    void load(String fileName) throws FileException;

    List<String> getData();

}
