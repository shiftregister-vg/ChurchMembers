<html>
	
	<head>
		<title>Create Linked Member</title>
		<meta name="layout" content="main" />
	</head>
	
	<body>
		
		<div id="content">
			
			<div class="post">
				<h2>Create Linked Member</h2>
				<g:form action="saveLinked">
					<g:render template="/member/editMemberFields" />
					<g:submitButton name="submit" value="Save" />
				</g:form>
			</div>
			
		</div>
		
		<div id="sidebar">
			
		</div>
		
	</body>
	
</html>
