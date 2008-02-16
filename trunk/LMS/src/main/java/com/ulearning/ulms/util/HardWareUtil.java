/**
 * Created by IntelliJ IDEA.
 * User: Jacky Dengj
 * Date: 2006-4-18
 * Time: 23:55:46
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.util;

import com.ulearning.ulms.core.crypto.MD5Encrypt;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class HardWareUtil
{
        String IPCONFIG_COMMAND_WIN = "ipconfig /all";
        boolean realMac = true;
        String unique = "";

        public static String getMacAddress()
        {
                HardWareUtil hwid = new HardWareUtil();
                return hwid.getUnique().trim();
        }

        private String getUnique()
        {
                String os = System.getProperty("os.name");

                if (os.startsWith("Windows"))
                {
                        return getUniqueWindows();
                }
                else
                {
                        return "";
                }
        }

        private String getUniqueWindows()
        {
// 运行控制台命令，返回结果字符串
                String ipConfigResponse = null;
                try
                {
                        ipConfigResponse = runConsoleCommand(IPCONFIG_COMMAND_WIN);
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                }

// 按行分割结果字符串，并循环判断每个字符串直道找出 Mac 地址
                StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
                while (tokenizer.hasMoreTokens())
                {
                        String line = tokenizer.nextToken().trim();

// 获取每行 ":" 后的字符串
                        int macAddressPosition = line.indexOf(":");
                        if (macAddressPosition <= 0)
                        {
                                continue;
                        }
                        String macAddressCandidate = line.substring(macAddressPosition + 1).
                                trim();

// 检查 macAddressCandidate 中内容是否为真实网卡 Mac 地址，根据 Mac 地址计算出唯一标识。
                        if (isMacAddWin(macAddressCandidate))
                        {
                                if (realMac == true)
                                {
                                        generateUnique(macAddressCandidate);
                                }
                                else
                                {
                                        realMac = true;
                                }
                        }
                }

                return unique;
        }


        /**
         * 运行控制台命令，返回结果字符串
         *
         * @param command String
         * @return String
         * @throws IOException
         */
        private String runConsoleCommand(String command) throws IOException
        {
                Process p = Runtime.getRuntime().exec(command);
                InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

                StringBuffer buffer = new StringBuffer();
                while (true)
                {
                        int c = stdoutStream.read();
                        if (c == -1)
                        {
                                break;
                        }
                        buffer.append((char) c);
                }
                String outputText = buffer.toString();

                stdoutStream.close();

                return outputText;
        }

        /**
         * 对输入参数进行检查，符合正则表达式的为 windows 平台下有效 Mac 地址
         *
         * @param macAddressCandidate String
         * @return boolean
         */
        private boolean isMacAddWin(String macAddressCandidate)
        {
                Pattern macPattern = Pattern.compile("[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}");
                Matcher m = macPattern.matcher(macAddressCandidate);
                return m.matches();
        }

        /**
         * 对输入参数进行检查，符合长度的为 MAC OS X 下有效网卡 Mac 地址
         *
         * @param macAddressCandidate String
         * @return boolean
         */
        private boolean isMacAddOSX(String macAddressCandidate)
        {
                if (macAddressCandidate.length() != 17)
                {
                        return false;
                }
                else
                {
                        return true;
                }
        }

        /**
         * 产生 Unique
         *
         * @param macAddress String
         */
        private void generateUnique(String macAddress)
        {
                if (unique == "")
                {
                        unique += macAddress;
                }
                else
                {
                        unique += "#";
                        unique += macAddress;
                }
        }

        public static void main(String[] args)
        {
                try
                {
                        //File test = new File("d://test1");
                        //test.createNewFile();
                        System.out.println(MD5Encrypt.encrypt("00-15-17-2C-62-17#00-15-17-2C-62-16"));
                }
                catch (Exception e)
                {

                }

                //testHibernate3();
                //System.out.println(MD5Encrypt.encrypt("00-15-C5-61-AB-21#00-16-6F-9A-E6-A8"));
                //System.out.println(MD5Encrypt.encrypt("00-14-22-A9-25-07#44-45-53-54-42-00#00-16-6F-94-01-7A"));
                //System.out.println(MD5Encrypt.encrypt("00-13-02-AC-DD-26#00-16-D3-26-49-DB#44-45-53-54-42-00"));
                //System.out.println("args = " + args);
                /*System.out.println(HardWareUtil.getMacAddress());
                String key = "00-13-72-4F-8F-0D#00-13-72-4F-8F-0C#00-50-56-C0-00-08#00-50-56-C0-00-01";
                key = "00-06-1B-D5-6A-42";
                key = "00-15-58-0B-27-31";
                key = "00-50-56-C0-00-08#00-50-56-C0-00-01#00-13-72-4F-8F-0D#00-13-72-4F-8F-0C";
                key = "00-15-58-0B-27-31";
                key = "00-06-5B-F5-73-3A#00-06-5B-F5-73-39#00-02-B3-DA-74-1E";
                key = "00-50-8D-70-E6-22#00-53-45-00-00-00";
                key = "00-50-56-C0-00-08#00-50-56-C0-00-01#00-16-6F-0D-45-34#00-16-41-11-67-08#00-53-45-00-00-00";
                key = "192.168.172.1#192.168.36.1";
                key = "00-50-56-C0-00-08#00-50-56-C0-00-01#00-16-6F-0D-45-34#00-16-41-11-67-08";
                key = "00-12-3F-79-1F-24";
                key = "00-13-46-6F-8A-32";
                key = "00-05-5D-86-E9-81#00-E0-4C-02-2C-A5";

                //key="00-00-00-00-00-06#00-50-56-C0-00-01#00-50-56-C0-00-08";
                System.out.println(MD5Encrypt.encrypt(key));
                //System.out.println(MD5Encrypt.encrypt(args[0]));
                //orderDate();*/
        }

        /**
         * Order the data
         */
        public static void orderDate()
        {
                //Init the arraylist
                int[] data = new int[20];
                data[0] = 82;
                data[1] = 12;
                data[2] = 676;
                data[3] = 9;
                data[4] = 873;
                data[5] = 32;
                data[6] = 62;
                data[7] = 982;
                data[8] = 1;
                data[9] = 98;
                data[10] = 52;
                data[11] = 132;
                data[12] = 1676;
                data[13] = 69;
                data[14] = 83;
                data[15] = 352;
                data[16] = 652;
                data[17] = 12982;
                data[18] = 11;
                data[19] = 908;
                int middleData = 0;

                //Begin to process the data
                for (int i = 0; i < data.length; i++)
                {
                        for (int j = 0; j < data.length - 1; j++)
                        {
                                if (data[j] > data[j + 1])
                                {
                                        middleData = data[j];
                                        data[j] = data[j + 1];
                                        data[j + 1] = middleData;
                                }
                        }
                }
                for (int i = 0; i < data.length; i++)
                {
                        System.out.println("[Data] ============= data[i] = " + data[i]);
                }
        }


        /**
         * Order the data
         */
        public static void orderDateSequence()
        {
                //Init the arraylist
                int[] data = new int[10];
                data[0] = 52;
                data[1] = 12;
                data[2] = 676;
                data[3] = 9;
                data[4] = 873;
                data[5] = 32;
                data[6] = 62;
                data[7] = 982;
                data[8] = 1;
                data[9] = 98;
                int middleData;
                int middlem;

                //Begin to process the data
                for (int i = 0; i < data.length; i++)
                {
                        middleData = data[i];
                        middlem = i;
                        for (int j = i; j < data.length; j++)
                        {
                                if (middleData > data[j])
                                {
                                        middleData = data[j];
                                        middlem = j;
                                }
                        }
                        data[middlem] = data[i];
                        data[i] = middleData;
                        //System.out.println("[Data] @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ i = " + i);
                }
                for (int i = 0; i < data.length; i++)
                {
                        System.out.println("[Data] ============= data[i] = " + data[i]);
                }
        }

        public static void testHibernate3()
        {
                System.out.println("[Hibernate3 is OK]=============");
                String hsl = " from UserModel where userID = 1";
                List h3 = HibernateDAO.find(hsl);
                System.out.println("[Hibernate3 is OK]============= list = " + h3.size());

        }
}
