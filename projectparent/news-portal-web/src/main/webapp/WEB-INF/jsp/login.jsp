<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@include file="login_style/css/main.css"%></style>
    <style><%@include file="login_style/css/util.css"%></style>
    <style><%@include file="login_style/vendor/daterangepicker/daterangepicker.css"%></style>
    <style><%@include file="login_style/vendor/select2/select2.min.css"%></style>
    <style><%@include file="login_style/vendor/animsition/css/animsition.min.css"%></style>
    <style><%@include file="login_style/vendor/css-hamburgers/hamburgers.min.css"%></style>
    <style><%@include file="login_style/vendor/animate/animate.css"%></style>
    <style><%@include file="login_style/fonts/font-awesome-4.7.0/css/font-awesome.min.css"%></style>
    <style><%@include file="login_style/vendor/bootstrap/css/bootstrap.min.css"%></style>

    
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="post">
					<span class="login100-form-title">
						Login
					</span>

                <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
                    <input class="input100" type="text" name="username" placeholder="Username">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate = "Please enter password">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="text-center p-t-14p-b-23" > <p style="color: #bd4147; padding-top: 10px">${errorString}</p></div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        Login
                    </button>
                </div>
                <ul class="text-md-left">
                    <li>Admin user - username: admin | password: admin</li>
                    <li>Author user - username: author | password: author</li>
                    <li>Author1 user - username: author1 | password: author1</li>
                    <li>Simple user - username: user | password: user</li>
                </ul>
                <div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
							Do not have an account?
						</span>

                    <a href="${pageContext.request.contextPath}/register" class="txt3">
                        Sign up now
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>