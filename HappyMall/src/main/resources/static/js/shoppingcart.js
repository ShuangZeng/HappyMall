/**
 * 
 */

$(document).ready(function() {
	formatMoney();
	//calPrice();	
	
	$("#btnEditShippingAddress").click(function () {
		$id = "#" + $('#shippingId').html();
        $($id).prop("checked", true);
        $('#textEdit').text('shipping');
        $('#editShippingAddressModal').modal('show');
    });
	
	$("#btnEditBillingAddress").click(function () {
		$id = "#" + $('#billingId').html();
        $($id).prop("checked", true);
        $('#textEdit').text('billing');
        $('#editBillingAddressModal').modal('show');
    });
	
	$("#btnEditPaymentMethod").click(function () {
		$id = "#" + $('#cardId').html();
        $($id).prop("checked", true);
        $('#editPaymentMethodModal').modal('show');
    });
	
});

// format money
function formatMoney() {
    $(".format-money").each( (i, val) => {
        console.log("format-money", $(val).text());
        let price = parseFloat($(val).text().replace("$","")).toFixed(2);
        $(val).text("$" + price);
    });
}

