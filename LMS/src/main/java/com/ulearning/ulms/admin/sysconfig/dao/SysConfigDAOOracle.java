/**
 * SysConfigDAOOracle.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.dao;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigConstants;
import com.ulearning.ulms.admin.sysconfig.exceptions.SysConfigDAOSysException;
import com.ulearning.ulms.admin.sysconfig.form.AutoInformForm;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class SysConfigDAOOracle extends SysConfigDAOImpl
{
        public void updateAutoInformConfig(ArrayList al)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;
                ArrayList altmp = new ArrayList();

                for (int i = 0; i < al.size(); i++)
                {
                        String tmp = "update S_AUTOINFORM_TAB set IsMail ='" +
                                ((AutoInformForm) al.get(i)).getIsMail() + "',IsMSG = '" +
                                ((AutoInformForm) al.get(i)).getIsMSG() + "' where OrgID =" +
                                ((AutoInformForm) al.get(i)).getOrgID() + " and type =" +
                                ((AutoInformForm) al.get(i)).getType();
                        altmp.add(tmp);
                }

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOOracle==============the SQL String]");

                        for (int i = 0; i < altmp.size(); i++)
                        {
                                stmt.execute((String) altmp.get(i));
                        }
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while updating autoInformConfig ");
                }
                finally
                {
                        try
                        {
                                if (stmt != null)
                                {
                                        stmt.close();
                                        closeConnection();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        public ArrayList getAutoInformByID(String orgID)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                ArrayList al = new ArrayList();

                //AutoInformForm aif= new AutoInformForm();
                int intOrgID = Integer.parseInt(orgID);
                String sqlStr = "select * from S_AUTOINFORM_TAB where orgID =" +
                        intOrgID;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[getAutoInformByID==============the SQL String]" + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                AutoInformForm aif = new AutoInformForm();
                                aif.setIsMail(StringUtil.nullToStr(rs.getString("isMail")));
                                aif.setIsMSG(StringUtil.nullToStr(rs.getString("isMSG")));
                                aif.setIsTip(StringUtil.nullToStr(rs.getString("isTip")));
                                aif.setType(rs.getInt("type"));
                                aif.setOrgID(rs.getInt("orgID"));
                                aif.setDescription(StringUtil.nullToStr(rs.getString(
                                        "description")));
                                aif.setName(StringUtil.nullToStr(rs.getString("name")));
                                al.add(aif);
                        }
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException(
                                "SQLException while getAutoInformByID " + "Cert;" + " Serial=");
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }

                return al;
        }

        public void addSysConfigByID(String orgID) throws SysConfigDAOSysException
        {
                Statement stmt = null;

                SysConfigForm sf = new SysConfigForm();
                AutoInformForm ai = new AutoInformForm();
                int intOrgID = Integer.parseInt(orgID);
                String sqlStr = "insert into S_Config_Tab " +
                        "(orgid,name,ISCHECKIP,ISRECORDLOG," +
                        "ISCHECKSEC , LOGINSTASTIC , ISCHECKTOTAL , ISFREE ," +
                        "ISREMOTE, ISWITHLICENCE,PWDLENGTH,Address , " +
                        "PROVINCE , POSTALCODE , TELEPHONE , EMAIL ," +
                        "PLATNAME , MASTEREMAIL , PLATLOGO , PLATINFO," +
                        "PLATCOPYRIGHT , HELPURL , SUPPORTEMAIL , ISLOGLOGIN," +
                        "ISLOGMODPASSWORD , SERVICEITEM , ISNEEDCONFIRM ," +
                        "ISSELFREGIST , ISNEEDSMTPAUTH , ISNEEDLDAP)" + "values ( " +
                        intOrgID + " , '" + sf.getName() + "','" + sf.getIsCheckIP() +
                        "','" + sf.getIsRecordLog() + "','" + sf.getIsCheckSec() + "'," +
                        sf.getLoginStastic() + ",'" + sf.getIsCheckTotal() + "','" +
                        sf.getIsFree() + "','" + sf.getIsRemote() + "','" +
                        sf.getIsWithLicence() + "'," + sf.getPWDLength() + ",'" +
                        sf.getAddress() + "','" + sf.getProvince() + "','" +
                        sf.getPostalcode() + "','" + sf.getTelephone() + "','" +
                        sf.getEmail() + "','" + sf.getPlatName() + "','" +
                        sf.getMasterEmail() + "','" + sf.getPlatLogo() + "','" +
                        sf.getPlatInfo() + "','" + sf.getPlatCopyright() + "','" +
                        sf.getHelpUrl() + "','" + sf.getSupportEmail() + "','" +
                        sf.getIsLogLogin() + "','" + sf.getIsLogModPassword() + "','" +
                        sf.getServiceItem() + "','" + sf.getIsNeedConfirm() + "'," +
                        sf.getIsSelfRegist() + ",'" + sf.getIsNeedSMTPAuth() + "','" +
                        sf.getIsNeedSMTPAuth() + "')";
                String sqlSMTPSERVER = "insert into T_SMTPSERVER_TAB (orgid,host,username,password) values (" +
                        intOrgID + ",null,null,null)";

                String sqlStr1 = "insert into S_AUTOINFORM_TAB " +
                        "(orgID,type,name,ismail,isMSG,isTip,description ) " + "values ( " +
                        intOrgID + " ,'" + SysConfigConstants.SYS_CONFIG_CALENDER_EVENT +
                        "','" + "日历事件提醒通知" + "','" + ai.getIsMail() + "','" +
                        ai.getIsMSG() + "','" + ai.getType() + "','" + ai.getDescription() +
                        "')";
                String sqlStr2 = "insert into S_AUTOINFORM_TAB " +
                        "(orgID,type,name,ismail,isMSG,isTip,description ) " + "values ( " +
                        intOrgID + " ,'" +
                        SysConfigConstants.SYS_CONFIG_APPLY_COURSE_SUC_EVENT + "','" +
                        "学生申请课程被批准通知" + "','" + ai.getIsMail() + "','" + ai.getIsMSG() +
                        "','" + ai.getType() + "','" + ai.getDescription() + "')";
                String sqlStr3 = "insert into S_AUTOINFORM_TAB " +
                        "(orgID,type,name,ismail,isMSG,isTip,description ) " + "values ( " +
                        intOrgID + " ,'" +
                        SysConfigConstants.SYS_CONFIG_APPLY_COURSE_FAIL_EVENT + "','" +
                        "学生申请课程被拒绝通知" + "','" + ai.getIsMail() + "','" + ai.getIsMSG() +
                        "','" + ai.getType() + "','" + ai.getDescription() + "')";
                String sqlStr4 = "insert into S_AUTOINFORM_TAB " +
                        "(orgID,type,name,ismail,isMSG,isTip,description ) " + "values ( " +
                        intOrgID + " ,'" +
                        SysConfigConstants.SYS_CONFIG_DATA_BAK_FINISH_EVENT + "','" +
                        "数据备份完成通知" + "','" + ai.getIsMail() + "','" + ai.getIsMSG() +
                        "','" + ai.getType() + "','" + ai.getDescription() + "')";
                String sqlStr5 = "insert into S_AUTOINFORM_TAB " +
                        "(orgID,type,name,ismail,isMSG,isTip,description ) " + "values ( " +
                        intOrgID + " ,'" +
                        SysConfigConstants.SYS_CONFIG_REPORT_FINISH_EVENT + "','" +
                        "报表完成通知" + "','" + ai.getIsMail() + "','" + ai.getIsMSG() + "','" +
                        ai.getType() + "','" + ai.getDescription() + "')";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOOracle==============the SQL String]" + sqlStr);
                        stmt.execute(sqlStr);
                        stmt.execute(sqlStr1);
                        stmt.execute(sqlStr2);
                        stmt.execute(sqlStr3);
                        stmt.execute(sqlStr4);
                        stmt.execute(sqlStr5);
                        stmt.execute(sqlSMTPSERVER);
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException("SQLException while updating " +
                                "Cert;" + sqlStr5 + " Serial=" + sf.getName() + ":\n" + se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        /**
         * Update SysConfigInfo by the new Form
         *
         * @param sysConfigForm value object for changed
         * @throws SysConfigDAOSysException
         */
        public void updateSysConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "update S_Config_Tab set " + "Name = '" +
                        sysConfigForm.getName() + "'," + "Province = '" +
                        sysConfigForm.getProvince() + "'," + "Address = '" +
                        sysConfigForm.getAddress() + "'," + "Postalcode = " +
                        sysConfigForm.getPostalcode() + "," + "Telephone = '" +
                        sysConfigForm.getTelephone() + "'," + "Email = '" +
                        sysConfigForm.getEmail() + "'," + "PlatName = '" +
                        sysConfigForm.getPlatName() + "'," + "MasterEmail = '" +
                        sysConfigForm.getMasterEmail() + "'," + "PlatLogo = '" +
                        sysConfigForm.getPlatLogo() + "'," + "PlatInfo = '" +
                        sysConfigForm.getPlatInfo() + "'," + "platCopyright='" +
                        sysConfigForm.getPlatCopyright() + "'" + "  where  orgID = " +
                        sysConfigForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[sysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new SysConfigDAOSysException("SQLException while updating " +
                                "sysConfig; Serial = " + sysConfigForm.getName() + " :\n" + se,
                                se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                        }
                }
        }

        public void updateUserRegister(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "update S_Config_Tab set " + "IsSelfRegist = " +
                        sysConfigForm.getIsSelfRegist() + "," + "IsNeedConfirm = '" +
                        sysConfigForm.getIsNeedConfirm() + "'" + " where orgID = " +
                        sysConfigForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[sysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SysConfigDAOSysException(
                                "SQLException while updating updateUserRegister" +
                                        "sysConfig; Serial = " + sysConfigForm.getName() + " :\n" + se,
                                se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }
        }

        /**
         * update genearl config
         *
         * @param sysConfigForm
         */
        public void updateGeneralConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "update S_Config_Tab set " + "HelpUrl = '" +
                        sysConfigForm.getHelpUrl() + "'," + "supportEmail = '" +
                        sysConfigForm.getSupportEmail() + "'" + " where orgID =" +
                        sysConfigForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[sysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SysConfigDAOSysException(
                                "SQLException while updating General config" +
                                        "sysConfig; Serial = " + sysConfigForm.getName() + " :\n" + se,
                                se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }
        }

        public void updatePWDConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "update S_Config_Tab set " + "PWDLength = " +
                        sysConfigForm.getPWDLength() + "  where orgID = " +
                        sysConfigForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[sysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SysConfigDAOSysException(
                                "SQLException while updatePWDConfig" + "sysConfig; Serial = " +
                                        sysConfigForm.getName() + " :\n" + se, se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }
        }

        public void updateLogConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "update S_Config_Tab set " + "isLogLogin = '" +
                        sysConfigForm.getIsLogLogin() + "'," + "IsLogModPassword = '" +
                        sysConfigForm.getIsLogModPassword() + "'" + "  where orgID = " +
                        sysConfigForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[sysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SysConfigDAOSysException(
                                "SQLException while updateLogConfig" + sqlStr +
                                        "sysConfig; Serial = " + sysConfigForm.getName() + " :\n" + se,
                                se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }
        }

        public void updateServiceItemConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;

                String sqlStr = "update S_Config_Tab set " + "ServiceItem = '" +
                        sysConfigForm.getServiceItem() + "'" + "where orgID = " +
                        sysConfigForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[sysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        throw new SysConfigDAOSysException(
                                "SQLException while updateLogConfig" + sqlStr);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }
        }

        public void updateSMTPConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;
                String sqlStrSMTP = null;

                sqlStrSMTP = "update T_SMTPServer_Tab set " + "Host = '" +
                        sysConfigForm.getHost() + "'," + "UserName ='" +
                        sysConfigForm.getUsername() + "'," + "password ='" +
                        sysConfigForm.getPassword() + "'" + "where orgID =" +
                        sysConfigForm.getOrgID();

                String sqlStr = "update S_Config_Tab set " + "isNeedSMTPAuth = '" +
                        sysConfigForm.getIsNeedSMTPAuth() + "'" + "where orgID = " +
                        sysConfigForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[sysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                        stmt.execute(sqlStrSMTP);
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        throw new SysConfigDAOSysException(
                                "SQLException while updateLogConfig" + sqlStr);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }
        }

        public void updateLDAPConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException
        {
                Statement stmt = null;

                String sqlStr = "update S_Config_Tab set " + "IsNeedLdap = '" +
                        sysConfigForm.getIsNeedLDAP() + "'" + "where orgID = " +
                        sysConfigForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[sysConfigDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        throw new SysConfigDAOSysException(
                                "SQLException while updateLogConfig" + sqlStr);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }
        }
}
