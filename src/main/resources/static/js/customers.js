function loadCustomers() {
    $.get("customers", function (customers) {
        $("#customers_table").empty();
        $.each(customers, function (index, customer) {
            $("#customers_table").append(
                $('<tr/>').append(
                    $('<td>', {text: customer.id}),
                    $('<td>', {text: customer.firstName}),
                    $('<td>', {html: '<button name="choose_customer" class="btn btn-primary btn-sm" onclick="chooseCustomer(' + customer.id + ')">choose</button>'}),
                    $('<td>', {html: '<button name="delete_customer" class="btn btn-danger btn-sm" onclick="deleteCustomer(' + customer.id + ')">delete</button>'})
                )
            )
        });
    });
}

function loadProducts() {
    $.get("products", function (products) {
        $("#products_table").empty();
        $.each(products, function (index, product) {
            $("#products_table").append(
                $('<tr/>', {id: 'tr_' + product.id}).append(
                    $('<td/>', {text: product.id}),
                    $('<td/>', {class: 'product_name', text: product.name}),
                    $('<td/>', {class: 'product_price', text: product.price}),
                    $('<td/>', {html: '<button name="edit_product" class="btn btn-primary btn-sm" onclick="editProduct(' + product.id + ')">edit</button>'}),
                    $('<td/>', {html: '<button name="delete_product" class="btn btn-danger btn-sm" onclick="deleteProduct(' + product.id + ')">delete</button>'})
                )
            )
        });
    });
}

function buyProducts() {
    $('button[name=edit_product]').replaceWith(
        $('<button>', {
            name: "buy_product",
            class: "btn btn-info btn-sm",
            // onclick: "buyProduct('')",
            text: "buy"
        })
    );
    $("button[name=delete_product]").hide();
    $("#product_name_price").hide();
    $("#save_product").hide();
    $("#create_product_header").replaceWith(
        $('<button>', {
            name: "buy_all_products",
            class: "btn btn-success btn-lg",
            // onclick: "buyProduct('')",
            text: "Buy All"
        })
    );
}

function editProduct(productId) {
    $.get("products/" + productId, function (product) {
        $("#tr_" + productId + " .product_name").replaceWith(
            $('<td/>').append(
                $('<input>', {
                    name: "new_product_name",
                    type: "text",
                    class: "form-control",
                    value: product.name,
                    onchange: "$(this).attr('value', this.value)"
                })
            )
        );
        $("#tr_" + productId + " .product_price").replaceWith(
            $('<td/>').append(
                $('<input>', {
                    name: "new_product_price",
                    type: "number",
                    class: "form-control",
                    value: product.price,
                    onchange: "$(this).attr('value', this.value)"
                })
            )
        );
        $("#tr_" + productId + " button[name=edit_product]").replaceWith(
            $('<button>', {
                name: "save_button",
                class: "btn btn-primary btn-sm",
                onclick: "updateProduct('" + productId + "')",
                text: "save"
            })
        );
    });
}

function updateProduct(productId) {
    var productId = productId;
    var productName = $('#tr_'+ productId + ' input[name=new_product_name]').val();
    var productPrice = $('#tr_'+ productId + ' input[name=new_product_price]').val();
    var product = {
        id: productId,
        name: productName,
        price: productPrice
    };
    $.ajax({
        url: '/products/update',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(product)
    });
    setTimeout(loadProducts, 200);
}

function chooseCustomer(customerId) {
    var customer_table = $("#customers_table");
    $.get("customers/" + customerId, function (customer) {
        customer_table.empty();
        customer_table.append(
            $('<tr/>').append(
                $('<td>', {text: customer.id}),
                $('<td>', {text: customer.firstName})
            )
        )
    });
    $('#customer_name').hide();
    $('#save_customer').hide();
    $('#create_customer_header').hide();
    $('#table_action').hide();
    buyProducts();
}

function saveCustomer() {
    var customer = {
        firstName: $('#customer_name input[name=firstName]').val()
    };
    $.ajax({
        url: '/customers',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(customer)
    });
    setTimeout(loadCustomers, 200);
}

function saveProduct(productName, productPrice) {
    var product = {
        name: productName,
        price: productPrice
    };
    $.ajax({
        url: '/products',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(product)
    });
    setTimeout(loadProducts, 200);
}

function deleteCustomer(customerId) {
    $.get("/customers/delete/" + customerId);
    setTimeout(loadCustomers, 200);
}

function deleteProduct(productId) {
    $.get("/products/delete/" + productId);
    setTimeout(loadProducts, 200);
}