package com.middlewaremagic;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

public class HelloWorld_Impl implements HelloWorld{
    public java.lang.String sayHello(String name) {
        System.out.println("\n\t [HelloWorld_Impl] sayHello("+name+") invoked.");
        return "Hello World !!! Mr. "+ name + " at " + new java.util.Date();
    }
}
