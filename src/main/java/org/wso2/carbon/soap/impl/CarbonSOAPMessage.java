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


import org.wso2.carbon.messaging.DefaultCarbonMessage;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class CarbonSOAPMessage extends DefaultCarbonMessage {
     /**
     *  Set the SOAP Envelope to the message body
     * @param soapEnvelope
     */
    public void setSOAPEnvelope(SOAPEnvelope soapEnvelope) {
        String stringMessageBody = soapEnvelope.asString();
        addMessageBody(ByteBuffer.wrap(stringMessageBody.getBytes(Charset.defaultCharset())));
        setEndOfMsgAdded(true);
    }

    /**
     * Get the SOAP Envelope
     * @return SOAP Envelope
     */
    public SOAPEnvelope getSOAPEnvelope() throws SOAPException, IOException, SAXException {
        ByteBuffer byteBuffer = getMessageBody();
        String soapEnvelopeStr = new String(byteBuffer.array(), "UTF-8");
        SOAPFactory soapFactory = new SOAPFactory();
        soapFactory.createSOAPEnvelope(soapEnvelopeStr);
        return soapFactory.getSoapEnvelope();
    }

    /**
     * Set the Header properties
     */
    public void setHeaderProperties(HeaderProperties headerProperties) {
        setHeaders(headerProperties.getHeaders());
    }



}
