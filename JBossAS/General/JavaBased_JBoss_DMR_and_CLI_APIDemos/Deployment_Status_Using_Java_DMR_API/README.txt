This program demonstrates how we can check the application status using DMR APIs in domain mode.

Same code can be altered to monitor the application status deployed in standalone mode of server by making the following changes:


#############################
 for Standalone mode
#############################

          try {
                  ModelNode op = new ModelNode(); 
                  op.get("operation").set("read-attribute"); 
                  op.get("name").set("status");

                  ModelNode address = op.get("address"); 
                  address.add("deployment", applicationName); 
                  op.get("operations").set(true);

                  System.out.println("\n\n\t client = "+client); 
                  ModelNode appStatus = client.execute(op); 
                  String status = appStatus.get("result").toString();
                  if(!status.equals("undefined"))
                    {
                       System.out.println("\n\n\tApplication "+applicationName+" Status : "+ status);
                    }
                  else
                    {
                       System.out.println("\n\n\tSeems that Application "+applicationName+" is NOT deployed. Status :  "+status);                       
                    }        
                  if (client != null ) client.close();
               }
           catch (Exception e) {
                  System.out.println ("Exception: " + e.getMessage());
                  e.printStackTrace();
               }
=============================
