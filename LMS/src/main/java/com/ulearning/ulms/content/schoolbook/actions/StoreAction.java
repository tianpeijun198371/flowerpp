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
 * �������ࣻ
 * ��������ֻ����ӣ���ˣ�������������ⵥ���б��ɾ����ɾ�����ڳ��ⷶΧ�ڣ�ͬ����߼����������޸�
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
        * ������(��˺���ʽ���)�� ��Ⲣ����ȫ����Ӳ�����
        * 1��������д��ڴ����ID,��ÿ���¼Ӧ�����¼������������ͽ���ܼƣ����¸ÿ���¼��
        * 2��������в����ڴ����ID,����Ӹÿ���¼��
        * 3�������̲���Ϣ���ʱ������ʹ�����������Session��ѭ����ѯ��д�룻
        * �쳣���һ�ɷ�����Ϣҳ�棻
        *  if(�����̲�ID){
        *   if(�ý̲��Ѵ���){
        *    update()���½̲Ŀ��
        *   } else{
        *       addStroe()��ӿ���¼
        *   }
        *  } else {
        *   try{
        *    business();����ʱ����ʹ�����ﴦ��;
        *   } catch() {
        *     �����쳣�ع���Ҫ����messageҳ; }
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
                        //����ɹ���Ҫɾ��������Ϣ
                        itemdao.deleteItems(ids);
                }
                catch (ActionSchoolbookException e)
                {
                        request.setAttribute(Globe.PAGE_CONTENT, e.getMessage());

                        return actionMapping.findForward("errorMessage");
                }

                return actionMapping.findForward("addStore");
        }

        //����߼�
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
                                throw new ActionSchoolbookException("��̲����ʱ�����������쳣���������ѻع�����");
                        }
                }
        }

        //ɾ������
        public ActionForward deleteItem(ActionMapping actionMapping,
                                        ActionForm actionForm, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception
        {
                log.debug("inside delete item");

                String[] ids = request.getParameterValues("id");
                itemdao.deleteItems(ids);

                return actionMapping.findForward("deleteItem");
        }

        //	 ��˱���������
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

        // �������������ǰ������getMessage()
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

        //	 ������ⵥ
        public ActionForward repeal(ActionMapping actionMapping,
                                    ActionForm actionForm, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception
        {
                log.debug("inside repeal!!");
                request.getSession().removeAttribute(Globe.SESSION_KEY);

                return actionMapping.findForward("repeal");
        }

        // д��������
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
                // Ҫ��Session�����ids,�������,��Ӧ��ID�ͷ��ص�������ID,��StoreItemKey��key�Ա�õ������Ϣʱ����
                request.getSession().setAttribute(Globe.SESSION_KEY, key);

                return actionMapping.findForward("addMessage");
        }

        // ���û���ʾ�����㣬�����Ϣ���������
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
                // messageList(),�������������Ԫ���õ������Ϣ�б�,��ֵ��messageView
                messageList(messageView, itemUnitList, null);
                //System.out.println(messageView);
                // ����д�� StoreMessageViewBean messageView
                request.setAttribute(Globe.PAGE_CONTENT, messageView);
                //��SESSION_KEY������Ӧ����Ϣ���
                request.getSession().setAttribute(Globe.SESSION_KEY, messageView);

                return actionMapping.findForward("getMessage");
        }

        //scale,�ۿ۱���
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

        // ������Ŀ��Ϣ�е�(��ҳ)
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

        // ��¼��ѯ���������͵�search
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

        // �б���Ŀ��Ϣ���У���ҳ��
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

        //�б������ϸ
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

        //�����������
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

        //���������ѯ
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
