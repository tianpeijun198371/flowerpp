/**
 * MatchDaoImpl.java.
 * User: zhangy Date: 2005-6-3 9:22:11
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.dao;

import com.ulearning.ulms.common.match.util.MatchConstants;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.match.form.MatchForm;
import com.ulearning.ulms.match.form.MatchItermForm;
import com.ulearning.ulms.match.model.MatchItermModel;
import com.ulearning.ulms.match.model.MatchModel;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MatchDaoImpl implements MatchDao
{
        /**
         * 接受AddMatchNameAction参数，向M_Match_Tab 表写入数据
         *
         * @param mm
         * @throws MatchDaoSysException
         */
        public void addMatch(MatchForm mm) throws MatchDaoSysException
        {
                try
                {
                        HibernateDAO.add(mm.getMatchModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException("MatchDaoImpl'addMatch throw====" +
                                e);
                }
        }

        /**
         * 接受AddMatchNameAction参数，删除M_Match_Tab表中数据
         *
         * @param matchid
         * @throws MatchDaoSysException
         */
        public void delMatch(int matchid) throws MatchDaoSysException
        {
                String sqlMatch = " from MatchModel where MATCHID=" + matchid;

                try
                {
                        HibernateDAO.delete(sqlMatch);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException("MatchDaoImpl'delMatch throw====" +
                                e);
                }
        }

        /**
         * 接受AddMatchNameAction参数，修改 M_Match_Tab 表中数据
         *
         * @param matchForm
         * @throws MatchDaoSysException
         */
        public void updateMatch(MatchForm matchForm) throws MatchDaoSysException
        {
                try
                {
                        HibernateDAO.update(matchForm.getMatchModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException("MatchDaoImpl'updateMatch throw====" +
                                e);
                }
        }

        /**
         * 接受AddMatchNameAction 参数，得到 M_Match_Tab 表的 MatchID
         *
         * @param Name
         * @return
         * @throws MatchDaoSysException
         */
        public int getMatchID(String Name) throws MatchDaoSysException
        {
                int matchid = 0;
                String sql = " from MatchModel where NAME='" + Name + "'";
                List list = new ArrayList();

                try
                {
                        list = HibernateDAO.find(sql);

                        MatchModel mm = (MatchModel) list.get(0);
                        matchid = mm.getMatchid();
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException("MatchDaoImpl'getMatchID throw====" +
                                e);
                }

                return matchid;
        }

        /**
         * 由 orgID 查出与 M_Match_Tab (匹配策略描述表)相符的数据
         *
         * @param orgID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatch(int orgID) throws MatchDaoSysException
        {
                List list = new ArrayList();
                String sql = " from MatchModel where RELATIONID=" + orgID;

                try
                {
                        list = HibernateDAO.find(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException("MatchDaoImpl'getMatch throw====" +
                                e);
                }

                return list;
        }

        /**
         * 由 modelID 查出与 M_Match_Tab (匹配策略描述表)相符的数据
         *
         * @param modelID
         * @return
         * @throws MatchDaoSysException
         */
        public MatchModel getMatchModel(int modelID) throws MatchDaoSysException
        {
                List list = new ArrayList();
                List matchModelList = new ArrayList();
                MatchModel mm = new MatchModel();
                String sql = " from MatchModel mm,MatchClassModel mcm where mm.matchid=mcm.comp_id.matchid and mcm.comp_id.modeleid=" +
                        modelID;

                try
                {
                        list = HibernateDAO.find(sql);

                        Object[] record = null;

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                matchModelList.add(record[0]);
                        }

                        if ((list != null) && (list.size() > 0))
                        {
                                mm = (MatchModel) matchModelList.get(0);
                        }
                        else
                        {
                                mm.setName("");
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException("MatchDaoImpl'getMatch throw====" +
                                e);
                }

                return mm;
        }

        /**
         * 接受AddMatchNameAction参数，删除M_Match_Tab、M_MatchIterm_Tab表中数据
         *
         * @param matchid
         * @throws MatchDaoSysException
         */
        public void delMatchAndMatchIterm(int matchid) throws MatchDaoSysException
        {
                try
                {
                        this.delMatch(matchid);
                        MatchItermDaoFactory.getDAO().delMatchIterm(matchid);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'delMatchAndMatchIterm throw====" + e);
                }
        }

        /**
         * 检查 将要插入的 name 与M_Match_Tab表中的是否有重复，
         * 如果有返回false，否则返回true
         *
         * @param name
         * @return
         * @throws MatchDaoSysException
         */
        public boolean getBooleanMatchName(String name) throws MatchDaoSysException
        {
                boolean matchid = true;
                String sql = " from MatchModel where NAME='" + name + "'";
                List list = new ArrayList();

                try
                {
                        list = HibernateDAO.find(sql);

                        if (list.size() > 0)
                        {
                                matchid = false;
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'getBooleanMatchName throw====" + e);
                }

                return matchid;
        }

        /**
         * 检查 将要插入的 name 与M_Match_Tab表中的是否有重复，
         * 如果有返回false，否则返回true
         *
         * @param matchid
         * @return
         * @throws MatchDaoSysException
         */
        public boolean getBooleanMatchName1(int matchid, String name)
                throws MatchDaoSysException
        {
                boolean matchTrue = true;
                String sql = " from MatchModel where NAME='" + name +
                        "' and MATCHID not in(" + matchid + ")";
                List list = new ArrayList();

                try
                {
                        list = HibernateDAO.find(sql);

                        if (list.size() > 0)
                        {
                                matchTrue = false;
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'getBooleanMatchName throw====" + e);
                }

                return matchTrue;
        }

        /**
         * 得到与 M_Match_Tab 表中 RelationID 相符的用户的 UserForm
         *
         * @param orgID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchUsersFromDepart(int orgID, int matchid)
                throws MatchDaoSysException
        {
                List listUsers = new ArrayList();
                List listUsers1 = UserHelper.getAllOrganUser(orgID);

                for (int i = 0; i < listUsers1.size(); i++)
                {
                        UserForm uf = (UserForm) listUsers1.get(i);

                        try
                        {
                                if (isMatchFromDepart(uf,
                                        MatchItermDaoFactory.getDAO().getMatchIterm(matchid)))
                                {
                                        LogUtil.debug("common",
                                                "[MatchDaoImpl]getMatchUsersFromDepart--userID==" +
                                                        uf.getUserID());
                                        listUsers.add(uf);
                                }
                        }
                        catch (MatchDaoSysException e)
                        {
                                e.printStackTrace();
                                throw new MatchDaoSysException(
                                        "[MatchDaoImpl]getMatchUsersFromDepart throw====" + e);
                        }
                }

                return listUsers;
        }

        /**
         * 判断用户是否与 M_Match_Tab 的规则相符,符合返回 true ,否则返回 false
         *
         * @param uf
         * @param listMatches
         * @return
         */
        public boolean isMatchFromDepart(UserForm uf, List listMatches)
        {
                MatchItermForm mm = null;
                String mv = null;
                boolean isRoleMatch = true;
                boolean isSexMatch = true;
                boolean isEduLevelMatch = true;
                boolean isPositionMatch = true;
                boolean isAspMatch = true;

                for (int i = 0; i < listMatches.size(); i++)
                {
                        mm = new MatchItermForm((MatchItermModel) listMatches.get(i));

                        if (mm.getItermid() == MatchConstants.MATCH_SEX)
                        { //系统内之匹配规则-'性别'
                                mv = mm.getMatchvalue();

                                if ((mv.equals("男")) || mv.equals("male"))
                                {
                                        mv = MatchConstants.MATCH_SEX_MALE;
                                }
                                else
                                {
                                        mv = MatchConstants.MATCH_SEX_FEMALE;
                                }

                                if (!uf.getSex().equals(mv))
                                {
                                        isSexMatch = false;
                                }
                        }
                        else if (mm.getItermid() == MatchConstants.MATCH_ROLE)
                        { //系统内之匹配规则-'角色'
                                mv = mm.getMatchvalue();
                                //todo
                                isRoleMatch = true;
                        }
                        else if (mm.getItermid() == MatchConstants.MATCH_POSITION)
                        { //系统内之匹配规则-''职务''
                                mv = mm.getMatchvalue();

                                if (!uf.getDesc1().equals(mv))
                                {
                                        isPositionMatch = false;
                                }
                        }
                        else if (mm.getItermid() == MatchConstants.MATCH_EDULEVEL)
                        { //系统内之匹配规则-'教育程度'
                                mv = mm.getMatchvalue();

                                if (!uf.getEduLevel().equals(mv))
                                {
                                        isEduLevelMatch = false;
                                }
                        }
                        else if (mm.getItermid() == MatchConstants.MATCH_DEPART)
                        { //系统内之匹配规则-'部门'

                                if (uf.getCatalogID() != OrganHelper.getOrgID(
                                        mm.getMatchvalue()))
                                {
                                        isAspMatch = false;
                                }
                        }
                }

                return isRoleMatch && isSexMatch && isEduLevelMatch && isPositionMatch &&
                        isAspMatch;
        }

        /**
         * 得到所有的用户
         *
         * @return
         * @throws MatchDaoSysException
         */
        public List getAllUser() throws MatchDaoSysException
        {
                List list = new ArrayList();
                String sql = " from UserModel ";

                try
                {
                        list = HibernateDAO.find(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException("MatchDaoImpl'getMatch throw====" +
                                e);
                }

                return list;
        }
}
