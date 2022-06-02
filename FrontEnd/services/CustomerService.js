/*Save Customer click event*/
$("#btnSaveCustomer").click(function () {
    saveCustomer();
    loadAllCustomers();
    clearAll();


})

function saveCustomer() {
    var data = $("#customerForm").serialize();
    $.ajax({
        url: "http://localhost:8080/backEnd/customer",
        method: "POST",
        data: data,
        success: function (res) {
            if (res.status == 200) {
                alert(res.message);
                loadAllCustomers();
            } else {
                alert(res.data);
            }

        },
        error: function (ob, textStatus, error) {
            console.log(ob);
            console.log(textStatus);
            console.log(error);
        }
    });


    // let customerId = $("#txtCusID").val();
    // let customerName = $("#txtCusName").val();
    // let customerAddress = $("#txtCusAddress").val();
    // let customerSalary = $("#txtCusSalary").val();
    // console.log(customerId)
    //
    // var customer = new Customer(customerId,customerName,customerAddress,customerSalary);
    // customerDB.push(customer);
    // console.log(customerDB.length);
}

// search customer
$("#btnSearch").click(function () {
    searchCustomer();
});

$("#btnUpdateCustomer").click(function () {
    updateCustomer();
});

$("#btnDeleteCustomer").click(function () {
    deleteCustomer();
})


function searchCustomer() {

    let customerId = $("#txtSearchCusID").val();
    $.ajax({
        url: "http://localhost:8080/backEnd/customer?option=SEARCH&customerId="+ customerId,
        method: "GET",
        success: function (resp) {
            $("#customerId").val(resp.customerId);
            $("#cusName").val(resp.customerName);
            $("#cusAddress").val(resp.address);
            $("#cusSalary").val(resp.salary);

        },
        error: function (ob, statusText, error) {
            alert("No such customer");
        }
    });

}

/*Reset Customer*/
$("#btnResetCustomer").click(function () {
    loadAllCustomers();
})


function loadAllCustomers() {
    $("#customerTable").empty();
    $.ajax({
        url: "http://localhost:8080/backEnd/customer?option=GETALL",
        method: "GET",
        // dataType:"json",
        success: function (resp) {
            for (const customer of resp.data) {
                let row = `<tr><td>${customer.customerId}</td><td>${customer.customerName}</td><td>${customer.address}</td><td>${customer.salary}</td></tr>`;
                $("#customerTable").append(row);
            }
        }
    });
}

function clearAll() {
    $("#txtCusID").val("");
    $("#txtCusName").val("");
    $("#txtCusAddress").val("");
    $("#txtCusSalary").val("");

    $("#txtCusID").focus();
}

/*Delete Customer*/
function deleteCustomer(){

    let customerID = $("#customerId").val();
    $.ajax({
        url: "http://localhost:8080/backEnd/customer?customerId=" + customerID,
        method: "DELETE",
        //data:data,// application/x-www-form-urlencoded
        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                loadAllCustomers();
            } else if (res.status == 400) {
                alert(res.data);
            } else {
                alert(res.data);
            }

        },
        error: function (ob, status, t) {
            console.log(ob);
            console.log(status);
            console.log(t);
        }
    });


    // $("#customerTable").on('click','#btnDlt',function(e){
    //     selectedRow = td.parentElement.parentElement;
    //     console.log(selectedRow.cells[0].innerHTML);
    //
    //     var index=-1;
    //     for (var i = 0; i < customerDB.length; i++) {
    //         if (customerDB[i].getCusId() == $.trim(selectedRow.cells[0].innerHTML)) {
    //             index=i;
    //         }
    //     }
    //     customerDB.splice(index,1);
    //     loadAllCustomers();
    //     clearAll();
    // });
}


//var customerId = null;
/*Update Customer  */
// function onEdit(td) {
//     selectedRow = td.parentElement.parentElement;
//     customerId=selectedRow.cells[0].innerHTML;
//
//     document.getElementById("txtCusID").value = selectedRow.cells[0].innerHTML;
//     document.getElementById("txtCusName").value = selectedRow.cells[1].innerHTML;
//     document.getElementById("txtCusAddress").value = selectedRow.cells[2].innerHTML;
//     document.getElementById("txtCusSalary").value = selectedRow.cells[3].innerHTML;
//
//     $("#txtCusID").attr('disabled',true);
// }

$("#btnUpdateCustomer").click(function () {
    updateCustomer();
})

function updateCustomer() {
    // let customerName = $("#txtCusName").val();
    // let customerAddress = $("#txtCusAddress").val();
    // let customerSalary = $("#txtCusSalary").val();
    //
    // for (var i = 0; i < customerDB.length; i++) {
    //     if(customerId==customerDB[i].getCusId()){
    //         customerDB[i].setCusId(customerId);
    //         customerDB[i].setCusName(customerName);
    //         customerDB[i].setCusAddress(customerAddress);
    //         customerDB[i].setCusSalary(customerSalary);
    //     }
    // }
    // $("#txtCusID").attr('disabled',false);
    // loadAllCustomers();

    var customer={
        customerId: $("#customerId").val(),
        customerName: $("#cusName").val(),
        address: $("#cusAddress").val(),
        salary: $("#cusSalary").val()
    }

    $.ajax({
        url: "http://localhost:8080/backEnd/customer",
        method: "PUT",
        data: JSON.stringify(customer),
        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                loadAllCustomers();
                clearCustomer();
            } else if (res.status == 400) {
                alert(res.message);
            } else {
                alert(res.data);
            }

        },
        error: function (ob, textStatus, error) {
            console.log(ob);
            console.log(textStatus);
            console.log(error);
        }
    });

}
