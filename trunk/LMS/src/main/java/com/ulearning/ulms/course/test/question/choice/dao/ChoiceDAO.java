/**
 * Created by IntelliJ IDEA.
 * Choice: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choice.dao;

import com.ulearning.ulms.course.test.question.choice.exceptions.ChoiceDAOSysException;
import com.ulearning.ulms.course.test.question.choice.form.ChoiceForm;

import java.util.List;


public interface ChoiceDAO
{
        public int addChoice(ChoiceForm details) throws ChoiceDAOSysException;

        public void updateChoice(ChoiceForm details) throws ChoiceDAOSysException;

        public void removeQuestionAndChoice(String[] questionID)
                throws ChoiceDAOSysException;

        public ChoiceForm getChoice(int questionID) throws ChoiceDAOSysException;

        public List getChoiceList(List questionID) throws ChoiceDAOSysException;
}
