package com.ulearning.ulms.core.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintStream;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspWriter;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2007-8-10
 * Time: 11:23:18
 * To change this template use File | Settings | File Templates.
 */
public class BeanUtil
{
        protected static Log log = LogFactory.getLog(BeanUtil.class);

        //打印对象object里的属性的值
        public static void print(Object object, JspWriter out)
                throws Exception
        {
                Map m = BeanUtils.describe(object);
                Iterator it = m.keySet().iterator();

                while (it.hasNext())
                {
                        String key = (String) it.next();
                        out.print("BeanUtil:" + key + "=" + m.get(key) + "<br>");
                }
        }

        //打印对象object里的属性的值
        public static void print(Object object, PrintStream out)
                throws Exception
        {
                Map m = BeanUtils.describe(object);
                Iterator it = m.keySet().iterator();

                while (it.hasNext())
                {
                        String key = (String) it.next();
                        out.print("BeanUtil:" + key + "=" + m.get(key) + "\n");
                }
        }

        //打印对象object里的属性的值
        public static void printToSystemOut(Object object)
                throws Exception
        {
                print(object, System.out);
        }

        //打印对象object里的属性的值
        public static void log(Object object, PrintStream out)
                throws Exception
        {
                Map m = BeanUtils.describe(object);
                Iterator it = m.keySet().iterator();

                while (it.hasNext())
                {
                        String key = (String) it.next();
                        log.info("BeanUtil:" + key + "=" + m.get(key) + " ");
                }
        }
}
