package interceptors;
public class InterceptorTwo {
    @javax.interceptor.AroundInvoke
    private Object iAmAround(final javax.interceptor.InvocationContext invocationContext) throws Exception {
        System.out.println("\n\t[InterceptorTwo] iAmAround(final InvocationContext invocationContext) invoked");
        return this.getClass().getName() + " " + invocationContext.proceed();
    }
}
