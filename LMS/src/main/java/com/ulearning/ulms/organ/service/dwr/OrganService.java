/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: fengch
 * Date: 2007-12-25 14:50:37 
 */
package com.ulearning.ulms.organ.service.dwr;

import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.model.OrganModel;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class OrganService
{
        public Map getOrgans(String country, String province, String city)
        {
                /*HttpServletRequest req = WebContextFactory.get()
                                  .getHttpServletRequest();
                  HttpSession session = req.getSession();
                  User user = (User) session.getAttribute(SysConstants.USER_IN_SESSION);
                  ServletContext application = session.getServletContext();*/
                List list = OrganHelper.getOrganByArea(country, province, city);
                Map map = new HashMap();
                for (int i = 0; i < list.size(); i++)
                {
                        OrganModel model = (OrganModel) list.get(i);
                        map.put(String.valueOf(model.getOrgid()), model.getOrgname());
                }
                return map;
        }


        public String getOrganStrs(String country, String province, String city)
        {
                /*HttpServletRequest req = WebContextFactory.get()
                                  .getHttpServletRequest();
                  HttpSession session = req.getSession();
                  User user = (User) session.getAttribute(SysConstants.USER_IN_SESSION);
                  ServletContext application = session.getServletContext();*/
                List list = OrganHelper.getOrganByArea(country, province, city);
                String str = "";
                for (int i = 0; i < list.size(); i++)
                {
                        OrganModel model = (OrganModel) list.get(i);
                        if(i!=0)
                        {
                                str+=",";
                        }
                        str+=model.getOrgid();
                }
                return str;
        }
}
