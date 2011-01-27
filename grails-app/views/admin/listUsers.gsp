<%@ page import="org.stevegood.user.User" %>

<html>
	<head>
		<title>Admin - Users List</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		
		<div id="content">
			
			<div class="post">
				<h2>Users List</h2>
				<ul>
					<g:each in="${userList}" var="user">
						<li><g:link action="editUser" params="[username:user]">${ user }</g:link></li>
					</g:each>
				</ul>
			</div>
			
		</div>
		
		<div id="sidebar">
			<g:render template="/admin/adminMenu" />
		</div>
		
	</body>
</html>