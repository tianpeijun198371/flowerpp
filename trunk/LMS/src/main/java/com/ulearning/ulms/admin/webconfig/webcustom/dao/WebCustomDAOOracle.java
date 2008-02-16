/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:27:21
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.dao;

import com.ulearning.ulms.admin.webconfig.webcustom.exceptions.WebCustomDAOSysException;
import com.ulearning.ulms.admin.webconfig.webcustom.form.WebCustomForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.log.LogUtil;


public class WebCustomDAOOracle extends WebCustomDAOImpl
{
        /**
         *
         * @param details
         * @return
         * @throws WebCustomDAOSysException
         */

        /*
          public int addWebCustom(WebCustomForm details) throws WebCustomDAOSysException
          {
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  String updateDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                  strSql = "insert into U_CUSTOM_TAB Values(CustomID.nextval,"
                          +details.getRelationID()+",'"
                          +details.getRelationType()+"','"
                          +details.getConfigTypeName()+"',"
                          +updateDate+",'"
                          +details.getDescription()+"')";
                  try
                  {
                          LogUtil.debug("WebCustom", "[WebCustomDAOOracle]====================the sql string is : " + strSql);
                  } catch (ULMSSysException se)
                  {
                          throw new WebCustomDAOSysException("SQLException while insert into WebCustom; Serial = " + details.getConfigTypeName()+ " :\n" + se);
                  }
                  int CustomID = 0;
                  CustomID = getCustomID(details);
                  return CustomID;
          }
        */

        /**
         *
         * @param details
         * @throws WebCustomDAOSysException
         */

        /*
          public void updateWebCustom(WebCustomForm details) throws WebCustomDAOSysException
          {
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  String updateDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                  strSql = "update U_CUSTOM_TAB set ConfigTypeName = '"
                          +details.getConfigTypeName()+"',UpdateDate = "
                          +updateDate+",Description = '"
                          +details.getDescription()+"' where CustomID = "
                          +details.getCustomID();
                  try
                  {
                          LogUtil.debug("WebCustom", "[WebCustomDAOOracle]====================the sql string is : " + strSql);
                  } catch (ULMSSysException se)
                  {
                          throw new WebCustomDAOSysException("SQLException while update  WebCustom; Serial = " + details.getConfigTypeName()+ " :\n" + se);
                  }
          }
        */
}
