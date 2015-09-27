package engine;
import test.Message;

import java.io.IOException;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class MyRuleEngine
  {
    /**
     * Creates a Drools KnowledgeBase and adds the given rules file into it
     */
    private static KnowledgeBase createKnowledgeBase( final String rulesFile ) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newClassPathResource( rulesFile ), ResourceType.DRL );
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        return kbase;
    }

    /**
     * Creates a Drools Stateful Knowledge Session
     */
    private static StatefulKnowledgeSession createKnowledgeSession( final KnowledgeBase kbase ) {
        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        return ksession;
    }
    
     public static void executeRuleEngile(String rulesFile,String userInput) throws Exception
       {
          KnowledgeBase kbase = createKnowledgeBase(rulesFile);
          StatefulKnowledgeSession ksession = createKnowledgeSession(kbase);

          Message message = new Message();
          message.setMessage(userInput);
          message.setStatus(Message.HELLO);
          ksession.insert(message);

          ksession.fireAllRules();
          ksession.dispose();
       }  
  }
