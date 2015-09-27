Step1).
===========Download the "drools-distribution-5.3.0.Final" from the following link:
http://www.jboss.org/drools/downloads


Step2). 
===========Now copy all the Jars placed inside "drools-distribution-5.3.0.Final/binaries" inside your "WEB-INF/lib" directory.  Or to do this through the ANT build file make sure to place all the JARs inside "${base.dir}/drools_lib" directory of this project.

List Of JARs which Drools distribution provides:
antlr-2.7.7.jar                                    drools-persistence-jpa-5.3.0.Final.jar      jta-1.1.jar
antlr-3.3.jar                                      drools-templates-5.3.0.Final.jar            jxl-2.6.10.jar
antlr-runtime-3.3.jar                              drools-verifier-5.3.0.Final.jar             knowledge-api-5.3.0.Final.jar
bcmail-jdk14-138.jar                               ecj-3.5.1.jar                               log4j-1.2.14.jar
bcprov-jdk14-138.jar                               ejb3-persistence-1.0.2.GA.jar               mvel2-2.1.0.drools4.jar
commons-collections-3.1.jar                        google-collections-1.0.jar                  persistence-api-1.0.jar
dom4j-1.6.1.jar                                    hibernate-annotations-3.4.0.GA.jar          slf4j-api-1.6.0.jar
drools-clips-5.3.0.Final.jar                       hibernate-commons-annotations-3.1.0.GA.jar  stringtemplate-3.2.1.jar
drools-compiler-5.3.0.Final.jar                    hibernate-core-3.3.2.GA.jar                 xml-apis-1.3.04.jar
drools-core-5.3.0.Final.jar                        hibernate-entitymanager-3.4.0.GA.jar        xmlpull-1.1.3.1.jar
drools-decisiontables-5.3.0.Final.jar              itext-2.1.2.jar                             xpp3_min-1.1.4c.jar
droolsjbpm-introduction-docs-5.3.0.Final.jdocbook  javassist-3.12.0.GA.jar                     xstream-1.4.1.jar
drools-jsr94-5.3.0.Final.jar                       jsr94-1.1.jar



Step3).
===========Run the ANT
