package com.ulearning.ulms.content.schoolbook.bean;

public class StoreMessageBean
{
        //�̲Ļ�����ϢID
        private long infoId;

        //���������
        private Integer tcitemquantity;

        //�̲�����
        private String bsifbookname;

        //����
        private Float bsifprice;

        //�ϼƽ��  =(tcitemquantity * bsifprice);
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

        //scaleΪ�ۿ۱�������̲���8�� scale���� new Float(0.8),��scaleΪnullʱΪ������;
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
