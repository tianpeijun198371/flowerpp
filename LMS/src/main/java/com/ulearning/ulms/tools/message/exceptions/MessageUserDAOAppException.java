package com.ulearning.ulms.tools.message.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-26
 * Time: 9:41:52
 * To change this template use File | Settings | File Templates.
 */
public class MessageUserDAOAppException extends ULMSAppException
{
        public MessageUserDAOAppException()
        {
        }

        public MessageUserDAOAppException(String msg)
        {
                super(msg);
        }
}
