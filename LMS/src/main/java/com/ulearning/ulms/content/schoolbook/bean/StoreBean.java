package com.ulearning.ulms.content.schoolbook.bean;

import java.util.Date;


/**
 * @author suh
 *         ���ڿ����ʾҳ
 */
public class StoreBean
{
        private Integer id;

        //�̲�����
        private String bsifbookname;

        //����������
        private String bsifpublishname;

        //����ʱ��
        private Date bsifpublishdate;

        //����
        private String bsifauthor;

        //��浥��
        private Float tcstprice;

        //�������
        private Integer tcstquantity;

        //�����
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
