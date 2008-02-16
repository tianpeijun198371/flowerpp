package com.ulearning.ulms.blog.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-4-8
 */
public class BlogSysException extends ULMSSysException
{
        public BlogSysException()
        {
                super();
        }

        public BlogSysException(String msg)
        {
                super(msg);
        }

        public BlogSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public BlogSysException(Throwable nested)
        {
                super(nested);
        }
}
