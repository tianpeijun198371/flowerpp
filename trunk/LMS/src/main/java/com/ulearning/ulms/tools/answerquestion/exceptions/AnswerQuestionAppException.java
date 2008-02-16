/**
 * NewDocumentAppException.java.
 * User: Administrator  Date: 2005-3-7
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class AnswerQuestionAppException extends ULMSAppException
{
        public AnswerQuestionAppException()
        {
        }

        public AnswerQuestionAppException(Throwable nested)
        {
                super(nested);
        }

        public AnswerQuestionAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public AnswerQuestionAppException(String msg)
        {
                super(msg);
        }
}
