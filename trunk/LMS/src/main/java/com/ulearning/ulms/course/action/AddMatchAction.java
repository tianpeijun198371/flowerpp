/**
 * addMatchAction.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.common.match.dao.MatchDAO;
import com.ulearning.ulms.common.match.dao.MatchDAOFactory;
import com.ulearning.ulms.common.match.helper.MatchHelper;
import com.ulearning.ulms.common.match.model.MatchModel;
import com.ulearning.ulms.common.match.util.MatchConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddMatchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.debug("course", "[addMatchAction]===========start");

                List availableMatchList_former = (List) request.getSession()
                        .getAttribute(MatchConstants.AVAILABLE_MATCH_LIST_SESSION_KEY);
                List availableMatchList_later = new ArrayList();

                MatchModel mm = (MatchModel) form;
                int relationID = mm.getRelationID();
                int type = mm.getType();
                String[] ss = null;

                try
                {
                        ss = (String[]) request.getParameterValues("available");
                        LogUtil.debug("course",
                                "[addMatchAction]===========size=" + ss.length);

                        MatchModel mm1 = null;

                        for (int i = 0; i < ss.length; i++)
                        {
                                mm1 = new MatchModel();
                                mm1.setAspID(mm.getAspID());
                                mm1.setRelationID(mm.getRelationID());
                                mm1.setType(mm.getType());
                                LogUtil.debug("course",
                                        "[addMatchAction]===========ss i=" + ss[i]);
                                parse(mm1, ss[i]);
                                availableMatchList_later.add(new Integer(mm1.getMatchID()));

                                LogUtil.debug("course",
                                        "[addMatchAction]===========@@will update...");
                                MatchHelper.update(mm1);
                        }

                        //delete the match
                        int matchID;
                        MatchModel mm2 = null;

                        for (int i = 0; i < availableMatchList_former.size(); i++)
                        {
                                mm2 = (MatchModel) availableMatchList_former.get(i);
                                matchID = mm2.getMatchID();
                                LogUtil.debug("course",
                                        "[addMatchAction]===========##matchID=" + matchID);
                                LogUtil.debug("course",
                                        "[addMatchAction]===========##(!availableMatchList_later.contains(new Integer(matchID)))=" +
                                                (!availableMatchList_later.contains(new Integer(matchID))));

                                if (!availableMatchList_later.contains(new Integer(matchID)))
                                {
                                        MatchHelper.delete(matchID, relationID, type);
                                }
                        }
                }
                catch (NullPointerException ne)
                {
                        //没有设置匹配规则，或者要删除所有
                        MatchHelper.deleteAll(relationID, type);
                }

                //to match
                String isNowMatch = request.getParameter("isNowMatch");

                if (isNowMatch.equals("true"))
                {
                        MatchHelper.match(relationID, type);
                }

                LogUtil.debug("course",
                        "[addMatchAction]===========resultScreen = " + resultScreen);

                return mapping.findForward(resultScreen);
        }

        //分解字符串
        private void parse(MatchModel mm, String str)
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
                mm.setMatchID(StringUtil.parseInt(matchID));

                matchValue = (String) l.get(1);
                LogUtil.debug("course",
                        "[addMatchAction]parse===========matchValue=" + matchValue);
                mm.setMatchValue(matchValue);

                operator = (String) l.get(2);
                LogUtil.debug("course",
                        "[addMatchAction]parse===========operator=" + operator);
                mm.setOperator(operator);
        }
}
