/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-6
 * Time: 15:55:56
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.action;

import com.ulearning.ulms.admin.webconfig.webcustom.bean.CustomTypeConstants;
import com.ulearning.ulms.admin.webconfig.webcustom.bean.WebCustomHelper;
import com.ulearning.ulms.admin.webconfig.webcustom.form.WebCustomForm;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.BatchInputUploadForm;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ModifyAspParaAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                BatchInputUploadForm biuf = (BatchInputUploadForm) form;

                String aspID = (String) request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY);
                String organID = (String) request.getSession()
                        .getAttribute(LMSConstants.USER_ORGID_KEY);

                if (aspID == null)
                {
                        aspID = "1";
                }

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);

                String type = biuf.getType();

                if (type.equals("1"))
                {
                        if (request.getContentType().startsWith("multipart/form-data"))
                        {
                                //upload file
                                try
                                {
                                        UploadForm uploadForm = biuf.getUploadForm();
                                        executeUpload(mapping, uploadForm, request, response);
                                }
                                catch (Exception e)
                                {
                                        LogUtil.debug("WebCustom",
                                                "[ModifyCustom] Exeception====================" + e);
                                }

                                if (!String.valueOf(request.getAttribute("size")).equals("0"))
                                {
                                        String file_path = "";
                                        String fileName = "";
                                        String rootPath = Config.getUploadVirtualPath();

                                        //get absolutely path of file that want to input
                                        file_path = biuf.getFilePath();
                                        fileName = (String) request.getAttribute("newFileName");
                                        file_path = rootPath + file_path + fileName;

                                        LogUtil.debug("UpdateFilePath", file_path);

                                        WebCustomHelper helper = new WebCustomHelper();
                                        WebCustomForm wcf = helper.getLastWebCustom(Integer.parseInt(
                                                aspID), CustomTypeConstants.CUSTOM_ASP_LOG_TYPE);

                                        if (wcf == null)
                                        {
                                                //insert into a new record
                                                wcf = new WebCustomForm();
                                                wcf.setRelationID(Integer.parseInt(aspID));
                                                wcf.setRelationType(CustomTypeConstants.CUSTOM_ASP_LOG_TYPE);
                                                wcf.setConfigTypeName(fileName);
                                                WebCustomHelper.addWebCustom(wcf);
                                        }
                                        else
                                        {
                                                //modify this record
                                                wcf.setConfigTypeName(fileName);
                                                WebCustomHelper.updateWebCustom(wcf);
                                        }
                                }

                                //modify aspName
                                String aspName = mp.getParameter("name");
                                LogUtil.debug("aspName =", aspName);

                                OrganForm of = OrganHelper.getOrgan(Integer.parseInt(organID));
                                of.setOrgName(aspName);

                                OrganDAO organDao = OrganDAOFactory.getDAO();
                                organDao.updateOrgan(of);
                        }
                }
                else if (type.equals("0"))
                {
                        WebCustomHelper helper = new WebCustomHelper();
                        WebCustomForm wcf = helper.getLastWebCustom(Integer.parseInt(aspID),
                                CustomTypeConstants.CUSTOM_ASP_LOG_TYPE);

                        if (wcf != null)
                        {
                                int customID = wcf.getCustomID();
                                helper.removeCustom(customID);
                        }
                }

                return mapping.findForward(resultScreen);
        }
}
