package com.itsight.signbox.service;

import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.domain.Usuarios;
import com.itsight.signbox.generic.BaseServiceImpl;
import com.itsight.signbox.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl extends BaseServiceImpl<UsuarioRepository> implements UsuarioService {

    public UsuarioServiceImpl(UsuarioRepository repository) {
        super(repository);
    }

    @Override
    public Usuarios save(Usuarios entity) {
        return repository.save(entity);
    }

    @Override
    public Usuarios update(Usuarios entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Usuarios findOne(Integer id) throws NotFoundValidationException, NotFoundValidationException {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Usuarios findOneWithFT(Integer id) {
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
    public List<Usuarios> findAll() {
        return null;
    }

    @Override
    public List<Usuarios> findByNombre(String nombre) {
        return null;
    }

    @Override
    public List<Usuarios> findByNombreContainingIgnoreCase(String nombre) {
        return null;
    }

    @Override
    public List<Usuarios> findByDescripcionContainingIgnoreCase(String descripcion) {
        return null;
    }

    @Override
    public List<Usuarios> findByFlagActivo(boolean flagActivo) {
        return null;
    }

    @Override
    public List<Usuarios> findByFlagEliminado(boolean flagEliminado) {
        return null;
    }

    @Override
    public List<Usuarios> findByIdsIn(List<Integer> ids) {
        return null;
    }

    @Override
    public List<Usuarios> listarPorFiltro(String comodin, String estado, String fk) {
        return null;
    }

    @Override
    public String registrar(Usuarios entity, String wildcard) throws CustomValidationException {
        return null;
    }

    @Override
    public String actualizar(Usuarios entity, String wildcard) throws CustomValidationException, CustomValidationException {
        return null;
    }

    @Override
    public void actualizarFlagActivoById(Integer id, boolean flagActivo) {

    }

    @Override
    public boolean isUsernameValid(String username, int modoAcceso) {
        return repository.isUsernameValid(username,modoAcceso);
    }

    @Override
    public boolean isDNIValid(String dni, Integer userId) {
        return repository.isDNIValid(dni, userId);
    }

    @Override
    public boolean isEmailValid(String email, Integer userId) {
        return repository.isEmailValid(email, userId);
    }

}
