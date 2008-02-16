/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 9:55:23
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustomconfigitem.form;

import com.ulearning.ulms.admin.webconfig.webcustom.form.WebCustomForm;

import org.apache.struts.action.ActionForm;

import java.util.ArrayList;


public class WebCustomConfigItemForm extends ActionForm
{
        private WebCustomForm wcf = null;
        private ArrayList list = null;
        private int CostomID = 0;

        public WebCustomForm getWcf()
        {
                return wcf;
        }

        public void setWcf(WebCustomForm wcf)
        {
                this.wcf = wcf;
        }

        public ArrayList getList()
        {
                return list;
        }

        public void setList(ArrayList list)
        {
                this.list = list;
        }

        public int getCostomID()
        {
                return CostomID;
        }

        public void setCostomID(int costomID)
        {
                CostomID = costomID;
        }
}
