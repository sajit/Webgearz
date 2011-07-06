<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Template list</title>
 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/thirdpty/jquery-1.6.1.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/thirdpty/jquery.form.js"></script>
 	<script type="text/javascript">
 		var userId = "${userId}";
 	</script>	
</head>
<body>
	<c:forEach items="${templates}" var="template">
		<input id="${template.id}"  type="checkbox" class="templatebox" name="template"/>
		${template.templateName} <input id="domain_${template.id}" type="text" name="domainName" style="display:none" /> <br/> 
	</c:forEach>
	<input id="templateSelectButton" type="button" value="Select" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/templates/templatehelper.js"></script>
</body>
</html>