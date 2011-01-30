<%@ page import="org.stevegood.user.User" %>
<%@ page import="org.stevegood.registration.RegistrationRequest" %>

<html>
	<head>
		<title>Admin - Users List</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		
		<div id="content">
			
			<div class="post">
				<h2>Users List</h2>
				<ul>
					<g:each in="${userList}" var="user">
						<g:set var="registrationRequest" value="${ RegistrationRequest.findByUser(user) }" />
						<g:set var="isApproved" value="${ (registrationRequest && !registrationRequest?.approved) ? false : true }" />
						<li class="${ isApproved ? '' : 'not_approved'}">
							<g:if test="${ isApproved }">
								<g:link action="editUser" params="[username:user]">${ user }</g:link>
							</g:if>
							<g:else>
								<g:link controller="registration" action="processRequest" id="${ registrationRequest.id }">${ user }</g:link>
							</g:else>
						</li>
					</g:each>
				</ul>
			</div>
			
		</div>
		
		<div id="sidebar">
			<g:render template="/admin/adminMenu" />
		</div>
		
	</body>
</html>