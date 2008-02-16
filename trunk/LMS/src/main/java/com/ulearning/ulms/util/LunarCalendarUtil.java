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
        //��������
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
        String[] Gan = {"��", "��", "��", "��", "��", "��", "��", "��", "��", "��"};
        String[] Zhi = {"��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", "��", "��"};
        String[] Animals = {"��", "ţ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��"};
        int[] sTermInfo = {0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758};
        String[] nStr1 = {"��", "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ"};
        String[] nStr2 = {"��", "ʮ", "إ", "ئ", "��"};
        String[] monthName = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

        public static Map solarTerm = new HashMap();

        private void addsolarTerm()
        {
                solarTerm.put("02041946", "����");
                solarTerm.put("1217", "����");
                solarTerm.put("02191539", "��ˮ");
                solarTerm.put("0102", "��ˮ");
                solarTerm.put("03051356", "����");
                solarTerm.put("0117", "����");
                solarTerm.put("03200000", "����");
                solarTerm.put("0203", "����");
                solarTerm.put("04050000", "����");
                solarTerm.put("0218", "����");
                solarTerm.put("01200211", "����");
                solarTerm.put("0304", "����");
                solarTerm.put("05051223", "����");
                solarTerm.put("0320", "����");
                solarTerm.put("05210133", "С��");
                solarTerm.put("0405", "С��");
                solarTerm.put("06050000", "â��");
                solarTerm.put("0421", "â��");
                solarTerm.put("06210939", "����");
                solarTerm.put("0508", "����");
                solarTerm.put("07070318", "С��");
                solarTerm.put("0523", "С��");
                solarTerm.put("07222030", "����");
                solarTerm.put("0610", "����");

                solarTerm.put("08070000", "����");
                solarTerm.put("0626", "����");
                solarTerm.put("08230328", "����");
                solarTerm.put("0711", "����");
                solarTerm.put("09071544", "��¶");
                solarTerm.put("0727", "��¶");
                solarTerm.put("09230052", "���");
                solarTerm.put("0813", "���");
                solarTerm.put("10080708", "��¶");
                solarTerm.put("0829", "��¶");
                solarTerm.put("10230000", "˪��");
                solarTerm.put("0914", "˪��");

                solarTerm.put("11070000", "����");
                solarTerm.put("0929", "����");
                solarTerm.put("11220120", "Сѩ");
                solarTerm.put("1014", "Сѩ");
                solarTerm.put("12070243", "��ѩ");
                solarTerm.put("1028", "��ѩ");
                solarTerm.put("12212033", "����");
                solarTerm.put("1113", "����");
                solarTerm.put("01050000", "С��");
                solarTerm.put("1118", "С��");
                solarTerm.put("01200000", "��");
                solarTerm.put("1202", "��");

        }

        //�������� *��ʾ�ż���
        public static Map xFtv = new HashMap();

        private void addxFtv()
        {
                xFtv.put("0101", "����Ԫ��");

                //xFtv.put("0202", "����ʪ����");
                //xFtv.put("0207", "������Ԯ�Ϸ���");
                //xFtv.put("0210", "���������");
                xFtv.put("0214", "���˽�");

                xFtv.put("0301", "���ʺ�����");
                xFtv.put("0303", "ȫ��������");
                xFtv.put("0308", "���ʸ�Ů��");
                xFtv.put("0312", "ֲ���� ����ɽ����������");
                ////xFtv.put("0314", "���ʾ�����");
                xFtv.put("0315", "����������Ȩ����");
                xFtv.put("0317", "�й���ҽ�� ���ʺ�����");
                //xFtv.put("0321", "����ɭ���� �����������ӹ����� ���������");
                //xFtv.put("0322", "����ˮ��");
                //xFtv.put("0323", "����������");
                //xFtv.put("0324", "������ν�˲���");
                xFtv.put("0325", "ȫ����Сѧ����ȫ������");
                //xFtv.put("0330", "����˹̹������");

                //xFtv.put("0401", "���˽� ȫ�����������˶���(����) ˰��������(����");
                //xFtv.put("0407", "����������");
                //xFtv.put("0422", "���������");
                // xFtv.put("0423", "����ͼ��Ͱ�Ȩ��");
                //xFtv.put("0424", "�Ƿ����Ź�������");

                xFtv.put("0501", "�����Ͷ���");
                xFtv.put("0504", "�й����������");
                //xFtv.put("0505", "��ȱ����������");
                xFtv.put("0508", "�����ʮ����");
                xFtv.put("0512", "���ʻ�ʿ��");
                //xFtv.put("0515", "���ʼ�ͥ��");
                xFtv.put("0517", "���������");
                //xFtv.put("0518", "���ʲ������");
                xFtv.put("0520", "ȫ��ѧ��Ӫ����");
                //xFtv.put("0523", "����ţ����");
                xFtv.put("0531", "����������");

                xFtv.put("0601", "���ʶ�ͯ��");
                //xFtv.put("0605", "���绷����");
                xFtv.put("0606", "ȫ��������");
                //xFtv.put("0617", "���λ�Į���͸ɺ���");
                //xFtv.put("0623", "���ʰ���ƥ����");
                xFtv.put("0625", "ȫ��������");
                //xFtv.put("0626", "���ʷ���Ʒ��");

                xFtv.put("0701", "�й������������� ���罨����");
                //xFtv.put("0702", "��������������");
                xFtv.put("0707", "�й�������ս��������");
                xFtv.put("0711", "�����˿���");
                //xFtv.put("0730", "���޸�Ů��");

                xFtv.put("0801", "�й�������");
                xFtv.put("0808", "�й����ӽ�(�ְֽ�)");
                xFtv.put("0815", "�ձ���ʽ����������Ͷ����");

                //xFtv.put("0908", "����ɨä�� �������Ź�������");
                xFtv.put("0910", "��ʦ��");
                //xFtv.put("0914", "������������");
                //xFtv.put("0916", "���ʳ����㱣����");
                xFtv.put("0918", "��һ���±������");
                xFtv.put("0920", "���ʰ�����");
                //xFtv.put("0927", "����������");
                //xFtv.put("1001", "����� ���������� �������˽� ����������");
                xFtv.put("1001", "�����");
                //xFtv.put("1002", "���ʺ�ƽ���������ɶ�����");
                //xFtv.put("1004", "���綯����");
                xFtv.put("1008", "ȫ����Ѫѹ�� �����Ӿ���");
                //xFtv.put("1009", "���������� ���������");
                xFtv.put("1010", "�������������� ���羫��������");
                //xFtv.put("1013", "���籣���� ���ʽ�ʦ��");
                //xFtv.put("1014", "�����׼��");
                //xFtv.put("1015", "����ä�˽�(�����Ƚ�");
                //xFtv.put("1016", "������ʳ��");
                //xFtv.put("1017", "��������ƶ����");
                //xFtv.put("1022", "���紫ͳҽҩ��");
                //xFtv.put("1024", "���Ϲ��� ���緢չ��Ϣ��");
                //xFtv.put("1031", "�����ڼ���");

                xFtv.put("1107", "ʮ������������������");
                xFtv.put("1108", "�й�������");
                xFtv.put("1109", "ȫ��������ȫ����������");
                //xFtv.put("1110", "���������");
                //xFtv.put("1111", "���ʿ�ѧ���ƽ��(����������һ��)");
                xFtv.put("1112", "����ɽ����������");
                //xFtv.put("1114", "����������");
                //xFtv.put("1117", "���ʴ�ѧ���� ����ѧ����");
                //xFtv.put("1121", "�����ʺ��� ���������");
                //xFtv.put("1129", "������Ԯ����˹̹���������");

                //xFtv.put("1201", "���簬�̲���");
                //xFtv.put("1203", "����м�����");
                //xFtv.put("1205", "���ʾ��ú���ᷢչ־Ը��Ա��");
                //xFtv.put("1208", "���ʶ�ͯ������");
                //xFtv.put("1209", "����������");
                //xFtv.put("1210", "������Ȩ��");
                xFtv.put("1212", "�����±������");
                //xFtv.put("1213", "�Ͼ�����ɱ(1937��)�����գ�����Ѫ��ʷ");
                //xFtv.put("1221", "����������");
                //xFtv.put("1224", "ƽ��ҹ");
                xFtv.put("1225", "ʥ����");
                //xFtv.put("1229", "���������������");

        }

        //ĳ�µĵڼ������ڼ���  5, 6, 7, 8 ��ʾ������ 1, 2, 3, 4 �����ڼ�
        /*
String[] wFtv = {
"0110 ������", "0150 ���������", "0520 ����ĸ�׽�", "0530 ȫ��������",
"0630 ���׽�", "0911 �Ͷ���", "0932 ���ʺ�ƽ��", "0940 �������˽� �����ͯ��",
"0950 ���纣����", "1011 ����ס����", "1013 ���ʼ�����Ȼ�ֺ���(������)", "1144 �ж���"}; */
        String[] wFtv = {"0520 ����ĸ�׽�", "0530 ȫ��������", "0630 ���׽�", "0911 �Ͷ���", "0932 ���ʺ�ƽ��",};


        //ũ������
        public static Map lFtv = new HashMap();

        private void addlFtv()
        {
                lFtv.put("0101", "����");
                lFtv.put("0115", "Ԫ����");
                //lFtv.put("0202", "��̧ͷ��");
                //lFtv.put("0323", "�������� (����ʥĸ����)");
                lFtv.put("0505", "�����");
                lFtv.put("0707", "�������˽�");
                lFtv.put("0815", "�����");
                lFtv.put("0909", "������");
                lFtv.put("1208", "���˽�");
                lFtv.put("1223", "С��");
                lFtv.put("0101", "����");
        }

        public LunarCalendarUtil()
        {
                addlFtv();
                addxFtv();
                addsolarTerm();
        }

        //====================================== ����ũ�� y���������
        public int lYearDays(int y)
        {
                int i, sum = 348;
                for (i = 0x8000; i > 0x8; i >>= 1)
                {
                        sum += ((lunarInfo[y - 1900] & i) != 0) ? 1 : 0;
                }
                return (sum + leapDays(y));
        }

        //====================================== ����ũ�� y�����µ�����
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

        //====================================== ����ũ��  y�����ĸ��� 1-12 , û�򷵻� 0
        public int leapMonth(int y)
        {
                int lm = lunarInfo[y - 1900] & 0xf;
                return (lm == 0xf ? 0 : lm);
        }

        //====================================== ����ũ�� y��m�µ�������
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

        //==============================���ع��� y��ĳm+1�µ�����
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

        //============================== ���� offset ���ظ�֧, 0=����
        public String cyclical(int num)
        {
                return (Gan[num % 10] + Zhi[num % 12]);
        }

