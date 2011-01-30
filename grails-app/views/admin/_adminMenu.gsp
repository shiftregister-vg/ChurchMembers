<ul>
	<li>
		<h2>Admin</h2>
		<ul>
			<li><g:link controller="admin">Admin Home</g:link></li>
		</ul>
	</li>
	
	<li>
		<h2>Users</h2>
		<ul>
			<sec:ifAllGranted roles="ROLE_SUPER_USER">
				<li><g:link action="addUser">Add</g:link></li>
				<li><g:link action="listUsers" controller="admin">List</g:link></li>
			</sec:ifAllGranted>
			<li><g:link action="listRejectedUsers" controller="admin">Rejected User Requests</g:link></li>
		</ul>
	</li>
	<li>
		<h2>Site Configuration</h2>
		<ul></ul>
	</li>
</ul>