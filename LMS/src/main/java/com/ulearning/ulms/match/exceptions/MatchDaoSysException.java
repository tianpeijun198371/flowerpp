/**
 * MatchDaoSysException.java.
 * User: zhangy Date: 2005-6-3 9:18:09
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class MatchDaoSysException extends ULMSSysException
{
        public MatchDaoSysException()
        {
        }

        public MatchDaoSysException(String msg)
        {
                super(msg);
        }

        public MatchDaoSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public MatchDaoSysException(Throwable nested)
        {
                super(nested);
        }
}
