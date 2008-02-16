/**
 * Constants.java.
 * User: fengch  Date: 2004-5-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;


/**
 * This interface contains all the keys that are used to
 * store data in the different scopes of web-tier. These
 * values are the same as those used in the JSP
 * pages (useBean tags).
 */
public interface Constants
{
        public static final String ulmsCONFIG_FILE_Key = "ulms.xml";
        public static final String SYSCONFIG_FILE_Key = "sysconfig.xml";
        public static final String PRODUCT_VERSION_KEY = "version";
        public static final String PRODUCT_NAME_KEY = "product-name";
        public static final String PROJECT_NAME_KEY = "project-name";

        /**
         * Integration variables
         */
        public static final String IS_INTEGRATE_EXAM_KEY = "is-integrate-exam";
        public static final String EXAM_JDBCURL_KEY = "exam-jdbc-url";
        public static final String EXAM_HTTPURL_KEY = "exam-http-url";
        public static final String EXAM_DATABASE_USER_KEY = "exam-user";
        public static final String EXAM_DATABASE_PWD_KEY = "exam-pwd";
        public static final String ORACLE_JDBCURL_KEY = "oracle-jdbc-url";
        public static final String ORACLE_JDBCUSERL_KEY = "oracle-jdbc-user";
        public static final String ORACLE_JDBCPWD_KEY = "oracle-jdbc-user";
        public static final String DB2_JDBCURL_KEY = "DB2-jdbc-url";
        public static final String DB2_JDBCUSERL_KEY = "DB2-jdbc-user";
        public static final String DB2_JDBCPWD_KEY = "DB2-jdbc-pwd";
        public static final String MSSQL_JDBCURL_KEY = "mssql-jdbc-url";
        public static final String MSSQL_JDBCUSERL_KEY = "mssql-jdbc-user";
        public static final String MSSQL_JDBCPWD_KEY = "mssql-jdbc-pwd";
        public static final String VCLASS_URL_KEY = "vclass-http-url";
        public static final String APPSERVER_KEY = "appserver";
        public static final String DATABASE_KEY = "database";
        public static final String CONTEXTROOT_KEY = "contextroot";
        public static final String UPLOAD_PHYSICAL_PATH_Key = "upload-physical-path";
        public static final String UPLOAD_VIRTUAL_PATH_Key = "upload-virtual-path";
        public static final String SMTPSERVERHOST_Key = "smtp-server-host";
        public static final String SMTPSERVERUSER_Key = "smtp-server-user";
        public static final String SMTPSERVERPWD_Key = "smtp-server-pwd";
        public static final String TRY_DATE_Key = "try-date";
}
