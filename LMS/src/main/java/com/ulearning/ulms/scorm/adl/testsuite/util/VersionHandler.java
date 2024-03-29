// Source File Name:   VersionHandler.java
package com.ulearning.ulms.scorm.adl.testsuite.util;

// Referenced classes of package com.ulearning.ulms.scorm.adl.testsuite.util:

//            AuditorIndicator
public class VersionHandler
{
        private String SCORM_VERSION;
        private String TESTSUITE_VERSION;
        private String JAR_VERSION;
        private String CERT_STMT;

        public VersionHandler()
        {
                SCORM_VERSION = new String("Version 1.2");
                TESTSUITE_VERSION = new String("Version 1.2.7");

                if (AuditorIndicator.ON)
                {
                        JAR_VERSION = new String("Auditor Test");
                }
                else
                {
                        JAR_VERSION = new String("Self Test");
                }

                CERT_STMT = new String(
                        "Successful outcome of this test does not constitute ADL Certification unless the test was conducted by an ADL Certified Auditor.");
        }

        public String getSCORMVersion()
        {
                return SCORM_VERSION;
        }

        public String getTestsuiteVersion()
        {
                return TESTSUITE_VERSION;
        }

        public String getJarVersion()
        {
                return JAR_VERSION;
        }

        public String getCertStmt()
        {
                return CERT_STMT;
        }
}
