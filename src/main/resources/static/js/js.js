var service = 'localhost:8099';

$(document).ready(function () {

    jQuery.support.cors = true;

    $.ajax({
        type: "GET",
        url: 'localhost:8099/getall',
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        cache: false,
        success: function (data) {

            var trHTML = '';
            console.log(data);
            $.each(data, function (i, item) {

                trHTML += '<tr><td>' + item.id+ '</td><td>' + item.lastName + '</td></tr>';
            });

            $('#employeesTable').append(trHTML);

        },

        error: function (msg) {

            alert(msg.responseText);
        }
    });
});