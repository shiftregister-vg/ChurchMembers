<html>
<head>
    <title>${user}</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h1>${user}</h1>
	<div>
		<li><g:link action="changePassword">Change Password</g:link></li>
		 | <li><g:link controller="logout">Logout</g:link></li>
	</div>
</body>
</html>