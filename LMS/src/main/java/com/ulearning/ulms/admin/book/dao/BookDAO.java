/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.dao;

import com.ulearning.ulms.admin.book.exceptions.BookDAOSysException;
import com.ulearning.ulms.admin.book.form.BookForm;

import java.io.Serializable;

import java.util.List;


public interface BookDAO
{
        public Serializable addBook(BookForm details) throws BookDAOSysException;

        public void updateBook(BookForm details) throws BookDAOSysException;

        public void removeBook(String bookID) throws BookDAOSysException;

        public BookForm getBook(int bookID) throws BookDAOSysException;

        public List getBookList(int type, int relationID)
                throws BookDAOSysException;
}
