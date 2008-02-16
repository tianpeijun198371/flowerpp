/**
 * SchoolWorkHelper.java.
 * User: yud  Date: 2005-4-16
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.bean;

import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.tools.schoolwork.dao.SchoolWorkDAO;
import com.ulearning.ulms.tools.schoolwork.dao.SchoolWorkDAOFactory;
import com.ulearning.ulms.tools.schoolwork.form.SchoolWorkForm;
import com.ulearning.ulms.tools.schoolwork.model.SchoolWorkModel;
import com.ulearning.ulms.tools.schoolwork.exceptions.SchoolWorkSysException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import java.util.Date;
import java.util.List;


public class SchoolWorkWeblmpl
{
        private SchoolWorkDAO dao;

        public SchoolWorkWeblmpl()
                throws SchoolWorkSysException
        {
                try
                {
                        dao = SchoolWorkDAOFactory.getDAO();

                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                        throw new SchoolWorkSysException(ex);
                }
        }

        public List getSchoolList(int relationID, int type) throws SchoolWorkSysException
        {
                return dao.getSchoolList(relationID, type);
        }

        public SchoolWorkModel getSchoolModel(int swID) throws SchoolWorkSysException
        {
                return dao.getSchoolModel(swID);
        }

        public void deleteSchoolWork(List l) throws SchoolWorkSysException
        {
                dao.deleteSchoolWork(l);
        }

}
