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

import org.wso2.carbon.soap.constants.Constants;
import org.wso2.carbon.soap.constants.SOAP11Constants;
import org.wso2.carbon.soap.constants.SOAP12Constants;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Defines the transport binding rules specific to each soap version
 */
public class HeaderProperties {
    private Map<String, String> headers = new ConcurrentHashMap<String, String>();

    /**
     * Sets the transport binding rules specific to each soap version
     *
     * @return properties which contains transport binding rules
     * @throws Exception
     */
    public HeaderProperties() {
        String soapVersion = "soap11";

        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            headers.put("Content-Type", SOAP11Constants.SOAP11_CONTENT_TYPE);
            headers.put("SOAPAction", ""); //Mandatory
        } else {
            headers.put("Content-Type", SOAP12Constants.SOAP12_CONTENT_TYPE);
            headers.put("SOAPAction", ""); //Optional
        }

    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value){
        headers.put(key, value);
    }

    public void addSOAPAction(String soapAction){
        headers.put("SOAPAction", soapAction);
    }

}
