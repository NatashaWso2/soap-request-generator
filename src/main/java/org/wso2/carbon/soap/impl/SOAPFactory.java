/**
 *  Copyright (c) 2015 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.soap.impl;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.wso2.carbon.soap.constants.Constants;
import org.wso2.carbon.soap.constants.SOAP11Constants;
import org.wso2.carbon.soap.constants.SOAP12Constants;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class SOAPFactory extends ElementImpl {

    private SOAPEnvelope envelope;
    private DocumentBuilder docBuilder;
    private Document doc;


    public SOAPFactory() throws Exception {
        envelope = new SOAPEnvelope();
        docBuilder = createDocumentBuilder();
        doc = docBuilder.newDocument();
    }

    private DocumentBuilder createDocumentBuilder() throws SOAPException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new SOAPException("Configuration Error", pce);
        }
        docFactory.setNamespaceAware(true);

        return docBuilder;
    }

    //Create the SOAP Envelope Element
    public SOAPEnvelope createSOAPEnvelope() throws SOAPException {

        String soapVersion = "soap11";
        String namespaceURI = null;
        String encodingNSURI = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            encodingNSURI = SOAP11Constants.SOAP_ENCODING_NAMESPACE_URI;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            encodingNSURI = SOAP12Constants.SOAP_ENCODING_NAMESPACE_URI;
        }
        Element rootElement = doc.createElementNS(namespaceURI,
                Constants.SOAP_ENVELOPE);
        rootElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

        Attr encodingStyleAttr = doc.createAttributeNS(namespaceURI, Constants.ENCODING_STYLE);
        encodingStyleAttr.setValue(encodingNSURI);
        encodingStyleAttr.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);
        rootElement.setAttributeNode(encodingStyleAttr);

        envelope.setSoapEnvelopeElement(rootElement);


        return envelope;
    }

    //Generate the soap envelope
    public SOAPEnvelope generateSOAPEnvelope() throws SOAPException {
        envelope = getSoapEnvelope();

        if (envelope != null) {
            if (envelope.getSoapHeader() == null) {
                createSOAPHeader();
            }
            if (envelope.getSoapBody() == null) {
                createSOAPBody();
            }
        } else {
            createSOAPEnvelope();
            createSOAPHeader();
            createSOAPBody();

        }

        return envelope;
    }

    public Node createNode(String payload) throws SOAPException {
        Node fragmentNode = null;
        try {
            fragmentNode = docBuilder.parse(new InputSource(new StringReader(payload)))
                    .getDocumentElement();
        } catch (SAXException e) {
            throw new SOAPException("Error with the XML Parser", e);
        } catch (IOException e) {
            throw new SOAPException("An I/O operation has been failed or interrupted", e);
        }
        fragmentNode = doc.importNode(fragmentNode, true);
        return fragmentNode;

    }


    public SOAPBody createSOAPBody() throws SOAPException {

        String soapVersion = "soap11";
        String namespaceURI = null;
        SOAPBody soapBody = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        }
        //Creates the main SOAP Body Element
        if (envelope != null) {
            if (envelope.getSoapBody() == null) {

                Element bodyElement = doc.createElementNS(namespaceURI, Constants.BODY);
                bodyElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

                soapBody = new SOAPBody(bodyElement);
                envelope.setSoapBody(soapBody);
            } else {
                soapBody = envelope.getSoapBody();
                envelope.setSoapBody(soapBody);
            }
        } else {
            throw new SOAPException("No SOAP Envelope created!!!");
        }

        return soapBody;

    }

    public SOAPBody createSOAPBody(Node payload) throws SOAPException {

        String soapVersion = "soap11";
        String namespaceURI = null;
        SOAPBody soapBody = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        }
        //Creates the main SOAP Body Element
        if (envelope != null) {
            if (envelope.getSoapBody() == null) {

                Element bodyElement = doc.createElementNS(namespaceURI, Constants.BODY);
                bodyElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

                //soapBody.setSoapBodyElement(bodyElement);
                soapBody = new SOAPBody(bodyElement);
                soapBody.setPayload(payload);

                bodyElement.appendChild(payload);
                envelope.setSoapBody(soapBody);
            } else {
                soapBody = envelope.getSoapBody();
                soapBody.setPayload(payload);

                envelope.setSoapBody(soapBody);
            }
        } else {
            throw new SOAPException("No SOAP Envelope created!!!");
        }
        return soapBody;

    }

    public SOAPEnvelope getSoapEnvelope() {
        return envelope;
    }


    //Header
    public SOAPHeader createSOAPHeader() throws SOAPException {

        String soapVersion = "soap11";
        String namespaceURI = null;
        SOAPHeader soapHeader = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        }
        //Creates the main SOAP Body Element
        if (envelope != null) {
            if (envelope.getSoapHeader() == null) {

                Element headerElement = doc.createElementNS(namespaceURI, Constants.HEADER);
                headerElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

                soapHeader = new SOAPHeader(headerElement);
                envelope.setSoapHeader(soapHeader);
            } else {
                soapHeader = envelope.getSoapHeader();
                envelope.setSoapHeader(soapHeader);
            }
        } else {
            throw new SOAPException("No SOAP Envelope created!!!");
        }

        return soapHeader;

    }

    public SOAPHeader createSOAPHeader(Node header) throws SOAPException {

        String soapVersion = "soap11";
        String namespaceURI = null;
        SOAPHeader soapHeader = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        }
        //Creates the main SOAP Body Element
        if (envelope != null) {
            if (envelope.getSoapHeader() == null) {

                Element headerElement = doc.createElementNS(namespaceURI, Constants.HEADER);
                headerElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

                //soapBody.setSoapBodyElement(headerElement);
                soapHeader = new SOAPHeader(headerElement);
                soapHeader.setHeader(header);

                headerElement.appendChild(header);
                envelope.setSoapHeader(soapHeader);
            } else {
                soapHeader = envelope.getSoapHeader();
                soapHeader.setHeader(header);

                envelope.setSoapHeader(soapHeader);
            }
        } else {
            throw new SOAPException("No SOAP Envelope created!!!");
        }
        return soapHeader;

    }

    public SOAPHeader createSOAPHeader(NodeList headers) throws SOAPException {

        String soapVersion = "soap11";
        String namespaceURI = null;
        SOAPHeader soapHeader = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        }
        //Creates the main SOAP Body Element
        if (envelope != null) {
            if (envelope.getSoapHeader() == null) {

                Element headerElement = doc.createElementNS(namespaceURI, Constants.HEADER);
                headerElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

                //soapBody.setSoapBodyElement(headerElement);
                soapHeader = new SOAPHeader(headerElement);
                soapHeader.setHeaders(headers);
                for (int i = 0; i < headers.getLength(); i++) {
                    Node n = headers.item(i);
                    headerElement.appendChild(n);
                }

                envelope.setSoapHeader(soapHeader);
            } else {
                soapHeader = envelope.getSoapHeader();
                soapHeader.setHeaders(headers);

                envelope.setSoapHeader(soapHeader);
            }
        } else {
            throw new SOAPException("No SOAP Envelope created!!!");
        }
        return soapHeader;

    }


}
