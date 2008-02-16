/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-24
 * Time: 11:28:41
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.util;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.FormatUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class DbInputUtil
{
        private static HSSFCell cell = null;

        public static String getThisCellValue(HSSFRow row, int col) throws ULMSSysException
        {
                String returnValue = "";
                try
                {
                        cell = row.getCell((short) col);
                        returnValue = cell.getStringCellValue();
                }
                catch (Exception e)
                {
                        //throw new ULMSSysException(e);
                }
                if (returnValue == null)
                {
                        returnValue = "";
                }
                returnValue = dealVarchar(returnValue);
                return returnValue;
        }

        public static String dealVarchar(String str1)
        {
                String res = str1;
                if (!str1.equals(""))
                {
                        res = FormatUtil.replaceString(res, "", "");
                        res = FormatUtil.replaceString(res, "/", "/");
                        res = FormatUtil.replaceString(res, "（", "(");
                        res = FormatUtil.replaceString(res, "）", ")");
                        res = res.trim();
                }
                return res;
        }

        //判断strEmail是否为Email格式
        public static boolean isEmail(String strEmail)
        {
                String temp = new String(strEmail);
                boolean re = false;
                String ss = "@";
                int m = 0;
                int n = 0;
                for (int i = 0; i < temp.length(); i++)
                {
                        if (temp.charAt(i) == ss.charAt(0))
                        {
                                m = m + 1;
                                n = i;
                        }
                }

                String kk = ".";
                int k = 0;
                for (int i = 0; i < temp.length(); i++)
                {
                        if (temp.charAt(i) == kk.charAt(0))
                        {
                                k = i;
                        }
                }

                if ((m == 1) && n > 0 && (n < temp.length() - 1) && k > n + 1)
                {
                        re = true;
                }
                return re;
        }


        public static boolean isNumeric(String ps)
        {
                String temp = new String(ps);
                String repStr = "1234567890.";
                String space = " ";
                for (int i = 0; i < repStr.length(); i++)
                {
                        temp = temp.replace(repStr.charAt(i), space.charAt(0));
                }
                temp = temp.trim();

                boolean re = false;
                if (temp.equals(""))
                {
                        re = true;
                }
                return re;
        }

}
