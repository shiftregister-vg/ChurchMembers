<%@ page import="org.stevegood.user.User" %>
<%@ page import="org.stevegood.user.Role" %>

<html>
	<head>
		<title>Admin - Edit User - ${user}</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		
		<div id="content">
			<g:form action="saveUser">
				<g:hiddenField name="id" value="${user?.id}" />
				<g:hiddenField name="version" value="${user?.version}" />
				
				<div class="post">
					<h2>Edit User</h2>
					<ul>
						<li class="value ${hasErrors(bean: user, field: 'username', 'errors')}">
							<label for="username"><g:message code="user.username.label" default="Username" /></label>
							<g:textField name="username" value="${user?.username}" />
						</li>
					</ul>
				</div>
				<div class="post">
					<h2>Roles</h2>
					<ul>
						<g:set var="authorities" value="${ user.getAuthorities().collect { it.authority } }" />
						<g:each in="${roleList}" var="role">
							<li>
								<g:checkBox name="${ role.authority }" value="${ true }" checked="${ authorities.contains(role.authority) }" />
								<label for="${ role.authority }">${role}</label>
							</li>
						</g:each>					
					</ul>
				</div>
				<div>
					<g:submitButton name="update" value="Update" />
				</div>
			</g:form>
		</div>
		
		<div id="sidebar">
			<g:render template="/admin/adminMenu" />
		</div>
		
	</body>
</html>