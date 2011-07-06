<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="logoutDiv">
	<a href="<c:url value="/j_spring_security_logout"/>" >Logout</a>
	<!--form name="f" action="/j_spring_security_logout" method="post">
		<input type="submit" value="Logout" />
	</form-->
</div>