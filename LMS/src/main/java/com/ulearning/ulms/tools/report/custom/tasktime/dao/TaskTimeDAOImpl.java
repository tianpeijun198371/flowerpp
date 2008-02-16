package com.ulearning.ulms.tools.report.custom.tasktime.dao;

import com.ulearning.ulms.tools.report.custom.tasktime.model.TaskTimeForm;
import com.ulearning.ulms.tools.report.custom.tasktime.model.TaskTimeModel;
import com.ulearning.ulms.tools.report.custom.tasktime.exceptions.TaskTimeDAOSysException;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.DateTimeUtil;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:09:41
 * To change this template use File | Settings | File Templates.
 */
public class TaskTimeDAOImpl implements TaskTimeDAO
{
        public Serializable insertTaskTime(TaskTimeForm details) throws TaskTimeDAOSysException
        {
                Serializable s = null;
                try
                {
                        s = HibernateDAO.add(details.getTaskTimeModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TaskTimeDAOSysException("" + e);
                }
                return s;
        }

        public void updateTaskTime(TaskTimeForm details) throws TaskTimeDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getTaskTimeModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TaskTimeDAOSysException("" + e);
                }
        }

        public void deleteTaskTime(int taskTimeID) throws TaskTimeDAOSysException
        {
                String hql = " from TaskTimeModel where TaskID = " + taskTimeID;
                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TaskTimeDAOSysException("" + e);
                }
        }

        public List getTaskTimeFormList(int taskID) throws TaskTimeDAOSysException
        {
                TaskTimeForm bf = new TaskTimeForm();
                TaskTimeModel bm = null;
                ArrayList doccontentList = new ArrayList();
                List tmList = null;
                String hql = " from TaskTimeModel where taskID = " + taskID;
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TaskTimeDAOSysException("" + e);
                }
                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (TaskTimeModel) tmList.get(i);
                        doccontentList.add(bf.getTaskTimeForm(bm));
                }
                return doccontentList;
        }

        public TaskTimeForm getTaskTime(int taskTimeID) throws TaskTimeDAOSysException
        {
                TaskTimeForm bf = new TaskTimeForm();
                TaskTimeForm res = null;
                List tmList = null;
                String hql = " from TaskTimeModel where TaskTimeID =" + taskTimeID;
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TaskTimeDAOSysException("" + e);
                }
                if ((tmList != null) && (tmList.size() > 0))
                {
                        TaskTimeModel bm = (TaskTimeModel) tmList.get(0);
                        res = bf.getTaskTimeForm(bm);
                }
                return res;
        }
        /*
        private TaskTimeForm convertRs2Form(ResultSet rs)
        {
                TaskTimeForm ttf = new TaskTimeForm();
                int rsIndex = 1;
                try
                {
                        ttf.setTaskTimeID(rs.getInt(rsIndex++));
                        ttf.setTaskID(rs.getInt(rsIndex++));
                        ttf.setTaskTime(DateTimeUtil.toDate(rs.getDate("TaskTime"),rs.getTime("TaskTime")));
                        rsIndex++;
                        ttf.setNotExecuteCondition(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return ttf;
        }
        */
}
