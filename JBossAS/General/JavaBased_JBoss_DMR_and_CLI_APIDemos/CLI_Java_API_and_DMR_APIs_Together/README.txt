Building and executing DMR requests
====================================

In this example we will deploy and undeploy applications using DMR + CLI APIs.
 
In case you want to execute DMR requests yourself, you can use the CommandContext to translate commands and operations to DMR requests. E.g.
 

        ModelNode deployRequest;
        try {
            ModelNode deployRequest = ctx.buildRequest("deploy myapp.ear");
        } catch (CommandFormatException e) {
            // there was a problem building a DMR request
        } 
 
 
Then you can execute DMR requests using the context's controller client, e.g.
 

        ModelControllerClient client = ctx.getModelControllerClient();
        if(client != null) {
            try {
                client.execute(deployRequest);
            } catch (IOException e) {
                // client failed to execute the request
            }
        } else {
            // the client is not available, meaning the connection to the controller
            // has not been established, which means ctx.connectController(...)
            // or ctx.handle("connect") haven't been executed before
        }
