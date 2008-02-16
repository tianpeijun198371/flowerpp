/**
 * ContentManageUtil.java.
 * User: Fengch  Date: 2005-6-25 17:44:44
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.util;

import com.ulearning.ulms.core.util.StringUtil;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;


public class ContentManageUtil
{
        //��Դ����ͼ������λ��
        private static String ICO_PHYSICIAL_PATH = "content/images/icon/filetype/";

        //��Դ�ļ���չ����Ӧ��ͼ���Ƿ������νHash��
        private static HashMap CONTENT_EXT_ICO_MAPPINGS = null;

        public static void initCONTENT_EXT_ICO_MAPPINGS(ServletContext sc)
        {
                CONTENT_EXT_ICO_MAPPINGS = new HashMap();

                String rootPhysicalPath = sc.getRealPath("/");
                String extIcoName = null;
                String ss = rootPhysicalPath + ICO_PHYSICIAL_PATH;

                ss = StringUtil.replaceString(ss, "/", File.separator);
                ss = StringUtil.replaceString(ss, "\\", File.separator);
                System.out.println(
                        "[ContentManageUtil]initCONTENT_EXT_ICO_MAPPINGS--------ԭʼ·�� = " +
                                ss);

                try
                {
                        File path = new File(ss);
                        String[] llist;
                        llist = path.list();

                        for (int i = 0; i < llist.length; i++)
                        {
                                System.out.println(
                                        "[ContentManageUtil]initCONTENT_EXT_ICO_MAPPINGS--------llist[i] = " +
                                                llist[i]);
                                extIcoName = FilenameUtils.getBaseName(llist[i]);
                                System.out.println(
                                        "[ContentManageUtil]initCONTENT_EXT_ICO_MAPPINGS--------extIcoName = " +
                                                extIcoName);

                                if (extIcoName != null)
                                {
                                        System.out.println(
                                                "[ContentManageUtil]initCONTENT_EXT_ICO_MAPPINGS--------extIcoName set to map = " +
                                                        extIcoName);
                                        CONTENT_EXT_ICO_MAPPINGS.put(extIcoName, "1");
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * ����type��ֵ���ض�Ӧ��Դ��������ơ�
         *
         * @param type
         * @return
         */
        public static String getTypeName(int type)
        {
                String name = "������Դ";

                if (type == ContentManageConstants.PUBLIC_CONTENT_TYPE)
                {
                        name = "������Դ";
                }
                else if (type == ContentManageConstants.PERSONAL_CONTENT_TYPE)
                {
                        name = "������Դ";
                }
                else if (type == ContentManageConstants.COURSE_CONTENT_TYPE)
                {
                        name = "�γ���Դ";
                }
                else if (type == ContentManageConstants.PAPER_CONTENT_TYPE)
                {
                        name = "������Դ";
                }
                else if (type == ContentManageConstants.OBTAIN_EMPLOYMENT_CONTENT_TYPE)
                {
                        name = "��ҵ���Ϸ�����";
                }
                else if (type == ContentManageConstants.COURSE_FOLDER_TYPE)
                {
                        name = "�γ���Ϣ";
                }
                else if (type == ContentManageConstants.SHIFANXIAO_TYPE)
                {
                        name = "ʾ��У��Դ";
                }
                return name;
        }

        public static String getServerURL(HttpServletRequest request)
        {
                return request.getScheme() + "://" + request.getServerName() + ":" +
                        request.getServerPort() + request.getContextPath();
        }

        /**
         * ͨ���ļ�����չ����ͼ���Ƿ����,���ڷ���true,�����ڷ���false.
         *
         * @param extIcoName �ļ�����չ��
         */
        public static boolean isValidateIMG(String extIcoName, ServletContext sc)
        {
                try
                {
                        if (CONTENT_EXT_ICO_MAPPINGS == null)
                        {
                                initCONTENT_EXT_ICO_MAPPINGS(sc);
                        }

                        if (extIcoName != null)
                        {
                                return CONTENT_EXT_ICO_MAPPINGS.containsKey(extIcoName.toLowerCase());
                        }
                        else
                        {
                                return false;
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();

                        return false;
                }
        }
}
