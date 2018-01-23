package com.johanneslee.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupUtil {
	private final String URL = "http://www.nlotto.co.kr/gameResult.do";
	private final String PARAM_DEFAULT = "?method=byWin";
	private final String USER_AGENT = "mozila/5.0";
	
	public void isConnected(int param) {
		Document doc = null;
		String paramTurn = "&drwNo=" + param;
		try {
			doc = Jsoup.connect(URL + PARAM_DEFAULT + paramTurn).header("User-Agent", USER_AGENT).get();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("FDHSGHSLJ");;
		}
		
		System.out.println(doc.html());
	}
	
	public List<String> getNumbers(int param) {
		List<String> list = new ArrayList<>();	
		Document doc = getDocument(param);
		for (Element e : doc.select("img")) {
		    if(e.attr("src").contains("ball"))
				list.add(e.attr("alt"));
		}
		return list;
	}
	
	private Document getDocument(int param) {
		Document doc = null;
		String paramTurn = "&drwNo=" + param;
		try {
			doc = Jsoup.connect(URL + PARAM_DEFAULT + paramTurn).header("User-Agent", USER_AGENT).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
}
