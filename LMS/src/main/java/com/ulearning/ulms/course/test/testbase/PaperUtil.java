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
         * �����Ծ�����ͷ�����Ӧ������
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
                        name = "��ϰ";
                }

                if (papertype.equals("2"))
                {
                        name = "���Ҳ���";
                }

                if (papertype.equals("3"))
                {
                        name = "���߿���";
                }

                return name;
        }
}
