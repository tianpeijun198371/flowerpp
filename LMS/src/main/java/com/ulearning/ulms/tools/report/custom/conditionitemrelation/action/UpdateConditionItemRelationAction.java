package com.ulearning.ulms.tools.report.custom.conditionitemrelation.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.custom.conditionitemrelation.form.ConditionItemRelationForm;
import com.ulearning.ulms.tools.report.custom.conditionitemrelation.dao.ConditionItemRelationDAO;
import com.ulearning.ulms.tools.report.custom.conditionitemrelation.dao.ConditionItemRelationDAOFactory;
import com.ulearning.ulms.util.log.LogUtil;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-21
 * Time: 11:13:24
 * To change this template use File | Settings | File Templates.
 */
public class UpdateConditionItemRelationAction
{
        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                ConditionItemRelationForm crf = (ConditionItemRelationForm) form;
                ConditionItemRelationDAO dao = ConditionItemRelationDAOFactory.getDAO();
                dao.updateConditionItemRelation(crf);

                LogUtil.info("system", "[AddDocumentAction]===========resultScreen = "
                        + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
