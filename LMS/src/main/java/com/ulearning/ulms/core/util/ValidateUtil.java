/**
 * ValidateUtil.java.
 * User: fengch  Date: 2004-7-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


/**
 * General input/data validation methods
 * Utility methods for validating data, especially input.
 * See detailed description below.
 * <p/>
 * <p/>
 * <p/>
 * <br> SUMMARY
 * <br>
 * <br> This is a set of meethods for validating input. Functions are provided to validate:
 * <br>    - U.S. and international phone/fax numbers
 * <br>    - U.S. ZIP codes(5 or 9 digit postal codes)
 * <br>    - U.S. Postal Codes(2 letter abbreviations for names of states)
 * <br>    - U.S. Social Security Numbers(abbreviated as SSNs)
 * <br>    - email addresses
 * <br>           - dates(entry of year, month, and day and validity of combined date)
 * <br>           - credit card numbers
 * <br>
 * <br> Supporting utility functions validate that:
 * <br>    - characters are Letter, Digit, or LetterOrDigit
 * <br>    - strings are a Signed, Positive, Negative, Nonpositive, or Nonnegative integer
 * <br>    - strings are a Float or a SignedFloat
 * <br>    - strings are Alphabetic, Alphanumeric, or Whitespace
 * <br>    - strings contain an integer within a specified range
 * <br>
 * <br> Other utility functions are provided to:
 * <br>    - remove from a string characters which are/are not in a "bag" of selected characters
 * <br>           - strip whitespace/leading whitespace from a string
 * <br>
 */
public class ValidateUtil
{
        public static final String module = ValidateUtil.class.getName();

        /**
         * boolean specifying by default whether or not it is okay for a String to be empty
         */
        public static final boolean defaultEmptyOK = true;

        /**
         * digit characters
         */
        public static final String digits = "0123456789";

        /**
         * lower-case letter characters
         */
        public static final String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";

        /**
         * upper-case letter characters
         */
        public static final String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        /**
         * letter characters
         */
        public static final String letters = lowercaseLetters + uppercaseLetters;

        /**
         * whitespace characters
         */
        public static final String whitespace = " \t\n\r";

        /**
         * decimal point character differs by language and culture
         */
        public static final String decimalPointDelimiter = ".";

        /**
         * non-digit characters which are allowed in phone numbers
         */
        public static final String phoneNumberDelimiters = "()- ";

        /**
         * characters which are allowed in US phone numbers
         */
        public static final String validUSPhoneChars = digits +
                phoneNumberDelimiters;

        /**
         * characters which are allowed in international phone numbers(a leading + is OK)
         */
        public static final String validWorldPhoneChars = digits +
                phoneNumberDelimiters + "+";

        /**
         * non-digit characters which are allowed in Social Security Numbers
         */
        public static final String SSNDelimiters = "- ";

        /**
         * characters which are allowed in Social Security Numbers
         */
        public static final String validSSNChars = digits + SSNDelimiters;

        /**
         * U.S. Social Security Numbers have 9 digits. They are formatted as 123-45-6789.
         */
        public static final int digitsInSocialSecurityNumber = 9;

        /**
         * U.S. phone numbers have 10 digits. They are formatted as 123 456 7890 or(123) 456-7890.
         */
        public static final int digitsInUSPhoneNumber = 10;
        public static final int digitsInUSPhoneAreaCode = 3;
        public static final int digitsInUSPhoneMainNumber = 7;

        /**
         * non-digit characters which are allowed in ZIP Codes
         */
        public static final String ZipCodeDelimiters = "-";

        /**
         * our preferred delimiter for reformatting ZIP Codes
         */
        public static final String ZipCodeDelimeter = "-";

        /**
         * characters which are allowed in Social Security Numbers
         */
        public static final String validZipCodeChars = digits + ZipCodeDelimiters;

