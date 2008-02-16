package com.ulearning.ulms.util;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public class PublicDAOFactory {
	//public PublicDAO dao = null;
	public static String  className="com.ulearning.ulms.util.PublicDAOImpl";
    /**
     * ����ͨ������ʵ�ֶ����м̳�PublicDAO����ʵ������
     * @return PublicDAO
     * @throws ULMSAppException
     */
	public static PublicDAO getDAO() throws ULMSAppException
    {
    	PublicDAO dao = null;
            try
            {
                    if (null == dao)
                    {
                            dao = (PublicDAO) Class.forName(className).newInstance();
                    }
                    else
                    {
                            if (!dao.getClass().getName().equals(className))
                            {
                                    dao = (PublicDAO) Class.forName(className).newInstance();
                            }
                    }
            }
            catch (ClassNotFoundException ex)
            {
                    ex.printStackTrace();
            }
            catch (IllegalAccessException ex)
            {
                    ex.printStackTrace();
            }
            catch (InstantiationException ex)
            {
                    ex.printStackTrace();
            }

            catch (Exception se)
            {
                    throw new ULMSAppException("PublicDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
            }

            return dao;

    }
}
