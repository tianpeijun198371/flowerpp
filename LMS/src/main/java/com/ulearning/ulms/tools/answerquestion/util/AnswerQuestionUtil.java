/**
 * AnswerQuestionUtil.java.
 * User: fengch Date: 2005-7-11 9:53:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.util;

public class AnswerQuestionUtil
{
        /**
         * ����type��ֵ���ض�Ӧ����ģ���������ơ�
         *
         * @param type
         * @return
         */
        public static String getTypeName(int type)
        {
                String name = "��������";

                if (type == AnswerQuestionConstants.COURSE_TYPE)
                {
                        name = "��������";
                }
                else if (type == AnswerQuestionConstants.ZHAOSHENG_TYPE)
                {
                        name = "������";
                }

                return name;
        }
}
