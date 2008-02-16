/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.dao;

import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.GradeForm;

import java.util.List;


public interface GradeDAO
{
        /**
         * Add a grade to platform according to the Grade object
         *
         * @param gradeForm
         * @return
         * @throws GradeDAOSysException
         */
        public int addGrade(GradeForm gradeForm) throws GradeDAOSysException;

        /**
         * Add a paper to grade
         *
         * @param gradeForm
         * @throws GradeDAOSysException
         */
        public void addPaper2Grade(GradeForm gradeForm) throws GradeDAOSysException;

        /**
         * Remove papre from grade
         *
         * @param gradeForm
         * @throws GradeDAOSysException
         */
        public void RemoveGradePaper(GradeForm gradeForm)
                throws GradeDAOSysException;

        /**
         * Update the info of grade
         *
         * @param details
         * @throws GradeDAOSysException
         */
        public void updateGrade(GradeForm details) throws GradeDAOSysException;

        /**
         * Delete the grade record
         *
         * @param GradeID
         * @throws GradeDAOSysException
         */
        public void removeGrade(String GradeID) throws GradeDAOSysException;

        /**
         * Get Grade Form according to gradeID
         *
         * @param GradeID
         * @return prepared GradeForm, default is null
         * @throws GradeDAOSysException
         */
        public GradeForm getGrade(int GradeID) throws GradeDAOSysException;

        /**
         * Get all the
         * @return
         * @throws GradeDAOSysException
         */

        /**
         * Get all the grade according to the courseID
         *
         * @param courseID
         * @return
         * @throws GradeDAOSysException
         */
        public List getGradeList(int courseID) throws GradeDAOSysException;

        /**
         * @param gradeID
         * @param courseID
         * @param type
         * @return
         * @throws GradeDAOSysException
         */
        public List getGradeNoSelectedPaperID(int gradeID, int type, int courseID)
                throws GradeDAOSysException;

        /**
         * @param gradeID
         * @param courseID
         * @param type
         * @return
         * @throws GradeDAOSysException
         */
        public List getGradeSelectePaperID(int gradeID, int type, int courseID)
                throws GradeDAOSysException;

        public boolean gradePaperIsExist(int gradeID, int relationID, int type)
                throws GradeDAOSysException;
}
