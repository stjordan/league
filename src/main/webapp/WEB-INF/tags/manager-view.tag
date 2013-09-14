<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="title"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>${title}</title>
		
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">

      <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.min.css"/>
      <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" media="screen">
      <link rel="stylesheet" type="text/css" href="../styles/common.css" />

      <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
      <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
      <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
      <!-- <script src="/scripts/main.js"></script> -->

      <!-- e.g. http://incredibleleague.appspot.com/, or http://localhost:8080/ -->
      <c:set var="req" value="${pageContext.request}"/>
      <c:set var="host" value="${fn:replace(req.requestURL, req.requestURI, '')}/"/>
      <base href="${host}manager/">
      
      <fmt:setTimeZone value="America/New_York"/>
  </head>
  <body>
	
    <nav class="navbar navbar-default navbar-static-top" role="navigation">
      <!-- Brand and toggle get grouped for better mobile display -->
	  <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
          <span class="sr-only">Toggle navigation</span>
		  <span class="icon-bar"></span>
		  <span class="icon-bar"></span>
		  <span class="icon-bar"></span>
        </button>
		<a class="navbar-brand" href="#"><!-- <span class="glyphicon glyphicon-home"></span> -->Incredible League</a>
	  </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
	  <div class="collapse navbar-collapse navbar-ex1-collapse">
    
	    <ul class="nav navbar-nav">
      
          <!-- Players -->
		  <li>
		    <a href="#"><span class="glyphicon glyphicon-user"></span> Players</a>
		  </li>
          
          <!-- Weeks -->
	      <li class="dropdown">
	        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			  <span class="glyphicon glyphicon-calendar"></span>
              <c:choose>
                <c:when test="${week ne null}">
                  Week ${week.id} - <fmt:formatDate type="both" pattern="M/d" value="${week.lock}"/>
                </c:when>
                <c:otherwise>
                  Weeks
                </c:otherwise>
              </c:choose>
			</a>
			<ul class="dropdown-menu">
		      <c:forEach var="week" items="${weeks}">
			    <li><a href="#">Week ${week.id} - <fmt:formatDate type="both" pattern="M/d" value="${week.lock}"/></a>
              </c:forEach>
              <li class="divider"></li>
              <li><a href="weeks">Edit Weeks</a></li>
			</ul>
          </li>
          
          <!-- Teams/Games/Tickets -->
		  <li><a href="#">Teams</a></li>
		  <li><a href="#">Games</a></li>
		  <li><a href="#">Tickets</a></li>
        </ul>
        
		<ul class="nav navbar-nav navbar-right">
		  <li class="dropdown">
		    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Steve <b class="caret"></b></a>
		    <ul class="dropdown-menu">
		      <li><a href="#">Settings</a></li>
		      <li><a href="#">Manager</a></li>
		      <li><a href="#">Log Out</a></li>
		    </ul>
		  </li>
        </ul>
        
      </div><!-- /.navbar-collapse -->
 	</nav>
	
		<div class="content">
			<jsp:doBody/>
		</div>
		
	</body>
</html>
