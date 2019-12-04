package com.itsight.signbox.controller;

import com.itsight.signbox.advice.NotFoundValidationException;
import com.itsight.signbox.constants.ViewConstant;
import com.itsight.signbox.domain.Persona;
import com.itsight.signbox.domain.pojo.ArchivoPOJO;
import com.itsight.signbox.domain.pojo.LogsPortalPOJO;
import com.itsight.signbox.domain.query.ArchivoQueryDTO;
import com.itsight.signbox.domain.query.LogsPortalQueryDTO;
import com.itsight.signbox.service.CertificadoProcedureInvoker;
import com.itsight.signbox.service.CertificadoService;
import com.itsight.signbox.service.ClienteProcedureInvoker;
import com.itsight.signbox.service.PersonaService;
import com.jcraft.jsch.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteProcedureInvoker clienteProcedureInvoker;
    private PersonaService personaService;
    private String remoteHost = "fepelab.cen.biz";
    private String username = "conalvias";
    private String password = "TESTCONA#2016";
    private String localDir = "src/main/resources/downloaded_files/";

    public ClienteController(ClienteProcedureInvoker clienteProcedureInvoker, PersonaService personaService) {
        this.clienteProcedureInvoker = clienteProcedureInvoker;
        this.personaService = personaService;
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

    @RequestMapping(path = "/archivos/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public void  exportarReporte(HttpServletResponse response, @RequestParam String path ,
                                                 @RequestParam String filename) throws IOException, SftpException, JSchException {

           response.setContentType("application/pdf;charset=UTF-8");
           response.setHeader("Content-Disposition", "attachment; filename=\" "+ filename + "\"");

      //     File testFile = new File(localDir + filename);
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

       /* try {
             inputStream = downloadTest(path, filename);
        }finally{

           InputStream finalInputStream = inputStream;
            return outputStream -> {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = finalInputStream.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, nRead);
                }
                finalInputStream.close();
            };
        }

*/

     }





    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();
        String khPath = System.getProperty("user.home") + "/.ssh/known_hosts";
        jsch.setKnownHosts(khPath);
        Session jschSession = jsch.getSession(username, remoteHost, 22);
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
