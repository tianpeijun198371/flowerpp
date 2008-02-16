package com.ulearning.ulms.tools.doc.document.ejb;

import java.rmi.RemoteException;

import javax.ejb.*;


public class DocumentEJB implements javax.ejb.SessionBean
{
        /**
         * @J2EE_METHOD --  DocumentEJB
         */
        public DocumentEJB()
        {
        }

        /**
         * @J2EE_METHOD --  ejbCreate
         * Called by the container to create a session bean instance. Its parameters typically
         * contain the information the client uses to customize the bean instance for its use.
         * It requires a matching pair in the bean class and its home interface.
         */
        public void ejbCreate()
        {
        }

        /**
         * @J2EE_METHOD --  ejbRemove
         * A container invokes this method before it ends the life of the session object. This
         * happens as a result of a client's invoking a remove operation, or when a container
         * decides to terminate the session object after a timeout. This method is called with
         * no transaction context.
         */
        public void ejbRemove()
        {
        }

        /**
         * @J2EE_METHOD --  ejbActivate
         * The activate method is called when the instance is activated from its 'passive' state.
         * The instance should acquire any resource that it has released earlier in the ejbPassivate()
         * method. This method is called with no transaction context.
         */
        public void ejbActivate()
        {
        }

        /**
         * @J2EE_METHOD --  ejbPassivate
         * The passivate method is called before the instance enters the 'passive' state. The
         * instance should release any resources that it can re-acquire later in the ejbActivate()
         * method. After the passivate method completes, the instance must be in a state that
         * allows the container to use the Java Serialization protocol to externalize and store
         * away the instance's state. This method is called with no transaction context.
         */
        public void ejbPassivate()
        {
        }

        /**
         * @J2EE_METHOD --  setSessionContext
         * Set the associated session context. The container calls this method after the instance
         * creation. The enterprise Bean instance should store the reference to the context
         * object in an instance variable. This method is called with no transaction context.
         */
        public void setSessionContext(javax.ejb.SessionContext arg0)
        {
        }
}
