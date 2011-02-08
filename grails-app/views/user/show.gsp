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
		
		<div class="post">
	    	<h2>Password</h2>
			<form id="changePasswordForm" method="post" action="${ createLink(action:'edit') }">
				<ul>
					<li>
						<label for="password1">Password</label>
						<g:passwordField name="password1" />
					</li>
					<li>
						<label for="password2">Repeat Password</label>
						<g:passwordField name="password2" />
					</li>
				</ul>
				<input type="submit" value="Set Password" />
			</form>
	    </div>
	</div>
	
	<div id="sidebar">
		<ul>
			<li>
				<h2>Actions</h2>
				<ul>
					<g:if test="${ member }">
						<li><g:link controller="member" action="editLinked">Edit Member Info</g:link></li>
						<li><g:link controller="user" action="unlinkMember">Unlink Member Info</g:link></li>
					</g:if>
					<g:else>
						<li>
							<g:link controller="member" action="createLinked">Create Member Details</g:link>
						</li>
						<li>
							<g:form action="linkMember">
								<g:render template="/user/linkMemberFormFields" />
							</g:form>
						</li>
					</g:else>
				</ul>
			</li>
		</ul>
	</div>
	
</body>
</html>
