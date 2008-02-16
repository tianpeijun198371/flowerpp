package com.ulearning.ulms.content.uteachergroup.action;

import com.ulearning.ulms.user.dao.UserDAOImpl;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.form.UserForm;


/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-19
 * Time: 14:20:54
 * To change this template use File | Settings | File Templates.
 */
public class TeacherHandler extends UserDAOImpl
{
        //pass
        public int addUser(UserForm details) throws UserDAOSysException
        {
                return super.addUser(details); //To change body of overridden methods use File | Settings | File Templates.
        }
}
