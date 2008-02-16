package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.Globe;
import com.ulearning.ulms.content.schoolbook.bean.OutStoreItemHandler;
import com.ulearning.ulms.content.schoolbook.bean.OutStoreItemUnit;
import com.ulearning.ulms.content.schoolbook.bean.OutStoreMessageView;
import com.ulearning.ulms.content.schoolbook.bean.StoreMessageBean;
import com.ulearning.ulms.content.schoolbook.exceptions.ActionSchoolbookException;
import com.ulearning.ulms.content.schoolbook.form.OutStoreForm;
import com.ulearning.ulms.content.schoolbook.model.MTeachbeseinfoTab;
import com.ulearning.ulms.content.schoolbook.model.MTeachmainstor;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachbeseinfoTabDAO;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachmainstorDAO;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachsortDAO;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachstorItemTabDAO;
import com.ulearning.ulms.content.schoolbook.util.PageWrapper;
import com.ulearning.ulms.content.schoolbook.util.UrlProperty;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OutStoreAction extends DispatchAction
{
        private static final Log log = LogFactory.getLog(OutStoreAction.class);
        private static final MTeachbeseinfoTabDAO infodao = MTeachbeseinfoTabDAO.getInstance();
        private static final MTeachmainstorDAO storedao = MTeachmainstorDAO.getInstance();
        private static final MTeachstorItemTabDAO itemdao = MTeachstorItemTabDAO.getInstance();
        private static final MTeachsortDAO outStoredao = MTeachsortDAO.getInstance();
        private Map searchCondition = new Hashtable();

        //出库
        public ActionForward outStore(ActionMapping actionMapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception
        {
                String[] ids = (String[]) request.getAttribute(Globe.PAGE_CONTENT);

                try
                {
                        inStore(ids);
                        //出库成功后要删除明晰信息
                        itemdao.deleteItems(ids);
                }
                catch (ActionSchoolbookException e)
                {
                        request.setAttribute(Globe.PAGE_CONTENT, e.getMessage());

                        return actionMapping.findForward("errorMessage");
                }

                return actionMapping.findForward("outStroe");
        }

        //	出库逻辑
        private void inStore(String[] ids) throws ActionSchoolbookException
        {
                log.debug("inside in store!!");

                if (ids.length == 1)
                {
                        String id = ids[0];

                        if (outStoredao.isInfo(Integer.parseInt(id)))
                        {
                                outStoredao.outStoreUpdate(new Integer(id));
                        }
                        else
                        {
                                throw new ActionSchoolbookException("此教材并不在库存中！！");
                        }
                }
                else
                {
                        try
                        {
                                outStoredao.buildOutStore(ids);
                        }
                        catch (ActionSchoolbookException e)
                        {
                                throw new ActionSchoolbookException("多教材出库时发生了事物异常，该事物已回滚！！");
                        }
                }
        }

        //删除出库明晰
        public ActionForward deleteItem(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
        {
                log.debug("inside delete item");

                String[] ids = request.getParameterValues("id");
                itemdao.deleteItems(ids);

                return actionMapping.findForward("deleteItem");
        }

        //审核本次出库操作
        public ActionForward audit(ActionMapping actionMapping,
                                   ActionForm actionForm, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception
        {
                log.debug("inside audit!!");

                String[] ids = request.getParameterValues("id");
                storedao.audit(ids, "admin");
                request.setAttribute(Globe.PAGE_CONTENT, ids);

                return actionMapping.findForward("audit");
        }

        //加入出库明晰
        public ActionForward add(ActionMapping actionMapping,
                                 ActionForm actionForm, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception
        {
                log.debug("inside add!!");

                OutStoreMessageView store = null;
                store = (OutStoreMessageView) request.getSession()
                        .getAttribute(Globe.SESSION_KEY);

                List messageBean = null;
                messageBean = store.getMessageList();
                itemdao.saveStoreItem(store, messageBean);
                request.getSession().removeAttribute(Globe.SESSION_KEY);

                return actionMapping.findForward("add");
        }

        //撤消出库单
        public ActionForward repeal(ActionMapping actionMapping,
                                    ActionForm actionForm, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception
        {
                log.debug("inside repeal!!");
                request.getSession().removeAttribute(Globe.SESSION_KEY);

                return actionMapping.findForward("repeal");
        }

        //	 写入出库管理
        public ActionForward addMessage(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
        {
                log.debug("inside addMessage!!");

                OutStoreForm sf = (OutStoreForm) actionForm;
                MTeachmainstor mms = new MTeachmainstor();
                BeanUtils.copyProperties(mms, sf);

                Integer mainStoreId = null;

                try
                {
                        mainStoreId = new Integer(storedao.save(mms).toString());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                String[] ids = request.getParameterValues("id");
                OutStoreItemHandler key = new OutStoreItemHandler(ids,
                        sf.getTcitemquantity(), sf.getTcitemtclientid(), mainStoreId);
                // 要在Session里的有ids,出库数量,客户ID和返回的出库管理ID
                request.getSession().setAttribute(Globe.SESSION_KEY, key);

                return actionMapping.findForward("addMessage");
        }

        //	 给用户显示，计算，出库信息，包括金额
        public ActionForward getMessage(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
        {
                log.debug("inside getMessage!!");

                OutStoreItemHandler key = null;
                key = (OutStoreItemHandler) request.getSession()
                        .getAttribute(Globe.SESSION_KEY);

                for (int i = 0; i < key.getInfoid().length; i++)
                {
                        System.out.println(key.getInfoid()[i]);
                }

                Integer mainStoreId = key.getMainstoreId();
                MTeachmainstor mt = storedao.load(Long.parseLong(mainStoreId.toString()));
                OutStoreMessageView messageView = new OutStoreMessageView();
                BeanUtils.copyProperties(messageView, mt);
                messageView.setTcmainid(mainStoreId);

                List itemUnitList = OutStoreItemHandler.buidlOutStoreUnit(key);
                // messageList(),根据出库明晰单元，得到出库消息列表,付值到messageView
                messageList(messageView, itemUnitList, null);
                //System.out.println(messageView);
                // 最终写入 StoreMessageViewBean messageView
                request.setAttribute(Globe.PAGE_CONTENT, messageView);
                //getValidateMessage()验证出库数量是否与库存数量，若大于消息提示，若小于消息为空
                request.setAttribute(Globe.PAGE_MESSAGE, getValidateMessage(key));
                //将SESSION_KEY换成相应的消息结果
                request.getSession().setAttribute(Globe.SESSION_KEY, messageView);

                return actionMapping.findForward("getMessage");
        }

        private String getValidateMessage(OutStoreItemHandler key)
        {
                String message = null;

                if (outStoredao.isOutQuantity(key.getInfoid(),
                        key.getTcitemquantity().intValue()))
                {
                        message = "出库数量大于库存数量，请重新添加！";
                }

                return message;
        }

        //	scale,折扣比例
        private List messageList(OutStoreMessageView messageView,
                                 List itemUnitList, Float scale)
        {
                log.debug("inside message list!!");

                List messageList = null;

                if (itemUnitList != null)
                {
                        try
                        {
                                messageList = new ArrayList();

                                for (int i = 0; i < itemUnitList.size(); i++)
                                {
                                        OutStoreItemUnit unit = (OutStoreItemUnit) itemUnitList.get(i);

                                        //System.out.println(unit);
                                        //System.out.println("======================" + unit.getInfoId().intValue() + "================");
                                        MTeachbeseinfoTab mt = infodao.load(unit.getInfoId()
                                                .intValue());
                                        StoreMessageBean smb = new StoreMessageBean();
                                        BeanUtils.copyProperties(smb, unit);
                                        BeanUtils.copyProperties(smb, mt);
                                        smb.setTcitemtotal(scale);
                                        smb.setInfoId(mt.getId());
                                        messageList.add(smb);
                                }

                                messageView.setMessageList(messageList);
                        }
                        catch (IllegalAccessException e)
                        {
                                e.printStackTrace();
                        }
                        catch (InvocationTargetException e)
                        {
                                e.printStackTrace();
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }

                return messageList;
        }

        //	 搜索书目信息中的(分页)
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

                PageWrapper page = null;
                page = outStoredao.getSearchPage(index, null, searchCondition);
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);
                searchCondition.clear();

                return actionMapping.findForward("search");
        }

        //	 搜索书目信息中的(分页)
        public ActionForward searchdata(ActionMapping actionMapping,
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

                PageWrapper page = null;
                page = outStoredao.getSearchPage(index, null, searchCondition);
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);
                searchCondition.clear();

                return actionMapping.findForward("searchdata");
        }

        //	 记录查询条件，发送到search
        public ActionForward goSearch(ActionMapping actionMapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception
        {
                log.debug("inside go search!!");
                searchCondition.clear();
                request.removeAttribute(Globe.PAGE_CONTENT);

                String bookname = request.getParameter("bsifbookname").trim();
                String publishname = request.getParameter("bsifpublishname").trim();
                String author = request.getParameter("bsifauthor").trim();

                if (!bookname.equals(""))
                {
                        searchCondition.put("bsifbookname", bookname);
                }

                if (!publishname.equals(""))
                {
                        searchCondition.put("bsifpublishname", publishname);
                }

                if (!author.equals(""))
                {
                        searchCondition.put("bsifauthor", author);
                }

                return actionMapping.findForward("goSearch");
        }

        //	 列表书目信息所有（分页）
        public ActionForward infoList(ActionMapping actionMapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception
        {
                log.debug("inside viewList!!");

                String index = request.getParameter("p");
                String baseUrl = request.getRequestURI()
                        .substring(request.getContextPath().length() +
                                1);
                UrlProperty url = new UrlProperty(baseUrl);
                url.put(request.getParameterMap());

                //应该是库存页
                PageWrapper page = outStoredao.getPage(index, null);
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);

                return actionMapping.findForward("infoList");
        }

        //	列表出库明细
        public ActionForward listStore(ActionMapping actionMapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception
        {
                log.debug("inside listStore!!");

                //System.out.println("============Search list!!!==============");
                String index = request.getParameter("p");
                String baseUrl = request.getRequestURI()
                        .substring(request.getContextPath().length() +
                                1);
                UrlProperty url = new UrlProperty(baseUrl);
                url.put(request.getParameterMap());

                PageWrapper page = itemdao.getPage(index, null, new Integer(2));
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);

                return actionMapping.findForward("listStore");
        }

        //	出库明晰条件
        public ActionForward goSearchStore(ActionMapping actionMapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
        {
                log.debug("inside go search out Store!!");
                searchCondition.clear();
                request.removeAttribute(Globe.PAGE_CONTENT);

                String bsifbookname = request.getParameter("bsifbookname").trim();
                String tcmaintran = request.getParameter("tcmainuser").trim();
                String tcmaindate = request.getParameter("maindate").trim();

                if (!bsifbookname.equals(""))
                {
                        //addSearchCondition(Expression.eq("bsifbookname", bsifbookname));
                        searchCondition.put("bsifbookname", bsifbookname);
                }

                if (!tcmaintran.equals(""))
                {
                        //addSearchCondition(Expression.eq("tcmaintran", tcmaintran));
                        searchCondition.put("tcmainuser", tcmaintran);
                }

                if (!tcmaindate.equals("") && (tcmaindate.length() == 10))
                {
                        //addSearchCondition(Expression.eq("tcmaindate", tcmaindate));
                        searchCondition.put("tcmaindate", tcmaindate);
                }

                return actionMapping.findForward("goSearchStore");
        }

        //	出库明晰查询
        public ActionForward searchStore(ActionMapping actionMapping,
                                         ActionForm actionForm, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception
        {
                log.debug("inside search store!!");

                String index = request.getParameter("p");
                String baseUrl = request.getRequestURI()
                        .substring(request.getContextPath().length() +
                                1);
                UrlProperty url = new UrlProperty(baseUrl);
                url.put(request.getParameterMap());

                PageWrapper page = null;
                page = itemdao.getOutStoreItemPage(index, null, searchCondition, 2);
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);
                searchCondition.clear();

                //return null;
                return actionMapping.findForward("searchStore");
        }
}
