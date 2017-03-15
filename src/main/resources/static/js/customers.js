function loadCustomers() {
    // $("#save_customer").click(function () {
        $.get("/customers", function (response) {
            console.log(response)
            $("#content").empty();
            $.each(response, function (customer) {
                $("#content")
                    .append("<tr>")
                    .append($("<td>").text(customer.id))
                    .append($("<td>").text(customer.firstName))
            });
        });
    // });
}

function saveCustomer() {
    var content = $('#customer_name').find("input[name='firstName']").val();
    $.post("/customers", {
        firstName: content
    }, "json");
}