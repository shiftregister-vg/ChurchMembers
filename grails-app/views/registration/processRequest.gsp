<html>
	<head>
		<title>Process User Request</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		<div id="content">
			
			<div class="post">
				<h2>Registration Request</h2>
				<p class="byline"><small>${ registrationRequest.dateCreated.format('MMMM dd, yyyy @ h:mm a') }</small></p>
				
				<ul>
					<li>Username: <strong>${ registrationRequest.user }</strong></li>
					<li>Email: <strong>${ registrationRequest.email }</strong></li>
				</ul>
				
				<g:if test="${ registrationRequest.member }">
					<h2>Member Info</h2>
					<ul>
						<li>${ registrationRequest.member } - <g:link action="removeMemberFromRequest" id="${ params.id }">Unlink</g:link></li>
					</ul>
				</g:if>
				
				<p>
					<g:link action="approveRequest" id="${ registrationRequest.id }">Approve</g:link> :: <g:link action="rejectRequest" id="${ registrationRequest.id }">Reject</g:link>
				</p>
			</div>
			
		</div>
		
		<div id="sidebar">
			
		</div>
	</body>
</html>