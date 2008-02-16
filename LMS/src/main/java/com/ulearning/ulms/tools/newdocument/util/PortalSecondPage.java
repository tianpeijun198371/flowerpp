/**
 * PortalSecondPage.java.
 * User: shid Date: 2005-11-15 14:42:53
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.util;

public class PortalSecondPage
{
        /**
         * 得到导航
         *
         * @param type
         * @return
         */
        public static String getNavTitle(int parentType, int type)
        {
                String nav = null;

                if (parentType == type)
                {
                        nav = oneNav(NewDocumentUtil.getTitle(type));
                }
                else
                {
                        nav = twoNav(NewDocumentUtil.getTitle(parentType), NewDocumentUtil.getTitle(type), parentType, type);
                }

                return nav;
        }

        //一级栏目导航
        private static String oneNav(String s)
        {
                return "<span class=\"daohang_t2\">" + s + "</span>";
        }

        //二级栏目导航
        private static String twoNav(String s1, String s2, int parentType, int type)
        {
                return "<a href=\"secondWelcome.jsp?parentType=" + parentType + "&type=" + parentType + "\"><span class=\"daohang_t2\">" + s1 + "</span></a>&nbsp;>>&nbsp;<span class=\"daohang_t2\">" + s2 + "</span>";
        }
}
