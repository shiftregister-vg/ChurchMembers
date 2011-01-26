<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Plain Office
Description: A two-column, fixed-width blog design.
Version    : 1.0
Released   : 20071001

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Church Member Manager - <g:layoutTitle default="" /></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="${ resource(dir:'css',file:'default.css') }" rel="stylesheet" type="text/css" media="screen" />
<g:layoutHead />
</head>
<body>
<!-- start header -->
<g:if test="${flash.message}">
  <div class="message">${flash.message}</div>
</g:if>
<div id="logo">
	<h1><a href="/ChurchMembers/">Church Member Manager</a></h1>
  <p>
    <sec:ifNotLoggedIn>
      <g:link controller="login">Login</g:link>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn>
      <g:link controller="logout">Logout</g:link>
      :: <g:link controller="user" action="show">Profile</g:link>
    </sec:ifLoggedIn>
  </p>
</div>
<div id="menu">
	<ul>
    <sec:ifNotLoggedIn>
    <li class="current_page_item"><g:link controller="login">Login</g:link></li>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn>
  		<li class="current_page_item"><a href="#">All Members</a></li>
  		<li><a href="#">Add Member</a></li>
      <!--li><a href="#">Services</a></li>
  		<li><a href="#">About Us</a></li>
  		<li><a href="#">Contact Us</a></li-->
    </sec:ifLoggedIn>
  </ul>
</div>
<!-- end header -->
<!-- start page -->
<div id="page">
	<div id="page-bg">
		<g:layoutBody />
		<div style="clear: both;">&nbsp;</div>
	</div>
</div>
<!-- end page -->
<div id="footer">
  <p>Copyright &copy; ${ new Date().format('yyyy') } <a href="http://stevegood.org/" target="_blank">stevegood.org</a> All Rights Reserved :: Design by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>.</p>
</div>
</body>
</html>
