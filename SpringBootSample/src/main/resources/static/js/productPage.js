$(function () {

    $.ajax({
        url: '/api/products/all',
        type: 'get',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (product) {
            for (let i = 0; i < product.length; i++) {
                $("#forAdd").before("<tr>" +
                    "<td>" + product[i].id + "</td>" +
                    "<td>" + product[i].name + "</td>" +
                    "<td>" + product[i].price + "</td>" +
                    "<td>" + product[i].amount + "</td>" +
                    "<td>" +
                    "<button class=\"btn btn-info\" data-target=\"#editProduct\" data-toggle=\"modal\" onclick=\"EditProduct("+product[i].id+")\">Edit</button>" +
                    "<button class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#BuyProduct\" onclick=\"BuyProduct("+product[i].id+")\">Buy</button>" +
                    "</td>" +
                    "</tr>")
            }
        },
        error: function (response) {
            alert('error');
            console.log(response);
        }
    });


});

function EditProduct(id) {
    //сгенерировать модальное окно

    $.ajax({
        url: '/api/products/get?id='+id,
        type: 'get',
        contentType: 'application/json; charset=utf-8',
       success: function (product) {
           $("#edit-id").val(product.id);
           $("#edit-Name").val(product.name);
           $("#edit-Price").val(product.price);
           $("#edit-Amount").val(product.amount);
       }
    });

}
function SendEditData() {
    //сгенерировать модальное окно
    var id=$("#edit-id").val();
    var name=$("#edit-Name").val();
    var price=$("#edit-Price").val();
    var amount=$("#edit-Amount").val();

    var editProduct={
        'id': id,
        'name':name,
        'price':price,
        'amount':amount
    }
    $.ajax({
        //запихиваем инфу в нужные поля

        url: '/api/products/add',
        method: 'post',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(editProduct),
        success: function () {
            window.location.replace("/products/all")
        },
        error: function (error) {

        }

    });

}
function BuyProduct(id) {
    //сгенерировать модальное окно

    $.ajax({
        url: '/api/products/get?id='+id,
        type: 'get',
        contentType: 'application/json; charset=utf-8',
        success: function (product) {
            $("#buy-id").val(product.id);
            $("#buy-amount").val(product.amount);
        }
    });

}
function buyProduct(id) {
    var id=$("#buy-id").val();
    var amount=$("#buy-Amount").val();

    var buyProduct={
        'id': id,
        'amount':amount-1,
    }
    $.ajax({
        //запихиваем инфу в нужные поля

        url: '/api/products/add',
        method: 'post',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(buyProduct),
        success: function () {
            window.location.replace("/products/all")
        },
        error: function (error) {

        }

    });
}
function AddProduct() {
    //сгенерировать модальное окно
    var name=$("#name").val();
    var price=$("#price").val();
    var amount=$("#amount").val();

    var newProduct={
        'name':name,
        'price':price,
        'amount':amount
    }
    $.ajax({
        //запихиваем инфу в нужные поля
        url: '/api/products/add',
        method: 'post',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(newProduct),
        success: function () {
            window.location.replace("/products/all")
        },
        error: function (error) {

        }

    });
       
}
