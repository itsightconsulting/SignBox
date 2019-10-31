package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.TipoFirma;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.TipoFirmaRepository;
import com.itsight.signbox.service.TipoFirmaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoFirmaServiceImpl extends BaseServiceImpl<TipoFirmaRepository> implements TipoFirmaService {

    public TipoFirmaServiceImpl(TipoFirmaRepository repository) {
        super(repository);
    }


    @Override
    public TipoFirma save(TipoFirma entity) {
        return null;
    }

    @Override
    public TipoFirma update(TipoFirma entity) {
        return null;
    }

    @Override
    public TipoFirma findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return null;
    }

    @Override
    public TipoFirma findOneWithFT(Integer id) {
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
    public List<TipoFirma> findAll() {
        return repository.findAll();
    }

    @Override
    public List<TipoFirma> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<TipoFirma> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<TipoFirma> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<TipoFirma> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<TipoFirma> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<TipoFirma> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<TipoFirma> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(TipoFirma entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(TipoFirma entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
