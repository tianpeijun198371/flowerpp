/**
 * NewDocumentUtil.java.
 * User: fengch Date: 2005-6-9 13:47:50
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.util;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;

import javax.servlet.http.HttpServletRequest;

public class NewDocumentUtil
{
        /**
         * 返回附件的地址。
         * 因为有可能有些模块的附件有初始值，这些初始值会放在我们包里面。
         * 比如网络教育简介。
         *
         * @param request
         * @param ndm
         * @return
         */
        public static String getNewDocumentAttachment(HttpServletRequest request,
                                                      NewDocumentModel ndm)
        {
                String link = null;
                if (ndm.getLink() == null)
                {
                        link = request.getContextPath() +
                                ndm.getRemark1();
                }
                else
                {
                        link = Config.getUploadVirtualPath() +
                                ndm.getLink();
                }
                return link;

        }

        /**
         * 获得type的名称
         *
         * @param type
         * @return
         */
        public static String getTitle(int type)
        {
                String title = null;
                //ngx start
                //动态资讯
                if (type == NewDocumentConstants.CHINA_EDUCATION)
                {
                        title = "动态资讯";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_SASAC)
                {
                        title = "远教教育";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_REGION)
                {
                        title = "农业教育";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_OTHER)
                {
                        title = "体系教育";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_CENTER)
                {
                        title = "备用";
                }
                //政策法规
                else if (type == NewDocumentConstants.POLITICS_EDUCATION)
                {
                        title = "政策法规";
                }
                else if (type == NewDocumentConstants.POLITICS_EDUCATION_CENTER)
                {
                        title = "教育部";
                }
                else if (type == NewDocumentConstants.POLITICS_EDUCATION_SASAC)
                {
                        title = "农业部";
                }
                //卫星网管理
                else if (type == NewDocumentConstants.SKYNET)
                {
                        title = "站点管理";
                }
                else if (type == NewDocumentConstants.SKYNET_YX)
                {
                        title = "工作通知";
                }
                else if (type == NewDocumentConstants.SKYNET_JS)
                {
                        title = "技术支持";
                }
                else if (type == NewDocumentConstants.SKYNET_ZL)
                {
                        title = "管理制度";
                }
                //远程教育培训
                else if (type == NewDocumentConstants.REMOTE)
                {
                        title = "远程教育培训";
                }
                else if (type == NewDocumentConstants.REMOTE_SZ)
                {
                        title = "师资培训";
                }
                else if (type == NewDocumentConstants.REMOTE_CERT)
                {
                        title = "专题培训";
                }
                else if (type == NewDocumentConstants.REMOTE_TCP)
                {
                        title = "继续教育";
                }
                else if (type == NewDocumentConstants.REMOTE_ZC)
                {
                        title = "证书培训";
                }
                //交流园地
                else if (type == NewDocumentConstants.COMMUNNITE)
                {
                        title = "交流园地";
                }
                else if (type == NewDocumentConstants.COMMUNNITE_ZY)
                {
                        title = "学习者交流";
                }
                else if (type == NewDocumentConstants.COMMUNNITE_ST)
                {
                        title = "技术交流";
                }
                else if (type == NewDocumentConstants.COMMUNNITE_EX)
                {
                        title = "资源共享";
                }
                else if (type == NewDocumentConstants.COMMUNNITE_M)
                {
                        title = "管理队伍交流";
                }
                //其他
                else if (type == NewDocumentConstants.ANNOUNCE)
                {
                        title = "公告通知";
                }
                else if (type == NewDocumentConstants.STUDY_HELP)
                {
                        title = "学习帮助";
                }
                else if (type == NewDocumentConstants.CONTACTUS)
                {
                        title = "关于我们";
                }
                //ngx   end
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE)
                {
                        title = "培训通知";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_SCHOOL)
                {
                        title = "学历教育";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_POLITICS)
                {
                        title = "党校教育";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_OTHER)
                {
                        title = "其他";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION)
                {
                        title = "远程教育";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_SHORT)
                {
                        title = "短期培训";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_LONG)
                {
                        title = "长期培训";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_QUALIFICATION)
                {
                        title = "资格认证";
                }
                else if (type == NewDocumentConstants.DOMESTIC)
                {
                        title = "国内培训";
                }
                else if (type == NewDocumentConstants.DOMESTIC_SASAC)
                {
                        title = "国资委";
                }
                else if (type == NewDocumentConstants.DOMESTIC_OTHER)
                {
                        title = "其他部委";
                }
                else if (type == NewDocumentConstants.DOMESTIC_LOCAL)
                {
                        title = "地方国资委";
                }
                else if (type == NewDocumentConstants.OVERSEAS)
                {
                        title = "国外培训";
                }
                else if (type == NewDocumentConstants.OVERSEAS_SASAC)
                {
                        title = "国资委";
                }
                else if (type == NewDocumentConstants.OVERSEAS_LOCAL)
                {
                        title = "地方国资委";
                }
                else if (type == NewDocumentConstants.OVERSEAS_OTHER)
                {
                        title = "其他机构";
                }
                else if (type == NewDocumentConstants.EDUCATION)
                {
                        title = "学历教育";
                }
                else if (type == NewDocumentConstants.ECONOMY)
                {
                        title = "经济动态";
                }
                else if (type == NewDocumentConstants.INTRODUCE_DEPARTMENT)
                {
                        title = "机构简介";
                }
                else if (type == NewDocumentConstants.LAW)
                {
                        title = "政策法规";
                }
                else if (type == NewDocumentConstants.PEOPLE)
                {
                        title = "人才交流";
                }
                else if (type == NewDocumentConstants.PEPOLE_CORP)
                {
                        title = "企业招聘";
                }
                else if (type == NewDocumentConstants.PEPOLE_SELF)
                {
                        title = "自我推荐";
                }
                else if (type == NewDocumentConstants.EXPERT)
                {
                        title = "专家库";
                }
                else if (type == NewDocumentConstants.DOWNLOAD)
                {
                        title = "相关下载";
                }
                else if (type == NewDocumentConstants.MAP)
                {
                        title = "网站地图";
                }
                else if (type == NewDocumentConstants.TRAIN)
                {
                        title = "培训计划";
                }
                else if (type == NewDocumentConstants.COLUMN)
                {

                        title = "专栏";

                        String projectName = com.ulearning.ulms.core.util.Config.getProjectName();

                        if (projectName.equals("NGX"))  //假如项目是农广校
                        {
                                title = "经典案例";
                        }
                        else if (projectName.equals("GZW"))  //假如项目是国资委
                        {
                                title = "专栏";
                        }
                }
                else if (type == NewDocumentConstants.SAMPLE)
                {
                        title = "经典案例";
                }
                else if (type == NewDocumentConstants.COMMUNION)
                {
                        title = "交流园地";
                }
                else if (type == NewDocumentConstants.DEMAND)
                {
                        title = "需求调查";
                }
                else if (type == NewDocumentConstants.MESSAGE)
                {
                        title = "意见反馈";
                }
                else if (type == NewDocumentConstants.result)
                {
                        title = "搜索结果";
                }
                else if (type == NewDocumentConstants.NEWS_PIC)
                {
                        title = "图片新闻";
                }
                else if (type == NewDocumentConstants.NEWS_TRAINSCHOOL_PIC)
                {
                        title = "培训学校FLASH图片";
                }
                else if (type == NewDocumentConstants.NEWS_SCHOOL_PIC)
                {
                        title = "示范校FLASH图片";
                }
                else if (type == NewDocumentConstants.NEWS_STUDENT_PIC)
                {
                        title = "学生专区FLASH图片";
                }
                else if (type == NewDocumentConstants.EXPERIENCE_CENTER)
                {
                        title = "体验中心";
                }
                else if (type == 251)
                {
                        title = "干部教育动态";
                }                              
                else if (type == NewDocumentConstants.EDUCATION_NEWS)
                {
                        title = "教育新闻";
                }
                else if (type == 254)
                {
                        title = "培训动态";
                }
                else if (type == 255)
                {
                        title = "党校培训";
                }
                else if (type == NewDocumentConstants.SCROLLNEWS)
                {
                        title = "滚动条信息";
                }
                else if (type == NewDocumentConstants.SERVICE)
                {
                        title = "客户服务";
                }
                else if (type == NewDocumentConstants.COURSEDEMO)
                {
                        title = "课件演示";
                }
                else if (type == NewDocumentConstants.ABOUTUS)
                {
                        title = "关于我们";
                }
                else if (type == NewDocumentConstants.EXAMPLE)
                {
                        title = "成功案例";
                }
                else if (type == NewDocumentConstants.PUBICCOURSE)
                {
                        title = "公共课程";
                }
                else if (type == NewDocumentConstants.CERT)
                {
                        title = "培训班";
                }
                /*新理念项目*/
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW)
                {
                        title = "学校资讯";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_BREIF)
                {
                        title = "学校简介";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_ACTIVE)
                {
                        title = "学校活动";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_ANNOUNCE)
                {
                        title = "校园公告";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_NEWS)
                {
                        title = "学校新闻";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_VIEW)
                {
                        title = "学校概况";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_HONOR)
                {
                        title = "学校荣誉";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_OUTLOOK)
                {
                        title = "学校风貌";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_MASTER)
                {
                        title = "校长致辞";
                }
                else if (type == NewDocumentConstants.SCHOOL_ZHAOSHENG)
                {
                        title = "招生报名";
                }
                else if (type == NewDocumentConstants.SCHOOL_ZHAOPIN)
                {
                        title = "教师招聘";
                }
                else if (type == NewDocumentConstants.SCHOOL_SUCCESS)
                {
                        title = "成果展示";
                }
                else if (type == NewDocumentConstants.SCHOOL_STAR)
                {
                        title = "校园明星";
                }
                else if (type == NewDocumentConstants.SCHOOL_STUDNET)
                {
                        title = "学生感言";
                }
                else if (type == NewDocumentConstants.SCHOOL_PARENTS)
                {
                        title = "家长感言";
                }
                else if (type == NewDocumentConstants.SCHOOL_MASTER)
                {
                        title = "校长风采";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL)
                {
                        title = "首页";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_ZIXUN)
                {
                        title = "资讯管理";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_ANNOUNCE)
                {
                        title = "通知公告";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_NEWS)
                {
                        title = "站内新闻";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAININFO)
                {
                        title = "培训信息";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_ZSZP)
                {
                        title = "招生招聘";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_ADV)
                {
                        title = "媒体报道";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_EXPERT)
                {
                        title = "专家讲座";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAIN)
                {
                        title = "培训与课程";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_BOOK)
                {
                        title = "教材介绍";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAINSCHOOL)
                {
                        title = "培训学校";
                }
                else
                {
                        title = "未知栏目（错误）";
                }

                return title;
        }


        /**
         * 根据type返回parentType
         *
         * @param type
         * @return
         */
        public static int getParentType(int type)
        {
                if (type == NewDocumentConstants.CHINA_EDUCATION || type == NewDocumentConstants.CHINA_EDUCATION_SASAC || type == NewDocumentConstants.CHINA_EDUCATION_REGION || type == NewDocumentConstants.CHINA_EDUCATION_OTHER || type == NewDocumentConstants.CHINA_EDUCATION_CENTER)
                {
                        return NewDocumentConstants.CHINA_EDUCATION;
                }
                else
                if (type == NewDocumentConstants.POLITICS_EDUCATION || type == NewDocumentConstants.POLITICS_EDUCATION_CENTER || type == NewDocumentConstants.POLITICS_EDUCATION_SASAC)
                {
                        return NewDocumentConstants.POLITICS_EDUCATION;
                }
                else
                if (type == NewDocumentConstants.SKYNET || type == NewDocumentConstants.SKYNET_YX || type == NewDocumentConstants.SKYNET_JS || type == NewDocumentConstants.SKYNET_ZL)
                {
                        return NewDocumentConstants.SKYNET;
                }
                else
                if (type == NewDocumentConstants.REMOTE || type == NewDocumentConstants.REMOTE_SZ || type == NewDocumentConstants.REMOTE_CERT || type == NewDocumentConstants.REMOTE_TCP)
                {
                        return NewDocumentConstants.REMOTE;
                }
                else
                if (type == NewDocumentConstants.COMMUNNITE || type == NewDocumentConstants.COMMUNNITE_ZY || type == NewDocumentConstants.COMMUNNITE_ST || type == NewDocumentConstants.COMMUNNITE_EX || type == NewDocumentConstants.COMMUNNITE_M)
                {
                        return NewDocumentConstants.COMMUNNITE;
                }
                else if (type == NewDocumentConstants.ANNOUNCE)
                {
                        return NewDocumentConstants.ANNOUNCE;
                }
                else if (type == NewDocumentConstants.STUDY_HELP)
                {
                        return NewDocumentConstants.STUDY_HELP;
                }
                else if (type == NewDocumentConstants.CONTACTUS)
                {
                        return NewDocumentConstants.CONTACTUS;
                }

                else
                if (type == NewDocumentConstants.TRAIN_ANNOUNCE || type == NewDocumentConstants.TRAIN_ANNOUNCE_SCHOOL || type == NewDocumentConstants.TRAIN_ANNOUNCE_POLITICS || type == NewDocumentConstants.TRAIN_ANNOUNCE_OTHER)
                {
                        return NewDocumentConstants.TRAIN_ANNOUNCE;
                }
                else
                if (type == NewDocumentConstants.REMOTE_EDUCATION || type == NewDocumentConstants.REMOTE_EDUCATION_SHORT || type == NewDocumentConstants.REMOTE_EDUCATION_LONG || type == NewDocumentConstants.REMOTE_EDUCATION_QUALIFICATION)
                {
                        return NewDocumentConstants.REMOTE_EDUCATION;
                }
                else
                if (type == NewDocumentConstants.DOMESTIC || type == NewDocumentConstants.DOMESTIC_SASAC || type == NewDocumentConstants.DOMESTIC_OTHER || type == NewDocumentConstants.DOMESTIC_LOCAL)
                {
                        return NewDocumentConstants.DOMESTIC;
                }
                else
                if (type == NewDocumentConstants.OVERSEAS || type == NewDocumentConstants.OVERSEAS_SASAC || type == NewDocumentConstants.OVERSEAS_LOCAL || type == NewDocumentConstants.OVERSEAS_OTHER)
                {
                        return NewDocumentConstants.OVERSEAS;
                }
                else
                if (type == NewDocumentConstants.PEOPLE || type == NewDocumentConstants.PEPOLE_CORP || type == NewDocumentConstants.PEPOLE_SELF)
                {
                        return NewDocumentConstants.PEOPLE;
                }
                else
                if (type == NewDocumentConstants.SCHOOL_OVERVIEW_BREIF || type == NewDocumentConstants.SCHOOL_OVERVIEW_ACTIVE
                        || type == NewDocumentConstants.SCHOOL_OVERVIEW_ANNOUNCE || type == NewDocumentConstants.SCHOOL_OVERVIEW_NEWS
                        || type == NewDocumentConstants.SCHOOL_OVERVIEW_VIEW || type == NewDocumentConstants.SCHOOL_OVERVIEW_HONOR
                        || type == NewDocumentConstants.SCHOOL_OVERVIEW_OUTLOOK || type == NewDocumentConstants.SCHOOL_OVERVIEW_MASTER)
                {
                        return NewDocumentConstants.SCHOOL_OVERVIEW;
                }
                else if (type == NewDocumentConstants.SCHOOL_ZHAOSHENG)
                {
                        return NewDocumentConstants.SCHOOL_ZHAOSHENG;
                }
                else if (type == NewDocumentConstants.SCHOOL_ZHAOPIN)
                {
                        return NewDocumentConstants.SCHOOL_ZHAOPIN;
                }
                else if (type == NewDocumentConstants.SCHOOL_SUCCESS)
                {
                        return NewDocumentConstants.SCHOOL_SUCCESS;
                }

                else if (type == NewDocumentConstants.MASTER_PORTAL)
                {
                        return NewDocumentConstants.MASTER_PORTAL;
                }
                else
                if (type == NewDocumentConstants.MASTER_PORTAL_ANNOUNCE || type == NewDocumentConstants.MASTER_PORTAL_NEWS
                        || type == NewDocumentConstants.MASTER_PORTAL_TRAININFO || type == NewDocumentConstants.MASTER_PORTAL_ZSZP
                        || type == NewDocumentConstants.MASTER_PORTAL_ADV)
                {
                        return NewDocumentConstants.MASTER_PORTAL_ZIXUN;
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_EXPERT)
                {
                        return NewDocumentConstants.MASTER_PORTAL_EXPERT;
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAIN)
                {
                        return NewDocumentConstants.MASTER_PORTAL_TRAIN;
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_BOOK)
                {
                        return NewDocumentConstants.MASTER_PORTAL_BOOK;
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAINSCHOOL)
                {
                        return NewDocumentConstants.MASTER_PORTAL_TRAINSCHOOL;
                }
                else
                {
                        return type; //除了以上栏目，剩下的栏目没有子栏目了，所以parentType就是type
                }
        }

        /**
         * 获得导航条
         *
         * @param type
         * @param request
         * @param parentType
         * @return
         */
        public static String getNavTile(int type, HttpServletRequest request, int parentType)
        {
                String nav = null;

                if (parentType == type)
                {
                        nav = oneNav(NewDocumentUtil.getTitle(type));
                }
                else
                {
                        nav = twoNav(NewDocumentUtil.getTitle(parentType), NewDocumentUtil.getTitle(type), parentType, type, request.getContextPath());
                }

                return nav;
        }

        /**
         * 两层的导航条
         *
         * @param firstName
         * @param secondName
         * @param parentType
         * @param type
         * @param contextString
         * @return
         */
        private static String twoNav(String firstName, String secondName, int parentType, int type, String contextString)
        {
                return "<a href=\"index.jsp?type=" + parentType + "&parentType=" + parentType + "\" target=\"_self\">" + firstName + "</a><img src=\"" + contextString + "/images/browse.gif\">" + secondName;
        }

        /**
         * 一层的导航条
         *
         * @param firstName
         * @return
         */
        private static String oneNav(String firstName)
        {
                return firstName;
        }

        /**
         * 获得action 参数是add时是添加操作，其他均为修改操作
         *
         * @param type
         * @param addOrUpdate
         * @return
         */
        public static String getAction(int type, String addOrUpdate)
        {
                String action = null;
                action = "/portal/sasac/manage?operation=";

                if (addOrUpdate.equals("add"))
                {
                        action += "addDocument";
                }
                else
                {
                        action += "updDocument";
                }


                return action;
        }

        /**
         * 获得文件上传的路径
         *
         * @param type
         * @param aspID
         * @return
         */
        public static String getUploadPath(int type, int aspID)
        {
                String path = null;
                if (type == NewDocumentConstants.CHINA_EDUCATION)
                {
                        path = "/portal/CHINA_EDUCATION/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_SASAC)
                {
                        path = "/portal/CHINA_EDUCATION_SASAC/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_REGION)
                {
                        path = "/portal/CHINA_EDUCATION_REGION/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_OTHER)
                {
                        path = "/portal/CHINA_EDUCATION_OTHER/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_CENTER)
                {
                        path = "/portal/CHINA_EDUCATION_CENTER/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.POLITICS_EDUCATION)
                {
                        path = "/portal/POLITICS_EDUCATION/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.POLITICS_EDUCATION_CENTER)
                {
                        path = "/portal/POLITICS_EDUCATION_CENTER/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.POLITICS_EDUCATION_SASAC)
                {
                        path = "/portal/POLITICS_EDUCATION_SASAC/" + aspID + "/";
                }
                else
                if (type == NewDocumentConstants.SKYNET || type == NewDocumentConstants.SKYNET_YX || type == NewDocumentConstants.SKYNET_JS || type == NewDocumentConstants.SKYNET_ZL)
                {
                        path = "/portal/POLITICS_SKYNET_SASAC/" + aspID + "/";
                }
                else
                if (type == NewDocumentConstants.REMOTE || type == NewDocumentConstants.REMOTE_SZ || type == NewDocumentConstants.REMOTE_CERT || type == NewDocumentConstants.REMOTE_TCP)
                {
                        path = "/portal/POLITICS_REMOTE_SASAC/" + aspID + "/";
                }
                else
                if (type == NewDocumentConstants.COMMUNNITE || type == NewDocumentConstants.COMMUNNITE_ZY || type == NewDocumentConstants.COMMUNNITE_ST || type == NewDocumentConstants.COMMUNNITE_EX || type == NewDocumentConstants.COMMUNNITE_M)
                {
                        path = "/portal/POLITICS_COMMUNNITE_SASAC/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.ANNOUNCE)
                {
                        path = "/portal/POLITICS_ANNOUNCE_SASAC/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.STUDY_HELP)
                {
                        path = "/portal/POLITICS_STUDY_HELP_SASAC/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.CONTACTUS)
                {
                        path = "/portal/POLITICS_CONTACTUS_SASAC/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE)
                {
                        path = "/portal/TRAIN_ANNOUNCE/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_SCHOOL)
                {
                        path = "/portal/TRAIN_ANNOUNCE_SCHOOL/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_POLITICS)
                {
                        path = "/portal/TRAIN_ANNOUNCE_POLITICS/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_OTHER)
                {
                        path = "/portal/TRAIN_ANNOUNCE_OTHER/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION)
                {
                        path = "/portal/REMOTE_EDUCATION/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_SHORT)
                {
                        path = "/portal/REMOTE_EDUCATION_SHORT/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_LONG)
                {
                        path = "/portal/REMOTE_EDUCATION_LONG/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_QUALIFICATION)
                {
                        path = "/portal/REMOTE_EDUCATION_QUALIFICATION/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.DOMESTIC)
                {
                        path = "/portal/DOMESTIC/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.DOMESTIC_SASAC)
                {
                        path = "/portal/DOMESTIC_SASAC/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.DOMESTIC_OTHER)
                {
                        path = "/portal/DOMESTIC_OTHER/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.DOMESTIC_LOCAL)
                {
                        path = "/portal/DOMESTIC_LOCAL/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.OVERSEAS)
                {
                        path = "/portal/OVERSEAS/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.OVERSEAS_SASAC)
                {
                        path = "/portal/OVERSEAS_SASAC/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.OVERSEAS_LOCAL)
                {
                        path = "/portal/OVERSEAS_LOCAL/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.OVERSEAS_OTHER)
                {
                        path = "/portal/OVERSEAS_OTHER/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.EDUCATION)
                {
                        path = "/portal/EDUCATION/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.ECONOMY)
                {
                        path = "/portal/ECONOMY/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.INTRODUCE_DEPARTMENT)
                {
                        path = "/portal/INTRODUCE_DEPARTMENT/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.LAW)
                {
                        path = "/portal/LAW/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.PEOPLE)
                {
                        path = "/portal/PEOPLE/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.EXPERT)
                {
                        path = "/portal/EXPERT/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.DOWNLOAD)
                {
                        path = "/portal/DOWNLOAD/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.MAP)
                {
                        path = "/portal/MAP/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.TRAIN)
                {
                        path = "/portal/TRAIN/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.COLUMN)
                {
                        path = "/portal/COLUMN/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.COMMUNION)
                {
                        path = "/portal/COMMUNION/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.DEMAND)
                {
                        path = "/portal/DEMAND/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.MESSAGE)
                {
                        path = "/portal/MESSAGE/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.result)
                {
                        path = "/portal/result/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.NEWS_PIC)
                {
                        path = "/portal/NEWS_PIC/" + aspID + "/";
                }
                else
                if (type == NewDocumentConstants.SCHOOL_OVERVIEW_BREIF || type == NewDocumentConstants.SCHOOL_OVERVIEW_ACTIVE
                        || type == NewDocumentConstants.SCHOOL_OVERVIEW_ANNOUNCE || type == NewDocumentConstants.SCHOOL_OVERVIEW_NEWS
                        || type == NewDocumentConstants.SCHOOL_OVERVIEW_VIEW || type == NewDocumentConstants.SCHOOL_OVERVIEW_HONOR
                        || type == NewDocumentConstants.SCHOOL_OVERVIEW_OUTLOOK || type == NewDocumentConstants.SCHOOL_OVERVIEW_MASTER)
                {
                        path = "/portal/SCHOOL_OVERVIEW/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.SCHOOL_ZHAOSHENG)
                {
                        path = "/portal/SCHOOL_ZHAOSHENG/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.SCHOOL_ZHAOPIN)
                {
                        path = "/portal/SCHOOL_ZHAOPIN/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.SCHOOL_SUCCESS)
                {
                        path = "/portal/SCHOOL_SUCCESS/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL)
                {
                        path = "/portal/MASTER_PORTAL/" + aspID + "/";

                }
                else
                if (type == NewDocumentConstants.MASTER_PORTAL_ANNOUNCE || type == NewDocumentConstants.MASTER_PORTAL_NEWS
                        || type == NewDocumentConstants.MASTER_PORTAL_TRAININFO || type == NewDocumentConstants.MASTER_PORTAL_ZSZP
                        || type == NewDocumentConstants.MASTER_PORTAL_ADV)
                {
                        path = "/portal/MASTER_PORTAL_ZIXUN/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_EXPERT)
                {
                        path = "/portal/MASTER_PORTAL_EXPERT/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAIN)
                {
                        path = "/portal/MASTER_PORTAL_EXPERT/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_BOOK)
                {
                        path = "/portal/MASTER_PORTAL_BOOK/" + aspID + "/";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAINSCHOOL)
                {
                        path = "/portal/MASTER_PORTAL_TRAINSCHOOL/" + aspID + "/";
                }
                else
                {
                        path = "/portal/" + aspID + "/";
                }

                return path;
        }
}