//===== ĳ��ĵ�n������Ϊ����(��0С������)
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
                        lcstr = n2s(month) + "��";
                        if (monthDays(year, month) == 30)
                        {
                                lcstr += "��";
                        }
                        else if (monthDays(year, month) == 29)
                        {
                                lcstr += "С";
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
                str += "��";
                int m = lc.getMonth();
                if (m >= 10)
                {
                        str += "ʮ";
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
                str += "��";
                str += convertNum2Str(lc.getDate());

                return str;

        }

        public String n2s(int n)
        {
                switch (n)
                {
                        case 0:
                                return "��";
                        case 1:
                                return "һ";
                        case 2:
                                return "��";
                        case 3:
                                return "��";
                        case 4:
                                return "��";
                        case 5:
                                return "��";
                        case 6:
                                return "��";
                        case 7:
                                return "��";
                        case 8:
                                return "��";
                        case 9:
                                return "��";
                        case 10:
                                return "ʮ";
                        case 11:
                                return "ʮһ";
                        case 12:
                                return "ʮ��";

                }
                return "";

        }

        public String convertNum2Str(int num)
        {
                String str = "", tmp = "";
                String prefix = "";
                if (num <= 10)
                {
                        prefix = "��";
                }
                else if (num < 20)
                {
                        prefix = "ʮ";
                }
                else if (num < 30)
                {
                        prefix = "إ";
                }
                else if (num < 40)
                {
                        prefix = "ئ";
                }
                else if (num < 50)
                {
                        prefix = "��";
                }


                int r = num % 10;
                switch (r)
                {
                        case 0:
                                tmp = "ʮ";
                                break;

                        case 1:
                                tmp = "һ";
                                break;

                        case 2:
                                tmp = "��";
                                break;

                        case 3:
                                tmp = "��";
                                break;

                        case 4:
                                tmp = "��";
                                break;

                        case 5:
                                tmp = "��";
                                break;

                        case 6:
                                tmp = "��";
                                break;

                        case 7:
                                tmp = "��";
                                break;
                        case 8:
                                tmp = "��";
                                break;

                        case 9:
                                tmp = "��";
                                break;
                }

                str = prefix + tmp;
                return str;
        }
}
