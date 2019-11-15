package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Parametro;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.ParametroRepository;
import com.itsight.signbox.service.ParametroService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParametroServiceImpl extends BaseServiceImpl<ParametroRepository> implements ParametroService {

    public ParametroServiceImpl(ParametroRepository repository) {
        super(repository);
    }

    @Override
    public Parametro save(Parametro entity) {
        return null;
    }

    @Override
    public Parametro update(Parametro entity) {

        return repository.saveAndFlush(entity);
    }

    @Override
    public Parametro findOne(Integer id) throws NotFoundValidationException {
        return null;
    }

    @Override
    public Parametro findOneWithFT(Integer id) {
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
    public List<Parametro> findAll() {
        return null;
    }

    @Override
    public List<Parametro> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<Parametro> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<Parametro> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<Parametro> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<Parametro> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<Parametro> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Parametro> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(Parametro entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(Parametro entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }

    @Override
    public List<Parametro> obtenerParametros() {
        return repository.findAll();
    }

    @Override
    public Parametro obtenerParametroPorId(Integer id)
    {
        return repository.findById(id).orElse(null);
    }

}
