/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-10-24 9:32:31 
 */

package com.ulearning.ulms.tools.study.info.dao;

import com.ulearning.ulms.tools.study.info.exceptions.StudyInfoDAOSysException;


        public class StudyInfoDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static StudyInfoDAO getDAO() throws StudyInfoDAOSysException
        {
                StudyInfoDAO dao = null;

                try
                {
                        dao = new StudyInfoDAOImpl();
                }
                catch (Exception se)
                {
                        throw new StudyInfoDAOSysException(
                                "StudyInfoDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}

