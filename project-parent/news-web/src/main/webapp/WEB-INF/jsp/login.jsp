<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <title><fmt:message key="login.login"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
        <%@include file="login_style/css/main.css" %>
        <%@include file="login_style/css/util.css" %>
        <%@include file="login_style/vendor/daterangepicker/daterangepicker.css" %>
        <%@include file="login_style/vendor/select2/select2.min.css" %>
        <%@include file="login_style/vendor/animsition/css/animsition.min.css" %>
        <%@include file="login_style/vendor/css-hamburgers/hamburgers.min.css" %>
        <%@include file="login_style/vendor/animate/animate.css" %>
        <%@include file="login_style/fonts/Linearicons-Free-v1.0.0/icon-font.min.css" %>
        <%@include file="login_style/fonts/font-awesome-4.7.0/css/font-awesome.min.css" %>
        <%@include file="login_style/vendor/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90">
            <form class="login100-form validate-form flex-sb flex-w" method="post">
					<span class="login100-form-title p-b-51">
						<fmt:message key="login.login"/>
					</span>


                <div class="wrap-input100 validate-input m-b-16" data-validate="Username is required">
                    <input class="input100" type="text" name="username"
                           placeholder="<fmt:message key="login.username"/>">
                    <span class="focus-input100"></span>
                </div>


                <div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
                    <input class="input100" type="password" name="password"
                           placeholder="<fmt:message key="login.password"/>">
                    <span class="focus-input100"></span>
                </div>

                <p style="color: #ff0010">${errorString}</p>

                <div class="flex-sb-m w-full p-t-3 p-b-24">
                    <div>
                        <a><fmt:message key="login.no.account"/></a>
                        <a href="${pageContext.request.contextPath}/register" class="txt1">
                            <fmt:message key="login.sign.up"/>
                        </a>
                    </div>
                </div>

                <div class="container-login100-form-btn m-t-17">
                    <button class="login100-form-btn">
                        <fmt:message key="login.login"/>
                    </button>
                </div>

            </form>
            <table border="1px">
                <tr>
                    <th>Role</th>
                    <th>Username</th>
                    <th>Password</th>
                </tr>
                <tr>
                    <td>SimpleUser</td>
                    <td>User</td>
                    <td>User</td>
                </tr>
                <tr>
                    <td>Author</td>
                    <td>Author</td>
                    <td>Author</td>
                </tr>
                <tr>
                    <td>Admin</td>
                    <td>Admin</td>
                    <td>Admin</td>
                </tr>
            </table>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>

</body>
</html>
