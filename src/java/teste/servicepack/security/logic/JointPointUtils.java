package teste.servicepack.security.logic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * Created by jorgemachado on 30/10/18.
 */
public class JointPointUtils
{
    public static Object getParameter(ProceedingJoinPoint joinPoint,String paramName) throws Throwable {
        final Signature signature = joinPoint.getStaticPart().getSignature();
        if (signature instanceof MethodSignature) {
            final MethodSignature ms = (MethodSignature) signature;
            String[] params = ms.getParameterNames();

            Object[] args = joinPoint.getArgs();
            for (int argIndex = 0; argIndex < args.length; argIndex++)
            {       if(params[argIndex].equals(paramName))
                        return args[argIndex];
            }
        }
        return null;
    }

}
