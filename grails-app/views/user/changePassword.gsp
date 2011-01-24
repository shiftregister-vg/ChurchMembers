<html>
<head>
    <title>Change Password</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h1>Change Password for ${user}</h1>
    <form id="changePasswordForm" method="post" action="${ createLink(action:'edit') }">
        <input type="password" name="password" />
        <input type="password" name="password2" />
        <input type="submit" value="Submit" />
    </form>
</body>
</html>