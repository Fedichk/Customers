function loadCustomers() {
    $.get('customers', function (customers) {
        $('#customers_table').empty();
        $.each(customers, function (index, customer) {
            $('#customers_table').append(
                $('<tr/>').append(
                    $('<td>', {class: "customer_id", text: customer.id}),
                    $('<td>', {class: "customer_first_name", text: customer.firstName}),
                    $('<td>', {html: '<button name="choose_customer" class="btn btn-primary btn-sm" onclick="chooseCustomer(' + customer.id + ')">choose</button>'}),
                    $('<td>', {html: '<button name="delete_customer" class="btn btn-danger btn-sm" onclick="deleteCustomer(' + customer.id + ')">delete</button>'}),
                    $('<td>', {html: '<button name="view_customer_orders" class="btn btn-info btn-sm" onclick="viewOrders(' + customer.id + ')">view</button>'})
                )
            )
        });
    });
}

function loadProducts() {
    $.get('products', function (products) {
        $('#products_table').empty();
        $.each(products, function (index, product) {
            $('#products_table').append(
                $('<tr/>', {id: 'tr_' + product.id}).append(
                    $('<td/>', {class: "product_id", text: product.id}),
                    $('<td/>', {class: "product_name", text: product.name}),
                    $('<td/>', {class: "product_price", text: product.price}),
                    $('<td/>', {html: '<button name="edit_product" class="btn btn-primary btn-sm" onclick="editProduct(' + product.id + ')">edit</button>'}),
                    $('<td/>', {html: '<button name="delete_product" class="btn btn-danger btn-sm" onclick="deleteProduct(' + product.id + ')">delete</button>'})
                )
            )
        });
    });
}

function viewOrders(customerId) {
    var customer_table = $('#customers_table');
$.get('orderdetails/' + customerId, function (details) {
        customer_table.empty();
        $.each(details, function (index, detail) {
            customer_table.append(
                $('<tr/>').append(
                    $('<td>', {class: "customer_id", text: (detail.product).name}),
                    $('<td>', {class: "customer_first_name", text: detail.count}),
                    $('<td>', {class: "customer_first_name", text: (detail.product).price}),
                    $('<td>', {class: "customer_first_name", text: (detail.order).id})
                )
            )
        });
        $('#customer_name').hide();
        $('#save_customer').hide();
        $('#create_customer_header').hide();
        $('#table_action').hide();
});
}

function buyProducts() {
    $('button[name=edit_product]').replaceWith(
        $('<button>', {
            name: "buy_product",
            class: "btn btn-info btn-sm",
            click: function () {
                var trId = $(this).closest('tr').prop('id');
                var count = parseInt($('#' + trId + ' div[name=product_count]').text());
                $('#' + trId + ' div[name=product_count]').text(++count);
                if (count > 0) {
                    $('#' + trId + ' button[name=delete_product]').prop('disabled', false);
                }
                sum();
            },
            text: "+"
        })
    );
    $('button[name=delete_product]').replaceWith(
        ($('<div>', {
            name: "product_count",
            width: "20",
            height: "30",
            style: "float:left;margin-right:40px;",
            text: 0
        })), ($('<button>', {
                name: "delete_product",
                class: "btn btn-danger btn-sm",
                disabled: "true",
                style: "float:left",
                click: function () {
                    var trId = $(this).closest('tr').prop('id');
                    var count = parseInt($('#' + trId + ' div[name=product_count]').text());
                    $('#' + trId + ' div[name=product_count]').text(--count);
                    if (count == 0) {
                        $('#' + trId + ' button[name=delete_product]').prop('disabled', true);
                    }
                    sum();
                },
                text: "-"
            })
        )
    );
    $('#product_name_price').hide();
    $('#save_product').hide();
    $('#create_product_header').replaceWith(
        ($('<button>', {
            name: "buy_all_products",
            class: "btn btn-success btn-lg",
            style: "float:left;margin-right:10px;",
            text: "Buy All",
            onclick: "buyAllProducts()"
        })),
        ($('<div>', {
                name: "total_price",
                width: "90",
                height: "45",
                style: "float:left; font-size:200%;",
                text: "Total price: 0"
            })
        )
    );
}

function buyAllProducts() {
    var customerId = parseInt($('#customers_table .customer_id').text());
    var orderId = 0;
    $('#products_table tr').each(function () {
        var trId = $(this).prop('id');
        var count = parseInt($('#' + trId + ' div[name=product_count]').text());
        if (count > 0) {
            var customer = {
                id: customerId
            };
            var product = {
                id: parseInt($('#' + trId + ' .product_id').text())
            };
            var order = {
                id: orderId,
                customer: customer
            };
            var orderDetails = {
                product: product,
                order: order,
                count: count
            };
            $.ajax({
                url: "/orderdetails",
                type: "post",
                async: false,
                contentType: "application/json",
                success: function (data) {
                    orderId = data;
                },
                data: JSON.stringify(orderDetails)
            });
        }
    });
}

function sum() {
    var sum = 0;
    $('#products_table tr').each(function () {
        var trId = $(this).prop('id');
        var tmp = parseInt($('#' + trId + ' .product_price').text()) * parseInt($('#' + trId + ' div[name=product_count]').text());
        sum = sum + tmp;
    });
    $('div[name=total_price]').text("Total price: " + sum);
}

function editProduct(productId) {
    $.get('products/' + productId, function (product) {
        $('#tr_' + productId + ' .product_name').replaceWith(
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
        $('#tr_' + productId + ' .product_price').replaceWith(
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
        $('#tr_' + productId + ' button[name=edit_product]').replaceWith(
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
    var productName = $('#tr_' + productId + ' input[name=new_product_name]').val();
    var productPrice = $('#tr_' + productId + ' input[name=new_product_price]').val();
    var product = {
        id: productId,
        name: productName,
        price: productPrice
    };
    $.ajax({
        url: "/products/update",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(product)
    });
    setTimeout(loadProducts, 200);
}

function chooseCustomer(customerId) {
    var customer_table = $('#customers_table');
    $.get('customers/' + customerId, function (customer) {
        customer_table.empty();
        customer_table.append(
            $('<tr/>').append(
                $('<td>', {class: "customer_id", text: customer.id}),
                $('<td>', {class: "customer_first_name", text: customer.firstName})
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
        firstName: $('#customer_name').find('input[name=firstName]').val()
    };
    $.ajax({
        url: "/customers",
        type: "post",
        contentType: "application/json",
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
        url: "/products",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(product)
    });
    setTimeout(loadProducts, 200);
}

function deleteCustomer(customerId) {
    $.get('/customers/delete/' + customerId);
    setTimeout(loadCustomers, 200);
}

function deleteProduct(productId) {
    $.get('/products/delete/' + productId);
    setTimeout(loadProducts, 200);
}