package com.ulearning.ulms.admin.teacher.exception;


/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-16
 * Time: 13:13:13
 * To change this template use File | Settings | File Templates.
 */
public class ActionException extends Exception
{
        public ActionException(String message)
        {
                super(message); //To change body of overridden methods use File | Settings | File Templates.
        }

        public ActionException()
        {
                super(); //To change body of overridden methods use File | Settings | File Templates.
        }

        public void printStackTrace()
        {
        }
}
