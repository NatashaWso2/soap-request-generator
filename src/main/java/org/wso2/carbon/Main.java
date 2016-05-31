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
import org.wso2.carbon.soap.impl.SOAPModel;

public class Main {

    public static void main(String[] args) throws Exception {

        //Create a instance of the SOAP Factory
        SOAPFactory soapFactory = new SOAPFactory();
        SOAPModel soapModel = soapFactory.getSoapModel();

        //Create the SOAP Envelope
        soapModel.createSOAPEnvelope();

        // ------- SOAP HEADER ----------
        //Creating the SOAP Header and Adding header blocks/nodes to the header can be done in many ways.
        Node headerBlock1 = soapModel.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER11</TestPart></ns1:hello>");
        Node headerBlock2 = soapModel.createNode("<ns2:h xmlns:ns2='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER22</TestPart></ns2:h>");

        NodeList headers = new NodeList();
        headers.addNode(headerBlock1);
        headers.addNode(headerBlock2);

        //1
        soapModel.createSOAPHeader();
        soapModel.getSoapEnvelope().getSoapHeader().setHeader(headerBlock1);
        soapModel.getSoapEnvelope().getSoapHeader().setHeader(headerBlock2);

        //2
        /*soapModel.getSoapEnvelope().getSoapHeader().setHeaders(headers);*/

        //3
       /* soapModel.createSOAPHeader(headers); */

        // Deleting the header blocks
        // soapModel.getSoapEnvelope().getSoapHeader().deleteHeaders(1);
        //soapModel.getSoapEnvelope().getSoapHeader().deleteHeaders(headerBlock1);

        //Get header block items
        //System.out.println(soapModel.getSoapEnvelope().getSoapHeader().getAllHeaders().item(0));


        // ------- SOAP BODY ------------------------------------------------------------------------------------------------------------
        //Creating the SOAP Body and Adding the payloads  to the header can be done in many ways.

        //1
        //soapModel.createSOAPBody(); //Creating the empty body

        //2
        soapModel.createSOAPBody(soapModel.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>"));

        //3
        /*SOAPBody soapBody =  soapModel.createSOAPBody();
        Node node = soapModel.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>");
        soapBody.setPayload(node);*/

        //4
      /*  soapModel.createSOAPBody();
        soapModel.getSoapEnvelope().getSoapBody().setPayload(soapModel.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>"));
        soapModel.createSOAPBody(soapModel.getSoapEnvelope().getSoapBody().getPayload());*/

        //Creating the soap envelope when a string message body is given ----------------------
       /* soapModel.createSOAPEnvelope("<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><soapenv:Header><ns1:hello xmlns:ns1=\"http://ode/bpel/unit-test.wsdl\"><TestPart>HEADER11</TestPart></ns1:hello><ns2:h xmlns:ns2=\"http://ode/bpel/unit-test.wsdl\"><TestPart>HEADER22</TestPart></ns2:h></soapenv:Header><soapenv:Body><ns1:hello xmlns:ns1=\"http://ode/bpel/unit-test.wsdl\"><TestPart>Mellow</TestPart></ns1:hello></soapenv:Body></soapenv:Envelope>\n");
        System.out.println(soapModel.getSoapEnvelope().toString());*/

        //Deleting the payload
        // soapModel.getSoapEnvelope().getSoapBody().deletePayload();

        //Changing the value of a node
        //soapModel.getSoapEnvelope().getSoapBody().getPayload().getFirstChild().getFirstChild().setNodeValue("Natasha");


        System.out.println(soapModel.generateSOAPEnvelope().toString());


        SOAPModel soapModel2 = soapFactory.getSoapModel("soap12");

        //Create the SOAP Envelope
        soapModel2.createSOAPEnvelope();

        // ------- SOAP HEADER ----------
        //Creating the SOAP Header and Adding header blocks/nodes to the header can be done in many ways.
        Node headerBlock = soapModel2.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER11</TestPart></ns1:hello>");

        NodeList headers1 = new NodeList();
        headers.addNode(headerBlock);


        //1
        soapModel2.createSOAPHeader();
        soapModel2.getSoapEnvelope().getSoapHeader().setHeader(headerBlock);
        System.out.println("2 --- "+soapModel2.generateSOAPEnvelope().toString());


        //Header properties
      /*  HTTPTransportHeaders headerProperties = new HTTPTransportHeaders();
        headerProperties.addHeader("abc", "1");
        headerProperties.addSOAPAction("method1");
        Map map = headerProperties.getHeaders();
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            String value = (String) map.get(key);

            System.out.println(key + " " + value);
        }*/

        /*String content = "It's easy to convert ByteBuffer to String in Java";
        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes("UTF-8")); // Now let's convert this ByteBuffer to String String converted = new String(buffer.array(), "UTF-8"); System.out.println("Original : " + content); System.out.println("Converted : " + converted);
        String converted = new String(buffer.array(), "UTF-8");
        System.out.println("Original : " + content);
        System.out.println("Converted : " + converted);*/


    }
}
