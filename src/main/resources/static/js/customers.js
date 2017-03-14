function saveCustomer() {
    var content = $('#customer_name').find("input[name='firstName']").val();
    $.post("/customers", {
        firstName: content
    }, "json");
}