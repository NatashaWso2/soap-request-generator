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


import org.wso2.carbon.messaging.CarbonCallback;
import org.wso2.carbon.messaging.CarbonMessage;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Callback class used in request path to execute in response path.
 * When response arrives callback methods should execute.
 */
public class CallbackSOAPMessage implements CarbonCallback {

    CarbonSOAPMessage response;

    /**
     * Calls in response path to do work for response.
     *
     * @param cMsg CarbonMessage to be processed.
     */
    @Override
    public void done(CarbonMessage cMsg) {

        List<ByteBuffer> bufferList = cMsg.getFullMessageBody();
        response = new CarbonSOAPMessage(bufferList);

        // Get the response as a string
       /* String responseStr = "";
        for (ByteBuffer buffer : bufferList) {
            byte[] bytes;
            if (buffer.hasArray()) {
                bytes = buffer.array();
            } else {
                bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
            }

            responseStr += new String(bytes);


        }
        System.out.println(responseStr);*/

        System.out.println(" - - - - - - - - ");
        try {
            System.out.println(response.getSOAPEnvelope().toString());
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the response from the response path
     *
     * @return CarbonSOAPMessage
     * @throws SOAPException
     * @throws IOException
     * @throws SAXException
     */
    public CarbonSOAPMessage getResponse() throws SOAPException, IOException, SAXException {

        while (true) {
            if (response != null) {

                break;
            }

        }


        return response;
    }

}
