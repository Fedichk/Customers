function loadCustomers() {
    $.get("customers", function (response) {
        $("#content").empty();
        $.each(response, function (index, customer) {
            $("#content")
                .append("<tr>")
                .append($("<td>").text(customer.id))
                .append($("<td>").text(customer.firstName))
                .append($("<td>").html('<button id="delete_customer" class="btn btn-danger btn-sm" onclick="deleteCustomer(' + customer.id + ')">delete</input>'))
                .append("</tr>")
        });
    });
}

function saveCustomer() {
    var content = $('#customer_name').find("input[name='firstName']").val();
    $.post("/customers", {
        firstName: content
    }, "json");
    setTimeout(loadCustomers,200);
}

function deleteCustomer(customerId) {
    $.get("/customers/delete/" + customerId);
    setTimeout(loadCustomers,200);
}