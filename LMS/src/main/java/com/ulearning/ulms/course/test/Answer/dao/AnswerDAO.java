/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Answer.dao;

import com.ulearning.ulms.course.test.Answer.exceptions.AnswerDAOSysException;
import com.ulearning.ulms.course.test.Answer.form.AnswerForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051121
 * Time: 135243
 */
public interface AnswerDAO
{
        public Serializable insertAnswer(AnswerForm tf)
                throws AnswerDAOSysException;

        public void updateAnswer(AnswerForm tf) throws AnswerDAOSysException;

        public void deleteAnswer(int answerID) throws AnswerDAOSysException;

        public List getAnswerList() throws AnswerDAOSysException;

        public AnswerForm getAnswer(int answerID) throws AnswerDAOSysException;
}
