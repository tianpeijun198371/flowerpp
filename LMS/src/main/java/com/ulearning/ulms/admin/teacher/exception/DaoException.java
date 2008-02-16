package com.ulearning.ulms.admin.teacher.exception;


/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-16
 * Time: 13:14:45
 * To change this template use File | Settings | File Templates.
 */
public class DaoException extends Exception
{
        public DaoException()
        {
                super(); //To change body of overridden methods use File | Settings | File Templates.
        }

        public DaoException(Throwable cause)
        {
                super(cause); //To change body of overridden methods use File | Settings | File Templates.
        }

        public void printStackTrace()
        {
                super.printStackTrace(); //To change body of overridden methods use File | Settings | File Templates.
        }
}
