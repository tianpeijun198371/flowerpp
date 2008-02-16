package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.Globe;
import com.ulearning.ulms.content.schoolbook.bean.StoreItemKey;
import com.ulearning.ulms.content.schoolbook.bean.StoreItemUnit;
import com.ulearning.ulms.content.schoolbook.bean.StoreMessageBean;
import com.ulearning.ulms.content.schoolbook.bean.StoreMessageViewBean;
import com.ulearning.ulms.content.schoolbook.exceptions.ActionSchoolbookException;
import com.ulearning.ulms.content.schoolbook.form.StoreForm;
import com.ulearning.ulms.content.schoolbook.model.MTeachbeseinfoTab;
import com.ulearning.ulms.content.schoolbook.model.MTeachmainstor;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachbeseinfoTabDAO;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachmainstorDAO;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachsortDAO;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachstorItemTabDAO;
import com.ulearning.ulms.content.schoolbook.util.PageWrapper;
import com.ulearning.ulms.content.schoolbook.util.UrlProperty;

import net.sf.hibernate.expression.Criterion;
import net.sf.hibernate.expression.Expression;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 入库管理类；
 * 入库管理中只有添加，审核，搜索，撤销入库单，列表和删除（删除属于出库范围内，同入库逻辑），不能修改
 */
