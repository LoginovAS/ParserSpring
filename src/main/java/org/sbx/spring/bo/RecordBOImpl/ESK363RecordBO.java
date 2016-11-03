package org.sbx.spring.bo.RecordBOImpl;

import org.sbx.spring.bo.RecordBO;
import org.sbx.spring.dao.RecordDAO;
import org.sbx.spring.model.Record;

import java.util.Date;
import java.util.List;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363RecordBO implements RecordBO {

    private RecordDAO esk363RecordDAO;

    public void setEsk363RecordDAO(RecordDAO esk363RecordDAO){
        this.esk363RecordDAO = esk363RecordDAO;
    }

    public void saveAll(List<Record> records){
        esk363RecordDAO.saveAll(records);
    }

    public void save(Record record) {
        esk363RecordDAO.save(record);
    }

    public List findByDateRange(Date dateFrom, Date dateTo) {
        return esk363RecordDAO.findByDateRange(dateFrom, dateTo);
    }
}
