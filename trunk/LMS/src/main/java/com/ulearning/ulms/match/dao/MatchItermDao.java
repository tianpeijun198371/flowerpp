/**
 * MatchItermDao.java.
 * User: zhangy Date: 2005-6-6 14:07:57
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.dao;

import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.match.form.MatchItermForm;
import com.ulearning.ulms.match.model.ItermModel;

import java.util.List;


public interface MatchItermDao
{
        /**
         * ����AddMatchNameAction�������� M_MatchIterm_Tab ��д������
         *
         * @param matchItermForm
         * @throws com.ulearning.ulms.match.exceptions.MatchDaoSysException
         *
         */
        public void addMatchIterm(MatchItermForm matchItermForm)
                throws MatchDaoSysException;

        /**
         * ����AddMatchNameAction������ɾ��M_Match_Tab��M_MatchIterm_Tab��������
         *
         * @param matchid
         * @throws MatchDaoSysException
         */
        public void delMatchIterm(int matchid) throws MatchDaoSysException;

        /**
         * ���� M_MatchIterm_Tab (ƥ����Ժ�ƥ����Ĺ���������)���ֶ�
         *
         * @param matchID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchIterm(int matchID) throws MatchDaoSysException;

        /**
         * �õ���c_match_tab���е� MatchKey
         *
         * @param ItemID
         * @return
         * @throws MatchDaoSysException
         */
        public String getMatchItermName(int ItemID) throws MatchDaoSysException;

        /**
         * �õ� ItermModel
         *
         * @param ItemID
         * @return
         * @throws MatchDaoSysException
         */
        public ItermModel getMatchItermForm(int ItemID) throws MatchDaoSysException;
}
