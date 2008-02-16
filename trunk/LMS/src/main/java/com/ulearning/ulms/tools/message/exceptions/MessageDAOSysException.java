package com.ulearning.ulms.tools.message.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 16:21:28
 * To change this template use File | Settings | File Templates.
 */
public class MessageDAOSysException extends ULMSSysException
{
        public MessageDAOSysException()
        {
        }

        public MessageDAOSysException(String msg)
        {
                super(msg);
        }
}
