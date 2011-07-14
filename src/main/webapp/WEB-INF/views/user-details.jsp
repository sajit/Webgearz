<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/thirdpty/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/thirdpty/jquery.form.js"></script>
</head>
<body>
	<form id="userCreateFormId" action="${pageContext.request.contextPath}/createUser" method="post">
	 First Name : <input type="text" name="firstName" value="${user.firstName }" /><br/>
	 Last Name : <input type="text" name="lastName" value="${user.lastName }" /><br/>
	 Email : <input type="text" name="email" value="${user.email }"/><br/>
	 Password : <input type="password" name="password" /> <br/>
	 <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
	 <input type="submit" name="_eventId_submitRegistration" value="Create" />
	 <input type="submit" name="_eventId_cancelRegistration" value="Cancel" />
	</form>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/users/userdetailshelper.js"></script>
</body>
</html>