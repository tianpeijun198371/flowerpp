package com.ulearning.ulms.tools.visit.dao;

import com.ulearning.ulms.tools.visit.model.VisitModel;
import com.ulearning.ulms.tools.visit.exceptions.VisitDAOSysException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jacky
 * Date: 2007-12-28
 * Time: 11:10:07
 * To change this template use File | Settings | File Templates.
 */
public interface VisitDAO
{
        public int addVisit(VisitModel details) throws VisitDAOSysException;

        public void saveVisit(VisitModel details) throws VisitDAOSysException;

        public void updateVisit(VisitModel details) throws VisitDAOSysException;

        public VisitModel getVisit(int visitID) throws VisitDAOSysException;

        public VisitModel getVisit(int type, int relationID) throws VisitDAOSysException;

        public List getVisitList(int type, int rowNum) throws VisitDAOSysException;

}