        /**
         * U.S. ZIP codes have 5 or 9 digits. They are formatted as 12345 or 12345-6789.
         */
        public static final int digitsInZipCode1 = 5;

        /**
         * U.S. ZIP codes have 5 or 9 digits. They are formatted as 12345 or 12345-6789.
         */
        public static final int digitsInZipCode2 = 9;

        /**
         * non-digit characters which are allowed in credit card numbers
         */
        public static final String creditCardDelimiters = " -";
        public static final String isNotEmptyMsg = "This field cannot be empty, please enter a value.";
        public static final String isStateCodeMsg = "The State Code must be a valid two character U.S. state abbreviation(like CA for California).";
        public static final String isContiguousStateCodeMsg = "The State Code must be a valid two character U.S. state abbreviation for one of the 48 contiguous United States (like CA for California).";
        public static final String isZipCodeMsg = "The ZIP Code must be a 5 or 9 digit U.S. ZIP Code(like 94043).";
        public static final String isUSPhoneMsg = "The US Phone must be a 10 digit U.S. phone number(like 415-555-1212).";
        public static final String isUSPhoneAreaCodeMsg = "The Phone Number Area Code must be 3 digits.";
        public static final String isUSPhoneMainNumberMsg = "The Phone Number must be 7 digits.";
        public static final String isContiguousZipCodeMsg = "Zip Code is not a valid Zip Code for one of the 48 contiguous United States .";
        public static final String isInternationalPhoneNumberMsg = "The World Phone must be a valid international phone number.";
        public static final String isSSNMsg = "The SSN must be a 9 digit U.S. social security number(like 123-45-6789).";
        public static final String isEmailMsg = "The Email must be a valid email address(like john@email.com). Please re-enter it now.";
        public static final String isAnyCardMsg = "The credit card number is not a valid card number.";
        public static final String isCreditCardPrefixMsg = " is not a valid ";
        public static final String isCreditCardSuffixMsg = " credit card number.";
        public static final String isDayMsg = "The Day must be a day number between 1 and 31. ";
        public static final String isMonthMsg = "The Month must be a month number between 1 and 12. ";
        public static final String isYearMsg = "The Year must be a 2 or 4 digit year number. ";
        public static final String isDatePrefixMsg = "The Day, Month, and Year for ";
        public static final String isDateSuffixMsg = " do not form a valid date.  Please reenter them now.";
        public static final String isHourMsg = "The Hour must be a number between 0 and 23.";
        public static final String isMinuteMsg = "The Hour must be a number between 0 and 59.";
        public static final String isSecondMsg = "The Hour must be a number between 0 and 59.";
        public static final String isTimeMsg = "The Time must be a valid time formed like: HH:MM or HH:MM:SS.";
        public static final String isDateMsg = "The Date must be a valid date formed like: MM/YY, MM/YYYY, MM/DD/YY, or MM/DD/YYYY.";
        public static final String isDateAfterToday = "The Date must be a valid date after today, and formed like: MM/YY, MM/YYYY, MM/DD/YY, or MM/DD/YYYY.";
        public static final String isIntegerMsg = "The Number must be a valid unsigned whole decimal number.";
        public static final String isSignedIntegerMsg = "The Number must be a valid signed whole decimal number.";
        public static final String isLongMsg = "The Number must be a valid unsigned whole decimal number.";
        public static final String isSignedLongMsg = "The Number must be a valid signed whole decimal number.";
        public static final String isFloatMsg = "The Number must be a valid unsigned decimal number.";
        public static final String isSignedFloatMsg = "The Number must be a valid signed decimal number.";
        public static final String isSignedDoubleMsg = "The Number must be a valid signed decimal number.";

