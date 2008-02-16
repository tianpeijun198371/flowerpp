/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-11-8 10:16:02
 */
package com.ulearning.ulms.tools.meeting.exception;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class MeetingAppException extends ULMSAppException {
    /**
     * Creates new <code>ULMSAppException</code> without detail message.
     */
    public MeetingAppException() {
        super();
    }

    /**
     * Constructs an <code>ContentAppException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MeetingAppException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>ContentAppException</code> with the specified detail message and nested Exception.
     *
     * @param msg the detail message.
     */
    public MeetingAppException(String msg, Throwable nested) {
        super(msg, nested);
    }

    /**
     * Constructs an <code>ContentAppException</code> with the specified detail message and nested Exception.
     *
     * @param nested the detail message.
     */
    public MeetingAppException(Throwable nested) {
        super(nested);
    }
}
