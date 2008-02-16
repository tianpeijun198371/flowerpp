/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: fengch
 * Date: 2007-10-30 11:46:44 
 */

package com.ulearning.ulms.core.uuid;

import java.net.InetAddress;
import java.security.SecureRandom;

public class UUIDKeyGenerator
        implements KeyGenerator
{

        SecureRandom seeder;
        private String midValue;

        public UUIDKeyGenerator()
                throws Exception
        {
                StringBuffer buffer = new StringBuffer(16);
                byte addr[] = InetAddress.getLocalHost().getAddress();
                for (int i = 0; i < addr.length; i++)
                {
                        System.out.println("# addr[i] = " + (int) addr[i]);

                }
                System.out.println("addr = " + new String(addr));
                System.out.println(" InetAddress.getLocalHost().getHostAddress() = "
                        + InetAddress.getLocalHost().getHostAddress());
                System.out.println(" InetAddress.getLocalHost().getHostName() = "
                        + InetAddress.getLocalHost().getHostName());
                System.out.println("toHex(toInt(addr), 8) = " + toHex(toInt(addr), 8));

                System.out.println("System.identityHashCode(this) = " + System.identityHashCode(this));

                System.out.println("toHex(System.identityHashCode(this), 8) = "
                        + toHex(System.identityHashCode(this), 8));
                buffer.append(toHex(toInt(addr), 8));
                buffer.append(toHex(System.identityHashCode(this), 8));
                midValue = buffer.toString();
                System.out.println("midValue = " + midValue);
                seeder = new SecureRandom();
                int node = seeder.nextInt();
        }

        public Object generateKey()
        {
                StringBuffer buffer = new StringBuffer(32);
                buffer.append(toHex((int) (System.currentTimeMillis() & -1L), 8));
                buffer.append(midValue);
                buffer.append(toHex(seeder.nextInt(), 8));
                return buffer.toString();
        }

        private String toHex(int value, int length)
        {
                char hexDigits[] = {
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                        'A', 'B', 'C', 'D', 'E', 'F'
                };
                StringBuffer buffer = new StringBuffer(length);
                int shift = length - 1 << 2;
                for (int i = -1; ++i < length;)
                {
                        buffer.append(hexDigits[value >> shift & 0xf]);
                        value <<= 4;
                }

                return buffer.toString();
        }

        private static int toInt(byte bytes[])
        {
                int value = 0;
                for (int i = -1; ++i < bytes.length;)
                {
                        value <<= 8;
                        int b = bytes[i] & 0xff;
                        value |= b;
                }

                return value;
        }
}
