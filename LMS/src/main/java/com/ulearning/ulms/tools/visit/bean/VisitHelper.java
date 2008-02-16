package com.ulearning.ulms.tools.visit.bean;

import com.ulearning.ulms.tools.visit.dao.VisitDAO;
import com.ulearning.ulms.tools.visit.dao.VisitDAOFactory;
import com.ulearning.ulms.tools.visit.exceptions.VisitDAOSysException;
import com.ulearning.ulms.tools.visit.model.VisitModel;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.CourseModel;

import javax.servlet.jsp.PageContext;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Jacky
 * Date: 2007-12-28
 * Time: 15:39:41
 * To change this template use File | Settings | File Templates.
 */
public class VisitHelper {
    /**
     * Adds visitNum to database according to type(see ),ralationID and visitAdd
     *
     * @param type
     * @param relationID
     * @param visitAdd
     * @return
     */
    public static int addVisit(int type, int relationID, int visitAdd) {
        int result = 0;
        try {
            VisitDAO visitDAO = VisitDAOFactory.getDAO();
            VisitModel vm = visitDAO.getVisit(type, relationID);
            if (vm != null) {
                int visitNum = vm.getVisitNum();
                vm.setVisitNum(visitNum + visitAdd);
                visitDAO.updateVisit(vm);
            } else {
                vm = new VisitModel();
                vm.setType(type);
                vm.setRelationID(relationID);
                vm.setVisitNum(visitAdd);
                visitDAO.saveVisit(vm);
            }
        }
        catch (VisitDAOSysException udse) {
            udse.printStackTrace();
        }
        return result;
    }

    /**
     * Gets VisitModel list order by visit num
     *
     * @param type
     * @param rowNum
     * @return VisitModel list,default is an empty list
     */
    public static List getVisitList(int type, int rowNum) {
        List result = new ArrayList();
        try {
            VisitDAO visitDAO = VisitDAOFactory.getDAO();
            result = visitDAO.getVisitList(type, rowNum);
        }
        catch (VisitDAOSysException udse) {
            udse.printStackTrace();
        }
        return result;
    }

    /**
     * @param type
     * @param rowNum
     * @param pageContext
     * @return
     */
    public static List getVisitList(int type, int rowNum, PageContext pageContext) {
        List result = new ArrayList();
        try {
            Object visitObject = null;
            if (type == LMSConstants.VISIT_TEACHER) {
                visitObject = pageContext.getServletContext().getAttribute(LMSConstants.VISIT_TEACHER_LIST + rowNum);
                if (visitObject != null) {
                    result = (List) (visitObject);
                } else {
                    result = getVisitList(LMSConstants.VISIT_TEACHER, rowNum);
                    if (result != null) {
                        List firstList = new ArrayList();
                        for (int i = 0; i < result.size(); i++) {
                            VisitModel vm = (VisitModel) result.get(i);
                            UserForm userForm = UserHelper.getUser(new Integer(vm.getRelationID()).toString());
                            firstList.add(userForm);
                        }
                        result = firstList;
                    }
                    pageContext.getServletContext().setAttribute(LMSConstants.VISIT_TEACHER_LIST + rowNum, result);
                }
            } else if (type == LMSConstants.VISIT_SCHOOL) {
                visitObject = pageContext.getServletContext().getAttribute(LMSConstants.VISIT_SCHOOL_LIST + rowNum);
                if (visitObject != null) {
                    result = (List) (visitObject);
                } else {
                    result = getVisitList(LMSConstants.VISIT_SCHOOL, rowNum);
                    if (result != null) {
                        List firstList = new ArrayList();
                        for (int i = 0; i < result.size(); i++) {
                            VisitModel vm = (VisitModel) result.get(i);
                            OrganForm organForm = OrganHelper.getOrgan(vm.getRelationID());
                            firstList.add(organForm);
                        }
                        result = firstList;
                    }
                    pageContext.getServletContext().setAttribute(LMSConstants.VISIT_SCHOOL_LIST + rowNum, result);
                }

            } else if (type == LMSConstants.VISIT_CLASS) {
                visitObject = pageContext.getServletContext().getAttribute(LMSConstants.VISIT_CLASS_LIST + rowNum);
                if (visitObject != null) {
                    result = (List) (visitObject);
                } else {
                    result = getVisitList(LMSConstants.VISIT_CLASS, rowNum);
                    if (result != null) {
                        List firstList = new ArrayList();
                        for (int i = 0; i < result.size(); i++) {
                            VisitModel vm = (VisitModel) result.get(i);
                            CourseModel courseModel = CourseHelper.getCourse(vm.getRelationID());
                            firstList.add(courseModel);
                        }
                        result = firstList;
                    }
                    pageContext.getServletContext().setAttribute(LMSConstants.VISIT_CLASS_LIST + rowNum, result);
                }
            }
        }
        catch (VisitDAOSysException udse) {
            udse.printStackTrace();
        }
        return result;
    }

    /**
     * Gets VisitModel list order by visit num
     *
     * @param type
     * @param relationID
     * @return VisitModel list,default is an empty list
     */
    public static VisitModel getVisit(int type, int relationID) {
        VisitModel vm = null;
        try {
            VisitDAO visitDAO = VisitDAOFactory.getDAO();
            vm = visitDAO.getVisit(type, relationID);
        }
        catch (VisitDAOSysException udse) {
            udse.printStackTrace();
        }
        return vm;
    }

    public static void main(String[] arg) {
        VisitModel vm = VisitHelper.getVisit(2, 660);
        System.out.println("vm.getVisitNum() = " + vm.getVisitNum());
    }

}
