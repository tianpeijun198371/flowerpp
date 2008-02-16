/**
 * MatchModelDao.java.
 * User: zhangy Date: 2005-6-6 14:09:41
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.dao;

import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.match.form.MatchClassForm;
import com.ulearning.ulms.match.model.MatchModel;
import com.ulearning.ulms.user.form.UserForm;

import java.util.List;


public interface MatchModelDao
{
        /**
         * ���� AddMatchNameAction �������� M_MatchModel_Tab ��д������
         *
         * @param matchClassForm
         * @throws com.ulearning.ulms.match.exceptions.MatchDaoSysException
         *
         */
        public void addMatchClass(MatchClassForm matchClassForm)
                throws MatchDaoSysException;

        /**
         * ���ݿγ̡���䣬ɾ�� M_MatchModel_Tab ������Ӧ�����ݣ�Ϊȷ��һ�ſγ�ֻ��ʹ��һ��ƥ�����
         *
         * @param matchid
         * @param modeleid
         * @throws MatchDaoSysException
         */
        public void delMatchClass(int matchid, int modeleid)
                throws MatchDaoSysException;

        /**
         * ���� AddMatchNameAction ������ɾ�� M_MatchModel_Tab ����
         *
         * @param modeleid
         * @throws MatchDaoSysException
         */
        public void delMatchClassByModeleid(int modeleid)
                throws MatchDaoSysException;

        /**
         * ���� AddMatchNameAction �������޸� M_MatchModel_Tab ��������
         *
         * @throws MatchDaoSysException
         */
        public void updateMatchClass(MatchClassForm matchClassForm)
                throws MatchDaoSysException;

        /**
         * �� matchid �õ� M_MatchModel_Tab ���е���ص�����
         *
         * @param matchid
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchClassList(int matchid) throws MatchDaoSysException;

        /**
         * �� AspID �õ� M_MatchModel_Tab ���е���ص�����
         *
         * @param aspID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchClassListAsp(int aspID) throws MatchDaoSysException;

        /**
         * Ϊ�γ�����û�
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public void addCoursUser(UserForm userForm, int relationID, int type)
                throws MatchDaoSysException;

        /**
         * ͨ�� UserForm ���� ���������еĿγ̣��ж��ſγ��Ƿ�ʹ���Զ�ƥ�����
         * ���Ѹ��û�����Ŀγ̵��С�
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public void addCoursUser(UserForm userForm, int aspID)
                throws MatchDaoSysException;

        /**
         * �жϽ�Ҫ��ӵ��û��Ƿ��Ѿ����ڱ��γ���
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public boolean isCoursUser(UserForm userForm, int relationID, int type)
                throws MatchDaoSysException;

        /**
         * �� modeleid���γ̱�ţ� �жϳ��� M_MatchModel_Tab �����Ƿ����
         *
         * @param modeleid
         * @param matchid
         * @return
         */
        public boolean isMatchByMatchClass(int modeleid, int matchid);
}
