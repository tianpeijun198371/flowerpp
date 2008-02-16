/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-12-7 15:28:02
 */
package com.ulearning.ulms.familyeducation.action;

import com.ulearning.ulms.familyeducation.form.FamilyEducationInfoForm;
import com.ulearning.ulms.familyeducation.helper.FamilyEducationHelper;
import com.ulearning.ulms.familyeducation.model.FamilyEducationInfoModel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FamilyEducationInfoAction extends DispatchAction
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
        {
                return super.execute(actionMapping, actionForm, request, response);
        }

        public ActionForward addFamilyEducationInfo(ActionMapping actionMapping,
                                                    ActionForm actionForm, HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception
        {
                String result = "FamilyEducationInfo_list";
                FamilyEducationInfoForm familyEducationInfoForm = (FamilyEducationInfoForm) actionForm;

                FamilyEducationInfoModel model = new FamilyEducationInfoModel();
                BeanUtils.copyProperties(model, familyEducationInfoForm);
                FamilyEducationHelper.addFamilyEducationInfo(model);

                String info = "发布做家教信息成功！我们会尽快联系您！";
                request.setAttribute("info", info);

                return actionMapping.findForward(result);
        }

        public ActionForward updateFamilyEducationInfo(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String result = "FamilyEducationInfo_list";
                FamilyEducationInfoForm familyEducationInfoForm = (FamilyEducationInfoForm) actionForm;

                FamilyEducationInfoModel model = new FamilyEducationInfoModel();
                BeanUtils.copyProperties(model, familyEducationInfoForm);
                FamilyEducationHelper.updateFamilyEducationInfo(model);
                String info = "修改成功！";
                request.getSession().setAttribute("info", info);
                return actionMapping.findForward(result);
        }

        public ActionForward deleteFamilyEducationInfo(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String result = "FamilyEducationInfo_list";
                String[] ids = request.getParameterValues("id");
                int[] ids_int = new int[ids.length];

                for (int i = 0; i < ids.length; i++)
                {
                        String id = ids[i];
                        ids_int[i] = NumberUtils.toInt(id);
                }

                FamilyEducationHelper.deleteFamilyEducationInfo(ids_int);

                String info = "删除成功！";
                request.getSession().setAttribute("info", info);

                return actionMapping.findForward(result);
        }
}
