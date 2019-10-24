package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Parametro;
import com.itsight.signbox.domain.TipoArchivo;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.TipoArchivoRepository;
import com.itsight.signbox.service.ParametroService;
import com.itsight.signbox.service.TipoArchivoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoArchivoServiceImpl extends BaseServiceImpl<TipoArchivoRepository> implements TipoArchivoService {

    public TipoArchivoServiceImpl(TipoArchivoRepository repository) {
        super(repository);
    }


    @Override
    public List<TipoArchivo> obtenerTipoArchivos() {
        return null;
    }

    @Override
    public TipoArchivo obtenerTipoArchivoPorId(Integer id) {
        return null;
    }

    @Override
    public TipoArchivo save(TipoArchivo entity) {
        return null;
    }

    @Override
    public TipoArchivo update(TipoArchivo entity) {
        return null;
    }

    @Override
    public TipoArchivo findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return null;
    }

    @Override
    public TipoArchivo findOneWithFT(Integer id) {
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
    public List<TipoArchivo> findAll() {
        return null;
    }

    @Override
    public List<TipoArchivo> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<TipoArchivo> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<TipoArchivo> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<TipoArchivo> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<TipoArchivo> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<TipoArchivo> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<TipoArchivo> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(TipoArchivo entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(TipoArchivo entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
