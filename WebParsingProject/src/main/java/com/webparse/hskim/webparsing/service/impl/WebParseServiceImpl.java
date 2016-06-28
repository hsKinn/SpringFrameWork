package com.webparse.hskim.webparsing.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.servlet.ModelAndView;

import com.webparse.hskim.webparsing.biz.WebParseBiz;
import com.webparse.hskim.webparsing.service.WebParseService;
import com.webparse.hskim.webparsing.vo.WebParseVO;

public class WebParseServiceImpl implements WebParseService{
	
	private WebParseBiz parseBiz;
	public void setParseBiz(WebParseBiz parseBiz) {
		this.parseBiz = parseBiz;
	}

	@Override
	public ModelAndView insertDatas() {
		
		ModelAndView view = new ModelAndView();
		
		// 사이트 목록 List 가졍괴
		List<WebParseVO> datas = getDatas();
		System.out.println("총 " + datas.size() + "개 데이터 Parsing");
		
		// Insert 사이트
		parseBiz.insertDatas(datas);
		
		view.addObject("datas", datas);
		view.setViewName("datas");
		
		return view;
	}
	
	
	/**
	 * Get WEB Parsing Datas
	 * 
	 * From http://www.alexa.com/topsites/global;0 ~ 19 Page
	 * 
	 * @return Page Data List
	 */
	public List<WebParseVO> getDatas() {
		
		// 사이트 목록 넣을 List
		List<WebParseVO> datas = new ArrayList<WebParseVO>();
		
		// 파싱 데이터 VO (사이트 정보)
		WebParseVO data;
		
		// URL Connection
		Document doc;
		
		// Parsing Datas
		Elements siteName;
		Elements siteDescription;
		
		// Page Number 만큼 반복
		for (int i = 0; i < 19; i++) {
			try {
				// 각각 페이지 접근 후 HTML을 기준으로 파싱
				doc = Jsoup.connect("http://www.alexa.com/topsites/global;" + i).get();
				siteName = doc.select(".listings .desc-paragraph");
				siteDescription = doc.select(".listings .description");
				
				// 한 페이지 내의 데이터들 객체에 넣고 Page List에 add
				for (int j = 0; j < siteName.size(); j++) {
					data = new WebParseVO();
					data.setSiteName(siteName.get(j).text());
					// 중간에 ...More이라는 상관없는 문구 제거
					data.setSiteDescription(siteDescription.get(j).text().replaceAll("…More", ""));
					
					datas.add(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return datas;
	}

}
