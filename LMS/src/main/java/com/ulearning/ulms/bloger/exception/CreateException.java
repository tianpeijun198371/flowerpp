/*
 * Created on 2004-9-29
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.exception;


/**
 * CreateException is thrown when an error occur during create
 * a new account, article, image, etc.
 *
 * @author Huaxia
 */
public class CreateException extends RuntimeException
{
        public CreateException()
        {
        }

        public CreateException(String msg)
        {
                super(msg);
        }

        public CreateException(Throwable throwable)
        {
                super(throwable);
        }
}
