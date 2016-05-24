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

import org.wso2.carbon.soap.impl.SOAPException;
import org.wso2.carbon.soap.impl.SOAPFactory;
import org.xml.sax.SAXException;

import java.io.IOException;

public class StrToDom {
    public static void main(String[] args) throws SOAPException, IOException, SAXException {
        SOAPFactory soapFactory = new SOAPFactory();
        soapFactory.createSOAPEnvelope("<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><soapenv:Header><ns1:hello xmlns:ns1=\"http://ode/bpel/unit-test.wsdl\"><TestPart>HEADER11</TestPart></ns1:hello><ns2:h xmlns:ns2=\"http://ode/bpel/unit-test.wsdl\"><TestPart>HEADER22</TestPart></ns2:h></soapenv:Header><soapenv:Body><ns1:hello xmlns:ns1=\"http://ode/bpel/unit-test.wsdl\"><TestPart>Mellow</TestPart></ns1:hello></soapenv:Body></soapenv:Envelope>\n");
        System.out.println(soapFactory.getSoapEnvelope().asString());
    }
}
