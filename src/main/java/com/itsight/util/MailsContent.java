package com.itsight.util;

public class MailsContent {

    public static StringBuilder contenidoRecordatorioCredenciales(String username, String originalPassword){
        StringBuilder sb = new StringBuilder(3000);

            sb.append("<div style = 'font-family: Calibri' >");
            sb.append("<p>Estimado cliente,</p>");
            sb.append("<p>Te estamos enviando las credenciales de acceso al portal https://dig.cmactacna.com.pe .No  ");
            sb.append("olvides guardarlos para cualquier consulta. Quedamos a tu disposición.</p>");
            sb.append("<p>Cordialmente, <br/><br/>Caja Tacna</p>");
            sb.append("<p> Los datos de tu cuenta son: <br/> ");
            sb.append("USUARIO: " + username+ " <br/>");
            sb.append("CONTRASEÑA: " + originalPassword + "<br/> </p>");
            sb.append("</div>");
        /*  sb.append("<p> <br/>");
            sb.append("<span style='font-size: 13px;'>"); */
        return sb;
    }

}
