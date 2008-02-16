/**
 * PaperUtil.java.
 * User: yangds  Date: 2005-8-31
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.testbase;

public class PaperUtil
{
        /**
         * 根据试卷的类型返回相应的名称
         *
         * @param papertype
         * @return
         */
        public static String getTypeName(String papertype)
        {
                String name = "";

                if (papertype == null)
                {
                        return "";
                }

                if (papertype.equals(""))
                {
                        return "";
                }

                if (papertype.equals("1"))
                {
                        name = "练习";
                }

                if (papertype.equals("2"))
                {
                        name = "自我测试";
                }

                if (papertype.equals("3"))
                {
                        name = "在线考试";
                }

                return name;
        }
}
