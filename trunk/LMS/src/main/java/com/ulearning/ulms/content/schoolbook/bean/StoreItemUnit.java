package com.ulearning.ulms.content.schoolbook.bean;

import java.io.Serializable;


public class StoreItemUnit implements Serializable
{
        //	 �̲Ļ�����ϢID
        private Integer unitInfoId;

        // �������
        private Integer tcitemquantity;

        // ��Ӧ��id
        private Integer tcitemsupplierid;

        // ��Ӧ����������ID
        private Integer mainstoreId;

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("unitInfoId=").append(unitInfoId);
                sb.append("\r\n").append("tcitemquantity=").append(tcitemquantity);
                sb.append("\r\n").append("tcitemsupplierid=").append(tcitemsupplierid);
                sb.append("\r\n").append("mainstoreId=").append(mainstoreId);

                return sb.toString();
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

        public Integer getTcitemsupplierid()
        {
                return tcitemsupplierid;
        }

        public void setTcitemsupplierid(Integer tcitemsupplierid)
        {
                this.tcitemsupplierid = tcitemsupplierid;
        }

        public Integer getUnitInfoId()
        {
                return unitInfoId;
        }

        public void setUnitInfoId(Integer unitInfoId)
        {
                this.unitInfoId = unitInfoId;
        }
}
