package com.ulearning.ulms.admin.teacher.dao;

import com.ulearning.ulms.admin.teacher.exception.DaoException;
import com.ulearning.ulms.admin.teacher.model.GroupShip;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;


/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-16
 * Time: 13:26:08
 * To change this template use File | Settings | File Templates.
 */
public class GroupShipDAO
{
        public static final Log log = LogFactory.getLog(GroupShipDAO.class);
        private static GroupShipDAO instance;

        private GroupShipDAO()
        {
        }

        public static GroupShipDAO getInstance()
        {
                if (instance == null)
                {
                        instance = new GroupShipDAO();
                }

                return instance;
        }

        public void add(GroupShip g) throws DaoException
        {
        }

        public void del(int id) throws DaoException
        {
        }

        public GroupShip update(GroupShip ship) throws DaoException
        {
                return null;
        }

        public GroupShip findbyId(int id) throws DaoException
        {
                return null;
        }

        //根据用户ID查找权限
        public Collection view(String userid) throws DaoException
        {
                return null;
        }
}
