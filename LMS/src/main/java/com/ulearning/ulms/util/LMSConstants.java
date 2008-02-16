/**
 * LMSConstants.java.
 * User: dengj  Date: 2004-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.util;

public interface LMSConstants
{
        /*
        * 常量
        */
        int TRUE = 0;
        int FALSE = 1;

        int AVAILABLE = 0;
        int UNAVAILABLE = 1;

        int CONFIRM = 0;
        int UNCONFIRM = 1;

        /**
         * SESSION 常量
         */
        //登陆后需要跳转的地址
        String PREVIOUS_LOGIN_URL = "SESSION_PREVIOUS_LOGIN_URL";


        //pager
        String PAGER_PAGESIZE = "PAGER_PAGESIZE";
        String PAGER_YUI_TABVIEW = "PAGER_YUI_TABVIEW";

        String USER_KEY = "lms_usermodel";
        String USER_ORGID_KEY = "lms_user_org";
        String USER_DEFAULT_ORGID_KEY = "lms_user_default_org";
        String USER_ASPID_KEY = "lms_user_asp";
        String TICKET_KEY = "lms_sessionticket";
        String USERID_KEY = "lms_sessionuserid";
        String LOGINNAME_KEY = "lms_sessionloginame";
        String CERTIFICATE_KEY = "lms_certificate";
        String CERTERROR_INFO = "您的Licence没有授权，请与系统管理员联系!";
        //Add  zhangy   start
        String LOGINNAME_KEY_NAME = "lms_sessionloginame_name";
        //Root Domain
        String DOMAIN_KEY_NAME = "www.bjmti.com";
        //end
        /**
         * Save the sum of login user number
         */
        String LOGINUSER_NUM_KEY = "lms_login_user_num";

        /**
         * Save the hashMap of login user infomation
         */
        String LOGINUSER_MAP_KEY = "lms_login_user_map";

        /**
         * The default sum of number for the online user
         */
        int LOGINUSER_DEFAULT_KEY = 10000;

        /**
         * 错误页面定义
         */

        //假如 request.getAttribute里含有此值,则出错时，隐藏该left页面
        String ERROR_PAGE_ACTION_HIDDENLEFT = "ERROR_PAGE_ACTION_HIDDENLEFT";
        //假如 request.getAttribute里含有此值,则点击返回按钮,则会关闭
        String ERROR_PAGE_ACTION_ISPOP="is_popup";
        //假如 session.getAttribute里含有此值,则点击返回按钮,则会返回到该地址
        String ERROR_PAGE_ACTION_RETURNURL="return_url";
        //假如 request.getAttribute里含有此值,则点击返回按钮,则会返回到首页
        String ERROR_PAGE_ACTION_RETURNPORTAL="returnPortal";

        String NOSESSION_PAGE = "nosession.jsp";

        String ERROR_PAGE_INFO = "lms_error_page_info";

        String ERROR_FORWARD = "error";

        String ERROR_FORWARD2 = "error2";

        String ERROR_LOGIN_FORWARD = "register_login";


        //Tree constants
        String TREE_COURSE = "lms_course_tree";
        String TREE_CERT = "lms_cert_tree";
        String TREE_MASTER = "lms_master_tree";
        String TREE_USER = "lms_user_tree";
        String TREE_ORGAN = "lms_organ_tree";
        String TREE_FORUM = "lms_forum_tree";
        String TREE_PUBLISH = "lms_publish_tree";
        String TREE_CERT_MASTER = "lms_cert_master_tree";
        String TREE_SELECT = "lms_select_tree";
        String TREE_PUBLISH_COURSE = "lms_publish_course_tree";
        String TREE_PUBLISHED_COURSE = "lms_published_course_tree";
        String TREE_PUBLISH_CERT = "lms_publish_cert_tree";
        String TREE_PUBLISHED_CERT = "lms_published_cert_tree";
        String TREE_BROWSE_COURSE = "lms_browse_course_tree";
        String TREE_COL_SELECT = "lms_col_select_tree";
        String TREE_COL_PERSONAL_SELECT = "lms_col_personal_select_tree";
        String TREE_SELECT_NO_SELECT_COURSE = "no_select_course";
        String TREE_COURSECONTENT = "lms_coursecontent_tree";
        String TREE_STUDENT_SIGNUP = "lms_studentSignup_tree";
        String TREE_CERT_OFFLINE = "lms_cert_tree_offline";
        String TREE_PUBLISH_CERT_OFFLINE = "lms_publish_cert_tree_offline";

        int LEARNING_MASTER = 0;

        int LEARNING_TUTORIAL = 1;

        int LEARNING_TUTORIAL_MASTER = 2;

        int LEARNING_DEFAULT = 3;

        int MULTI_PAGE_NUMBER = 15;

        int MODULE_SYS_ADMIN = 1;
        int MODULE_ORG_ADMIN = 2;
        int MODULE_TRAIN_ADMIN = 5;
        int MODULE_STUDY = 3;
        int MODULE_MYSPACE = 4;

        /**
         * Code Initialize constants
         */
        int CODE_DUTY_TITLE = 1;
        int CODE_WORK_TYPE = 2;
        int CODE_TRAINING_OBJECT = 3;
        int CODE_TRAINING_STYLE = 4;


        /**
         * catalog type
         */
        int CATALOG_MASTER_COURSE = 0;
        int CATALOG_MASTER_CERT = 1;
        int CATALOG_COURSE_COURSE = 2;
        int CATALOG_COURSE_CERT = 3;

        /**
         * User type 0 职工用户，1 外来用户
         */
        int USER_TYPE_EMPLOYEE = 0;
        int USER_TYPE_FOREIGN = 1;
        int USER_TYPE_PORTAL = 2;
        String PORTAL_USER__KEY = "lms_portaluser";

        /**
         * 培训计划状态
         */
        int PLAN_NEW = 0;//未实施(执行)的计划;
        int PLAN_DO = 1;//已实施(执行)的计划
        int PLAN_STAY = 2;//下月待办(执行)的计划
        int PLAN_BACK = 3;//逾期未办(执行)的计划

        /**
         * 访问量类型
         */
        int VISIT_USER = 0;//用户的首页访问量，包括教师和学生;
        int VISIT_TEACHER = 1;//ASP的首页访问量，包括示范校
        int VISIT_SCHOOL = 2;//加盟校的首页访问量
        int VISIT_CLASS = 3;//班级首页的访问量
        int VISIT_STUDENT = 4;
        int VISIT_APPLY = 5;

        String VISIT_USER_LIST = "VISIT_USER_LIST";//用户的首页访问量，包括教师和学生;
        String VISIT_TEACHER_LIST = "VISIT_TEACHER_LIST";//ASP的首页访问量，包括示范校
        String VISIT_SCHOOL_LIST = "VISIT_SCHOOL_LIST";//加盟校的首页访问量
        String VISIT_CLASS_LIST = "VISIT_CLASS_LIST";//班级首页的访问量


}
