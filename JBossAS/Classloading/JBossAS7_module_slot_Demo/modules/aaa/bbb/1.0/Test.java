package aaa.bbb;
public class Test
  {
     static {
          System.out.println("\n\n\taaa.bbb.Test class Loaded  [VERSION-1.0]");
      }
     public String sayHello(String name)
      {
          System.out.println("\n\n\taaa.bbb.Test sayHello() called");
          return "Mr. " +name;
      }
  }
