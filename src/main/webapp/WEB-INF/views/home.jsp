<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link type="text/css" rel="stylesheet"
	href="./Pivotal PCF HTML base_files/css_pbm0lsQQJ7A7WCCIMgxLho6mI_kBNgznNUWmTWcnfoE.css"
	media="all">
<link type="text/css" rel="stylesheet"
	href="./Pivotal PCF HTML base_files/css_2KVgb-KG7K_xmcxXlaXqcvBdCkOiV1FRpdcoCf8n9Bc.css"
	media="all">
<link type="text/css" rel="stylesheet"
	href="./Pivotal PCF HTML base_files/css_pRq26yQgFLdk1xMQ2byNXmVJkVZ_w6XJQsnGDdo5RZc.css"
	media="all">
<link type="text/css" rel="stylesheet"
	href="./Pivotal PCF HTML base_files/css_Qjk3o5CWsMvvHGLS2jhWKbZCSWruwUFepoGUjziT_XY.css"
	media="all">
</head>
<body>
<h1>
	Hello world!  
</h1>
<form:form method="POST" action="/appServlet/save">
   <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="emailAddr">Email Addr</form:label></td>
        <td><form:input path="emailAddr" /></td>
    </tr>
    <tr>
        <td><form:label path="phoneNbr">Phone Nbr</form:label></td>
        <td><form:input path="phoneNbr" /></td>
    </tr>
    <tr>
        <td><form:label path="eventID">Event ID</form:label></td>
        <td><form:input path="eventID" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
