<%@ page import="org.stevegood.user.User" %>

<ul>
	<li>
		<label for="username"><g:message code="user.username.label" default="Username" /></label>
		<g:textField name="username" value="${ params?.username }" />
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