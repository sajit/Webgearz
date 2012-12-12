<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<div id="LoginBoxDiv">
	
	<form:form method="post" action="dologin" commandName="loginForm">  
		<table>  
		<tr>  
			<td colspan="2">Login</td>  
		</tr>  
		 
		<tr>  
		<td><form:label path="email">Email</form:label></td>  
		<td><form:input path="email" /></td>  
		</tr>  
		<tr>  
		<td><form:label path="password">Password</form:label></td>  
		<td><form:password path="password" /></td>  
		</tr>  
		<tr>  
		<td colspan="2"><input type="submit" value="Login!" /></td>  
		</tr>  
		</table>  
	</form:form> 
</div>