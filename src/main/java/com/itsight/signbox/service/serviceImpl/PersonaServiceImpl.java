package com.itsight.signbox.service.serviceImpl;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.PersonaRepository;
import com.itsight.signbox.service.PersonaService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.itsight.util.Enums.ResponseCode.EMPTY_RESPONSE;

@Service
@Transactional
public class PersonaServiceImpl extends BaseServiceImpl<PersonaRepository> implements PersonaService {

    public PersonaServiceImpl(PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Persona save(Persona entity) {
        return repository.save(entity);
    }

    @Override
    public Persona update(Persona entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Persona findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return repository.findById(id).orElseThrow(()-> new NotFoundValidationException(EMPTY_RESPONSE.get()));
    }

    @Override
    public Persona findOneWithFT(Integer id) {
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
    public List<Persona> findAll() {
        return null;
    }

    @Override
    public List<Persona> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<Persona> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<Persona> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<Persona> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<Persona> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<Persona> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Persona> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(Persona entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(Persona entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }

    @Override
    public void ActualizarContrasenia(int id, String pass, String guid) throws NotFoundValidationException {

        Persona qPersona = findOne(id);

        if (qPersona.getPersonaId() != 0){
            qPersona.setHashContrasenia(new BCryptPasswordEncoder().encode(pass));
            qPersona.setGuid(guid);
            qPersona.setFlagCambio(true);

            update(qPersona);
        }
    }

    @Override
    public void ActualizarFlagCambio(int id) throws NotFoundValidationException {
        Persona qPersona = findOne(id);
        if (qPersona.getPersonaId() != 0){

            qPersona.setFlagCambio(false);

            update(qPersona);
        }
    }
}