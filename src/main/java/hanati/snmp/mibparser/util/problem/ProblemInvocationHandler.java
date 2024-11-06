package hanati.snmp.mibparser.util.problem;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.problem.annotations.ProblemMethod;
import hanati.snmp.mibparser.util.problem.annotations.ProblemProperty;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import hanati.snmp.mibparser.util.TextUtil;

public class ProblemInvocationHandler<T> implements InvocationHandler {
    private ProblemEventHandler problemEventHandler;
    private Map<Method, ProblemInvocationHandler<T>.MethodInvocationHandler> methodInvocationHandlerMap = new HashMap();

    public ProblemInvocationHandler(Class<T> cl, ProblemEventHandler eh) {
        this.problemEventHandler = eh;
        Method[] arr$ = cl.getDeclaredMethods();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Method method = arr$[i$];
            ProblemInvocationHandler<T>.MethodInvocationHandler mih = new MethodInvocationHandler(method);
            this.methodInvocationHandlerMap.put(method, mih);
        }

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ((args == null || args.length == 0) && method.getName().equals("toString")) {
            return this.toString();
        } else {
            ProblemInvocationHandler<T>.MethodInvocationHandler mih = (MethodInvocationHandler)this.methodInvocationHandlerMap.get(method);
            return mih.invoke(args);
        }
    }

    private String getMethodName(Method method) {
        return method.getDeclaringClass().getName() + "." + method.getName();
    }

    class MethodInvocationHandler {
        private Method method;
        private ProblemMethod problemMethod;
        private String messageKey;
        private int locationParameterIndex = -1;

        public MethodInvocationHandler(Method method) {
            this.method = method;
            this.problemMethod = (ProblemMethod)method.getAnnotation(ProblemMethod.class);
            if (this.problemMethod == null) {
                throw new IllegalArgumentException(ProblemInvocationHandler.this.getMethodName(method) + " is missing the " + ProblemMethod.class.getSimpleName() + " annotation");
            } else {
                this.check();
            }
        }

        private void check() {
            Class[] paramTypes = this.method.getParameterTypes();
            Annotation[][] paramAnnotations = this.method.getParameterAnnotations();

            int i;
            for(i = 0; i < paramAnnotations.length; ++i) {
                ProblemProperty ep = this.getErrorParameter(paramAnnotations[i]);
                if (ep != null) {
                    Class paramType = paramTypes[i];
                    String propertyName = ep.value();
                    Method m = MethodUtils.getAccessibleMethod(paramType, "getOne" + TextUtil.ucFirst(propertyName), (Class[])null);
                    if (m == null) {
                        m = MethodUtils.getAccessibleMethod(paramType, "is" + TextUtil.ucFirst(propertyName), (Class[])null);
                    }

                    if (m == null) {
                        m = MethodUtils.getAccessibleMethod(paramType, propertyName, (Class[])null);
                    }

                    if (m == null) {
                        throw new IllegalArgumentException(ProblemInvocationHandler.this.getMethodName(this.method) + ": parameter class does not have a property " + propertyName);
                    }
                }
            }

            for(i = 0; i < paramTypes.length; ++i) {
                Class c = paramTypes[i];
                if (Location.class.equals(c) && this.locationParameterIndex < 0) {
                    this.locationParameterIndex = i;
                }
            }

        }

        public Object invoke(Object[] args) {
            try {
                args = this.processParameters(args);
                Location location = null;
                if (this.locationParameterIndex >= 0) {
                    location = this.getLocation(args);
                    args = this.removeLocation(args);
                }

                ProblemEvent ev = new ProblemEvent(location, this.problemMethod.severity(), this.messageKey, this.problemMethod.message(), args);
                ProblemInvocationHandler.this.problemEventHandler.handle(ev);
                return null;
            } catch (NoSuchMethodException var4) {
                throw new RuntimeException(var4);
            } catch (IllegalAccessException var5) {
                throw new RuntimeException(var5);
            } catch (InvocationTargetException var6) {
                throw new RuntimeException(var6);
            }
        }

        private Location getLocation(Object[] args) {
            return this.locationParameterIndex >= 0 ? (Location)args[this.locationParameterIndex] : null;
        }

        private Object[] removeLocation(Object[] args) {
            Object[] result = args;
            if (this.locationParameterIndex >= 0) {
                result = new Object[args.length - 1];

                int i;
                for(i = 0; i < this.locationParameterIndex; ++i) {
                    result[i] = args[i];
                }

                for(i = this.locationParameterIndex + 1; i < args.length; ++i) {
                    result[i - 1] = args[i];
                }
            }

            return result;
        }

        private Object[] processParameters(Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
            Annotation[][] paramAnnotations = this.method.getParameterAnnotations();

            for(int i = 0; i < paramAnnotations.length; ++i) {
                ProblemProperty ep = this.getErrorParameter(paramAnnotations[i]);
                if (ep != null) {
                    Object arg = args[i];
                    String propertyName = ep.value();
                    Method m = MethodUtils.getAccessibleMethod(arg.getClass(), propertyName, (Class[])null);
                    if (m != null) {
                        args[i] = m.invoke(arg);
                    } else {
                        args[i] = BeanUtils.getProperty(arg, propertyName);
                    }
                }
            }

            return args;
        }

        private ProblemProperty getErrorParameter(Annotation[] paramAnnotations) {
            Annotation[] arr$ = paramAnnotations;
            int len$ = paramAnnotations.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Annotation annotation = arr$[i$];
                if (annotation instanceof ProblemProperty) {
                    return (ProblemProperty)annotation;
                }
            }

            return null;
        }
    }
}
