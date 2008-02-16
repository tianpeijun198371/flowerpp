/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.cerdangan.bean;

import com.ulearning.ulms.admin.cerdangan.dao.CerDanganDAO;
import com.ulearning.ulms.admin.cerdangan.dao.CerDanganDAOFactory;
import com.ulearning.ulms.admin.cerdangan.exceptions.CerDanganDAOSysException;
import com.ulearning.ulms.admin.cerdangan.form.CerNewDanganForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class CerDanganImpl
{
        public List getCerDanganList() throws CerDanganDAOSysException
        {
                List CerDanganList = null;

                try
                {
                        CerDanganDAO dao = CerDanganDAOFactory.getDAO();
                        CerDanganList = dao.getCerDanganList();
                }
                catch (CerDanganDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return CerDanganList;
        }

        public CerNewDanganForm getCerDangan(int danganID)
                throws CerDanganDAOSysException
        {
                CerNewDanganForm tf = null;

                try
                {
                        CerDanganDAO dao = CerDanganDAOFactory.getDAO();
                        tf = dao.getCerDangan(danganID);
                }
                catch (CerDanganDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public void deleteCerDangan(int danganID) throws CerDanganDAOSysException
        {
                try
                {
                        CerDanganDAO dao = CerDanganDAOFactory.getDAO();
                        dao.deleteCerDangan(danganID);
                }
                catch (CerDanganDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
