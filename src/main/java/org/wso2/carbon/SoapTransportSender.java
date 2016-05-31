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
package org.wso2.carbon;

import org.w3c.dom.Node;
import org.wso2.carbon.messaging.Constants;
import org.wso2.carbon.messaging.MessageProcessorException;
import org.wso2.carbon.soap.impl.CallBackResponseImpl;
import org.wso2.carbon.soap.impl.CallbackSOAPMessage;
import org.wso2.carbon.soap.impl.CarbonSOAPMessage;
import org.wso2.carbon.soap.impl.HTTPTransportHeaders;
import org.wso2.carbon.soap.impl.NodeList;
import org.wso2.carbon.soap.impl.SOAPEnvelope;
import org.wso2.carbon.soap.impl.SOAPException;
import org.wso2.carbon.soap.impl.SOAPFactory;
import org.wso2.carbon.soap.impl.SOAPModel;
import org.wso2.carbon.transport.http.netty.config.SenderConfiguration;
import org.wso2.carbon.transport.http.netty.sender.NettySender;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Transport Sender for SOAP using netty
 */
public class SoapTransportSender {

    public static void main(String[] args) throws SOAPException, IOException, SAXException {
        SenderConfiguration senderConfiguration = new SenderConfiguration("netty-gw");
        NettySender nettySender = new NettySender(senderConfiguration);

        // Setting the properties
        CarbonSOAPMessage carbonSOAPMessage = new CarbonSOAPMessage();
        carbonSOAPMessage.setProperty(Constants.HOST, "localhost");
        carbonSOAPMessage.setProperty(Constants.PORT, 9764);
        carbonSOAPMessage.setProperty(Constants.TO, "/services/HelloService");
        carbonSOAPMessage.setProperty(org.wso2.carbon.transport.http.netty.common.Constants.IS_DISRUPTOR_ENABLE, "true");

         //Creating the soap envelope
        SOAPFactory soapFactory = new SOAPFactory();
        SOAPModel soapModel = soapFactory.getSoapModel();
        soapModel.createSOAPEnvelope();
        soapModel.createSOAPBody(soapModel.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Hello</TestPart></ns1:hello>"));
        Node headerBlock1 = soapModel.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER11</TestPart></ns1:hello>");
        Node headerBlock2 = soapModel.createNode("<ns2:h xmlns:ns2='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER22</TestPart></ns2:h>");

        NodeList headers = new NodeList();
        headers.addNode(headerBlock1);
        headers.addNode(headerBlock2);

        soapModel.createSOAPHeader();
        soapModel.getSoapEnvelope().getSoapHeader().setHeader(headerBlock1);
        soapModel.getSoapEnvelope().getSoapHeader().setHeader(headerBlock2);
        SOAPEnvelope soapEnvelope = soapModel.generateSOAPEnvelope();

        // Setting the SOAP Envelope
        carbonSOAPMessage.setSOAPEnvelope(soapEnvelope);

        HTTPTransportHeaders httpTransportHeaders = new HTTPTransportHeaders();
        //User should add these
        httpTransportHeaders.addHeader(Constants.HTTP_CONNECTION, Constants.KEEP_ALIVE);
        httpTransportHeaders.addHeader(Constants.HTTP_HOST, "localhost:9764");
        httpTransportHeaders.addHeader(Constants.HTTP_TRANSFER_ENCODING, "chunked");

        carbonSOAPMessage.setHeaderProperties(httpTransportHeaders);

        try {
            CallBackResponseImpl callBackResponse = new CallBackResponseImpl();
            CallbackSOAPMessage callbackSOAPMessage = new CallbackSOAPMessage(callBackResponse);
            nettySender.send(carbonSOAPMessage, callbackSOAPMessage);
            /*carbonSOAPMessage = callbackSOAPMessage.getResponse();
            System.out.println(carbonSOAPMessage.getSOAPEnvelope().toString());*/


        } catch (MessageProcessorException e) {
            throw new SOAPException("Message Processor Exception " , e);
        }

    }

}
