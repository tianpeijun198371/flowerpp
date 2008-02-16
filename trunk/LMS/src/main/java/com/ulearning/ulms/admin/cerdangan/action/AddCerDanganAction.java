/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.cerdangan.action;

import com.ulearning.ulms.admin.cerdangan.dao.CerDanganDAO;
import com.ulearning.ulms.admin.cerdangan.dao.CerDanganDAOFactory;
import com.ulearning.ulms.admin.cerdangan.form.CerNewDanganForm;
import com.ulearning.ulms.core.util.DateTimeUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class AddCerDanganAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                try
                {
                        System.out.println(
                                "tf.getCertname()================================================== = ");

                        CerNewDanganForm tf = (CerNewDanganForm) form;
                        System.out.println(
                                "tf.getCertname()================================================== = " +
                                        tf.getCertname());

                        CerDanganDAO dao = CerDanganDAOFactory.getDAO();
                        dao.insertCerDangan(tf);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
