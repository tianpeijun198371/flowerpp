package com.ulearning.ulms.content.schoolbook.bean;

import java.util.Date;


/**
 * @author suh
 *         用于入库单出库单页的视图显示类
 */
public class StoreItemView
{
        //明细ID
        private Integer tcitemid;

        //出入库主单管理ID
        private Integer tcmainid;

        //教材名称
        private String bsifbookname;

        //教材单格
        private Float tcitemprice;

        //出入库数量
        private Integer tcitemquantity;

        //合计金额
        private Float tcitemtotal;

        //经办人(入库)
        private String tcmaintran;

        //领用人（出库）
        private String tcmainuser;

        //出入库日期
        private Date tcmaindate;

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("tcitemid=").append(tcitemid);
                sb.append("\r\n").append("tcmainid=").append(tcmainid);
                sb.append("\r\n").append("bsifbookname=").append(bsifbookname);
                sb.append("\r\n").append("tcitemprice=").append(tcitemprice);
                sb.append("\r\n").append("tcitemquantity=").append(tcitemquantity);
                sb.append("\r\n").append("tcitemtotal=").append(tcitemtotal);
                sb.append("\r\n").append("tcmaintran=").append(tcmaintran);
                sb.append("\r\n").append("tcmainuser=").append(tcmainuser);
                sb.append("\r\n").append("tcmaindate=").append(tcmaindate);

                return sb.toString();
        }

        public String getBsifbookname()
        {
                return bsifbookname;
        }

        public void setBsifbookname(String bsifbookname)
        {
                this.bsifbookname = bsifbookname;
        }

        public Integer getTcitemid()
        {
                return tcitemid;
        }

        public void setTcitemid(Integer tcitemid)
        {
                this.tcitemid = tcitemid;
        }

        public Float getTcitemprice()
        {
                return tcitemprice;
        }

        public void setTcitemprice(Float tcitemprice)
        {
                this.tcitemprice = tcitemprice;
        }

        public Integer getTcitemquantity()
        {
                return tcitemquantity;
        }

        public void setTcitemquantity(Integer tcitemquantity)
        {
                this.tcitemquantity = tcitemquantity;
        }

        public Float getTcitemtotal()
        {
                return tcitemtotal;
        }

        public void setTcitemtotal(Float tcitemtotal)
        {
                this.tcitemtotal = tcitemtotal;
        }

        public Date getTcmaindate()
        {
                return tcmaindate;
        }

        public void setTcmaindate(Date tcmaindate)
        {
                this.tcmaindate = tcmaindate;
        }

        public Integer getTcmainid()
        {
                return tcmainid;
        }

        public void setTcmainid(Integer tcmainid)
        {
                this.tcmainid = tcmainid;
        }

        public String getTcmaintran()
        {
                return tcmaintran;
        }

        public void setTcmaintran(String tcmaintran)
        {
                this.tcmaintran = tcmaintran;
        }

        public String getTcmainuser()
        {
                return tcmainuser;
        }

        public void setTcmainuser(String tcmainuser)
        {
                this.tcmainuser = tcmainuser;
        }
}
