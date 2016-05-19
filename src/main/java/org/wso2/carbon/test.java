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
import org.wso2.carbon.soap.impl.SOAPBody;
import org.wso2.carbon.soap.impl.SOAPFactory;

public class test {


    public static void main(String[] args) throws Exception {

        SOAPFactory soapFactory = new SOAPFactory();
        soapFactory.createSOAPEnvelope();
        soapFactory.createSOAPHeader(soapFactory.createNode("<ns1:header xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:header>"));


        //ONE way of adding a node
          /*  SOAPBody soapBody = soapFactory.createSOAPBody();

            Node node = soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>");
            soapBody.setPayload(node);*/
        ///////////

        // ANOTHER way of adding a node
        soapFactory.createSOAPBody(soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>"));
     //   System.out.println(soapFactory.generateSOAPEnvelope().asString());

        /////////
     //   soapFactory.getSoapEnvelope().getSoapHeader().deletePayload();

        //Another way of adding a node
           /* soapFactory.createSOAPBody();
            soapFactory.getSoapEnvelope().getSoapBody().setPayload(soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>"));

            soapFactory.createSOAPBody(soapFactory.getSoapEnvelope().getSoapBody().getPayload());*/
        ////////

        //Changing the value of a node
            /*soapFactory.getSOAPBody().getPayload().getFirstChild().getFirstChild().setNodeValue("Hello");
            System.out.println();*/


       // soapFactory.createSOAPHeader();

       /* System.out.println(soapFactory.getSoapEnvelope().getSoapBody().asString());*/
        System.out.println(" -- ENVELOPE 11111111111111111111111111--");


        System.out.println(soapFactory.generateSOAPEnvelope().asString());

       /* System.out.println("**** After deleting a node **");
        soapFactory.getSoapEnvelope().getSoapBody().deletePayload();
        System.out.println(soapFactory.generateSOAPEnvelope().asString());*/

          //  System.out.println(soapFactory.getSoapEnvelope().getSoapBody().asString());

          /*  System.out.println("Add node again----------");
            soapFactory.createSOAPBody(soapFactory.createNode("<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Your</TestPart></ns1:hello>"));
            System.out.println(" -- ENVELOPE 2222222222222222222222--");
            System.out.println(soapFactory.generateSOAPEnvelope().asString());
*/
        //Node can be deleted after creating the soap envelope


        //  System.out.println(soapFactory.getSOAPBody().asString());

        //  System.out.println(soapFactory.generateSOAPEnvelope().asString());


        //Editing the node information
        // System.out.println(soapFactory.getSOAPBody().getPayload().getFirstChild());


        System.out.println();
        System.out.println();


    }


}
