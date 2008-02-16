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
         * 接受 AddMatchNameAction 参数，向 M_MatchModel_Tab 表写入数据
         *
         * @param matchClassForm
         * @throws com.ulearning.ulms.match.exceptions.MatchDaoSysException
         *
         */
        public void addMatchClass(MatchClassForm matchClassForm)
                throws MatchDaoSysException;

        /**
         * 根据课程　ｉｄ，删除 M_MatchModel_Tab 表中相应的数据，为确保一门课程只能使用一项匹配规则。
         *
         * @param matchid
         * @param modeleid
         * @throws MatchDaoSysException
         */
        public void delMatchClass(int matchid, int modeleid)
                throws MatchDaoSysException;

        /**
         * 接受 AddMatchNameAction 参数，删除 M_MatchModel_Tab 表中
         *
         * @param modeleid
         * @throws MatchDaoSysException
         */
        public void delMatchClassByModeleid(int modeleid)
                throws MatchDaoSysException;

        /**
         * 接受 AddMatchNameAction 参数，修改 M_MatchModel_Tab 表中数据
         *
         * @throws MatchDaoSysException
         */
        public void updateMatchClass(MatchClassForm matchClassForm)
                throws MatchDaoSysException;

        /**
         * 由 matchid 得到 M_MatchModel_Tab 表中的相关的数据
         *
         * @param matchid
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchClassList(int matchid) throws MatchDaoSysException;

        /**
         * 由 AspID 得到 M_MatchModel_Tab 表中的相关的数据
         *
         * @param aspID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchClassListAsp(int aspID) throws MatchDaoSysException;

        /**
         * 为课程添加用户
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public void addCoursUser(UserForm userForm, int relationID, int type)
                throws MatchDaoSysException;

        /**
         * 通过 UserForm 查找 本部门所有的课程，有多门课程是否使用自动匹配规则，
         * 并把该用户加入的课程当中。
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public void addCoursUser(UserForm userForm, int aspID)
                throws MatchDaoSysException;

        /**
         * 判断将要添加的用户是否已经存在本课程中
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public boolean isCoursUser(UserForm userForm, int relationID, int type)
                throws MatchDaoSysException;

        /**
         * 由 modeleid（课程编号） 判断出在 M_MatchModel_Tab 表中是否存在
         *
         * @param modeleid
         * @param matchid
         * @return
         */
        public boolean isMatchByMatchClass(int modeleid, int matchid);
}
