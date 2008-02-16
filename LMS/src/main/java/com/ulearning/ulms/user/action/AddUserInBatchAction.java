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
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.tools.upload.model.BatchInputUploadForm;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;

import java.util.List;

public class AddUserInBatchAction extends UploadAction
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
                                UserForm uf = null;

                                //get absolutely path of file want to input
                                file_path = biuf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");

                                file_path = rootPath + file_path + fileName;

                                //check file of want to input
                                String returnString = "";
                                try
                                {
                                        returnString = userDbInput.checkExcel(file_path, request);
                                }
                                catch (UserSystemException e)
                                {
                                        e.printStackTrace();
                                }
                                if (returnString.equals(""))
                                {
                                        /*UserHome home = EJBUtil.getUserHome();
                                        User user = home.create();*/
                                        //insert user info from Execel file
                                        try
                                        {
                                                userFormList = userDbInput.insertIntoDb(file_path);
                                                for (int i = 0; i < userFormList.size(); i++)
                                                {
                                                        uf = (UserForm) userFormList.get(i);
                                                        //To use user EJB insert a user to system
                                                        //int userID = user.addUser(uf);

                                                        //Avoid to using EJB to add user
                                                        int userID = UserHelper.addUser(uf);

                                                        // System.out.println("[AddUserAction]===========use ejb to add a account " + uf.getLoginName());
                                                        LogUtil.debug("user", "[AddUserBatchAction]===========use ejb to add a account " + userID);
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
