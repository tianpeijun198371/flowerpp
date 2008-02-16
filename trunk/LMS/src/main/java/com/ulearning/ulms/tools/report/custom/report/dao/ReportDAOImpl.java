/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.report.dao;

import com.ulearning.ulms.tools.report.custom.report.form.ReportForm;
import com.ulearning.ulms.tools.report.custom.report.exceptions.ReportDAOSysException;
import com.ulearning.ulms.tools.report.custom.report.model.ReportModel;
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
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.Serializable;


public class ReportDAOImpl implements ReportDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public int addReport(ReportForm details) throws ReportDAOSysException
        {
                Serializable s = null;
                try
                {
                        s = HibernateDAO.add(details.getReportModel());
                        details.setReportID(s.hashCode());
                        Date now = new Date();
                        details.setCreateDate(now);
                        details.setUpdateDate(now);
                        HibernateDAO.update(details.getReportModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ReportDAOSysException("" + e);
                }
                return s.hashCode();
        }

        public void updateReport(ReportForm details) throws ReportDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getReportModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ReportDAOSysException("" + e);
                }
        }

        public void insertReportField(HttpServletRequest request, int reportID)
        {
                String reportType = request.getParameter("reportType");
                MReportForm mReportForm = null;
                FieldItemForm fieldItemForm = null;
                SubReportForm subReportForm = null;
                CustomFieldItemForm customFieldItemForm = null;
                int mReportID = 0;
                String mReportName = "";
                int fieldItemID = 0;
                List fieldItemFormList = null;
                GeneralHelper generalHelper = new GeneralHelper();
                SubReportHelper subReportHelper = new SubReportHelper();
                CustomFieldItemHelper customFieldItemHelper = new CustomFieldItemHelper();
                List MReportFormList = generalHelper.getMReportFormList(reportType);
                for (Iterator it = MReportFormList.iterator(); it.hasNext();)
                {
                        mReportForm = (MReportForm) it.next();
                        mReportID = mReportForm.getMReportID();
                        mReportName = mReportForm.getMReportName();
                        //add sub report
                        subReportForm = new SubReportForm();
                        subReportForm.setReportID(reportID);
                        subReportForm.setMReportID(mReportID);
                        subReportForm.setReportName(mReportName);
                        subReportForm.setState("1");
                        subReportHelper.addSubReport(subReportForm);

                        //add field of sub report
                        fieldItemFormList = generalHelper.getFieldItemFormList(mReportID);
                        for (Iterator itera = fieldItemFormList.iterator(); itera.hasNext();)
                        {
                                fieldItemForm = (FieldItemForm) itera.next();
                                fieldItemID = fieldItemForm.getFieldItemID();
                                customFieldItemForm = new CustomFieldItemForm();
                                customFieldItemForm.setReportID(reportID);
                                customFieldItemForm.setMReportID(mReportID);
                                customFieldItemForm.setFieldItemID(fieldItemID);
                                customFieldItemForm.setNewItemName(fieldItemForm.getItemName());
                                customFieldItemForm.setSequence(0);
                                customFieldItemHelper.addCustomFieldItem(customFieldItemForm);
                        }
                }

        }

        public void updateReportField(HttpServletRequest request, int reportID) throws ReportDAOSysException
        {
                //delete sub report
                SubReportHelper SubReportHelper = new SubReportHelper();
                try
                {
                        SubReportHelper.removeSubReport(reportID);
                }
                catch (Exception udse)
                {
                        udse.printStackTrace();
                }

                CustomFieldItemDAOImpl customFieldItemDAOImpl = new CustomFieldItemDAOImpl();
                try
                {
                        customFieldItemDAOImpl.removeCustomFieldItem(reportID);
                }
                catch (CustomFieldItemDAOSysException e)
                {
                        e.printStackTrace();
                        throw new ReportDAOSysException("SQLException while updating " + "report; Serial = " + reportID + " :\n" + e);
                }
                String reportType = request.getParameter("reportType");
                MReportForm mReportForm = null;
                FieldItemForm fieldItemForm = null;
                SubReportForm subReportForm = null;
                CustomFieldItemForm customFieldItemForm = null;
                int mReportID = 0;
                String mReportName = "";
                int fieldItemID = 0;
                List fieldItemFormList = null;
                GeneralHelper generalHelper = new GeneralHelper();
                SubReportHelper subReportHelper = new SubReportHelper();
                CustomFieldItemHelper customFieldItemHelper = new CustomFieldItemHelper();
                List MReportFormList = generalHelper.getMReportFormList(reportType);

                String itemName = "";
                String sequenceStr = "";
                int sequence = 0;
                String tmp = "";
                for (Iterator it = MReportFormList.iterator(); it.hasNext();)
                {
                        mReportForm = (MReportForm) it.next();
                        mReportID = mReportForm.getMReportID();

                        if (request.getParameter("mReportState" + mReportID) == null)
                        {
                                continue;
                        }
                        mReportName = request.getParameter("mReportName" + mReportID);

                        //add sub report
                        subReportForm = new SubReportForm();
                        subReportForm.setReportID(reportID);
                        subReportForm.setMReportID(mReportID);
                        subReportForm.setReportName(mReportName);
                        subReportForm.setState("1");
                        subReportHelper.addSubReport(subReportForm);

                        //add field of sub report
                        fieldItemFormList = generalHelper.getFieldItemFormList(mReportID);
                        for (Iterator itera = fieldItemFormList.iterator(); itera.hasNext();)
                        {
                                fieldItemForm = (FieldItemForm) itera.next();
                                fieldItemID = fieldItemForm.getFieldItemID();
                                tmp = request.getParameter("customFieldID" + fieldItemID);
                                if (tmp == null)
                                {
                                        continue;
                                }
                                itemName = request.getParameter("itemName" + fieldItemID);
                                sequenceStr = request.getParameter("sequence" + fieldItemID);
                                if (sequenceStr != null)
                                {
                                        sequence = Integer.parseInt(sequenceStr);
                                }
                                customFieldItemForm = new CustomFieldItemForm();
                                customFieldItemForm.setReportID(reportID);
                                customFieldItemForm.setMReportID(mReportID);
                                customFieldItemForm.setFieldItemID(fieldItemID);
                                customFieldItemForm.setNewItemName(itemName);
                                customFieldItemForm.setSequence(sequence);
                                customFieldItemHelper.addCustomFieldItem(customFieldItemForm);
                        }
                }

        }

        public void updateReportTask(HttpServletRequest request, int reportID) throws ReportDAOSysException
        {
                TaskForm taskForm = new TaskForm();
                taskForm.setReportID(reportID);
                if (request.getParameter("taskType") == null)
                {
                        taskForm.setTaskType("0");
                }
                else
                {
                        taskForm.setTaskType(request.getParameter("taskType"));
                }
                taskForm.setEmail(request.getParameter("email"));
                if (request.getParameter("state") != null)
                {
                        taskForm.setState("1");
                }
                else
                {
                        taskForm.setState("0");
                }
                TaskDAOOracle taskDAOOracle = new TaskDAOOracle();
                int taskID = 0;
                try
                {
                        taskDAOOracle.deleteTaskReport(reportID);
                        taskID = taskDAOOracle.insertTask(taskForm);
                }
                catch (TaskDAOSysException e)
                {
                        e.printStackTrace();
                }
                TaskTimeForm taskTimeForm = new TaskTimeForm();
                taskTimeForm.setTaskID(taskID);
                String taskTime = "0001-01-01 ";
                if (request.getParameter("taskType") == null)
                {
                        return;
                }
                if (request.getParameter("taskType").equals(Report_Constants.TASK_NOT_CIRCLE_TYPE))
                {
                        taskTime = request.getParameter("datetime") + ":00";
                }
                else if (request.getParameter("taskType").equals(Report_Constants.TASK_DAY_TYPE))
                {
                        taskTime = "0001-01-01 " + request.getParameter("hour") + ":"
                                + request.getParameter("minute") + ":00";
                }
                else if (request.getParameter("taskType").equals(Report_Constants.TASK_MONTH_TYPE))
                {
                        taskTime = "0001-01-" + request.getParameter("day") + " "
                                + request.getParameter("hour") + ":"
                                + request.getParameter("minute") + ":00";
                }
                else if (request.getParameter("taskType").equals(Report_Constants.TASK_YEAR_TYPE))
                {
                        taskTime = "0001-" + request.getParameter("month") + "-"
                                + request.getParameter("day") + " "
                                + request.getParameter("hour") + ":"
                                + request.getParameter("minute") + ":00";
                }
                else if (request.getParameter("taskType").equals(Report_Constants.TASK_WEEK_TYPE))
                {
                        int d = Integer.parseInt(request.getParameter("week")) + 1;
                        taskTime = "0001-01-0" + String.valueOf(d) + " "
                                + request.getParameter("hour") + ":"
                                + request.getParameter("minute") + ":00";
                }
                taskTimeForm.setTaskTimeStr(taskTime);
                taskTimeForm.setNotExecuteCondition(request.getParameter("notExecuteCondition"));

                TaskTimeDAOOracle taskTimeDAOOracle = new TaskTimeDAOOracle();
                try
                {
                        taskTimeDAOOracle.insertTaskTime(taskTimeForm);
                }
                catch (TaskTimeDAOSysException e)
                {
                        e.printStackTrace();
                }
        }


        /**
         * Remove the report from database by the reportID
         *
         * @param reportID
         * @throws ReportDAOSysException
         */
        public void removeReport(String reportID) throws ReportDAOSysException
        {
                String sqlStr = "delete R_Report_Tab where ReportID = " + reportID;
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new ReportDAOSysException("SQLException while updating " + "report; Serial = " + reportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * Get the report info via the unique reportID
         *
         * @param reportID
         * @return the prepared reportForm, default is null
         * @throws ReportDAOSysException
         */
        public ReportForm getReport(int reportID) throws ReportDAOSysException
        {
                ReportForm bf = new ReportForm();
                ReportForm res = null;
                List tmList = null;
                String hql = " from ReportModel where ReportID = " + reportID;
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ReportDAOSysException("" + e);
                }
                if ((tmList != null) && (tmList.size() > 0))
                {
                        ReportModel bm = (ReportModel) tmList.get(0);
                        res = bf.getReportForm(bm);
                }
                return res;
        }

        /**
         * Get the report list by the catalogID
         *
         * @param ModulID relationID     CategoryID     UserID
         * @return The prepared arraylist object,default size is 0
         * @throws ReportDAOSysException
         */
        public List getReportList(int ModulID, int RelationID, int CategoryID, int UserID) throws ReportDAOSysException
        {
                ReportForm bf = new ReportForm();
                ReportModel bm = null;
                ArrayList doccontentList = new ArrayList();
                List tmList = null;
                String hql = " from ReportModel where ModulID = " + ModulID
                        + " and RelationID=" + RelationID
                        + " and CategoryID=" + CategoryID
                        + " and UserID=" + UserID;
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ReportDAOSysException("" + e);
                }
                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (ReportModel) tmList.get(i);
                        doccontentList.add(bf.getReportForm(bm));
                }
                return doccontentList;
        }

        public List getReportList(int ModulID, int RelationID, int UserID) throws ReportDAOSysException
        {
                ReportForm bf = new ReportForm();
                ReportModel bm = null;
                ArrayList doccontentList = new ArrayList();
                List tmList = null;
                String hql = " from ReportModel where ModulID = " + ModulID
                        + " and RelationID=" + RelationID
                        + " and UserID=" + UserID;
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ReportDAOSysException("" + e);
                }
                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (ReportModel) tmList.get(i);
                        doccontentList.add(bf.getReportForm(bm));
                }
                return doccontentList;
        }

        /**
         * Convert the resultSet object to reportForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted reportForm
         */
        private ReportForm convertRs2Form(ResultSet rs)
        {
                ReportForm bf = new ReportForm();
                int rsIndex = 1;
                try
                {
                        bf.setReportID(rs.getInt(rsIndex++));
                        bf.setReportName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setReportType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setReportFormat(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setModulID(rs.getInt(rsIndex++));
                        bf.setRelationID(rs.getInt(rsIndex++));
                        bf.setCategoryID(rs.getInt(rsIndex++));
                        bf.setJasperReportXMLPath(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setUserID(rs.getInt(rsIndex++));
                        bf.setCreateDate(rs.getDate(rsIndex++));
                        bf.setUpdateDate(rs.getDate(rsIndex++));
                        bf.setRemark(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return bf;
        }
}
