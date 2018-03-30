<!DOCTYPE html>

<html lang="en">

<body>
<#list cityList as city>

City: ${city.cityName}! <br>
Q:Why I like? <br>
A:<h1>${city.description}!</h1>

</#list>
</body>

</html>