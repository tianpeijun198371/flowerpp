package com.ulearning.ulms.admin.teacher.dao;

import com.ulearning.ulms.admin.teacher.exception.DaoException;
import com.ulearning.ulms.admin.teacher.model.TeacherGroup;

/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-16
 * Time: 13:49:32
 * To change this template use File | Settings | File Templates.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;


public class TeacherGroupDAO
{
        private static final Log log = LogFactory.getLog(TeacherGroupDAO.class);
        private static TeacherGroupDAO instance;

        private TeacherGroupDAO()
        {
        }

        public static TeacherGroupDAO getInstance()
        {
                if (instance == null)
                {
                        instance = new TeacherGroupDAO();
                }

                return instance;
        }

        public void add(TeacherGroup t) throws DaoException
        {
        }

        public void del(int i) throws DaoException
        {
        }

        public TeacherGroup update(TeacherGroup t) throws DaoException
        {
                return null;
        }

        public TeacherGroup findbyId(int id) throws DaoException
        {
                return null;
        }

        //根据师资关系ID查询师资库
        public Collection view(int groupid) throws DaoException
        {
                return null;
        }
}
