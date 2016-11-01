package org.sbx.file.bo;

import java.io.File;
import java.util.List;

/**
 * Created by aloginov on 01.11.16.
 */
public interface FileBO {

    void load(String fileName);

    List<String> getData();

}
