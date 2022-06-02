/*Save Customer click event*/
$("#btnSaveItem").click(function () {
    saveItem();
    loadAllItems();
    clearAll();

})

function saveItem() {
    var data = $("#itemForm").serialize();
    $.ajax({
        url: "http://localhost:8080/backEnd/item",
        method: "POST",
        data: data,
        success: function (res) {
            if (res.status == 200) {
                alert(res.message);
                loadAllItems();
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

// search customer
$("#btnSearchItem").click(function () {
    searchItem();
});

$("#btnUpdateItem").click(function () {
    updateItems();
});

$("#btnDeleteItem").click(function () {
    deleteItems();
})


function searchItem() {

    let itemCode = $("#txtSearchItemCode").val();
    $.ajax({
        url: "http://localhost:8080/backEnd/item?option=SEARCH&itemCode="+ itemCode,
        method: "GET",
        success: function (resp) {
            $("#txtItemCode").val(resp.itemCode);
            $("#txtItemName").val(resp.itemName);
            $("#txtQty").val(resp.itemQty);
            $("#txtItemPrice").val(resp.itemPrice);

        },
        error: function (ob, statusText, error) {
            alert("No such Item");
        }
    });

}

/*Reset Customer*/
$("#btnResetItem").click(function () {
    loadAllItems();
})


function loadAllItems() {
    $("#itemTable").empty();
    $.ajax({
        url: "http://localhost:8080/backEnd/item?option=GETALL",
        method: "GET",
        // dataType:"json",
        success: function (resp) {
            for (const item of resp.data) {
                let row = `<tr><td>${item.itemCode}</td><td>${item.itemName}</td><td>${item.itemQty}</td><td>${item.itemPrice}</td></tr>`;
                $("#itemTable").append(row);
            }
        }
    });



}

function clearAll() {
    $("#txtItemCode").val("");
    $("#txtItemName").val("");
    $("#txtQty").val("");
    $("#txtItemPrice").val("");

    $("#txtItemCode").focus();
}

/*Delete Customer*/
function deleteItems(){

    let itemCode = $("#txtItemCode").val();
    $.ajax({
        url: "http://localhost:8080/backEnd/item?itemCode=" + itemCode,
        method: "DELETE",
        //data:data,// application/x-www-form-urlencoded
        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                loadAllItems();
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

}


function updateItems() {

    var item={
        itemCode: $("#txtItemCode").val(),
        itemName: $("#txtItemName").val(),
        itemQty: $("#txtQty").val(),
        itemPrice: $("#txtItemPrice").val()
    }

    $.ajax({
        url: "http://localhost:8080/backEnd/item",
        method: "PUT",
        data: JSON.stringify(item),
        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                loadAllItems();
                clearAll();
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
