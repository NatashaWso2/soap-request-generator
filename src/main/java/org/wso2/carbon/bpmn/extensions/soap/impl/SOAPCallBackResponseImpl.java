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

package org.wso2.carbon.bpmn.extensions.soap.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.bpmn.extensions.soap.SOAPCallBackResponse;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Handles the response message received.
 */
public class SOAPCallBackResponseImpl implements SOAPCallBackResponse {
    private static final Logger log = LoggerFactory.getLogger(SOAPCallBackResponseImpl.class);
    @Override
    public void handleResponseReceived(CarbonSOAPMessage carbonSOAPMessage) {

        try {
            log.info(carbonSOAPMessage.getSOAPEnvelope().toString());
            log.info(carbonSOAPMessage.getSOAPEnvelope().getSoapModel().getSoapVersion());
            log.info(carbonSOAPMessage.getSOAPEnvelope().getSoapBody().toString());

        } catch (IOException e) {
            new SOAPException("I/O Exception", e);
        } catch (SAXException e) {
            new SOAPException("Error or warning information from either the XML parser or the application", e);
        } catch (SOAPException e) {
            new SOAPException("SOAP Exception when creating the SOAP Envelope", e);
        }
    }
}
