package teste.servicepack.security.logic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import teste.domain.UserSession;
import teste.domain.dao.AbstractDao;
import teste.servicepack.security.SecurityContextProvider;
import teste.servicepack.security.logic.Exception.FailRoleException;
import teste.servicepack.security.logic.Exception.NotAuthenticatedException;
import teste.utils.HibernateUtils;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by jorgemachado on 18/10/18.
 */
@Aspect
public class SecurityAspects {

    private static final Logger logger = Logger.getLogger(String.valueOf(SecurityAspects.class));

    @Pointcut("@annotation(Transaction)")
    public void TransactionPointCut(){}

    @Pointcut("@annotation(IsAuthenticated)")
    public void isAuthenticatedPointCut(){}

    @Pointcut("@annotation(hasRole)")
    public void hasRolePointCut(HasRole hasRole){}


    @Pointcut("execution(* *(..))")
    public void executionPointCut(){}


    //Transaction
    @Around("TransactionPointCut() && executionPointCut()")
    public Object transactionAdvise(ProceedingJoinPoint pjp) throws Throwable{
        AbstractDao.beginTransaction();
        try {
            Object obj = pjp.proceed();
            AbstractDao.commitTransaction();
            logger.info("Transaction finished successfully!");
            return obj;
        }catch (Exception e){
            AbstractDao.rollbackTransaction();
            throw e;
        }
    }

    // isAuthenticated
    @Around("isAuthenticatedPointCut() && executionPointCut()")
    public Object isAuthenticatedAdvise(ProceedingJoinPoint pjp) throws Throwable
    {

        logger.info("isAuthenticated");
        String cookie = SecurityContextProvider.getInstance().getSecuritySessionContext().getRequester();
        UserSession session = (UserSession) HibernateUtils.getCurrentSession().load(UserSession.class,cookie);

        if(session.getUser() != null)
            return pjp.proceed();
        throw new NotAuthenticatedException("Access Denied, not authenticated at " + pjp.getSourceLocation().getFileName() + " " + pjp.getSourceLocation().getLine() + " service: " + pjp.getSignature().getName());
    }


    // HasRole
    @Around("hasRolePointCut(hasRole) && executionPointCut()")
    public Object hasRoleAdvise(ProceedingJoinPoint pjp,HasRole hasRole) throws Throwable
    {
        logger.info("hasRole");


        String cookie = SecurityContextProvider.getInstance().getSecuritySessionContext().getRequester();

        UserSession session = (UserSession) HibernateUtils.getCurrentSession().load(UserSession.class,cookie);

        String[] rolesIn = hasRole.role().split(",");
        String[] roles = session.getUser().getRoles().split(",");

        for(String checkRole: rolesIn){
            if(Arrays.asList(roles).contains(checkRole)) {
                return pjp.proceed();
            }
        }
        throw new FailRoleException("Access Denied, does not have role " + hasRole.role() + " at " + pjp.getSourceLocation().getFileName() + " " + pjp.getSourceLocation().getLine() + " service: " + pjp.getSignature().getName());
    }



}
