/**
 * NewDocumentConstants.java.
 * User: fengch  Date: 2005-3-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.util;

/**
 * 在这里添加完常数后还需要修改NewDocumentUtil.getTitle(int type)，NewDocumentUtil.getUploadPath(int type, int aspID)，NewDocumentUtil.getParentType(int type)这三个方法中的内容
 */
public interface NewDocumentConstants
{
        //动态资讯_ngx
        int CHINA_EDUCATION = 1;
        //动态资讯----远教教育
        int CHINA_EDUCATION_SASAC = 11;
        //动态资讯---农业教育
        int CHINA_EDUCATION_REGION = 12;
        //动态资讯---体系教育
        int CHINA_EDUCATION_OTHER = 13;
        //动态资讯---
        int CHINA_EDUCATION_CENTER = 14;

        //------ 国资委项目 start -----
        //政策法规_ngx
        int POLITICS_EDUCATION = 2;
        //政策法规---教育部
        int POLITICS_EDUCATION_CENTER = 21;
        //政策法规---农业部
        int POLITICS_EDUCATION_SASAC = 22;

        //站点管理_ngx
        int SKYNET = 3;
        //站点管理_工作通知
        int SKYNET_YX = 31;
        //站点管理_技术支持
        int SKYNET_JS = 32;
        //站点管理_管理制度
        int SKYNET_ZL = 33;

        //远程教育培训_ngx
        int REMOTE = 6;
        //远程教育培训--师资培训
        int REMOTE_SZ = 61;
        //远程教育培训--专题培训
        int REMOTE_CERT = 62;
        //远程教育培训--继续教育
        int REMOTE_TCP = 63;
        //远程教育培训--证书培训
        int REMOTE_ZC = 64;

        //交流园地
        int COMMUNNITE = 9;
        //交流园地_学习者交流
        int COMMUNNITE_ZY = 91;
        //交流园地_技术交流
        int COMMUNNITE_ST = 92;
        //交流园地_资源共享
        int COMMUNNITE_EX = 93;
        //交流园地_管理队伍交流
        int COMMUNNITE_M = 94;

        //公告通知
        int ANNOUNCE = 300;
        //学习帮助
        int STUDY_HELP = 400;
        //联系我们
        int CONTACTUS = 500;

        //培训通知_gzw
        int TRAIN_ANNOUNCE = 4;
        //培训通知----学历教育_gzw
        int TRAIN_ANNOUNCE_SCHOOL = 41;
        //培训通知----党校教育
        int TRAIN_ANNOUNCE_POLITICS = 42;
        //培训通知----其他
        int TRAIN_ANNOUNCE_OTHER = 43;

        //远程教育_gzw
        int REMOTE_EDUCATION = 5;
        //远程教育---短期培训
        int REMOTE_EDUCATION_SHORT = 51;
        //远程教育---长期培训
        int REMOTE_EDUCATION_LONG = 52;
        //远程教育---资格认证
        int REMOTE_EDUCATION_QUALIFICATION = 53;

        //国内培训_gzw
        int DOMESTIC = 7;
        //国内培训--国资委
        int DOMESTIC_SASAC = 71;
        //国内培训--其他部委
        int DOMESTIC_OTHER = 72;
        //国内培训--地方国资委
        int DOMESTIC_LOCAL = 73;

        //国外培训_gzw
        int OVERSEAS = 8;
        //国外培训--国资委
        int OVERSEAS_SASAC = 81;
        //国外培训--地方国资委
        int OVERSEAS_LOCAL = 82;
        //国外培训--其他机构
        int OVERSEAS_OTHER = 83;

