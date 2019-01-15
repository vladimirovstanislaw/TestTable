$(document).ready(function () {
    var basicUrl = "http://localhost:8099";
    var user = "admin";
    var password = "admin";
    var OAuth = user + ":" + password;
    var stompClient = null;
    connect();
    var table = $('#personTable').DataTable({
        "sAjaxSource": "/getall",
        "sAjaxDataProp": "",

        "aoColumns": [{
                "mData": "id"
            },
            {
                "mData": "lastName"
            },
            {
                "mData": "firstName"
            },
            {
                "mData": "middleName"
            },
            {
                "mData": "birthDate"
            },
            {
                "mData": "comment"
            },
            {
                "mData": "updateDate"
            }
        ]
    });
    
    function connect() {
        var socket = new SockJS('localhost:8099/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                showGreeting(JSON.parse(greeting.body).content);
            });
        });
    }

    function showGreeting(message) {
        alert(message);
        console.log(message);
    }

    function sendName() {
        stompClient.send("/app/hello", {}, JSON.stringify({'id': 'buddy'}));
    }
    $('#personTable tbody').on('click', 'tr', function () {
        $(this).toggleClass('selected');
    });

    $('#refresh').click(function () {
        table.ajax.reload();
    });

    $('#getById').click(function () {
        var $form = $("#addForm"),

            id = $form.find('input[name="id"]').val()
        if (id === '') {
            console.log("kira");
        } else {
            var getperson;
            //$form.find('input[name="'+key+'"]').val("2");
            $.getJSON(basicUrl + "/get/" + id, function (data) {
                //var items = [];
                if (data == null) {
                    alert("There is no Person with that id");
                } else {
                    $.each(data, function (key, val) {
                        $form.find('input[name="' + key + '"]').val(val);
                    });
                }
            });
        }
    });
    $('#updateRow').click(function () {
        
        var $form = $("#addForm"),

            id = $form.find('input[name="id"]').val(),
            lastName = $form.find('input[name="lastName"]').val(),
            firstName = $form.find('input[name="firstName"]').val(),
            middleName = $form.find('input[name="middleName"]').val(),
            birthDate = $form.find('input[name="birthDate"]').val()
        var person = {
            id: id,
            lastName: lastName,
            firstName: firstName,
            middleName: middleName,
            birthDate: birthDate
        };
        $.ajax({
            type: "PUT",
            url: basicUrl + "/update",
            contentType: "application/json",
            data: JSON.stringify(person),
            success: function (response) {
                alert(response);
            }
        });
        setTimeout(function () {
            table.ajax.reload()
        }, 100);

    });
    $('#deleteById').click(function () {
        var $form = $("#addForm"),

            id = $form.find('input[name="id"]').val()
        if (id === '') {
            console.log("kira");
        } else {
            $.ajax({
                type: "DELETE",
                url: basicUrl + "/deleteone/" + id,
                contentType: "application/json",
                success: function (response) {
                    alert(response);
                },

                error: function (xhr, ajaxOptions, thrownError) {
                    alert("Code: " + xhr.status + "  " + xhr.responseText);
                }
            });
        }
        setTimeout(function () {
            table.ajax.reload()
        }, 100);
    });
    $("#addForm").trigger('reset');
    setTimeout(function () {
        table.ajax.reload()
    }, 150);

    $('#updateRows').click(function () {
        var i;
        var array = [];
        for (i = 0; i < table.rows('.selected').data().length; i++) {
            array.push(table.rows('.selected').data()[i].id);
        }
        if (array.length > 0) {
            console.log("Running");
            $.ajax({
                type: "PUT",
                url: basicUrl + "/updateSomePersons",
                contentType: "application/json",
                data: JSON.stringify(array)
            });
        }
        setTimeout(function () {
            table.ajax.reload()
        }, 100);

    });
    $("#addForm").submit(function (event) {
        event.preventDefault();
        sendName();
        /* get some values from elements on the page: */
        //'{"lastName":"Samwise","firstName":"Samwise","birthDate":"","comment":"Samwise","updateDate":""}'
        var $form = $(this),
            url = $form.attr('action'),

            id = $form.find('input[name="id"]').val(),
            lastName = $form.find('input[name="lastName"]').val(),
            firstName = $form.find('input[name="firstName"]').val(),
            middleName = $form.find('input[name="middleName"]').val(),
            birthDate = $form.find('input[name="birthDate"]').val()
        var person = {
            lastName: lastName,
            firstName: firstName,
            middleName: middleName,
            birthDate: birthDate
        };

        $.ajax({
            type: "POST",
            url: basicUrl + url,
            contentType: "application/json",
            data: JSON.stringify(person)
        });
        $("#addForm").trigger('reset');
        setTimeout(function () {
            table.ajax.reload()
        }, 100);
    });

});