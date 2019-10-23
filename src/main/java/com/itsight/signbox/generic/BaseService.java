package com.itsight.signbox.generic;
import com.itsight.signbox.advice.CustomValidationException;
import com.itsight.signbox.advice.NotFoundValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BaseService<T, V> {

    T save(T entity);

    T update(T entity);

    T findOne(V id) throws NotFoundValidationException, NotFoundValidationException;

    T findOneWithFT(V id);

    void delete(V id);

    List<Integer> findIdsByFlagActivo(boolean flagActivo);

    List<T> findAll();

    List<T> findByNombre(String nombre);

    List<T> findByNombreContainingIgnoreCase(String nombre);

    List<T> findByDescripcionContainingIgnoreCase(String descripcion);

    List<T> findByFlagActivo(boolean flagActivo);

    List<T> findByFlagEliminado(boolean flagEliminado);

    List<T> findByIdsIn(List<V> ids);

    List<T> listarPorFiltro(String comodin, String estado, String fk);

    String registrar(T entity, String wildcard) throws CustomValidationException;

    String actualizar(T entity, String wildcard) throws CustomValidationException, CustomValidationException;

    void actualizarFlagActivoById(V id, boolean flagActivo);


}
