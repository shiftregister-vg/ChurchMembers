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
		
		
		<div id="logo">
			<h1><a href="/ChurchMembers/">Church Member Manager</a></h1>
		  <p>
		    <sec:ifNotLoggedIn>
		      <g:link controller="login">Login</g:link>
		      &nbsp;::&nbsp;<g:link controller="registration" action="register">Register</g:link>
		    </sec:ifNotLoggedIn>
		    <sec:ifLoggedIn>
			    <g:link controller="user" action="show">Profile</g:link>
				&nbsp;::&nbsp;<g:link controller="logout">Logout</g:link>
				<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SUPER_USER">
					&nbsp;::&nbsp;<g:link controller="admin">Admin</g:link>
				</sec:ifAnyGranted>
		    </sec:ifLoggedIn>
		  </p>
		</div>
		<div id="menu">
			<ul>
		    <sec:ifNotLoggedIn>
		    <li class="current_page_item"><g:link controller="login">Login</g:link></li>
		    </sec:ifNotLoggedIn>
		    <sec:ifLoggedIn>
		  		<li class="${ params.controller == 'member' && params.action == 'list' ? 'current_page_item' : '' }"><g:link controller="member" action="list">All Members</g:link></li>
		  		<sec:ifAllGranted roles="ROLE_ADMIN">
					<li class="${ params.controller == 'member' && params.action == 'create' ? 'current_page_item' : '' }"><g:link controller="member" action="create">Add Member</g:link></li>
				</sec:ifAllGranted>
				<li class="${ params.controller == 'member' && (params.action == 'search' || params.action == "searchResults") ? 'current_page_item' : '' }"><g:link controller="member" action="search">Member Search</g:link></li>
		    </sec:ifLoggedIn>
		  </ul>
		</div>
		<!-- end header -->
		<!-- start page -->
		<div id="page">
			<div id="page-bg">
				<g:if test="${flash.message}">
					<div class="message">${flash.message}</div>
				</g:if>
				<g:layoutBody />
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
		<!-- end page -->
		<div id="footer">
		  <p>Copyright &copy; ${ new Date().format('yyyy') } <a href="http://stevegood.org/" target="_blank">Steve Good</a> All Rights Reserved :: Design by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>.</p>
		</div>
	</body>
</html>
