/**
 * 
 */

$(document).ready(function() {
	/*$('#btnSaveName').click(function() {
		$('#shippingName').html($('#nameModal #inputName').val()); 
		$('#nameModal').modal('hide');
	});*/
	/*$('#btnSaveAddress').click(function() {
		$('#shippingName').html($('#addressModal #inputName').val()); 
		$('#shippingPhoneNo').html($('#addressModal #inputPhoneNo').val()); 
		//$('#addressModal').modal('hide');
	});*/
	
//	$("#addressModalForm").validate({
//	    rules: {
//	      inputName: {
//	        required: true,
//	        minlength: 5
//	      },
//	      inputPhoneNo: {
//		        required: true,
//		        minlength: 12,
//		        maxlength: 12
//		      },
//	      action: "required"
//	    },
//	    messages: {
//	      inputName: {
//	        required: "Please enter some data",
//	        minlength: "Your data must be at least 5 characters"
//	      },
//	      inputPhoneNo: {
//		        required: "Please enter phone number",
//		        minlength: "Your data must be like 000-000-0000",
//		        maxlength: "Your data must be like 000-000-0000"
//		      },
//	      action: "Please provide some data"
//	    }
//	  });

});


function enableAddressModal() {
	document.getElementById('lineOne').disabled=false;
	document.getElementById('lineTwo').disabled=false;
	document.getElementById('city').disabled=false;
	document.getElementById('state').disabled=false;
	document.getElementById('zipcode').disabled=false;
	
	document.getElementById('lineOne').required=true;
	document.getElementById('lineTwo').required=true;
	document.getElementById('city').required=true;
	document.getElementById('state').required=true;
	document.getElementById('zipcode').required=true;
}
function disableAddressModal() {
	document.getElementById('lineOne').disabled=true;
	document.getElementById('lineTwo').disabled=true;
	document.getElementById('city').disabled=true;
	document.getElementById('state').disabled=true;
	document.getElementById('zipcode').disabled=true;
	
	document.getElementById('lineOne').required=false;
	document.getElementById('lineTwo').required=false;
	document.getElementById('city').required=false;
	document.getElementById('state').required=false;
	document.getElementById('zipcode').required=false;
}


function enablePaymentMethodModal() {
	document.getElementById('nameOnCard').disabled=false;
	document.getElementById('cardNumber').disabled=false;
	document.getElementById('expiredDate').disabled=false;
	document.getElementById('cardAddressLineOne').disabled=false;
	document.getElementById('cardAddressLineTwo').disabled=false;
	document.getElementById('cardAddressCity').disabled=false;
	document.getElementById('cardAddressState').disabled=false;
	document.getElementById('cardAddressZipcode').disabled=false;
	
	document.getElementById('nameOnCard').required=true;
	document.getElementById('cardNumber').required=true;
	document.getElementById('expiredDate').required=true;
	document.getElementById('cardAddressLineOne').required=true;
	document.getElementById('cardAddressLineTwo').required=true;
	document.getElementById('cardAddressCity').required=true;
	document.getElementById('cardAddressState').required=true;
	document.getElementById('cardAddressZipcode').required=true;	
}
function disablePaymentMethodModal() {
	document.getElementById('nameOnCard').disabled=true;
	document.getElementById('cardNumber').disabled=true;
	document.getElementById('expiredDate').disabled=true;
	document.getElementById('cardAddressLineOne').disabled=true;
	document.getElementById('cardAddressLineTwo').disabled=true;
	document.getElementById('cardAddressCity').disabled=true;
	document.getElementById('cardAddressState').disabled=true;
	document.getElementById('cardAddressZipcode').disabled=true;
	
	document.getElementById('nameOnCard').required=false;
	document.getElementById('cardNumber').required=false;
	document.getElementById('expiredDate').required=false;
	document.getElementById('cardAddressLineOne').required=false;
	document.getElementById('cardAddressLineTwo').required=false;
	document.getElementById('cardAddressCity').required=false;
	document.getElementById('cardAddressState').required=false;
	document.getElementById('cardAddressZipcode').required=false;	
}