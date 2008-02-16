package com.ulearning.ulms.finance.helper;

import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.dao.MasterDAOImpl;
import com.ulearning.ulms.course.model.MasterModel;

import java.util.HashMap;
import java.util.Map;

public class publicHelper
{
        /**
         * @param typeID �ʻ�����ID   0������
         * @param type   �ʻ����͡�1�����롡��2֧��
         * @return Map ����ʻ��������ƺ�����
         */
        public static Map getTypeName(int typeID, int type)
        {
                String sName = "";
                Map map = new HashMap();
                int itype = type;

                if (1 == itype)
                { //����

                        if (0 == typeID)
                        {
                                sName = "��"; //ȫ�ǿո�HTML��ʾ��
                        }
                        else
                        {
                                IncomeTypeHelper iHelp = new IncomeTypeHelper();
                                sName = iHelp.getIncomenameOfID(typeID);
                        }

                        map.put("typeName", sName);
                        map.put("typeDesc", "�ʻ�����");
                }
                else
                { //֧��

                        if (0 == typeID)
                        {
                                sName = "��"; //ȫ�ǿո�HTML��ʾ��
                        }
                        else
                        {
                                PayoutTypeHelper oHelp = new PayoutTypeHelper();
                                sName = oHelp.getPayoutNameOfID(typeID);
                        }

                        map.put("typeName", sName);
                        map.put("typeDesc", "�ʻ�����");
                }

                return map;
        }

        /**
         * ���ݿγ�IDȡ�γ�����
         *
         * @param ID
         * @return
         */
        public static String getMastName(int ID)
        {
                String sName = "�γ���ɾ��";
                MasterModel model = null; //�γ�model
                MasterDAOImpl mastDAO = (MasterDAOImpl) MasterDAOFactory.getDAO();
                model = mastDAO.getMaster(ID); //����

                if (null != model)
                {
                        sName = model.getName();
                }

                return sName;
        }
}