import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StoreAction extends DispatchAction
{
        private static final Log log = LogFactory.getLog(StoreAction.class);
        private static final MTeachbeseinfoTabDAO infodao = MTeachbeseinfoTabDAO.getInstance();
        private static final MTeachmainstorDAO storedao = MTeachmainstorDAO.getInstance();
        private static final MTeachstorItemTabDAO itemdao = MTeachstorItemTabDAO.getInstance();
        private static final MTeachsortDAO inStoredao = MTeachsortDAO.getInstance();
        private List searchCondition = new Vector();
        private Map searchStoreMap = new Hashtable();

        /*
        * 添加入库(审核后正式入库)： 入库并非完全是添加操作；
        * 1，若库存中存在此书的ID,则该库存记录应该重新计算库存数量，和金额总计，更新该库存记录；
        * 2，若库存中不存在此书的ID,则添加该库存记录；
        * 3，多条教材信息入库时，必须使用事物，不能用Session的循环查询和写入；
        * 异常情况一律返回消息页面；
        *  if(单条教材ID){
        *   if(该教材已存在){
        *    update()更新教材库存
        *   } else{
        *       addStroe()添加库存记录
        *   }
        *  } else {
        *   try{
        *    business();多条时必须使用事物处理;
        *   } catch() {
        *     出现异常回滚后要跳到message页; }
        *   }
        */
        public ActionForward addStore(ActionMapping actionMapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception
        {
                log.debug("inside add store!!");

                String[] ids = (String[]) request.getAttribute(Globe.PAGE_CONTENT);

                try
                {
                        inStore(ids);
                        //加入成功后要删除明晰信息
                        itemdao.deleteItems(ids);
                }
                catch (ActionSchoolbookException e)
                {
                        request.setAttribute(Globe.PAGE_CONTENT, e.getMessage());

                        return actionMapping.findForward("errorMessage");
                }

                return actionMapping.findForward("addStore");
        }

        //入库逻辑
        private void inStore(String[] ids) throws ActionSchoolbookException
        {
                log.debug("inside in store!!");

                if (ids.length == 1)
                {
                        String id = ids[0];

                        if (inStoredao.isInfo(Integer.parseInt(id)))
                        {
                                inStoredao.inStoreUpdate(new Integer(id));
                        }
                        else
                        {
                                inStoredao.inStoreAdd(new Integer(id));
                        }
                }
                else
                {
                        try
                        {
                                inStoredao.buildStore(ids);
                        }
                        catch (ActionSchoolbookException e)
                        {
                                throw new ActionSchoolbookException("多教材入库时发生了事物异常，该事物已回滚！！");
                        }
                }
        }

        //删除明晰
        public ActionForward deleteItem(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
        {
                log.debug("inside delete item");

                String[] ids = request.getParameterValues("id");
                itemdao.deleteItems(ids);

                return actionMapping.findForward("deleteItem");
        }

        //	 审核本次入库操作
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

        // 加入入库明晰；前置条件getMessage()
        public ActionForward add(ActionMapping actionMapping,
                                 ActionForm actionForm, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception
        {
                log.debug("inside add!!");

                StoreMessageViewBean store = null;
                store = (StoreMessageViewBean) request.getSession()
                        .getAttribute(Globe.SESSION_KEY);

                //System.out.println(store);
                List messageBean = null;
                messageBean = store.getMessageList();
                itemdao.saveStoreItem(store, messageBean);
                request.getSession().removeAttribute(Globe.SESSION_KEY);

                //itemdao.save(new MTeachstorItemTab());
                return actionMapping.findForward("add");
        }

        //	 撤消入库单
        public ActionForward repeal(ActionMapping actionMapping,
                                    ActionForm actionForm, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception
        {
                log.debug("inside repeal!!");
                request.getSession().removeAttribute(Globe.SESSION_KEY);

                return actionMapping.findForward("repeal");
        }

        // 写入入库管理
        public ActionForward addMessage(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
        {
                log.debug("inside addMessage!!");

                StoreForm sf = (StoreForm) actionForm;
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

                // System.out.println(sf);
                String[] ids = request.getParameterValues("id");
                StoreItemKey key = new StoreItemKey(ids, sf.getTcitemquantity(),
                        sf.getTcitemsupplierid(), mainStoreId);
                // 要在Session里的有ids,入库数量,供应商ID和返回的入库管理ID,用StoreItemKey做key以便得到入库消息时调用
                request.getSession().setAttribute(Globe.SESSION_KEY, key);

                return actionMapping.findForward("addMessage");
        }

        // 给用户显示，计算，入库信息，包括金额
        public ActionForward getMessage(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
        {
                log.debug("inside getMessage!!");

                StoreItemKey key = null;
                key = (StoreItemKey) request.getSession().getAttribute(Globe.SESSION_KEY);

                Integer mainStoreId = key.getMainstoreId();
                MTeachmainstor mt = storedao.load(Long.parseLong(mainStoreId.toString()));
                StoreMessageViewBean messageView = new StoreMessageViewBean();
                BeanUtils.copyProperties(messageView, mt);
                messageView.setTcmainid(mainStoreId);

                List itemUnitList = StoreItemKey.buildStoreItemUnit(key);
                // messageList(),根据入库明晰单元，得到入库消息列表,付值到messageView
                messageList(messageView, itemUnitList, null);
                //System.out.println(messageView);
                // 最终写入 StoreMessageViewBean messageView
                request.setAttribute(Globe.PAGE_CONTENT, messageView);
                //将SESSION_KEY换成相应的消息结果
                request.getSession().setAttribute(Globe.SESSION_KEY, messageView);

                return actionMapping.findForward("getMessage");
        }

        //scale,折扣比例
        private List messageList(StoreMessageViewBean messageView,
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
                                        StoreItemUnit unit = (StoreItemUnit) itemUnitList.get(i);
                                        MTeachbeseinfoTab mt = infodao.load(unit.getUnitInfoId()
                                                .intValue());
                                        StoreMessageBean smb = new StoreMessageBean();
                                        BeanUtils.copyProperties(smb, unit);
                                        BeanUtils.copyProperties(smb, mt);
                                        smb.setTcitemtotal(scale);
                                        smb.setInfoId(mt.getId());
                                        //System.out.println(smb);
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

        // 搜索书目信息中的(分页)
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
                page = infodao.getPage(index, null, getSearchCondition(), null);
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);
                searchCondition.clear();

                return actionMapping.findForward("search");
        }

        // 记录查询条件，发送到search
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
                        addSearchCondition(Expression.eq("bsifbookname", bookname));
                }

                if (!publishname.equals(""))
                {
                        addSearchCondition(Expression.eq("bsifpublishname", publishname));
                }

                if (!author.equals(""))
                {
                        addSearchCondition(Expression.eq("bsifauthor", author));
                }

                /*
                * if (null != getSearchCondition()) {
                * System.out.println(getSearchCondition().length); }
                */
                return actionMapping.findForward("goSearch");
        }

        private void addSearchCondition(Criterion exp)
        {
                searchCondition.add(exp);
        }

        private Criterion[] getSearchCondition()
        {
                if (searchCondition.size() == 0)
                {
                        return null;
                }

                Criterion[] temp = new Criterion[searchCondition.size()];

                for (int i = 0; i < searchCondition.size(); i++)
                {
                        temp[i] = (Criterion) searchCondition.get(i);
                }

                return temp;
        }

        // 列表书目信息所有（分页）
        public ActionForward infoList(ActionMapping actionMapping,
                                      ActionForm actionForm, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception
        {
                log.debug("inside viewList!!");

                String index = request.getParameter("p");

                // int idx = request.getRequestURI().lastIndexOf("/");
                String baseUrl = request.getRequestURI()
                        .substring(request.getContextPath().length() +
                                1);
                UrlProperty url = new UrlProperty(baseUrl);
                url.put(request.getParameterMap());

                PageWrapper page = infodao.getPage(index, null);
                page.setBaseUrl(url);
                // List list = dao.findAll();
                // PageWrapper page = dao.getPage(null,null);
                request.setAttribute(Globe.PAGE_CONTENT, page);

                return actionMapping.findForward("infoList");
        }

        //列表入库明细
        public ActionForward listStore(ActionMapping actionMapping,
                                       ActionForm actionForm, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception
        {
                log.debug("inside listStore!!");

                String index = request.getParameter("p");

                String baseUrl = request.getRequestURI()
                        .substring(request.getContextPath().length() +
                                1);
                UrlProperty url = new UrlProperty(baseUrl);
                url.put(request.getParameterMap());

                PageWrapper page = itemdao.getPage(index, null, new Integer(1));
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);

                return actionMapping.findForward("listStore");
        }

        //入库明晰条件
        public ActionForward goSearchStore(ActionMapping actionMapping,
                                           ActionForm actionForm, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
        {
                log.debug("inside go search store!!");
                //System.out.println("==========start go search store!!============");
                searchStoreMap.clear();
                request.removeAttribute(Globe.PAGE_CONTENT);

                String bsifbookname = request.getParameter("bsifbookname").trim();
                String tcmaintran = request.getParameter("tcmaintran").trim();
                String tcmaindate = request.getParameter("maindate").trim();

                if (!bsifbookname.equals(""))
                {
                        //addSearchCondition(Expression.eq("bsifbookname", bsifbookname));
                        searchStoreMap.put("bsifbookname", bsifbookname);
                }

                if (!tcmaintran.equals(""))
                {
                        //addSearchCondition(Expression.eq("tcmaintran", tcmaintran));
                        searchStoreMap.put("tcmaintran", tcmaintran);
                }

                if (!tcmaindate.equals("") && (tcmaindate.length() == 10))
                {
                        //addSearchCondition(Expression.eq("tcmaindate", tcmaindate));
                        searchStoreMap.put("tcmaindate", tcmaindate);
                }

                return actionMapping.findForward("goSearchStore");
        }

        //入库明晰查询
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
                page = itemdao.getStoreItemPage(index, null, searchStoreMap);
                page.setBaseUrl(url);
                request.setAttribute(Globe.PAGE_CONTENT, page);
                searchStoreMap.clear();

                return actionMapping.findForward("searchStore");
        }
}
