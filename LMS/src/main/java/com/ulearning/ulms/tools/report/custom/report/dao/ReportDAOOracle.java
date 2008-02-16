/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.report.dao;

import com.ulearning.ulms.tools.report.custom.report.exceptions.ReportDAOSysException;
import com.ulearning.ulms.tools.report.custom.report.form.ReportForm;
import com.ulearning.ulms.tools.report.custom.subreport.form.SubReportForm;
import com.ulearning.ulms.tools.report.custom.subreport.bean.SubReportHelper;
import com.ulearning.ulms.tools.report.custom.fieldItem.form.CustomFieldItemForm;
import com.ulearning.ulms.tools.report.custom.fieldItem.bean.CustomFieldItemHelper;
import com.ulearning.ulms.tools.report.custom.fieldItem.dao.CustomFieldItemDAOImpl;
import com.ulearning.ulms.tools.report.custom.fieldItem.exceptions.CustomFieldItemDAOSysException;
import com.ulearning.ulms.tools.report.custom.task.form.TaskForm;
import com.ulearning.ulms.tools.report.custom.task.dao.TaskDAOOracle;
import com.ulearning.ulms.tools.report.custom.task.exceptions.TaskDAOSysException;
import com.ulearning.ulms.tools.report.custom.tasktime.model.TaskTimeForm;
import com.ulearning.ulms.tools.report.custom.tasktime.dao.TaskTimeDAOOracle;
import com.ulearning.ulms.tools.report.custom.tasktime.exceptions.TaskTimeDAOSysException;
import com.ulearning.ulms.tools.report.general.model.MReportForm;
import com.ulearning.ulms.tools.report.general.model.FieldItemForm;
import com.ulearning.ulms.tools.report.general.bean.GeneralHelper;
import com.ulearning.ulms.tools.report.general.util.Report_Constants;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;


public class ReportDAOOracle extends ReportDAOImpl
{

        /**
         * Insert a new report record to database
         * @param reportForm   the value object to be added
         * @throws ReportDAOSysException
         */
        /*
       public int addReport(ReportForm reportForm) throws ReportDAOSysException
       {
               java.sql.Date nowDate = new java.sql.Date(System.currentTimeMillis());
               java.sql.Time nowTime = new java.sql.Time(System.currentTimeMillis());
               String createDate  = "to_date('" + nowDate+" " +nowTime+ "','yyyy-mm-dd  hh24:mi:ss')";
               String updateDate =  createDate;

               String sqlStr = "insert into R_Report_Tab values(reportID.nextval,'" +
                       reportForm.getReportName() + "','" +
                       reportForm.getReportType() + "','" +
                       reportForm.getReportFormat() + "'," +
                       reportForm.getModulID() + "," +
                       reportForm.getRelationID() + "," +
                       reportForm.getCategoryID() + ",'" +
                       reportForm.getJasperReportXMLPath() + "'," +
                       reportForm.getUserID() + "," +
                       createDate + "," +
                       updateDate + ",'" +
                       reportForm.getRemark() + "')";
               {
               }
               try
               {
                   LogUtil.debug("system", "[ReportDAOOracle]====================the sql string is : " + sqlStr);

               } catch (ULMSSysException se)
               {
                   throw new ReportDAOSysException("SQLException while addReport: sql = " + sqlStr + " :\n" + se);
               }

               int reportID=-1;
               sqlStr="select ReportID from R_Report_Tab where "
                       +"ReportName='"+reportForm.getReportName() + "' and "
                       +"ReportType='"+reportForm.getReportType() + "' and "
                       +"to_char(CreateDate,'yyyy-mm-dd hh24:mi:ss')='"
                       + nowDate+" " +nowTime+ "'"+" order by CreateDate desc";
               try
               {
                       LogUtil.debug("system","[ReportDAOImpl]====================the sql string is : " + sqlStr);
                       if (rs.next())
                       {
                               reportID = rs.getInt(1);
                       }
               }
               catch (SQLException se)
               {
                       throw new ReportDAOSysException("SQLException while updating " + "report; Serial = " + reportID + " :\n" + se);
               }
               catch (ULMSSysException se)
               {
                       throw new ReportDAOSysException("SQLException while updating " + "report; Serial = " + reportID + " :\n" + se);
               }
               finally
               {
                       try
                       {
                       }
                       catch (SQLException se) { }
               }
               return reportID;
       }
        */
        /**
         * Update reportInfo by the new Form
         * @param reportForm   value object for changed
         * @throws ReportDAOSysException
         */
        /*
     public void updateReport(ReportForm reportForm) throws ReportDAOSysException
     {
             java.sql.Date nowDate = new java.sql.Date(System.currentTimeMillis());
             java.sql.Time nowTime = new java.sql.Time(System.currentTimeMillis());
             String updateDate  = "to_date('" + nowDate+" " +nowTime+ "','yyyy-mm-dd  hh24:mi:ss')";
             String sqlStr = "update R_Report_Tab set " +
                 "ReportName = '" + reportForm.getReportName() + "'," +
                 "ReportType = '" + reportForm.getReportType() + "'," +
                 "ReportFormat = '" + reportForm.getReportFormat() + "', " +
                 "ModulID = '" + reportForm.getModulID() + "', " +
                 "RelationID = " + reportForm.getRelationID() + ", " +
                 "CategoryID = " + reportForm.getCategoryID() + ", " +
                 "JasperReportXMLPath = '" + reportForm.getJasperReportXMLPath() + "'," +
                 "UserID = " + reportForm.getUserID() + ", " +
                 "UpdateDate = " + updateDate + ", " +
                 "Remark = '" + reportForm.getRemark() + "'"+
                 "where ReportID = " + reportForm.getReportID();
         try
         {
                 LogUtil.debug("system", "[ReportDAOOracle]====================the sql string is : " + sqlStr);

         } catch (ULMSSysException se)
         {
             throw new ReportDAOSysException("SQLException while updateReport sql = " + sqlStr + " :\n" + se);
         }
     }
        */

}
