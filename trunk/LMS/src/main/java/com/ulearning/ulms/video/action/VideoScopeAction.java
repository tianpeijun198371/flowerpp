/**
 * Created by IntelliJ IDEA.
 * User: liaoxx
 * Date: 2007-1-17
 * Time: 9:58:44
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.video.action;

import com.ulearning.ulms.video.dao.VideoScopeDAO;
import com.ulearning.ulms.video.dao.VideoScopeDAOFactory;
import com.ulearning.ulms.video.form.VideoScopeForm;
import com.ulearning.ulms.video.model.*;
import com.ulearning.ulms.video.helper.VideoScopeHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.LinkedList;

public class VideoScopeAction extends DispatchAction
{
        public ActionForward addvideoscope(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response)
                throws Exception
        {
                String vclassid = request.getParameter("vclassID");
                String[] userIDList = request.getParameterValues("userIDList");
                String[] orgIDList = request.getParameterValues("orgIDList");
                String type = request.getParameter("type");
                List orgUserList = new LinkedList();
                List orgAllList = new LinkedList();
                VideoScopeDAO dao = VideoScopeDAOFactory.getDAO();
                VideoScopeHelper videoScopeHelper = new VideoScopeHelper();
                VideoOrganModel eom = new VideoOrganModel();
                if (orgIDList != null && orgIDList.length > 0)
                {
                        for (int i = 0; i < orgIDList.length; i++)
                        {
                                List tmporgChildList = OrganHelper.getOrganChildList(Integer.parseInt(orgIDList[i]));
                                tmporgChildList.add(new Integer(orgIDList[i]));
                                orgAllList.addAll(tmporgChildList);
                        }
                }
                if (orgAllList.size() > 0)
                {
                        for (int i = 0; i < orgAllList.size(); i++)
                        {
                                String orgID = orgAllList.get(i).toString();
                                List tmpUserList = UserHelper.getUserList(orgID);
                                orgUserList.addAll(tmpUserList);
                                //唯一性判断
                                int listSize = videoScopeHelper.getVideoOrg(Integer.parseInt(vclassid), Integer.parseInt(orgID), Integer.parseInt(type)).size();
                                if (listSize < 1)
                                {
                                        VideoOrganModelPK eompk = new VideoOrganModelPK(Integer.parseInt(vclassid), Integer.parseInt(orgID), type);
                                        eom.setComp_id(eompk);
                                        dao.addVideoOrg(eom);
                                }
                        }
                }
                //从搜索用户加人
                VideouserModel eum = new VideouserModel();
                if (userIDList != null && userIDList.length > 0)
                {
                        for (int j = 0; j < userIDList.length; j++)
                        {
                                int listSize = videoScopeHelper.getVideoUser(Integer.parseInt(vclassid), Integer.parseInt(userIDList[j]), Integer.parseInt(type)).size();
                                if (listSize < 1 && Integer.parseInt(request.getParameter("userID")) != Integer.parseInt(userIDList[j]))
                                {
                                        VideouserModelPK eumpk = new VideouserModelPK(Integer.parseInt(vclassid), Integer.parseInt(userIDList[j]), type);
                                        eum.setComp_id(eumpk);
                                        dao.addVideoUser(eum);
                                }
                        }
                }
                //从机构加人
                System.out.println("orgUserList========================" + orgUserList.size());
                if (orgUserList.size() > 0)
                {
                        for (int i = 0; i < orgUserList.size(); i++)
                        {
                                int userID = ((UserForm) orgUserList.get(i)).getUserID();
                                int listSize = videoScopeHelper.getVideoUser(Integer.parseInt(vclassid), userID, Integer.parseInt(type)).size();
                                if (listSize < 1 && userID != Integer.parseInt(request.getParameter("userID")))
                                {
                                        System.out.println("userID = ----------------------------------------------------------------------------- ========================================" + userID);
                                        VideouserModelPK eumpk = new VideouserModelPK(Integer.parseInt(vclassid), userID, type);
                                        eum.setComp_id(eumpk);
                                        dao.addVideoUser(eum);
                                }
                        }
                }
                System.out.println("Integer.parseInt------------------------------------------------------------------------------------ = " + Integer.parseInt(request.getParameter("userID")));
                int listSize = videoScopeHelper.getVideoUser(Integer.parseInt(vclassid), Integer.parseInt(request.getParameter("userID")), Integer.parseInt(type)).size();
                if (listSize < 1)
                {
                        VideouserModelPK eumpk = new VideouserModelPK(Integer.parseInt(vclassid), Integer.parseInt(request.getParameter("userID")), type);
                        eum.setComp_id(eumpk);
                        dao.addVideoUser(eum);
                }
                return mapping.findForward("addvideoscope");
        }

        public ActionForward addvideo(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                throws Exception
        {
                VideoScopeForm pf = (VideoScopeForm) form;
                VideoScopeDAO dao = VideoScopeDAOFactory.getDAO();
                int vclassID = dao.addVideo(pf.getVideoModel());
                request.setAttribute("vclassID", String.valueOf(vclassID));
                System.out.println("vclassID = " + vclassID);
                return mapping.findForward("addvideo");
        }

        public ActionForward updatevideo(ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response)
                throws Exception
        {
                VideoScopeForm pf = (VideoScopeForm) form;
                VideoScopeDAO dao = VideoScopeDAOFactory.getDAO();
                dao.updateVideo(pf.getVideoModel());
                String vclassID = request.getParameter("vclassID");
                System.out.println("vclassID = " + vclassID);
                request.setAttribute("vclassID", vclassID);
                return mapping.findForward("updatevideo");
        }

        public ActionForward delvideo(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                throws Exception
        {
                VideoScopeForm pf = (VideoScopeForm) form;
                String[] vclassID = request.getParameterValues("vclassID");
                VideoScopeDAO dao = VideoScopeDAOFactory.getDAO();
                for (int i = 0; i < vclassID.length; i++)
                {
                        dao.delVideo(Integer.parseInt(vclassID[i]), Integer.parseInt(pf.getType()));
                }
                return mapping.findForward("delvideo");
        }

        public ActionForward delvideoscope(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response)
                throws Exception
        {
                VideoScopeForm pf = (VideoScopeForm) form;
                String[] userID = request.getParameterValues("userID");
                String[] orgID = request.getParameterValues("orgID");
                String type = request.getParameter("type");
                System.out.println("type ================= " + type);
                System.out.println("pf.getvclassid() ========== " + pf.getvclassid());
                VideoScopeDAO dao = VideoScopeDAOFactory.getDAO();
                if (orgID != null && orgID.length > 0)
                {
                        for (int i = 0; i < orgID.length; i++)
                        {
                                dao.delVideoOrgbyorgid(pf.getvclassid(), Integer.parseInt(orgID[i]), Integer.parseInt(type));
                        }
                }
                VideouserModel eum = new VideouserModel();
                if (userID != null && userID.length > 0)
                {
                        for (int j = 0; j < userID.length; j++)
                        {
                                System.out.println("userID = " + userID[j]);
                                dao.delVideoUserbyuserid(pf.getvclassid(), Integer.parseInt(userID[j]), Integer.parseInt(type));
                        }
                }
                return mapping.findForward("delvideoscope");
        }
}
