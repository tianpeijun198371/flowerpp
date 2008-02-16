package com.ulearning.ulms.tools.message.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-26
 * Time: 9:42:12
 * To change this template use File | Settings | File Templates.
 */
public class MessageUserDAOSysException extends ULMSSysException
{
        public MessageUserDAOSysException()
        {
        }

        public MessageUserDAOSysException(String msg)
        {
                super(msg);
        }
}
