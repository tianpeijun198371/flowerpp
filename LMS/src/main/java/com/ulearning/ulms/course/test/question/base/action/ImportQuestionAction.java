/**
 * Created by IntelliJ IDEA.
 * Base: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.base.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.question.base.bean.baseConstants;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.util.BatchImportQuestionUtil;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImportQuestionAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                LogUtil.info("yangds", "ImportQuestionAction===========start");

                String resultScreen = "success";
                BaseForm bf = (BaseForm) form;

                String courseID = request.getParameter("courseID");
                String parentID = request.getParameter("parentID");
                LogUtil.info("yangds",
                        "ImportQuestionAction===========courseID=" + courseID);

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        //upload file to system
                        try
                        {
                                UploadForm uf = bf.getUploadForm();
                                executeUpload(mapping, uf, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("user",
                                        "[AddUserInBatchAction] Exeception====================" +
                                                e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                String fileName = "";
                                String file_path = "";
                                String rootPath = Config.getUploadPhysicalPath();
                                //                                BatchImportMasterUserUtil batchImportMasterUserUtil = new BatchImportMasterUserUtil();
                                //                                List userFormList = null;
                                //                                UserForm uf = null;
                                //
                                //get absolutely path of file want to input
                                file_path = bf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");

                                file_path = rootPath + file_path + fileName;
                                LogUtil.info("yangds",
                                        "ImportQuestionAction===========file_path=" + file_path);

                                //check file of want to input
                                String returnString = "";
                                BatchImportQuestionUtil iqu = new BatchImportQuestionUtil();
                                returnString = iqu.checkExcel(file_path);

                                if (returnString.equals(""))
                                {
                                        //                                        UserHome home = EJBUtil.getUserHome();
                                        //User user = home.create();
                                        //insert user info from Execel file
                                        try
                                        {
                                                iqu.insertIntoDb(file_path, parentID, courseID);
                                        }
                                        catch (Exception e)
                                        {
                                                e.printStackTrace();
                                        }

                                        resultScreen = "success";
                                }
                                else
                                {
                                        request.setAttribute("CheckInfo", returnString);
                                        resultScreen = "batch.Input.check.error";
                                }
                        }
                }

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);
                request.setAttribute("courseID", courseID);

                return mapping.findForward(resultScreen);
        }
}
