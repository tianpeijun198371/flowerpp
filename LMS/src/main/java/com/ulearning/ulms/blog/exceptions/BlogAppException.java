package com.ulearning.ulms.blog.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-4-8
 */
public class BlogAppException extends ULMSAppException
{
        public BlogAppException()
        {
                super();
        }

        public BlogAppException(String msg)
        {
                super(msg);
        }

        public BlogAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public BlogAppException(Throwable nested)
        {
                super(nested);
        }
}
