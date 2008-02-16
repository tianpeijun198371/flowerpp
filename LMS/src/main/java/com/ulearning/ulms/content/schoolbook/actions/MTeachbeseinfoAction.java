package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.Globe;
import com.ulearning.ulms.content.schoolbook.exceptions.ActionSchoolbookException;
import com.ulearning.ulms.content.schoolbook.form.TeachbeseinfoForm;
import com.ulearning.ulms.content.schoolbook.model.MTeachbeseinfoTab;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachbeseinfoTabDAO;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachsortDAO;
import com.ulearning.ulms.content.schoolbook.util.ImgUploadHandler;
import com.ulearning.ulms.content.schoolbook.util.PageWrapper;
import com.ulearning.ulms.content.schoolbook.util.UrlProperty;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA. User: suh Date: 2006-4-17 Time: 9:45:03 To change
 * this template use File | Settings | File Templates.
 */
public class MTeachbeseinfoAction extends DispatchAction
{
        private static final Log log = LogFactory.getLog(MTeachbeseinfoAction.class);
        private static final MTeachbeseinfoTabDAO dao = MTeachbeseinfoTabDAO.getInstance();
        private static final MTeachsortDAO mtdao = MTeachsortDAO.getInstance();

        public ActionForward add(ActionMapping actionMapping,
                                 ActionForm actionForm, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception
        {
                log.debug("inside add!!");

                TeachbeseinfoForm tf = (TeachbeseinfoForm) actionForm;

                if (tf.getBsifFile().getFileSize() > 0)
                {
                        String filename = ImgUploadHandler.getFileName(tf.getBsifFile()
                                .getFileName());
                        ImgUploadHandler.handlerFiles(tf.getBsifFile().getInputStream(),
                                filename);

                        String address = ImgUploadHandler.getContentPath() + filename;
                        tf.setBsifcover(address);
                }

                MTeachbeseinfoTab mt = new MTeachbeseinfoTab();
                BeanUtils.copyProperties(mt, tf);

                //debug bean
                Map m = BeanUtils.describe(mt);
                Iterator it = m.keySet().iterator();

                while (it.hasNext())
                {
                        String key = (String) it.next();
                        System.out.print("[MTeachbeseinfoAction]:" + key + "=" +
                                m.get(key) + "\n");
                }

                dao.save(mt);

                return actionMapping.findForward("add");
        }

        public ActionForward del(ActionMapping actionMapping,
                                 ActionForm actionForm, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception
        {
                log.debug("inside delete!!");

                String[] ids = null;
                ids = request.getParameterValues("id");

                if ((ids != null) || (ids.length > 0))
                {
                        if (mtdao.isExist(ids))
                        {
                                request.setAttribute(Globe.PAGE_MESSAGE,
                                        "请重新选择，部分教材基本信息已入库，暂时不能删除！！");

                                return actionMapping.findForward("message");
                        }

                        for (int i = 0; i < ids.length; i++)
                        {
                                dao.delete(Long.parseLong(ids[i]));
                        }
                }

                return actionMapping.findForward("del");
        }

        public ActionForward modify(ActionMapping actionMapping,
                                    ActionForm actionForm, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception
        {
                log.debug("inside modify!!");

                TeachbeseinfoForm tf = (TeachbeseinfoForm) actionForm;

                if ((tf.getBsifid() != null) || (tf.getBsifid().intValue() > 0))
                {
                        if (mtdao.isExist(tf.getBsifid().intValue()))
                        {
                                request.setAttribute(Globe.PAGE_MESSAGE, "此教材基本信息已入库，暂时不能修改！！");

                                return actionMapping.findForward("message");
                        }

                        if (tf.getBsifFile().getFileSize() > 0)
                        {
                                String filename = ImgUploadHandler.getFileName(tf.getBsifFile()
                                        .getFileName());
                                ImgUploadHandler.handlerFiles(tf.getBsifFile().getInputStream(),
                                        filename);

                                String address = ImgUploadHandler.getContentPath() + filename;
                                tf.setBsifcover(address);
                        }

                        MTeachbeseinfoTab mt = new MTeachbeseinfoTab();
                        BeanUtils.copyProperties(mt, tf);
                        mt.setId(tf.getBsifid().longValue());
                        dao.update(mt);

                        //System.out.println(tf);
                }

                return actionMapping.findForward("modify");
        }

        public ActionForward goEdit(ActionMapping actionMapping,
                                    ActionForm actionForm, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception
        {
                log.debug("inside go to Edit!!");

                String id = null;
                id = request.getParameter("id");

                MTeachbeseinfoTab info = null;

                try
                {
                        info = dao.load(Long.parseLong(id));
                }
                catch (Exception e)
                {
                        throw new ActionSchoolbookException(e.getMessage());
                }

                request.setAttribute(Globe.PAGE_CONTENT, info);

                return actionMapping.findForward("goEdit");
        }

        public ActionForward view(ActionMapping actionMapping,
                                  ActionForm actionForm, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
        {
                log.debug("inside view!!");

                String index = request.getParameter("p");

                //int idx = request.getRequestURI().lastIndexOf("/");
                String baseUrl = request.getRequestURI()
                        .substring(request.getContextPath().length() +
                                1);
                UrlProperty url = new UrlProperty(baseUrl);
                url.put(request.getParameterMap());

                PageWrapper page = dao.getPage(index, null);
                page.setBaseUrl(url);
                //List list = dao.findAll();
                //PageWrapper page = dao.getPage(null,null);
                request.setAttribute(Globe.PAGE_CONTENT, page);

                return actionMapping.findForward("view");
        }

        public ActionForward goView(ActionMapping actionMapping,
                                    ActionForm actionForm, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception
        {
                log.debug("inside go to view!!");

                return actionMapping.findForward("goView");
        }

        public ActionForward goAdd(ActionMapping actionMapping,
                                   ActionForm actionForm, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception
        {
                log.debug("inside go to Add!!");

                return actionMapping.findForward("goAdd");
        }
}
