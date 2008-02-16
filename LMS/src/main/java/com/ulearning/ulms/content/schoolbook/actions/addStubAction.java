/**
 * addStubAction.java.
 * User: liz  Date: 2006-5-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.bean.TeachbillHelper;
import com.ulearning.ulms.content.schoolbook.form.MTeachmailbillitemForm;
import com.ulearning.ulms.content.schoolbook.form.MTeachmailbillmainform;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class addStubAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //��������
                String tcmllevydept = request.getParameter("tcmllevydept"); //���鵥λ
                String tcmllevyoperator = request.getParameter("tcmllevyoperator"); //������
                String tcmltotal = request.getParameter("tcmltotal"); //�ϼƽ��
                String tcmlphone = request.getParameter("tcmlphone"); //��ϵ�绰
                String tcmlfax = request.getParameter("tcmlfax"); //����
                String tcmlman = request.getParameter("tcmlman"); //��ϵ��
                String tcmlpostman = request.getParameter("tcmlpostman"); //�տ���
                String tcmlpostadds = request.getParameter("tcmlpostadds"); //��ַ
                String tcmlpostcode = request.getParameter("tcmlpostcode"); //�ʱ�
                String tcmlbankcom = request.getParameter("tcmlbankcom"); //�տλ
                String tcmlbankname = request.getParameter("tcmlbankname"); //������
                String tcmlbankaccount = request.getParameter("tcmlbankaccount"); //�ʺ�
                String tcmlid = request.getParameter("tcmlid");

                MTeachmailbillmainform frm = new MTeachmailbillmainform();
                frm.setRemark1("");
                frm.setRemark2("");
                frm.setRemark3("");
                frm.setRemark4("");
                frm.setRemark5("");
                frm.setTcmllevydept(tcmllevydept);
                frm.setTcmllevyoperator(tcmllevyoperator);
                frm.setTcmlbankaccount(tcmlbankaccount);
                frm.setTcmlbankcom(tcmlbankcom);
                frm.setTcmlbankname(tcmlbankname);
                frm.setTcmldate(new Date());
                frm.setTcmlfax(tcmlfax);
                frm.setTcmlid(Integer.parseInt(tcmlid));
                frm.setTcmllevyadds("");
                frm.setTcmllevyfax("");
                frm.setTcmllevymailcode("");

                frm.setTcmllevyphone("");
                frm.setTcmllevyrem(0);
                frm.setTcmlman(tcmlman);
                frm.setTcmlphone(tcmlphone);
                frm.setTcmlpostadds(tcmlpostadds);
                frm.setTcmlpostcode(tcmlpostcode);
                frm.setTcmlpostman(tcmlpostman);
                frm.setTcmltotal(Double.valueOf(tcmltotal).doubleValue());

                if (tcmlid.equals("0"))
                { //����
                        //
                        System.out.println("����");

                        TeachbillHelper help = new TeachbillHelper();

                        tcmlid = String.valueOf(help.instNewMainData(frm));
                }
                else
                { //�޸�
                        System.out.println("�޸�");

                        //upMainData(frm);
                }

                //��ӡ��ӱ�����
                String bsifid = request.getParameter("bsifid");

                if ((null != tcmlid) && !tcmlid.equals("0") && (null != bsifid) &&
                        !bsifid.equals("0"))
                {
                        additem(request, tcmlid, frm);
                }

                request.setAttribute("tcmlid", tcmlid);

                return mapping.findForward("success");
        }

        /**
         * �޸���������
         *
         * @param frm
         */
        private void upMainData(MTeachmailbillmainform frm)
        {
                TeachbillHelper help = new TeachbillHelper();
                help.updateMainData(frm);
        }

        /**
         * ����ӱ�����
         *
         * @param request
         * @param tcmlid
         */
        private void additem(HttpServletRequest request, String tcmlid,
                             MTeachmailbillmainform frmm)
        {
                //�ӱ�����
                /** �̲�ID */
                String bsifid = request.getParameter("bsifid");
                String type = request.getParameter("type");

                /** �̲����� */
                String bsifbookname = request.getParameter("bsifbookname");
                String titemid = request.getParameter("titemid");

                /** ���� */
                double tcmitemPrice = Double.valueOf(request.getParameter(
                        "tcmitemPrice")).doubleValue();

                /** �������� */
                int tcmitemQuantity = Integer.parseInt(request.getParameter(
                        "tcmitemQuantity"));

                //�������ݺϼ�
                MTeachmailbillitemForm frm = new MTeachmailbillitemForm();
                frm.setBsifbookname(bsifbookname);
                frm.setBsifid(Integer.parseInt(bsifid));
                frm.setRemark1("");
                frm.setRemark2("");
                frm.setRemark3("");
                frm.setRemark4("");
                frm.setRemark5("");
                frm.setTcmitemPrice(tcmitemPrice);
                frm.setTcmitemQuantity(tcmitemQuantity);
                frm.setTcmlid(Integer.parseInt(tcmlid));
                frm.setTitemid(Integer.parseInt(titemid));

                if ((null != titemid) && !titemid.equals("0") && type.equals("1"))
                { //�޸�

                        double sum = getitem(titemid);
                        double sumdata = tcmitemPrice * tcmitemQuantity;
                        frmm.setTcmltotal((frmm.getTcmltotal() + sumdata) - sum);
                        upMainData(frmm);

                        TeachbillHelper help = new TeachbillHelper();
                        help.updateItemData(frm);
                }
                else
                { //����

                        double sumdata = tcmitemPrice * tcmitemQuantity;

                        frmm.setTcmlid(Integer.parseInt(tcmlid));
                        frmm.setTcmltotal(frmm.getTcmltotal() + sumdata);

                        upMainData(frmm);

                        TeachbillHelper help = new TeachbillHelper();
                        help.instNewItemData(frm);
                }
        }

        private double getitem(String itemid)
        {
                double rsum = 0;
                TeachbillHelper help = new TeachbillHelper();
                List iulist = help.getItemDataById(String.valueOf(itemid));
                System.out.println("itemid=========" + itemid);

                MTeachmailbillitemForm frm = null;

                if ((null != iulist) && (iulist.size() > 0))
                {
                        frm = (MTeachmailbillitemForm) iulist.get(0);
                        rsum = frm.getTcmitemQuantity() * frm.getTcmitemPrice();
                }

                return rsum;
        }
}
