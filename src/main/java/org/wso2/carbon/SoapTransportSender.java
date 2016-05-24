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

import org.wso2.carbon.messaging.DefaultCarbonMessage;
import org.wso2.carbon.transport.http.netty.config.SenderConfiguration;
import org.wso2.carbon.transport.http.netty.sender.NettySender;

/**
 * Transport Sender for SOAP using netty
 */
public class SoapTransportSender {

    public static void main(String[] args) {
        SenderConfiguration senderConfiguration = new SenderConfiguration("netty-gw");
        NettySender nettySender = new NettySender(senderConfiguration);

        /*DefaultCarbonMessage carbonMessage = new DefaultCarbonMessage();
        carbonMessage.setProperty(Constants.HOST, "localhost");
        carbonMessage.setProperty(Constants.PORT, 9000);
        carbonMessage.setProperty(Constants.TO,"/services/SimpleStockQuoteService");
        carbonMessage.setProperty(org.wso2.carbon.transport.http.netty.common.Constants.
                IS_DISRUPTOR_ENABLE, "true");
        String payload = "<A>test</A>";

        carbonMessage.setStringMessageBody(payload);
        byte[] errorMessageBytes = payload.getBytes(Charset.defaultCharset());

        Map<String, String> transportHeaders = new HashMap();
        transportHeaders.put(Constants.HTTP_CONNECTION, Constants.KEEP_ALIVE);
        transportHeaders.put(Constants.HTTP_CONTENT_TYPE, Constants.TEXT_XML);
        transportHeaders.put(Constants.HTTP_CONTENT_LENGTH, (String.valueOf(errorMessageBytes.length)));
        transportHeaders.put(Constants.TO, "/services/SimpleStockQuoteService");
        carbonMessage.setHeaders(transportHeaders);
        try {
            nettySender.send(carbonMessage, new CarbonCallback() {
                @Override
                public void done(CarbonMessage carbonMessage) {
                    //System.out.print(carbonMessage.getFullMessageLength());
                    System.out.print(carbonMessage.getMessageBody());
                    List<ByteBuffer> byteBufferList = carbonMessage.getFullMessageBody();

                }
            });
        } catch (MessageProcessorException e) {
            e.printStackTrace();
        }*/
    }

}
