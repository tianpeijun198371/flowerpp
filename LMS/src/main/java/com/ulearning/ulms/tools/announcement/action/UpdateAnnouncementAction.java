/**
 * Created by IntelliJ IDEA.
 * Announcement: dengj
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
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


public class UpdateAnnouncementAction extends UploadAction
{
        public UpdateAnnouncementAction()
        {
        }

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                Announcement uf = (Announcement) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                com.ulearning.ulms.tools.upload.model.UploadForm uploadForm = uf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[UpdateAnnouncementAction] Exeception====================" +
                                                e);
                        }

                        if (uf.getLink().equals(""))
                        {
                                if (!String.valueOf(request.getAttribute("size")).equals("0"))
                                {
                                        uf.setLink(uf.getFilePath() +
                                                request.getAttribute("newFileName"));
                                }
                                else
                                {
                                        uf.setLink("");
                                }
                        }
                }

                AnnouncementDAO dao = AnnouncementDAOFactory.getDAO();
                //　去掉subject中不合法字符
                uf.setSubject(StringUtil.filterByHTMLTag(uf.getSubject()));
                uf.setCreateDate(new Date());
                uf.setModifyDate(new Date());

                if (!uf.getBeginDate().equals(""))
                {
                        String[] tmp = StringUtil.splitString(uf.getBeginDate(), "-");
                        uf.setDisplayBeginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "0", "0", "0"));
                }

                if (!uf.getEndDate().equals(""))
                {
                        String[] tmp = StringUtil.splitString(uf.getEndDate(), "-");
                        uf.setDisplayEndDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "23", "59", "59"));
                }

                dao.update(uf);
                LogUtil.info("Announcement",
                        "[UpdateAnnouncementAction]===========resultScreen = " +
                                resultScreen);

                MultipartParamUtils mp = new MultipartParamUtils(request, 0x9e7000);
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
