/**
 * SecurityConstants.java.
 * User: dengj  Date: 2004-4-30
 * 
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.bean;

public interface SecurityConstants
{
        // ******************  Session ******************
        public static final String SES_SYS_SECURE_TOKEN = "ses_sys_secure_session_token";
        public static final String PERMISSION_KEY = "Permission_key";

        // ****************** Default UserID *****************
        public static final int USERID_SYSADMIN = 1;
        public static final int USERID_ORGADMIN = 2;
        public static final int USERID_SYSGUEST = 3;

        // ****************** Default Role *****************
        public static final int ROLE_SYS_ADMINISTRATOR = 6;
        public static final int ROLE_SYS_GUEST = 2;
        public static final int ROLE_SYS_STAFF = 1;
        public static final int ROLE_ORG_ADMINISTRATOR = 4;
        public static final int ROLE_ORG_SECRETARY = 5;
        public static final int ROLE_ORG_STAFF = 2;
        public static final int ROLE_TRAINING_ADMINISTRATOR = 10;
        public static final int ROLE_COURSE_ADMINISTRATOR = 7;
        public static final int ROLE_COURSE_TEACHER = 8;
        public static final int ROLE_COURSR_STUDENT = 9;
        public static final int ROLE_COURSR_GUEST = 11;
        public static final int ROLE_RESOURCE_PROVIDER = 12;
        public static final int ROLE_ORG_CUSTOMER = 13;
        /*xln��Ŀѧ���ҳ�*/
        public static final int ROLE_STUPARENT = 14;

        public static final int DEFAULT_RELATIONID_PLATFORM = 0;
        public static final int DEFAULT_RELATIONID = 0;

        public static final int DEFAULT_USERID = 0;

        /**
         * The default organ is root
         */
        public static final int DEFAULT_ORGID = 0;

        /**
         * Default asp is the default asp organizition, e.g. "HUAXIA DADI"
         */
        public static final int DEFAULT_ASPID = 1;

        // ****************** Relation Type *****************
        public static final int USER_PLATFORM_RELATION = 1;
        public static final int USER_ORGAN_RELATION = 1;  //ƽ̨�ͻ�����һ  ԭ��Ϊ2
        public static final int USER_COURSE_RELATION = 3;
        public static final int USER_CERTIFICATE_RELATION = 4;
        public static final int USER_PROJECT_RELATION = 5;
        public static final int USER_DEFAULT_RELATION = 0;
        public static final int USER_OFFLINE_CERTIFICATE_RELATION = 6; //�Ѳ���ѵ��
        public static final int USER_EXAM_RELATION = 7; //��������
        public static final int USER_VCLASS_RELATION = 8; //ͬ������
        public static final int USER_METTING_RELATION = 9; //���ϻ���        

        // ****************** Role Scope *****************
        public static final int ROLE_SCOPE_SYSTEM = 1;
        public static final int ROLE_SCOPE_ORGAN = 2;
        public static final int ROLE_SCOPE_COURSE = 3;

        // ****************** Permission ID *****************
        //System management module
        public static final int SYSTEM_ANNOUNCE_MANAGE = 1010;
        public static final int SYSTEM_ORGAN_MANAGE = 1020;
        public static final int SYSTEM_USER_MANAGE = 1030;
        public static final int SYSTEM_ROLE_MANAGE = 1040;
        public static final int SYSTEM_TOOLS_MANAGE = 1050;
        public static final int SYSTEM_CONFIG_MANAGE = 1060;
        public static final int SYSTEM_INFO_MANAGE = 1070;
        public static final int SYSTEM_HELP_MANAGE = 1080;
        public static final int SYSTEM_ASSESSMENT_MANAGE = 1090;
        public static final int SYSTEM_LOG_MANAGE = 1100;
        public static final int SYSTEM_DELETE_MANAGE = 1110;
        public static final int SYSTEM_BACKUP_MANAGE = 1120;
        public static final int SYSTEM_VOD_MANAGE = 1130;                      //VOD�㲥
        public static final int SYSTEM_NEWS_MANAGE = 1140;                     //���Ź���
        public static final int SYSTEM_TOOLSDOWN_MANAGE = 1150;                //��������
        public static final int SYSTEM_FLUXSTAT_MANAGE = 1160;                 //����ͳ��


        //Organ Management module
        public static final int ORG_ANNOUNCE_MANAGE = 2010;        //����
        public static final int ORG_ORGAN_MANAGE = 2020;            //��������
        public static final int ORG_USER_MANAGE = 2030;             //�û�����
        public static final int ORG_ROLE_MANAGE = 2040;             //�������
        public static final int ORG_ASP_MANAGE = 2050;              //��ѵ�ƻ�
        public static final int ORG_MAP_BROWSER = 2060;             //��������
        public static final int ORG_PAGE_CUSTOMIZE = 2070;         //��ѵ����
        public static final int ORG_COLSIGN_CUSTOMIZE = 2080;      //��������
        public static final int TAINING_GANGWEI_MANAGE = 2090;             //��λ����
        public static final int TAINING_COURSE_MANAGE = 2100;              //��ѵ�γ�
        public static final int TAINING_CERTIFICATE_MANAGE = 2100;               //��ѵ��
        public static final int TAINING_RELEASE_MANAGE = 2120;                  //����
        public static final int TAINING_SELECT_MANAGE = 2130;            //ѡ��
        public static final int TAINING_GRADUATE_MANAGE = 2140;                //��ҵ����
        public static final int TAINING_TOPTEN_MANAGE = 2150;              //���а�
        public static final int TAINING_COMMENDCOURSE_MANAGE = 2160;      //�γ�Ԥ��

        public static final int ORG_ANNOUNCE_VIEW = 2011;
        public static final int ORG_ORGAN_VIEW = 2021;
        public static final int ORG_USER_VIEW = 2031;
        public static final int ORG_ROLE_VIEW = 2041;
        public static final int ORG_ASP_VIEW = 2051;
        public static final int ORG_MAP_VIEW = 2061;
        public static final int ORG_PAGE_VIEW = 2071;
        public static final int ORG_COLSIGN_VIEW = 2081;
        public static final int TAINING_GANGWEI_VIEW = 2091;
        public static final int TAINING_COURSE_VIEW = 2101;
        public static final int TAINING_CERTIFICATE_VIEW = 2111;
        public static final int TAINING_RELEASE_VIEW = 2121;
        public static final int TAINING_SELECT_VIEW = 2131;
        public static final int TAINING_GRADUATE_VIEW = 2141;
        public static final int TAINING_TOPTEN_VIEW = 2151;
        public static final int TAINING_COMMENDCOURSE_VIEW = 2161;


        //Training Management module
        public static final int TAINING_ANNOUNCE_MANAGE = 5010;
        public static final int TAINING_INFO_MANAGE = 5020;
        //public static final int TAINING_COURSE_MANAGE = 5030;
        //public static final int TAINING_CERTIFICATE_MANAGE = 5040;
        //public static final int TAINING_RELEASE_MANAGE = 5050;
        //public static final int TAINING_SELECT_MANAGE = 5060;
        public static final int TAINING_PROJECT_MANAGE = 5070;
        public static final int TAINING_PLAN_MANAGE = 5080;
        public static final int TAINING_BOOK_MANAGE = 5090;
        public static final int TAINING_DEVICE_MANAGE = 5100;
        //public static final int TAINING_GRADUATE_MANAGE = 5110;
        public static final int TAINING_ASSESMENT_MANAGE = 5120;
        public static final int TAINING_REPORT_MANAGE = 5130;
        public static final int TAINING_SIGN_MANAGE = 5140;
        public static final int TAINING_CONFIG_MANAGE = 5150;
        public static final int TAINING_RESEARCH_MANAGE = 5160;
        // public static final int TAINING_TOPTEN_MANAGE = 5170;			        //���а����
        //public static final int TAINING_COMMENDCOURSE_MANAGE = 5180;		        //�γ�Ԥ�����
        public static final int TAINING_SHENPI_MANAGE = 5190;			//������������
        public static final int TAINING_OUTLAY_MANAGE = 5200;			//��ѵ���ѹ���
        //public static final int TAINING_GANGWEI_MANAGE = 5210;			//��λ����



        public static final int TAINING_ANNOUNCE_VIEW = 5011;
        public static final int TAINING_INFO_VIEW = 5021;
        // public static final int TAINING_COURSE_VIEW = 5031;
        //public static final int TAINING_CERTIFICATE_VIEW = 5041;
        //public static final int TAINING_RELEASE_VIEW = 5051;
        //public static final int TAINING_SELECT_VIEW = 5061;
        public static final int TAINING_PROJECT_VIEW = 5071;
        public static final int TAINING_PLAN_VIEW = 5081;
        public static final int TAINING_BOOK_VIEW = 5091;
        public static final int TAINING_DEVICE_VIEW = 5101;
        //public static final int TAINING_GRADUATE_VIEW = 5111;
        public static final int TAINING_ASSESMENT_VIEW = 5121;
        public static final int TAINING_REPORT_VIEW = 5131;
        public static final int TAINING_SIGN_VIEW = 5141;
        public static final int TAINING_CONFIG_VIEW = 5151;
        public static final int TAINING_RESEARCH_VIEW = 5161;
        //public static final int TAINING_TOPTEN_VIEW = 5171;		//���а����
        //public static final int TAINING_COMMENDCOURSE_VIEW = 5181;	//�γ�Ԥ�����
        public static final int TAINING_SHENPI_VIEW = 5191;		//�����������
        public static final int TAINING_OUTLAY_VIEW = 5201;		//��ѵ�������
        //public static final int TAINING_GANGWEI_VIEW = 5211;			//��λ���

        //Course module
        public static final int COURSE_ANNOUNCE_MANAGE = 3010;
        public static final int COURSE_INFO_MANAGE = 3020;
        public static final int COURSE_USER_MANAGE = 3030;
        public static final int COURSE_CONTENT_MANAGE = 3040;
        public static final int COURSE_ASSIGNMENT_MANAGE = 3050;
        public static final int COURSE_BOOK_MANAGE = 3060;
        public static final int COURSE_COMMUNION_MANAGE = 3070;
        public static final int COURSE_DISCUSSION_MANAGE = 3080;
        public static final int COURSE_LINK_MANAGE = 3090;
        public static final int COURSE_TOOL_MANAGE = 3100;          //ѧϰ���Ϲ���
        public static final int COURSE_ASSESSMENT_MANAGE = 3110;
        public static final int COURSE_PAPER_MANAGE = 3120;
        public static final int COURSE_SYNCH_MANAGE = 3130;
        public static final int COURSE_CALENDER_MANAGE = 3140;
        public static final int COURSE_SURVY_MANAGE = 3150;

        public static final int COURSE_ANNOUNCE_VIEW = 3011;
        public static final int COURSE_INFO_VIEW = 3021;
        public static final int COURSE_USER_VIEW = 3031;
        public static final int COURSE_CONTENT_VIEW = 3041;
        public static final int COURSE_ASSIGNMENT_VIEW = 3051;
        public static final int COURSE_BOOK_VIEW = 3061;
        public static final int COURSE_COMMUNION_VIEW = 3071;
        public static final int COURSE_DISCUSSION_VIEW = 3081;
        public static final int COURSE_LINK_VIEW = 3091;
        public static final int COURSE_TOOL_VIEW = 3101;         //ѧϰ�������
        public static final int COURSE_ASSESSMENT_VIEW = 3111;
        public static final int COURSE_PAPER_VIEW = 3121;
        public static final int COURSE_SYNCH_VIEW = 3131;
        public static final int COURSE_CALENDER_VIEW = 3141;
        public static final int COURSE_SURVY_VIEW = 3151;

        //Myspace module
        public static final int MYSPACE_ANNOUNCE_VIEW = 4010;
        public static final int MYSPACE_CALENDER_VIEW = 4020;
        public static final int MYSPACE_STUDYHISTORY_VIEW = 4030;
        public static final int MYSPACE_EMAIL_VIEW = 4040;
        public static final int MYSPACE_ADDRESS_VIEW = 4050;
        public static final int MYSPACE_MESSAGE_VIEW = 4060;
        public static final int MYSPACE_PROFILE_VIEW = 4070;
        public static final int MYSPACE_UPDATEPWD_VIEW = 4080;
        public static final int MYSPACE_CUSTOMIZE_PAGE_VIEW = 4090;
        public static final int MYSPACE_MYCOURSE_VIEW = 4100;
        public static final int MYSPACE_MYCERT_VIEW = 4110;
        public static final int MYSPACE_UserList_VIEW = 4120;
        public static final int MYSPACE_ONLINEEXAM_VIEW = 4130;        //���翼��
        public static final int MYSPACE_COMPLETE_VIEW = 4140;          //��ѵ����
        public static final int MYSPACE_VOD_VIEW = 4150;               //VOD�㲥
        public static final int MYSPACE_NEWS_VIEW = 4160;              //�������
        public static final int MYSPACE_TOOLS_VIEW = 4170;             //�����������
        public static final int MYSPACE_SIGN_VIEW = 4180;             //���˱���
        public static final int MYSPACE_BLOG_VIEW = 4190;             //BLOG�ռ�


        public static final int ASSESMENT_ALL_VIEW = 6010;             //�ſ�����
        public static final int ASSESMENT_USER_VIEW = 6020;            //�û�����
        public static final int ASSESMENT_COURSE_VIEW = 6030;          //�γ̱���
        public static final int ASSESMENT_CLASS_VIEW = 6040;           //��ѵ�౨��
        public static final int ASSESMENT_COURSEASSESMENT_VIEW = 6050; //�γ�����
        public static final int ASSESMENT_GRADUATE_VIEW = 6060;        //��ҵ����
        public static final int ASSESMENT_REPORT_VIEW = 6070;          //ͳ�Ʊ���
        public static final int ASSESMENT_REPORTTOOL_VIEW = 6080;      //������
        public static final int ASSESMENT_VOE_VIEW = 6090;             //��������

        //��ѵ��Ȩ��
        public static final int TRAINCLASS_ANNOUNCE_MANAGE = 10010;    //�������
        public static final int TRAINCLASS_CALENDER_MANAGE = 10020;    //��������
        public static final int TRAINCLASS_RESOURCE_MANAGE = 10030;    //��Դ����
        public static final int TRAINCLASS_INTRO_MANAGE = 10040;       //���ܹ���
        public static final int TRAINCLASS_USER_MANAGE = 10050;        //��ѵ��Ա����
        public static final int TRAINCLASS_TEACHER_MANAGE = 10060;     //��ѵ��ʦ����
        public static final int TRAINCLASS_COURSE_MANAGE = 10070;      //��ѵ�γ̹���
        public static final int TRAINCLASS_DISCUSS_MANAGE = 10080;    //ͬ�����۹���
        public static final int TRAINCLASS_RESEARCH_MANAGE = 10090;    //���鷴������
        public static final int TRAINCLASS_EVALUATE_MANAGE = 10100;    //��������

        public static final int TRAINCLASS_ANNOUNCE_VIEW = 10011;      //�������
        public static final int TRAINCLASS_CALENDER_VIEW = 10021;      //�������
        public static final int TRAINCLASS_RESOURCE_VIEW = 10031;      //��Դ���
        public static final int TRAINCLASS_INTRO_VIEW = 10041;         //�������
        public static final int TRAINCLASS_USER_VIEW = 10051;          //��ѵ��Ա���
        public static final int TRAINCLASS_TEACHER_VIEW = 10061;       //��ѵ��ʦ���
        public static final int TRAINCLASS_COURSE_VIEW = 10071;        //��ѵ�γ����
        public static final int TRAINCLASS_DISCUSS_VIEW = 10081;      //ͬ���������
        public static final int TRAINCLASS_RESEARCH_VIEW = 10091;      //���鷴�����
        public static final int TRAINCLASS_EVALUATE_VIEW = 10101;      //�������

        public static final int SOURCE_VIEW = 11010;    //��Դ���

        //source manage
        public static final int SOURCE_COMMONUSER_UPLOAD_VIEW = 12001;    //'������'
        public static final int SOURCE_PUBLIC_MANAGE = 12010;    //������Դ����
        public static final int SOURCE_PUBLIC__VIEW = 12011;    //������Դ���

        public static final int SOURCE_COURSE_MANAGE = 12020;    //�γ���Դ����
        public static final int SOURCE_COURSE__VIEW = 12021;    //�γ���Դ���

        public static final int SOURCE_TYPES_MANAGE = 12031;          //��Դ��������
        public static final int SOURCE_CONTENT_SERVER_MANAGE = 12041; //�γ���Դ�������
        public static final int SOURCE_LANGUAGE_MANAGE = 12051;       //��������
        public static final int SOURCE_SETTING_MANAGE = 12061; //��������
        public static final int SOURCE_ADVSEARCH_MANAGE = 12071; //�߼�����

        public static final int STUDY_RECORD_ZHUNRU = 11;     //׼�뿼��
        public static final int STUDY_RECORD_PINGSHI = 12;    //ƽʱ����
        public static final int STUDY_RECORD_JIEDUAN = 13;    //�׶β���
        public static final int STUDY_RECORD_JIEYE = 14;      //��ҵ����
        public static final int STUDY_RECORD_GONGLI = 15;      //����ѧУ����



}
