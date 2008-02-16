/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-12-7 15:36:14
 */
package com.ulearning.ulms.familyeducation.action;

import com.ulearning.ulms.familyeducation.form.FamilyEducationTeacherProfileForm;
import com.ulearning.ulms.familyeducation.helper.FamilyEducationHelper;
import com.ulearning.ulms.familyeducation.model.FamilyEducationTeacherProfileModel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FamilyEducationTeacherProfileAction extends DispatchAction
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
        {
                return super.execute(actionMapping, actionForm, request, response);
        }

        public ActionForward addFamilyEducationTeacherProfile(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String result = "FamilyEducationTeacherProfile_list";
                FamilyEducationTeacherProfileForm familyEducationTeacherProfileForm = (FamilyEducationTeacherProfileForm) actionForm;

                FamilyEducationTeacherProfileModel model = new FamilyEducationTeacherProfileModel();
                BeanUtils.copyProperties(model, familyEducationTeacherProfileForm);
                FamilyEducationHelper.addFamilyEducationTeacherProfile(model);

                String info = "申请做家教信息已发布，我们会尽快联系您！";
                request.setAttribute("info", info);

                return actionMapping.findForward(result);
        }

        public ActionForward updateFamilyEducationTeacherProfile(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String result = "FamilyEducationTeacherProfile_list";
                FamilyEducationTeacherProfileForm familyEducationTeacherProfileForm = (FamilyEducationTeacherProfileForm) actionForm;

                FamilyEducationTeacherProfileModel model = new FamilyEducationTeacherProfileModel();
                BeanUtils.copyProperties(model, familyEducationTeacherProfileForm);
                FamilyEducationHelper.updateFamilyEducationTeacherProfile(model);

                String info = "修改成功！";
                request.setAttribute("info", info);

                return actionMapping.findForward(result);
        }

        public ActionForward deleteFamilyEducationTeacherProfile(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String result = "FamilyEducationTeacherProfile_list";
                String[] ids = request.getParameterValues("id");
                int[] ids_int = new int[ids.length];

                for (int i = 0; i < ids.length; i++)
                {
                        String id = ids[i];
                        ids_int[i] = NumberUtils.toInt(id);
                }

                FamilyEducationHelper.deleteFamilyEducationTeacherProfile(ids_int);

                String info = "删除成功！";
                request.setAttribute("info", info);

                return actionMapping.findForward(result);
        }
}