        /**
         * An array of ints representing the number of days in each month of the year.
         * Note: February varies depending on the year
         */
        public static final int[] daysInMonth = {
                31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        /**
         * Delimiter for USStateCodes String
         */
        public static final String USStateCodeDelimiter = "|";

        /**
         * Valid U.S. Postal Codes for states, territories, armed forces, etc.
         * See http://www.usps.gov/ncsc/lookups/abbr_state.txt.
         */
        public static final String USStateCodes = "AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY|AE|AA|AE|AE|AP";

        /**
         * Valid contiguous U.S. postal codes
         */
        public static final String ContiguousUSStateCodes = "AL|AZ|AR|CA|CO|CT|DE|DC|FL|GA|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY";

        public static boolean areEqual(Object obj, Object obj2)
        {
                if (obj == null)
                {
                        return obj2 == null;
                }
                else
                {
                        return obj.equals(obj2);
                }
        }

        /**
         * Check whether string s is empty.
         */
        public static boolean isEmpty(String s)
        {
                return ((s == null) || (s.length() == 0));
        }

        /**
         * Check whether collection c is empty.
         */
        public static boolean isEmpty(Collection c)
        {
                return ((c == null) || (c.size() == 0));
        }

        /**
         * Check whether string s is NOT empty.
         */
        public static boolean isNotEmpty(String s)
        {
                return ((s != null) && (s.length() > 0));
        }

        /**
         * Check whether string s is NOT empty.
         */
        public static boolean isTrue(String s)
        {
                return ((s != null) &&
                        (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("1") ||
                                s.equalsIgnoreCase("y")));
        }

        /**
         * Check whether collection c is NOT empty.
         */
        public static boolean isNotEmpty(Collection c)
        {
                return ((c != null) && (c.size() > 0));
        }

        /**
         * Returns true if string s is empty or whitespace characters only.
         */
        public static boolean isWhitespace(String s)
        {
                // Is s empty?
                if (isEmpty(s))
                {
                        return true;
                }

                // Search through string's characters one by one
                // until we find a non-whitespace character.
                // When we do, return false; if we don't, return true.
                for (int i = 0; i < s.length(); i++)
                {
                        // Check that current character isn't whitespace.
                        char c = s.charAt(i);

                        if (whitespace.indexOf(c) == -1)
                        {
                                return false;
                        }
                }

                // All characters are whitespace.
                return true;
        }

        /**
         * Removes all characters which appear in string bag from string s.
         */
        public static String stripCharsInBag(String s, String bag)
        {
                int i;
                String returnString = "";

                // Search through string's characters one by one.
                // If character is not in bag, append to returnString.
                for (i = 0; i < s.length(); i++)
                {
                        char c = s.charAt(i);

                        if (bag.indexOf(c) == -1)
                        {
                                returnString += c;
                        }
                }

                return returnString;
        }

        /**
         * Removes all characters which do NOT appear in string bag from string s.
         */
        public static String stripCharsNotInBag(String s, String bag)
        {
                int i;
                String returnString = "";

                // Search through string's characters one by one.
                // If character is in bag, append to returnString.
                for (i = 0; i < s.length(); i++)
                {
                        char c = s.charAt(i);

                        if (bag.indexOf(c) != -1)
                        {
                                returnString += c;
                        }
                }

                return returnString;
        }

        /**
         * Removes all whitespace characters from s.
         * Member whitespace(see above) defines which characters are considered whitespace.
         */
        public static String stripWhitespace(String s)
        {
                return stripCharsInBag(s, whitespace);
        }

        /**
         * Returns true if single character c(actually a string) is contained within string s.
         */
        public static boolean charInString(char c, String s)
        {
                return (s.indexOf(c) != -1);

                // for(int i = 0; i < s.length; i++) {
                // if(s.charAt(i) == c) return true;
                // }
                // return false;
        }

        /**
         * Removes initial(leading) whitespace characters from s.
         * Member whitespace(see above) defines which characters are considered whitespace.
         */
        public static String stripInitialWhitespace(String s)
        {
                int i = 0;

                while ((i < s.length()) && charInString(s.charAt(i), whitespace))
                {
                        i++;
                }

                return s.substring(i);

                // return s.substring(i, s.length);
        }

        /**
         * Returns true if character c is an English letter (A .. Z, a..z).
         * <p/>
         * NOTE: Need i18n version to support European characters.
         * This could be tricky due to different character
         * sets and orderings for various languages and platforms.
         */
        public static boolean isLetter(char c)
        {
                return Character.isLetter(c);
        }

        /**
         * Returns true if character c is a digit (0 .. 9).
         */
        public static boolean isDigit(char c)
        {
                return Character.isDigit(c);
        }

        /**
         * Returns true if character c is a letter or digit.
         */
        public static boolean isLetterOrDigit(char c)
        {
                return Character.isLetterOrDigit(c);
        }

        /**
         * Returns true if all characters in string s are numbers.
         * <p/>
         * Accepts non-signed integers only. Does not accept floating
         * point, exponential notation, etc.
         */
        public static boolean isInteger(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                // Search through string's characters one by one
                // until we find a non-numeric character.
                // When we do, return false; if we don't, return true.
                for (int i = 0; i < s.length(); i++)
                {
                        // Check that current character is number.
                        char c = s.charAt(i);

                        if (!isDigit(c))
                        {
                                return false;
                        }
                }

                // All characters are numbers.
                return true;
        }

        /**
         * Returns true if all characters are numbers;
         * first character is allowed to be + or - as well.
         * <p/>
         * Does not accept floating point, exponential notation, etc.
         */
        public static boolean isSignedInteger(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                try
                {
                        int temp = Integer.parseInt(s);

                        return true;
                }
                catch (Exception e)
                {
                        return false;
                }

                // int startPos = 0;
                // boolean secondArg = defaultEmptyOK;

                // if(isSignedInteger.arguments.length > 1) secondArg = isSignedInteger.arguments[1];

                // skip leading + or -
                // if((s.charAt(0) == "-") ||(s.charAt(0) == "+") ) startPos = 1;
                // return(isInteger(s.substring(startPos, s.length), secondArg))
        }

        /**
         * Returns true if all characters are numbers;
         * first character is allowed to be + or - as well.
         * <p/>
         * Does not accept floating point, exponential notation, etc.
         */
        public static boolean isSignedLong(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                try
                {
                        long temp = Long.parseLong(s);

                        return true;
                }
                catch (Exception e)
                {
                        return false;
                }
        }

        /**
         * Returns true if string s is an integer > 0. NOTE: using the Java Long object for greatest precision
         */
        public static boolean isPositiveInteger(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                try
                {
                        long temp = Long.parseLong(s);

                        if (temp > 0)
                        {
                                return true;
                        }

                        return false;
                }
                catch (Exception e)
                {
                        return false;
                }

                // return(isSignedInteger(s, secondArg)
                // &&((isEmpty(s) && secondArg)  ||(parseInt(s) > 0) ) );
        }

        /**
         * Returns true if string s is an integer >= 0.
         */
        public static boolean isNonnegativeInteger(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                try
                {
                        int temp = Integer.parseInt(s);

                        if (temp >= 0)
                        {
                                return true;
                        }

                        return false;
                }
                catch (Exception e)
                {
                        return false;
                }

                // return(isSignedInteger(s, secondArg)
                // &&((isEmpty(s) && secondArg)  ||(parseInt(s) >= 0) ) );
        }

        /**
         * Returns true if string s is an integer < 0.
         */
        public static boolean isNegativeInteger(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                try
                {
                        int temp = Integer.parseInt(s);

                        if (temp < 0)
                        {
                                return true;
                        }

                        return false;
                }
                catch (Exception e)
                {
                        return false;
                }

                // return(isSignedInteger(s, secondArg)
                // &&((isEmpty(s) && secondArg)  ||(parseInt(s) < 0) ) );
        }

        /**
         * Returns true if string s is an integer <= 0.
         */
        public static boolean isNonpositiveInteger(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                try
                {
                        int temp = Integer.parseInt(s);

                        if (temp <= 0)
                        {
                                return true;
                        }

                        return false;
                }
                catch (Exception e)
                {
                        return false;
                }

                // return(isSignedInteger(s, secondArg)
                // &&((isEmpty(s) && secondArg)  ||(parseInt(s) <= 0) ) );
        }

        /**
         * True if string s is an unsigned floating point(real) number.
         * <p/>
         * Also returns true for unsigned integers. If you wish
         * to distinguish between integers and floating point numbers,
         * first call isInteger, then call isFloat.
         * <p/>
         * Does not accept exponential notation.
         */
        public static boolean isFloat(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                boolean seenDecimalPoint = false;

                if (s.startsWith(decimalPointDelimiter))
                {
                        return false;
                }

                // Search through string's characters one by one
                // until we find a non-numeric character.
                // When we do, return false; if we don't, return true.
                for (int i = 0; i < s.length(); i++)
                {
                        // Check that current character is number.
                        char c = s.charAt(i);

                        if (c == decimalPointDelimiter.charAt(0))
                        {
                                if (!seenDecimalPoint)
                                {
                                        seenDecimalPoint = true;
                                }
                                else
                                {
                                        return false;
                                }
                        }
                        else
                        {
                                if (!isDigit(c))
                                {
                                        return false;
                                }
                        }
                }

                // All characters are numbers.
                return true;
        }

        /**
         * True if string s is a signed or unsigned floating point
         * (real) number. First character is allowed to be + or -.
         * <p/>
         * Also returns true for unsigned integers. If you wish
         * to distinguish between integers and floating point numbers,
         * first call isSignedInteger, then call isSignedFloat.
         */
        public static boolean isSignedFloat(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                try
                {
                        float temp = Float.parseFloat(s);

                        if (temp <= 0)
                        {
                                return true;
                        }

                        return false;
                }
                catch (Exception e)
                {
                        return false;
                }

                // int startPos = 0;
                // if(isSignedFloat.arguments.length > 1) secondArg = isSignedFloat.arguments[1];
                // skip leading + or -
                // if((s.charAt(0) == "-") ||(s.charAt(0) == "+") ) startPos = 1;
                // return(isFloat(s.substring(startPos, s.length), secondArg))
        }

        /**
         * True if string s is a signed or unsigned floating point
         * (real) number. First character is allowed to be + or -.
         * <p/>
         * Also returns true for unsigned integers. If you wish
         * to distinguish between integers and floating point numbers,
         * first call isSignedInteger, then call isSignedFloat.
         */
        public static boolean isSignedDouble(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                try
                {
                        double temp = Double.parseDouble(s);

                        return true;
                }
                catch (Exception e)
                {
                        return false;
                }
        }

        /**
         * Returns true if string s is letters only.
         * <p/>
         * NOTE: This should handle i18n version to support European characters, etc.
         * since it now uses Character.isLetter()
         */
        public static boolean isAlphabetic(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                // Search through string's characters one by one
                // until we find a non-alphabetic character.
                // When we do, return false; if we don't, return true.
                for (int i = 0; i < s.length(); i++)
                {
                        // Check that current character is letter.
                        char c = s.charAt(i);

                        if (!isLetter(c))
                        {
                                return false;
                        }
                }

                // All characters are letters.
                return true;
        }

        /**
         * Returns true if string s is English letters (A .. Z, a..z) and numbers only.
         * <p/>
         * NOTE: Need i18n version to support European characters.
         * This could be tricky due to different character
         * sets and orderings for various languages and platforms.
         */
        public static boolean isAlphanumeric(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                // Search through string's characters one by one
                // until we find a non-alphanumeric character.
                // When we do, return false; if we don't, return true.
                for (int i = 0; i < s.length(); i++)
                {
                        // Check that current character is number or letter.
                        char c = s.charAt(i);

                        if (!isLetterOrDigit(c))
                        {
                                return false;
                        }
                }

                // All characters are numbers or letters.
                return true;
        }

        /* ================== METHODS TO CHECK VARIOUS FIELDS. ==================== */

        /**
         * isSSN returns true if string s is a valid U.S. Social Security Number.  Must be 9 digits.
         */
        public static boolean isSSN(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                String normalizedSSN = stripCharsInBag(s, SSNDelimiters);

                return (isInteger(normalizedSSN) &&
                        (normalizedSSN.length() == digitsInSocialSecurityNumber));
        }

        /**
         * isUSPhoneNumber returns true if string s is a valid U.S. Phone Number.  Must be 10 digits.
         */
        public static boolean isUSPhoneNumber(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                String normalizedPhone = stripCharsInBag(s, phoneNumberDelimiters);

                return (isInteger(normalizedPhone) &&
                        (normalizedPhone.length() == digitsInUSPhoneNumber));
        }

        /**
         * isUSPhoneAreaCode returns true if string s is a valid U.S. Phone Area Code.  Must be 3 digits.
         */
        public static boolean isUSPhoneAreaCode(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                String normalizedPhone = stripCharsInBag(s, phoneNumberDelimiters);

                return (isInteger(normalizedPhone) &&
                        (normalizedPhone.length() == digitsInUSPhoneAreaCode));
        }

        /**
         * isUSPhoneMainNumber returns true if string s is a valid U.S. Phone Main Number.  Must be 7 digits.
         */
        public static boolean isUSPhoneMainNumber(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                String normalizedPhone = stripCharsInBag(s, phoneNumberDelimiters);

                return (isInteger(normalizedPhone) &&
                        (normalizedPhone.length() == digitsInUSPhoneMainNumber));
        }

        /**
         * isInternationalPhoneNumber returns true if string s is a valid
         * international phone number.  Must be digits only; any length OK.
         * May be prefixed by + character.
         */
        public static boolean isInternationalPhoneNumber(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                String normalizedPhone = stripCharsInBag(s, phoneNumberDelimiters);

                return isPositiveInteger(normalizedPhone);
        }

        /**
         * isZIPCode returns true if string s is a valid U.S. ZIP code.  Must be 5 or 9 digits only.
         */
        public static boolean isZipCode(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                String normalizedZip = stripCharsInBag(s, ZipCodeDelimiters);

                return (isInteger(normalizedZip) &&
                        ((normalizedZip.length() == digitsInZipCode1) ||
                                (normalizedZip.length() == digitsInZipCode2)));
        }

        /**
         * Returns true if string s is a valid contiguous U.S. Zip code.  Must be 5 or 9 digits only.
         */
        public static boolean isContiguousZipCode(String s)
        {
                boolean retval = false;

                if (isZipCode(s))
                {
                        if (isEmpty(s))
                        {
                                retval = defaultEmptyOK;
                        }
                        else
                        {
                                String normalizedZip = s.substring(0, 5);
                                int iZip = Integer.parseInt(normalizedZip);

                                if (((iZip >= 96701) && (iZip <= 96898)) ||
                                        ((iZip >= 99501) && (iZip <= 99950)))
                                {
                                        retval = false;
                                }
                                else
                                {
                                        retval = true;
                                }
                        }
                }

                return retval;
        }

        /**
         * Return true if s is a valid U.S. Postal Code (abbreviation for state).
         */
        public static boolean isStateCode(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                return ((USStateCodes.indexOf(s) != -1) &&
                        (s.indexOf(USStateCodeDelimiter) == -1));
        }

        /**
         * Return true if s is a valid contiguous U.S. Postal Code (abbreviation for state).
         */
        public static boolean isContiguousStateCode(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                return ((ContiguousUSStateCodes.indexOf(s) != -1) &&
                        (s.indexOf(USStateCodeDelimiter) == -1));
        }

        /**
         * Email address must be of form a@b.c -- in other words:
         * - there must be at least one character before the @
         * - there must be at least one character before and after the .
         * - the characters @ and . are both required
         */
        public static boolean isEmail(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                // is s whitespace?
                if (isWhitespace(s))
                {
                        return false;
                }

                // there must be >= 1 character before @, so we
                // start looking at character position 1
                // (i.e. second character)
                int i = 1;
                int sLength = s.length();

                // look for @
                while ((i < sLength) && (s.charAt(i) != '@'))
                {
                        i++;
                }

                // there must be at least one character after the .
                if ((i >= (sLength - 1)) || (s.charAt(i) != '@'))
                {
                        return false;
                }
                else
                {
                        return true;
                }

                // DEJ 2001-10-13 Don't look for '.', some valid emails do not have a dot in the domain name
                // else i += 2;

                // look for .
                // while((i < sLength) && (s.charAt(i) != '.')) i++;
                // there must be at least one character after the .
                // if((i >= sLength - 1) || (s.charAt(i) != '.')) return false;
                // else return true;
        }

        /**
         * isUrl returns true if the string contains ://
         *
         * @param s String to validate
         * @return true if s contains ://
         */
        public static boolean isUrl(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                if (s.indexOf("://") != -1)
                {
                        return true;
                }

                return false;
        }

        /**
         * isYear returns true if string s is a valid
         * Year number.  Must be 2 or 4 digits only.
         * <p/>
         * For Year 2000 compliance, you are advised
         * to use 4-digit year numbers everywhere.
         */
        public static boolean isYear(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                if (!isNonnegativeInteger(s))
                {
                        return false;
                }

                return ((s.length() == 2) || (s.length() == 4));
        }

        /**
         * isIntegerInRange returns true if string s is an integer
         * within the range of integer arguments a and b, inclusive.
         */
        public static boolean isIntegerInRange(String s, int a, int b)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                // Catch non-integer strings to avoid creating a NaN below,
                // which isn't available on JavaScript 1.0 for Windows.
                if (!isSignedInteger(s))
                {
                        return false;
                }

                // Now, explicitly change the type to integer via parseInt
                // so that the comparison code below will work both on
                // JavaScript 1.2(which typechecks in equality comparisons)
                // and JavaScript 1.1 and before(which doesn't).
                int num = Integer.parseInt(s);

                return ((num >= a) && (num <= b));
        }

