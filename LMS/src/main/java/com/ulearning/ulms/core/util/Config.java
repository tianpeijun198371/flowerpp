/**
 * Config.java.
 * User: fengch  Date: 2004-5-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigHelper;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.core.config.ConfigDAO;
import com.ulearning.ulms.core.config.ConfigDAOFactory;

import org.apache.struts.util.RequestUtils;

import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;


public class Config
{
        private static HashMap ulmsMappings;
        private static HashMap sysMappings;
        private static ConfigDAO dao;
        private static SysConfigHelper helper = null;
        private static SysConfigForm sysConfigForm = null;
        private static String ulms_URL = null;
        private static String COURSE_I18N_NAME = null;
        private static String CERT_I18N_NAME = null;
        private static boolean isInit = false;

        static
        {
                dao = ConfigDAOFactory.getDAO();
                ulmsMappings = dao.loadulmsConfigMappings();
                sysMappings = dao.loadSYSConfigMappings();
                helper = new SysConfigHelper();
                sysConfigForm = helper.getSysConfig("0");
        }

        //forbidden the constructor
        private Config()
        {
        }

        /**
         * init config.
         *
         * @param pageContext
         */
        public static void initialize(PageContext pageContext)
        {
                if (!isInit)
                {
                        try
                        {
                                //根据messageKey返回对应i18n value.
                                COURSE_I18N_NAME = RequestUtils.message(pageContext, null,
                                        null, "message.course");
                                CERT_I18N_NAME = RequestUtils.message(pageContext, null, null,
                                        "message.certificate");
                                setulmsURL(pageContext);
                                isInit = true;
                        }
                        catch (Exception ex)
                        {
                                ex.printStackTrace();
                        }
                }
        }

        public static String getCOURSE_I18N_NAME()
        {
                return COURSE_I18N_NAME;
        }

        public static String getCERT_I18N_NAME()
        {
                return CERT_I18N_NAME;
        }

        /**
         * 根据orgID返回系统配置信息.
         *
         * @param aspID
         */
        public static SysConfigForm getSysConfig(String aspID)
        {
                //todo: 这里指返回orgID=0的系统配置信息,多aspID的支持以后实现
                return sysConfigForm;
        }

        /**
         * 使系统配置信息与数据库同步.
         *
         * @param sysConfigForm
         */
        public static void setSysConfig(SysConfigForm sysConfigForm)
        {
                Config.sysConfigForm = sysConfigForm;
        }

        private static void setulmsURL(PageContext pageContext)
        {
                String url = null;

                try
                {
                        ServletRequest request = pageContext.getRequest();
                        String scheme = request.getScheme();
                        String host = request.getServerName();
                        int port = request.getServerPort();
                        String context = Config.getContextRoot();
                        url = scheme + "://" + host + ":" + port  + context;
                }
                catch (Exception ex)
                {
                }

                ulms_URL = url;
        }

        /**
         * 返回ulms的URL地址.
         */
        public static String getulmsURL()
        {
                return ulms_URL;
        }

        /*
        * return the value according to the key.<br>.
        */
        public static String get(String key)
        {
                String value = (String) sysMappings.get(key);

                if (value == null)
                {
                        value = (String) ulmsMappings.get(key);
                }

                return value;
        }

        /*
        * return the product's name.
        */
        public static String getProductName()
        {
                String value = (String) sysMappings.get(Constants.PRODUCT_NAME_KEY);

                return value;
        }

        /*
        * return the product's version.
        */
        public static String getProductVersion()
        {
                String value = (String) sysMappings.get(Constants.PRODUCT_VERSION_KEY);

                return value;
        }

        /*
        * return the product's App Serve.
        */
        public static String getApplicationServer()
        {
                String value = (String) ulmsMappings.get(Constants.APPSERVER_KEY);

                return value;
        }

        /*
        * return the product's Database.
        */
        public static String getDatabase()
        {
                String value = (String) ulmsMappings.get(Constants.DATABASE_KEY);

                return value;
        }

        /*
        * return the product's App Context.
        */
        public static String getContextRoot()
        {
                String value = (String) sysMappings.get(Constants.CONTEXTROOT_KEY);

                return value;
        }

        /*
        * return the product's UploadPhysicalPath.
        */
        public static String getUploadPhysicalPath()
        {
                String value = (String) ulmsMappings.get(Constants.UPLOAD_PHYSICAL_PATH_Key);
                return value;
        }

        /*
        * return the product's UploadVirtualPat.
        */
        public static String getUploadVirtualPath()
        {
                String value = (String) ulmsMappings.get(Constants.UPLOAD_VIRTUAL_PATH_Key);

                return value;
        }

        /*
        * return the product's smtp host.
        */
        public static String getSmtpHost()
        {
                String value = (String) ulmsMappings.get(Constants.SMTPSERVERHOST_Key);

                return value;
        }

        /*
        * return the product's smtp host.
        */
        public static String getSmtpUser()
        {
                String value = (String) ulmsMappings.get(Constants.SMTPSERVERUSER_Key);

                return value;
        }

        /*
        * return the product's smtp host.
        */
        public static String getSmtpPassword()
        {
                String value = (String) ulmsMappings.get(Constants.SMTPSERVERPWD_Key);

                return value;
        }

        /**
         * Return whether use JieFo paper system
         */
        public static boolean getIsIntegrateJieFo()
        {
                String value = (String) ulmsMappings.get(Constants.IS_INTEGRATE_EXAM_KEY);

                if ("true".equalsIgnoreCase(value))
                {
                        return true;
                }

                return false;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getOracleJDBCURL()
        {
                String value = (String) ulmsMappings.get(Constants.ORACLE_JDBCURL_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getOracleJDBCUser()
        {
                String value = (String) ulmsMappings.get(Constants.ORACLE_JDBCUSERL_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getOracleJDBCPWD()
        {
                String value = (String) ulmsMappings.get(Constants.ORACLE_JDBCPWD_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getDB2JDBCURL()
        {
                String value = (String) ulmsMappings.get(Constants.DB2_JDBCURL_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getDB2JDBCUser()
        {
                String value = (String) ulmsMappings.get(Constants.DB2_JDBCUSERL_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getDB2JDBCPWD()
        {
                String value = (String) ulmsMappings.get(Constants.DB2_JDBCPWD_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getMssqlJDBCURL()
        {
                String value = (String) ulmsMappings.get(Constants.MSSQL_JDBCURL_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getMssqlJDBCUser()
        {
                String value = (String) ulmsMappings.get(Constants.MSSQL_JDBCUSERL_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getMssqlJDBCPWD()
        {
                String value = (String) ulmsMappings.get(Constants.MSSQL_JDBCPWD_KEY);

                return value;
        }

        /**
         * Return Project Name
         */
        public static String getProjectName()
        {
                String value = (String) ulmsMappings.get(Constants.PROJECT_NAME_KEY);

                if (value == null)
                {
                        value = "";
                }

                return value;
        }

        /**
         * 判断是否是国资委项目
         */
        public static boolean isGuoZiWeiProject()
        {
                String value = getProjectName();
                return value.equalsIgnoreCase("GZW");
        }

        /**
         * 判断是否是新理念项目
         */
        public static boolean isXLNProject()
        {
                String value = getProjectName();
                return value.equalsIgnoreCase("XLN");
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getExamJDBCURL()
        {
                String value = (String) ulmsMappings.get(Constants.EXAM_JDBCURL_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getExamJDBCUser()
        {
                String value = (String) ulmsMappings.get(Constants.EXAM_DATABASE_USER_KEY);

                return value;
        }

        /**
         * Return JDBC url for jiefo paper system
         */
        public static String getExamJDBCPWD()
        {
                String value = (String) ulmsMappings.get(Constants.EXAM_DATABASE_PWD_KEY);

                return value;
        }

        /**
         * Return the url for jiefo system
         */
        public static String getJieFoHTTPURL()
        {
                String value = (String) ulmsMappings.get(Constants.EXAM_HTTPURL_KEY);

                return value;
        }

        /**
         * Return the http url for Vclass
         */
        public static String getVclassHTTPURL()
        {
                String value = (String) ulmsMappings.get(Constants.VCLASS_URL_KEY);

                return value;
        }

        public static String getTryDate()
        {
                String value = (String) sysMappings.get(Constants.TRY_DATE_Key);

                return value;
        }

        /**
         * return the mao: ulmsMappings
         *
         * @return HashMap
         */
        public static HashMap getulmsMappings()
        {
                return ulmsMappings;
        }

        /**
         * return the mao: sysMappings
         *
         * @return HashMap
         */
        public static HashMap getSysMappings()
        {
                return sysMappings;
        }
}
