/***********************************************************************
 * Module:  monthfinance.java
 * Author:  liz
 * Modified: 2005��12��31�� 11:11:06
 * Purpose: Defines the Class monthfinance
 ***********************************************************************/

package com.ulearning.ulms.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ��������������ڶ�
 *
 * @author Liz
 * @ date 2005.12.31
 */
public class monthfinance implements intfcmonth
{
        /**
         * @param monthmark Ҫͳ�Ƶ��·ݣ�1��2��3...12��
         *                  <p/>
         *                  Map ����һ���µĿ�ʼ�ͽ�������key=beginDate �͡�endDate
         */
        public Map makeDateRange(int monthmark)
        {
                // TODO: implement
                Map map = null;
                switch (monthmark)
                {
                        case 1:
                                map = new HashMap();
                                return map;
                        case 2:
                                map = new HashMap();
                                return map;
                        case 3:
                                map = new HashMap();
                                return map;
                        case 4:
                                map = new HashMap();
                                return map;
                        case 5:
                                map = new HashMap();
                                return map;
                        case 6:
                                map = new HashMap();
                                return map;
                        case 7:
                                map = new HashMap();
                                return map;
                        case 8:
                                map = new HashMap();
                                return map;
                        case 9:
                                map = new HashMap();
                                return map;
                        case 10:
                                map = new HashMap();
                                return map;
                        case 11:
                                map = new HashMap();
                                return map;
                        case 12:
                                map = new HashMap();
                                return map;
                }
                return map;
        }

}