/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.form;

import com.ulearning.ulms.admin.book.model.BookModel;
import com.ulearning.ulms.core.util.StringUtil;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class BookForm extends ActionForm
{
        private int bookID = 0;
        private int orgID = 0;
        private String serial = null;
        private int stock = 0;
        private double price = 0;
        private double originPrice = 0;
        private String title = null;
        private String author = null;
        private String publishCompany = null;
        private int minStock = 0;
        private int type = 0;
        private int relationID = 0;
        private String edition = null;
        private String printing = null;
        private String reference = null;
        private Date publishDate = null;
        private String publishDateStr = null;
        private Date createDate = null;
        private String keyword = null;
        private String summary = null;
        private String remark = null;

        public BookModel getBookModel()
        {
                BookModel bookModel = new BookModel();
                bookModel.setBookID(this.bookID);
                bookModel.setOrgID(this.orgID);
                bookModel.setSerial(this.serial);
                bookModel.setStock(this.stock);
                bookModel.setPrice(this.price);
                bookModel.setOriginPrice(this.originPrice);
                bookModel.setTitle(this.title);
                bookModel.setAuthor(this.author);
                bookModel.setPublishCompany(this.publishCompany);
                bookModel.setMinStock(this.minStock);
                bookModel.setType(this.type);
                bookModel.setRelationID(this.relationID);
                bookModel.setEdition(this.edition);
                bookModel.setPrinting(this.printing);
                bookModel.setReference(this.reference);
                bookModel.setPublishDate(this.publishDate);
                bookModel.setCreateDate(this.createDate);
                bookModel.setKeyword(this.keyword);
                bookModel.setSummary(this.summary);
                bookModel.setRemark(this.remark);

                return bookModel;
        }

        public BookForm getBookForm(BookModel bookModel)
        {
                BookForm bookForm = new BookForm();
                bookForm.setBookID(bookModel.getBookID());
                bookForm.setTitle(bookModel.getTitle());
                bookForm.setOrgID(bookModel.getOrgID());
                bookForm.setSerial(bookModel.getSerial());
                bookForm.setStock(bookModel.getStock());
                bookForm.setPrice(bookModel.getPrice());
                bookForm.setOriginPrice(bookModel.getOriginPrice());
                bookForm.setAuthor(StringUtil.nullToStr(bookModel.getAuthor()));
                bookForm.setPublishCompany(StringUtil.nullToStr(
                        bookModel.getPublishCompany()));
                bookForm.setMinStock(bookModel.getMinStock());
                bookForm.setType(bookModel.getType());
                bookForm.setRelationID(bookModel.getRelationID());
                bookForm.setEdition(StringUtil.nullToStr(bookModel.getEdition()));
                bookForm.setPrinting(bookModel.getPrinting());
                bookForm.setReference(StringUtil.nullToStr(bookModel.getReference()));
                bookForm.setPublishDate(bookModel.getPublishDate());
                bookForm.setCreateDate(bookModel.getCreateDate());
                bookForm.setKeyword(StringUtil.nullToStr(bookModel.getKeyword()));
                bookForm.setSummary(StringUtil.nullToStr(bookModel.getSummary()));
                bookForm.setRemark(StringUtil.nullToStr(bookModel.getRemark()));

                return bookForm;
        }

        public int getBookID()
        {
                return bookID;
        }

        public void setBookID(int bookID)
        {
                this.bookID = bookID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getSerial()
        {
                return serial;
        }

        public void setSerial(String serial)
        {
                this.serial = serial;
        }

        public int getStock()
        {
                return stock;
        }

        public void setStock(int stock)
        {
                this.stock = stock;
        }

        public double getPrice()
        {
                return price;
        }

        public void setPrice(double price)
        {
                this.price = price;
        }

        public double getOriginPrice()
        {
                return originPrice;
        }

        public void setOriginPrice(double originPrice)
        {
                this.originPrice = originPrice;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getAuthor()
        {
                return author;
        }

        public void setAuthor(String author)
        {
                this.author = author;
        }

        public String getPublishCompany()
        {
                return publishCompany;
        }

        public void setPublishCompany(String publishCompany)
        {
                this.publishCompany = publishCompany;
        }

        public int getMinStock()
        {
                return minStock;
        }

        public void setMinStock(int minStock)
        {
                this.minStock = minStock;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getEdition()
        {
                return edition;
        }

        public void setEdition(String edition)
        {
                this.edition = edition;
        }

        public String getPrinting()
        {
                return printing;
        }

        public void setPrinting(String printing)
        {
                this.printing = printing;
        }

        public String getReference()
        {
                return reference;
        }

        public void setReference(String reference)
        {
                this.reference = reference;
        }

        public Date getPublishDate()
        {
                return publishDate;
        }

        public void setPublishDate(Date publishDate)
        {
                this.publishDate = publishDate;
        }

        public String getPublishDateStr()
        {
                return publishDateStr;
        }

        public void setPublishDateStr(String publishDateStr)
        {
                this.publishDateStr = publishDateStr;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public String getKeyword()
        {
                return keyword;
        }

        public void setKeyword(String keyword)
        {
                this.keyword = keyword;
        }

        public String getSummary()
        {
                return summary;
        }

        public void setSummary(String summary)
        {
                this.summary = summary;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }
}
