package com.ulearning.ulms.content.schoolbook.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * @author sunny sky
 * @author bill thomas
 *         <p/>
 *         <ul>
 *         <li>Title: UrlProperty.java</li>
 *         <li>description: Handler the url and it's parameters for page facitily.</li>
 *         <ul>
 */
public class UrlProperty
{
        private String baseUrl = ""; // page url
        private String seperator = "&"; // parameter seperator in url.
        private Map paraMap = new HashMap(); // store the parameters.

        public UrlProperty(String baseUrl)
        {
                baseUrl = baseUrl.replaceAll("&", "&");
                this.baseUrl = baseUrl.replaceAll("&", "&");
        }

        public UrlProperty()
        {
        }

        public String getSeperator()
        {
                return seperator;
        }

        public void setSeperator(String seperator)
        {
                this.seperator = seperator;
        }

        /**
         * Append single new parameter to this url.
         *
         * @param key
         * @param value
         */
        public void put(String key, String value)
        {
                paraMap.put(key, value);
        }

        /**
         * Append parameters from request.getParameterMap() to this url.
         *
         * @param map
         */
        public void put(Map map)
        {
                paraMap.putAll(map);
        }

        public String get(String key)
        {
                return (String) paraMap.get(key);
        }

        public String getBaseUrl()
        {
                return baseUrl;
        }

        public void setBaseUrl(String rawUrl)
        {
                this.baseUrl = rawUrl.replaceAll("&", "&");
        }

        /**
         * Main method of the class, create the url with all parameters in paraMap.
         *
         * @return The url with corresponding parameters. Such as
         *         "http://www.best.com?a=XXX&amp;b=XXX".
         */
        public String getUrl()
        {
                StringBuffer sb = new StringBuffer(baseUrl);

                if (baseUrl.indexOf("?") > -1)
                {
                        if (baseUrl.indexOf("=") > -1)
                        {
                                sb.append(seperator);
                        }
                }
                else
                {
                        sb.append("?");
                }

                Set keySet = paraMap.keySet();

                for (Iterator iter = keySet.iterator(); iter.hasNext();)
                {
                        String key = (String) iter.next();
                        Object tmp = paraMap.get(key);
                        String value = "";

                        if (tmp instanceof String[])
                        {
                                String[] values = (String[]) tmp;

                                // for (int i = 0; i < values.length; i++) {
                                if (values.length > 0)
                                {
                                        value += values[0];
                                }

                                // if (i != values.length - 1) {
                                // value += ":";
                                // }
                                // }
                        }
                        else
                        {
                                value = (String) tmp;
                        }

                        sb.append(key).append("=").append(value).append(seperator);
                }

                String url = null;
                String temp = sb.toString();
                url = temp.substring(0, temp.length() - 1);

                return url;
        }

        /*
        public static void main(String args[]) {
                String str = "sldkfjsldf";
                String temp = null;
                temp = str.substring(0, str.length()-1);
                System.out.println(temp);
        }*/
}
