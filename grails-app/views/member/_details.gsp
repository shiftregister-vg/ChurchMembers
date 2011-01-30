<ul>
	<li>First Name: <strong>${ member.firstName }</strong></li>
	<li>Last Name: <strong>${ member.lastName }</strong></li>
	<li>Date of Birth: <strong>${ member.dob.format('MMMM dd, yyyy') }</strong></li>
	<li>Email: <strong>${ member.email }</strong></li>
	<g:if test="${ member?.spouse() }">
		<li>Spouse: <g:link controller="member" action="show" id="${ member?.spouse()?.id }">${ member?.spouse() }</g:link><strong></li>
	</g:if>
</ul>
<ul>
	<g:each in="${ member.phones }" var="phone">
		<li>${ phone }: <strong>${ phone.format() }</strong></li>
	</g:each>
	<g:each in="${ member.addresses }" var="address">
		<li>${ address }: <strong>${ address.format() }</strong></li>
	</g:each>
</ul>