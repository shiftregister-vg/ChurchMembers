<html>
<head>
    <title>Change Password</title>
    <meta name="layout" content="main" />
</head>
<body>
	<div id="content">
	    <div class="post">
	    	<h2>${ user }</h2>
	    </div>
	    <form id="changePasswordForm" method="post" action="${ createLink(action:'edit') }">
	        <g:render template="/user/changePasswordFields" />
	        <input type="submit" value="Save" />
	    </form>
	</div>
	
	<div id="sidebar"></div>
</body>
</html>