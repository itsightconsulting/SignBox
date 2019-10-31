package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Certificados;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.CertificadoRepository;
import com.itsight.signbox.service.CertificadoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CertificadoServiceImpl extends BaseServiceImpl<CertificadoRepository> implements CertificadoService {
    public CertificadoServiceImpl(CertificadoRepository repository) {
        super(repository);
    }

    @Override
    public Certificados save(Certificados entity) {
        return repository.save(entity);
    }

    @Override
    public Certificados update(Certificados entity) {
        return null;
    }

    @Override
    public Certificados findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return null;
    }

    @Override
    public Certificados findOneWithFT(Integer id) {
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
    public List<Certificados> findAll() {
        return null;
    }

    @Override
    public List<Certificados> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<Certificados> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<Certificados> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<Certificados> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<Certificados> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<Certificados> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Certificados> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(Certificados entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(Certificados entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
