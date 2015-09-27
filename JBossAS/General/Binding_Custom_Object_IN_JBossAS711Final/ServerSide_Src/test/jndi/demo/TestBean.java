package test.jndi.demo;
public class TestBean implements java.io.Serializable
  {
      private String name;
      private String value;

      public TestBean(String name,String value)
       {
         this.name=name;
         this.value=value;
         System.out.println("[TestBean] TestBean initialized.");
       }

      public String getName()  
       {
          return name;
       }      
      public String getValue()  
       {
          return value;
       }      
  }
