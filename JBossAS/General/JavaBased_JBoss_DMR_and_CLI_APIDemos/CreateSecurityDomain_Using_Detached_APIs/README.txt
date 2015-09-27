1). Creating following kind of security-domain using DMR (detached) APIs

                <security-domain name="test-security-domain" cache-type="default">
                    <authentication>
                        <login-module code="UsersRoles" flag="required">
                            <module-option name="usersProperties" value="${jboss.server.config.dir}/test-users.properties"/>
                            <module-option name="rolesProperties" value="${jboss.server.config.dir}/test-roles.properties"/>
                            <module-option name="unauthenticatedIdentity" value="nobody"/>
                        </login-module>
                    </authentication>
                </security-domain>


2). The program output:

     [java] 
     [java] 	 login-module Creation Was = "success"
     [java] 
     [java] 
     [java] ModelNode = {
     [java]     "operation" => "composite",
     [java]     "address" => [],
     [java]     "steps" => [
     [java]         {
     [java]             "operation" => "add",
     [java]             "address" => [
     [java]                 ("subsystem" => "security"),
     [java]                 ("security-domain" => "test-security-domain")
     [java]             ],
     [java]             "cache-type" => "default"
     [java]         },
     [java]         {
     [java]             "operation" => "add",
     [java]             "address" => [
     [java]                 ("subsystem" => "security"),
     [java]                 ("security-domain" => "test-security-domain"),
     [java]                 ("authentication" => "classic")
     [java]             ],
     [java]             "login-modules" => [{
     [java]                 "code" => "UsersRoles",
     [java]                 "flag" => "required",
     [java]                 "module-options" => {
     [java]                     "usersProperties" => "${jboss.server.config.dir}/test-users.properties",
     [java]                     "rolesProperties" => "${jboss.server.config.dir}/test-roles.properties",
     [java]                     "unauthenticatedIdentity" => "nobody"
     [java]                 }
     [java]             }]
     [java]         }
     [java]     ]
     [java] }
     [java] 
     [java] 
     [java] Recieved Response: -{
     [java]     "outcome" => "success",
     [java]     "result" => {
     [java]         "step-1" => {"outcome" => "success"},
     [java]         "step-2" => {"outcome" => "success"}
     [java]     }
     [java] }

all:

BUILD SUCCESSFUL
Total time: 4 seconds



The Code Snippet
=========================
    public static ModelNode createAddSecurityDomain(final String name) {
        ModelNode addDomain = new ModelNode();
        addDomain.get(OP).set("add");
        addDomain.get(OP_ADDR).add("subsystem", "security").add("security-domain", name);
        addDomain.get("cache-type").set("default");

        ModelNode addDomainContent = new ModelNode();
        addDomainContent.get(OP).set("add");
        addDomainContent.get(OP_ADDR).add("subsystem", "security").add("security-domain", name).add("authentication", "classic");
        ModelNode loginModules = addDomainContent.get("login-modules");

        ModelNode remotingModule = new ModelNode();
        remotingModule.get("code").set("UsersRoles");
        remotingModule.get("flag").set("required");
        //remotingModule.get("module").set("epji.tools.auth.jboss");
        ModelNode moduleOptions = remotingModule.get("module-options");
        moduleOptions.get("usersProperties").set("${jboss.server.config.dir}/test-users.properties");
        moduleOptions.get("rolesProperties").set("${jboss.server.config.dir}/test-roles.properties");
        moduleOptions.get("unauthenticatedIdentity").set("nobody");
        loginModules.add(remotingModule);

        return createCompositeOp(Arrays.asList(new ModelNode[] { addDomain, addDomainContent }));
    }

    private static ModelNode createCompositeOp(final Collection<ModelNode> operations) {
        ModelNode composite = new ModelNode();
        composite.get(OP).set(COMPOSITE);
        composite.get(OP_ADDR).setEmptyList();

        ModelNode steps = composite.get(STEPS);
        for (ModelNode current : operations) {
            steps.add(current);
        }
        return composite;
    }
