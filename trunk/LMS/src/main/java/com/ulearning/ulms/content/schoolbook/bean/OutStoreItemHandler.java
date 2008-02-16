package com.ulearning.ulms.content.schoolbook.bean;

import java.util.ArrayList;
import java.util.List;


public class OutStoreItemHandler
{
        //tcitemquantity:��������,tcitemtclientid:�ͻ�id
        //	 �̲Ļ�����ϢID��
        private String[] infoid;

        // ��������
        private Integer tcitemquantity;

        // ��Ӧ��id
        private Integer tcitemtclientid;

        // ��Ӧ�����������ID
        private Integer mainstoreId;

        public OutStoreItemHandler()
        {
        }

        public OutStoreItemHandler(String[] infoid, Integer tcitemquantity,
                                   Integer tcitemtclientid, Integer mainstoreId)
        {
                this.infoid = infoid;
                this.tcitemquantity = tcitemquantity;
                this.tcitemtclientid = tcitemtclientid;
                this.mainstoreId = mainstoreId;
        }

        public static List buidlOutStoreUnit(OutStoreItemHandler handler)
        {
                List list = null;

                try
                {
                        list = new ArrayList();

                        for (int i = 0;
                             (handler.infoid != null) && (i < handler.infoid.length);
                             i++)
                        {
                                OutStoreItemUnit out = new OutStoreItemUnit();
                                out.setInfoId(new Integer(handler.infoid[i]));
                                out.setTcitemquantity(handler.getTcitemquantity());
                                out.setTcitemtclientid(handler.getTcitemtclientid());
                                out.setMainstoreId(handler.getMainstoreId());
                                list.add(out);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return list;
        }

        public String[] getInfoid()
        {
                return infoid;
        }

        public void setInfoid(String[] infoid)
        {
                this.infoid = infoid;
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
