package com.ulearning.ulms.util;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public class PublicDAOFactory {
	//public PublicDAO dao = null;
	public static String  className="com.ulearning.ulms.util.PublicDAOImpl";
    /**
     * 该类通过反射实现对所有继承PublicDAO的类实例化。
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