        /**
         * isMonth returns true if string s is a valid month number between 1 and 12.
         */
        public static boolean isMonth(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                return isIntegerInRange(s, 1, 12);
        }

        /**
         * isDay returns true if string s is a valid day number between 1 and 31.
         */
        public static boolean isDay(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                return isIntegerInRange(s, 1, 31);
        }

        /**
         * Given integer argument year, returns number of days in February of that year.
         */
        public static int daysInFebruary(int year)
        {
                // February has 29 days in any year evenly divisible by four,
                // EXCEPT for centurial years which are not also divisible by 400.
                return ((((year % 4) == 0) &&
                        ((!((year % 100) == 0)) || ((year % 400) == 0))) ? 29 : 28);
        }

        /**
         * isHour returns true if string s is a valid number between 0 and 23.
         */
        public static boolean isHour(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                return isIntegerInRange(s, 0, 23);
        }

        /**
         * isMinute returns true if string s is a valid number between 0 and 59.
         */
        public static boolean isMinute(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                return isIntegerInRange(s, 0, 59);
        }

        /**
         * isSecond returns true if string s is a valid number between 0 and 59.
         */
        public static boolean isSecond(String s)
        {
                if (isEmpty(s))
                {
                        return defaultEmptyOK;
                }

                return isIntegerInRange(s, 0, 59);
        }

