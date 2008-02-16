package com.ulearning.ulms.tools.report.custom.conditionitemrelation.bean;

import com.ulearning.ulms.tools.report.custom.conditionitemrelation.exceptions.ConditionItemRelationDAOSysException;
import com.ulearning.ulms.tools.report.custom.conditionitemrelation.dao.ConditionItemRelationDAOFactory;
import com.ulearning.ulms.tools.report.custom.conditionitemrelation.dao.ConditionItemRelationDAO;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-21
 * Time: 11:13:51
 * To change this template use File | Settings | File Templates.
 */
public class ConditionItemRelationHelper
{
        public String getConditionItemRelation(int reportID) throws ConditionItemRelationDAOSysException
        {
                String relation = null;
                try
                {
                        ConditionItemRelationDAO dao = ConditionItemRelationDAOFactory.getDAO();
                        relation = dao.getConditionItemRelation(reportID);
                }
                catch (ConditionItemRelationDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return relation;
        }
}
