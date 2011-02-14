<head>
<meta name='layout' content='main' />
<title>Login</title>
</head>

<body>
	<div id="content">
		<div class="post">
			<h2>Please Login...</h2>
			<div id='login'>
				<div class='inner'>
					<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
						<div class="fields">
							<p>
								<label for='username'>Username</label>
								<input type='text' class='text_' name='j_username' id='username' />
							</p>
							<p>
								<label for='password'>Password</label>
								<input type='password' class='text_' name='j_password' id='password' />
							</p>
						</div>
						<p>
							<label for='remember_me'>Remember me</label>
							<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
							<g:if test='${hasCookie}'>checked='checked'</g:if> />
							<input class="submit" type='submit' value='Login' />
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript'>
	<!--
	(function(){
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
	</script>
</body>
