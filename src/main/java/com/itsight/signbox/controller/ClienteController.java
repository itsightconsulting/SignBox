package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.pojo.ArchivoPOJO;
import com.itsight.signbox.domain.query.ArchivoQueryDTO;
import com.itsight.signbox.service.ClienteProcedureInvoker;
import com.itsight.signbox.service.ParametroService;
import com.itsight.signbox.service.PersonaService;
import com.itsight.util.Enums;
import com.jcraft.jsch.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.Vector;

import static com.itsight.util.Enums.ResponseCode.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteProcedureInvoker clienteProcedureInvoker;
    private PersonaService personaService;
    private String keyRemoteHost = "SFTP_SERVIDOR";
    private String keyUsername = "SFTP_USUARIO";
    private String keyPassword = "SFTP_CONTRASENIA";
    private String keyPuerto = "SFTP_PUERTO";
    private String localDir = "src/main/resources/downloaded_files/";
    private ParametroService parametroService;

    public ClienteController(ClienteProcedureInvoker clienteProcedureInvoker, PersonaService personaService,
                             ParametroService parametroService) {
        this.clienteProcedureInvoker = clienteProcedureInvoker;
        this.personaService = personaService;
        this.parametroService = parametroService;
    }

    @RequestMapping("/archivos/consulta")
    public ModelAndView principal(Model model) throws SftpException, JSchException {
        return new ModelAndView(ViewConstant.MAIN_CLIENTES_ARCHIVOS);
    }

    @GetMapping("/archivos")
    public ResponseEntity<List<ArchivoPOJO>>
        getArchivos(@ModelAttribute @Valid ArchivoQueryDTO archivoQueryDTO, HttpSession session) throws NotFoundValidationException {
        Integer id = (Integer) session.getAttribute("id");
        archivoQueryDTO.setDocumentoId(personaService.findOne(id).getDocumento());
        return new ResponseEntity<List<ArchivoPOJO>>(clienteProcedureInvoker.getArchivosByClienteNumDoc(archivoQueryDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/archivos/validar-descarga")
    public @ResponseBody
    String verificaExistencia( @RequestParam String path ,
                      @RequestParam String filename) throws JSchException {

      String fullPath = path + filename;
        if (!fullPath.isEmpty() || fullPath != null) {
            ChannelSftp channelSftp =  channelSftp = setupJsch();
            channelSftp.connect();
            SftpATTRS attrs = null;
           try {
               attrs = channelSftp.stat(fullPath);
               if(attrs != null) {
                   return GENERIC_SUCCESS.get();
               } else {
                   return EX_GENERIC.get();
               }
           } catch (SftpException e) {
               if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                   return NOT_FOUND_MATCHES.get();
               } else {
                   return EX_GENERIC.get();
               }
           } finally {
               channelSftp.exit();
           }
       }
        else{
             return ILLEGAL_ARGUMENT_EXCEPTION.get();
        }
    }




    @RequestMapping(path = "/archivos/descarga", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void  descargarArchivo(HttpServletResponse response, @RequestParam String path ,
                                                 @RequestParam String filename) throws IOException, SftpException, JSchException {
        response.setContentType("application/pdf;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\" "+ filename + "\"");
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect();
        String remoteFile = path + filename;
        try {
           channelSftp.get(remoteFile, response.getOutputStream());
        } catch (SftpException e) {
            System.out.println("No se pudo descargar el fichero");
        }
        channelSftp.exit();
        response.getOutputStream().close();
        response.flushBuffer();

     }

    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();
        String khPath = System.getProperty("user.home") + "/.ssh/known_hosts";
        jsch.setKnownHosts(khPath);

        String username = parametroService.findByNombre(keyUsername).get(0).getValor();
        String remoteHost = parametroService.findByNombre(keyRemoteHost).get(0).getValor();
        String password = parametroService.findByNombre(keyPassword).get(0).getValor();
        String port = parametroService.findByNombre(keyPuerto).get(0).getValor();

        Session jschSession = jsch.getSession(username, remoteHost, Integer.parseInt(port));
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

/*
    public InputStream downloadTest(String path, String filename) throws JSchException, SftpException {


        return is;
    }
*/
}
