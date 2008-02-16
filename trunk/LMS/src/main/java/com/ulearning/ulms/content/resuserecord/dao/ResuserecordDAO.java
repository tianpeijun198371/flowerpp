/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.resuserecord.dao;

import com.ulearning.ulms.content.resuserecord.exceptions.ResuserecordDAOSysException;
import com.ulearning.ulms.content.resuserecord.form.ResuserecordForm;

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
public interface ResuserecordDAO
{
        public Serializable insertResuserecord(ResuserecordForm tf)
                throws ResuserecordDAOSysException;

        public void updateResuserecord(ResuserecordForm tf)
                throws ResuserecordDAOSysException;

        public void deleteResuserecord(int resuseID)
                throws ResuserecordDAOSysException;

        public List getResuserecordList() throws ResuserecordDAOSysException;

        public ResuserecordForm getResuserecord(int resuseID)
                throws ResuserecordDAOSysException;
}
