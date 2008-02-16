/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


/**
 * Class description goes here.
 * <p/>
 * 定义系统异常
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class ExambatchDAOSysException extends ULMSSysException
{
        public ExambatchDAOSysException()
        {
                super();
        }

        public ExambatchDAOSysException(String msg)
        {
                super(msg);
        }
}
