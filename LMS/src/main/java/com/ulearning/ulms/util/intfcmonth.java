/***********************************************************************
 * Module:  intfcmonth.java
 * Author:  liz
 * Modified: 2005��12��31�� 11:09:01
 * Purpose: Defines the Interface intfcmonth
 ***********************************************************************/

package com.ulearning.ulms.util;

import java.util.*;

/**
 * ��������ʱ��Σ�����������ͳ��ʱʹ�ã����û�ѡ��ĳ���£������û���ѡ�������Ӧ�����ڶΣ�����ѯͳ����ʹ�á�
 *
 * @author Liz
 * @ date 2005.12.31
 */
public interface intfcmonth
{
        /**
         * @param monthmark Ҫͳ�Ƶ��·ݣ�1��2��3...12��
         *                  <p/>
         *                  Map ����һ���µĿ�ʼ�ͽ�������key=beginDate �͡�endDate
         */
        java.util.Map makeDateRange(int monthmark);

}