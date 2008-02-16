package com.ulearning.ulms.tools.report.custom.task.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 14:31:18
 * To change this template use File | Settings | File Templates.
 */
public class TaskDAOSysException extends ULMSSysException
{
        public TaskDAOSysException()
        {
        }

        public TaskDAOSysException(String msg)
        {
                super(msg);
        }
}
