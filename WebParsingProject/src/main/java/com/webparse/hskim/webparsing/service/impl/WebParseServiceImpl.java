package com.webparse.hskim.webparsing.service.impl;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.flexible.standard.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.servlet.ModelAndView;

import com.webparse.hskim.lucene.index.Indexer;
import com.webparse.hskim.lucene.search.Searcher;
import com.webparse.hskim.webparsing.service.WebParseService;
import com.webparse.hskim.webparsing.vo.WebParseVO;

public class WebParseServiceImpl implements WebParseService{
	
	// Index Class DI
	private Indexer indexer;
	
	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}


	// Search Class DI
	private Searcher searcher;
	
	public void setSearcher(Searcher searcher) {
		this.searcher = searcher;
	}


	/**
	 * Get Web Parsing Data
	 * 
	 * @author hskim
	 */
	@Override
	public ModelAndView getWebParsingDatas() {
		
		ModelAndView view = new ModelAndView();
		
		// 사이트 목록 List 가져오기
		List<WebParseVO> datas = getDatas();
		
		// txt 파일 생성
		createTXTFile(datas);
		
		// Lucene Index 생성
		try {
			indexer.getIndex();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		view.setViewName("search");
		
		return view;
	}
	
	
	/**
	 * Get WEB Parsing Datas
	 * 
	 * From http://www.alexa.com/topsites/global;0 ~ 19 Page
	 * 
	 * @author hskim
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
		for (int i = 0; i < 20; i++) {
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
	
	
	/**
	 * Create TXT File 
	 * 
	 * @author hskim
	 * @param datas
	 */
	public void createTXTFile(List<WebParseVO> datas) {
		
		try {
			
			FileWriter fw;
			BufferedWriter bw;
			
			// 웹 사이트당 파일 생성
			for (WebParseVO webParseVO : datas) {
				
				fw = new FileWriter("D:\\Lucene/" + webParseVO.getSiteName() + ".txt", true);
				bw = new BufferedWriter(fw);
				
				bw.write(webParseVO.getSiteDescription());
				
				bw.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 키워드를 받아와 Index된 폴더에서 검색 후 
	 * 검색된 데이터를 리턴
	 * 
	 */
	@Override
	public ModelAndView getSearchingDatas(String keyword) {
	
		ModelAndView view = new ModelAndView();
		
		List<WebParseVO> searchedDatas = null;
		
		// 초기 접근 시 에러 제어
		if ( !keyword.equals("") ) {
			// Lucene 검색 과정
			try {
				searchedDatas = searcher.search(keyword);
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
			
			view.addObject("results", searchedDatas);
		}
		
		view.addObject("keyword", keyword);
		view.setViewName("search");
		
		return view;
	}
	

}
