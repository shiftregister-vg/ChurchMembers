<html>
<head>
    <title>${user}</title>
    <meta name="layout" content="main" />
</head>
<body>
	
	<div id="content">
		
		<div class="post">
			<h2>${ member ? "$member ($user)" : user }</h2>
			<ul>
				<li>Username: <strong>${ user }</strong></li>
			</ul>
			<g:if test="${ member }">
				<g:render template="/member/details" />
			</g:if>
		</div>
		
	</div>
	
	<div id="sidebar">
		<ul>
			<li>
				<h2>Actions</h2>
				<ul>
					<li><g:link action="changePassword">Change Password</g:link></li>
					<g:if test="${ member }">
						<li><g:link controller="member" action="editLinked">Edit Member Info</g:link></li>
						<li>Unlink Member Info</li>
					</g:if>
				</ul>
			</li>
		</ul>
	</div>
	
</body>
</html>
