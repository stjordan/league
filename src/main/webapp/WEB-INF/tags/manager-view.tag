<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="title"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>${title}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- e.g. http://incredibleleague.appspot.com/, or http://localhost:8080/ -->
		<c:set var="req" value="${pageContext.request}"/>
		<c:set var="host" value="${fn:replace(req.requestURL, req.requestURI, '')}/"/>
		<base href="${host}manager/">
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<!-- <script src="/scripts/main.js"></script> -->
		<link rel="stylesheet" type="text/css" href="../styles/common.css" />
		<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.min.css"/>
	</head>
	<body>
	
		<ul class="nav">
			<li>
				<div class="exit-icon"></div>
				<div>Exit</div>
			</li>
			<li>
				<div class="no-icon"></div>
				<div>
					<a href="weeks">Weeks</a>
				</div>
			</li>
			<li>
				<div class="divider-icon"></div>
				<div>${week.id}
					<span><fmt:formatDate pattern="M/d" value="${week.lock}"/></span>
				</div>
				<ul>
					<c:forEach var="_week" items="${weeks}">
						<li>
							<a  href="weeks/${_week.id}/${weekResource}">${_week.id} <span><fmt:formatDate pattern="M/d" value="${week.lock}"/></span></a>
						</li>
					</c:forEach>
				</ul>
			</li>
			<li class="user">
				<div class="menu-icon"></div>
				<%-- <div>${player.name}</div> --%>
			</li>
		</ul>
	

		<jsp:doBody/>
	</body>
</html>
