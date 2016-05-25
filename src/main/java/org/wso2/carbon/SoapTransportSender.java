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
import org.wso2.carbon.messaging.CarbonCallback;
import org.wso2.carbon.messaging.CarbonMessage;
import org.wso2.carbon.messaging.Constants;
import org.wso2.carbon.messaging.DefaultCarbonMessage;
import org.wso2.carbon.messaging.MessageProcessorException;
import org.wso2.carbon.soap.impl.CarbonSOAPMessage;
import org.wso2.carbon.soap.impl.HeaderProperties;
import org.wso2.carbon.soap.impl.NodeList;
import org.wso2.carbon.soap.impl.SOAPEnvelope;
import org.wso2.carbon.soap.impl.SOAPException;
import org.wso2.carbon.soap.impl.SOAPFactory;
import org.wso2.carbon.transport.http.netty.config.SenderConfiguration;
import org.wso2.carbon.transport.http.netty.sender.NettySender;

import java.io.UnsupportedEncodingException;

/**
 * Transport Sender for SOAP using netty
 */
public class SoapTransportSender {

    public static void main(String[] args) throws SOAPException {
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
        soapFactory.createSOAPEnvelope();
        soapFactory.createSOAPBody(soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Hello</TestPart></ns1:hello>"));
        Node headerBlock1 = soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER11</TestPart></ns1:hello>");
        Node headerBlock2 = soapFactory.createNode("<ns2:h xmlns:ns2='http://ode/bpel/unit-test.wsdl'><TestPart>HEADER22</TestPart></ns2:h>");

        NodeList headers = new NodeList();
        headers.addNode(headerBlock1);
        headers.addNode(headerBlock2);

        soapFactory.createSOAPHeader();
        soapFactory.getSoapEnvelope().getSoapHeader().setHeader(headerBlock1);
        soapFactory.getSoapEnvelope().getSoapHeader().setHeader(headerBlock2);
        SOAPEnvelope soapEnvelope = soapFactory.generateSOAPEnvelope();

        // Setting the SOAP Envelope
        carbonSOAPMessage.setSOAPEnvelope(soapEnvelope);

        HeaderProperties headerProperties = new HeaderProperties();
        //User should add these
        headerProperties.addHeader(Constants.HTTP_CONNECTION, Constants.KEEP_ALIVE);
        headerProperties.addHeader(Constants.HTTP_HOST, "localhost:9764");
        headerProperties.addHeader(Constants.HTTP_TRANSFER_ENCODING, "chunked");

        carbonSOAPMessage.setHeaderProperties(headerProperties);

        try {
            nettySender.send(carbonSOAPMessage, new CarbonCallback() {
                @Override
                public void done(CarbonMessage carbonMessage) {

                    System.out.print(" ------ " + carbonMessage.getMessageBody());
                }
            });
        } catch (MessageProcessorException e) {
            throw new SOAPException("Message Processor Exception " , e);
        }

    }

}
