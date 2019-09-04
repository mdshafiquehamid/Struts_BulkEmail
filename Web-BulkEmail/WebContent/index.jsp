<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action=sendemail method="post">
		<table>
	    	<tr>
	    		<td><label for="to">To*</label></td>
	    		<td><input size="69" id="to" type="text" name="to" required></td>
	    	</tr>
	    	<tr>
	    		<td><label for="subject">Subject*</label></td>
	    		<td><input size="69" id="subject" type="text" name="subject" required></td>
	    	</tr>
	    	<tr>
	    		<td><label for="message">Message*</label></td>
	    		<td><textarea rows="10" cols="70" id="message" name="message" required></textarea></td>
	    	</tr>
    	</table>
    	<button name="submitType" value="send" type="submit" class="btn btn-primary">Send</button>     			
    </form>
    <p class="email-message"><s:property value="msg"/></p>
</body>
</html>