<html>
	
	<head>
		<title>Edit Linked Member</title>
		<meta name="layout" content="main" />
	</head>
	
	<body>
		
		<div id="content">
			
			<div class="post">
				<h2>Edit Linked Member Details</h2>
				<g:form action="saveLinked">
					<input type="hidden" name="id" value="${ member.id }" />
					<g:render template="/member/editMemberFields" />
					<g:submitButton name="submit" value="Save" />
				</g:form>
			</div>
			
		</div>
		
		<div id="sidebar">
			
		</div>
		
	</body>
	
</html>
