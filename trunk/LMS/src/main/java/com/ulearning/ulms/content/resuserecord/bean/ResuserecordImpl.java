/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.resuserecord.bean;

import com.ulearning.ulms.content.resuserecord.dao.ResuserecordDAO;
import com.ulearning.ulms.content.resuserecord.dao.ResuserecordDAOFactory;
import com.ulearning.ulms.content.resuserecord.exceptions.ResuserecordDAOSysException;
import com.ulearning.ulms.content.resuserecord.form.ResuserecordForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class ResuserecordImpl
{
        public List getResuserecordList() throws ResuserecordDAOSysException
        {
                List ResuserecordList = null;

                try
                {
                        ResuserecordDAO dao = ResuserecordDAOFactory.getDAO();
                        ResuserecordList = dao.getResuserecordList();
                }
                catch (ResuserecordDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return ResuserecordList;
        }

        public ResuserecordForm getResuserecord(int resuseID)
                throws ResuserecordDAOSysException
        {
                ResuserecordForm tf = null;

                try
                {
                        ResuserecordDAO dao = ResuserecordDAOFactory.getDAO();
                        tf = dao.getResuserecord(resuseID);
                }
                catch (ResuserecordDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public void deleteResuserecord(int resuseID)
                throws ResuserecordDAOSysException
        {
                try
                {
                        ResuserecordDAO dao = ResuserecordDAOFactory.getDAO();
                        dao.deleteResuserecord(resuseID);
                }
                catch (ResuserecordDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
