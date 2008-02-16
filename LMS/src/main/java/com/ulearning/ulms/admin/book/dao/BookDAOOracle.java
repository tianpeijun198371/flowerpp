/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.dao;

import com.ulearning.ulms.admin.book.exceptions.BookDAOSysException;
import com.ulearning.ulms.admin.book.form.BookForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.*;


public class BookDAOOracle extends BookDAOImpl
{
        /**
         * Insert a new book record to database
         * @param bookForm   the value object to be added
         * @throws BookDAOSysException
         */

        /**
         * Update bookInfo by the new Form
         * @param bookForm   value object for changed
         * @throws BookDAOSysException
         */

        /*
          public void updateBook(BookForm bookForm) throws BookDAOSysException
          {
              Statement stmt = null;
              String publishDate = "to_date('" + bookForm.getPublishDate() + "','yyyy-mm-dd')";
              String sqlStr = "update TM_Book_Tab set " +
                      "Title = '" + bookForm.getTitle() + "', " +
                      "OrgID = " + bookForm.getOrgID() + ", " +
                      "Serial = '" + bookForm.getSerial() + "', " +
                      "Stock = '" + bookForm.getStock() + "', " +
                      "Price = " + bookForm.getPrice() + ", " +
                      "OriginPrice = " + bookForm.getOriginPrice() + ", " +
                      "Author = '" + bookForm.getAuthor() + "', " +
                      "PublishCompany = '" + bookForm.getPublishCompany() + "', " +
                      "MinStock = " + bookForm.getMinStock() + ", " +
                      "Type = " + bookForm.getType() + ", " +
                      "RelationID = " + bookForm.getRelationID() + ", " +
                      "Edition = '" + bookForm.getEdition() + "', " +
                      "Printing = '" + bookForm.getPrinting() + "', " +
                      "Reference = '" + bookForm.getReference() + "', " +
                      "PublishDate = " + publishDate + ", " +
                      "Keyword = '" + bookForm.getKeyword() + "', " +
                      "Summary = '" + bookForm.getSummary() + "', " +
                      "Remark = '" + bookForm.getRemark() + "' " +
                      "where BookID = " + bookForm.getBookID();

        */
}
