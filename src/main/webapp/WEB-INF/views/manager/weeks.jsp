<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:manager-view title="Weeks">

  <div class="panel panel-default">
    <div class="panel-heading">
      <h3 class="panel-title">Add a Week</h3>
    </div>
    <div class="panel-body">
      <form:form commandName="newWeek" method="post" action="weeks" role="form" class="form-horizontal">
        <div class="form-group">
          <form:label path="id" class="col-sm-2 control-label">ID</form:label>
          <div class="col-sm-3">
            <form:input path="id" class="form-control"/>
            <form:errors path="id"/>
          </div>
        </div>
        <div class="form-group">
          <form:label path="lock" class="col-sm-2 control-label">Game Day</form:label>
          <div class="col-sm-3">
            <form:select path="lock" items="${gameDays}" class="form-control"/>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-3">
            <button type="submit" class="btn btn-primary">Add Week</button>
          </div>
        </div>
      </form:form>    
    </div>
  </div>


	
	<h3>Weeks</h3>
	
</t:manager-view>
