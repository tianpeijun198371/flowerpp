package com.ulearning.ulms.content.schoolbook.bean;

import java.util.List;


public class StoreMessageViewBean
{
        private Integer tcmainid;

        // 经办人
        private String tcmaintran;

        // 供应商ID
        private Integer tcitemsupplierid;

        // 供应商名称
        private String tcitemsuppliername;

        // 入库教材消息列表
        private List messageList;

        // 合计入库数量
        private Integer sumQuantity = new Integer(0);

        // 合计总价
        private Float sumPrice = new Float(0);

        // constructor stub or Copy form MTeachmainstor
        public StoreMessageViewBean()
        {
        }

        public StoreMessageViewBean(String tcmaintran, Integer tcitemsupplierid,
                                    String tcitemsuppliername)
        {
                this.tcmaintran = tcmaintran;
                this.tcitemsupplierid = tcitemsupplierid;
                this.tcitemsuppliername = tcitemsuppliername;
        }

        //添加消息列后自动计算
        public StoreMessageViewBean(String tcmaintran, Integer tcitemsupplierid,
                                    String tcitemsuppliername, List messageList)
        {
                this.tcmaintran = tcmaintran;
                this.tcitemsupplierid = tcitemsupplierid;
                this.tcitemsuppliername = tcitemsuppliername;
                this.messageList = messageList;
                caculate();
        }

        public Float getSumPrice()
        {
                return sumPrice;
        }

        public Integer getSumQuantity()
        {
                return sumQuantity;
        }

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("tcmaintran=").append(getTcmaintran());
                sb.append("\r\n").append("tcitemsupplierid=")
                        .append(getTcitemsupplierid());
                sb.append("\r\n").append("tcitemsuppliername=")
                        .append(getTcitemsuppliername());
                sb.append("\r\n").append("messageList ****LIST[]****=")
                        .append(getMessageList());
                sb.append("\r\n").append("sumQuantity=").append(getSumQuantity());
                sb.append("\r\n").append("sumPrice=").append(getSumPrice());

                return sb.toString();
        }

        //计算合计总价和合计入库数量
        private void caculate()
        {
                if ((null != messageList) && (messageList.size() > 0))
                {
                        try
                        {
                                float temp = 0.0f;
                                int tempNum = 0;

                                for (int i = 0; i < messageList.size(); i++)
                                {
                                        StoreMessageBean smb = (StoreMessageBean) messageList.get(i);
                                        temp = temp + smb.getTcitemtotal().floatValue();
                                        tempNum = tempNum + smb.getTcitemquantity().intValue();
                                }

                                sumPrice = new Float(temp);
                                sumQuantity = new Integer(tempNum);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        public List getMessageList()
        {
                return messageList;
        }

        public void setMessageList(List messageList)
        {
                this.messageList = messageList;
                caculate();
        }

        public Integer getTcitemsupplierid()
        {
                return tcitemsupplierid;
        }

        public void setTcitemsupplierid(Integer tcitemsupplierid)
        {
                this.tcitemsupplierid = tcitemsupplierid;
        }

        public String getTcitemsuppliername()
        {
                if ((tcitemsupplierid == null) || (tcitemsupplierid.intValue() == 0))
                {
                        tcitemsuppliername = "自购";
                }

                return tcitemsuppliername;
        }

        public void setTcitemsuppliername(String tcitemsuppliername)
        {
                this.tcitemsuppliername = tcitemsuppliername;
        }

        public String getTcmaintran()
        {
                return tcmaintran;
        }

        public void setTcmaintran(String tcmaintran)
        {
                this.tcmaintran = tcmaintran;
        }

        public Integer getTcmainid()
        {
                return tcmainid;
        }

        public void setTcmainid(Integer tcmainid)
        {
                this.tcmainid = tcmainid;
        }
}
