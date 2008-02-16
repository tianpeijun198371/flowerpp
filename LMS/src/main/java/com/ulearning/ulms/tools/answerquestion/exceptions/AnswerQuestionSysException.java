/**
 * NewDocumentSysException.java.
 * User: Administrator  Date: 2005-3-7
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class AnswerQuestionSysException extends ULMSSysException
{
        public AnswerQuestionSysException()
        {
        }

        public AnswerQuestionSysException(String msg)
        {
                super(msg);
        }

        public AnswerQuestionSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public AnswerQuestionSysException(Throwable nested)
        {
                super(nested);
        }
}
