package org.sbx.servlet;

import org.sbx.enums.Bean;
import org.sbx.enums.EnumDateFormat;
import org.sbx.service.Service;
import org.sbx.spring.bo.RecordBO;
import org.sbx.spring.bo.RecordBOImpl.ESK363RecordBO;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363RangeServlet extends HttpServlet {

    //private RecordBO esk363RecordBO;

    //public void setEsk363RecordBO(RecordBO esk363RecordBO){
    //    this.esk363RecordBO = esk363RecordBO;
    //}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

        RecordBO esk363RecordBO = (ESK363RecordBO) context.getBean(Bean.ESK363_RECORD_BO.toString());

        Date dateFrom = Service.stringToDate(request.getParameter("startDate"), EnumDateFormat.INPUT.getFormat());
        Date dateTo = Service.stringToDate(request.getParameter("endDate"), EnumDateFormat.INPUT.getFormat());

        ArrayList<ESK363DBRecord> resultList = (ArrayList) esk363RecordBO.findByDateRange(dateFrom, dateTo);

        request.setAttribute("records", Service.getJsonByRecordList(resultList));

        RequestDispatcher view = request.getRequestDispatcher("records.jsp");

        view.forward(request, response);
    }
}
