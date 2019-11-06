package com.itsight.signbox.controller;

import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.domain.query.LogsPortalQueryDTO;
import com.itsight.signbox.service.LogsPortalService;
import com.itsight.signbox.service.LogsPortalProcedureInvoker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reportes/logs-portal")
public class LogsPortalController {

    private LogsPortalService logsPortalService;

    private LogsPortalProcedureInvoker logsPortalProcedureInvoker;

    public LogsPortalController(LogsPortalService logsPortalService, LogsPortalProcedureInvoker logsPortalProcedureInvoker) {
        this.logsPortalService = logsPortalService;
        this.logsPortalProcedureInvoker = logsPortalProcedureInvoker;
    }

    @RequestMapping("/gestion")
    public ModelAndView principal(Model model){
        return new ModelAndView(ViewConstant.MAIN_LOGS_ADMIN);
    }

    @GetMapping("")
    public ResponseEntity<List<LogsPortalPOJO>> listarTodo(@ModelAttribute @Valid LogsPortalQueryDTO LogsPortalQueryDTO){
        return new ResponseEntity<List<LogsPortalPOJO>>(logsPortalProcedureInvoker.getLogsAdmin(LogsPortalQueryDTO), HttpStatus.OK);
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<LogsAdmin> listarporId(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        return new ResponseEntity<LogsAdmin>(logsAdminService.findOne(id),HttpStatus.OK) ;
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
    public LogsAdmin agregar(@ModelAttribute @Valid LogsAdmin logsAdmin){
        logsAdmin.setFlagActivo(true);
        return logsAdminService.save(logsAdmin);
    }



    @PutMapping("/{id}")
    public @ResponseBody LogsAdmin actualizar(@ModelAttribute @Valid LogsAdmin logsAdmin, @PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        LogsAdmin qUsuario = logsAdminService.findOne(id);
        qUsuario.setUsuario(logsAdmin);

        return logsAdminService.update(qUsuario);
    }


    @PutMapping("/estado/{id}")
    public @ResponseBody LogsAdmin actualizarEstado(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        LogsAdmin logsAdmin = logsAdminService.findOne(id);
        logsAdmin.setFlagActivo(!logsAdmin.isFlagActivo());

        return logsAdminService.update(logsAdmin);
    }




    @GetMapping("/username/validacion")
    public @ResponseBody
    Map<String, Boolean> existeUsername(@RequestParam String username, @RequestParam Integer modoAccesoId) {
        return Collections.singletonMap("isUsernameValid", logsAdminService.isUsernameValid(username,modoAccesoId));
    }



    @GetMapping("/dni/validacion")
    public @ResponseBody
    Map<String, Boolean> existeDni(@RequestParam String dni, @RequestParam Integer userId) {
        return Collections.singletonMap("isDNIValid", logsAdminService.isDNIValid(dni, userId));
    }


    @GetMapping("/email/validacion")
    public @ResponseBody
    Map<String, Boolean> existeEmail(@RequestParam String email, @RequestParam Integer userId) {
        return Collections.singletonMap("isEmailValid", logsAdminService.isEmailValid(email, userId));
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable(name = "id") Integer id) {
        logsAdminService.delete(id);
    }


 */
/*
    @GetMapping("/{id}")
    public ResponseEntity<LogsAdmin> listarporId(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        return new ResponseEntity<LogsAdmin>(UsuarioService.findOne(id),HttpStatus.OK) ;
    }


    @PostMapping("")
    public LogsAdmin agregar(@ModelAttribute @Valid LogsAdmin Usuario){
        Usuario.setFlagActivo(true);
        return UsuarioService.save(Usuario);
    }




    @PutMapping("/estado/{id}")
    public @ResponseBody LogsAdmin actualizarEstado(@PathVariable(name = "id") Integer id) throws NotFoundValidationException {

        LogsAdmin LogsAdmin = UsuarioService.findOne(id);
        LogsAdmin.setFlagActivo(!LogsAdmin.isFlagActivo());

        return UsuarioService.update(LogsAdmin);
    }


    @GetMapping("/alias/validacion")
    public @ResponseBody
    Map<String, Boolean> existeAlias(@RequestParam String alias) {
        return Collections.singletonMap("aliasExiste", UsuarioService.existsByAlias(alias));
    }
*/

}
