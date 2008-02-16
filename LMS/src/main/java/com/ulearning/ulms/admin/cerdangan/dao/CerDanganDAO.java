/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.cerdangan.dao;

import com.ulearning.ulms.admin.cerdangan.exceptions.CerDanganDAOSysException;
import com.ulearning.ulms.admin.cerdangan.form.CerNewDanganForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public interface CerDanganDAO
{
        public void insertCerDangan(CerNewDanganForm tf)
                throws CerDanganDAOSysException;

        public void updateCerDangan(CerNewDanganForm tf)
                throws CerDanganDAOSysException;

        public void deleteCerDangan(int danganID) throws CerDanganDAOSysException;

        public List getCerDanganList() throws CerDanganDAOSysException;

        public CerNewDanganForm getCerDangan(int danganID)
                throws CerDanganDAOSysException;
}
