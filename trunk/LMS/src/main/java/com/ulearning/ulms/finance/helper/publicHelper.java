package com.ulearning.ulms.finance.helper;

import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.dao.MasterDAOImpl;
import com.ulearning.ulms.course.model.MasterModel;

import java.util.HashMap;
import java.util.Map;

public class publicHelper
{
        /**
         * @param typeID 帐户类型ID   0：其它
         * @param type   帐户类型　1：收入　　2支出
         * @return Map 组合帐户类型名称和描述
         */
        public static Map getTypeName(int typeID, int type)
        {
                String sName = "";
                Map map = new HashMap();
                int itype = type;

                if (1 == itype)
                { //收入

                        if (0 == typeID)
                        {
                                sName = "　"; //全角空格HTML显示用
                        }
                        else
                        {
                                IncomeTypeHelper iHelp = new IncomeTypeHelper();
                                sName = iHelp.getIncomenameOfID(typeID);
                        }

                        map.put("typeName", sName);
                        map.put("typeDesc", "帐户增加");
                }
                else
                { //支出

                        if (0 == typeID)
                        {
                                sName = "　"; //全角空格HTML显示用
                        }
                        else
                        {
                                PayoutTypeHelper oHelp = new PayoutTypeHelper();
                                sName = oHelp.getPayoutNameOfID(typeID);
                        }

                        map.put("typeName", sName);
                        map.put("typeDesc", "帐户减少");
                }

                return map;
        }

        /**
         * 根据课程ID取课程名称
         *
         * @param ID
         * @return
         */
        public static String getMastName(int ID)
        {
                String sName = "课程已删除";
                MasterModel model = null; //课程model
                MasterDAOImpl mastDAO = (MasterDAOImpl) MasterDAOFactory.getDAO();
                model = mastDAO.getMaster(ID); //调用

                if (null != model)
                {
                        sName = model.getName();
                }

                return sName;
        }
}
