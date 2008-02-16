/*
 * Created on 2004-10-2
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.exception;


/**
 * AuthorizationException occurs if login failed.
 *
 * @author Huaxia
 */
public class AuthorizationException extends RuntimeException
{
        public AuthorizationException()
        {
        }

        public AuthorizationException(String msg)
        {
                super(msg);
        }

        public AuthorizationException(Throwable throwable)
        {
                super(throwable);
        }
}
