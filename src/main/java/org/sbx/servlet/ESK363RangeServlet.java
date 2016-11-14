package org.sbx.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.enums.Bean;
import org.sbx.enums.EnumDateFormat;
import org.sbx.enums.Page;
import org.sbx.messages.impl.ApplicationErrorMessage;
import org.sbx.messages.impl.ApplicationInfoMessage;
import org.sbx.service.Service;
import org.sbx.spring.bo.RecordBO;
import org.sbx.spring.bo.RecordBOImpl.ESK363RecordBO;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363RangeServlet extends Dispatcher {

    private static final Logger logger = LogManager.getLogger(ESK363RangeServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

        RecordBO esk363RecordBO = (ESK363RecordBO) context.getBean(Bean.ESK363_RECORD_BO.toString());

        Date dateFrom = Service.stringToDate(request.getParameter("startDate"), EnumDateFormat.INPUT.getFormat());
        Date dateTo = Service.stringToDate(request.getParameter("endDate"), EnumDateFormat.INPUT.getFormat());

        ArrayList<ESK363DBRecord> resultList = (ArrayList) esk363RecordBO.findByDateRange(dateFrom, dateTo);

        if ((resultList == null) || (resultList.isEmpty()))
            logger.error(ApplicationErrorMessage.DATA_RECEIVE_ERROR.getMessage());
        else
            logger.info(ApplicationInfoMessage.DATA_RECEIVED.getMessage());

        request.setAttribute("records", Service.getJsonByRecordList(resultList));

        this.forward(Page.GET_RECORDS.toString(), request, response);
    }
}
