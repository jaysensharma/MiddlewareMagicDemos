package com.middlewaremagic;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@javax.jws.WebService(
                      serviceName = "HelloWorldService",
                      portName = "HelloWorld",
                      targetNamespace = "http://middlewaremagic.com",
                      wsdlLocation = "WEB-INF/helloworld.wsdl",
                      endpointInterface = "com.middlewaremagic.HelloWorld")
                      
public class HelloWorldImpl implements HelloWorld {

    private static final Logger LOG = Logger.getLogger(HelloWorldImpl.class.getName());

    public java.lang.String sayHello(java.lang.String name) { 
        System.out.println("\n\t [HelloWorld_Impl] sayHello("+name+") invoked.");
        return "Hello World !!! Mr. "+ name + " at " + new java.util.Date();
    }

}
