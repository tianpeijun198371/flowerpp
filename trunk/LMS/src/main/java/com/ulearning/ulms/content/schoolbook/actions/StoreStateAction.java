package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.Globe;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachsortDAO;
import com.ulearning.ulms.content.schoolbook.util.PageWrapper;
import com.ulearning.ulms.content.schoolbook.util.UrlProperty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StoreStateAction extends DispatchAction
{
        private static final Log log = LogFactory.getLog(StoreStateAction.class);
        private static final MTeachsortDAO dao = MTeachsortDAO.getInstance();

        //private Float sumTotal = dao.sumTotal();
        //private Integer sumQuantity = dao.sumQuantity();
        private Map searchMap = new Hashtable();

        //全部库存分页
        public ActionForward list(ActionMapping actionMapping,
                                  ActionForm actionForm, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
        {
                log.debug("inside list!!");

                String index = request.getParameter("p");

                // int idx = request.getRequestURI().lastIndexOf("/");
                String baseUrl = request.getRequestURI()
                        .substring(request.getContextPath().length() +
                                1);
                UrlProperty url = new UrlProperty(baseUrl);
                url.put(request.getParameterMap());

                PageWrapper page = dao.getPage(index, null);
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);
                request.setAttribute("sumTotal", dao.sumTotal().toString());
                request.setAttribute("sumQuantity", dao.sumQuantity().toString());

                return actionMapping.findForward("list");
        }

        //查询分类，分页
        public ActionForward search(ActionMapping actionMapping,
                                    ActionForm actionForm, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception
        {
                log.debug("inside search!!");

                String index = request.getParameter("p");
                String baseUrl = request.getRequestURI()
                        .substring(request.getContextPath().length() +
                                1);
                UrlProperty url = new UrlProperty(baseUrl);
                url.put(request.getParameterMap());

                PageWrapper page = dao.getSearchPage(index, null, searchMap);
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);
                request.setAttribute("sumTotal", dao.sumQuantity());
                request.setAttribute("sumQuantity", dao.sumQuantity());
                searchMap.clear();

                return actionMapping.findForward("search");
        }

        //设置查询条件
        public ActionForward goSearch(ActionMapping actionMapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception
        {
                log.debug("inside go search!!");
                searchMap.clear();
                request.removeAttribute(Globe.PAGE_CONTENT);

                String bookname = request.getParameter("bsifbookname").trim();
                String publishname = request.getParameter("bsifpublishname").trim();
                String tcstprice = request.getParameter("tcstprice").trim();

                if (!bookname.equals(""))
                {
                        searchMap.put("bsifbookname", bookname);
                }

                if (!publishname.equals(""))
                {
                        searchMap.put("bsifpublishname", publishname);
                }

                if (!tcstprice.equals(""))
                {
                        searchMap.put("tcstprice", tcstprice);
                }

                return actionMapping.findForward("goSearch");
        }
}
