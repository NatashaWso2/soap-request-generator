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

import org.w3c.dom.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Creates the SOAP Header of the SOAP Envelope
 */
public class SOAPHeader extends ElementImpl {


    Element soapHeaderElement;


    public SOAPHeader() {
    }

    public SOAPHeader(Element soapHeaderElement) {
        this.soapHeaderElement = soapHeaderElement;
    }

    public Element getSoapHeaderElement() {

        return soapHeaderElement;
    }

    public void setSoapHeaderElement(Element soapHeaderElement) {
        this.soapHeaderElement = soapHeaderElement;
    }

    // NODE
    public Node getHeader(int index) {
        return soapHeaderElement.getFirstChild();
    }

    public void setHeader(Node header) {

        soapHeaderElement.appendChild(header);
    }

    // NODE LIST

    // NODE
    public NodeList getAllHeaders() {
        org.w3c.dom.NodeList list = soapHeaderElement.getChildNodes();
        NodeList nodes = new NodeList();
        for (int i = 0; i < list.getLength(); i++) {
            Node n = list.item(i);
            nodes.addNode(n);
        }
        return nodes;
    }

    public void setHeaders(NodeList headers) {
        for (int i = 0; i < headers.getLength(); i++) {
            Node n = headers.item(i);
            setHeader(n);
        }
    }

    public void deleteHeaders(Node node) {
        soapHeaderElement.removeChild(node);
    }

    public void deleteHeaders(int index) {
        soapHeaderElement.removeChild(soapHeaderElement.getChildNodes().item(index));
    }

    public String asString() {
        String str = null;
        Transformer serializer = null;
        try {
            serializer = TransformerFactory.newInstance().newTransformer();
            StringWriter stw = new StringWriter();
            serializer.transform(new DOMSource(soapHeaderElement), new StreamResult(stw));
            str = stw.toString();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        return str;
    }


}
