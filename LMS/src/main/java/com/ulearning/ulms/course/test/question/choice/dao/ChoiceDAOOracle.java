/**
 * Created by IntelliJ IDEA.
 * Choice: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choice.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOImpl;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOOracle;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.question.choice.exceptions.ChoiceDAOSysException;
import com.ulearning.ulms.course.test.question.choice.form.ChoiceForm;
import com.ulearning.ulms.course.test.question.choiceitem.dao.ChoiceItemDAOOracle;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;


public class ChoiceDAOOracle extends ChoiceDAOImpl
{
        /**
         * Insert a new choice record to database
         *
         * @param choiceForm the value object to be added
         * @throws ChoiceDAOSysException
         */
        public int addChoice(ChoiceForm choiceForm) throws ChoiceDAOSysException
        {
                int questionID = 0;
                BaseForm baseForm = choiceForm.getBaseForm();
                ArrayList list = choiceForm.getList();
                BaseDAOOracle baseDAOOracle = new BaseDAOOracle();
                ChoiceItemDAOOracle choiceItemDAOOracle = new ChoiceItemDAOOracle();
                BaseDAOImpl baseDAOImpl = new BaseDAOImpl();

                try
                {
                        questionID = baseDAOOracle.addBase(baseForm);
                        //questionID=baseDAOImpl.getQuestionID(baseForm);
                        LogUtil.debug("system",
                                "[ChoiceDAOOracle.addChoice]====================");

                        if (questionID != 0)
                        {
                                for (Iterator it = list.iterator(); it.hasNext();)
                                {
                                        ChoiceItemForm cim = (ChoiceItemForm) it.next();
                                        cim.setQuestionID(questionID);
                                        choiceItemDAOOracle.addChoiceItem(cim);
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new ChoiceDAOSysException(
                                "SQLException while addChoice ; Serial =  :\n" + se);
                }

                return questionID;
        }

        /**
         * Update choiceInfo by the new Form
         *
         * @param choiceForm value object for changed
         * @throws ChoiceDAOSysException
         */
        public void updateChoice(ChoiceForm choiceForm)
                throws ChoiceDAOSysException
        {
                BaseForm baseForm = choiceForm.getBaseForm();
                ArrayList list = choiceForm.getList();
                BaseDAOOracle baseDAOOracle = new BaseDAOOracle();
                ChoiceItemDAOOracle choiceItemDAOOracle = new ChoiceItemDAOOracle();

                try
                {
                        baseDAOOracle.updateBase(baseForm);
                        LogUtil.debug("system",
                                "[ChoiceDAOOracle.addChoice]====================");
                        choiceItemDAOOracle.removeQuestionChoiceItem(baseForm.getQuestionID());

                        for (Iterator it = list.iterator(); it.hasNext();)
                        {
                                ChoiceItemForm cim = (ChoiceItemForm) it.next();
                                choiceItemDAOOracle.addChoiceItem(cim);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new ChoiceDAOSysException(
                                "SQLException while addChoice ; Serial =  :\n" + se);
                }
        }
}
