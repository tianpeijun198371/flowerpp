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
         * 根据type的值返回对应答疑模块大类的名称。
         *
         * @param type
         * @return
         */
        public static String getTypeName(int type)
        {
                String name = "常见问题";

                if (type == AnswerQuestionConstants.COURSE_TYPE)
                {
                        name = "常见问题";
                }
                else if (type == AnswerQuestionConstants.ZHAOSHENG_TYPE)
                {
                        name = "问题解答";
                }

                return name;
        }
}
