/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 14, 2004
 * Time: 10:51:43 AM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.util.log;

public class DebugUtil
{

        public static boolean DEBUG = false;

        /**
         * Print stack trace if the debug mode is true.
         *
         * @param e
         */
        public static void print(Exception e)
        {
                if (DEBUG)
                {
                        e.printStackTrace();
                }
        }

        /**
         * Print the system info for development debug
         *
         * @param info
         */
        public static void print(String info)
        {
                if (DEBUG)
                {
                        System.out.println(info);
                }
        }

}
