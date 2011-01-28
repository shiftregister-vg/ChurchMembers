<html>
	<head>
		<title>Admin - Add User</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		<div id="content">
			<g:form action="createUser">
			<div class="post">
				<h2>Add User</h2>				
				<g:render template="/user/createUserForm" />
			</div>
			
			<div>
				<g:submitButton name="add" value="Add" />
			</div>
			</g:form>
		</div>
		
		<div id="sidebar">
			<g:render template="/admin/adminMenu" />
		</div>
	</body>
</html>