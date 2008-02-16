package com.ulearning.ulms.tools.message.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 16:21:41
 * To change this template use File | Settings | File Templates.
 */
public class MessageDAOAppException extends ULMSAppException
{
        public MessageDAOAppException()
        {
        }

        public MessageDAOAppException(String msg)
        {
                super(msg);
        }
}
