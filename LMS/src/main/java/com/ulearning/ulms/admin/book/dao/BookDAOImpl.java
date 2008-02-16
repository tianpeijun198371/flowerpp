/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.dao;

import com.ulearning.ulms.admin.book.exceptions.BookDAOSysException;
import com.ulearning.ulms.admin.book.form.BookForm;
import com.ulearning.ulms.admin.book.model.BookModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.Serializable;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class BookDAOImpl implements BookDAO
{
        public Serializable addBook(BookForm details) throws BookDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getBookModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BookDAOSysException("" + e);
                }

                return s;
        }

        public void updateBook(BookForm details) throws BookDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getBookModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BookDAOSysException("" + e);
                }
        }

        /**
         * Remove the book from database by the bookID
         *
         * @param bookID
         * @throws BookDAOSysException
         */
        public void removeBook(String bookID) throws BookDAOSysException
        {
                String hql = " from BookModel where bookID = " + bookID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BookDAOSysException("" + e);
                }
        }

        /**
         * Get the book info via the unique bookID
         *
         * @param bookID
         * @return the prepared bookForm, default is null
         * @throws BookDAOSysException
         */
        public BookForm getBook(int bookID) throws BookDAOSysException
        {
                BookForm bf = new BookForm();
                BookForm res = null;
                List tmList = null;
                String hql = " from BookModel where bookID = " + bookID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BookDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        BookModel bm = (BookModel) tmList.get(0);
                        res = bf.getBookForm(bm);
                }

                return res;
        }

        /**
         * Get the book list by the catalogID
         *
         * @param type relationID
         * @return The prepared arraylist object,default size is 0
         * @throws BookDAOSysException
         */
        public List getBookList(int type, int relationID)
                throws BookDAOSysException
        {
                BookForm bf = new BookForm();
                BookModel bm = null;
                ArrayList bookList = new ArrayList();
                List tmList = null;
                String hql = " from BookModel where type = " + type +
                        " and relationID=" + relationID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BookDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (BookModel) tmList.get(i);
                        bookList.add(bf.getBookForm(bm));
                }

                return bookList;
        }

        /**
         * Convert the resultSet object to bookForm
         * @param rs  the resultSet which needs to convert
         * @return the wanted bookForm
         */

        /*
          private BookForm convertRs2Form(ResultSet rs)
          {
              BookForm bf = new BookForm();
              int rsIndex = 1;
              try
              {
                      bf.setBookID(rs.getInt(rsIndex++));
                      bf.setTitle(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setOrgID(rs.getInt(rsIndex++));
                      bf.setSerial(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setStock(rs.getInt(rsIndex++));
                      bf.setPrice(rs.getDouble(rsIndex++));
                      bf.setOriginPrice(rs.getDouble(rsIndex++));
                      bf.setAuthor(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setPublishCompany(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setMinStock(rs.getInt(rsIndex++));
                      bf.setType(rs.getInt(rsIndex++));
                      bf.setRelationID(rs.getInt(rsIndex++));
                      bf.setEdition(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setPrinting(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setReference(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setPublishDate(rs.getDate(rsIndex++));
                      bf.setCreateDate(rs.getDate(rsIndex++));
                      bf.setKeyword(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setSummary(rs.getString(rsIndex++));
              } catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return bf;
          }
              public static void main(String[] args)
              {
              }
        */
}
