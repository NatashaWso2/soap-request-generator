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
package org.wso2.carbon.soap.impl;


import org.wso2.carbon.messaging.CarbonCallback;
import org.wso2.carbon.messaging.CarbonMessage;
import org.wso2.carbon.soap.CallBackResponse;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Callback class used in request path to execute in response path.
 * When response arrives callback methods should execute.
 */
public class CallbackSOAPMessage implements CarbonCallback {

    private CarbonSOAPMessage response;
    private CallBackResponse callBackResponse;

    public CallbackSOAPMessage() {
    }

    public CallbackSOAPMessage(CallBackResponse callBackResponse) {
        this.callBackResponse = callBackResponse;
    }

    /**
     * Calls in response path to do work for response.
     *
     * @param cMsg CarbonMessage to be processed.
     */
    @Override
    public void done(CarbonMessage cMsg) {

        List<ByteBuffer> bufferList = cMsg.getFullMessageBody();
        response = new CarbonSOAPMessage(bufferList);

        //Handle the received response message
        callBackResponse.handleResponseReceived(response);

    }

    /**
     * Gets the response from the response path
     *
     * @return CarbonSOAPMessage
     * @throws SOAPException
     * @throws IOException
     * @throws SAXException
     */
    public CarbonSOAPMessage getResponse() {

        return response;
    }

}
