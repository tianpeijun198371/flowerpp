/**
 * PaperDAO.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperForm;

import java.util.List;
import java.util.Set;


public interface PaperDAO
{
        public int addPaper(PaperForm paperForm) throws PaperDAOSysException;

        public void updatePaper(PaperForm paperForm) throws PaperDAOSysException;

        public void removePaper(int paperID) throws PaperDAOSysException;

        public PaperForm getPaper(int paperID) throws PaperDAOSysException;

        public List getPaperList(int courseID, int type)
                throws PaperDAOSysException;

        public List getPaperShowList(int courseID, int type)
                throws PaperDAOSysException;

        public List getExerciseList(int courseID, int type)
                throws PaperDAOSysException;

        public List getDistinctExerciseList(int courseID, int type)
                throws PaperDAOSysException;
}
