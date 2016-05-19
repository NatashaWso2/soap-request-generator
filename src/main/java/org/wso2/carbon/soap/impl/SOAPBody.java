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
import org.w3c.dom.Node;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Creates the SOAP body of the SOAP Envelope
 */
public class SOAPBody extends ElementImpl {


    Element soapBodyElement;


    public SOAPBody() {
    }

    public SOAPBody(Element soapBodyElement) {
        this.soapBodyElement = soapBodyElement;
    }

    public Element getSoapBodyElement() {

        return soapBodyElement;
    }

    public void setSoapBodyElement(Element soapBodyElement) {
        this.soapBodyElement = soapBodyElement;
    }

    public Node getPayload() {
        return soapBodyElement.getFirstChild();
    }

    public void setPayload(Node payload) {

        soapBodyElement.appendChild(payload);
    }

  /*  public void setPayload(Node[] payload) {

        soapBodyElement.appendChild(payload);
    }*/

    public void deletePayload() {

        soapBodyElement.removeChild(soapBodyElement.getFirstChild());


    }

    public String asString() {
        String str = null;
        Transformer serializer = null;
        try {
            serializer = TransformerFactory.newInstance().newTransformer();
            StringWriter stw = new StringWriter();
            serializer.transform(new DOMSource(soapBodyElement), new StreamResult(stw));
            str = stw.toString();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        return str;
    }


}