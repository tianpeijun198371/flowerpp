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
        public static final String SHIP_OFFER = "remark1"; //���ݹ�Ӧ�̹����ϵ�ֶ�
        public static final String SHIP_TECH = "remark2"; //���ݽ�ʦ���ܹ����ϵ�ֶ�
        public static final String SHIP_FEE = "remark9"; //�շѵ�����������ϵ�ֶ�
        public static final String OFFER_DO = "manageship.do?method=offer"; //�����ַ
        public static final String TECH_DO = "manageship.do?method=tech";
        public static final String FEE_DO = "manageship.do?method=fee";

        private ManageShipHandler()
        {
        }

        //����һ���ϵ�������ϵ����
        public static void insertShip(int[] shipGroupId) throws DaoException
        {
        }

        /*����һ���ϵ�����ι�ϵ���󣬿��ǵ��û�����Ƶ���޸Ĺ����ϵ����������������ʵ�֣����
          ����Ķ�Ӧ��ϵ�޸�Ƶ������������������µķ�ʽ
        */
        public static void cancelShip(int[] shipGroupId) throws DaoException
        {
        }

        //���ڹ���ҳ������б�
        public static Vector getGroupShipList(int userId) throws DaoException
        {
                //Ҫ������������
                return null;
        }
}
