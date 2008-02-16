/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-24
 * Time: 16:26:42
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.user.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.EJBUtil;
import com.ulearning.ulms.user.util.UserDbInput;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.ejb.UserHome;
import com.ulearning.ulms.user.ejb.User;
import com.ulearning.ulms.user.exceptions.UserSystemException;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.tools.upload.model.BatchInputUploadForm;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;
import com.ulearning.ulms.finance.helper.UserAccountHelper;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;

import java.util.List;

public class AddUserInChargeAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                BatchInputUploadForm biuf = (BatchInputUploadForm) form;

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
                                LogUtil.debug("user", "[AddUserInBatchAction] Exeception====================" + e);
                        }
                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                String fileName = "";
                                String file_path = "";
                                String rootPath = Config.getUploadPhysicalPath();
                                UserDbInput userDbInput = new UserDbInput();
                                List userFormList = null;
                                List userFormList1 = null;
                                UserForm uf = null;

                                //get absolutely path of file want to input
                                file_path = biuf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");

                                file_path = rootPath + file_path + fileName;

                                //check file of want to input
                                String returnString = "";
                                // String returnString1 = "";
                                try
                                {
                                        returnString = userDbInput.checkExcel(file_path, request);
                                        // returnString1 = userDbInput.checkCharge(file_path,request);
                                        //System.out.println("===================returnString ================== " + returnString);
                                        //System.out.println("returnString1 ================== " + returnString1);
                                }
                                catch (UserSystemException e)
                                {
                                        e.printStackTrace();
                                }
                                if (returnString.equals(""))
                                {
                                        try
                                        {
                                                userFormList = userDbInput.insertIntoDb(file_path);
                                                for (int i = 0; i < userFormList.size(); i++)
                                                {
                                                        uf = (UserForm) userFormList.get(i);
                                                        int userID = 0;
                                                        if (uf.getCatalogID() != -1)
                                                        {
                                                                userID = UserHelper.addUser(uf);
                                                                UserAccountDetailForm saveFrm = new UserAccountDetailForm();
                                                                saveFrm.setUaDetailDate(DateTimeUtil.parseDate(saveFrm.getAssDate()));//将从表单收到的字符形式的日期转换后使用
                                                                UserAccountHelper help = new UserAccountHelper();
                                                                saveFrm.setUaDetailInOutType(1);
                                                                saveFrm.setUaDetailAmount(StringUtil.parseFloat(userDbInput.chargeInsert(file_path).get(i).toString()));
                                                                saveFrm.setUaDetailUserName(uf.getName());
                                                                //如果导入用户成功即导入经费
                                                                if (userID != 0)
                                                                {
                                                                        saveFrm.setUserID(userID);
                                                                        help.insert(saveFrm);
                                                                }

                                                        }
                                                        else
                                                        {
                                                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO, " 用户导入模板的单位编号与系统存在的单位编号不一致，请检查单位编号!");
                                                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                                                        }
                                                }
                                        }
                                        catch (UserSystemException e)
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
                MultipartParamUtils mp = new MultipartParamUtils(request, 1024 * 1014 * 10);
                request.setAttribute("mp", mp);
                request.setAttribute("orgID", biuf.getOrgID());
                return mapping.findForward(resultScreen);
        }
}
