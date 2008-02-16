package com.ulearning.ulms.content.schoolbook.bean;

public class OutStoreItemUnit
{
        //教材基本信息
        private Integer infoId;

        //出库数量
        private Integer tcitemquantity;

        //客户ID
        private Integer tcitemtclientid;

        //对应入库管理主单ID
        private Integer mainstoreId;

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("infoId=").append(getInfoId());
                sb.append("\r\n").append("tcitemquantity=").append(getTcitemquantity());
                sb.append("\r\n").append("tcitemtclientid=").append(getTcitemtclientid());
                sb.append("\r\n").append("mainstoreId=").append(getMainstoreId());

                return sb.toString();
        }

        public Integer getInfoId()
        {
                return infoId;
        }

        public void setInfoId(Integer infoId)
        {
                this.infoId = infoId;
        }

        public Integer getMainstoreId()
        {
                return mainstoreId;
        }

        public void setMainstoreId(Integer mainstoreId)
        {
                this.mainstoreId = mainstoreId;
        }

        public Integer getTcitemquantity()
        {
                return tcitemquantity;
        }

        public void setTcitemquantity(Integer tcitemquantity)
        {
                this.tcitemquantity = tcitemquantity;
        }

        public Integer getTcitemtclientid()
        {
                return tcitemtclientid;
        }

        public void setTcitemtclientid(Integer tcitemtclientid)
        {
                this.tcitemtclientid = tcitemtclientid;
        }
}
