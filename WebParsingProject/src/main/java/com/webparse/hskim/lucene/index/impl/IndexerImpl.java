package com.webparse.hskim.lucene.index.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.webparse.hskim.lucene.index.Indexer;

public class IndexerImpl implements Indexer {

	private IndexWriter writer;

	/**
	 * 인덱스 처리
	 * indexDir : 인덱스된 정보를 담을 폴더
	 * dataDir : 인덱스 처리 할 데이터가 있는 폴더
	 */
	public void getIndex() throws IOException {
		
		String indexDir = "D:\\Lucene/";
		String dataDir = "D:\\Lucene/";
		
		Directory dir = FSDirectory.open(new File(indexDir));
		Analyzer analyzer = new SimpleAnalyzer(Version.LUCENE_46);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, analyzer);

		writer = new IndexWriter(dir, config);
		
		int numIndexed = 0;

		try {
			
			numIndexed = this.index(dataDir, new TextFilesFilter());
			this.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("인덱싱 " + numIndexed + "개 완료");
	}

	
	/**
	 * IndexWriter를 닫기 위한 메소드
	 */
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 지정된 dataDir에서 파일 리스트를 가져와서 Validation Check를 한 후
	 * 인덱스 처리
	 * 총 인덱스 처리 된 파일 개수 리턴
	 */
	public int index(String dataDir, FileFilter filter) throws Exception {

		File[] files = new File(dataDir).listFiles();

		for (File f : files) {
			if (!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead()
					&& (filter == null || filter.accept(f))) {
				indexFile(f);
			}
		}
		return writer.numDocs();

	}

	
	/**
	 * 인덱스 처리를 위한 처리 메소드
	 */
	public void indexFile(File f) throws Exception {
		Document doc = getDocument(f);
		writer.addDocument(doc);
	}

	
	/**
	 * 파일 정보를 가져와서 필드를 추가한다
	 */
	public Document getDocument(File f) throws Exception {
		Document doc = new Document();
		
		doc.add(new TextField("KEY_SITENAME", f.getName(), Field.Store.YES));
		
		doc.add(new TextField("siteName", f.getName(), Field.Store.YES));
		doc.add(new TextField("contents", new FileReader(f)));
		doc.add(new StringField("fullPath", f.getCanonicalPath(), Field.Store.YES));
		
		return doc;
	}

	
	/**
	 * 텍스트 파일만을 가져오기 위한 필터
	 */
	private static class TextFilesFilter implements FileFilter {
		@Override
		public boolean accept(File pathname) {
			return pathname.getName().toLowerCase().endsWith(".txt");
		}
	}

}
