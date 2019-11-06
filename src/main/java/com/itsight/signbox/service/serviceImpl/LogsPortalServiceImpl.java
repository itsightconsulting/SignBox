package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.LogsPortal;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.LogsPortalRepository;
import com.itsight.signbox.service.LogsPortalService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LogsPortalServiceImpl extends BaseServiceImpl<LogsPortalRepository> implements LogsPortalService {

    public LogsPortalServiceImpl(LogsPortalRepository repository) {
        super(repository);
    }

    @Override
    public LogsPortal save(LogsPortal entity) {
        return null;
    }

    @Override
    public LogsPortal update(LogsPortal entity) {
        return null;
    }

    @Override
    public LogsPortal findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return null;
    }

    @Override
    public LogsPortal findOneWithFT(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Integer> findIdsByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<LogsPortal> findAll() {
        return null;
    }

    @Override
    public List<LogsPortal> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<LogsPortal> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<LogsPortal> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<LogsPortal> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<LogsPortal> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<LogsPortal> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<LogsPortal> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(LogsPortal entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(LogsPortal entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
