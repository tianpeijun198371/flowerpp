/*******************************************************************************
 **
 ** Filename:  ServletWriter.java
 **
 ** File Description:  This class provides a simple method of posting multiple
 ** Serialized objects to a Java servlet and getting objects in return. This
 ** code was inspired by code samples from the book 'Java Servlet Programming'
 ** by Jason Hunter and William Crawford (O'Reilly & Associates. 1998).
 **
 ** Author: ADL Technical Team
 **
 ** Contract Number:
 ** Company Name: CTC
 **
 ** Module/Package Name:
 ** Module/Package Description:
 **
 ** Design Issues:
 **
 ** Implementation Issues:
 ** Known Problems:
 ** Side Effects:
 **
 ** References: ADL SCORM
 **
 /*******************************************************************************
 **
 ** Concurrent Technologies Corporation (CTC) grants you ("Licensee") a non-
 ** exclusive, royalty free, license to use, modify and redistribute this
 ** software in source and binary code form, provided that i) this copyright
 ** notice and license appear on all copies of the software; and ii) Licensee
 ** does not utilize the software in a manner which is disparaging to CTC.
 **
 ** This software is provided "AS IS," without a warranty of any kind.  ALL
 ** EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 ** IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-
 ** INFRINGEMENT, ARE HEREBY EXCLUDED.  CTC AND ITS LICENSORS SHALL NOT BE LIABLE
 ** FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 ** DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES.  IN NO EVENT WILL CTC  OR ITS
 ** LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 ** INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 ** CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 ** OR INABILITY TO USE SOFTWARE, EVEN IF CTC  HAS BEEN ADVISED OF THE
 ** POSSIBILITY OF SUCH DAMAGES.
 **
 *******************************************************************************/
package com.ulearning.ulms.scorm.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.net.URL;
import java.net.URLConnection;


public class ServletWriter
{
        static public ObjectInputStream postObjects(URL servlet,
                                                    Serializable[] objs, String sessionid) throws Exception
        {
                //System.out.println(
                //        "[servletWriter]postObjects  In ServletWriter::postObjects()");

                URLConnection con;

                try
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects   Opening HTTP URL connection to servlet.");
                        con = servlet.openConnection();
                }
                catch (Exception e)
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects  Exception caught In ServletWriter::postObjects()");
                        //System.out.println("[servletWriter]postObjects Exception= " +
                        //        e.getMessage());
                        e.printStackTrace();
                        throw e;
                }

                //System.out.println(
                //        "[servletWriter]postObjects Exception  HTTP connection to servlet is open");
                //System.out.println(
                //        "[servletWriter]postObjects configuring HTTP connection properties");

                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setRequestProperty("Content-Type", "text/plain");
                con.setAllowUserInteraction(false);
                con.setRequestProperty("Cookie", sessionid);

                // Write the arguments as post data
                ObjectOutputStream out;

                try
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects  Creating new http output stream");
                        out = new ObjectOutputStream(con.getOutputStream());

                        //System.out.println(
                        //        "[servletWriter]postObjects   Created new http output stream.");
                        //System.out.println(
                        //        "[servletWriter]postObjects   Writing command and data to servlet...");

                        int numObjects = objs.length;

                        //System.out.println("[servletWriter]postObjects   Num objects: " +
                        //        numObjects);

                        for (int x = 0; x < numObjects; x++)
                        {
                                out.writeObject(objs[x]);
                                //System.out.println(
                                 //       "[servletWriter]postObjects   just wrote a serialized object " +
                                 //               "on output stream... " + objs[x].getClass().getName());
                        }
                }
                catch (Exception e)
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects  Exception caught In ServletWriter::postObjects()");
                        //System.out.println("[servletWriter]postObjects  ex=" +
                        //        e.getMessage());
                        e.printStackTrace();
                        throw e;
                }

                try
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects   Flushing Object Output Stream.");
                        out.flush();
                }
                catch (IOException ioe)
                {
                        //System.out.println(
                         //       "[servletWriter]postObjects    Caught IOException when calling out.flush()");
                        ioe.printStackTrace();
                        throw ioe;
                }
                catch (Exception e)
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects   Caught Exception when calling out.flush()");
                        e.printStackTrace();
                        throw e;
                }

                try
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects    Closing Object Output Stream.");
                        out.close();
                }
                catch (IOException ioe)
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects   Caught IOException when calling out.close()");
                        //System.out.println("[servletWriter]postObjects   ex=" +
                        //        ioe.getMessage());

                        ioe.printStackTrace();
                        throw ioe;
                }
                catch (Exception e)
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects   Caught Exception when calling out.close()");

                        e.printStackTrace();
                        throw e;
                }

                //if ( true )
                //{
                //   LogUtil.debug("scorm","[ServletWriter]--about to do four things");
                //   if ( con == null )
                //   {
                //       LogUtil.debug("scorm","[ServletWriter]--the connection is gone");
                //   }
                //   LogUtil.debug("scorm","[ServletWriter]--Content: "+ String.valueOf(con.getContent()));
                //   LogUtil.debug("scorm","[ServletWriter]--did first");
                //   LogUtil.debug("scorm","[ServletWriter]--Content Type: " + con.getContentType());
                //   LogUtil.debug("scorm","[ServletWriter]--did second");
                //   LogUtil.debug("scorm","[ServletWriter]--Content Encoding: " + con.getContentEncoding());
                //   LogUtil.debug("scorm","[ServletWriter]--did third");
                //     System.out.println("[servletWriter]Ready to create ObjectInputStream object." );
                //   LogUtil.debug("scorm","[ServletWriter]--did last");
                //}
                ObjectInputStream in;

                try
                {
                        //System.out.println(
                         //       "[servletWriter]postObjects    Creating new http input stream.");
                        in = new ObjectInputStream(con.getInputStream());
                }
                catch (Exception e)
                {
                        //System.out.println(
                        //        "[servletWriter]postObjects  Exception caught In ServletWriter::postObjects()");
                        //System.out.println("[servletWriter]postObjects   ex=" +
                        //        e.getMessage());

                        e.printStackTrace();
                        throw e;
                }

                return in;
        }
}
