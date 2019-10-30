package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Ambientes;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.AmbientesRepository;
import com.itsight.signbox.service.AmbientesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.itsight.util.Enums.ResponseCode.EMPTY_RESPONSE;

@Service
@Transactional
public class AmbientesServiceImpl extends BaseServiceImpl<AmbientesRepository> implements AmbientesService {

    public AmbientesServiceImpl(AmbientesRepository repository){super(repository);}

    @Override
    public boolean validarCodigo(String codigo) {

        Object[] existe = repository.findAll().stream().filter(c -> c.getPrefijo() == codigo && !c.getFlagEliminado()).toArray();
        if (existe.length > 0 ){
            return true;
        }

        return false;
    }

    @Override
    public Ambientes save(Ambientes entity) {
        return repository.save(entity);
    }

    @Override
    public Ambientes update(Ambientes entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Ambientes findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return repository.findById(id).orElseThrow(()-> new NotFoundValidationException(EMPTY_RESPONSE.get()));
    }

    @Override
    public Ambientes findOneWithFT(Integer id) {
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
    public List<Ambientes> findAll() {
        return null;
    }

    @Override
    public List<Ambientes> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<Ambientes> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<Ambientes> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<Ambientes> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<Ambientes> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<Ambientes> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Ambientes> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(Ambientes entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(Ambientes entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
