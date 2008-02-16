/*
 * Created on 2004-9-29
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.exception;


/**
 * QueryException is thrown when a query failed: not found, access forbidden, database error, etc.
 *
 * @author Huaxia
 */
public class QueryException extends RuntimeException
{
        public QueryException()
        {
        }

        public QueryException(String msg)
        {
                super(msg);
        }

        public QueryException(Throwable throwable)
        {
                super(throwable);
        }
}
