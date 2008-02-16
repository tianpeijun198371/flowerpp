/**
 * Timer.java.
 * User: fengch  Date: 2004-4-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;


/**
 * A bean that can be used to time execution of pages
 */
public class Timer
{
        // Attributes ----------------------------------------------------
        long current = System.currentTimeMillis();
        long start = current;

        // Public --------------------------------------------------------
        public long getTime()
        {
                // Return how long time has passed since last check point
                long now = System.currentTimeMillis();
                long time = now - current;

                // Reset so that next time we get from this point
                current = now;

                return time;
        }

        public long getTotal()
        {
                // Reset start so that next time we get from this point
                return System.currentTimeMillis() - start;
        }

        public long getStartTime()
        {
                return start;
        }
}
