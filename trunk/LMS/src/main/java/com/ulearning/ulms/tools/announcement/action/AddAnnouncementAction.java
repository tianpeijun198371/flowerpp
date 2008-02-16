package com.ulearning.ulms.tools.announcement.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.announcement.dao.AnnouncementDAO;
import com.ulearning.ulms.tools.announcement.dao.AnnouncementDAOFactory;
import com.ulearning.ulms.tools.announcement.model.Announcement;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddAnnouncementAction extends UploadAction
{
        public AddAnnouncementAction()
        {
        }

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.info("system", "[AddAnnouncementAction]===========Begin");

                Announcement acf = (Announcement) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                com.ulearning.ulms.tools.upload.model.UploadForm uploadForm = acf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[AddAnnouncementAction] Exeception====================" +
                                                e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                acf.setLink(acf.getFilePath() +
                                        request.getAttribute("newFileName"));
                        }
                        else
                        {
                                acf.setLink("");
                        }
                }

                AnnouncementDAO dao = AnnouncementDAOFactory.getDAO();
                //　去掉subject中不合法字符
                acf.setSubject(StringUtil.filterByHTMLTag(acf.getSubject()));
                acf.setCreateDate(new Date());
                acf.setModifyDate(new Date());

                if (!acf.getBeginDate().equals(""))
                {
                        String[] tmp = StringUtil.splitString(acf.getBeginDate(), "-");
                        acf.setDisplayBeginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "0", "0", "0"));
                }

                if (!acf.getEndDate().equals(""))
                {
                        String[] tmp = StringUtil.splitString(acf.getEndDate(), "-");
                        acf.setDisplayEndDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "23", "59", "59"));
                }

                dao.insert(acf);
                LogUtil.info("system",
                        "[AddAnnouncementAction]===========resultScreen = " + resultScreen);

                MultipartParamUtils mp = new MultipartParamUtils(request, 0x9e7000);
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
