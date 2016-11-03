package org.sbx.spring.dao.RecordDAOImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.sbx.enums.EnumDateFormat;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.sbx.spring.dao.RecordDAO;
import org.sbx.spring.model.Record;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363RecordDAO extends HibernateDaoSupport implements RecordDAO {

    private static final Logger logger = LogManager.getLogger(ESK363RecordDAO.class);

    public void save(Record record) {
        getHibernateTemplate().save(record);
    }

    public void saveAll(List<Record> records){
        Session session = null;
        HibernateTemplate hibernateTemplate = null;

        try {
            hibernateTemplate = getHibernateTemplate();
            session = hibernateTemplate.getSessionFactory().openSession();
            session.beginTransaction();
            for (Record record: records)
                session.save(record);
            session.getTransaction().commit();
        } catch (Exception ex){
            logger.error(ex);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public List findByDateRange(Date dateFrom, Date dateTo) {

        DetachedCriteria criteria = DetachedCriteria.forClass(ESK363DBRecord.class).add(Restrictions.between("recordDate", dateFrom, dateTo));
        List list = getHibernateTemplate().findByCriteria(criteria);
        return list;
    }
}
