//load all customers to combo
function loadAllCustomersToCombo() {
    var ddlCusNames = document.getElementById("txtCustomerId");
    $.ajax({
        url: "http://localhost:8080/backEnd/customer?option=GETALL",
        method: "GET",
        // dataType:"json",
        success: function (resp) {
            for (const customer of resp.data) {
                var opt = customer.customerName;
                var el = document.createElement("option");

                el.textContent = opt;
                el.value = opt;

                ddlCusNames.appendChild(el);
            }
        }
    });
}

$("#txtCustomerId").click(function () {
    $.ajax({
        url: "http://localhost:8080/backEnd/customer?option=GETALL",
        method: "GET",
        // dataType:"json",
        success: function (resp) {
            for (const customer of resp.data) {
                if($("#txtCustomerId option:selected").text()==customer.customerId){
                    $("#txtCustomer").val(customer.customerName);
                    $("#txtSalary").val(customer.salary);
                    $("#txtAddress").val(customer.address);
                }
            }
        }
    });
})

//load all items to combo
function loadAllItemsToCombo(){
    var ddlItemCodes = document.getElementById("#selectItemCode");
    $.ajax({
        url: "http://localhost:8080/backEnd/item?option=GETALL",
        method: "GET",
        // dataType:"json",
        success: function (resp) {
            for (const item of resp.data) {
                var opt = item.itemCode;
                var el = document.createElement("option");

                el.textContent = opt;
                el.value = opt;

                ddlItemCodes.appendChild(el);
            }
        }
    });
}

$("#selectItemCode").click(function () {
    $.ajax({
        url: "http://localhost:8080/backEnd/customer?option=GETALL",
        method: "GET",
        // dataType:"json",
        success: function (resp) {
            for (const item of resp.data) {
                if($("#selectItemCode option:selected").text()==item.itemCode){
                    $("#txtItemDescription").val(item.itemName);
                    $("#txtItemUnitPrice").val(item.itemPrice);
                    $("#txtQTYOnHand").val(item.itemQty);
                }
            }
        }
    });
})