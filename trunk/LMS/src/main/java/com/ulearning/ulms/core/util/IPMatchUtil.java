/**
 * IpMatchUtil.java.
 * User: fengch  Date: 2007-6-23 w
 *
 * Copyright (c) 2000-2004.Wenhua online Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.perl.Perl5Util;

/**
 * define the IpMatchUtil.java Create at 2006-4-21 16:23:43
 * <p>
 * 
 */
public class IPMatchUtil {

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(IPMatchUtil.class);

	/**
	 * 拆分";"后得到单元格式,单独一个个匹配
	 * @param match
	 * @param ip
	 * @return
	 */
	public static boolean matchIpCell(String match, String ip) {
		try {
			Perl5Util perl = new Perl5Util();
			// 如果有"~"就代表是范围,这个时候默认为不会出现172.16.1.*
			if (match.indexOf("~") != -1) {
				String matchCell[] = match.split("~");
				String cell1 = matchCell[0].trim();
				String cell2 = matchCell[1].trim();
				// 得到172.16.1.2的前3位,172.16.1.
				String temp = cell1.substring(0, cell1.lastIndexOf(".") + 1);
				temp += "*";
				// 如果匹配172.16.1.*,再比较是不是在范围内
				if (perl.match("/" + temp + "/", ip)) {
					int start = NumberUtils.toInt(cell1.substring(cell1.lastIndexOf(".") + 1));
					int end =  NumberUtils.toInt(cell2.substring(cell2.lastIndexOf(".") + 1));
					int ipEnd =  NumberUtils.toInt(ip.substring(ip.lastIndexOf(".") + 1));
					if (ipEnd >= start && ipEnd <= end) {
						return true;
					}
				}
			}
			// 这里代表只有一个格式
			else {
				if (perl.match("/" + match.trim() + "/", ip)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return false;
		}
	}

	/**
	 * 匹配ip是否在ipMatch格式内,172.16.1.25 ~ 172.16.1.28 ;172.16.1.6;172.16.2.*;的格式
	 * @param ipMatch 172.16.1.25 ~ 172.16.1.28 ;172.16.1.6;172.16.2.*;
	 * @param ip 172.16.2.6
	 * @return
	 */
	public static boolean matchIp(String ipMatch, String ip) {
		if (ipMatch != null) {
			if(ipMatch.trim().equals(""))
			{
				return true;
			}
			String matchCell[] = ipMatch.split(";");
			for (int i = 0; i < matchCell.length; i++) {
				if (matchIpCell(matchCell[i], ip)) {
					return true;
				}
			}
		}
		else 
		{
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {

		
		IPMatchUtil st = new IPMatchUtil();
		
		System.out.println(st.matchIp("172.16.1.25 ~ 172.16.1.28 ;172.16.1.6;172.16.2.*; ","172.16.2.6"));
                System.out.println(st.matchIp("172.16.1.25 ~ 172.16.1.28 ;172.16.1.6;172.16.2.*; ","172.16.1.27"));
                System.out.println(st.matchIp("172.16.1.25 ~ 172.16.1.28 ;172.16.1.6;172.16.2.*; ","172.16.1.25"));
                System.out.println(st.matchIp("172.16.1.25 ~ 172.16.1.28 ;172.16.1.6;172.16.2.*; ","172.16.1.28"));
                System.out.println(st.matchIp("172.16.1.25 ~ 172.16.1.28 ;172.16.1.6;172.16.2.*; ","172.16.1.29"));
                System.out.println(st.matchIp("172.16.1.25 ~ 172.16.1.28 ;172.16.1.6;172.16.2.*; ","172.16.1.24"));
        }

}
