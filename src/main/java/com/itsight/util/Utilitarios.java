package com.itsight.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.Cookie;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Utilitarios {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    public static String generarRandomPassword(int len){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static String parseFormalUUID(String noParsed) {
        noParsed = noParsed.substring(0, 8).toUpperCase() + "-" +
                noParsed.substring(8, 12).toUpperCase() + "-" +
                noParsed.substring(12, 16).toUpperCase() + "-" +
                noParsed.substring(16, 20).toUpperCase() + "-" +
                noParsed.substring(20).toUpperCase();
        return noParsed;
    }

    public static boolean validateOnlyNumbers(String cadena) {
        char[] array = cadena.toCharArray();
        int i = 0;

        for (i = 0; i < array.length; i++) {
            if (!Character.isDigit(array[i])) {
                i--;
                break;
            }
        }

        if (i == array.length) {
            return true;
        } else {
            return false;
        }
    }

    public static final String encoderPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }


    public static Cookie createCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
        cookie.setHttpOnly(false);//Not access available by js
        cookie.setSecure(false);//Only accessed with https
        return cookie;
    }

    public static final String parseMonth(String month) {
        switch (month) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                month = "0" + month;
                break;
            default:
                break;
        }
        return month;
    }

    public static String nombreMes(int mes) {
        String nombre;
        switch (mes) {
            case 1:
                nombre = "Enero";
                break;
            case 2:
                nombre = "Febrero";
                break;
            case 3:
                nombre = "Marzo";
                break;
            case 4:
                nombre = "Abril";
                break;
            case 5:
                nombre = "Mayo";
                break;
            case 6:
                nombre = "Junio";
                break;
            case 7:
                nombre = "Julio";
                break;
            case 8:
                nombre = "Agosto";
                break;
            case 9:
                nombre = "Setiembre";
                break;
            case 10:
                nombre = "Octubre";
                break;
            case 11:
                nombre = "Noviembre";
                break;
            default:
                nombre = "Diciembre";
        }

        return nombre;
    }

    public static String[] filterStringArray(String[] array){
        array = Arrays.stream(array)
                .filter(x -> (x != null))
                .toArray(String[]::new);
        return array;
    }

    public static Integer[] agregarElementoArray(Integer[] inArray, Integer nuevoElemento){
        Integer[] nuevoArray = new Integer[inArray.length+1];
        for (int i=0; i<nuevoArray.length-1;i++){
            nuevoArray[i] = inArray[i];
        }
        nuevoArray[inArray.length] = nuevoElemento;
        return nuevoArray;
    }

    public static boolean objExists(Object object){
        Optional<Object> cxtObject = Optional.ofNullable(object);
        if(cxtObject.isPresent())
            return true;
        return false;
    }

    public static String jsonResponse(String r) {
        return "{\"res\":\""+ r +"\"}";
    }

    public static String jsonResponse(String r, String uuid) {
        return "{\"res\":\""+ r +"\",\"rdm\":\""+ uuid +"\"}";
    }

    public static String getRandomString(int length){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}

