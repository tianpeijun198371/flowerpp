/**
 * VersionStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package com.ulearning.ulms.tools.meeting.xuechuang.service;


/*
 *  VersionStub java implementation
 */
public class VersionStub extends org.apache.axis2.client.Stub
{
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();
        private javax.xml.namespace.QName[] opNameArray = null;

        /**
         * Constructor that takes in a configContext
         */
        public VersionStub(
                org.apache.axis2.context.ConfigurationContext configurationContext,
                java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault
        {
                this(configurationContext, targetEndpoint, false);
        }

        /**
         * Constructor that takes in a configContext  and useseperate listner
         */
        public VersionStub(
                org.apache.axis2.context.ConfigurationContext configurationContext,
                java.lang.String targetEndpoint, boolean useSeparateListener)
                throws org.apache.axis2.AxisFault
        {
                //To populate AxisService
                populateAxisService();
                populateFaults();

                _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,
                        _service);

                configurationContext = _serviceClient.getServiceContext()
                        .getConfigurationContext();

                _serviceClient.getOptions()
                        .setTo(new org.apache.axis2.addressing.EndpointReference(
                                targetEndpoint));
                _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

                //Set the soap version
                _serviceClient.getOptions()
                        .setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
        }

        /**
         * Default Constructor
         */
        public VersionStub(
                org.apache.axis2.context.ConfigurationContext configurationContext)
                throws org.apache.axis2.AxisFault
        {
                this(configurationContext,
                        "http://192.168.0.31:8080/axis2/services/Version");
        }

        /**
         * Default Constructor
         */
        public VersionStub() throws org.apache.axis2.AxisFault
        {
                this("http://192.168.0.31:8080/axis2/services/Version");
        }

        /**
         * Constructor taking the target endpoint
         */
        public VersionStub(java.lang.String targetEndpoint)
                throws org.apache.axis2.AxisFault
        {
                this(null, targetEndpoint);
        }

        private void populateAxisService() throws org.apache.axis2.AxisFault
        {
                //creating the Service with a unique name
                _service = new org.apache.axis2.description.AxisService("Version" +
                        this.hashCode());

                //creating the operations
                org.apache.axis2.description.AxisOperation __operation;

                _operations = new org.apache.axis2.description.AxisOperation[1];

                __operation = new org.apache.axis2.description.OutInAxisOperation();

                __operation.setName(new javax.xml.namespace.QName(
                        "http://axisversion.sample", "getVersion"));
                _service.addOperation(__operation);

                _operations[0] = __operation;
        }

