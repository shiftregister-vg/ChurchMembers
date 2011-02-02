<select name="memberid">
	<option value="0">Select Member...</option>
	<optgroup label="---------------">
		<g:each in="${ membersList }" var="member">
			<option value="${ member.id }">${ member }</option>
		</g:each>
	</optgroup>
</select>
<g:submitButton name="link" value="Link" />