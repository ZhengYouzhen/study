function aa() {
    alert(1);
    $.get("/api/city/1",
        function (data) {
            $("#test").text(data.cityName);
        }
    );
}