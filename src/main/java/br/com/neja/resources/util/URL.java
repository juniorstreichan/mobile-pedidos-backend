package br.com.neja.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList(String req){	
		String letras = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM?@#$%^&*(){}+`'~-/";
		String numeros = "0123456789"; 
		List<String> list = new ArrayList<String>();
		
		for (String id : req.split(",")) {
			if (numeros.contains(id) && !letras.contains(id)) {
				list.add(id.trim().replace(" ", ""));
			}
		}
		
		return list.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
 
	}
	
	
	public static String decodeParam(String req) {
		try {
			return URLDecoder.decode(req,"UTF-8");
		} catch (UnsupportedEncodingException e) {
	 
			return "";
		}
		
	}
	
}