        //populates the faults
        private void populateFaults()
        {
                faultExceptionNameMap.put(new javax.xml.namespace.QName(
                        "http://axisversion.sample", "Exception"),
                        "com.ulearning.ulms.tools.meeting.xuechuang.service.ExceptionException0");
                faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
                        "http://axisversion.sample", "Exception"),
                        "com.ulearning.ulms.tools.meeting.xuechuang.service.ExceptionException0");
                faultMessageMap.put(new javax.xml.namespace.QName(
                        "http://axisversion.sample", "Exception"),
                        "com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub$Exception0");
        }

        /**
         * Auto generated method signature
         */
        public VersionStub.GetVersionResponse getVersion()
                throws java.rmi.RemoteException,
                ExceptionException0
        {
                try
                {
                        org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
                        _operationClient.getOptions().setAction("urn:getVersion");
                        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

                        addPropertyToOperationClient(_operationClient,
                                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                                "&");

                        // create a message context
                        org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                        // create SOAP envelope with that payload
                        org.apache.axiom.soap.SOAPEnvelope env = null;

                        //Style is taken to be "document". No input parameters
                        // according to the WS-Basic profile in this case we have to send an empty soap message
                        org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient.getOptions()
                                .getSoapVersionURI());
                        env = factory.getDefaultEnvelope();

                        //adding SOAP soap_headers
                        _serviceClient.addHeadersToEnvelope(env);
                        // set the message context with that soap envelope
                        _messageContext.setEnvelope(env);

                        // add the message contxt to the operation client
                        _operationClient.addMessageContext(_messageContext);

                        //execute the operation client
                        _operationClient.execute(true);

                        org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                        org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

                        java.lang.Object object = fromOM(_returnEnv.getBody()
                                .getFirstElement(),
                                com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.GetVersionResponse.class,
                                getEnvelopeNamespaces(_returnEnv));
                        _messageContext.getTransportOut().getSender()
                                .cleanup(_messageContext);

                        return (com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.GetVersionResponse) object;
                }
                catch (org.apache.axis2.AxisFault f)
                {
                        org.apache.axiom.om.OMElement faultElt = f.getDetail();

                        if (faultElt != null)
                        {
                                if (faultExceptionNameMap.containsKey(faultElt.getQName()))
                                {
                                        //make the fault by reflection
                                        try
                                        {
                                                java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
                                                java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                                                java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();

                                                //message class
                                                java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
                                                java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                                                java.lang.Object messageObject = fromOM(faultElt,
                                                        messageClass, null);
                                                java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                                        new java.lang.Class[]{messageClass});
                                                m.invoke(ex, new java.lang.Object[]{messageObject});

                                                if (ex instanceof com.ulearning.ulms.tools.meeting.xuechuang.service.ExceptionException0)
                                                {
                                                        throw (com.ulearning.ulms.tools.meeting.xuechuang.service.ExceptionException0) ex;
                                                }

                                                throw new java.rmi.RemoteException(ex.getMessage(), ex);
                                        }
                                        catch (java.lang.ClassCastException e)
                                        {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                throw f;
                                        }
                                        catch (java.lang.ClassNotFoundException e)
                                        {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                throw f;
                                        }
                                        catch (java.lang.NoSuchMethodException e)
                                        {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                throw f;
                                        }
                                        catch (java.lang.reflect.InvocationTargetException e)
                                        {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                throw f;
                                        }
                                        catch (java.lang.IllegalAccessException e)
                                        {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                throw f;
                                        }
                                        catch (java.lang.InstantiationException e)
                                        {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                throw f;
                                        }
                                }
                                else
                                {
                                        throw f;
                                }
                        }
                        else
                        {
                                throw f;
                        }
                }
        }

        /**
         * A utility method that copies the namepaces from the SOAPEnvelope
         */
        private java.util.Map getEnvelopeNamespaces(
                org.apache.axiom.soap.SOAPEnvelope env)
        {
                java.util.Map returnMap = new java.util.HashMap();
                java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();

                while (namespaceIterator.hasNext())
                {
                        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
                        returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
                }

                return returnMap;
        }

        private boolean optimizeContent(javax.xml.namespace.QName opName)
        {
                if (opNameArray == null)
                {
                        return false;
                }

                for (int i = 0; i < opNameArray.length; i++)
                {
                        if (opName.equals(opNameArray[i]))
                        {
                                return true;
                        }
                }

                return false;
        }

        private org.apache.axiom.om.OMElement toOM(
                com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.GetVersionResponse param,
                boolean optimizeContent) throws org.apache.axis2.AxisFault
        {
                try
                {
                        return param.getOMElement(com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.GetVersionResponse.MY_QNAME,
                                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                }
                catch (org.apache.axis2.databinding.ADBException e)
                {
                        throw org.apache.axis2.AxisFault.makeFault(e);
                }
        }

        private org.apache.axiom.om.OMElement toOM(
                com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.Exception0 param,
                boolean optimizeContent) throws org.apache.axis2.AxisFault
        {
                try
                {
                        return param.getOMElement(com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.Exception0.MY_QNAME,
                                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                }
                catch (org.apache.axis2.databinding.ADBException e)
                {
                        throw org.apache.axis2.AxisFault.makeFault(e);
                }
        }

        /**
         * get the default envelope
         */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
                org.apache.axiom.soap.SOAPFactory factory)
        {
                return factory.getDefaultEnvelope();
        }

        private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
                                        java.lang.Class type, java.util.Map extraNamespaces)
                throws org.apache.axis2.AxisFault
        {
                try
                {
                        if (com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.GetVersionResponse.class.equals(
                                type))
                        {
                                return com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.GetVersionResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                        }

                        if (com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.Exception0.class.equals(
                                type))
                        {
                                return com.ulearning.ulms.tools.meeting.xuechuang.service.VersionStub.Exception0.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                        }
                }
                catch (java.lang.Exception e)
                {
                        throw org.apache.axis2.AxisFault.makeFault(e);
                }

                return null;
        }

        //http://localhost:8080/axis2/services/Version
        public static class ExtensionMapper
        {
                public static java.lang.Object getTypeObject(
                        java.lang.String namespaceURI, java.lang.String typeName,
                        javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception
                {
                        if ("http://axisversion.sample".equals(namespaceURI) &&
                                "Exception".equals(typeName))
                        {
                                return Exception.Factory.parse(reader);
                        }

                        throw new org.apache.axis2.databinding.ADBException(
                                "Unsupported type " + namespaceURI + " " + typeName);
                }
        }

        public static class Exception implements org.apache.axis2.databinding.ADBBean
        {
                /**
                 * field for Exception
                 */
                protected org.apache.axiom.om.OMElement localException;

                /*  This tracker boolean wil be used to detect whether the user called the set method
                *   for this attribute. It will be used to determine whether to include this field
                *   in the serialized XML
                */
                protected boolean localExceptionTracker = false;

                /* This type was generated from the piece of schema that had
                  name = Exception
                  Namespace URI = http://axisversion.sample
                  Namespace Prefix = ns1
                */
                private static java.lang.String generatePrefix(
                        java.lang.String namespace)
                {
                        if (namespace.equals("http://axisversion.sample"))
                        {
                                return "ns1";
                        }

                        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                /**
                 * Auto generated getter method
                 *
                 * @return org.apache.axiom.om.OMElement
                 */
                public org.apache.axiom.om.OMElement getException()
                {
                        return localException;
                }

                /**
                 * Auto generated setter method
                 *
                 * @param param Exception
                 */
                public void setException(org.apache.axiom.om.OMElement param)
                {
                        if (param != null)
                        {
                                //update the setting tracker
                                localExceptionTracker = true;
                        }
                        else
                        {
                                localExceptionTracker = true;
                        }

                        this.localException = param;
                }

                /**
                 * isReaderMTOMAware
                 *
                 * @return true if the reader supports MTOM
                 */
                public static boolean isReaderMTOMAware(
                        javax.xml.stream.XMLStreamReader reader)
                {
                        boolean isReaderMTOMAware = false;

                        try
                        {
                                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                                        org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
                        }
                        catch (java.lang.IllegalArgumentException e)
                        {
                                isReaderMTOMAware = false;
                        }

                        return isReaderMTOMAware;
                }

                /**
                 * @param parentQName
                 * @param factory
                 * @return org.apache.axiom.om.OMElement
                 */
                public org.apache.axiom.om.OMElement getOMElement(
                        final javax.xml.namespace.QName parentQName,
                        final org.apache.axiom.om.OMFactory factory)
                        throws org.apache.axis2.databinding.ADBException
                {
                        org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                                parentQName)
                        {
                                public void serialize(
                                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                        throws javax.xml.stream.XMLStreamException
                                {
                                        Exception.this.serialize(parentQName, factory, xmlWriter);
                                }
                        };

                        return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                                factory, dataSource);
                }

                public void serialize(final javax.xml.namespace.QName parentQName,
                                      final org.apache.axiom.om.OMFactory factory,
                                      org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException,
                        org.apache.axis2.databinding.ADBException
                {
                        java.lang.String prefix = null;
                        java.lang.String namespace = null;

                        prefix = parentQName.getPrefix();
                        namespace = parentQName.getNamespaceURI();

                        if (namespace != null)
                        {
                                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                                if (writerPrefix != null)
                                {
                                        xmlWriter.writeStartElement(namespace,
                                                parentQName.getLocalPart());
                                }
                                else
                                {
                                        if (prefix == null)
                                        {
                                                prefix = generatePrefix(namespace);
                                        }

                                        xmlWriter.writeStartElement(prefix,
                                                parentQName.getLocalPart(), namespace);
                                        xmlWriter.writeNamespace(prefix, namespace);
                                        xmlWriter.setPrefix(prefix, namespace);
                                }
                        }
                        else
                        {
                                xmlWriter.writeStartElement(parentQName.getLocalPart());
                        }

                        if (localExceptionTracker)
                        {
                                if (localException != null)
                                {
                                        // write null attribute
                                        java.lang.String namespace2 = "http://axisversion.sample";

                                        if (!namespace2.equals(""))
                                        {
                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                if (prefix2 == null)
                                                {
                                                        prefix2 = generatePrefix(namespace2);

                                                        xmlWriter.writeStartElement(prefix2, "Exception",
                                                                namespace2);
                                                        xmlWriter.writeNamespace(prefix2, namespace2);
                                                        xmlWriter.setPrefix(prefix2, namespace2);
                                                }
                                                else
                                                {
                                                        xmlWriter.writeStartElement(namespace2, "Exception");
                                                }
                                        }
                                        else
                                        {
                                                xmlWriter.writeStartElement("Exception");
                                        }

                                        localException.serialize(xmlWriter);
                                        xmlWriter.writeEndElement();
                                }
                                else
                                {
                                        // write null attribute
                                        java.lang.String namespace2 = "http://axisversion.sample";

                                        if (!namespace2.equals(""))
                                        {
                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                if (prefix2 == null)
                                                {
                                                        prefix2 = generatePrefix(namespace2);

                                                        xmlWriter.writeStartElement(prefix2, "Exception",
                                                                namespace2);
                                                        xmlWriter.writeNamespace(prefix2, namespace2);
                                                        xmlWriter.setPrefix(prefix2, namespace2);
                                                }
                                                else
                                                {
                                                        xmlWriter.writeStartElement(namespace2, "Exception");
                                                }
                                        }
                                        else
                                        {
                                                xmlWriter.writeStartElement("Exception");
                                        }

                                        // write the nil attribute
                                        writeAttribute("xsi",
                                                "http://www.w3.org/2001/XMLSchema-instance", "nil",
                                                "1", xmlWriter);
                                        xmlWriter.writeEndElement();
                                }
                        }

                        xmlWriter.writeEndElement();
                }

                /**
                 * Util method to write an attribute with the ns prefix
                 */
                private void writeAttribute(java.lang.String prefix,
                                            java.lang.String namespace, java.lang.String attName,
                                            java.lang.String attValue,
                                            javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (xmlWriter.getPrefix(namespace) == null)
                        {
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                        }

                        xmlWriter.writeAttribute(namespace, attName, attValue);
                }

                /**
                 * Util method to write an attribute without the ns prefix
                 */
                private void writeAttribute(java.lang.String namespace,
                                            java.lang.String attName, java.lang.String attValue,
                                            javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (namespace.equals(""))
                        {
                                xmlWriter.writeAttribute(attName, attValue);
                        }
                        else
                        {
                                registerPrefix(xmlWriter, namespace);
                                xmlWriter.writeAttribute(namespace, attName, attValue);
                        }
                }

                /**
                 * Util method to write an attribute without the ns prefix
                 */
                private void writeQNameAttribute(java.lang.String namespace,
                                                 java.lang.String attName, javax.xml.namespace.QName qname,
                                                 javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String attributeNamespace = qname.getNamespaceURI();
                        java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

                        if (attributePrefix == null)
                        {
                                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                        }

                        java.lang.String attributeValue;

                        if (attributePrefix.trim().length() > 0)
                        {
                                attributeValue = attributePrefix + ":" + qname.getLocalPart();
                        }
                        else
                        {
                                attributeValue = qname.getLocalPart();
                        }

                        if (namespace.equals(""))
                        {
                                xmlWriter.writeAttribute(attName, attributeValue);
                        }
                        else
                        {
                                registerPrefix(xmlWriter, namespace);
                                xmlWriter.writeAttribute(namespace, attName, attributeValue);
                        }
                }

                /**
                 * method to handle Qnames
                 */
                private void writeQName(javax.xml.namespace.QName qname,
                                        javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String namespaceURI = qname.getNamespaceURI();

                        if (namespaceURI != null)
                        {
                                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                                if (prefix == null)
                                {
                                        prefix = generatePrefix(namespaceURI);
                                        xmlWriter.writeNamespace(prefix, namespaceURI);
                                        xmlWriter.setPrefix(prefix, namespaceURI);
                                }

                                if (prefix.trim().length() > 0)
                                {
                                        xmlWriter.writeCharacters(prefix + ":" +
                                                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                        qname));
                                }
                                else
                                {
                                        // i.e this is the default namespace
                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                qname));
                                }
                        }
                        else
                        {
                                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                        qname));
                        }
                }

                private void writeQNames(javax.xml.namespace.QName[] qnames,
                                         javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (qnames != null)
                        {
                                // we have to store this data until last moment since it is not possible to write any
                                // namespace data after writing the charactor data
                                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                                java.lang.String namespaceURI = null;
                                java.lang.String prefix = null;

                                for (int i = 0; i < qnames.length; i++)
                                {
                                        if (i > 0)
                                        {
                                                stringToWrite.append(" ");
                                        }

                                        namespaceURI = qnames[i].getNamespaceURI();

                                        if (namespaceURI != null)
                                        {
                                                prefix = xmlWriter.getPrefix(namespaceURI);

                                                if ((prefix == null) || (prefix.length() == 0))
                                                {
                                                        prefix = generatePrefix(namespaceURI);
                                                        xmlWriter.writeNamespace(prefix, namespaceURI);
                                                        xmlWriter.setPrefix(prefix, namespaceURI);
                                                }

                                                if (prefix.trim().length() > 0)
                                                {
                                                        stringToWrite.append(prefix).append(":")
                                                                .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                                        qnames[i]));
                                                }
                                                else
                                                {
                                                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                                qnames[i]));
                                                }
                                        }
                                        else
                                        {
                                                stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                        qnames[i]));
                                        }
                                }

                                xmlWriter.writeCharacters(stringToWrite.toString());
                        }
                }

                /**
                 * Register a namespace prefix
                 */
                private java.lang.String registerPrefix(
                        javax.xml.stream.XMLStreamWriter xmlWriter,
                        java.lang.String namespace)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String prefix = xmlWriter.getPrefix(namespace);

                        if (prefix == null)
                        {
                                prefix = generatePrefix(namespace);

                                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
                                {
                                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                                }

                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                        }

                        return prefix;
                }

                /**
                 * databinding method to get an XML representation of this object
                 */
                public javax.xml.stream.XMLStreamReader getPullParser(
                        javax.xml.namespace.QName qName)
                        throws org.apache.axis2.databinding.ADBException
                {
                        java.util.ArrayList elementList = new java.util.ArrayList();
                        java.util.ArrayList attribList = new java.util.ArrayList();

                        if (localExceptionTracker)
                        {
                                elementList.add(new javax.xml.namespace.QName(
                                        "http://axisversion.sample", "Exception"));

                                elementList.add((localException == null) ? null : localException);
                        }

                        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                                elementList.toArray(), attribList.toArray());
                }

                /**
                 * Factory class that keeps the parse method
                 */
                public static class Factory
                {
                        /**
                         * static method to create the object
                         * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
                         * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
                         * Postcondition: If this object is an element, the reader is positioned at its end element
                         * If this object is a complex type, the reader is positioned at the end element of its outer element
                         */
                        public static Exception parse(
                                javax.xml.stream.XMLStreamReader reader)
                                throws java.lang.Exception
                        {
                                Exception object = new Exception();

                                int event;
                                java.lang.String nillableValue = null;
                                java.lang.String prefix = "";
                                java.lang.String namespaceuri = "";

                                try
                                {
                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        if (reader.getAttributeValue(
                                                "http://www.w3.org/2001/XMLSchema-instance",
                                                "type") != null)
                                        {
                                                java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                                        "type");

                                                if (fullTypeName != null)
                                                {
                                                        java.lang.String nsPrefix = null;

                                                        if (fullTypeName.indexOf(":") > -1)
                                                        {
                                                                nsPrefix = fullTypeName.substring(0,
                                                                        fullTypeName.indexOf(":"));
                                                        }

                                                        nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                                                        java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                                                ":") + 1);

                                                        if (!"Exception".equals(type))
                                                        {
                                                                //find namespace for the prefix
                                                                java.lang.String nsUri = reader.getNamespaceContext()
                                                                        .getNamespaceURI(nsPrefix);

                                                                return (Exception) ExtensionMapper.getTypeObject(nsUri,
                                                                        type, reader);
                                                        }
                                                }
                                        }

                                        // Note all attributes that were handled. Used to differ normal attributes
                                        // from anyAttributes.
                                        java.util.Vector handledAttributes = new java.util.Vector();

                                        reader.next();

                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        if (reader.isStartElement() &&
                                                new javax.xml.namespace.QName(
                                                        "http://axisversion.sample", "Exception").equals(
                                                        reader.getName()))
                                        {
                                                boolean loopDone1 = false;
                                                javax.xml.namespace.QName startQname1 = new javax.xml.namespace.QName("http://axisversion.sample",
                                                        "Exception");

                                                while (!loopDone1)
                                                {
                                                        if (reader.isStartElement() &&
                                                                startQname1.equals(reader.getName()))
                                                        {
                                                                loopDone1 = true;
                                                        }
                                                        else
                                                        {
                                                                reader.next();
                                                        }
                                                }

                                                // We need to wrap the reader so that it produces a fake START_DOCUEMENT event
                                                // this is needed by the builder classes
                                                org.apache.axis2.databinding.utils.NamedStaxOMBuilder builder1 =
                                                        new org.apache.axis2.databinding.utils.NamedStaxOMBuilder(new org.apache.axis2.util.StreamWrapper(
                                                                reader), startQname1);
                                                object.setException(builder1.getOMElement()
                                                        .getFirstElement());

                                                reader.next();
                                        } // End of if for expected property start element

                                        else
                                        {
                                        }

                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        if (reader.isStartElement())
                                        {
                                                // A start element we are not expecting indicates a trailing invalid property
                                                throw new org.apache.axis2.databinding.ADBException(
                                                        "Unexpected subelement " + reader.getLocalName());
                                        }
                                }
                                catch (javax.xml.stream.XMLStreamException e)
                                {
                                        throw new java.lang.Exception(e);
                                }

                                return object;
                        }
                } //end of factory class
        }

        public static class GetVersionResponse implements org.apache.axis2.databinding.ADBBean
        {
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://axisversion.sample",
                        "getVersionResponse", "ns1");

                /**
                 * field for _return
                 */
                protected java.lang.String local_return;

                /*  This tracker boolean wil be used to detect whether the user called the set method
                *   for this attribute. It will be used to determine whether to include this field
                *   in the serialized XML
                */
                protected boolean local_returnTracker = false;

                private static java.lang.String generatePrefix(
                        java.lang.String namespace)
                {
                        if (namespace.equals("http://axisversion.sample"))
                        {
                                return "ns1";
                        }

                        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                /**
                 * Auto generated getter method
                 *
                 * @return java.lang.String
                 */
                public java.lang.String get_return()
                {
                        return local_return;
                }

                /**
                 * Auto generated setter method
                 *
                 * @param param _return
                 */
                public void set_return(java.lang.String param)
                {
                        if (param != null)
                        {
                                //update the setting tracker
                                local_returnTracker = true;
                        }
                        else
                        {
                                local_returnTracker = true;
                        }

                        this.local_return = param;
                }

                /**
                 * isReaderMTOMAware
                 *
                 * @return true if the reader supports MTOM
                 */
                public static boolean isReaderMTOMAware(
                        javax.xml.stream.XMLStreamReader reader)
                {
                        boolean isReaderMTOMAware = false;

                        try
                        {
                                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                                        org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
                        }
                        catch (java.lang.IllegalArgumentException e)
                        {
                                isReaderMTOMAware = false;
                        }

                        return isReaderMTOMAware;
                }

                /**
                 * @param parentQName
                 * @param factory
                 * @return org.apache.axiom.om.OMElement
                 */
                public org.apache.axiom.om.OMElement getOMElement(
                        final javax.xml.namespace.QName parentQName,
                        final org.apache.axiom.om.OMFactory factory)
                        throws org.apache.axis2.databinding.ADBException
                {
                        org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                                MY_QNAME)
                        {
                                public void serialize(
                                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                        throws javax.xml.stream.XMLStreamException
                                {
                                        GetVersionResponse.this.serialize(MY_QNAME, factory,
                                                xmlWriter);
                                }
                        };

                        return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                                factory, dataSource);
                }

                public void serialize(final javax.xml.namespace.QName parentQName,
                                      final org.apache.axiom.om.OMFactory factory,
                                      org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException,
                        org.apache.axis2.databinding.ADBException
                {
                        java.lang.String prefix = null;
                        java.lang.String namespace = null;

                        prefix = parentQName.getPrefix();
                        namespace = parentQName.getNamespaceURI();

                        if (namespace != null)
                        {
                                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                                if (writerPrefix != null)
                                {
                                        xmlWriter.writeStartElement(namespace,
                                                parentQName.getLocalPart());
                                }
                                else
                                {
                                        if (prefix == null)
                                        {
                                                prefix = generatePrefix(namespace);
                                        }

                                        xmlWriter.writeStartElement(prefix,
                                                parentQName.getLocalPart(), namespace);
                                        xmlWriter.writeNamespace(prefix, namespace);
                                        xmlWriter.setPrefix(prefix, namespace);
                                }
                        }
                        else
                        {
                                xmlWriter.writeStartElement(parentQName.getLocalPart());
                        }

                        if (local_returnTracker)
                        {
                                namespace = "http://axisversion.sample";

                                if (!namespace.equals(""))
                                {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null)
                                        {
                                                prefix = generatePrefix(namespace);

                                                xmlWriter.writeStartElement(prefix, "return", namespace);
                                                xmlWriter.writeNamespace(prefix, namespace);
                                                xmlWriter.setPrefix(prefix, namespace);
                                        }
                                        else
                                        {
                                                xmlWriter.writeStartElement(namespace, "return");
                                        }
                                }
                                else
                                {
                                        xmlWriter.writeStartElement("return");
                                }

                                if (local_return == null)
                                {
                                        // write the nil attribute
                                        writeAttribute("xsi",
                                                "http://www.w3.org/2001/XMLSchema-instance", "nil",
                                                "1", xmlWriter);
                                }
                                else
                                {
                                        xmlWriter.writeCharacters(local_return);
                                }

                                xmlWriter.writeEndElement();
                        }

                        xmlWriter.writeEndElement();
                }

                /**
                 * Util method to write an attribute with the ns prefix
                 */
                private void writeAttribute(java.lang.String prefix,
                                            java.lang.String namespace, java.lang.String attName,
                                            java.lang.String attValue,
                                            javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (xmlWriter.getPrefix(namespace) == null)
                        {
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                        }

                        xmlWriter.writeAttribute(namespace, attName, attValue);
                }

                /**
                 * Util method to write an attribute without the ns prefix
                 */
                private void writeAttribute(java.lang.String namespace,
                                            java.lang.String attName, java.lang.String attValue,
                                            javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (namespace.equals(""))
                        {
                                xmlWriter.writeAttribute(attName, attValue);
                        }
                        else
                        {
                                registerPrefix(xmlWriter, namespace);
                                xmlWriter.writeAttribute(namespace, attName, attValue);
                        }
                }

                /**
                 * Util method to write an attribute without the ns prefix
                 */
                private void writeQNameAttribute(java.lang.String namespace,
                                                 java.lang.String attName, javax.xml.namespace.QName qname,
                                                 javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String attributeNamespace = qname.getNamespaceURI();
                        java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

                        if (attributePrefix == null)
                        {
                                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                        }

                        java.lang.String attributeValue;

                        if (attributePrefix.trim().length() > 0)
                        {
                                attributeValue = attributePrefix + ":" + qname.getLocalPart();
                        }
                        else
                        {
                                attributeValue = qname.getLocalPart();
                        }

                        if (namespace.equals(""))
                        {
                                xmlWriter.writeAttribute(attName, attributeValue);
                        }
                        else
                        {
                                registerPrefix(xmlWriter, namespace);
                                xmlWriter.writeAttribute(namespace, attName, attributeValue);
                        }
                }

                /**
                 * method to handle Qnames
                 */
                private void writeQName(javax.xml.namespace.QName qname,
                                        javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String namespaceURI = qname.getNamespaceURI();

                        if (namespaceURI != null)
                        {
                                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                                if (prefix == null)
                                {
                                        prefix = generatePrefix(namespaceURI);
                                        xmlWriter.writeNamespace(prefix, namespaceURI);
                                        xmlWriter.setPrefix(prefix, namespaceURI);
                                }

                                if (prefix.trim().length() > 0)
                                {
                                        xmlWriter.writeCharacters(prefix + ":" +
                                                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                        qname));
                                }
                                else
                                {
                                        // i.e this is the default namespace
                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                qname));
                                }
                        }
                        else
                        {
                                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                        qname));
                        }
                }

                private void writeQNames(javax.xml.namespace.QName[] qnames,
                                         javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (qnames != null)
                        {
                                // we have to store this data until last moment since it is not possible to write any
                                // namespace data after writing the charactor data
                                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                                java.lang.String namespaceURI = null;
                                java.lang.String prefix = null;

                                for (int i = 0; i < qnames.length; i++)
                                {
                                        if (i > 0)
                                        {
                                                stringToWrite.append(" ");
                                        }

                                        namespaceURI = qnames[i].getNamespaceURI();

                                        if (namespaceURI != null)
                                        {
                                                prefix = xmlWriter.getPrefix(namespaceURI);

                                                if ((prefix == null) || (prefix.length() == 0))
                                                {
                                                        prefix = generatePrefix(namespaceURI);
                                                        xmlWriter.writeNamespace(prefix, namespaceURI);
                                                        xmlWriter.setPrefix(prefix, namespaceURI);
                                                }

                                                if (prefix.trim().length() > 0)
                                                {
                                                        stringToWrite.append(prefix).append(":")
                                                                .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                                        qnames[i]));
                                                }
                                                else
                                                {
                                                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                                qnames[i]));
                                                }
                                        }
                                        else
                                        {
                                                stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                        qnames[i]));
                                        }
                                }

                                xmlWriter.writeCharacters(stringToWrite.toString());
                        }
                }

                /**
                 * Register a namespace prefix
                 */
                private java.lang.String registerPrefix(
                        javax.xml.stream.XMLStreamWriter xmlWriter,
                        java.lang.String namespace)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String prefix = xmlWriter.getPrefix(namespace);

                        if (prefix == null)
                        {
                                prefix = generatePrefix(namespace);

                                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
                                {
                                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                                }

                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                        }

                        return prefix;
                }

                /**
                 * databinding method to get an XML representation of this object
                 */
                public javax.xml.stream.XMLStreamReader getPullParser(
                        javax.xml.namespace.QName qName)
                        throws org.apache.axis2.databinding.ADBException
                {
                        java.util.ArrayList elementList = new java.util.ArrayList();
                        java.util.ArrayList attribList = new java.util.ArrayList();

                        if (local_returnTracker)
                        {
                                elementList.add(new javax.xml.namespace.QName(
                                        "http://axisversion.sample", "return"));

                                elementList.add((local_return == null) ? null
                                        : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                        local_return));
                        }

                        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                                elementList.toArray(), attribList.toArray());
                }

                /**
                 * Factory class that keeps the parse method
                 */
                public static class Factory
                {
                        /**
                         * static method to create the object
                         * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
                         * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
                         * Postcondition: If this object is an element, the reader is positioned at its end element
                         * If this object is a complex type, the reader is positioned at the end element of its outer element
                         */
                        public static GetVersionResponse parse(
                                javax.xml.stream.XMLStreamReader reader)
                                throws java.lang.Exception
                        {
                                GetVersionResponse object = new GetVersionResponse();

                                int event;
                                java.lang.String nillableValue = null;
                                java.lang.String prefix = "";
                                java.lang.String namespaceuri = "";

                                try
                                {
                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        if (reader.getAttributeValue(
                                                "http://www.w3.org/2001/XMLSchema-instance",
                                                "type") != null)
                                        {
                                                java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                                        "type");

                                                if (fullTypeName != null)
                                                {
                                                        java.lang.String nsPrefix = null;

                                                        if (fullTypeName.indexOf(":") > -1)
                                                        {
                                                                nsPrefix = fullTypeName.substring(0,
                                                                        fullTypeName.indexOf(":"));
                                                        }

                                                        nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                                                        java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                                                ":") + 1);

                                                        if (!"getVersionResponse".equals(type))
                                                        {
                                                                //find namespace for the prefix
                                                                java.lang.String nsUri = reader.getNamespaceContext()
                                                                        .getNamespaceURI(nsPrefix);

                                                                return (GetVersionResponse) ExtensionMapper.getTypeObject(nsUri,
                                                                        type, reader);
                                                        }
                                                }
                                        }

                                        // Note all attributes that were handled. Used to differ normal attributes
                                        // from anyAttributes.
                                        java.util.Vector handledAttributes = new java.util.Vector();

                                        reader.next();

                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        if (reader.isStartElement() &&
                                                new javax.xml.namespace.QName(
                                                        "http://axisversion.sample", "return").equals(
                                                        reader.getName()))
                                        {
                                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                                        "nil");

                                                if (!"true".equals(nillableValue) &&
                                                        !"1".equals(nillableValue))
                                                {
                                                        java.lang.String content = reader.getElementText();

                                                        object.set_return(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                                content));
                                                }
                                                else
                                                {
                                                        reader.getElementText(); // throw away text nodes if any.
                                                }

                                                reader.next();
                                        } // End of if for expected property start element

                                        else
                                        {
                                        }

                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        if (reader.isStartElement())
                                        {
                                                // A start element we are not expecting indicates a trailing invalid property
                                                throw new org.apache.axis2.databinding.ADBException(
                                                        "Unexpected subelement " + reader.getLocalName());
                                        }
                                }
                                catch (javax.xml.stream.XMLStreamException e)
                                {
                                        throw new java.lang.Exception(e);
                                }

                                return object;
                        }
                } //end of factory class
        }

        public static class Exception0 implements org.apache.axis2.databinding.ADBBean
        {
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://axisversion.sample",
                        "Exception", "ns1");

                /**
                 * field for Exception
                 */
                protected Exception localException;

                /*  This tracker boolean wil be used to detect whether the user called the set method
                *   for this attribute. It will be used to determine whether to include this field
                *   in the serialized XML
                */
                protected boolean localExceptionTracker = false;

                private static java.lang.String generatePrefix(
                        java.lang.String namespace)
                {
                        if (namespace.equals("http://axisversion.sample"))
                        {
                                return "ns1";
                        }

                        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                /**
                 * Auto generated getter method
                 *
                 * @return Exception
                 */
                public Exception getException()
                {
                        return localException;
                }

                /**
                 * Auto generated setter method
                 *
                 * @param param Exception
                 */
                public void setException(Exception param)
                {
                        if (param != null)
                        {
                                //update the setting tracker
                                localExceptionTracker = true;
                        }
                        else
                        {
                                localExceptionTracker = true;
                        }

                        this.localException = param;
                }

                /**
                 * isReaderMTOMAware
                 *
                 * @return true if the reader supports MTOM
                 */
                public static boolean isReaderMTOMAware(
                        javax.xml.stream.XMLStreamReader reader)
                {
                        boolean isReaderMTOMAware = false;

                        try
                        {
                                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                                        org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
                        }
                        catch (java.lang.IllegalArgumentException e)
                        {
                                isReaderMTOMAware = false;
                        }

                        return isReaderMTOMAware;
                }

                /**
                 * @param parentQName
                 * @param factory
                 * @return org.apache.axiom.om.OMElement
                 */
                public org.apache.axiom.om.OMElement getOMElement(
                        final javax.xml.namespace.QName parentQName,
                        final org.apache.axiom.om.OMFactory factory)
                        throws org.apache.axis2.databinding.ADBException
                {
                        org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                                MY_QNAME)
                        {
                                public void serialize(
                                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                        throws javax.xml.stream.XMLStreamException
                                {
                                        Exception0.this.serialize(MY_QNAME, factory, xmlWriter);
                                }
                        };

                        return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                                factory, dataSource);
                }

                public void serialize(final javax.xml.namespace.QName parentQName,
                                      final org.apache.axiom.om.OMFactory factory,
                                      org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException,
                        org.apache.axis2.databinding.ADBException
                {
                        java.lang.String prefix = null;
                        java.lang.String namespace = null;

                        prefix = parentQName.getPrefix();
                        namespace = parentQName.getNamespaceURI();

                        if (namespace != null)
                        {
                                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                                if (writerPrefix != null)
                                {
                                        xmlWriter.writeStartElement(namespace,
                                                parentQName.getLocalPart());
                                }
                                else
                                {
                                        if (prefix == null)
                                        {
                                                prefix = generatePrefix(namespace);
                                        }

                                        xmlWriter.writeStartElement(prefix,
                                                parentQName.getLocalPart(), namespace);
                                        xmlWriter.writeNamespace(prefix, namespace);
                                        xmlWriter.setPrefix(prefix, namespace);
                                }
                        }
                        else
                        {
                                xmlWriter.writeStartElement(parentQName.getLocalPart());
                        }

                        if (localExceptionTracker)
                        {
                                if (localException == null)
                                {
                                        java.lang.String namespace2 = "http://axisversion.sample";

                                        if (!namespace2.equals(""))
                                        {
                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                if (prefix2 == null)
                                                {
                                                        prefix2 = generatePrefix(namespace2);

                                                        xmlWriter.writeStartElement(prefix2, "Exception",
                                                                namespace2);
                                                        xmlWriter.writeNamespace(prefix2, namespace2);
                                                        xmlWriter.setPrefix(prefix2, namespace2);
                                                }
                                                else
                                                {
                                                        xmlWriter.writeStartElement(namespace2, "Exception");
                                                }
                                        }
                                        else
                                        {
                                                xmlWriter.writeStartElement("Exception");
                                        }

                                        // write the nil attribute
                                        writeAttribute("xsi",
                                                "http://www.w3.org/2001/XMLSchema-instance", "nil",
                                                "1", xmlWriter);
                                        xmlWriter.writeEndElement();
                                }
                                else
                                {
                                        localException.serialize(new javax.xml.namespace.QName(
                                                "http://axisversion.sample", "Exception"), factory,
                                                xmlWriter);
                                }
                        }

                        xmlWriter.writeEndElement();
                }

                /**
                 * Util method to write an attribute with the ns prefix
                 */
                private void writeAttribute(java.lang.String prefix,
                                            java.lang.String namespace, java.lang.String attName,
                                            java.lang.String attValue,
                                            javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (xmlWriter.getPrefix(namespace) == null)
                        {
                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                        }

                        xmlWriter.writeAttribute(namespace, attName, attValue);
                }

                /**
                 * Util method to write an attribute without the ns prefix
                 */
                private void writeAttribute(java.lang.String namespace,
                                            java.lang.String attName, java.lang.String attValue,
                                            javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (namespace.equals(""))
                        {
                                xmlWriter.writeAttribute(attName, attValue);
                        }
                        else
                        {
                                registerPrefix(xmlWriter, namespace);
                                xmlWriter.writeAttribute(namespace, attName, attValue);
                        }
                }

                /**
                 * Util method to write an attribute without the ns prefix
                 */
                private void writeQNameAttribute(java.lang.String namespace,
                                                 java.lang.String attName, javax.xml.namespace.QName qname,
                                                 javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String attributeNamespace = qname.getNamespaceURI();
                        java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

                        if (attributePrefix == null)
                        {
                                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                        }

                        java.lang.String attributeValue;

                        if (attributePrefix.trim().length() > 0)
                        {
                                attributeValue = attributePrefix + ":" + qname.getLocalPart();
                        }
                        else
                        {
                                attributeValue = qname.getLocalPart();
                        }

                        if (namespace.equals(""))
                        {
                                xmlWriter.writeAttribute(attName, attributeValue);
                        }
                        else
                        {
                                registerPrefix(xmlWriter, namespace);
                                xmlWriter.writeAttribute(namespace, attName, attributeValue);
                        }
                }

                /**
                 * method to handle Qnames
                 */
                private void writeQName(javax.xml.namespace.QName qname,
                                        javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String namespaceURI = qname.getNamespaceURI();

                        if (namespaceURI != null)
                        {
                                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                                if (prefix == null)
                                {
                                        prefix = generatePrefix(namespaceURI);
                                        xmlWriter.writeNamespace(prefix, namespaceURI);
                                        xmlWriter.setPrefix(prefix, namespaceURI);
                                }

                                if (prefix.trim().length() > 0)
                                {
                                        xmlWriter.writeCharacters(prefix + ":" +
                                                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                        qname));
                                }
                                else
                                {
                                        // i.e this is the default namespace
                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                qname));
                                }
                        }
                        else
                        {
                                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                        qname));
                        }
                }

                private void writeQNames(javax.xml.namespace.QName[] qnames,
                                         javax.xml.stream.XMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException
                {
                        if (qnames != null)
                        {
                                // we have to store this data until last moment since it is not possible to write any
                                // namespace data after writing the charactor data
                                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                                java.lang.String namespaceURI = null;
                                java.lang.String prefix = null;

                                for (int i = 0; i < qnames.length; i++)
                                {
                                        if (i > 0)
                                        {
                                                stringToWrite.append(" ");
                                        }

                                        namespaceURI = qnames[i].getNamespaceURI();

                                        if (namespaceURI != null)
                                        {
                                                prefix = xmlWriter.getPrefix(namespaceURI);

                                                if ((prefix == null) || (prefix.length() == 0))
                                                {
                                                        prefix = generatePrefix(namespaceURI);
                                                        xmlWriter.writeNamespace(prefix, namespaceURI);
                                                        xmlWriter.setPrefix(prefix, namespaceURI);
                                                }

                                                if (prefix.trim().length() > 0)
                                                {
                                                        stringToWrite.append(prefix).append(":")
                                                                .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                                        qnames[i]));
                                                }
                                                else
                                                {
                                                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                                qnames[i]));
                                                }
                                        }
                                        else
                                        {
                                                stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                                        qnames[i]));
                                        }
                                }

                                xmlWriter.writeCharacters(stringToWrite.toString());
                        }
                }

                /**
                 * Register a namespace prefix
                 */
                private java.lang.String registerPrefix(
                        javax.xml.stream.XMLStreamWriter xmlWriter,
                        java.lang.String namespace)
                        throws javax.xml.stream.XMLStreamException
                {
                        java.lang.String prefix = xmlWriter.getPrefix(namespace);

                        if (prefix == null)
                        {
                                prefix = generatePrefix(namespace);

                                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null)
                                {
                                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                                }

                                xmlWriter.writeNamespace(prefix, namespace);
                                xmlWriter.setPrefix(prefix, namespace);
                        }

                        return prefix;
                }

                /**
                 * databinding method to get an XML representation of this object
                 */
                public javax.xml.stream.XMLStreamReader getPullParser(
                        javax.xml.namespace.QName qName)
                        throws org.apache.axis2.databinding.ADBException
                {
                        java.util.ArrayList elementList = new java.util.ArrayList();
                        java.util.ArrayList attribList = new java.util.ArrayList();

                        if (localExceptionTracker)
                        {
                                elementList.add(new javax.xml.namespace.QName(
                                        "http://axisversion.sample", "Exception"));

                                elementList.add((localException == null) ? null : localException);
                        }

                        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                                elementList.toArray(), attribList.toArray());
                }

                /**
                 * Factory class that keeps the parse method
                 */
                public static class Factory
                {
                        /**
                         * static method to create the object
                         * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
                         * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
                         * Postcondition: If this object is an element, the reader is positioned at its end element
                         * If this object is a complex type, the reader is positioned at the end element of its outer element
                         */
                        public static Exception0 parse(
                                javax.xml.stream.XMLStreamReader reader)
                                throws java.lang.Exception
                        {
                                Exception0 object = new Exception0();

                                int event;
                                java.lang.String nillableValue = null;
                                java.lang.String prefix = "";
                                java.lang.String namespaceuri = "";

                                try
                                {
                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                                "nil");

                                        if ("true".equals(nillableValue) ||
                                                "1".equals(nillableValue))
                                        {
                                                // Skip the element and report the null value.  It cannot have subelements.
                                                while (!reader.isEndElement())
                                                {
                                                        reader.next();
                                                }

                                                return null;
                                        }

                                        if (reader.getAttributeValue(
                                                "http://www.w3.org/2001/XMLSchema-instance",
                                                "type") != null)
                                        {
                                                java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                                        "type");

                                                if (fullTypeName != null)
                                                {
                                                        java.lang.String nsPrefix = null;

                                                        if (fullTypeName.indexOf(":") > -1)
                                                        {
                                                                nsPrefix = fullTypeName.substring(0,
                                                                        fullTypeName.indexOf(":"));
                                                        }

                                                        nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                                                        java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                                                ":") + 1);

                                                        if (!"Exception".equals(type))
                                                        {
                                                                //find namespace for the prefix
                                                                java.lang.String nsUri = reader.getNamespaceContext()
                                                                        .getNamespaceURI(nsPrefix);

                                                                return (Exception0) ExtensionMapper.getTypeObject(nsUri,
                                                                        type, reader);
                                                        }
                                                }
                                        }

                                        // Note all attributes that were handled. Used to differ normal attributes
                                        // from anyAttributes.
                                        java.util.Vector handledAttributes = new java.util.Vector();

                                        reader.next();

                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        if (reader.isStartElement() &&
                                                new javax.xml.namespace.QName(
                                                        "http://axisversion.sample", "Exception").equals(
                                                        reader.getName()))
                                        {
                                                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                                        "nil");

                                                if ("true".equals(nillableValue) ||
                                                        "1".equals(nillableValue))
                                                {
                                                        object.setException(null);
                                                        reader.next();

                                                        reader.next();
                                                }
                                                else
                                                {
                                                        object.setException(Exception.Factory.parse(reader));

                                                        reader.next();
                                                }
                                        } // End of if for expected property start element

                                        else
                                        {
                                        }

                                        while (!reader.isStartElement() && !reader.isEndElement())
                                        {
                                                reader.next();
                                        }

                                        if (reader.isStartElement())
                                        {
                                                // A start element we are not expecting indicates a trailing invalid property
                                                throw new org.apache.axis2.databinding.ADBException(
                                                        "Unexpected subelement " + reader.getLocalName());
                                        }
                                }
                                catch (javax.xml.stream.XMLStreamException e)
                                {
                                        throw new java.lang.Exception(e);
                                }

                                return object;
                        }
                } //end of factory class
        }
}
