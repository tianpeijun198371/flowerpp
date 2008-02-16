/*
 * Created on 2004-9-29
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.exception;


/**
 * TODO Description here...
 *
 * @author Huaxia
 */
public class UpdateException extends RuntimeException
{
        public UpdateException()
        {
        }

        public UpdateException(String msg)
        {
                super(msg);
        }

        public UpdateException(Throwable throwable)
        {
                super(throwable);
        }
}
