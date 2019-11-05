package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Estampa;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.EstampaRepository;
import com.itsight.signbox.service.EstampaService;
import javassist.expr.Cast;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.itsight.util.Enums.ResponseCode.EMPTY_RESPONSE;

@Service
@Transactional
public class EstampaServiceImpl  extends BaseServiceImpl<EstampaRepository> implements EstampaService {

    public EstampaServiceImpl(EstampaRepository repository) {
        super(repository);
    }

    @Override
    public boolean validarCodigo(String codigo) {
        Object[] existe = repository.findAll().stream().filter(c -> c.getAlias() == codigo.toUpperCase()).toArray();
        if (existe.length > 0 ){
            return true;
        }

        return false;
    }

    @Override
    public Estampa save(Estampa entity) {
        return repository.save(entity);
    }

    @Override
    public Estampa update(Estampa entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Estampa findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return repository.findById(id).orElseThrow(()-> new NotFoundValidationException(EMPTY_RESPONSE.get()));
    }

    @Override
    public Estampa findOneWithFT(Integer id) {
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
    public List<Estampa> findAll() {
        return null;
    }

    @Override
    public List<Estampa> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<Estampa> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<Estampa> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<Estampa> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<Estampa> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<Estampa> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Estampa> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(Estampa entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(Estampa entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
