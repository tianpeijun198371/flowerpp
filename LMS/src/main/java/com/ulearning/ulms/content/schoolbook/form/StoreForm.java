package com.ulearning.ulms.content.schoolbook.form;

import org.apache.struts.action.ActionForm;

/**
 * �������
 */
import java.util.Date;


public class StoreForm extends ActionForm
{
        //��⾭����
        private String tcmaintran = "";

        //����Ա: userName in Session
        private String tcmainoperator = "";

        //����
        private Date tcmaindate = new Date();

        //��˱�ǣ�Ĭ��δ���
        private Integer tcmainaudmark = new Integer(0);

        //���������
        private String tcmainaudman = "";

        //��ע
        private String tcmainmemo = "";

        //�������
        private Integer tcitemquantity = new Integer(0);

        //��Ӧ��,0Ϊ�޹�Ӧ�̣��Թ���
        private Integer tcitemsupplierid = new Integer(0);

        //�������
        private Integer inmainmark = new Integer(1);

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("tcmaintran=").append(getTcmaintran());
                sb.append("\r\n").append("tcmainoperator=").append(getTcmainoperator());
                sb.append("\r\n").append("tcmaindate=").append(getTcmaindate());
                sb.append("\r\n").append("tcmainaudmark=").append(getTcmainaudmark());
                sb.append("\r\n").append("tcmainaudman=").append(getTcmainaudman());
                sb.append("\r\n").append("tcmainmemo=").append(getTcmainmemo());
                sb.append("\r\n").append("tcitemquantity=").append(getTcitemquantity());
                sb.append("\r\n").append("tcitemsupplierid=")
                        .append(getTcitemsupplierid());
                sb.append("\r\n").append("inmainmark=").append(getInmainmark());

                return sb.toString();
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

        public String getTcmaintran()
        {
                return tcmaintran;
        }

        public void setTcmaintran(String tcmaintran)
        {
                this.tcmaintran = tcmaintran;
        }

        public Integer getInmainmark()
        {
                return inmainmark;
        }

        public void setInmainmark(Integer inmainmark)
        {
                this.inmainmark = inmainmark;
        }
}
