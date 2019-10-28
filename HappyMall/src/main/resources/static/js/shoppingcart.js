/**
 * 
 */

$(document).ready(function() {
	formatMoney();
	
	$("#expiredDate").datepicker({
	    format: "mm-yyyy",
	    startDate: '+1m',
	    minViewMode: 1,
	    autoclose: true
	  });
	
	$("#groupCardType").change(function(){
		let type = $("input:radio[name='cardType']:checked").val();
		if(type == "Visacard")
		{
			$("#cardNumber").attr("pattern", "^4[0-9]{12}(?:[0-9]{3})?$");
			$("#cardNumber").attr("title", "It should be a Visa card's number.");
		}
		else
		{
			$("#cardNumber").attr("pattern", "^5[1-5]\d{14}$");
			$("#cardNumber").attr("title", "It should be a Master card's number.");
		}
	});
	
	$("#btnEditShippingAddress").click(function () {
		$id = "#" + $('#shippingId').html();
		if ($id != "#")
			$($id).prop("checked", true);
        $('#textEdit').text('shipping');
        $('#editShippingAddressModal').modal('show');
    });
	
	$("#btnEditBillingAddress").click(function () {
		$id = "#" + $('#billingId').html();
		if ($id != "#")
			$($id).prop("checked", true);
        $('#textEdit').text('billing');
        $('#editBillingAddressModal').modal('show');
    });
	
	$("#btnEditPaymentMethod").click(function () {
		$id = "#" + $('#cardId').html();
		if ($id != "#")
			$($id).prop("checked", true);
        $('#editPaymentMethodModal').modal('show');
    });
	
	$("#newShippingAddressForm").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        createShippingAddress();
    });
	
	$("#newBillingAddressForm").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        createBillingAddress();
    });
	
	$("#createCardForm").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        createCardDetail();
    });
	
	$(".guestQuantity").change(function(event) {
        event.preventDefault();
		var productId = $(this).attr('id');
		var quantity = $(this).val();
        $id = "#" + productId;
        $noti = "#" + productId + "_noti";
        $productQuantity = "#" + productId + "_quantity";
        if(quantity == $($productQuantity).text())
        	$($noti).attr("hidden", false);
        else
        	$($noti).attr("hidden", true);
        console.log("productId: " + productId);
        console.log("quantity: " + quantity);
        let url = "http://localhost:8080/shoppingcart/updateQuantity/guest/"  + productId + "/" + quantity;
        console.log("url: " + url);
		$.ajax({  
		      type: 'PUT',  
		      url :  url,
		      contentType: "application/json",
		      success: function(order){
		          console.log("Edit quantity success");
		          let ordertotal = order.total;
			    	  $('#totalBeforeTax').html(order.subTotal);
			    	  $('#estimatedTax').html(order.tax);
			    	  $('#orderTotal').html(order.total);
			    	  formatMoney();
		      },  
		      error: function(e){          
		      	alert('Error: ' + e);  
		      }  
		    });  
	});

	
	$(".enduserQuantity").change(function() {
		let orderLineId = $(this).attr('id');
		let quantity = $(this).val();
        $id = "#" + orderLineId;
        $noti = "#" + orderLineId + "_noti";
        $productQuantity = "#" + orderLineId + "_quantity";
        if(quantity == $($productQuantity).text())
        	$($noti).attr("hidden", false);
        else
        	$($noti).attr("hidden", true);
        console.log("orderLineId: " + orderLineId);
        console.log("quantity: " + quantity);
        let url = "http://localhost:8080/shoppingcart/updateQuantity/enduser/"  + orderLineId + "/" + quantity;
        console.log("url: " + url);
		$.ajax({  
		      type: 'PUT',  
		      url :  url,
		      contentType: "application/json",
		      success: function(order){
		          console.log("Edit quantity success");
		          let ordertotal = order.total;
			    	  $('#totalBeforeTax').html(order.subTotal);
			    	  $('#estimatedTax').html(order.tax);
			    	  $('#orderTotal').html(order.total);		        	  
			    	  formatMoney();
		      },  
		      error: function(e){        
		      	setInterval('location.reload()', 2000);
		      }  
		    });  
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

function createShippingAddress() {     
    console.log("Ajax: create shipping address...");
    let address = {};
    address.lineOne = $("#shippingLineOne").val();
    address.lineTwo = $("#shippingLineTwo").val();
    address.city = $("#shippingCity").val();
    address.state = $("#shippingState").val();
    address.zipcode = $("#shippingZipcode").val();
    console.log(address);
    
    $.ajax({  
      type: 'POST',  
      url : "http://localhost:8080/shoppingcart/createShippingAddress",
      contentType: "application/json",
      data: JSON.stringify(address),
      datatype: "json",
      success: function(){
    	setInterval('location.reload()', 2000);
      },  
      error: function(e){          
      	alert('Error: ' + e);  
      }  
    });  
}

function createBillingAddress() {     
    console.log("Ajax: create billing address...");
    let address = {};
    address.lineOne = $("#billingLineOne").val();
    address.lineTwo = $("#billingLineTwo").val();
    address.city = $("#billingCity").val();
    address.state = $("#billingState").val();
    address.zipcode = $("#billingZipcode").val();
    console.log(address);
    
    $.ajax({  
      type: 'POST',  
      url :  "http://localhost:8080/shoppingcart/createBillingAddress",
      contentType: "application/json",
      data: JSON.stringify(address),
      datatype: "json",
      success: function(){
    	setInterval('location.reload()', 2000);
      },  
      error: function(e){          
      	alert('Error: ' + e);  
      }  
    });  
}


function createCardDetail() {     
    console.log("Ajax: create cardDetail...");
    //var dataToSend = JSON.stringify($('#createCardForm').serializeFormJSON());
    let date = $("#expiredDate").val();
    let address = {};
    address.lineOne = $("#cardAddressLineOne").val();
    address.lineTwo = $("#cardAddressLineTwo").val();
    address.city = $("#cardAddressCity").val();
    address.state = $("#cardAddressState").val();
    address.zipcode = $("#cardAddressZipcode").val();
    
    let card = {};
    card.type = $("input:radio[name='cardType']:checked").val();
    card.nameOnCard = $("#nameOnCard").val();
    card.cardNumber = $("#cardNumber").val();
    card.expiredDate =  date.substring(3, 7) + "-" + date.substring(0, 2) + "-01";
    card.address = address;
    console.log(card);
    
    $.ajax({  
      type: 'POST',  
      url :  "http://localhost:8080/shoppingcart/createCardDetail",
      contentType: "application/json",
      data: JSON.stringify(card),
      datatype: "json",
      success: function(){
    	  setInterval('location.reload()', 500);
      },  
      error: function(e){          
      	alert('Error: ' + e);  
      }  
    });  
}