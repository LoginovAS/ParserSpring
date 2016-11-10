package org.sbx.servlet;

import org.sbx.enums.Bean;
import org.sbx.enums.EnumDateFormat;
import org.sbx.enums.RegExp;
import org.sbx.file.bo.FileBO;
import org.sbx.file.bo.impl.ESK363FileBO;
import org.sbx.file.builders.impl.ESK363RecordBuilder;
import org.sbx.file.parser.Parser;
import org.sbx.spring.bo.RecordBO;
import org.sbx.spring.bo.RecordBOImpl.ESK363RecordBO;
import org.sbx.spring.model.Record;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by aloginov on 01.11.16.
 */
public class LoadESK363DataServlet extends HttpServlet {

    //private RecordBO esk363RecordBO;

    //private FileBO esk363FileBO;

    //public void setEsk363RecordBO(RecordBO esk363RecordBO){
    //    this.esk363RecordBO = esk363RecordBO;
    //}

    //public void setEsk363FileBO(FileBO esk363FileBO){
    //    this.esk363FileBO = esk363FileBO;
    //}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

        RecordBO esk363RecordBO = (ESK363RecordBO) context.getBean(Bean.ESK363_RECORD_BO.toString());

        FileBO esk363FileBO = (ESK363FileBO ) context.getBean(Bean.ESK363_FILE_BO.toString());

        // Debug
        PrintWriter pw = response.getWriter();
        if (esk363RecordBO == null)
            pw.println("esk363RecordBO is NULL.");
        if (esk363FileBO == null)
            pw.println("esk363FileBO is NULL.");





        Parser parser = new Parser();

        List<String> list = new ArrayList<String>();

        List<Record> records = new ArrayList<Record>();

        Date date = null;
        Date tmpDate = null;
        Date mainDate = null;
        int itemCount = 0;
        int tmpIC = 0;
        String logLevel;
        String tmpLL = "";

        ESK363DBRecord esk363DBRecord = null;

        for (File file: esk363FileBO.getFiles()){
            esk363FileBO.load(file);
            list.addAll(esk363FileBO.getData());
        }


        ESK363RecordBuilder esk363RecordBuilder;

        if (!list.isEmpty()){
            parser.setRegExp(RegExp.EXECUTION_LISTENER.getRegExp());
            for (String s: list){
                parser.setTargetString(s);
                parser.parse();

                esk363RecordBuilder = new ESK363RecordBuilder();
                date = parser.getDate(EnumDateFormat.INPUT.getFormat());

                if (date == null) continue;

                if (tmpDate != null)
                    if (date.equals(tmpDate)) {
                        tmpIC += parser.getItemCount();
                        continue;
                    } else {
                        mainDate = tmpDate;
                        logLevel = tmpLL;
                        itemCount = tmpIC;

                        tmpDate = date;
                        tmpLL = parser.getLogLevel();
                        tmpIC = parser.getItemCount();
                    }
                else {
                    tmpDate = date;
                    tmpLL = parser.getLogLevel();
                    tmpIC = parser.getItemCount();
                    continue;
                }

                esk363RecordBuilder.addDate(mainDate);
                esk363RecordBuilder.setLogLevel(logLevel);
                esk363RecordBuilder.addItemCount(itemCount);
                esk363DBRecord = esk363RecordBuilder.build();

                records.add(esk363DBRecord);
            }

            esk363RecordBO.saveAll(records);
        }

        RequestDispatcher view = request.getRequestDispatcher("index.jsp");

        view.forward(request, response);

    }

}
