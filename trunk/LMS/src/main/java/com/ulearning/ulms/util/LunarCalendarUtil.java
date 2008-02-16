/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * LunarCalendar.java .
 * <p/>
 * User: keyh * Date: 2004-9-14
 * Time: 16:58:49
 * <p/>
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

public class LunarCalendarUtil
{
        //日期资料
        int[] lunarInfo = {
                0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0, 0x55d2,
                0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd295, 0xb54f, 0xd6a0, 0xada2, 0x95b0, 0x4977,
                0x497f, 0xa4b0, 0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970,
                0x6566, 0xd4a0, 0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f,
                0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2, 0xa950, 0xb557,
                0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0,
                0xaea6, 0xab50, 0x4b60, 0xaae4, 0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0,
                0x96d0, 0x4dd5, 0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6,
                0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40, 0xaf46, 0xab60, 0x9570,
                0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0,
                0xc960, 0xd954, 0xd4a0, 0xda50, 0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5,
                0xa950, 0xb4a0, 0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930,
                0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260, 0xea65, 0xd530,
                0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520, 0xdd45,
                0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0,
                0x4b63, 0x937f, 0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef,
                0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4,
                0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50, 0x55a0, 0xaba4, 0xa5b0, 0x52b0,
                0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260,
                0xe968, 0xd520, 0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252,
                0xd520};
        int[] solarMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] Gan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
        String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
        String[] Animals = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        int[] sTermInfo = {0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758};
        String[] nStr1 = {"日", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
        String[] nStr2 = {"初", "十", "廿", "卅", "□"};
        String[] monthName = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

        public static Map solarTerm = new HashMap();

        private void addsolarTerm()
        {
                solarTerm.put("02041946", "立春");
                solarTerm.put("1217", "立春");
                solarTerm.put("02191539", "雨水");
                solarTerm.put("0102", "雨水");
                solarTerm.put("03051356", "惊蛰");
                solarTerm.put("0117", "惊蛰");
                solarTerm.put("03200000", "春分");
                solarTerm.put("0203", "春分");
                solarTerm.put("04050000", "清明");
                solarTerm.put("0218", "清明");
                solarTerm.put("01200211", "谷雨");
                solarTerm.put("0304", "谷雨");
                solarTerm.put("05051223", "立夏");
                solarTerm.put("0320", "立夏");
                solarTerm.put("05210133", "小满");
                solarTerm.put("0405", "小满");
                solarTerm.put("06050000", "芒种");
                solarTerm.put("0421", "芒种");
                solarTerm.put("06210939", "夏至");
                solarTerm.put("0508", "夏至");
                solarTerm.put("07070318", "小暑");
                solarTerm.put("0523", "小暑");
                solarTerm.put("07222030", "大暑");
                solarTerm.put("0610", "大暑");

                solarTerm.put("08070000", "立秋");
                solarTerm.put("0626", "立秋");
                solarTerm.put("08230328", "处暑");
                solarTerm.put("0711", "处暑");
                solarTerm.put("09071544", "白露");
                solarTerm.put("0727", "白露");
                solarTerm.put("09230052", "秋分");
                solarTerm.put("0813", "秋分");
                solarTerm.put("10080708", "寒露");
                solarTerm.put("0829", "寒露");
                solarTerm.put("10230000", "霜降");
                solarTerm.put("0914", "霜降");

                solarTerm.put("11070000", "立冬");
                solarTerm.put("0929", "立冬");
                solarTerm.put("11220120", "小雪");
                solarTerm.put("1014", "小雪");
                solarTerm.put("12070243", "大雪");
                solarTerm.put("1028", "大雪");
                solarTerm.put("12212033", "冬至");
                solarTerm.put("1113", "冬至");
                solarTerm.put("01050000", "小寒");
                solarTerm.put("1118", "小寒");
                solarTerm.put("01200000", "大寒");
                solarTerm.put("1202", "大寒");

        }

        //公历节日 *表示放假日
        public static Map xFtv = new HashMap();

        private void addxFtv()
        {
                xFtv.put("0101", "新年元旦");

                //xFtv.put("0202", "世界湿地日");
                //xFtv.put("0207", "国际声援南非日");
                //xFtv.put("0210", "国际气象节");
                xFtv.put("0214", "情人节");

                xFtv.put("0301", "国际海豹日");
                xFtv.put("0303", "全国爱耳日");
                xFtv.put("0308", "国际妇女节");
                xFtv.put("0312", "植树节 孙中山逝世纪念日");
                ////xFtv.put("0314", "国际警察日");
                xFtv.put("0315", "国际消费者权益日");
                xFtv.put("0317", "中国国医节 国际航海日");
                //xFtv.put("0321", "世界森林日 消除种族歧视国际日 世界儿歌日");
                //xFtv.put("0322", "世界水日");
                //xFtv.put("0323", "世界气象日");
                //xFtv.put("0324", "世界防治结核病日");
                xFtv.put("0325", "全国中小学生安全教育日");
                //xFtv.put("0330", "巴勒斯坦国土日");

                //xFtv.put("0401", "愚人节 全国爱国卫生运动月(四月) 税收宣传月(四月");
                //xFtv.put("0407", "世界卫生日");
                //xFtv.put("0422", "世界地球日");
                // xFtv.put("0423", "世界图书和版权日");
                //xFtv.put("0424", "亚非新闻工作者日");

                xFtv.put("0501", "国际劳动节");
                xFtv.put("0504", "中国五四青年节");
                //xFtv.put("0505", "碘缺乏病防治日");
                xFtv.put("0508", "世界红十字日");
                xFtv.put("0512", "国际护士节");
                //xFtv.put("0515", "国际家庭日");
                xFtv.put("0517", "世界电信日");
                //xFtv.put("0518", "国际博物馆日");
                xFtv.put("0520", "全国学生营养日");
                //xFtv.put("0523", "国际牛奶日");
                xFtv.put("0531", "世界无烟日");

                xFtv.put("0601", "国际儿童节");
                //xFtv.put("0605", "世界环境日");
                xFtv.put("0606", "全国爱眼日");
                //xFtv.put("0617", "防治荒漠化和干旱日");
                //xFtv.put("0623", "国际奥林匹克日");
                xFtv.put("0625", "全国土地日");
                //xFtv.put("0626", "国际反毒品日");

                xFtv.put("0701", "中国共产党建党日 世界建筑日");
                //xFtv.put("0702", "国际体育记者日");
                xFtv.put("0707", "中国人民抗日战争纪念日");
                xFtv.put("0711", "世界人口日");
                //xFtv.put("0730", "非洲妇女日");

                xFtv.put("0801", "中国建军节");
                xFtv.put("0808", "中国男子节(爸爸节)");
                xFtv.put("0815", "日本正式宣布无条件投降日");

                //xFtv.put("0908", "国际扫盲日 国际新闻工作者日");
                xFtv.put("0910", "教师节");
                //xFtv.put("0914", "世界清洁地球日");
                //xFtv.put("0916", "国际臭氧层保护日");
                xFtv.put("0918", "九一八事变纪念日");
                xFtv.put("0920", "国际爱牙日");
                //xFtv.put("0927", "世界旅游日");
                //xFtv.put("1001", "国庆节 世界音乐日 国际老人节 国际音乐日");
                xFtv.put("1001", "国庆节");
                //xFtv.put("1002", "国际和平与民主自由斗争日");
                //xFtv.put("1004", "世界动物日");
                xFtv.put("1008", "全国高血压日 世界视觉日");
                //xFtv.put("1009", "世界邮政日 万国邮联日");
                xFtv.put("1010", "辛亥革命纪念日 世界精神卫生日");
                //xFtv.put("1013", "世界保健日 国际教师节");
                //xFtv.put("1014", "世界标准日");
                //xFtv.put("1015", "国际盲人节(白手杖节");
                //xFtv.put("1016", "世界粮食日");
                //xFtv.put("1017", "世界消除贫困日");
                //xFtv.put("1022", "世界传统医药日");
                //xFtv.put("1024", "联合国日 世界发展信息日");
                //xFtv.put("1031", "世界勤俭日");

                xFtv.put("1107", "十月社会主义革命纪念日");
                xFtv.put("1108", "中国记者日");
                xFtv.put("1109", "全国消防安全宣传教育日");
                //xFtv.put("1110", "世界青年节");
                //xFtv.put("1111", "国际科学与和平周(本日所属的一周)");
                xFtv.put("1112", "孙中山诞辰纪念日");
                //xFtv.put("1114", "世界糖尿病日");
                //xFtv.put("1117", "国际大学生节 世界学生节");
                //xFtv.put("1121", "世界问候日 世界电视日");
                //xFtv.put("1129", "国际声援巴勒斯坦人民国际日");

                //xFtv.put("1201", "世界艾滋病日");
                //xFtv.put("1203", "世界残疾人日");
                //xFtv.put("1205", "国际经济和社会发展志愿人员日");
                //xFtv.put("1208", "国际儿童电视日");
                //xFtv.put("1209", "世界足球日");
                //xFtv.put("1210", "世界人权日");
                xFtv.put("1212", "西安事变纪念日");
                //xFtv.put("1213", "南京大屠杀(1937年)纪念日！紧记血泪史");
                //xFtv.put("1221", "国际篮球日");
                //xFtv.put("1224", "平安夜");
                xFtv.put("1225", "圣诞节");
                //xFtv.put("1229", "国际生物多样性日");

        }

        //某月的第几个星期几。  5, 6, 7, 8 表示到数第 1, 2, 3, 4 个星期几
        /*
String[] wFtv = {
"0110 黑人日", "0150 世界麻风日", "0520 国际母亲节", "0530 全国助残日",
"0630 父亲节", "0911 劳动节", "0932 国际和平日", "0940 国际聋人节 世界儿童日",
"0950 世界海事日", "1011 国际住房日", "1013 国际减轻自然灾害日(减灾日)", "1144 感恩节"}; */
        String[] wFtv = {"0520 国际母亲节", "0530 全国助残日", "0630 父亲节", "0911 劳动节", "0932 国际和平日",};


        //农历节日
        public static Map lFtv = new HashMap();

        private void addlFtv()
        {
                lFtv.put("0101", "春节");
                lFtv.put("0115", "元宵节");
                //lFtv.put("0202", "龙抬头节");
                //lFtv.put("0323", "妈祖生辰 (天上圣母诞辰)");
                lFtv.put("0505", "端午节");
                lFtv.put("0707", "七七情人节");
                lFtv.put("0815", "中秋节");
                lFtv.put("0909", "重阳节");
                lFtv.put("1208", "腊八节");
                lFtv.put("1223", "小年");
                lFtv.put("0101", "春节");
        }

        public LunarCalendarUtil()
        {
                addlFtv();
                addxFtv();
                addsolarTerm();
        }

        //====================================== 返回农历 y年的总天数
        public int lYearDays(int y)
        {
                int i, sum = 348;
                for (i = 0x8000; i > 0x8; i >>= 1)
                {
                        sum += ((lunarInfo[y - 1900] & i) != 0) ? 1 : 0;
                }
                return (sum + leapDays(y));
        }

        //====================================== 返回农历 y年闰月的天数
        public int leapDays(int y)
        {
                if (leapMonth(y) != 0)
                {
                        return ((lunarInfo[y - 1899] & 0xf) == 0xf ? 30 : 29);
                }
                else
                {
                        return (0);
                }
        }

        //====================================== 返回农历  y年闰哪个月 1-12 , 没闰返回 0
        public int leapMonth(int y)
        {
                int lm = lunarInfo[y - 1900] & 0xf;
                return (lm == 0xf ? 0 : lm);
        }

        //====================================== 返回农历 y年m月的总天数
        public int monthDays(int y, int m)
        {
                return (!((lunarInfo[y - 1900] & (0x10000 >> m)) != 0) ? 29 : 30);
        }

        public LunarCalendar Lunar(Calendar cal)
        {
                LunarCalendar lc = new LunarCalendar();
                lc.setSolarFestival(getxFtv(cal));
                cal.add(Calendar.DAY_OF_MONTH, 1);

                /*Calendar t_cal=Calendar.getInstance();
           t_cal.set(1900, 0, 31, 0, 0, 0);*/
                int i, leap = 0, temp = 0;
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);

                long offset = (Date.UTC(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0) - Date.UTC(1900, 0, 31, 0, 0, 0)) / 86400000;
                //long offset= cal.get(Calendar.MILLISECOND)-t_cal.get(Calendar.MILLISECOND) ;
                for (i = 1900; i < 2100 && offset > 0; i++)
                {
                        temp = lYearDays(i);
                        offset -= temp;
                }

                if (offset < 0)
                {
                        offset += temp;
                        i--;
                }

                lc.setYear(i);

                leap = leapMonth(i);
                lc.setLeap(false);

                for (i = 1; i < 13 && offset > 0; i++)
                {

                        if (leap > 0 && i == (leap + 1) && lc.isLeap() == false)
                        {
                                --i;
                                lc.setLeap(true);
                                temp = leapDays(lc.getYear());
                        }
                        else
                        {
                                temp = monthDays(lc.getYear(), i);
                        }


                        if (lc.isLeap() && i == (leap + 1))
                        {
                                lc.setLeap(false);
                        }

                        offset -= temp;
                }

                if (offset == 0 && leap > 0 && i == leap + 1)
                {
                        if (lc.isLeap())
                        {
                                lc.setLeap(false);
                        }
                        else
                        {
                                lc.setLeap(true);
                                --i;
                        }
                }

                if (offset < 0)
                {
                        offset += temp;
                        --i;
                }
                lc.setMonth(i);
                lc.setDate((int) (offset + 1));
                lc.setLunarDateString(getLunarDateString(lc));
                lc.setLunarFestival(getlFtv(lc.getMonth(), lc.getDate()));
                lc.setLunarFulDateString(getFulDateString(lc));
                lc.setSolarTerms(getsolarTerms(lc));
                cal.add(Calendar.DAY_OF_MONTH, -1);
                return lc;
        }

        //==============================返回公历 y年某m+1月的天数
        public int solarDays(int y, int m)
        {
                if (m == 1)
                {
                        return (((y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0)) ? 29 : 28);
                }
                else
                {
                        return (solarMonth[m]);
                }
        }

        //============================== 传入 offset 返回干支, 0=甲子
        public String cyclical(int num)
        {
                return (Gan[num % 10] + Zhi[num % 12]);
        }

