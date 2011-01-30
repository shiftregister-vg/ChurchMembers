<html>
	<head>
		<title>Admin - Rejected User Registrations</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		<div id="content">
			
			<div class="post">
				<h2>Rejected User Registrations</h2>
				<ul>
					<g:each in="${ registrationRequests }" var="registrationRequest">
						<li>${ registrationRequest.user } ( ${ registrationRequest.dateCreated.format('MM/dd/yyyy') } ) - <g:link controller="registration" action="approveRequest" id="${ registrationRequest.id }">Approve</g:link> :: <g:link controller="registration" action="deleteRequest" id="${ registrationRequest.id }">Delete</g:link></li>
					</g:each>
				</ul>
			</div>
			
		</div>
		
		<div id="sidebar">
			<g:render template="/admin/adminMenu" />
		</div>
	</body>
</html>