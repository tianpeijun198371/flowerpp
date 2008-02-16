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
         * ���ظ����ĵ�ַ��
         * ��Ϊ�п�����Щģ��ĸ����г�ʼֵ����Щ��ʼֵ��������ǰ����档
         * �������������顣
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
         * ���type������
         *
         * @param type
         * @return
         */
        public static String getTitle(int type)
        {
                String title = null;
                //ngx start
                //��̬��Ѷ
                if (type == NewDocumentConstants.CHINA_EDUCATION)
                {
                        title = "��̬��Ѷ";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_SASAC)
                {
                        title = "Զ�̽���";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_REGION)
                {
                        title = "ũҵ����";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_OTHER)
                {
                        title = "��ϵ����";
                }
                else if (type == NewDocumentConstants.CHINA_EDUCATION_CENTER)
                {
                        title = "����";
                }
                //���߷���
                else if (type == NewDocumentConstants.POLITICS_EDUCATION)
                {
                        title = "���߷���";
                }
                else if (type == NewDocumentConstants.POLITICS_EDUCATION_CENTER)
                {
                        title = "������";
                }
                else if (type == NewDocumentConstants.POLITICS_EDUCATION_SASAC)
                {
                        title = "ũҵ��";
                }
                //����������
                else if (type == NewDocumentConstants.SKYNET)
                {
                        title = "վ�����";
                }
                else if (type == NewDocumentConstants.SKYNET_YX)
                {
                        title = "����֪ͨ";
                }
                else if (type == NewDocumentConstants.SKYNET_JS)
                {
                        title = "����֧��";
                }
                else if (type == NewDocumentConstants.SKYNET_ZL)
                {
                        title = "�����ƶ�";
                }
                //Զ�̽�����ѵ
                else if (type == NewDocumentConstants.REMOTE)
                {
                        title = "Զ�̽�����ѵ";
                }
                else if (type == NewDocumentConstants.REMOTE_SZ)
                {
                        title = "ʦ����ѵ";
                }
                else if (type == NewDocumentConstants.REMOTE_CERT)
                {
                        title = "ר����ѵ";
                }
                else if (type == NewDocumentConstants.REMOTE_TCP)
                {
                        title = "��������";
                }
                else if (type == NewDocumentConstants.REMOTE_ZC)
                {
                        title = "֤����ѵ";
                }
                //����԰��
                else if (type == NewDocumentConstants.COMMUNNITE)
                {
                        title = "����԰��";
                }
                else if (type == NewDocumentConstants.COMMUNNITE_ZY)
                {
                        title = "ѧϰ�߽���";
                }
                else if (type == NewDocumentConstants.COMMUNNITE_ST)
                {
                        title = "��������";
                }
                else if (type == NewDocumentConstants.COMMUNNITE_EX)
                {
                        title = "��Դ����";
                }
                else if (type == NewDocumentConstants.COMMUNNITE_M)
                {
                        title = "������齻��";
                }
                //����
                else if (type == NewDocumentConstants.ANNOUNCE)
                {
                        title = "����֪ͨ";
                }
                else if (type == NewDocumentConstants.STUDY_HELP)
                {
                        title = "ѧϰ����";
                }
                else if (type == NewDocumentConstants.CONTACTUS)
                {
                        title = "��������";
                }
                //ngx   end
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE)
                {
                        title = "��ѵ֪ͨ";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_SCHOOL)
                {
                        title = "ѧ������";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_POLITICS)
                {
                        title = "��У����";
                }
                else if (type == NewDocumentConstants.TRAIN_ANNOUNCE_OTHER)
                {
                        title = "����";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION)
                {
                        title = "Զ�̽���";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_SHORT)
                {
                        title = "������ѵ";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_LONG)
                {
                        title = "������ѵ";
                }
                else if (type == NewDocumentConstants.REMOTE_EDUCATION_QUALIFICATION)
                {
                        title = "�ʸ���֤";
                }
                else if (type == NewDocumentConstants.DOMESTIC)
                {
                        title = "������ѵ";
                }
                else if (type == NewDocumentConstants.DOMESTIC_SASAC)
                {
                        title = "����ί";
                }
                else if (type == NewDocumentConstants.DOMESTIC_OTHER)
                {
                        title = "������ί";
                }
                else if (type == NewDocumentConstants.DOMESTIC_LOCAL)
                {
                        title = "�ط�����ί";
                }
                else if (type == NewDocumentConstants.OVERSEAS)
                {
                        title = "������ѵ";
                }
                else if (type == NewDocumentConstants.OVERSEAS_SASAC)
                {
                        title = "����ί";
                }
                else if (type == NewDocumentConstants.OVERSEAS_LOCAL)
                {
                        title = "�ط�����ί";
                }
                else if (type == NewDocumentConstants.OVERSEAS_OTHER)
                {
                        title = "��������";
                }
                else if (type == NewDocumentConstants.EDUCATION)
                {
                        title = "ѧ������";
                }
                else if (type == NewDocumentConstants.ECONOMY)
                {
                        title = "���ö�̬";
                }
                else if (type == NewDocumentConstants.INTRODUCE_DEPARTMENT)
                {
                        title = "�������";
                }
                else if (type == NewDocumentConstants.LAW)
                {
                        title = "���߷���";
                }
                else if (type == NewDocumentConstants.PEOPLE)
                {
                        title = "�˲Ž���";
                }
                else if (type == NewDocumentConstants.PEPOLE_CORP)
                {
                        title = "��ҵ��Ƹ";
                }
                else if (type == NewDocumentConstants.PEPOLE_SELF)
                {
                        title = "�����Ƽ�";
                }
                else if (type == NewDocumentConstants.EXPERT)
                {
                        title = "ר�ҿ�";
                }
                else if (type == NewDocumentConstants.DOWNLOAD)
                {
                        title = "�������";
                }
                else if (type == NewDocumentConstants.MAP)
                {
                        title = "��վ��ͼ";
                }
                else if (type == NewDocumentConstants.TRAIN)
                {
                        title = "��ѵ�ƻ�";
                }
                else if (type == NewDocumentConstants.COLUMN)
                {

                        title = "ר��";

                        String projectName = com.ulearning.ulms.core.util.Config.getProjectName();

                        if (projectName.equals("NGX"))  //������Ŀ��ũ��У
                        {
                                title = "���䰸��";
                        }
                        else if (projectName.equals("GZW"))  //������Ŀ�ǹ���ί
                        {
                                title = "ר��";
                        }
                }
                else if (type == NewDocumentConstants.SAMPLE)
                {
                        title = "���䰸��";
                }
                else if (type == NewDocumentConstants.COMMUNION)
                {
                        title = "����԰��";
                }
                else if (type == NewDocumentConstants.DEMAND)
                {
                        title = "�������";
                }
                else if (type == NewDocumentConstants.MESSAGE)
                {
                        title = "�������";
                }
                else if (type == NewDocumentConstants.result)
                {
                        title = "�������";
                }
                else if (type == NewDocumentConstants.NEWS_PIC)
                {
                        title = "ͼƬ����";
                }
                else if (type == NewDocumentConstants.NEWS_TRAINSCHOOL_PIC)
                {
                        title = "��ѵѧУFLASHͼƬ";
                }
                else if (type == NewDocumentConstants.NEWS_SCHOOL_PIC)
                {
                        title = "ʾ��УFLASHͼƬ";
                }
                else if (type == NewDocumentConstants.NEWS_STUDENT_PIC)
                {
                        title = "ѧ��ר��FLASHͼƬ";
                }
                else if (type == NewDocumentConstants.EXPERIENCE_CENTER)
                {
                        title = "��������";
                }
                else if (type == 251)
                {
                        title = "�ɲ�������̬";
                }                              
                else if (type == NewDocumentConstants.EDUCATION_NEWS)
                {
                        title = "��������";
                }
                else if (type == 254)
                {
                        title = "��ѵ��̬";
                }
                else if (type == 255)
                {
                        title = "��У��ѵ";
                }
                else if (type == NewDocumentConstants.SCROLLNEWS)
                {
                        title = "��������Ϣ";
                }
                else if (type == NewDocumentConstants.SERVICE)
                {
                        title = "�ͻ�����";
                }
                else if (type == NewDocumentConstants.COURSEDEMO)
                {
                        title = "�μ���ʾ";
                }
                else if (type == NewDocumentConstants.ABOUTUS)
                {
                        title = "��������";
                }
                else if (type == NewDocumentConstants.EXAMPLE)
                {
                        title = "�ɹ�����";
                }
                else if (type == NewDocumentConstants.PUBICCOURSE)
                {
                        title = "�����γ�";
                }
                else if (type == NewDocumentConstants.CERT)
                {
                        title = "��ѵ��";
                }
                /*��������Ŀ*/
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW)
                {
                        title = "ѧУ��Ѷ";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_BREIF)
                {
                        title = "ѧУ���";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_ACTIVE)
                {
                        title = "ѧУ�";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_ANNOUNCE)
                {
                        title = "У԰����";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_NEWS)
                {
                        title = "ѧУ����";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_VIEW)
                {
                        title = "ѧУ�ſ�";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_HONOR)
                {
                        title = "ѧУ����";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_OUTLOOK)
                {
                        title = "ѧУ��ò";
                }
                else if (type == NewDocumentConstants.SCHOOL_OVERVIEW_MASTER)
                {
                        title = "У���´�";
                }
                else if (type == NewDocumentConstants.SCHOOL_ZHAOSHENG)
                {
                        title = "��������";
                }
                else if (type == NewDocumentConstants.SCHOOL_ZHAOPIN)
                {
                        title = "��ʦ��Ƹ";
                }
                else if (type == NewDocumentConstants.SCHOOL_SUCCESS)
                {
                        title = "�ɹ�չʾ";
                }
                else if (type == NewDocumentConstants.SCHOOL_STAR)
                {
                        title = "У԰����";
                }
                else if (type == NewDocumentConstants.SCHOOL_STUDNET)
                {
                        title = "ѧ������";
                }
                else if (type == NewDocumentConstants.SCHOOL_PARENTS)
                {
                        title = "�ҳ�����";
                }
                else if (type == NewDocumentConstants.SCHOOL_MASTER)
                {
                        title = "У�����";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL)
                {
                        title = "��ҳ";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_ZIXUN)
                {
                        title = "��Ѷ����";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_ANNOUNCE)
                {
                        title = "֪ͨ����";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_NEWS)
                {
                        title = "վ������";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAININFO)
                {
                        title = "��ѵ��Ϣ";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_ZSZP)
                {
                        title = "������Ƹ";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_ADV)
                {
                        title = "ý�屨��";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_EXPERT)
                {
                        title = "ר�ҽ���";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAIN)
                {
                        title = "��ѵ��γ�";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_BOOK)
                {
                        title = "�̲Ľ���";
                }
                else if (type == NewDocumentConstants.MASTER_PORTAL_TRAINSCHOOL)
                {
                        title = "��ѵѧУ";
                }
                else
                {
                        title = "δ֪��Ŀ������";
                }

                return title;
        }


        /**
         * ����type����parentType
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
                        return type; //����������Ŀ��ʣ�µ���Ŀû������Ŀ�ˣ�����parentType����type
                }
        }

        /**
         * ��õ�����
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
         * ����ĵ�����
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
         * һ��ĵ�����
         *
         * @param firstName
         * @return
         */
        private static String oneNav(String firstName)
        {
                return firstName;
        }

        /**
         * ���action ������addʱ����Ӳ�����������Ϊ�޸Ĳ���
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
         * ����ļ��ϴ���·��
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
