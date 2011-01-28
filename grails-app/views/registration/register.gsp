<html>
	<head>
		<title>Register</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		
		<div id="content">
			
			<div class="post">
				<h2>Request an Account</h2>
				<g:form action="submitRegistrationRequest">
					<ul>
						<li>
							<label for="username">Username</label>
							<g:textField name="username" value="${ params?.username }" />
						</li>
						<li>
							<label for="email">Email Address</label>
							<g:textField name="email" value="${ params?.email }" />
						</li>
					</ul>
					<h2>Password</h2>
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
					<g:submitButton name="submit" value="Submit Request" /> 
				</g:form>
			</div>
			
		</div>
		
		<div id="sidebar">
			
		</div>
		
	</body>
</html>