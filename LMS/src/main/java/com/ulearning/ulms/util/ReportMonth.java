/***********************************************************************
 * Module:  ReportMonth.java
 * Author:  liz
 * Modified: 2005��12��31�� 13:15:35
 * Purpose: Defines the Class ReportMonth
 ***********************************************************************/

package com.ulearning.ulms.util;

import java.util.Map;

/**
 * ���������¶�Ӧ�����ڶΡ�
 */
public class ReportMonth
{
        private intfcmonth state;

        /**
         * @param state
         */
        public void setState(intfcmonth state)
        {
                // TODO: implement
                this.state = state;
        }

        /**
         * @param monthmark Ҫͳ�Ƶ��·ݣ�1��2��3...12��
         *                  <p/>
         *                  Map ����һ���µĿ�ʼ�ͽ�������key=beginDate �͡�endDate
         */
        public Map makeDateRange(int monthmark)
        {
                // TODO: implement
                return state.makeDateRange(monthmark);
        }

}