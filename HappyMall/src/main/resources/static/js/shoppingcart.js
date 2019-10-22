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
	
	$(".guestQuantity").change(function() {
		var productId = $(this).attr('id');
		var quantity = $(this).val();
        console.log("productId: " + productId);
        console.log("quantity: " + quantity);
		$.ajax({  
		      type: 'POST',  
		      url :  window.location + "/updateQuantity/guest/"  + productId + "/" + quantity,
		      contentType: "application/json",
		      success: function(order){
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
		var orderLineId = $(this).attr('id');
		var quantity = $(this).val();
        console.log("orderLineId: " + orderLineId);
        console.log("quantity: " + quantity);
		$.ajax({  
		      type: 'PUT',  
		      url :  window.location + "/updateQuantity/enduser/"  + orderLineId + "/" + quantity,
		      contentType: "application/json",
		      success: function(orderUpdate){
		          console.log("update success");
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
    var address = {};
    address.lineOne = $("#shippingLineOne").val();
    address.lineTwo = $("#shippingLineTwo").val();
    address.city = $("#shippingCity").val();
    address.state = $("#shippingState").val();
    address.zipcode = $("#shippingZipcode").val();
    console.log(address);
    
    $.ajax({  
      type: 'POST',  
      url :  window.location + "/createShippingAddress",
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
    var address = {};
    address.lineOne = $("#billingLineOne").val();
    address.lineTwo = $("#billingLineTwo").val();
    address.city = $("#billingCity").val();
    address.state = $("#billingState").val();
    address.zipcode = $("#billingZipcode").val();
    console.log(address);
    
    $.ajax({  
      type: 'POST',  
      url :  window.location + "/createBillingAddress",
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

    var address = {};
    address.lineOne = $("#cardAddressLineOne").val();
    address.lineTwo = $("#cardAddressLineTwo").val();
    address.city = $("#cardAddressCity").val();
    address.state = $("#cardAddressState").val();
    address.zipcode = $("#cardAddressZipcode").val();
    
    var card = {};
    card.type = $("input:radio[name='cardType']:checked").val();
    card.nameOnCard = $("#nameOnCard").val();
    card.cardNumber = $("#cardNumber").val();
    card.expiredDate = $("#expiredDate").val() + "-01";
    card.address = address;
    console.log(card);
    
    $.ajax({  
      type: 'POST',  
      url :  window.location + "/createCardDetail",
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