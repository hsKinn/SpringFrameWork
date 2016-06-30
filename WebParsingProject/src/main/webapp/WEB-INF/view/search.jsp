<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Web Parsing Datas</title>
<style type="text/css">
div#header {
	float: left;
	width: 100%;
	height: 20%;
	text-align: center;
}

div#content {
	float: left;
	width: 100%;
	height: 80%;
	overflow-y: scroll;
}

p#siteName {
	font-size: 16px;
}

p#siteDescription {
	font-size: 13px;
}
</style>
</head>
<body>

<form method="post" action="/WebParsingProject/searchingDatas">
	<div id="header">
		Web Parsing Search by Lucene <br/><br/>
		<input type="text" id="keyword" value="${ keyword }" name="keyword" autofocus="autofocus" size="30" placeholder="검색어를 입력하세요" />
		<input type="submit" id="btnSubmit" value="검색" />
		<input type="button" id="btnInit" value="초기화" onclick="location.href='/WebParsingProject/searchingDatas'" />
	</div>
</form>

<div id="content">
	
	<c:set value="${ results.size() }" var="searchCount" /> 
	
	<c:if test="${ searchCount gt 1}">
		총 ${ results[searchCount-1].sortNo } 개 검색
	</c:if>

	<c:if test="${ keyword ne '' }">
		<c:if test="${ results ne null }">
			<c:forEach items="${results}" var="result">
				<p id="siteName">
					<a href="http://${ result.siteName }" target="_blank">${result.sortNo}. ${ result.siteName }</a>
				</p>
				<p id="siteDescription">
					${ result.siteDescription }
				</p><br/>
			</c:forEach>
		</c:if>
		
		<c:if test="${ results.size() lt 1 }">
			${ keyword }로 인덱싱 된 데이터가 존재하지 않습니다
		</c:if>
	</c:if>
</div>

</body>
</html>