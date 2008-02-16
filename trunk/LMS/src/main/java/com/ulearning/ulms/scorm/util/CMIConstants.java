/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-10-8 10:07:22
 */
package com.ulearning.ulms.scorm.util;

public interface CMIConstants
{
        String CREDIT_CREDIT = "credit";
        String CREDIT_NOCREDIT = "no-credit";

        String EXIT_TIMEOUT = "time-out";
        String EXIT_SUSPEND = "suspend";
        String EXIT_LOGOUT = "logout";

        String INTERACTION_TRUEFALSE = "true-false";
        String INTERACTION_CHOICE = "choice";
        String INTERACTION_FILLIN = "fill-in";
        String INTERACTION_MATCHING = "matching";
        String INTERACTION_PERFORMANCE = "performance";
        String INTERACTION_LIKERT = "likert";
        String INTERACTION_SEQUENCING = "sequencing";
        String INTERACTION_NUMERIC = "numeric";

        String MODE_NORMAL = "normal";
        String MODE_REVIEW = "review";
        String MODE_BROWSE = "browse";
        
        String RESULT_CORRECT = "correct";
        String RESULT_WRONG = "wrong";
        String RESULT_UNANTICIPATED = "unanticipated";
        String RESULT_NEUTRAL = "neutral";
        String RESULT_CMIDECIMAL = "CMIDecimal";

        String STATUS_PASSED = "passed";
        String STATUS_COMPLETED = "completed";
        String STATUS_FAILED = "failed";
        String STATUS_INCOMPLETE = "incomplete";
        String STATUS_BROWSED = "browsed";
        String STATUS_NOTATTEMPTED = "not attempted";

        String TIMELIMITACTION_EXIT_MESSAGE = "exit,message";
        String TIMELIMITACTION_EXIT_NOMESSAGE = "exit,no message";
        String TIMELIMITACTION_CONTINUE_MESSAGE = "continue,message";
        String TIMELIMITACTION_CONTINUE_NOMESSAGE = "continue,no message";
        
        String WHYLEFT_STUDENT_SELECTED = "student selected";
        String WHYLEFT_LESSONDIRECTED = "lesson directed";
        String WHYLEFT_EXIT = "exit";
        String WHYLEFT_DIRECTEDDEPARTURE = "directed departure";
        
        String ENTRY_ABINITIO = "ab-initio";
        String ENTRY_RESUME = "resume";
        String ENTRY_EMPTY = "";

}
