package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.AplicacionCertificado;
import com.itsight.signbox.domain.Certificados;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.AplicacionCertificadoRepository;
import com.itsight.signbox.service.AplicacionCertificadoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static com.itsight.util.Enums.ResponseCode.EMPTY_RESPONSE;


@Service
@Transactional
public class AplicacionCertificadoServiceImpl  extends BaseServiceImpl<AplicacionCertificadoRepository> implements AplicacionCertificadoService {

    private EntityManager entityManager;

    public AplicacionCertificadoServiceImpl(AplicacionCertificadoRepository repository, EntityManager entityManager) {
        super(repository);
        this.entityManager = entityManager;
    }

    @Override
    public List<Certificados> obtenerCertificadosPorAplicacion(Integer idAplicacion) {
        TypedQuery<Certificados> query
                //= entityManager.createQuery("SELECT d FROM AmbienteAplicacion e, Ambientes d WHERE e.idAmbiente = d", Ambientes.class);
                = entityManager.createQuery("SELECT d FROM AplicacionCertificado e, Certificados d WHERE e.idCertificado = d.certificadosId and e.idAplicacion = " + idAplicacion, Certificados.class);
        //SELECT DISTINCT e FROM Employee e INNER JOIN e.tasks t where t.supervisor='Denise'
        List<Certificados> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List<AplicacionCertificado> obtenerAplicacionCertificado(Integer idAplicacion, Integer idCertificado) {
        TypedQuery<AplicacionCertificado> query
                //= entityManager.createQuery("SELECT d FROM AmbienteAplicacion e, Ambientes d WHERE e.idAmbiente = d", Ambientes.class);
                = entityManager.createQuery("SELECT d FROM AplicacionCertificado d WHERE d.idAplicacion = " + idAplicacion + " and d.idCertificado = " + idCertificado, AplicacionCertificado.class);

        List<AplicacionCertificado> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public AplicacionCertificado save(AplicacionCertificado entity) {
        return repository.save(entity);
    }

    @Override
    public AplicacionCertificado update(AplicacionCertificado entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public AplicacionCertificado findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return repository.findById(id).orElseThrow(()-> new NotFoundValidationException(EMPTY_RESPONSE.get()));
    }

    @Override
    public AplicacionCertificado findOneWithFT(Integer id) {
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
    public List<AplicacionCertificado> findAll() {
        return null;
    }

    @Override
    public List<AplicacionCertificado> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<AplicacionCertificado> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<AplicacionCertificado> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<AplicacionCertificado> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<AplicacionCertificado> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<AplicacionCertificado> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<AplicacionCertificado> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(AplicacionCertificado entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(AplicacionCertificado entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }
}
