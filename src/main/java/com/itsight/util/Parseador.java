package com.itsight.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

public class Parseador {

	public static final Logger LOGGER = LogManager.getLogger(Parseador.class);

	public static String getDecodeBase64(String encode){
		return new String(Base64.getDecoder().decode(encode), StandardCharsets.UTF_8);
	}
	public static String getEncodeBase64(String encode){
		return new String(Base64.getEncoder().encode(encode.getBytes()), StandardCharsets.UTF_8);
	}
}
