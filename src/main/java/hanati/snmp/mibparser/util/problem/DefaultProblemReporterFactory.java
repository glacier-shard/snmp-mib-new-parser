package hanati.snmp.mibparser.util.problem;

import java.lang.reflect.Proxy;

public class DefaultProblemReporterFactory implements ProblemReporterFactory {
    private final ClassLoader classLoader;
    private final ProblemEventHandler problemEventHandler;

    public DefaultProblemReporterFactory(ProblemEventHandler ph) {
        this.classLoader = Thread.currentThread().getContextClassLoader();
        this.problemEventHandler = ph;
    }

    public DefaultProblemReporterFactory(ClassLoader classLoader, ProblemEventHandler ph) {
        this.classLoader = classLoader;
        this.problemEventHandler = ph;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public ProblemEventHandler getProblemEventHandler() {
        return this.problemEventHandler;
    }

    public <T> T create(Class<T> cl) {
        Class[] classArray = new Class[]{cl};
        return (T) Proxy.newProxyInstance(this.classLoader, classArray, new ProblemInvocationHandler(cl, this.problemEventHandler));
    }
}
