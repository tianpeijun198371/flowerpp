package com.ulearning.ulms.admin.teacher.util;

import com.ulearning.ulms.admin.teacher.exception.DaoException;

/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-16
 * Time: 14:17:05
 * To change this template use File | Settings | File Templates.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Vector;


public class ManageShipHandler
{
        private static final Log log = LogFactory.getLog(ManageShipHandler.class);
        public static final String SHIP_OFFER = "remark1"; //根据供应商管理关系字段
        public static final String SHIP_TECH = "remark2"; //根据教师技能管理关系字段
        public static final String SHIP_FEE = "remark9"; //收费单价情况管理关系字段
        public static final String OFFER_DO = "manageship.do?method=offer"; //处理地址
        public static final String TECH_DO = "manageship.do?method=tech";
        public static final String FEE_DO = "manageship.do?method=fee";

        private ManageShipHandler()
        {
        }

        //根据一组关系键插入关系对象
        public static void insertShip(int[] shipGroupId) throws DaoException
        {
        }

        /*根据一组关系键屏蔽关系对象，考虑到用户不会频繁修改管理关系所以用这两个方法实现，如果
          管理的对应关系修改频繁，建议采用批量更新的方式
        */
        public static void cancelShip(int[] shipGroupId) throws DaoException
        {
        }

        //用于管理页面组的列表
        public static Vector getGroupShipList(int userId) throws DaoException
        {
                //要根据组名排重
                return null;
        }
}
