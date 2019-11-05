package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Usuarios;
import com.itsight.signbox.domain.pojo.UsuariosPOJO;
import com.itsight.signbox.domain.query.UsuariosQueryDTO;
import com.itsight.signbox.service.UsuarioService;
import com.itsight.signbox.service.UsuariosProcedureInvoker;
import com.itsight.util.Enums;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    private UsuariosProcedureInvoker usuarioProcedureInvoker;

    public UsuarioController(UsuarioService usuarioService, UsuariosProcedureInvoker usuarioProcedureInvoker) {
        this.usuarioService = usuarioService;
        this.usuarioProcedureInvoker = usuarioProcedureInvoker;
    }

    @RequestMapping("/gestion")
    public ModelAndView principal(Model model){
        return new ModelAndView(ViewConstant.MAIN_USUARIOS);
    }

    @GetMapping("")
    public ResponseEntity<List<UsuariosPOJO>> listarTodo(@ModelAttribute @Valid UsuariosQueryDTO UsuariosQueryDTO){

        return new ResponseEntity<List<UsuariosPOJO>>(usuarioProcedureInvoker.getUsuarios(UsuariosQueryDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> listarporId(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        return new ResponseEntity<Usuarios>(usuarioService.findOne(id),HttpStatus.OK) ;
    }

    @GetMapping("/perfiles")
    public ResponseEntity<List<Map<String, String>>> getPerfiles(){

        List<Map<String, String>> listPerfiles = Stream.of(Enums.Perfiles.values()).map(temp -> {
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("id", temp.getId());
            obj.put("nombre", temp.getNombre());
            return obj;
        }).collect(Collectors.toList());

        return new ResponseEntity<List<Map<String, String>>>(listPerfiles, HttpStatus.OK);
    }


    @GetMapping("/modos-acceso")
    public ResponseEntity<List<Map<String, String>>> getModosAcceso(){

        List<Map<String, String>> listModosAcceso = Stream.of(Enums.ModosAcceso.values()).map(temp -> {
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("id", temp.getId());
            obj.put("descripcion", temp.getDescripcion());
            return obj;
        }).collect(Collectors.toList());

        return new ResponseEntity<List<Map<String, String>>>(listModosAcceso, HttpStatus.OK);
    }


    @PostMapping("")
    public Usuarios agregar(@ModelAttribute @Valid Usuarios usuario){
        usuario.setFlagActivo(true);
        return usuarioService.save(usuario);
    }



    @PutMapping("/{id}")
    public @ResponseBody Usuarios actualizar(@ModelAttribute @Valid Usuarios usuario, @PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        Usuarios qUsuario = usuarioService.findOne(id);
        qUsuario.setUsuario(usuario);

        return usuarioService.update(qUsuario);
    }


    @PutMapping("/estado/{id}")
    public @ResponseBody Usuarios actualizarEstado(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        Usuarios usuario = usuarioService.findOne(id);
        usuario.setFlagActivo(!usuario.isFlagActivo());

        return usuarioService.update(usuario);
    }




    @GetMapping("/username/validacion")
    public @ResponseBody
    Map<String, Boolean> existeUsername(@RequestParam String username, @RequestParam Integer modoAccesoId) {
        return Collections.singletonMap("isUsernameValid", usuarioService.isUsernameValid(username,modoAccesoId));
    }



    @GetMapping("/dni/validacion")
    public @ResponseBody
    Map<String, Boolean> existeDni(@RequestParam String dni, @RequestParam Integer userId) {
        return Collections.singletonMap("isDNIValid", usuarioService.isDNIValid(dni, userId));
    }


    @GetMapping("/email/validacion")
    public @ResponseBody
    Map<String, Boolean> existeEmail(@RequestParam String email, @RequestParam Integer userId) {
        return Collections.singletonMap("isEmailValid", usuarioService.isEmailValid(email, userId));
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable(name = "id") Integer id) {
        usuarioService.delete(id);
    }

/*
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> listarporId(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        return new ResponseEntity<Usuarios>(UsuarioService.findOne(id),HttpStatus.OK) ;
    }


    @PostMapping("")
    public Usuarios agregar(@ModelAttribute @Valid Usuarios Usuario){
        Usuario.setFlagActivo(true);
        return UsuarioService.save(Usuario);
    }




    @PutMapping("/estado/{id}")
    public @ResponseBody Usuarios actualizarEstado(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        Usuarios Usuarios = UsuarioService.findOne(id);
        Usuarios.setFlagActivo(!Usuarios.isFlagActivo());

        return UsuarioService.update(Usuarios);
    }


    @GetMapping("/alias/validacion")
    public @ResponseBody
    Map<String, Boolean> existeAlias(@RequestParam String alias) {
        return Collections.singletonMap("aliasExiste", UsuarioService.existsByAlias(alias));
    }
*/

}
