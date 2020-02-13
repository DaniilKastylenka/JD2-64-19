<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <fmt:setBundle basename="messages"/>
    <title>News</title>
    <meta charset="UTF-8">
</head>
<body>
<%@include file="/WEB-INF/include/menu.jsp" %>

<h1><fmt:message key="home.page"/></h1>

<div style="word-wrap: break-word">Это проект студента it-academy - Костыленко Даниила. Проектом является новостной
    портал.
    На данный момент возможны такие действия:
</div>
<ul>
    <li>логин;</li>
    <li>логаут;</li>
    <li>регистрация под ролью обычного юзера, с шифрованием пароля;</li>
    <li>добавление юзеров под ролью "автор" через
        аккаунт админа, списиок всех юзеров и возможность их удалить;</li>
    <li>создание статей админом и авторами, их удаление и изменение; </li>
    <li>список всех статей,
        а также только тех, которые создал текущий автор;</li>
    <li>просмотр статьи на отдельной странице;  </li>
    <li>возможность оставить комментарий статье и удалить его;</li>
    <li>возможность поставить
        лайк;</li>
    <li>изменение пароля;</li>
    <li>удаление своего аккаунта.</li>
</ul>

<%@include file="/WEB-INF/include/footer.jsp" %>

</body>
</html>
