/**
 * Created by IntelliJ IDEA.
 * ChoiceItem: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choiceitem.dao;

import com.ulearning.ulms.course.test.question.choiceitem.exceptions.ChoiceItemDAOSysException;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;

import java.util.List;


public interface ChoiceItemDAO
{
        public void addChoiceItem(ChoiceItemForm details)
                throws ChoiceItemDAOSysException;

        public void updateChoiceItem(ChoiceItemForm details)
                throws ChoiceItemDAOSysException;

        public void removeChoiceItem(String choiceitemID)
                throws ChoiceItemDAOSysException;

        public void removeQuestionChoiceItem(int questionID)
                throws ChoiceItemDAOSysException;

        public void removeQuestionAndChoiceItem(String[] questionID)
                throws ChoiceItemDAOSysException;

        public ChoiceItemForm getChoiceItem(int choiceitemID)
                throws ChoiceItemDAOSysException;

        public List getChoiceItemList(int questionID)
                throws ChoiceItemDAOSysException;
}