        /**
         * isDate returns true if string arguments year, month, and day form a valid date.
         */
        public static boolean isDate(String year, String month, String day)
        {
                // catch invalid years(not 2- or 4-digit) and invalid months and days.
                if (!(isYear(year) && isMonth(month) && isDay(day)))
                {
                        return false;
                }

                int intYear = Integer.parseInt(year);
                int intMonth = Integer.parseInt(month);
                int intDay = Integer.parseInt(day);

                // catch invalid days, except for February
                if (intDay > daysInMonth[intMonth - 1])
                {
                        return false;
                }

                if ((intMonth == 2) && (intDay > daysInFebruary(intYear)))
                {
                        return false;
                }

                return true;
        }

        /**
         * isDate returns true if string argument date forms a valid date.
         */
        public static boolean isDate(String date)
        {
                if (isEmpty(date))
                {
                        return defaultEmptyOK;
                }

                String month;
                String day;
                String year;

                int dateSlash1 = date.indexOf("/");
                int dateSlash2 = date.lastIndexOf("/");

                if ((dateSlash1 <= 0) || (dateSlash1 == dateSlash2))
                {
                        return false;
                }

                month = date.substring(0, dateSlash1);
                day = date.substring(dateSlash1 + 1, dateSlash2);
                year = date.substring(dateSlash2 + 1);

                return isDate(year, month, day);
        }

