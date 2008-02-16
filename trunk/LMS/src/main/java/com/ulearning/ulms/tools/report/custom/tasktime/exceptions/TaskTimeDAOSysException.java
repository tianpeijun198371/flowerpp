package com.ulearning.ulms.tools.report.custom.tasktime.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:03:38
 * To change this template use File | Settings | File Templates.
 */
public class TaskTimeDAOSysException extends ULMSSysException
{
        public TaskTimeDAOSysException()
        {
        }

        public TaskTimeDAOSysException(String msg)
        {
                super(msg);
        }
}
