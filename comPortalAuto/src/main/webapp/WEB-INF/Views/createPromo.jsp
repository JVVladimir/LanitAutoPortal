<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание объявления</title>
</head>
<body>
<form id="create" action="createPromo" enctype="text/plain" method="get">
    <div class="rendered-form">
        <div class="">
            <h2 id="control-4978634">Создайте объявление о продаже авто</h2>
        </div>
        <div class="fb-text form-group field-text-1530785999997">
            Стоимость: <input type="number" name="cost" placeholder="Цена в рублях" required maxlength="12" min = "0" max = "99999999">
        </div>
        <div class="fb-text form-group field-text-1530786126294">
            Марка авто: <input type="text" name="mark" maxlength="12" required-pattern="^[a-zA-Z0-9]+$">
        </div>
        <div class="fb-text form-group field-text-1530786151326">
            Модель авто: <input type="text" name="model" maxlength="12" required-pattern="^[a-zA-Z0-9]+$">
        </div>
        <div class="fb-text form-group field-text-1530786209446">
            Мощность (л/с): <input type="number" name="power" maxlength="5" min = "0" max = "9999">
        </div>
        <div class="fb-date form-group field-date-1530786347767">
            Год выпуска авто: <input type="date" name="date" placeholder="1980-10-10">
        </div>
        <div class="fb-text form-group field-text-1530785999997">
            Регистрационный номер: <input type="text" name="num" maxlength="8" required-pattern="^[a-zA-Z0-9]+$"> <per><B>    ${infoNum} </B> </per>
        </div>
        <div class="fb-text form-group field-text-1530786074413">
            Дополнительная информация: <input type="text" maxlength="150" name="info">
        </div>
        <div class="fb-button form-group field-button-1530786301839">
            <input type="submit" value="Создать">
        </div>
    </div>
</form>
</body>
</html>
