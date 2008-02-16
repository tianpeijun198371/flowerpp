/**
 * Created by IntelliJ IDEA.
 * Delete: dengj
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.delete.action;

import com.ulearning.ulms.tools.delete.dao.DeleteDAO;
import com.ulearning.ulms.tools.delete.dao.DeleteDAOFactory;
import com.ulearning.ulms.tools.delete.form.DeleteForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateDeleteAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                DeleteForm uf = (DeleteForm) form;
                DeleteDAO dao = DeleteDAOFactory.getDAO();
                String[] deleteID = request.getParameterValues("deleteID");
                String[] objectType = request.getParameterValues("objectType");
                String[] state = request.getParameterValues("state");
                String[] saveTimeNum = request.getParameterValues("saveTimeNum");
                String[] timeType = request.getParameterValues("timeType");
                String[] saveRows = request.getParameterValues("saveRows");

                for (int i = 0; i < deleteID.length; i++)
                {
                        uf.setDeleteID(Integer.parseInt(deleteID[i]));
                        uf.setObjectType(objectType[i]);
                        uf.setState(state[i]);
                        uf.setSaveTimeNum(Integer.parseInt(saveTimeNum[i]));
                        uf.setTimeType(timeType[i]);
                        uf.setSaveRows(Integer.parseInt(saveRows[i]));
                        uf.setUpdateDate(new Date());

                        if (deleteID[i].equals("0"))
                        {
                                dao.addDelete(uf);
                        }
                        else
                        {
                                dao.updateDelete(uf);
                        }
                }

                LogUtil.info("admin",
                        "[UpdateDeleteAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
