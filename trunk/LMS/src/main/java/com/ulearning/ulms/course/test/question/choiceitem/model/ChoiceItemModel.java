package com.ulearning.ulms.course.test.question.choiceitem.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class ChoiceItemModel implements Serializable
{
        /**
         * identifier field
         */
        private int choiceItemID;

        /**
         * nullable persistent field
         */
        private int questionID;

        /**
         * persistent field
         */
        private String title;

        /**
         * nullable persistent field
         */
        private String link;

        /**
         * full constructor
         */
        public ChoiceItemModel(int questionID, String title, String link)
        {
                this.questionID = questionID;
                this.title = title;
                this.link = link;
        }

        /**
         * default constructor
         */
        public ChoiceItemModel()
        {
        }

        /**
         * minimal constructor
         */
        public ChoiceItemModel(String title)
        {
                this.title = title;
        }

        public int getChoiceItemID()
        {
                return this.choiceItemID;
        }

        public void setChoiceItemID(int choiceItemID)
        {
                this.choiceItemID = choiceItemID;
        }

        public int getQuestionID()
        {
                return this.questionID;
        }

        public void setQuestionID(int questionID)
        {
                this.questionID = questionID;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getLink()
        {
                return this.link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("choiceItemID",
                        getChoiceItemID()).toString();
        }
}
