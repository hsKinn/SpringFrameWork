package com.webparse.hskim.lucene.index;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.lucene.document.Document;

public interface Indexer {

	public void getIndex() throws IOException ;
	
	public void close();
	
	public int index(String dataDir, FileFilter filter) throws Exception;
	
	public void indexFile(File f) throws Exception;
	
	public Document getDocument(File f) throws Exception;
	
	
}
