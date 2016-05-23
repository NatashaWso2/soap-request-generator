/**
 * Copyright (c) 2015 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.carbon;

import org.w3c.dom.Node;
import org.wso2.carbon.soap.impl.NodeList;
import org.wso2.carbon.soap.impl.SOAPFactory;

public class Main {

    public static void main(String[] args) throws Exception {

        //Create a instance of the SOAP Factory
        SOAPFactory soapFactory = new SOAPFactory();

        //Create the SOAP Envelope
        soapFactory.createSOAPEnvelope();

        // ------- SOAP HEADER ----------
        //Creating the SOAP Header and Adding header blocks/nodes to the header can be done in many ways.
        Node headerBlock1 = soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER11</TestPart></ns1:hello>");
        Node headerBlock2 = soapFactory.createNode("<ns2:h xmlns:ns2='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER22</TestPart></ns2:h>");

        NodeList headers = new NodeList();
        headers.addNode(headerBlock1);
        headers.addNode(headerBlock2);

        //1
        soapFactory.createSOAPHeader();
        soapFactory.getSoapEnvelope().getSoapHeader().setHeader(headerBlock1);
        soapFactory.getSoapEnvelope().getSoapHeader().setHeader(headerBlock2);

        //2
        /*soapFactory.getSoapEnvelope().getSoapHeader().setHeaders(headers);*/

        //3
       /* soapFactory.createSOAPHeader(headers); */

        // Deleting the header blocks
        // soapFactory.getSoapEnvelope().getSoapHeader().deleteHeaders(1);
        //soapFactory.getSoapEnvelope().getSoapHeader().deleteHeaders(headerBlock1);

        //Get header block items
        //System.out.println(soapFactory.getSoapEnvelope().getSoapHeader().getAllHeaders().item(0));


        // ------- SOAP BODY ------------------------------------------------------------------------------------------------------------
        //Creating the SOAP Body and Adding the payloads  to the header can be done in many ways.

        //1
        //soapFactory.createSOAPBody(); //Creating the empty body

        //2
        soapFactory.createSOAPBody(soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>"));

        //3
        /*SOAPBody soapBody =  soapFactory.createSOAPBody();
        Node node = soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>");
        soapBody.setPayload(node);*/

        //4
      /*  soapFactory.createSOAPBody();
        soapFactory.getSoapEnvelope().getSoapBody().setPayload(soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>"));
        soapFactory.createSOAPBody(soapFactory.getSoapEnvelope().getSoapBody().getPayload());*/

        //Deleting the payload
        // soapFactory.getSoapEnvelope().getSoapBody().deletePayload();

        //Changing the value of a node
        //soapFactory.getSoapEnvelope().getSoapBody().getPayload().getFirstChild().getFirstChild().setNodeValue("Natasha");


        System.out.println(soapFactory.generateSOAPEnvelope().asString());
    }
}
