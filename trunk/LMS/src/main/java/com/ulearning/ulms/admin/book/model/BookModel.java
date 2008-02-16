package com.ulearning.ulms.admin.book.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class BookModel implements Serializable
{
        /**
         * identifier field
         */
        private int bookID;

        /**
         * persistent field
         */
        private String title;
        private int orgID;

        /**
         * persistent field
         */
        private String serial;

        /**
         * persistent field
         */
        private int stock;

        /**
         * persistent field
         */
        private double price;

        /**
         * persistent field
         */
        private double originPrice;

        /**
         * persistent field
         */
        private String author;

        /**
         * persistent field
         */
        private String publishCompany;

        /**
         * nullable persistent field
         */
        private int minStock;

        /**
         * nullable persistent field
         */
        private int type;

        /**
         * nullable persistent field
         */
        private int relationID;

        /**
         * nullable persistent field
         */
        private String edition;

        /**
         * nullable persistent field
         */
        private String printing;

        /**
         * nullable persistent field
         */
        private String reference;

        /**
         * nullable persistent field
         */
        private Date publishDate;

        /**
         * nullable persistent field
         */
        private Date createDate;

        /**
         * nullable persistent field
         */
        private String keyword;

        /**
         * nullable persistent field
         */
        private String summary;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * full constructor
         */
        public BookModel(String title, String serial, int stock, double price,
                         double originPrice, String author, String publishCompany, int minStock,
                         int type, int relationID, String edition, String printing,
                         String reference, Date publishDate, Date createDate, String keyword,
                         String summary, String remark, int orgID)
        {
                this.title = title;
                this.orgID = orgID;
                this.serial = serial;
                this.stock = stock;
                this.price = price;
                this.originPrice = originPrice;
                this.author = author;
                this.publishCompany = publishCompany;
                this.minStock = minStock;
                this.type = type;
                this.relationID = relationID;
                this.edition = edition;
                this.printing = printing;
                this.reference = reference;
                this.publishDate = publishDate;
                this.createDate = createDate;
                this.keyword = keyword;
                this.summary = summary;
                this.remark = remark;
        }

        /**
         * default constructor
         */
        public BookModel()
        {
        }

        /**
         * minimal constructor
         */
        public BookModel(String title, String serial, int stock, double price,
                         double originPrice, String author, String publishCompany, int orgID)
        {
                this.title = title;
                this.serial = serial;
                this.stock = stock;
                this.price = price;
                this.originPrice = originPrice;
                this.author = author;
                this.publishCompany = publishCompany;
                this.orgID = orgID;
        }

        public int getBookID()
        {
                return this.bookID;
        }

        public void setBookID(int bookID)
        {
                this.bookID = bookID;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getSerial()
        {
                return this.serial;
        }

        public void setSerial(String serial)
        {
                this.serial = serial;
        }

        public int getStock()
        {
                return this.stock;
        }

        public void setStock(int stock)
        {
                this.stock = stock;
        }

        public double getPrice()
        {
                return this.price;
        }

        public void setPrice(double price)
        {
                this.price = price;
        }

        public double getOriginPrice()
        {
                return this.originPrice;
        }

        public void setOriginPrice(double originPrice)
        {
                this.originPrice = originPrice;
        }

        public String getAuthor()
        {
                return this.author;
        }

        public void setAuthor(String author)
        {
                this.author = author;
        }

        public String getPublishCompany()
        {
                return this.publishCompany;
        }

        public void setPublishCompany(String publishCompany)
        {
                this.publishCompany = publishCompany;
        }

        public int getMinStock()
        {
                return this.minStock;
        }

        public void setMinStock(int minStock)
        {
                this.minStock = minStock;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getEdition()
        {
                return this.edition;
        }

        public void setEdition(String edition)
        {
                this.edition = edition;
        }

        public String getPrinting()
        {
                return this.printing;
        }

        public void setPrinting(String printing)
        {
                this.printing = printing;
        }

        public String getReference()
        {
                return this.reference;
        }

        public void setReference(String reference)
        {
                this.reference = reference;
        }

        public Date getPublishDate()
        {
                return this.publishDate;
        }

        public void setPublishDate(Date publishDate)
        {
                this.publishDate = publishDate;
        }

        public Date getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public String getKeyword()
        {
                return this.keyword;
        }

        public void setKeyword(String keyword)
        {
                this.keyword = keyword;
        }

        public String getSummary()
        {
                return this.summary;
        }

        public void setSummary(String summary)
        {
                this.summary = summary;
        }

        public String getRemark()
        {
                return this.remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public int getOrgID()
        {
                return this.orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("bookID", getBookID()).toString();
        }
}
