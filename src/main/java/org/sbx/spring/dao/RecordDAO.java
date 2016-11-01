package org.sbx.spring.dao;

import org.sbx.spring.model.Record;

import java.util.Date;
import java.util.List;

/**
 * Created by aloginov on 01.11.16.
 */
public interface RecordDAO {

    void save(Record record);

    List findByDateRange(Date dateFrom, Date dateTo);

}
