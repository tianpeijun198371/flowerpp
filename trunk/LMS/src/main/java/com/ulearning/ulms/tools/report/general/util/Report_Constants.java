/** * Report_Constants.java. 
 * User: xiejh  Date: 2004-7-16 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */
package com.ulearning.ulms.tools.report.general.util;

public interface Report_Constants
{
        public static final String REPORT_SURVEY_TYPE = "050101";   //培训情况统计总表

        public static final String REPORT_USER_INFO_TYPE = "050201"; //用户信息
        public static final String REPORT_USER_STUDY_SCHEDULE_TYPE = "050202";  //用户学习进度信息
        public static final String REPORT_USER_LOGIN_TYPE = "050203";    //用户登录信息

        public static final String REPORT_COURSE_INFO_TYPE = "050301";   //课程信息
        public static final String REPORT_COURSE_ISSUANCE_TYPE = "050302";   //发布课程信息
        public static final String REPORT_COURSE_USER_TYPE = "050303";   //课程用户信息
        public static final String REPORT_COURSE_STUDY_SCHEDULE_TYPE = "050304";   //用户学习进度信息
        public static final String REPORT_COURSE_VISIT_TYPE = "050305";   //用户访问统计信息

        public static final String REPORT_CERT_INFO_TYPE = "050401";        //证书信息
        public static final String REPORT_CERT_ISSUANCE_TYPE = "050402";    //发布证书信息
        public static final String REPORT_CERT_USER_TYPE = "050403";        //证书用户信息
        public static final String REPORT_CERT_SCHEDULE_TYPE = "050404";   //用户学习进度信息

        public static final String REPORT_PROJECT_INFO_TYPE = "050501";   //项目信息
        public static final String REPORT_PROJECT_USER_TYPE = "050502";   //项目用户信息
        public static final String REPORT_PROJECT_SCHEDULE_TYPE = "050503";   //项目用户学习进度信息

        public static final String TASK_NEVER_TYPE = "1";      //从不
        public static final String TASK_NOT_CIRCLE_TYPE = "2";// 不循环运行
        public static final String TASK_DAY_TYPE = "3";        // 每天
        public static final String TASK_WEEK_TYPE = "4";       // 每周
        public static final String TASK_MONTH_TYPE = "5";      // 每月
        public static final String TASK_YEAR_TYPE = "6";       // 每年
}

