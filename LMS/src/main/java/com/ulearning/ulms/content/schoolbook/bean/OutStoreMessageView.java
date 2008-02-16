package com.ulearning.ulms.content.schoolbook.bean;

import java.util.List;


public class OutStoreMessageView
{
        private Integer tcmainid;

        //领用人
        private String tcmainuser;

        //客户ID
        private Integer tcitemtclientid;

        // 客户名称
        private String tcitemtclientname;

        // 出库教材消息列表
        private List messageList;

        // 合计数量
        private Integer sumQuantity = new Integer(0);

        // 合计总价
        private Float sumPrice = new Float(0);

        public OutStoreMessageView()
        {
        }

        public OutStoreMessageView(String tcmainuser, Integer tcitemtclientid,
                                   String tcitemtclientname)
        {
                this.tcmainuser = tcmainuser;
                this.tcitemtclientid = tcitemtclientid;
                this.tcitemtclientname = tcitemtclientname;
        }

        public OutStoreMessageView(String tcmainuser, Integer tcitemtclientid,
                                   String tcitemtclientname, List messageList)
        {
                this.tcmainuser = tcmainuser;
                this.tcitemtclientid = tcitemtclientid;
                this.tcitemtclientname = tcitemtclientname;
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
                sb.append("\r\n").append("tcmainid=").append(getTcmainid());
                sb.append("\r\n").append("tcmainuser=").append(getTcmainuser());
                sb.append("\r\n").append("tcitemtclientid=").append(getTcitemtclientid());
                sb.append("\r\n").append("tcitemtclientname=")
                        .append(getTcitemtclientname());
                sb.append("\r\n").append("messageList ****LIST[]****=")
                        .append(getMessageList());
                sb.append("\r\n").append("sumQuantity=").append(getSumQuantity());
                sb.append("\r\n").append("sumPrice=").append(getSumPrice());

                return sb.toString();
        }

        //	计算合计总价和合计出库数量
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

        public Integer getTcitemtclientid()
        {
                return tcitemtclientid;
        }

        public void setTcitemtclientid(Integer tcitemtclientid)
        {
                this.tcitemtclientid = tcitemtclientid;
        }

        public String getTcitemtclientname()
        {
                if ((tcitemtclientid == null) || (tcitemtclientid.intValue() == 0))
                {
                        tcitemtclientname = "内部";
                }

                return tcitemtclientname;
        }

        public void setTcitemtclientname(String tcitemtclientname)
        {
                this.tcitemtclientname = tcitemtclientname;
        }

        public Integer getTcmainid()
        {
                return tcmainid;
        }

        public void setTcmainid(Integer tcmainid)
        {
                this.tcmainid = tcmainid;
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
