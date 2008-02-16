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
         * 接受AddMatchNameAction参数，向M_Match_Tab 表写入数据
         *
         * @param matchForm
         * @throws MatchDaoSysException
         */
        public void addMatch(MatchForm matchForm) throws MatchDaoSysException;

        /**
         * 接受AddMatchNameAction参数，删除M_Match_Tab表中数据
         *
         * @param matchid
         * @throws MatchDaoSysException
         */
        public void delMatch(int matchid) throws MatchDaoSysException;

        /**
         * 接受AddMatchNameAction 参数，由 name 得到 M_Match_Tab 表的 MatchID
         *
         * @param name
         * @return
         * @throws MatchDaoSysException
         */
        public int getMatchID(String name) throws MatchDaoSysException;

        /**
         * 由 orgID 查出与 M_Match_Tab (匹配策略描述表)相符的数据
         *
         * @param orgID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatch(int orgID) throws MatchDaoSysException;

        /**
         * 由 modelID 查出与 M_Match_Tab (匹配策略描述表)相符的数据
         *
         * @param modelID
         * @return
         * @throws MatchDaoSysException
         */
        public MatchModel getMatchModel(int modelID) throws MatchDaoSysException;

        /**
         * 检查 将要插入的 name 与M_Match_Tab表中的是否有重复，
         * 如果有返回false，否则返回true
         *
         * @param name
         * @return
         * @throws MatchDaoSysException
         */
        public boolean getBooleanMatchName(String name) throws MatchDaoSysException;

        /**
         * 检查 将要插入的 name 与M_Match_Tab表中的是否有重复，
         * 如果有返回false，否则返回true
         *
         * @param matchid
         * @return
         * @throws MatchDaoSysException
         */
        public boolean getBooleanMatchName1(int matchid, String name)
                throws MatchDaoSysException;

        /**
         * 接受AddMatchNameAction参数，修改 M_MatchIterm_Tab 和 M_MatchIterm_Tab 表中数据
         *
         * @param matchForm
         * @throws MatchDaoSysException
         */
        public void updateMatch(MatchForm matchForm) throws MatchDaoSysException;

        /**
         * 得到与 M_Match_Tab 表中 RelationID 相符的用户的 UserForm
         *
         * @param orgID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchUsersFromDepart(int orgID, int matchid)
                throws MatchDaoSysException;

        /**
         * 判断用户是否与 M_Match_Tab 的规则相符,符合返回 true ,否则返回 false
         *
         * @param uf
         * @param listMatches
         * @return
         */
        public boolean isMatchFromDepart(UserForm uf, List listMatches);

        /**
         * 得到所有的用户
         *
         * @return
         * @throws MatchDaoSysException
         */
        public List getAllUser() throws MatchDaoSysException;
}
