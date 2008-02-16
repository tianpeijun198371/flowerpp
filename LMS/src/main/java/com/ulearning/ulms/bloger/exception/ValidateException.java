/*
 * Created on 2004-10-1
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.exception;


/**
 * ValidateException is thrown when validate failed.
 *
 * @author Huaxia
 */
public class ValidateException extends RuntimeException
{
        public ValidateException()
        {
        }

        public ValidateException(String msg)
        {
                super(msg);
        }

        public ValidateException(int msgId)
        {
                super();
        }
}
