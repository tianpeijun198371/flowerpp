package com.ulearning.ulms.content.schoolbook.bean;

public class StoreMessageBean
{
        //教材基本信息ID
        private long infoId;

        //出入库数量
        private Integer tcitemquantity;

        //教材名称
        private String bsifbookname;

        //单价
        private Float bsifprice;

        //合计金额  =(tcitemquantity * bsifprice);
        private Float tcitemtotal;

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("infoId=").append(getInfoId());
                sb.append("\r\n").append("tcitemquantity=").append(getTcitemquantity());
                sb.append("\r\n").append("bsifbookname=").append(getBsifbookname());
                sb.append("\r\n").append("bsifprice=").append(getBsifprice());
                sb.append("\r\n").append("tcitemtotal=").append(getTcitemtotal());

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

        public Float getBsifprice()
        {
                return bsifprice;
        }

        public void setBsifprice(Float bsifprice)
        {
                this.bsifprice = bsifprice;
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

        //scale为折扣比例，如教材是8折 scale就是 new Float(0.8),若scale为null时为不打折;
        public void setTcitemtotal(Float scale)
        {
                if ((tcitemquantity != null) && (bsifprice != null))
                {
                        if (null != scale)
                        {
                                this.tcitemtotal = new Float(tcitemquantity.intValue() * bsifprice.floatValue() * scale.floatValue());
                        }
                        else
                        {
                                this.tcitemtotal = new Float(tcitemquantity.intValue() * bsifprice.floatValue());
                        }
                }
                else
                {
                        this.tcitemtotal = new Float(0);
                }
        }

        public long getInfoId()
        {
                return infoId;
        }

        public void setInfoId(long infoId)
        {
                this.infoId = infoId;
        }
}
