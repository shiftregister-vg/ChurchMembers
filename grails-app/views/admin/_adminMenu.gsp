<ul>
	<li>
		<h2>Admin</h2>
		<ul>
			<li><g:link controller="admin">Admin Home</g:link></li>
		</ul>
	</li>
	<sec:ifAllGranted roles="ROLE_SUPER_USER">
	<li>
		<h2>Users</h2>
		<ul>
			<li><g:link action="addUser">Add</g:link></li>
			<li><g:link action="listUsers" controller="admin">List</g:link></li>
		</ul>
	</li>
	</sec:ifAllGranted>
	<li>
		<h2>Site Configuration</h2>
		<ul></ul>
	</li>
</ul>