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
public class DeleteException extends RuntimeException
{
        public DeleteException()
        {
        }

        public DeleteException(String msg)
        {
                super(msg);
        }

        public DeleteException(Throwable throwable)
        {
                super(throwable);
        }
}
