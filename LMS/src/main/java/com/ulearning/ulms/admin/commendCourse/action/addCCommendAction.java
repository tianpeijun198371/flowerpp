/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.commendCourse.action;

import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAO;
import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAOFactory;
import com.ulearning.ulms.admin.commendCourse.exceptions.CCommendDAOSysException;
import com.ulearning.ulms.admin.commendCourse.form.CCommendForm;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class addCCommendAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.info("system",
                        "[AddCCommendAction]========================Begin");

                CCommendForm ccf = (CCommendForm) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                LogUtil.info("UpdateFilePath=====================",
                                        "I am in here !!!");

                                UploadForm uploadForm = ccf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[AddplanAction] Exeception====================" + e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                String file_path = "";
                                String fileName = "";
                                String rootPath = Config.getUploadVirtualPath();
                                file_path = ccf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");
                                file_path = rootPath + file_path + fileName;
                                LogUtil.info("UpdateFilePath=====================", file_path);
                                ccf.setDesc0(ccf.getFilePath() + fileName);
                        }
                }

                //处理时间
                String beginTimeValue = ccf.getBeginTimeValue();
                Date beginDateTime = new Date();

                if ((beginTimeValue != null) && !beginTimeValue.equals(""))
                {
                        beginDateTime = DateTimeUtil.parseDate(beginTimeValue);
                        ccf.setBeginTime(beginDateTime);
                }

                String endTimeValue = ccf.getEndTimeValue();
                System.out.println("endTimeValue ===============" + endTimeValue);

                Date endDateTime = new Date();

                if ((endTimeValue != null) && !endTimeValue.equals(""))
                {
                        endDateTime = DateTimeUtil.parseDate(endTimeValue);
                        ccf.setEndTime(endDateTime);
                }

                ccf.setPublishTime(new Date());

                try
                {
                        CCommendDAO dao = CCommendDAOFactory.getDAO();
                        LogUtil.info("system",
                                "[AddCCommendAction]========================add ccommend, name = " +
                                        ccf.getName());
                        dao.addCCommend(ccf);
                }
                catch (CCommendDAOSysException e)
                {
                        e.printStackTrace();
                }
                request.setAttribute("returnPortal", "returnPortal");
                request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "注册成功，请等待审批！");
                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                //LogUtil.info("system", "[AddCCommendAction]========================End");
                // Forward to result page
               // return mapping.findForward(resultScreen);
        }
}
