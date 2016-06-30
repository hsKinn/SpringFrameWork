package com.webparse.hskim.lucene.search;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.flexible.standard.parser.ParseException;

import com.webparse.hskim.webparsing.vo.WebParseVO;

public interface Searcher {

	public List<WebParseVO> search(String keyword) throws IOException, ParseException;
}
