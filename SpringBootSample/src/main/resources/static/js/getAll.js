function getAll() {

    alert(5);


    $.ajax(
        {
            URL: 'http://localhost:8080/api/film/get?id=1',
            type: 'GET',
            complete: function (data) {
                alert(JSON.stringify(data));
            }
        }
    );





}
