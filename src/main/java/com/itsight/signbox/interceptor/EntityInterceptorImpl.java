package com.itsight.signbox.interceptor;

import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.base.MiniAuditingEntity;
import com.itsight.signbox.interceptor.EntityInterceptor;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Component
public class EntityInterceptorImpl extends EmptyInterceptor implements EntityInterceptor {


    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof AuditingEntity) {

            for (int i = 0; i < propertyNames.length; i++) {
                String propertyName = propertyNames[i];

                if (propertyName.equals("creadoPor")) {
                    Optional<Authentication> optSc =  Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
                    if(optSc.isPresent()){
                        state[i] = SecurityContextHolder.getContext().getAuthentication().getName();
                    }else{
                        state[i] = "InitialSeeder";
                    }
                } else if (propertyName.equals("fechaCreacion")) {
                    state[i] = currentTime();
                }
            }
        }
        return true; //super.onSave(entity, id, state, propertyNames, types);
    }

    private Date currentTime() {

        return new Timestamp(System.currentTimeMillis());

   }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof AuditingEntity || entity instanceof MiniAuditingEntity) {
            for (int i = 0; i < propertyNames.length; i++) {
                String propertyName = propertyNames[i];

                if (propertyName.equals("modificadoPor")) {
                    currentState[i] = SecurityContextHolder.getContext().getAuthentication().getName();
                } else if (propertyName.equals("fechaModificacion")) {
                    currentState[i] = currentTime();
                }
            }
        }
        return true;
    }
}