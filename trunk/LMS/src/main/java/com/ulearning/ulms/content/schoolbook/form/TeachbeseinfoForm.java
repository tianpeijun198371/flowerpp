package com.ulearning.ulms.content.schoolbook.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

//import java.sql.Timestamp;
import java.sql.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060417
 * Time: 111620
 */
public class TeachbeseinfoForm extends ActionForm
{
        private Integer bsifid;
        private String isbn = "";
        private String bsifbookname = "";
        private Integer bsifsortid;
        private String bdifsortname = "";
        private Integer bsifpublishid;
        private String bsifpublishname = "";
        private Date bsifpublishdate;
        private String bsifauthor = "";
        private String bsifeditor = "";
        private String bsifholdtrue = "";
        private Integer bsifimpression;
        private Float bsifword;
        private Date bsifappenddate;
        private String bsifappenddept = "";
        private Float bsifprice;
        private Float bsifmailprice;
        private String bsifintr = "";

        //教材封面图片存放地址
        private String bsifcover = "";

        //图片对象流文件
        private FormFile bsifFile;
        private String bsifcatalong = "";
        private String bsifmemo = "";
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";
        private String remark5 = "";

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("bsifid=").append(getBsifid());
                sb.append("\r\n").append("isbn=").append(getIsbn());
                sb.append("\r\n").append("bsifbookname=").append(getBsifbookname());
                sb.append("\r\n").append("bsifsortid=").append(getBsifsortid());
                sb.append("\r\n").append("bsifpublishid=").append(getBsifpublishid());
                sb.append("\r\n").append("bsifpublishname=").append(getBsifpublishname());
                sb.append("\r\n").append("bsifpublishdate=").append(getBsifpublishdate());
                sb.append("\r\n").append("bsifauthor=").append(getBsifauthor());
                sb.append("\r\n").append("bsifeditor=").append(getBsifeditor());
                sb.append("\r\n").append("bsifholdtrue=").append(getBsifholdtrue());
                sb.append("\r\n").append("bsifimpression=").append(getBsifimpression());
                sb.append("\r\n").append("bsifword=").append(getBsifword());
                sb.append("\r\n").append("bsifappenddate=").append(getBsifappenddate());
                sb.append("\r\n").append("bsifappenddept=").append(getBsifappenddept());
                sb.append("\r\n").append("bsifprice=").append(getBsifprice());
                sb.append("\r\n").append("bsifmailprice=").append(getBsifmailprice());
                sb.append("\r\n").append("bsifintr=").append(getBsifintr());
                sb.append("\r\n").append("bsifcover=").append(getBsifcover());
                sb.append("\r\n").append("bsifFile=").append(getBsifFile());
                sb.append("\r\n").append("bsifcatalong=").append(getBsifcatalong());
                sb.append("\r\n").append("bsifmemo=").append(getBsifmemo());
                sb.append("\r\n").append("remark1=").append(getRemark1());
                sb.append("\r\n").append("remark2=").append(getRemark2());
                sb.append("\r\n").append("remark3=").append(getRemark3());
                sb.append("\r\n").append("remark4=").append(getRemark4());
                sb.append("\r\n").append("remark5=").append(getRemark5());

                return sb.toString();
        }

        public Integer getBsifid()
        {
                return bsifid;
        }

        public void setBsifid(Integer bsifid)
        {
                this.bsifid = bsifid;
        }

        public String getIsbn()
        {
                return isbn;
        }

        public void setIsbn(String isbn)
        {
                this.isbn = isbn;
        }

        public String getBsifbookname()
        {
                return bsifbookname;
        }

        public void setBsifbookname(String bsifbookname)
        {
                this.bsifbookname = bsifbookname;
        }

        public Integer getBsifsortid()
        {
                return bsifsortid;
        }

        public void setBsifsortid(Integer bsifsortid)
        {
                this.bsifsortid = bsifsortid;
        }

        public String getBdifsortname()
        {
                return bdifsortname;
        }

        public void setBdifsortname(String bdifsortname)
        {
                this.bdifsortname = bdifsortname;
        }

        public Integer getBsifpublishid()
        {
                return bsifpublishid;
        }

        public void setBsifpublishid(Integer bsifpublishid)
        {
                this.bsifpublishid = bsifpublishid;
        }

        public String getBsifpublishname()
        {
                return bsifpublishname;
        }

        public void setBsifpublishname(String bsifpublishname)
        {
                this.bsifpublishname = bsifpublishname;
        }

        public Date getBsifpublishdate()
        {
                return bsifpublishdate;
        }

        public void setBsifpublishdate(Date bsifpublishdate)
        {
                this.bsifpublishdate = bsifpublishdate;
        }

        public String getBsifauthor()
        {
                return bsifauthor;
        }

        public void setBsifauthor(String bsifauthor)
        {
                this.bsifauthor = bsifauthor;
        }

        public String getBsifeditor()
        {
                return bsifeditor;
        }

        public void setBsifeditor(String bsifeditor)
        {
                this.bsifeditor = bsifeditor;
        }

        public String getBsifholdtrue()
        {
                return bsifholdtrue;
        }

        public void setBsifholdtrue(String bsifholdtrue)
        {
                this.bsifholdtrue = bsifholdtrue;
        }

        public Integer getBsifimpression()
        {
                return bsifimpression;
        }

        public void setBsifimpression(Integer bsifimpression)
        {
                this.bsifimpression = bsifimpression;
        }

        public Float getBsifword()
        {
                return bsifword;
        }

        public void setBsifword(Float bsifword)
        {
                this.bsifword = bsifword;
        }

        public Date getBsifappenddate()
        {
                return bsifappenddate;
        }

        public void setBsifappenddate(Date bsifappenddate)
        {
                this.bsifappenddate = bsifappenddate;
        }

        public String getBsifappenddept()
        {
                return bsifappenddept;
        }

        public void setBsifappenddept(String bsifappenddept)
        {
                this.bsifappenddept = bsifappenddept;
        }

        public Float getBsifprice()
        {
                return bsifprice;
        }

        public void setBsifprice(Float bsifprice)
        {
                this.bsifprice = bsifprice;
        }

        public Float getBsifmailprice()
        {
                return bsifmailprice;
        }

        public void setBsifmailprice(Float bsifmailprice)
        {
                this.bsifmailprice = bsifmailprice;
        }

        public String getBsifintr()
        {
                return bsifintr;
        }

        public void setBsifintr(String bsifintr)
        {
                this.bsifintr = bsifintr;
        }

        public String getBsifcover()
        {
                return bsifcover;
        }

        public void setBsifcover(String bsifcover)
        {
                this.bsifcover = bsifcover;
        }

        public String getBsifcatalong()
        {
                return bsifcatalong;
        }

        public void setBsifcatalong(String bsifcatalong)
        {
                this.bsifcatalong = bsifcatalong;
        }

        public String getBsifmemo()
        {
                return bsifmemo;
        }

        public void setBsifmemo(String bsifmemo)
        {
                this.bsifmemo = bsifmemo;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public FormFile getBsifFile()
        {
                return bsifFile;
        }

        public void setBsifFile(FormFile bsifFile)
        {
                this.bsifFile = bsifFile;
        }
}
