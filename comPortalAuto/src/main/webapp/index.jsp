<%--
  Главный сайт с поиском, регистрацией и авторизацией пользователей
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Портал объявлений HSEAuto</title>
</head>
<body>
<H1> Главная страница портала! Привет, пользователь! </H1>
<form action="checkAccount" enctype="text/plain" id="account" method="post" name="Account">
    <p>
    <H3>Войдите или зарегистрируйтесь</H3></p>
    <p>Логин: &nbsp;<input maxlength="100" name="login" type="text" required pattern="^[a-zA-Z0-9]+$"></p>
    <p>Пароль: &nbsp;<input maxlength="50" name="password" type="password" required pattern="^[a-zA-Z0-9]+$"></p>
    <input type="submit" value="Войти">
</form>
<form action="registration" enctype="text/plain" id="reg" method="get" name="Registration">
    <input type="submit" value="Зарегистрироваться">
</form>
<form id="search" action="searchPromo" enctype="text/plain" method="get">
    <div class="rendered-form">
        <div class="">
            <h2 id="control-4978634">Начните поиск объявления</h2>
        </div>
        <div class="fb-text form-group field-text-1530785999997">
            Стоимость от: <input type="number" name="costFrom" placeholder="Цена от" maxlength="12" min = "0" max = "99999999">
        </div>
        <div class="fb-text form-group field-text-1530786074413">
            Стоимость до: <input type="number" name="costTo" placeholder="Цена до" maxlength="12" min = "0" max = "99999999">
        </div>
        <div class="fb-text form-group field-text-1530786126294">
            Марка авто: <input type="text" name="mark" maxlength="12" pattern="^[a-zA-Z0-9]+$">
        </div>
        <div class="fb-text form-group field-text-1530786151326">
            Модель авто: <input type="text" name="model" maxlength="12" pattern="^[a-zA-Z0-9]+$">
        </div>
        <div class="fb-text form-group field-text-1530786209446">
            Мощность (л/с): <input type="number" name="power" maxlength="5" min = "0" max = "9999">
        </div>
        <div class="fb-date form-group field-date-1530786347767">
            Год выпуска авто: <input type="date" name="date" placeholder="1980-10-10">
        </div>
        <div class="fb-button form-group field-button-1530786301839">
            <input type="submit" value="Поиск">
        </div>
    </div>
</form>
</body>
</html>
