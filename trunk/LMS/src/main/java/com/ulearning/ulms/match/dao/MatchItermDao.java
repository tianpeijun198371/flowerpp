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
         * 接受AddMatchNameAction参数，向 M_MatchIterm_Tab 表写入数据
         *
         * @param matchItermForm
         * @throws com.ulearning.ulms.match.exceptions.MatchDaoSysException
         *
         */
        public void addMatchIterm(MatchItermForm matchItermForm)
                throws MatchDaoSysException;

        /**
         * 接受AddMatchNameAction参数，删除M_Match_Tab、M_MatchIterm_Tab表中数据
         *
         * @param matchid
         * @throws MatchDaoSysException
         */
        public void delMatchIterm(int matchid) throws MatchDaoSysException;

        /**
         * 给出 M_MatchIterm_Tab (匹配策略和匹配项的关联描述表)的字段
         *
         * @param matchID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchIterm(int matchID) throws MatchDaoSysException;

        /**
         * 得到　c_match_tab　中的 MatchKey
         *
         * @param ItemID
         * @return
         * @throws MatchDaoSysException
         */
        public String getMatchItermName(int ItemID) throws MatchDaoSysException;

        /**
         * 得到 ItermModel
         *
         * @param ItemID
         * @return
         * @throws MatchDaoSysException
         */
        public ItermModel getMatchItermForm(int ItemID) throws MatchDaoSysException;
}
