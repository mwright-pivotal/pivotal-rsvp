<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html><head>


<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="0" http-equiv="expires">
<meta content="text/javascript" http-equiv="Content-Script-Type">
<meta content="text/css" http-equiv="Content-Style-Type">
<meta content="IE=Edge,chrome=1" http-equiv="X-UA-Compatible">
<link href="/favicon.png" rel="icon" type="image/png"><title>Pivotal Cloud Foundry Workshop</title>

<meta content="authenticity_token" name="csrf-param">
<meta content="Ydy5lm7k0vswzFuG7LnyOBhBYZCglmcbdiiJ5LTdJMQ=" name="csrf-token">
<link href="${pageContext.servletContext.contextPath}/home_files/application-77e95d0a00de1e891a42937543958acf.css" media="screen" rel="stylesheet">
<script src="${pageContext.servletContext.contextPath}/home_files/application-9656b783446a3defe004ae299b95931d.js"></script></head><body id="without-user">
<div style="margin-left: 0px; width: 485px;" class="login">
Pivotal Cloud Foundry Workshop Invitation<br>
<form:form modelAttribute="registrant" action="/save" method="post">
<div style="margin:0;padding:0;display:inline"><br>
</div><fieldset><div class='control-group'>
<div class='controls input-with-tooltip'>
<div class='input-prepend'>
<span class='add-on'>
<i class='fa fa-user'></i>
</span>
<span style="color: white;">
   <table>
    <tr>
    	<label for="name">Name</label>
      <form:input path="name" id="name" />
    </tr>
    <tr>
        <td><label path="emailAddr">Email Addr</label></td>
        <td><form:input path="emailAddr" id="emailAddr" /></td>
    </tr>
    <tr>
        <td><label path="phoneNbr">Phone Nbr</label></td>
        <td><form:input path="phoneNbr" id="phoneNbr" /></td>
    </tr>
    <tr>
        <td><label path="eventID">Event ID</label></td>
        <td><form:input path="eventID" id="eventID" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <button class='btn btn-primary' id='reg-action' type='submit'>Register</button>
        </td>
    </tr>
</table>
</span>
</div></div>
</div>
</fieldset>
</form:form>
<span style="color: white;"></span><br style="color: white;">
<br style="color: white;">
<span style="color: white;"></span><br>


</div>
<div class="footer"><footer class="site-footer">
<span class="version">Pivotal Cloud Foundry</span> <span>� 2017 Pivotal Inc. All Rights Reserved.</span>
<span class="float-right"><a href="/eula">End User License Agreement</a></span>
</footer>
</div>
<div id="preload_images" style="display: none;">
<img src="${pageContext.servletContext.contextPath}/home_files/p_logo-hover-8af12cb15b4dc5394e58c9f3d52ab5ca.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/logo-pivotal-cf-cf895f339e23413b3d186c2f6bcab752.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/page-background-65459cf504d813b0542514c1c86d7473.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/pivotal_cf_logo-6f1d6ce62d56e3c394519748eea220d3.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/logo-pivotal-ops-manager-2f9eb768e525c06856e1072561238c0f.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/powered_by_pivotal-67a05721e886f6ecec7132eeb7be1db5.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/p_logo-0bc1434ab56da0da1e8ceca47602ed1c.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/bg-body-65459cf504d813b0542514c1c86d7473.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/logo-pivotal-01f9bb815545fe7fa33cbb78f25bc755.png" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/bg-login-667738d760943dcd9b025ba489c80fd6.jpg" height="0" width="0">
<img src="${pageContext.servletContext.contextPath}/home_files/logo-vSphere-5559a8ef44d7b73dd077e6e910736dca.png" height="0" width="0">
</div>
<P>  The time on the server is ${serverTime}. </P>
<P>  The event ID is ${eventID}. </P>
</body></html>