/**
 * Created by IntelliJ IDEA.
 * ChoiceItem: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choiceitem.form;

import com.ulearning.ulms.course.test.question.choiceitem.model.ChoiceItemModel;

import org.apache.struts.action.ActionForm;


public class ChoiceItemForm
{
        private int choiceItemID = 0;
        private int questionID = 0;
        private String itemTitle = null;
        private String link = null;
        private ChoiceItemModel choiceItemModel = null;

        public ChoiceItemModel getChoiceItemModel()
        {
                choiceItemModel = new ChoiceItemModel();
                choiceItemModel.setQuestionID(this.questionID);
                choiceItemModel.setChoiceItemID(this.choiceItemID);
                choiceItemModel.setTitle(this.itemTitle);
                choiceItemModel.setLink(this.link);

                return choiceItemModel;
        }

        public ChoiceItemForm getChoiceItemForm(ChoiceItemModel choiceItemModel)
        {
                ChoiceItemForm choiceItemForm = new ChoiceItemForm();
                choiceItemForm.setQuestionID(choiceItemModel.getQuestionID());
                choiceItemForm.setChoiceItemID(choiceItemModel.getChoiceItemID());
                choiceItemForm.setItemTitle(choiceItemModel.getTitle());
                choiceItemForm.setLink(choiceItemModel.getLink());

                return choiceItemForm;
        }

        public int getChoiceItemID()
        {
                return choiceItemID;
        }

        public void setChoiceItemID(int choiceItemID)
        {
                this.choiceItemID = choiceItemID;
        }

        public int getQuestionID()
        {
                return questionID;
        }

        public void setQuestionID(int questionID)
        {
                this.questionID = questionID;
        }

        public String getItemTitle()
        {
                return itemTitle;
        }

        public void setItemTitle(String itemTitle)
        {
                this.itemTitle = itemTitle;
        }

        public String getLink()
        {
                return link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }
}
