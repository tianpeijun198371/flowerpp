package com.ulearning.ulms.core.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.model.OrganModel;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Jacky
 * Date: 2007-11-14
 * Time: 10:12:03
 * To change this template use File | Settings | File Templates.
 */
public class LMSPortal extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String resultScreen = "mainPortal";
        LogUtil.debug("portal",
                "[LMSPortal]Entered LMSPortal Action:perform()");

        String url = request.getRequestURL().toString();
        int namePlace = url.indexOf('.');
        String userName = url.substring(7, namePlace);

        //judge the second school main page first
        //Master page
        OrganModel organ = OrganHelper.getOrganByDomain(userName);
        if(organ != null){
            resultScreen = "masterPortal";
            request.setAttribute("aspID", new Integer(organ.getAspid()));
            return mapping.findForward(resultScreen);
        }

        int userID = UserHelper.getUserID(userName);

        if (userID <= 0) {
            resultScreen = "mainPortal";
            int classID = 0;
            CourseModel course = CourseHelper.getCourse(0, userName);
            //Class main page
            if (course != null) {
                classID = course.getCourseID();
                resultScreen = "classPortal";
                request.setAttribute("courseID", new Integer(classID));
            }
        } else {
            int roleID = SecurityHelper.getUserRoleForPortal(userID, SecurityConstants.DEFAULT_RELATIONID_PLATFORM, SecurityConstants.ROLE_SCOPE_SYSTEM);
            //Master page
            //this logic is changed
            /*if (roleID == SecurityConstants.ROLE_TRAINING_ADMINISTRATOR) {
                resultScreen = "masterPortal";
                UserForm uf = UserHelper.getUser(userID + "");
                if (uf != null) {
                    request.setAttribute("aspID", new Integer(uf.getCatalogID()));
                }
                request.setAttribute("aspID", new Integer(userID));
                return mapping.findForward(resultScreen);
            }*/
            //Teacher page
            if (roleID == SecurityConstants.ROLE_COURSE_TEACHER) {
                resultScreen = "teacherPortal";
                request.setAttribute("userID", new Integer(userID));
                return mapping.findForward(resultScreen);
            }
            //Student page
            if (roleID == SecurityConstants.ROLE_COURSR_STUDENT) {
                resultScreen = "studentPortal";
                request.setAttribute("userID", new Integer(userID));
                return mapping.findForward(resultScreen);
            }
        }
        LogUtil.debug("portal",
                "[LMSPortal]--resultScreen=" + resultScreen);
        // Forward back to the test page
        return mapping.findForward(resultScreen);
    }
}