        /**
         * isDate returns true if string argument date forms a valid date and is after today.
         */
        public static boolean isDateAfterToday(String date)
        {
                if (isEmpty(date))
                {
                        return defaultEmptyOK;
                }

                int dateSlash1 = date.indexOf("/");
                int dateSlash2 = date.lastIndexOf("/");

                if (dateSlash1 <= 0)
                {
                        return false;
                }

                java.util.Date passed = null;

                if (dateSlash1 == dateSlash2)
                {
                        // consider the day to be optional; use the first day of the following month for comparison since this is an is after test
                        String month = date.substring(0, dateSlash1);
                        String day = "28";
                        String year = date.substring(dateSlash1 + 1);

                        if (!isDate(year, month, day))
                        {
                                return false;
                        }

                        try
                        {
                                int monthInt = Integer.parseInt(month);
                                int yearInt = Integer.parseInt(year);
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(yearInt, monthInt - 1, 0, 0, 0, 0);
                                calendar.add(Calendar.MONTH, 1);
                                passed = new java.util.Date(calendar.getTime().getTime());
                        }
                        catch (Exception e)
                        {
                                passed = null;
                        }
                }
                else
                {
                        String month = date.substring(0, dateSlash1);
                        String day = date.substring(dateSlash1 + 1, dateSlash2);
                        String year = date.substring(dateSlash2 + 1);

                        if (!isDate(year, month, day))
                        {
                                return false;
                        }

                        passed = DateTimeUtil.toDate(month, day, year, "0", "0", "0");
                }

                java.util.Date now = new Date();

                if (passed != null)
                {
                        return passed.after(now);
                }
                else
                {
                        return false;
                }
        }

        /**
         * isTime returns true if string arguments hour, minute, and second form a valid time.
         */
        public static boolean isTime(String hour, String minute, String second)
        {
                // catch invalid years(not 2- or 4-digit) and invalid months and days.
                if (isHour(hour) && isMinute(minute) && isSecond(second))
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        /**
         * isTime returns true if string argument time forms a valid time.
         */
        public static boolean isTime(String time)
        {
                if (isEmpty(time))
                {
                        return defaultEmptyOK;
                }

                String hour;
                String minute;
                String second;

                int timeColon1 = time.indexOf(":");
                int timeColon2 = time.lastIndexOf(":");

                if (timeColon1 <= 0)
                {
                        return false;
                }

                hour = time.substring(0, timeColon1);

                if (timeColon1 == timeColon2)
                {
                        minute = time.substring(timeColon1 + 1);
                        second = "0";
                }
                else
                {
                        minute = time.substring(timeColon1 + 1, timeColon2);
                        second = time.substring(timeColon2 + 1);
                }

                return isTime(hour, minute, second);
        }
}
