A Simple demo which shows how to use simple FormBased authentication in WildFly 10.

2. Edit the “standalone.xml” file and create the security-domain as following:

                <security-domain name="form-security-domain" cache-type="default">
                    <authentication>
                        <login-module code="UsersRoles" flag="required">
                            <module-option name="usersProperties" value="${jboss.server.config.dir}/form-users.properties"/>
                            <module-option name="rolesProperties" value="${jboss.server.config.dir}/form-roles.properties"/>
                            <module-option name="unauthenticatedIdentity" value="nobody"/>
                        </login-module>
                    </authentication>
                </security-domain>



3. Also place the following kind of "${jboss.server.config.dir}/form-users.properties" with the user credentials:

admin=admin@123

4. place the following kind of "${jboss.server.config.dir}/form-roles.properties" with the user credentials:

admin=AdminRole


5. Deploy the WebApplication to WildFly as following and then access the URL:

mvn clean install wildfly:deploy


6. Test URL:
http://localhost:8080/FormBasedAuthDemo/
