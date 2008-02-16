/**
 * CodeMaintainDAO.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.common.form.PeizForm;
import com.ulearning.ulms.common.form.UpdatePeizForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


public interface CodeMaintainDAO
{
        /*
        * get the course'score type
        */
        public String getScoreType(int scoreType) throws ULMSSysException;

        /*
        * get the list of score type
        */
        public List getScoreTypes() throws ULMSSysException;

        /*
        * get the value of the SpecScore
        */
        public String getSpecScore(int specScore, int scoreType)
                throws ULMSSysException;

        /*
        * get the list of the scoreType
        */
        public List getSpecScores(int scoreType) throws ULMSSysException;

        /*
        * get the TeachMode
        */
        public String getTeachMode(int teachModeID) throws ULMSSysException;

        /*
        * get the TeachMode list.
        */
        public List getTeachModes() throws ULMSSysException;

        /*
        * get the CourseType
        */
        public String getCourseType(int courseTypeID) throws ULMSSysException;

        /*
        * get the CourseType list
        */
        public List getCourseTypes() throws ULMSSysException;

        /**
         * Get the code list according to the code type
         *
         * @param codeID the code type
         * @param aspID  the asp's id
         * @return Prepared code list ,otherwise return empty arraylist
         * @throws ULMSSysException
         */
        public List getCodeInfo(int codeID, int aspID) throws ULMSSysException;

        /**
         * @param codeID
         * @throws ULMSSysException
         */
        public void delete(ArrayList codeID) throws ULMSSysException;

        /**
         * @param CodeItemID
         * @throws ULMSSysException
         */
        public void deleteitem(ArrayList CodeItemID) throws ULMSSysException;

        /**
         * get all code
         * @return the code list.
         * @throws ULMSSysException
         */

        /**
         * @param aspID
         * @return
         * @throws ULMSSysException
         */
        public List getCodes(int aspID) throws ULMSSysException;

        /**
         * @param details
         * @return
         * @throws ULMSSysException
         */
        public Serializable addpeiz(PeizForm details) throws ULMSSysException;

        /**
         * @param details
         * @throws ULMSSysException
         */
        public void updatepeiz(UpdatePeizForm details) throws ULMSSysException;

        public UpdatePeizForm getCodeItemByID(int id) throws ULMSSysException;
}
