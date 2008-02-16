package com.ulearning.ulms.content.schoolbook.bean;

import java.util.Date;


/**
 * @author suh
 *         用于库存显示页
 */
public class StoreBean
{
        private Integer id;

        //教材名称
        private String bsifbookname;

        //出版社名称
        private String bsifpublishname;

        //出版时间
        private Date bsifpublishdate;

        //作者
        private String bsifauthor;

        //库存单价
        private Float tcstprice;

        //库存数量
        private Integer tcstquantity;

        //库存金额
        private Float tcsttotal;

        public String getBsifauthor()
        {
                return bsifauthor;
        }

        public void setBsifauthor(String bsifauthor)
        {
                this.bsifauthor = bsifauthor;
        }

        public String getBsifbookname()
        {
                return bsifbookname;
        }

        public void setBsifbookname(String bsifbookname)
        {
                this.bsifbookname = bsifbookname;
        }

        public Date getBsifpublishdate()
        {
                return bsifpublishdate;
        }

        public void setBsifpublishdate(Date bsifpublishdate)
        {
                this.bsifpublishdate = bsifpublishdate;
        }

        public String getBsifpublishname()
        {
                return bsifpublishname;
        }

        public void setBsifpublishname(String bsifpublishname)
        {
                this.bsifpublishname = bsifpublishname;
        }

        public Float getTcstprice()
        {
                return tcstprice;
        }

        public void setTcstprice(Float tcstprice)
        {
                this.tcstprice = tcstprice;
        }

        public Integer getTcstquantity()
        {
                return tcstquantity;
        }

        public void setTcstquantity(Integer tcstquantity)
        {
                this.tcstquantity = tcstquantity;
        }

        public Float getTcsttotal()
        {
                return tcsttotal;
        }

        public void setTcsttotal(Float tcsttotal)
        {
                this.tcsttotal = tcsttotal;
        }

        public Integer getId()
        {
                return id;
        }

        public void setId(Integer id)
        {
                this.id = id;
        }
}
