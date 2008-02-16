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
         * 通过 UserForm 查找 本部门所有的课程，有多门课程是否使用自动匹配规则，
         * 并把该用户加入的课程当中。
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
         * 由 modeleid（课程编号） 判断出在 M_MatchModel_Tab 表中是否存在
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
         * 由 modelID 查出与 M_Match_Tab (匹配策略描述表)相符的数据
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
         * 得到所有的用户
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
