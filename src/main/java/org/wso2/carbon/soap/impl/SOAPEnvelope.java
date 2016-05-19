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

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Creates the SOAP Envelope i.e. the SOAP request payload
 */
public class SOAPEnvelope extends ElementImpl {


    SOAPBody soapBody;
    Element soapEnvelopeElement;
    SOAPHeader soapHeader;

    public SOAPEnvelope() throws SOAPException {

    }

    public Element getSoapEnvelopeElement() {
        return soapEnvelopeElement;
    }

    public void setSoapEnvelopeElement(Element soapEnvelopeElement) {

        this.soapEnvelopeElement = soapEnvelopeElement;
    }

    //SOAP Body
    public SOAPBody getSoapBody() {

        return soapBody;
    }

    public void setSoapBody(SOAPBody soapBody) {
        soapEnvelopeElement.appendChild(soapBody.getSoapBodyElement());
        this.soapBody = soapBody;
    }

    //SOAP Header
    public SOAPHeader getSoapHeader() {

        return soapHeader;
    }

    public void setSoapHeader(SOAPHeader soapHeader) {
        //soapEnvelopeElement.appendChild(soapHeader.getSoapHeaderElement());
        if(soapBody != null){
            soapEnvelopeElement.insertBefore(soapHeader.getSoapHeaderElement(),soapEnvelopeElement.getFirstChild());
        } else {
            soapEnvelopeElement.appendChild(soapHeader.getSoapHeaderElement());
        }
        this.soapHeader = soapHeader;
    }


    /**
     * Creates the documentBuilder object used to create the xml document i.e. the SOAP Envelope
     *
     * @return documentBuilder
     * @throws Exception
     */
    public DocumentBuilder createDocumentBuilder() throws SOAPException {
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

    public String asString() {
        String str = null;
        Transformer serializer = null;
        try {
            serializer = TransformerFactory.newInstance().newTransformer();
            StringWriter stw = new StringWriter();
            serializer.transform(new DOMSource(soapEnvelopeElement), new StreamResult(stw));
            str = stw.toString();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        return str;
    }

}
