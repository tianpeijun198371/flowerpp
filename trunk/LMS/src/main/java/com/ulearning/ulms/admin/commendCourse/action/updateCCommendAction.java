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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class updateCCommendAction extends UploadAction
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

                                LogUtil.debug("UpdateFilePath", file_path);
                                ccf.setDesc0(file_path + fileName);
                        }
                }

                String beginTimeValue = ccf.getBeginTimeValue();
                Date beginDateTime = new Date();

                if ((beginTimeValue != null) && !beginTimeValue.equals(""))
                {
                        beginDateTime = DateTimeUtil.parseDate(beginTimeValue);
                        ccf.setBeginTime(beginDateTime);
                }

                String endTimeValue = ccf.getEndTimeValue();
                Date endDateTime = new Date();

                if ((endTimeValue != null) && !endTimeValue.equals(""))
                {
                        endDateTime = DateTimeUtil.parseDate(endTimeValue);
                        ccf.setEndTime(endDateTime);
                }

                try
                {
                        CCommendDAO dao = CCommendDAOFactory.getDAO();

                        //恢复以前的数据
                        CCommendForm newForm = dao.getCCommend(ccf.getCcourseID());

                        if (ccf.getPublishTime() == null)
                        {
                                ccf.setPublishTime(newForm.getPublishTime());
                        }

                        if (ccf.getDesc1() == null)
                        {
                                ccf.setDesc1(newForm.getDesc1());
                        }

                        if (ccf.getDescription() == null)
                        {
                                ccf.setDescription(newForm.getDescription());
                        }

                        if (String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                ccf.setDesc0(newForm.getDesc0());
                        }

                        LogUtil.info("system",
                                "[AddCCommendAction]========================add ccommend, name = " +
                                        ccf.getName());
                        dao.updateCCommend(ccf);
                }
                catch (CCommendDAOSysException e)
                {
                        e.printStackTrace();
                }

                LogUtil.info("system", "[AddCCommendAction]========================End");

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
