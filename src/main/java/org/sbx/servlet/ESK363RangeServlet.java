package org.sbx.servlet;

import org.sbx.enums.Bean;
import org.sbx.enums.EnumDateFormat;
import org.sbx.service.Service;
import org.sbx.spring.bo.RecordBO;
import org.sbx.spring.bo.RecordBOImpl.ESK363RecordBO;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363RangeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Date dateFrom = Service.stringToDate(request.getParameter("startDate"), EnumDateFormat.INPUT.getFormat());
        Date dateTo = Service.stringToDate(request.getParameter("endDate"), EnumDateFormat.INPUT.getFormat());

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/src/main/webapp/WEB-INF/config/BeanLocations.xml");

        RecordBO esk363RecordBO = (ESK363RecordBO) applicationContext.getBean(Bean.ESK363_RECORD_BO.toString());

        ArrayList<ESK363DBRecord> resultList = (ArrayList) esk363RecordBO.findByDateRange(dateFrom, dateTo);

        request.setAttribute("records", resultList);

        RequestDispatcher view = request.getRequestDispatcher("records.jsp");

        view.forward(request, response);
    }
}
