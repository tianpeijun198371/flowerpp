/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.user.action;

import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.blog.helper.BlogHelper;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelUserAction extends Action
{

        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";


                String[] userIDList = request.getParameterValues("userID");

                if ((userIDList != null) || (userIDList.length > 0))
                {
                        for (int i = 0; i < userIDList.length; i++)
                        {
                                UserDAO userDao = UserDAOFactory.getDAO();
                                userDao.removeUser(userIDList[i]);

                                BlogHelper.deleteAccount(userDao.getUser(userIDList[i]).getLoginName());
                                /*                              try
                                {
                                        if(Config.getIsIntegrateJieFo())
                                        {
                                          dao.removeJieFoUser(userIDList[i]);
                                        }
                                }
                                catch (Exception e)
                                {
                                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                }*/

                        }
                }
                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