        //学历教育
        int EDUCATION = 100;
        //重要经济动态_gzw
        int ECONOMY = 200;
        //机构简介_gzw
        int INTRODUCE_DEPARTMENT = 110;
        //政策法规_gzw
        int LAW = 120;
        //人才交流_gzw
        int PEOPLE = 130;
        //企业招聘
        int PEPOLE_CORP = 131;
        //自我推荐
        int PEPOLE_SELF = 132;
        //专家库_gzw
        int EXPERT = 140;
        //相关下载_gzw
        int DOWNLOAD = 150;
        //网站地图_gzw
        int MAP = 160;
        //培训计划_gzw
        int TRAIN = 170;
        //专栏
        int COLUMN = 180;
        //专栏
        int SAMPLE = 181;
        //交流园地_gzw
        int COMMUNION = 190;
        //需求调查
        int DEMAND = 210;
        //留言板
        int MESSAGE = 220;
        //查找结果
        int result = 230;
        //图片新闻_gzw
        int NEWS_PIC = 240;
        int NEWS_SCHOOL_PIC = 241;
        int NEWS_STUDENT_PIC = 242;
        int NEWS_TRAINSCHOOL_PIC = 243;
        //体验中心_gzw
        int EXPERIENCE_CENTER = 250;

        //教育新闻
        int EDUCATION_NEWS = 252;
        
        //滚动条信息
        int SCROLLNEWS = 256;
        //在门户显示
        int IS_VIEW = 1;
        //不在门户显示
        int NOT_IS_VIEW = 0;

        //客户服务
        int SERVICE = 260;
        //课件演示
        int COURSEDEMO = 261;
        //关于我们
        int ABOUTUS = 262;
        //成功案例
        int EXAMPLE = 263;
        //公共课程
        int PUBICCOURSE = 264;
        //培训班
        int CERT = 265;
        //------ 国资委项目 end -----
        
        /*新理念项目  START*/
        /*总网校首页*/
        int MASTER_PORTAL = 1000;
        /*资讯管理*/
        int MASTER_PORTAL_ZIXUN = 1001;
        /*通知公告*/
        int MASTER_PORTAL_ANNOUNCE = 10011;
        /*站内新闻*/
        int MASTER_PORTAL_NEWS = 10012;
        /*培训信息*/
        int MASTER_PORTAL_TRAININFO = 10013;
        /*招生招聘*/
        int MASTER_PORTAL_ZSZP = 10014;
        /*媒体报道*/
        int MASTER_PORTAL_ADV = 10015;
        /*专家讲座*/
        int MASTER_PORTAL_EXPERT = 1002;
        /*培训与课程*/
        int MASTER_PORTAL_TRAIN = 1003;
        /*教材介绍*/
        int MASTER_PORTAL_BOOK = 1004;
        /*培训学校*/
        int MASTER_PORTAL_TRAINSCHOOL = 1005;

        /*学校资讯*/
        int SCHOOL_OVERVIEW = 600;
        /*学校简介*/
        int SCHOOL_OVERVIEW_BREIF = 601;
        /*学校活动*/
        int SCHOOL_OVERVIEW_ACTIVE = 602;
        /*校园公告*/
        int SCHOOL_OVERVIEW_ANNOUNCE = 603;
        /*学校新闻*/
        int SCHOOL_OVERVIEW_NEWS = 604;
        /*学校概况*/
        int SCHOOL_OVERVIEW_VIEW = 605;
        /*学校荣誉*/
        int SCHOOL_OVERVIEW_HONOR = 606;
        /*学校风貌*/
        int SCHOOL_OVERVIEW_OUTLOOK = 607;
        /*校长致辞*/
        int SCHOOL_OVERVIEW_MASTER = 608;
        /*招生报名*/
        int SCHOOL_ZHAOSHENG = 609;
        /*教师招聘*/
        int SCHOOL_ZHAOPIN = 610;
        /*校园明星*/
        int SCHOOL_STAR = 611;
        /*学生感言*/
        int SCHOOL_STUDNET = 612;
        /*教学成果*/
        int SCHOOL_SUCCESS = 613;
        /*家长感言*/
        int SCHOOL_PARENTS = 614;
        /*校长风采*/
        int SCHOOL_MASTER = 616;
        /*新理念项目  END*/
}
