/**
 * MatchItermDaoImpl.java.
 * User: zhangy Date: 2005-6-6 14:09:06
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.match.form.MatchItermForm;
import com.ulearning.ulms.match.model.ItermModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;


public class MatchItermDaoImpl implements MatchItermDao
{
        /**
         * 接受AddMatchNameAction参数，向 M_MatchIterm_Tab 表写入数据
         *
         * @param mim
         * @throws com.ulearning.ulms.match.exceptions.MatchDaoSysException
         *
         */
        public void addMatchIterm(MatchItermForm mim) throws MatchDaoSysException
        {
                try
                {
                        HibernateDAO.add(mim.getMatchItermModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'addMatchIterm throw====" + e);
                }
        }

        /**
         * 接受AddMatchNameAction参数，删除M_MatchIterm_Tab表中数据
         *
         * @param matchid
         * @throws MatchDaoSysException
         */
        public void delMatchIterm(int matchid) throws MatchDaoSysException
        {
                String sqlMatchIterm = " from MatchItermModel where MATCHID=" +
                        matchid;

                try
                {
                        HibernateDAO.delete(sqlMatchIterm);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'delMatchIterm throw====" + e);
                }
        }

        /**
         * 给出 M_MatchIterm_Tab (匹配策略和匹配项的关联描述表)的字段
         *
         * @param matchID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchIterm(int matchID) throws MatchDaoSysException
        {
                List list = new ArrayList();
                String sql = " from MatchItermModel where MATCHID=" + matchID;

                try
                {
                        list = HibernateDAO.find(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'getMatchIterm throw====" + e);
                }

                return list;
        }

        /**
         * 得到　c_match_tab　中的 MatchKey
         *
         * @param ItemID
         * @return
         * @throws MatchDaoSysException
         */
        public String getMatchItermName(int ItemID) throws MatchDaoSysException
        {
                String matchKey = "";
                List list = new ArrayList();
                String sql = "from ItermModel where ITERMID=" + ItemID;

                try
                {
                        LogUtil.debug("common",
                                "[MatchDaoImpl]getMatchClassList--sql==" + sql);
                        list = HibernateDAO.find(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'updateMatchClass throw====" + e.getMessage());
                }

                matchKey = ((ItermModel) list.get(0)).getMatchkey();

                return matchKey;
        }

        /**
         * 得到 ItermModel
         *
         * @param ItemID
         * @return
         * @throws MatchDaoSysException
         */
        public ItermModel getMatchItermForm(int ItemID) throws MatchDaoSysException
        {
                ItermModel matchKey = new ItermModel();
                List list = new ArrayList();
                String sql = "from ItermModel where ITERMID=" + ItemID;

                try
                {
                        LogUtil.debug("common",
                                "[MatchDaoImpl]getMatchClassList--sql==" + sql);
                        list = HibernateDAO.find(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'updateMatchClass throw====" + e.getMessage());
                }

                matchKey = (ItermModel) list.get(0);

                return matchKey;
        }
}
