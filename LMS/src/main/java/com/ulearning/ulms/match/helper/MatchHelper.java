/**
 * MatchHelper.java.
 * User: zhangy Date: 2005-6-10 9:30:51
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.helper;

import com.ulearning.ulms.match.dao.MatchDaoFactory;
import com.ulearning.ulms.match.dao.MatchModelDaoFactory;
import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.match.model.MatchModel;
import com.ulearning.ulms.user.form.UserForm;

import java.util.ArrayList;
import java.util.List;


public class MatchHelper
{
        /**
         * ͨ�� UserForm ���� ���������еĿγ̣��ж��ſγ��Ƿ�ʹ���Զ�ƥ�����
         * ���Ѹ��û�����Ŀγ̵��С�
         *
         * @param userForm
         */
        public static void addCoursUser(UserForm userForm, int aspID)
                throws MatchDaoSysException
        {
                try
                {
                        MatchModelDaoFactory.getDAO().addCoursUser(userForm, aspID);
                }
                catch (MatchDaoSysException e)
                {
                        throw new MatchDaoSysException("[MatchHelper]addCoursUser=========" +
                                e);
                }
        }

        /**
         * �� modeleid���γ̱�ţ� �жϳ��� M_MatchModel_Tab �����Ƿ����
         *
         * @param modeleid
         * @param matchid
         * @return
         * @throws MatchDaoSysException
         */
        public boolean isMatchByMatchClass(int modeleid, int matchid)
                throws MatchDaoSysException
        {
                boolean isMatchByClass = false;

                try
                {
                        isMatchByClass = MatchModelDaoFactory.getDAO()
                                .isMatchByMatchClass(modeleid,
                                        matchid);
                }
                catch (MatchDaoSysException e)
                {
                        throw new MatchDaoSysException("[MatchHelper]addCoursUser=========" +
                                e);
                }

                return isMatchByClass;
        }

        /**
         * �� modelID ����� M_Match_Tab (ƥ�����������)���������
         *
         * @param modeleid
         * @return
         * @throws MatchDaoSysException
         */
        public static MatchModel getMatchModel(int modeleid)
                throws MatchDaoSysException
        {
                MatchModel matchModel = new MatchModel();

                try
                {
                        matchModel = MatchDaoFactory.getDAO().getMatchModel(modeleid);
                }
                catch (MatchDaoSysException e)
                {
                        throw new MatchDaoSysException("[MatchHelper]addCoursUser=========" +
                                e);
                }

                return matchModel;
        }

        /**
         * �õ����е��û�
         *
         * @return
         * @throws MatchDaoSysException
         */
        public static List getAllUser() throws MatchDaoSysException
        {
                List list = new ArrayList();

                try
                {
                        list = MatchDaoFactory.getDAO().getAllUser();
                }
                catch (MatchDaoSysException e)
                {
                        throw new MatchDaoSysException("[MatchHelper]addCoursUser=========" +
                                e);
                }

                return list;
        }
}
