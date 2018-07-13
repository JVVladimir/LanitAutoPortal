<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет админа</title>
</head>
<body>
<p><b>Данные пользователя</b></p>
<form action="updateUserData" enctype="text/plain" method="get" name="Admin">
    <p>ФИО:&nbsp;<input maxlength="100" name="fio" type="text" required value="${fioSub}"></p>
    <p>Адрес:&nbsp;<input maxlength="100" name="addr" type="text" value="${addrSub}"></p>
    <p>Моб. телефон:&nbsp;<input maxlength="11" name="phone" required type="text" value="8${phoneSub}"></p>
    <p>Email:&nbsp;<input maxlength="50" name="email" type="text" value="${emailSub}"></p>
    <p>&nbsp; <input type="submit" value="Обновить"></p>
</form>
<form id="promo" action="showPromoCreation" enctype="text/plain" method="get">
    <p>&nbsp; <input type="submit" value="Создать объявление"></p>
</form>
<form id="rootDelete" action="deletePromo" enctype="text/plain" method="post">
    <p>Номер машины:&nbsp;<input maxlength="8" name="num" type="text" required-pattern="^[a-zA-Z0-9]+$"> ${info2} </p>
    <p>&nbsp; <input type="submit" value="Удалить объявление"></p>
</form>
<form id="rootBlock" action="blockUser" enctype="text/plain" method="post">
    <p>Логин пользователя:&nbsp;<input maxlength="50" name="login" type="text" placeholder="кого блокируем?" required-pattern="^[a-zA-Z0-9]+$"> ${info}</p>
    <p>&nbsp; <input type="submit" value="Забл/разбл пользователя"></p>
</form>
<form id="rootLogOut" action="logOut" enctype="text/plain" method="get">
    <p>&nbsp; <input type="submit" value="Выйти"></p>
</form>
</body>
</html>
