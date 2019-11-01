package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Aplicaciones;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.AplicacionesRepository;
import com.itsight.signbox.service.AplicacionesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.itsight.util.Enums.ResponseCode.EMPTY_RESPONSE;

@Service
@Transactional
public class AplicacionesServiceImpl extends BaseServiceImpl<AplicacionesRepository> implements AplicacionesService {

    public AplicacionesServiceImpl(AplicacionesRepository repository) {
        super(repository);
    }

    @Override
    public boolean validarCodigo(String codigo) {

        Object[] existe = repository.findAll().stream().filter(c -> c.getCodigo() == codigo && !c.getFlagEliminado()).toArray();
        if (existe.length > 0 ){
            return true;
        }

        return false;
    }

    @Override
    public Aplicaciones save(Aplicaciones entity) {
        return repository.save(entity);
    }

    @Override
    public Aplicaciones update(Aplicaciones entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Aplicaciones findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return repository.findById(id).orElseThrow(()-> new NotFoundValidationException(EMPTY_RESPONSE.get()));
    }

    @Override
    public Aplicaciones findOneWithFT(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Integer> findIdsByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<Aplicaciones> findAll() {
        return null;
    }

    @Override
    public List<Aplicaciones> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<Aplicaciones> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<Aplicaciones> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<Aplicaciones> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<Aplicaciones> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<Aplicaciones> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Aplicaciones> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(Aplicaciones entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(Aplicaciones entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
