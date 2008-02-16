/**
 * ContentManageAction.java.
 * User: fengch   Date: 2007-11-12 13:25:59
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.tools.meeting.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.ValidateUtil;
import com.ulearning.ulms.tools.meeting.form.MeetingForm;
import com.ulearning.ulms.tools.meeting.helper.MeetingHelper;
import com.ulearning.ulms.tools.meeting.model.MeetingModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MeetingAction  extends DispatchAction
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
        {
                return super.execute(actionMapping, actionForm, request,
                        response);
        }

        public ActionForward addMeeting(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String result = "meeting_list";
                MeetingForm meetingForm = (MeetingForm) actionForm;

                meetingForm.setStartTime(DateTimeUtil.toDateYMDHM(meetingForm.getStartTimeStr()));
                meetingForm.setEndTime(DateTimeUtil.toDateYMDHM(meetingForm.getEndTimeStr()));
                MeetingModel meetModel=new MeetingModel();
                BeanUtils.copyProperties(meetModel,meetingForm);
                MeetingHelper.addMeeting(meetModel);

                String info="新建成功！";
                request.getSession().setAttribute("info", info);

                String view=StringUtils.trimToEmpty(request.getParameter("view"));
                int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?pager.offset=" + pageOffset +
                        "&relationID=" + meetingForm.getRelationID()+
                        "&type=" + meetingForm.getType()+
                        "&meetingType=" + meetingForm.getMeetingType()+
                         "&view=" + view);

                return new ActionForward(bf.toString());
        }
                                        
        public ActionForward updateMeeting(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String result = "meeting_list";
                MeetingForm meetingForm = (MeetingForm) actionForm;
                meetingForm.setStartTime(DateTimeUtil.toDateYMDHM(meetingForm.getStartTimeStr()));
                meetingForm.setEndTime(DateTimeUtil.toDateYMDHM(meetingForm.getEndTimeStr()));
                
                MeetingModel meetModel=new MeetingModel();
                BeanUtils.copyProperties(meetModel,meetingForm);
                MeetingHelper.updateMeeting(meetingForm.getId(),meetingForm.getName(),meetingForm.getSummary(),
                        meetingForm.getCameras(),meetingForm.getCapacity(),meetingForm.getCompereCode(),
                        meetingForm.getStartTime(),meetingForm.getEndTime(),
                        meetingForm.getFixed());

                String info="修改成功！";
                request.getSession().setAttribute("info", info);

                String view=StringUtils.trimToEmpty(request.getParameter("view"));
                int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));

                ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf=null;

                if(ValidateUtil.isTrue(request.getParameter("isReturnAuditList")))
                {
                        bf = new StringBuffer(actionMapping.findForward("meeting_audit").getPath());
                }
                else
                {
                      bf = new StringBuffer(forward.getPath());
                }
                bf.append("?pager.offset=" + pageOffset +
                        "&relationID=" + meetingForm.getRelationID()+
                        "&type=" + meetingForm.getType()+
                        "&meetingType=" + meetingForm.getMeetingType()+
                         "&view=" + view);

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward deleteMeeting(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String result = "meeting_list";
                String[] ids=request.getParameterValues("id");
                int[] ids_int=new int[ids.length];
                for (int i = 0; i < ids.length; i++)
                {
                        String id = ids[i];
                        ids_int[i]= NumberUtils.toInt(id);
                }

                MeetingHelper.deleteMeeting(ids_int);
                String info="删除成功！";
                request.getSession().setAttribute("info", info);

                String view=StringUtils.trimToEmpty(request.getParameter("view"));
                int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                int relationID = NumberUtils.toInt(request.getParameter("relationID"));
                int type = NumberUtils.toInt(request.getParameter("type"));
                int meetingType= NumberUtils.toInt(request.getParameter("meetingType"));

                ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf=null;
                if(ValidateUtil.isTrue(request.getParameter("isReturnAuditList")))
                {
                        bf = new StringBuffer(actionMapping.findForward("meeting_audit").getPath());
                }
                else
                {
                      bf = new StringBuffer(forward.getPath());
                }
                bf.append("?pager.offset=" + pageOffset +
                        "&relationID=" + relationID+
                        "&type=" + type+
                        "&meetingType=" + meetingType+
                         "&view=" + view);

                return new ActionForward(bf.toString(), true);
        }

        public ActionForward auditMeetings(
                ActionMapping actionMapping, ActionForm actionForm,
                HttpServletRequest request,
                HttpServletResponse response) throws Exception
        {
                String result = "meeting_audit";
                String[] ids=request.getParameterValues("id");
                int[] ids_int=new int[ids.length];
                for (int i = 0; i < ids.length; i++)
                {
                        String id = ids[i];
                        ids_int[i]= NumberUtils.toInt(id);
                }
                 String view=StringUtils.trimToEmpty(request.getParameter("view"));
                int auditStatus = NumberUtils.toInt(request.getParameter("auditStatus"));
                int primaryServer = NumberUtils.toInt(request.getParameter("primaryServer"));
                String services = StringUtils.trimToEmpty(request.getParameter("services"));
                String fixed = StringUtils.trimToEmpty(request.getParameter("fixed"));
                MeetingHelper.auditMeetings(ids_int,services,primaryServer,auditStatus,fixed);

                String info="审核成功！";
                request.getSession().setAttribute("info", info);

                int pageOffset = NumberUtils.toInt(request.getParameter("pager.offset"));
                int relationID = NumberUtils.toInt(request.getParameter("relationID"));
                int type = NumberUtils.toInt(request.getParameter("type"));
                int meetingType= NumberUtils.toInt(request.getParameter("meetingType"));
                ActionForward forward = actionMapping.findForward(result);
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?pager.offset=" + pageOffset +
                        "&relationID=" + relationID+
                        "&type=" + type+
                        "&meetingType=" + meetingType+
                        "&view=" + view);

                return new ActionForward(bf.toString(), true);
        }
}