package org.sbx.main;

import org.sbx.service.Service;
import org.sbx.spring.bo.RecordBO;
import org.sbx.spring.bo.RecordBOImpl.ESK363RecordBO;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aloginov on 01.11.16.
 */
public class App {

    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/config/BeanLocations.xml");

        RecordBO esk363RecordBO = (ESK363RecordBO) applicationContext.getBean("esk363RecordBO");

        Date dateFrom = Service.stringToDate("10.14.2016 17:35:26", "MM.dd.yyyy HH:mm:ss");
        Date dateTo = Service.stringToDate("10.14.2016 17:35:42", "MM.dd.yyyy HH:mm:ss");

        ArrayList<ESK363DBRecord> list = (ArrayList) esk363RecordBO.findByDateRange(dateFrom, dateTo);

        if (!list.isEmpty())
            for (ESK363DBRecord record: list)
                System.out.printf("%s %s - %d\n", Service.dateToString(record.getRecordDate()), record.getLogLevel(), record.getItemCount());

    }

}
