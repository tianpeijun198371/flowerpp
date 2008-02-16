/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-12-7 15:37:16
 */
package com.ulearning.ulms.familyeducation.action;

import com.ulearning.ulms.evaluate.form.EFeedBackForm;
import com.ulearning.ulms.evaluate.model.FeedBackModel;
import com.ulearning.ulms.familyeducation.helper.FamilyEducationHelper;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OnLineReservationAction extends DispatchAction
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
        {
                return super.execute(actionMapping, actionForm, request, response);
        }

        public ActionForward addFeedBack(ActionMapping actionMapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception
        {
                String result = "OnLineReservation_list";
                EFeedBackForm feedBackForm = (EFeedBackForm) actionForm;

                FeedBackModel model = new FeedBackModel();
                BeanUtils.copyProperties(model, feedBackForm);
                FamilyEducationHelper.addOnLineReservation(model);

                String info = "在线申请信息已发布，我们会尽快联系您！！";
                request.setAttribute("info", info);

                return actionMapping.findForward(result);
        }

        public ActionForward updateFeedBack(ActionMapping actionMapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception
        {
                String result = "OnLineReservation_list";
                EFeedBackForm feedBackForm = (EFeedBackForm) actionForm;

                FeedBackModel model = new FeedBackModel();
                BeanUtils.copyProperties(model, feedBackForm);
                FamilyEducationHelper.updateOnLineReservation(model);

                String info = "修改成功！";
                request.getSession().setAttribute("info", info);

                return actionMapping.findForward(result);
        }

        public ActionForward deleteFeedBack(ActionMapping actionMapping,
                                            ActionForm actionForm, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception
        {
                String result = "OnLineReservation_list";
                String[] ids = request.getParameterValues("id");
                int[] ids_int = new int[ids.length];

                for (int i = 0; i < ids.length; i++)
                {
                        String id = ids[i];
                        ids_int[i] = NumberUtils.toInt(id);
                }

                FamilyEducationHelper.deleteOnLineReservation(ids_int);

                String info = "删除成功！";
                request.getSession().setAttribute("info", info);

                return actionMapping.findForward(result);
        }
}
