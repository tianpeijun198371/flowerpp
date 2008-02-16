/**
 * Created by IntelliJ IDEA.
 * Base: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.base.dao;

import com.ulearning.ulms.course.test.question.base.exceptions.BaseDAOSysException;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;

import java.util.List;


public interface BaseDAO
{
        public int addBase(BaseForm details) throws BaseDAOSysException;

        public void updateBase(BaseForm details) throws BaseDAOSysException;

        public void removeBase(String baseID) throws BaseDAOSysException;

        public void removeBase(String[] baseID) throws BaseDAOSysException;

        public BaseForm getBase(int baseID) throws BaseDAOSysException;

        public List getBaseList(int courseID, int parentID)
                throws BaseDAOSysException;

        /**
         * 得到题库目录列表
         *
         * @param courseID
         * @return
         * @throws BaseDAOSysException
         */
        public List getBaseListExamFloder(int courseID) throws BaseDAOSysException;

        public List getBaseListExam(int courseID, int parentID)
                throws BaseDAOSysException;

        public List getBaseAList(List questionID) throws BaseDAOSysException;

        public List getBaseList(List questionID) throws BaseDAOSysException;

        public List searchBase(int courseID, int parentID, String type,
                               String hardLevel, String scope, String[] chapter, String[] point,
                               String[] key) throws BaseDAOSysException;

        public List searchBase(String[] parentID, boolean includeSub, int courseID,
                               String type, String hardLevel, String[] scope, String[] chapter,
                               String maxScore, String minScore, String[] point, String[] key)
                throws BaseDAOSysException;

        public List searchBase(int courseID, int parentID, String chapter,
                               String point, int hardLevel, String type, String strscope)
                throws BaseDAOSysException;

        public List getBaseListExamChapter(int courseID) throws BaseDAOSysException;

        public List getBaseListExamPoint(int courseID) throws BaseDAOSysException;

        public List getBaseListExam(int courseID) throws BaseDAOSysException;
}
