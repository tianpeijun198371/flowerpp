package com.ulearning.ulms.content.schoolbook.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class OutStoreForm extends ActionForm
{
        //	领用人
        private String tcmainuser = "";

        //操作员: userName in Session
        private String tcmainoperator = "";

        //日期
        private Date tcmaindate = new Date();

        //审核标记，默认未审核
        private Integer tcmainaudmark = new Integer(0);

        //审核人名称
        private String tcmainaudman = "";

        //备注
        private String tcmainmemo = "";

        //出库数量
        private Integer tcitemquantity = new Integer(0);

        //客户ID
        private Integer tcitemtclientid = new Integer(0);

        //出入库标记
        private Integer inmainmark = new Integer(2);

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("tcmainuser=").append(getTcmainuser());
                sb.append("\r\n").append("tcmainoperator=").append(getTcmainoperator());
                sb.append("\r\n").append("tcmaindate=").append(getTcmaindate());
                sb.append("\r\n").append("tcmainaudmark=").append(getTcmainaudmark());
                sb.append("\r\n").append("tcmainaudman=").append(getTcmainaudman());
                sb.append("\r\n").append("tcmainmemo=").append(getTcmainmemo());
                sb.append("\r\n").append("tcitemquantity=").append(getTcitemquantity());
                sb.append("\r\n").append("tcitemtclientid=").append(getTcitemtclientid());
                sb.append("\r\n").append("inmainmark=").append(getInmainmark());

                return sb.toString();
        }

        public Integer getInmainmark()
        {
                return inmainmark;
        }

        public void setInmainmark(Integer inmainmark)
        {
                this.inmainmark = inmainmark;
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

        public String getTcmainaudman()
        {
                return tcmainaudman;
        }

        public void setTcmainaudman(String tcmainaudman)
        {
                this.tcmainaudman = tcmainaudman;
        }

        public Integer getTcmainaudmark()
        {
                return tcmainaudmark;
        }

        public void setTcmainaudmark(Integer tcmainaudmark)
        {
                this.tcmainaudmark = tcmainaudmark;
        }

        public Date getTcmaindate()
        {
                return tcmaindate;
        }

        public void setTcmaindate(Date tcmaindate)
        {
                this.tcmaindate = tcmaindate;
        }

        public String getTcmainmemo()
        {
                return tcmainmemo;
        }

        public void setTcmainmemo(String tcmainmemo)
        {
                this.tcmainmemo = tcmainmemo;
        }

        public String getTcmainoperator()
        {
                return tcmainoperator;
        }

        public void setTcmainoperator(String tcmainoperator)
        {
                this.tcmainoperator = tcmainoperator;
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
