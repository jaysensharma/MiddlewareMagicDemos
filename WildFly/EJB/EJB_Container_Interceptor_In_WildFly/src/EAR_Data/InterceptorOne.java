package interceptors;
public class InterceptorOne {

    @javax.interceptor.AroundInvoke
    private Object iAmAround(final javax.interceptor.InvocationContext invocationContext) throws Exception {
        String invokedMethod = invocationContext.getMethod().getName();
        System.out.println("\n\t[InterceptorOne]** iAmAround(final InvocationContext invocationContext) invoked");
        System.out.println("\t[InterceptorOne]** iAmAround, The EJB Method which will be processed is: " + invokedMethod);
        
        long startTime = System.currentTimeMillis();
        String originalParam = (String)invocationContext.getParameters()[0];
        
        // Altering the param value which was passed to the EJB method by the user While during invocation.
        String alteredParam = originalParam+"XYZ";
        invocationContext.setParameters(new String[]{alteredParam});
 
        try {
              return this.getClass().getName() + " " + invocationContext.proceed();
        } catch(Exception ex) {
            throw ex;
        }
        finally {
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("\n\n\t[InterceptorOne]** iAmAround, Total Time taken for method execution : " + invokedMethod + " is " + totalTime + "-ms");
        }
    }
 
}
