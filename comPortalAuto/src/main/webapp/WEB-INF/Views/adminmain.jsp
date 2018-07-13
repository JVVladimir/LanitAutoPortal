<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личная страница админа</title>
</head>
<body>
<B> Добро пожаловать! Пользователь, ${userName}!</B>
<P>   </P>
<form id="per" action="personalArea" enctype="text/plain" method="get">
    <div class="rendered-form">
        <div class="fb-button form-group field-button-1530786301839">
            <input type="submit" value="Личный кабинет">
        </div>
    </div>
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
<B> ${infoSearch} </B>
</body>
</html>
