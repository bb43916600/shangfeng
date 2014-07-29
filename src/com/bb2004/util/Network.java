package com.bb2004.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

public class Network {
	public static final String getParamUTF8(HttpServletRequest req, String key){
		try {
			return URLDecoder.decode(req.getParameter(key), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final String[] getParamsUTF8(HttpServletRequest req, String key){
		String [] result = req.getParameterValues(key);
		if(result==null)
			return null;
		if(result.length==1){
			try {
				return URLDecoder.decode(result[0], "utf-8").split(",");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}else{
			return result;
		}
		
	}
}
