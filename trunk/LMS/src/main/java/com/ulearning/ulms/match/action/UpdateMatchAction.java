/**
 * UpdateMatchAction.java.
 * User: zhangy Date: 2005-6-2 15:27:30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseCodeRepeatedException;
import com.ulearning.ulms.match.dao.MatchDao;
import com.ulearning.ulms.match.dao.MatchDaoFactory;
import com.ulearning.ulms.match.dao.MatchItermDao;
import com.ulearning.ulms.match.dao.MatchItermDaoFactory;
import com.ulearning.ulms.match.form.MatchForm;
import com.ulearning.ulms.match.form.MatchItermForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateMatchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.debug("course", "[addMatchAction]===========start");

                int matchid = Integer.parseInt(request.getParameter("matchid"));

                MatchForm mf = new MatchForm();
                mf.setMatchid(matchid);
                mf.setRelationid(Integer.parseInt(request.getParameter("aspID")));
                mf.setName(request.getParameter("matchTitle"));

                MatchDao md = MatchDaoFactory.getDAO();

                if (md.getBooleanMatchName1(matchid, request.getParameter("matchTitle")))
                {
                        md.updateMatch(mf);
                }
                else
                {
                        LogUtil.debug("course", "[AddMatchAction]==========匹配规则的名称重复");
                        throw new CourseCodeRepeatedException("该匹配规则的名称已经存在");
                }

                MatchItermForm matchItermForm = new MatchItermForm();
                MatchItermDao mid = MatchItermDaoFactory.getDAO();
                mid.delMatchIterm(matchid);
                matchItermForm.setMatchid(matchid);

                String[] ss = null;

                try
                {
                        ss = (String[]) request.getParameterValues("available");

                        for (int i = 0; i < ss.length; i++)
                        {
                                parse(matchItermForm, ss[i]);
                                mid.addMatchIterm(matchItermForm);
                        }
                }
                catch (NullPointerException ne)
                {
                        ne.printStackTrace();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                LogUtil.debug("course",
                        "[addMatchAction]===========resultScreen = " + resultScreen);

                return mapping.findForward(resultScreen);
        }

        //分解字符串
        private void parse(MatchItermForm mm, String str)
        {
                //matchID/matchValue/operator
                String matchID = null;
                String matchValue = null;
                String operator = null;

                List l = StringUtil.split(str, "/");

                LogUtil.debug("course",
                        "[addMatchAction]parse===========l.size()=" + l.size());

                matchID = (String) l.get(0);
                LogUtil.debug("course",
                        "[addMatchAction]parse===========matchID=" + matchID);
                mm.setItermid(StringUtil.parseInt(matchID));

                matchValue = (String) l.get(1);
                LogUtil.debug("course",
                        "[addMatchAction]parse===========matchValue=" + matchValue);
                mm.setMatchvalue(matchValue);

                operator = (String) l.get(2);
                LogUtil.debug("course",
                        "[addMatchAction]parse===========operator=" + operator);
                mm.setOperator(operator);
        }
}
