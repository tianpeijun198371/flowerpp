/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-30
 * Time: 10:32:26
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.MasterModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.util.MasterDbImport;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.BatchInputUploadForm;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BatchImportMasterAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success_master";
                BatchInputUploadForm biuf = (BatchInputUploadForm) form;

                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet()
                        .getServletContext();
                String userID = (String) session.getAttribute(LMSConstants.USERID_KEY);
                String aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                String orgID = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);
                String operateType = biuf.getType();

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        //upload file to system
                        try
                        {
                                UploadForm uploadForm = biuf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("user",
                                        "[AddUserInBatchAction] Exeception====================" +
                                                e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                String file_path = "";
                                String fileName = "";
                                String rootPath = Config.getUploadPhysicalPath();

                                MasterDbImport masterDbImport = new MasterDbImport();

                                //get absolutely path of file that want to input
                                file_path = biuf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");

                                file_path = rootPath + file_path + fileName;
                                DebugUtil.print("[Batch Master]================ file_path = " +
                                        file_path);

                                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                                {
                                        aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                                }

                                /*TreeControl control = null;
                                TreeControl controlInContext = null;
                                String images = "course.gif";
                                DebugUtil.print("[Batch Mater]================== Begin");
                                DebugUtil.print("[Batch Mater]================== operateType = " + operateType);
                                if(Integer.parseInt(operateType) == SecurityConstants.USER_CERTIFICATE_RELATION)
                                {
                                        DebugUtil.print("[Batch Mater]================== certificate");
                                        if(session.getAttribute(LMSConstants.TREE_CERT) != null)
                                        session.removeAttribute(LMSConstants.TREE_CERT);
                                        if(servletContext.getAttribute(LMSConstants.TREE_CERT+aspID) != null)
                                        servletContext.removeAttribute(LMSConstants.TREE_CERT+aspID);
                                }
                                else if(Integer.parseInt(operateType) == SecurityConstants.USER_COURSE_RELATION)
                                {
                                        DebugUtil.print("[Batch Mater]================== course");
                                        if(session.getAttribute(LMSConstants.TREE_COURSE) != null)
                                        session.removeAttribute(LMSConstants.TREE_COURSE);
                                        if(servletContext.getAttribute(LMSConstants.TREE_COURSE+aspID) != null)
                                        servletContext.removeAttribute(LMSConstants.TREE_COURSE+aspID);
                                }*/
                                String returnString = "";

                                try
                                {
                                        returnString = masterDbImport.checkExcel(file_path,
                                                operateType, request);
                                }
                                catch (CourseSysException e)
                                {
                                        e.printStackTrace();
                                }

                                DebugUtil.print(
                                        "[Batch Mater]================== returnString = " +
                                                returnString);

                                //insert database
                                if (returnString.equals(""))
                                {
                                        MasterDAO masterDao = MasterDAOFactory.getDAO();
                                        MasterModel mm = null;

                                        try
                                        {
                                                List masterModelList = masterDbImport.insertIntoDb(file_path,
                                                        operateType);

                                                for (int i = 0; i < masterModelList.size(); i++)
                                                {
                                                        mm = (MasterModel) masterModelList.get(i);

                                                        if (userID != null)
                                                        {
                                                                mm.setCreator(Integer.parseInt(userID));
                                                                mm.setAspID(Integer.parseInt(aspID));
                                                                mm.setOrgID(Integer.parseInt(orgID));
                                                                mm.setEstablishDate(new Date());
                                                        }
                                                        else
                                                        {
                                                                mm.setCreator(1);
                                                                mm.setOrgID(1);
                                                                mm.setAspID(1);
                                                        }

                                                        int masterID = masterDao.createMaster(mm);
                                                }
                                        }
                                        catch (CourseSysException e)
                                        {
                                                e.printStackTrace();
                                        }

                                        if (operateType.equals(String.valueOf(
                                                SecurityConstants.USER_CERTIFICATE_RELATION)))
                                        {
                                                resultScreen = "success_cert";
                                        }
                                        else
                                        {
                                                resultScreen = "success_master";
                                        }
                                }
                                else
                                {
                                        request.setAttribute("CheckInfo", returnString);
                                        resultScreen = "batch.Input.check.error";
                                }
                        }
                }

                //¸üÐÂÊ÷
                TreeFlush.flush(servletContext, session, Integer.parseInt(orgID), null);

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
