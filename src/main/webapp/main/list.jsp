<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html >
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="table.css" />
<script>
	
</script>
</head>
<body>
	<%-- <%=application.getRealPath("/") %> 여기서 나오는 경로에 이미지를 저장해야파일올렸을때 새로고침안해도 바로 나온다.--%>
	<center>
		<h3>사원 목록</h3>
		<table id="table_content" width="700">
			<tr>
				<td align="left">
					<a href="graph.do">그래프로 통계보기</a>
				</td>
			</tr>/
		</table>
		<table id="table_content" width="700">
			<tr>
				<th>사원</th>
				<th>이름</th>
				<th>직위</th>
				<th>입사일</th>
				<th>급여</th>
				<th>부서</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.empno }</td>
					<td>${vo.ename }</td>
					<td>${vo.job }</td>
					<td>${vo.hiredate}</td>
					<%-- <td><fmt:formatDate value="${vo.hiredate }" pattern="yyyy-MM-dd"/></td> --%>
					<td>${vo.sal }</td>
					<td>${vo.deptno }</td>
				</tr>
			</c:forEach>
		</table>
		<img src="sal.png"/><!--경로에 맞게 저장했으므로 바로 불러오면 된다.  -->
	</center>
</body>
</html>