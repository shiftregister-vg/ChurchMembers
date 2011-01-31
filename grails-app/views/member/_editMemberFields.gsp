<ul>
	<li>
		<label for="firstName">First Name</label>
		<g:textField name="firstName" value="${ member?.firstName }" />
	</li>
	<li>
		<label for="lastName">Last Name</label>
		<g:textField name="lastName" value="${ member?.lastName }" />
	</li>
	<li>
		<label for="email">Email</label>
		<g:textField name="email" value="${ member?.email }" />
	</li>
	<li>
		<label for="dob">Date of Birth</label>
		<g:datePicker name="dob" value="${ member?.dob }" precision="day" />
	</li>
	<li>
		<label for="spouse">Spouse</label>
		<g:select name="spouse" from="${ memberList }" optionKey="id" value="${memberList*.id}" />
	</li>
</ul>
