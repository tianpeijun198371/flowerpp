/**
 * MatchDao.java.
 * User: zhangy Date: 2005-6-3 9:17:02
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.dao;

import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.match.form.MatchForm;
import com.ulearning.ulms.match.model.MatchModel;
import com.ulearning.ulms.user.form.UserForm;

import java.util.List;


public interface MatchDao
{
        /**
         * ����AddMatchNameAction��������M_Match_Tab ��д������
         *
         * @param matchForm
         * @throws MatchDaoSysException
         */
        public void addMatch(MatchForm matchForm) throws MatchDaoSysException;

        /**
         * ����AddMatchNameAction������ɾ��M_Match_Tab��������
         *
         * @param matchid
         * @throws MatchDaoSysException
         */
        public void delMatch(int matchid) throws MatchDaoSysException;

        /**
         * ����AddMatchNameAction �������� name �õ� M_Match_Tab ��� MatchID
         *
         * @param name
         * @return
         * @throws MatchDaoSysException
         */
        public int getMatchID(String name) throws MatchDaoSysException;

        /**
         * �� orgID ����� M_Match_Tab (ƥ�����������)���������
         *
         * @param orgID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatch(int orgID) throws MatchDaoSysException;

        /**
         * �� modelID ����� M_Match_Tab (ƥ�����������)���������
         *
         * @param modelID
         * @return
         * @throws MatchDaoSysException
         */
        public MatchModel getMatchModel(int modelID) throws MatchDaoSysException;

        /**
         * ��� ��Ҫ����� name ��M_Match_Tab���е��Ƿ����ظ���
         * ����з���false�����򷵻�true
         *
         * @param name
         * @return
         * @throws MatchDaoSysException
         */
        public boolean getBooleanMatchName(String name) throws MatchDaoSysException;

        /**
         * ��� ��Ҫ����� name ��M_Match_Tab���е��Ƿ����ظ���
         * ����з���false�����򷵻�true
         *
         * @param matchid
         * @return
         * @throws MatchDaoSysException
         */
        public boolean getBooleanMatchName1(int matchid, String name)
                throws MatchDaoSysException;

        /**
         * ����AddMatchNameAction�������޸� M_MatchIterm_Tab �� M_MatchIterm_Tab ��������
         *
         * @param matchForm
         * @throws MatchDaoSysException
         */
        public void updateMatch(MatchForm matchForm) throws MatchDaoSysException;

        /**
         * �õ��� M_Match_Tab ���� RelationID ������û��� UserForm
         *
         * @param orgID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchUsersFromDepart(int orgID, int matchid)
                throws MatchDaoSysException;

        /**
         * �ж��û��Ƿ��� M_Match_Tab �Ĺ������,���Ϸ��� true ,���򷵻� false
         *
         * @param uf
         * @param listMatches
         * @return
         */
        public boolean isMatchFromDepart(UserForm uf, List listMatches);

        /**
         * �õ����е��û�
         *
         * @return
         * @throws MatchDaoSysException
         */
        public List getAllUser() throws MatchDaoSysException;
}
