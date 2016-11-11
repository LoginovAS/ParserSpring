package org.sbx.spring.dao.RecordDAOImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.sbx.messages.impl.ApplicationErrorMessage;
import org.sbx.messages.impl.ApplicationInfoMessage;
import org.sbx.messages.impl.DBErrorMessage;
import org.sbx.messages.impl.DBInfoMessage;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.sbx.spring.dao.RecordDAO;
import org.sbx.spring.model.Record;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363RecordDAO extends HibernateDaoSupport implements RecordDAO {

    private static final Logger logger = LogManager.getLogger(ESK363RecordDAO.class);

    public ESK363RecordDAO(){

    }

    public void save(Record record) {
        getHibernateTemplate().save(record);
    }

    public void saveAll(List<Record> records){
        /**
         * The method is used to save a list of data to the DB using the only commit.
         */
        Session session = null;
        HibernateTemplate hibernateTemplate = null;

        try {
            hibernateTemplate = getHibernateTemplate();
            session = hibernateTemplate.getSessionFactory().openSession();
            if (session.isOpen())
                logger.info(DBInfoMessage.SESSION_OPENED);
            session.beginTransaction();
            for (Record record: records)
                session.save(record);
            session.getTransaction().commit();

            logger.info(DBInfoMessage.DATA_SAVED);

        } catch (Exception ex){
            logger.error(DBErrorMessage.CANNOT_SAVE_DATA);
            if (session == null)
                logger.error(DBErrorMessage.CANNOT_CREATE_SESSION);
            logger.error(ex);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
                if (session.isOpen())
                    logger.error(DBErrorMessage.CANNOT_CLOSE_SESSION);
                else
                    logger.info(DBInfoMessage.SESSION_CLOSED);
            }
        }

    }

    public List findByDateRange(Date dateFrom, Date dateTo) {

        DetachedCriteria criteria = DetachedCriteria.forClass(ESK363DBRecord.class).add(Restrictions.between("recordDate", dateFrom, dateTo));
        List list = getHibernateTemplate().findByCriteria(criteria);

        if (list != null && !list.isEmpty())
            logger.info(ApplicationInfoMessage.DATA_RECEIVED);
        else
            logger.error(ApplicationErrorMessage.DATA_RECEIVE_ERROR);

        return list;
    }
}
