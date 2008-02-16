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
        //资源管理图标所在位置
        private static String ICO_PHYSICIAL_PATH = "content/images/icon/filetype/";

        //资源文件扩展名对应的图标是否存在所谓Hash表
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
                        "[ContentManageUtil]initCONTENT_EXT_ICO_MAPPINGS--------原始路径 = " +
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
         * 根据type的值返回对应资源大类的名称。
         *
         * @param type
         * @return
         */
        public static String getTypeName(int type)
        {
                String name = "公共资源";

                if (type == ContentManageConstants.PUBLIC_CONTENT_TYPE)
                {
                        name = "公共资源";
                }
                else if (type == ContentManageConstants.PERSONAL_CONTENT_TYPE)
                {
                        name = "个人资源";
                }
                else if (type == ContentManageConstants.COURSE_CONTENT_TYPE)
                {
                        name = "课程资源";
                }
                else if (type == ContentManageConstants.PAPER_CONTENT_TYPE)
                {
                        name = "论文资源";
                }
                else if (type == ContentManageConstants.OBTAIN_EMPLOYMENT_CONTENT_TYPE)
                {
                        name = "就业资料发布区";
                }
                else if (type == ContentManageConstants.COURSE_FOLDER_TYPE)
                {
                        name = "课程信息";
                }
                else if (type == ContentManageConstants.SHIFANXIAO_TYPE)
                {
                        name = "示范校资源";
                }
                return name;
        }

        public static String getServerURL(HttpServletRequest request)
        {
                return request.getScheme() + "://" + request.getServerName() + ":" +
                        request.getServerPort() + request.getContextPath();
        }

        /**
         * 通过文件的扩展名的图标是否存在,存在返回true,不存在返回false.
         *
         * @param extIcoName 文件的扩展名
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
