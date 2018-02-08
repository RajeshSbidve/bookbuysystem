<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
    .error {
        color: black; font-weight: bold;
    }
</style>
</head>
<body style="background: -webkit-linear-gradient(left top, brown, pink);">
<div align="center">
        <h2>Login Form</h2>
        <table border="0" width="90%">
        <c:url var="action" value="/loginForm.html"></c:url>
        <form:form action="${action}" modelAttribute="loginForm">
                <tr>
                    <td align="left" width="20%">Email: </td>
                    <td align="left" width="40%"><form:input path="email" size="30"/> </td>
                    <td align="left"><form:errors path="email" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><form:password path="password" size="30"/></td>
                    <td><form:errors path="password" cssClass="error"/>${key}</td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center"><input type="submit" value="Login"/></td>
                    <td></td>
                    
                </tr>
                <tr>
 				<td><a href="Register"><input type="button" value="Register" title="Register"/></a></td>
 				</tr>
 				 
        </form:form>
        </table>
    </div>


</body>
</html>