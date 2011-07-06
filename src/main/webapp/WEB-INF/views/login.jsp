<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="LoginBoxDiv">
	
	<form name="f" action="j_spring_security_check"" method="post">
      <table>
        <tr><td>User:</td><td><input type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'></td></tr>
        <!--tr><td><input type="checkbox" name="_spring_security_remember_me"></td><td>Don't ask for my password for two weeks</td></tr-->
 
        <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
        <tr><td colspan='2'><input name="reset" type="reset"></td></tr>
      </table>
    </form>
</div>