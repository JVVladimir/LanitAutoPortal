<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Страница регистрации</title>
</head>
<body>
<p>
<H1><b>Регистрация нового пользователя</b></H1> </p>
<form action="main" enctype="text/plain" id="account" method="post" name="Account">
    <p><B>Для ввода логина и пароля используйте латинские буквы и цифры без пробелов</B></p>
    <p>Логин:* &nbsp;<input maxlength="100" name="login" type="text" required pattern="^[a-zA-Z0-9]+$"> ${infoLogin} </p>
    <p>Пароль:* &nbsp;<input maxlength="50" name="password" type="password" required pattern="^[a-zA-Z0-9]+$"> </p>
    <p>Повторите пароль:* &nbsp;<input maxlength="50" name="repPassword" type="password" required pattern="^[a-zA-Z0-9]+$"> ${infoPasRep}</p>
    <hr>
    <p>ФИО:* &nbsp;<input maxlength="200" name="fio" type="text" required> </p>
    <p>Адрес: &nbsp;<input maxlength="200" name="addr" type="text"> </p>
    <p>Моб. телефон:* &nbsp;<input maxlength="11" name="phone" type="tel" placeholder="+7" required pattern="[0-9]{10}"> </p>
    <p>Email: &nbsp;<input maxlength="50" name="email" type="email"> </p>
    <p>&nbsp; <input type="submit" value="Зарегистрироваться"></p>
</form>
</body>
</html>