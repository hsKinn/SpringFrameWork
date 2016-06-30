package com.webparse.hskim.lucene.search.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.standard.parser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.webparse.hskim.lucene.search.Searcher;
import com.webparse.hskim.webparsing.vo.WebParseVO;

public class SearcherImpl implements Searcher {

	/**
	 * 검색 키워드와 색인 폴더를 받아 검색 처리
	 *
	 * @param indexDir
	 * @param keyword
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<WebParseVO> search(String keyword) throws IOException, ParseException {

		// 검색 폴더 설정
		String indexDir = "D:\\Lucene/";
		
		List<WebParseVO> searchedDatas = new ArrayList<WebParseVO>();
		WebParseVO data;
		
        Directory dir = FSDirectory.open(new File(indexDir));
        
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        
        // QueryParser "KEY_SITENAME" 필드 검색
        QueryParser parse = new QueryParser(Version.LUCENE_46, "KEY_SITENAME", new SimpleAnalyzer(Version.LUCENE_46));
        
        Query query;
        
		try {
			// "KEY_SITENAME" 필드에 keyword 값으로 검색
			query = parse.parse(keyword);
			
			// 검색 결과를 가져온다
	        TopDocs hits = is.search(query, 500);
	        System.out.println("총 " + hits.totalHits + "개 데이터 검색");
	        
	        int sortNo = 1;
	        
	        // 검색 결과 하나씩 List에 담기
	        for(ScoreDoc scoreDoc : hits.scoreDocs){
	        	
	        	data = new WebParseVO();
	        	
	            Document doc = is.doc(scoreDoc.doc);
	            
	            data.setSiteName( doc.get("siteName").substring(0, doc.get("siteName").length() -4 ) );
	            data.setSiteDescription("");
	            data.setSortNo(sortNo);
	            sortNo++;
	            
	            // Description 읽기
	            File file = new File(doc.get("fullPath"));
	            FileReader fileReader = new FileReader(file);
	            BufferedReader contents = new BufferedReader(fileReader);
	            
	            String line = "";
	            while ( (line = contents.readLine()) != null ) {
	            	data.setSiteDescription(data.getSiteDescription() + line);
	            }
	            contents.close();
	            
	            searchedDatas.add(data);
	        }
	        
	        reader.close();
	        
		} catch (org.apache.lucene.queryparser.classic.ParseException e) {
			e.printStackTrace();
		}
		
		return searchedDatas;
    }

}