/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-28
 * Time: 17:05:58
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.organ.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.organ.exceptions.OrganSysException;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.organ.form.OrganJieFo;
import com.ulearning.ulms.organ.util.OrgDbImport;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.BatchInputUploadForm;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BatchImportOrgAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                BatchInputUploadForm biuf = (BatchInputUploadForm) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
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
                                String fileName = "";
                                String file_path = "";
                                String rootPath = Config.getUploadPhysicalPath();
                                OrgDbImport orgDbImport = new OrgDbImport();
                                List orgList = null;

                                //get absolutely path of file that want to input
                                file_path = biuf.getFilePath();
                                fileName = (String) request.getAttribute("newFileName");
                                file_path = rootPath + file_path + fileName;

                                System.out.println("file_path = " + file_path);

                                //check file want to input
                                String returnString = "";

                                try
                                {
                                        returnString = orgDbImport.checkExcel(file_path);
                                }
                                catch (OrganSysException e)
                                {
                                        e.printStackTrace();
                                }

                                //insert into db
                                if (returnString.equals(""))
                                {
                                        OrganDAO dao = OrganDAOFactory.getDAO();

                                        try
                                        {
                                                orgList = orgDbImport.insertIntoDb(file_path);

                                                for (int i = 0; i < orgList.size(); i++)
                                                {
                                                        OrganForm of = (OrganForm) orgList.get(i);

                                                        if (of.getParentID() == -1)
                                                        {
                                                                String[] temp = new String[2];
                                                                int parentID = 0;
                                                                int aspID = 1;
                                                                List descriptions = StringUtil.split(of.getDescription(),
                                                                        "/!@#$%/");

                                                                of.setDescription((String) descriptions.get(0));

                                                                String parentOrganNo = (String) descriptions.get(1);
                                                                parentID = OrganHelper.getOrgIDByCode(parentOrganNo);

                                                                OrganForm of1 = null;
                                                                of1 = OrganHelper.getOrgan(parentID);

                                                                if (of1 != null)
                                                                {
                                                                        aspID = of1.getAspID();
                                                                }

                                                                LogUtil.debug("inner description = ",
                                                                        (String) descriptions.get(0));
                                                                LogUtil.debug("inner parentOrganNo= ",
                                                                        (String) descriptions.get(1));
                                                                LogUtil.debug("inner aspID = ", "" + aspID);
                                                                of.setParentID(parentID);
                                                                of.setAspID(aspID);
                                                        }

                                                        dao.addOrgan(of);

                                                        if (Config.getIsIntegrateJieFo())
                                                        {
                                                                try
                                                                {
                                                                        if (of.getIsAsp() == 1)
                                                                        {
                                                                                OrganJieFo ojf = new OrganJieFo();
                                                                                ojf.setOrganID(of.getOrgID());
                                                                                ojf.setOrganName(of.getOrgName());
                                                                                ojf.setOrganBrief(of.getDescription());
                                                                                dao.addJieFoOrgan(ojf);
                                                                        }
                                                                }
                                                                catch (Exception e)
                                                                {
                                                                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                                                                }
                                                        }

                                                        LogUtil.debug("Organ",
                                                                "[BatchImportOrganAction]===========add a organ");
                                                }
                                        }
                                        catch (OrganSysException e)
                                        {
                                                e.printStackTrace();
                                        }

                                        resultScreen = "success";
                                }
                                else //forward to check error  page
                                {
                                        request.setAttribute("CheckInfo", returnString);
                                        resultScreen = "batch.Input.check.error";
                                }
                        }
                }

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
