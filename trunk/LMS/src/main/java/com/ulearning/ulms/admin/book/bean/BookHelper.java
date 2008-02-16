/** * BookHelper.java.
 * Book: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.book.bean;

import com.ulearning.ulms.admin.book.dao.BookDAO;
import com.ulearning.ulms.admin.book.dao.BookDAOFactory;
import com.ulearning.ulms.admin.book.exceptions.BookDAOSysException;
import com.ulearning.ulms.admin.book.form.BookForm;

import java.util.List;


public class BookHelper
{
        /**
         * Wrapping the get book method for JSP and  the other modules
         *
         * @param bookID
         * @return the book modle according to the bookID
         */
        public BookForm getBook(int bookID)
        {
                BookForm bf = null;

                try
                {
                        BookDAO bookDao = BookDAOFactory.getDAO();
                        bf = bookDao.getBook(bookID);
                }
                catch (BookDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get bookList method for JSP and  the other modules
         *
         * @param type relationID
         * @return the book list according to the catalog
         */
        public List getBookList(int type, int relationID)
        {
                List bookList = null;

                try
                {
                        BookDAO bookDao = BookDAOFactory.getDAO();
                        bookList = bookDao.getBookList(type, relationID);
                }
                catch (BookDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bookList;
        }
}
