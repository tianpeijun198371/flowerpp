/**
 *   Created by yud      Date:2005.04.10
 * FunctionConfigAction.java
 */
package com.ulearning.ulms.portal.fuctionconfig.action;

import com.ulearning.ulms.portal.fuctionconfig.dao.FunctionConfigDAO;
import com.ulearning.ulms.portal.fuctionconfig.dao.FunctionConfigDaolmpl;
import com.ulearning.ulms.portal.fuctionconfig.helper.FunctionHelper;
import com.ulearning.ulms.portal.fuctionconfig.model.FunctionConfigModel;
import com.ulearning.ulms.portal.fuctionconfig.util.FunctionConfigConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FunctionConfigAction extends Action
{
        FunctionHelper functionHelper = new FunctionHelper();
        FunctionConfigDAO functionConfigDAO = new FunctionConfigDaolmpl();

        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Exception
        {
                String resultScreen = "success";

                List l = functionHelper.getAll(FunctionConfigConstants.PORTAL_TYPE);

                //String documentIDs[] = httpServletRequest.getParameterValues("documentIDs");
                for (int i = 0; i < l.size(); i++)
                {
                        String[] documentIDs = httpServletRequest.getParameterValues(
                                "documentIDs[" + i + "]");
                        FunctionConfigModel ndm = (FunctionConfigModel) l.get(i);

                        if (documentIDs != null)
                        {
                                ndm.setIsAvailable("1");
                        }
                        else
                        {
                                ndm.setIsAvailable("0");
                        }
                }

                functionHelper.update(l);

                return actionMapping.findForward(resultScreen);
        }
}
