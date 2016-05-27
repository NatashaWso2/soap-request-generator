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
import java.util.List;

public class CarbonSOAPMessage extends DefaultCarbonMessage {
    public CarbonSOAPMessage(List<ByteBuffer> bufferList) {

        for (ByteBuffer buffer : bufferList) {
            this.addMessageBody(buffer);
        }
    }

    public CarbonSOAPMessage() {
    }

    /**
     * Get the SOAP Envelope
     *
     * @return SOAP Envelope
     */
    public SOAPEnvelope getSOAPEnvelope() throws SOAPException, IOException, SAXException {
        ByteBuffer byteBuffer = getMessageBody();
        //String soapEnvelopeStr = new String(byteBuffer.array(), "UTF-8");
        byte[] data = new byte[byteBuffer.remaining()];
        byteBuffer.get(data);
        String token = new String(data);
        SOAPModel soapModel = new SOAPModel();
        soapModel.createSOAPEnvelope(token);
        return soapModel.getSoapEnvelope();
    }

    /**
     * Set the SOAP Envelope to the message body
     *
     * @param soapEnvelope
     */
    public void setSOAPEnvelope(SOAPEnvelope soapEnvelope) {
        String stringMessageBody = soapEnvelope.toString();
        addMessageBody(ByteBuffer.wrap(stringMessageBody.getBytes(Charset.defaultCharset())));
        setEndOfMsgAdded(true);
    }

    /**
     * Set the Header properties
     *
     * @param HTTPTransportHeaders
     */
    public void setHeaderProperties(HTTPTransportHeaders HTTPTransportHeaders) {
        setHeaders(HTTPTransportHeaders.getHeaders());
    }
}