//===== 某年的第n个节气为几日(从0小寒起算)
        /*    public int sTerm(int y,int n) {
          Date offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) + Date.UTC(1900,0,6,2,5,) );
          return(offDate.getUTCDate());
       } */

        public String getLunarDateString(LunarCalendar lc)
        {
                String lcstr = "";
                int year = lc.getYear();
                int month = lc.getMonth();
                int date = lc.getDate();
                Calendar cal = Calendar.getInstance();
                cal.set(lc.getYear(), lc.getMonth() - 1, lc.getDate());
                if (date == 1)
                {
                        lcstr = n2s(month) + "月";
                        if (monthDays(year, month) == 30)
                        {
                                lcstr += "大";
                        }
                        else if (monthDays(year, month) == 29)
                        {
                                lcstr += "小";
                        }
                }
                else
                {
                        lcstr = convertNum2Str(date);
                }
                return lcstr;
        }

        public String getlFtv(int m, int d)
        {
                String mstr = new Integer(m).toString();
                if (m < 10)
                {
                        mstr = "0" + mstr;
                }
                String dstr = new Integer(d).toString();
                if (d < 10)
                {
                        dstr = "0" + dstr;
                }
                String lFtvstr = (String) lFtv.get(mstr + dstr);
                return lFtvstr;
        }


        public String getxFtv(Calendar cal)
        {
                int m = cal.get(Calendar.MONTH) + 1;
                int d = cal.get(Calendar.DAY_OF_MONTH);

                String mstr = new Integer(m).toString();
                if (m < 10)
                {
                        mstr = "0" + mstr;
                }

                String dstr = new Integer(d).toString();
                if (d < 10)
                {
                        dstr = "0" + dstr;
                }

                String xFtvstr = (String) xFtv.get(mstr + dstr);
                return xFtvstr;

        }

        public String getxFtv(int m, int d)
        {
                String mstr = new Integer(m).toString();
                if (m < 10)
                {
                        mstr = "0" + mstr;
                }

                String dstr = new Integer(d).toString();
                if (d < 10)
                {
                        dstr = "0" + dstr;
                }

                String xFtvstr = (String) xFtv.get(mstr + dstr);
                return xFtvstr;
        }

        public String getsolarTerms(LunarCalendar lc)
        {
                int m = lc.getMonth();
                int d = lc.getDate();
                String mstr = new Integer(m).toString();
                if (m < 10)
                {
                        mstr = "0" + mstr;
                }

                String dstr = new Integer(d).toString();
                if (d < 10)
                {
                        dstr = "0" + dstr;
                }

                String solarTermstr = (String) solarTerm.get(mstr + dstr);
                return solarTermstr;
        }

        public String getFulDateString(LunarCalendar lc)
        {
                String str = "";
                String nums = String.valueOf(lc.getYear());
                for (int i = 0; i < nums.length(); i++)
                {
                        str += n2s(Integer.parseInt(nums.substring(i, i + 1)));
                }
                str += "年";
                int m = lc.getMonth();
                if (m >= 10)
                {
                        str += "十";
                        m = m - 10;
                }
                if (m > 0)
                {
                        nums = String.valueOf(m);
                        for (int i = 0; i < nums.length(); i++)
                        {
                                str += n2s(Integer.parseInt(nums.substring(i, i + 1)));
                        }
                }
                str += "月";
                str += convertNum2Str(lc.getDate());

                return str;

        }

        public String n2s(int n)
        {
                switch (n)
                {
                        case 0:
                                return "零";
                        case 1:
                                return "一";
                        case 2:
                                return "二";
                        case 3:
                                return "三";
                        case 4:
                                return "四";
                        case 5:
                                return "五";
                        case 6:
                                return "六";
                        case 7:
                                return "七";
                        case 8:
                                return "八";
                        case 9:
                                return "九";
                        case 10:
                                return "十";
                        case 11:
                                return "十一";
                        case 12:
                                return "十二";

                }
                return "";

        }

        public String convertNum2Str(int num)
        {
                String str = "", tmp = "";
                String prefix = "";
                if (num <= 10)
                {
                        prefix = "初";
                }
                else if (num < 20)
                {
                        prefix = "十";
                }
                else if (num < 30)
                {
                        prefix = "廿";
                }
                else if (num < 40)
                {
                        prefix = "卅";
                }
                else if (num < 50)
                {
                        prefix = "□";
                }


                int r = num % 10;
                switch (r)
                {
                        case 0:
                                tmp = "十";
                                break;

                        case 1:
                                tmp = "一";
                                break;

                        case 2:
                                tmp = "二";
                                break;

                        case 3:
                                tmp = "三";
                                break;

                        case 4:
                                tmp = "四";
                                break;

                        case 5:
                                tmp = "五";
                                break;

                        case 6:
                                tmp = "六";
                                break;

                        case 7:
                                tmp = "七";
                                break;
                        case 8:
                                tmp = "八";
                                break;

                        case 9:
                                tmp = "九";
                                break;
                }

                str = prefix + tmp;
                return str;
        }
}
