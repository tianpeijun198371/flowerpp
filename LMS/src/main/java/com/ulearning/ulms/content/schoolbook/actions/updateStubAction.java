/**
 * updateStubAction.java.
 * User: liz  Date: 2006-5-24
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.bean.TeachbillHelper;
import com.ulearning.ulms.content.schoolbook.form.MTeachmailbillitemForm;
import com.ulearning.ulms.content.schoolbook.form.MTeachmailbillmainform;
import com.ulearning.ulms.core.util.DateTimeUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class updateStubAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //主表数据
                String tcmllevydept = request.getParameter("tcmllevydept"); //购书单位
                String tcmllevyoperator = request.getParameter("tcmllevyoperator"); //经办人
                String tcmltotal = request.getParameter("tcmltotal"); //合计金额
                String tcmlphone = request.getParameter("tcmlphone"); //联系电话
                String tcmlfax = request.getParameter("tcmlfax"); //传真
                String tcmlman = request.getParameter("tcmlman"); //联系人
                String tcmlpostman = request.getParameter("tcmlpostman"); //收款人
                String tcmlpostadds = request.getParameter("tcmlpostadds"); //地址
                String tcmlpostcode = request.getParameter("tcmlpostcode"); //邮编
                String tcmlbankcom = request.getParameter("tcmlbankcom"); //收款单位
                String tcmlbankname = request.getParameter("tcmlbankname"); //开户行
                String tcmlbankaccount = request.getParameter("tcmlbankaccount"); //帐号
                String tcmlid = request.getParameter("tcmlid");
                String tcmldate = request.getParameter("tcmldate");

                //回执信息
                String tcmllevyadds = request.getParameter("tcmllevyadds"); //详细地址
                String tcmllevymailcode = request.getParameter("tcmllevymailcode"); //邮编
                String tcmllevyphone = request.getParameter("tcmllevyphone"); //征订电话
                String tcmllevyfax = request.getParameter("tcmllevyfax"); //征订传真
                String tcmllevyrem = request.getParameter("tcmllevyrem"); //汇款方式

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
                frm.setTcmldate(DateTimeUtil.parseDateTime(tcmldate));
                frm.setTcmlfax(tcmlfax);
                frm.setTcmlid(Integer.parseInt(tcmlid));
                frm.setTcmllevyadds(tcmllevyadds);
                frm.setTcmllevyfax(tcmllevyfax);
                frm.setTcmllevymailcode(tcmllevymailcode);

                frm.setTcmllevyphone(tcmllevyphone);
                frm.setTcmllevyrem(Integer.parseInt(tcmllevyrem));
                frm.setTcmlman(tcmlman);
                frm.setTcmlphone(tcmlphone);
                frm.setTcmlpostadds(tcmlpostadds);
                frm.setTcmlpostcode(tcmlpostcode);
                frm.setTcmlpostman(tcmlpostman);
                frm.setTcmltotal(Double.valueOf(tcmltotal).doubleValue());

                //修改主表数据
                TeachbillHelper help = new TeachbillHelper();
                help.updateMainData(frm);

                //修改子表数据
                String[] titemid = request.getParameterValues("titemid");
                String[] bsifid = request.getParameterValues("bsifid");
                String[] bsifbookname = request.getParameterValues("bsifbookname");
                String[] tcmitemPrice = request.getParameterValues("tcmitemPrice");
                String[] tcmitemQuantity = request.getParameterValues("tcmitemQuantity");
                MTeachmailbillitemForm frmi = new MTeachmailbillitemForm();

                for (int i = 0; (null != titemid) && (i < titemid.length); i++)
                {
                        frmi.setBsifbookname(bsifbookname[i]);
                        frmi.setBsifid(Integer.parseInt(bsifid[i]));
                        frmi.setRemark1("");
                        frmi.setRemark2("");
                        frmi.setRemark3("");
                        frmi.setRemark4("");
                        frmi.setRemark5("");
                        frmi.setTcmitemPrice((Double.valueOf(tcmitemPrice[i])).doubleValue());
                        frmi.setTcmitemQuantity(Integer.parseInt(tcmitemQuantity[i]));
                        frmi.setTcmlid(Integer.parseInt(tcmlid));
                        frmi.setTitemid(Integer.parseInt(titemid[i]));

                        help.updateItemData(frmi);
                }

                return mapping.findForward("success");
        }
}
