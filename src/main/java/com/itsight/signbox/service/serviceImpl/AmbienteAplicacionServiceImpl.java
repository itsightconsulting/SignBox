package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.AmbienteAplicacion;
import com.itsight.signbox.domain.Ambientes;
import com.itsight.signbox.domain.Certificados;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.AmbienteAplicacionRepository;
import com.itsight.signbox.service.AmbienteAplicacionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static com.itsight.util.Enums.ResponseCode.EMPTY_RESPONSE;

@Service
@Transactional
public class AmbienteAplicacionServiceImpl extends BaseServiceImpl<AmbienteAplicacionRepository> implements AmbienteAplicacionService {


    private EntityManager entityManager;
    public AmbienteAplicacionServiceImpl(AmbienteAplicacionRepository repository
    ,EntityManager entityManager) {
        super(repository);
        this.entityManager = entityManager;
    }

    @Override
    public AmbienteAplicacion save(AmbienteAplicacion entity) {
        return repository.save(entity);
    }

    @Override
    public AmbienteAplicacion update(AmbienteAplicacion entity) {

        return repository.saveAndFlush(entity);
    }

    @Override
    public AmbienteAplicacion findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return repository.findById(id).orElseThrow(()-> new NotFoundValidationException(EMPTY_RESPONSE.get()));
    }

    @Override
    public AmbienteAplicacion findOneWithFT(Integer id) {
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
    public List<AmbienteAplicacion> findAll() {
        return null;
    }

    @Override
    public List<AmbienteAplicacion> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<AmbienteAplicacion> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<AmbienteAplicacion> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<AmbienteAplicacion> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<AmbienteAplicacion> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<AmbienteAplicacion> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<AmbienteAplicacion> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(AmbienteAplicacion entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(AmbienteAplicacion entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }

    @Override
    public List<Ambientes> obtenerAmbientesPorAplicacion(Integer idAplicacion) {
        //return repository.findAll().stream().filter(c -> c.);

        TypedQuery<Ambientes> query
                //= entityManager.createQuery("SELECT d FROM AmbienteAplicacion e, Ambientes d WHERE e.idAmbiente = d", Ambientes.class);
         = entityManager.createQuery("SELECT d FROM AmbienteAplicacion e, Ambientes d WHERE e.idAmbiente = d and e.idAplicacion = " + idAplicacion, Ambientes.class);
        //SELECT DISTINCT e FROM Employee e INNER JOIN e.tasks t where t.supervisor='Denise'
        List<Ambientes> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public AmbienteAplicacion obtenerPorAmbiente(Integer idAmbiente) {
        AmbienteAplicacion query
                //= entityManager.createQuery("SELECT d FROM AmbienteAplicacion e, Ambientes d WHERE e.idAmbiente = d", Ambientes.class);
                = (AmbienteAplicacion) entityManager.createQuery("SELECT d FROM AmbienteAplicacion d WHERE d.idAmbiente = " + idAmbiente).getSingleResult();

        AmbienteAplicacion resultList = query;

        return resultList;
    }

    @Override
    public List<AmbienteAplicacion> obtenerAmbienteAplicacion(Integer idAplicacion, Integer idAmbiente) {

        TypedQuery<AmbienteAplicacion> query
                //= entityManager.createQuery("SELECT d FROM AmbienteAplicacion e, Ambientes d WHERE e.idAmbiente = d", Ambientes.class);
                = entityManager.createQuery("SELECT d FROM AmbienteAplicacion d WHERE d.idAplicacion = " + idAplicacion + " and d.idAmbiente = " + idAmbiente, AmbienteAplicacion.class);

        List<AmbienteAplicacion> resultList = query.getResultList();

        return resultList;
    }


}
