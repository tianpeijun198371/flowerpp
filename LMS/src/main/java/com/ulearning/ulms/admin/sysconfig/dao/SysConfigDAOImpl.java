/**
 * SysConfigDAOImpl.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.dao;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigConstants;
import com.ulearning.ulms.admin.sysconfig.exceptions.SysConfigDAOSysException;
import com.ulearning.ulms.admin.sysconfig.form.AutoInformForm;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.admin.sysconfig.model.AutoInformModel;
import com.ulearning.ulms.admin.sysconfig.model.AutoInformModelPK;
import com.ulearning.ulms.admin.sysconfig.model.SmtpServerModel;
import com.ulearning.ulms.admin.sysconfig.model.SysConfigModel;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class SysConfigDAOImpl implements SysConfigDAO
{
        protected static SessionFactory sessionFactory = null;

        //protected transient Connection dbConnection = null;
        //protected transient DataSource datasource = null;
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;

        public void addSysConfig(SysConfigForm details)
                throws SysConfigDAOSysException
        {
        }

        public void updateSysConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Session session = null;
                int orgID = sysConfigForm.getOrgID();

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SysConfigModel updateModel = sysConfigForm.toConfigModel();
                        Object object = session.get(SysConfigModel.class, new Integer(orgID));

                        if (object != null)
                        {
                                sm = (SysConfigModel) object;

                                sm.setIsselfregist(updateModel.getIsselfregist());
                                sm.setIsneedconfirm(updateModel.getIsneedconfirm());
                                sm.setName(updateModel.getName());
                                sm.setProvince(updateModel.getProvince());
                                sm.setAddress(updateModel.getAddress());
                                sm.setPostalcode(updateModel.getPostalcode());
                                sm.setTelephone(updateModel.getTelephone());
                                sm.setEmail(updateModel.getEmail());
                                sm.setPlatname(updateModel.getPlatname());
                                sm.setMasteremail(updateModel.getMasteremail());
                                sm.setPlatlogo(updateModel.getPlatlogo());
                                sm.setPlatinfo(updateModel.getPlatinfo());
                                sm.setPlatcopyright(updateModel.getPlatcopyright());
                                session.update(sm, new Integer(orgID));
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        public void addSysConfigByID(String orgID) throws SysConfigDAOSysException
        {
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sysConfigModel = new SysConfigModel(Integer.parseInt(
                                orgID));
                        SmtpServerModel smtpServerModel = new SmtpServerModel(Integer.parseInt(
                                orgID));
                        AutoInformModel autoInformModel1 = new AutoInformModel(new AutoInformModelPK(
                                Integer.parseInt(orgID),
                                SysConfigConstants.SYS_CONFIG_CALENDER_EVENT),
                                "日历事件提醒通知", "1", "1", "1", "");
                        AutoInformModel autoInformModel2 = new AutoInformModel(new AutoInformModelPK(
                                Integer.parseInt(orgID),
                                SysConfigConstants.SYS_CONFIG_APPLY_COURSE_SUC_EVENT),
                                "学生申请课程被批准通知", "1", "1", "1", "");
                        AutoInformModel autoInformModel3 = new AutoInformModel(new AutoInformModelPK(
                                Integer.parseInt(orgID),
                                SysConfigConstants.SYS_CONFIG_APPLY_COURSE_FAIL_EVENT),
                                "学生申请课程被拒绝通知", "1", "1", "1", "");
                        AutoInformModel autoInformModel4 = new AutoInformModel(new AutoInformModelPK(
                                Integer.parseInt(orgID),
                                SysConfigConstants.SYS_CONFIG_DATA_BAK_FINISH_EVENT),
                                "数据备份完成通知", "1", "1", "1", "");
                        AutoInformModel autoInformModel5 = new AutoInformModel(new AutoInformModelPK(
                                Integer.parseInt(orgID),
                                SysConfigConstants.SYS_CONFIG_REPORT_FINISH_EVENT),
                                "报表完成通知", "1", "1", "1", "");

                        session.save(sysConfigModel);
                        session.save(smtpServerModel);
                        session.save(autoInformModel1);
                        session.save(autoInformModel2);
                        session.save(autoInformModel3);
                        session.save(autoInformModel4);
                        session.save(autoInformModel5);
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        /**
         * Get the SysConfig info via the unique Name
         *
         * @param orgid
         * @return the prepared SysConfigForm, default is null
         * @throws SysConfigDAOSysException
         */
        public SysConfigForm getSysConfig(String orgid)
                throws SysConfigDAOSysException
        {
                SysConfigForm sysConfigFrom = null;
                Session session = null;
                int intOrgID = Integer.parseInt(orgid);

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SmtpServerModel ssm = null;

                        Object object1 = null;
                        Object object2 = null;

                        String hql = "from SysConfigModel";
                        List modelList = session.find(hql);
                        boolean isFind = false;

                        for (int i = 0; i < modelList.size(); i++)
                        {
                                int tmpOrgID = ((SysConfigModel) modelList.get(i)).getOrgid();

                                if (intOrgID == tmpOrgID)
                                {
                                        isFind = true;
                                }
                        }

                        if (isFind)
                        {
                                object1 = session.get(SysConfigModel.class,
                                        new Integer(intOrgID));
                                object2 = session.get(SmtpServerModel.class,
                                        new Integer(intOrgID));
                        }
                        else
                        {
                                /* SysConfigForm sysConfigForm = new SysConfigForm(intOrgID);
                                SysConfigModel sysConfigModel = sysConfigForm.toConfigModel();
                                SmtpServerModel smtpServerModel = sysConfigForm.toSmtpServerModel();
                                AutoInformModel autoInformModel1 = new AutoInformModel(new AutoInformModelPK(intOrgID, SysConfigConstants.SYS_CONFIG_CALENDER_EVENT), "日历事件提醒通知", "1", "1", "1", "");
                                AutoInformModel autoInformModel2 = new AutoInformModel(new AutoInformModelPK(intOrgID, SysConfigConstants.SYS_CONFIG_APPLY_COURSE_SUC_EVENT), "学生申请课程被批准通知", "1", "1", "1", "");
                                AutoInformModel autoInformModel3 = new AutoInformModel(new AutoInformModelPK(intOrgID, SysConfigConstants.SYS_CONFIG_APPLY_COURSE_FAIL_EVENT), "学生申请课程被拒绝通知", "1", "1", "1", "");
                                AutoInformModel autoInformModel4 = new AutoInformModel(new AutoInformModelPK(intOrgID, SysConfigConstants.SYS_CONFIG_DATA_BAK_FINISH_EVENT), "数据备份完成通知", "1", "1", "1", "");
                                AutoInformModel autoInformModel5 = new AutoInformModel(new AutoInformModelPK(intOrgID, SysConfigConstants.SYS_CONFIG_REPORT_FINISH_EVENT), "报表完成通知", "1", "1", "1", "");
                                session.save(sysConfigModel);
                                session.save(smtpServerModel);
                                session.save(autoInformModel1);
                                session.save(autoInformModel2);
                                session.save(autoInformModel3);
                                session.save(autoInformModel4);
                                session.save(autoInformModel5);
                                session.flush();
                                session.connection().commit();*/
                                intOrgID = 0; // ，todo：暂时不支持多个orgID以后加上就可以了，
                                object1 = session.get(SysConfigModel.class,
                                        new Integer(intOrgID));
                                object2 = session.get(SmtpServerModel.class,
                                        new Integer(intOrgID));
                        }

                        sm = (SysConfigModel) object1;
                        ssm = (SmtpServerModel) object2;
                        sysConfigFrom = new SysConfigForm(sm, ssm);
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + " :\n" + he);
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }

                return sysConfigFrom;
        }

        /**
         * Get the SysConfig info via the unique Name
         *
         * @return the prepared SysConfigForm, default is null
         * @throws SysConfigDAOSysException
         */
        public SysConfigForm getSysConfig(int intOrgID)
                throws SysConfigDAOSysException
        {
                SysConfigForm sysConfigFrom = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SmtpServerModel ssm = null;

                        Object object1 = null;
                        Object object2 = null;

                        object1 = session.get(SysConfigModel.class, new Integer(intOrgID));
                        object2 = session.get(SmtpServerModel.class, new Integer(intOrgID));

                        sm = (SysConfigModel) object1;
                        ssm = (SmtpServerModel) object2;
                        sysConfigFrom = new SysConfigForm(sm, ssm);
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + " :\n" + he);
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }

                return sysConfigFrom;
        }

        /**
         * Get the SysConfig info via the unique Name
         *
         * @return the prepared SysConfigForm, default is null
         * @throws SysConfigDAOSysException
         */
        public SysConfigForm getSysConfig() throws SysConfigDAOSysException
        {
                Statement stmt = null;
                SysConfigForm sf = null;
                ResultSet rs = null;
                String sqlStr = "select * from S_Config_Tab ";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("user",
                                "[SysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                sf = convertRs2Form(rs);
                        }
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException("SQLException while updating " +
                                "SysConfig; Serial =getSysConfig() :\n" + se);
                }
                finally
                {
                        try
                        {
                                closeResultSet(rs);
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }

                return sf;
        }

        public ArrayList getAutoInformByID(String orgID)
                throws SysConfigDAOSysException
        {
                ArrayList autoInformFormList = new ArrayList();

                Session session = null;

                //int intOrgID = Integer.parseInt(orgID);
                int intOrgID = 0; //todo:以后支持多个asp的时候把这一行去掉就行了。
                String hql = "from AutoInformModel as aim where aim.comp_id.orgid = :orgIDpara";

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);

                        query.setInteger("orgIDpara", intOrgID);

                        List tmList = query.list();

                        ArrayList arrayList = (ArrayList) tmList;

                        for (int i = 0; i < arrayList.size(); i++)
                        {
                                AutoInformForm aif = new AutoInformForm((AutoInformModel) arrayList.get(
                                        i));
                                autoInformFormList.add(aif);
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while get  autoInform" + "orgID; " + orgID +
                                        " :\n" + he);
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }

                return autoInformFormList;
        }

        public AutoInformForm getAutoInfromFormByOrgIDAndType(String orgID, int type)
                throws SysConfigDAOSysException
        {
                Session session = null;
                AutoInformForm autoInformForm = null;

                int orgIDInt = Integer.parseInt(orgID);

                int typeInt = type;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = "from AutoInformModel";
                        List autmp = session.find(hql);
                        boolean isfind = false;

                        for (int i = 0; i < autmp.size(); i++)
                        {
                                int orgtmp = ((AutoInformModel) autmp.get(i)).getComp_id()
                                        .getOrgid();

                                if (orgIDInt == orgtmp)
                                {
                                        isfind = true;
                                }
                        }

                        if (isfind)
                        {
                                Object object = session.get(AutoInformModel.class,
                                        new AutoInformModelPK(orgIDInt, typeInt));
                                autoInformForm = new AutoInformForm((AutoInformModel) object);
                        }
                        else
                        {
                                SysConfigForm sysConfigForm = new SysConfigForm(orgIDInt);
                                SysConfigModel sysConfigModel = sysConfigForm.toConfigModel();
                                SmtpServerModel smtpServerModel = sysConfigForm.toSmtpServerModel();
                                AutoInformModel autoInformModel1 = new AutoInformModel(new AutoInformModelPK(
                                        orgIDInt,
                                        SysConfigConstants.SYS_CONFIG_CALENDER_EVENT),
                                        "日历事件提醒通知", "1", "1", "1", "");
                                AutoInformModel autoInformModel2 = new AutoInformModel(new AutoInformModelPK(
                                        orgIDInt,
                                        SysConfigConstants.SYS_CONFIG_APPLY_COURSE_SUC_EVENT),
                                        "学生申请课程被批准通知", "1", "1", "1", "");
                                AutoInformModel autoInformModel3 = new AutoInformModel(new AutoInformModelPK(
                                        orgIDInt,
                                        SysConfigConstants.SYS_CONFIG_APPLY_COURSE_FAIL_EVENT),
                                        "学生申请课程被拒绝通知", "1", "1", "1", "");
                                AutoInformModel autoInformModel4 = new AutoInformModel(new AutoInformModelPK(
                                        orgIDInt,
                                        SysConfigConstants.SYS_CONFIG_DATA_BAK_FINISH_EVENT),
                                        "数据备份完成通知", "1", "1", "1", "");
                                AutoInformModel autoInformModel5 = new AutoInformModel(new AutoInformModelPK(
                                        orgIDInt,
                                        SysConfigConstants.SYS_CONFIG_REPORT_FINISH_EVENT),
                                        "报表完成通知", "1", "1", "1", "");

                                session.save(sysConfigModel);
                                session.save(smtpServerModel);
                                session.save(autoInformModel1);
                                session.save(autoInformModel2);
                                session.save(autoInformModel3);
                                session.save(autoInformModel4);
                                session.save(autoInformModel5);
                                session.flush();
                                session.connection().commit();

                                Object object = session.get(AutoInformModel.class,
                                        new AutoInformModelPK(orgIDInt, typeInt));
                                autoInformForm = new AutoInformForm((AutoInformModel) object);
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }

                return autoInformForm;
        }

        /**
         * Get the SysConfig list by the IsFree
         *
         * @param IsFree
         * @return The prepared arraylist object,default size is 0
         * @throws SysConfigDAOSysException
         */
        public List getSysConfigList(int IsFree) throws SysConfigDAOSysException
        {
                Statement stmt = null;
                SysConfigForm sf = null;
                ResultSet rs = null;
                ArrayList planList = new ArrayList();
                String sqlStr = "select * from S_Config_Tab where IsFree = " + IsFree;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[SysConfigDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                sf = convertRs2Form(rs);
                                planList.add(sf);
                        }
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException("SQLException while updating " +
                                "SysConfig; " + "IsFree = " + IsFree + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                closeResultSet(rs);
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                        }
                }

                return planList;
        }

        public void updateUserRegister(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Session session = null;
                int orgID = sysConfigForm.getOrgID();

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SysConfigModel updateModel = sysConfigForm.toConfigModel();
                        Object object = session.get(SysConfigModel.class, new Integer(orgID));

                        if (object != null)
                        {
                                sm = (SysConfigModel) object;

                                sm.setIsselfregist(updateModel.getIsselfregist());
                                sm.setIsneedconfirm(updateModel.getIsneedconfirm());
                                session.update(sm, new Integer(orgID));
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        public void updateGeneralConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Session session = null;
                int orgID = sysConfigForm.getOrgID();

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SysConfigModel updateModel = sysConfigForm.toConfigModel();
                        Object object = session.get(SysConfigModel.class, new Integer(orgID));

                        if (object != null)
                        {
                                sm = (SysConfigModel) object;

                                sm.setSupportemail(updateModel.getSupportemail());
                                sm.setHelpurl(updateModel.getHelpurl());

                                sm.setIsLimitMaxOnLineUsers(updateModel.getIsLimitMaxOnLineUsers());
                                sm.setMaxOnLineUsers(updateModel.getMaxOnLineUsers());
                                sm.setIsAllowMultiLogin(updateModel.getIsAllowMultiLogin());

                                sm.setIsAllowDownloadCourseWare(updateModel.getIsAllowDownloadCourseWare());
                                sm.setDownloadCourseWareStartDate(updateModel.getDownloadCourseWareStartDate());
                                sm.setDownloadCourseWareEndDate(updateModel.getDownloadCourseWareEndDate());
                                sm.setDownloadCourseWareSizeLimit(updateModel.getDownloadCourseWareSizeLimit());

                                session.update(sm, new Integer(orgID));
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        public void updatePWDConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Session session = null;
                int orgID = sysConfigForm.getOrgID();

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SysConfigModel updateModel = sysConfigForm.toConfigModel();
                        Object object = session.get(SysConfigModel.class, new Integer(orgID));

                        if (object != null)
                        {
                                sm = (SysConfigModel) object;

                                sm.setPwdlength(updateModel.getPwdlength());
                                session.update(sm, new Integer(orgID));
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        public void updateLogConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Session session = null;
                int orgID = sysConfigForm.getOrgID();

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SysConfigModel updateModel = sysConfigForm.toConfigModel();
                        Object object = session.get(SysConfigModel.class, new Integer(orgID));
                        System.out.println(orgID);

                        if (object != null)
                        {
                                sm = (SysConfigModel) object;

                                sm.setIsloglogin(updateModel.getIsloglogin());
                                sm.setIslogmodpassword(updateModel.getIslogmodpassword());
                                session.update(sm, new Integer(orgID));
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        public void updateServiceItemConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Session session = null;
                int orgID = sysConfigForm.getOrgID();

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SysConfigModel updateModel = sysConfigForm.toConfigModel();
                        Object object = session.get(SysConfigModel.class, new Integer(orgID));

                        if (object != null)
                        {
                                sm = (SysConfigModel) object;

                                sm.setServiceitem(updateModel.getServiceitem());
                                session.update(sm, new Integer(orgID));
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        public void updateSMTPConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Session session = null;
                int orgID = sysConfigForm.getOrgID();

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SmtpServerModel sourceSmtp = sysConfigForm.toSmtpServerModel();
                        SysConfigModel updateModel = sysConfigForm.toConfigModel();

                        Object objectSys = session.get(SysConfigModel.class,
                                new Integer(orgID));

                        // Object objectSmtp = session.get(SmtpServerModel.class,new Integer(orgID));
                        if (objectSys != null)
                        {
                                sm = (SysConfigModel) objectSys;

                                sm.setIsneedsmtpauth(updateModel.getIsneedsmtpauth());
                                session.update(sm, new Integer(orgID));
                                session.update(sourceSmtp, new Integer(orgID));
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        public void updateLDAPConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Session session = null;
                int orgID = sysConfigForm.getOrgID();

                try
                {
                        session = HibernateUtil.getSession();

                        SysConfigModel sm = null;
                        SysConfigModel updateModel = sysConfigForm.toConfigModel();
                        Object object = session.get(SysConfigModel.class, new Integer(orgID));

                        if (object != null)
                        {
                                sm = (SysConfigModel) object;

                                sm.setIsneedldap(updateModel.getIsneedldap());
                                session.update(sm, new Integer(orgID));
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  SysConfig" + "orgID; " +
                                        "orgID = " + orgID + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        public void updateAutoInformConfig(ArrayList al)
                throws SysConfigDAOSysException
        {
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        for (int i = 0; i < al.size(); i++)
                        {
                                int orgIDTmp = ((AutoInformForm) al.get(i)).getOrgID();
                                int typeTmp = ((AutoInformForm) al.get(i)).getType();

                                AutoInformModel object = (AutoInformModel) session.get(AutoInformModel.class,
                                        new AutoInformModelPK(orgIDTmp, typeTmp));

                                AutoInformModel updateModel = new AutoInformModel();
                                updateModel = ((AutoInformForm) al.get(i)).toModel();

                                updateModel.setName(object.getName());

                                DebugUtil.print("updateModel name = " + updateModel.getName());
                                DebugUtil.print("updateModel orgID = " +
                                        updateModel.getComp_id().getOrgid());
                                DebugUtil.print("updateModel type = " +
                                        updateModel.getComp_id().getType());
                                DebugUtil.print("updatModel isMSG = " + updateModel.getIsmsg());
                                DebugUtil.print("updatModel ismail = " +
                                        updateModel.getIsmail());
                                DebugUtil.print("updatModel isTip = " + updateModel.getIstip());
                                DebugUtil.print("updatModel description = " +
                                        updateModel.getDescription());
                                DebugUtil.print("updatModel compID = " +
                                        updateModel.getComp_id());
                                session.saveOrUpdateCopy(updateModel);
                        }

                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException he)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating  AutoInformConfig" + "orgID; " +
                                        "orgID = " + " :\n" + he);
                }
                catch (SQLException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }
        }

        protected void closeConnection() throws SysConfigDAOSysException
        {
                try
                {
                        if ((dbConnection != null) && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        protected void closeResultSet(ResultSet result)
                throws SysConfigDAOSysException
        {
                try
                {
                        if (result != null)
                        {
                                result.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(PreparedStatement stmt)
                throws SysConfigDAOSysException
        {
                try
                {
                        if (stmt != null)
                        {
                                stmt.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws SysConfigDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new SysConfigDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
        }

        /**
         * Convert the resultSet object to planForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted planForm
         */
        private SysConfigForm convertRs2Form(ResultSet rs)
        {
                SysConfigForm sf = new SysConfigForm();

                try
                {
                        sf.setOrgID(rs.getInt("orgID"));
                        sf.setName(StringUtil.nullToStr(rs.getString("Name")));
                        sf.setIsCheckIP(StringUtil.nullToStr(rs.getString("isCheckIP")));
                        sf.setIsRecordLog(StringUtil.nullToStr(rs.getString("isRecordLog")));
                        sf.setIsCheckSec(StringUtil.nullToStr(rs.getString("isCheckSec")));
                        sf.setLoginStastic(rs.getInt("loginStastic"));
                        sf.setIsCheckTotal(StringUtil.nullToStr(rs.getString("isCheckTotal")));
                        sf.setIsFree(StringUtil.nullToStr(rs.getString("isFree")));
                        sf.setIsRemote(StringUtil.nullToStr(rs.getString("isWithLicence")));
                        sf.setPWDLength(rs.getInt("PWDLength"));
                        sf.setProvince(StringUtil.nullToStr(rs.getString("Province")));
                        sf.setAddress(StringUtil.nullToStr(rs.getString("Address")));
                        sf.setPostalcode(StringUtil.nullToStr(rs.getString("Postalcode")));
                        sf.setTelephone(StringUtil.nullToStr(rs.getString("Telephone")));
                        sf.setEmail(StringUtil.nullToStr(rs.getString("Email")));
                        sf.setPlatName(StringUtil.nullToStr(rs.getString("PlatName")));
                        sf.setMasterEmail(StringUtil.nullToStr(rs.getString("MasterEmail")));
                        sf.setPlatLogo(StringUtil.nullToStr(rs.getString("PlatLogo")));
                        sf.setPlatInfo(StringUtil.nullToStr(rs.getString("PlatInfo")));
                        sf.setPlatCopyright(StringUtil.nullToStr(rs.getString(
                                "PlatCopyright")));
                        sf.setHelpUrl(StringUtil.nullToStr(rs.getString("helpUrl")));
                        sf.setSupportEmail(StringUtil.nullToStr(rs.getString("supportEmail")));
                        sf.setIsLogLogin(StringUtil.nullToStr(rs.getString("isLogLogin")));
                        sf.setIsLogModPassword(StringUtil.nullToStr(rs.getString(
                                "isLogModPassword")));
                        sf.setServiceItem(StringUtil.nullToStr(rs.getString("serviceItem")));
                        sf.setIsNeedConfirm(StringUtil.nullToStr(rs.getString(
                                "isNeedConfirm")));
                        sf.setIsSelfRegist(StringUtil.nullToStr(rs.getString("isSelfRegist")));
                        sf.setIsNeedSMTPAuth(StringUtil.nullToStr(rs.getString(
                                "isNeedSMTPAuth")));
                        sf.setIsNeedLDAP(StringUtil.nullToStr(rs.getString("isNeedLDAP")));

                        // get data from T_SMTPServer_Tab
                        //sf.setHost(StringUtil.nullToStr(rs.getString("host")));
                        //sf.setUsername(StringUtil.nullToStr(rs.getString("username")));
                        //sf.setPassword(StringUtil.nullToStr(rs.getString("password")));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return sf;
        }
}
