/**
 * ThaoDao created and edited
 */

$(document).ready(function() {	
	$(".btnAddToCart").click(function(event){
		 event.preventDefault();
		console.log($(this).attr('href'));
        let href = $(this).attr('href');
        let location = window.location.href.slice(0, -1);
		console.log(location);
        $.ajax({
			url: "http://localhost:8080/index" + href,
			type: 'PUT',
			dataType: "json",
			success: function(response){
				console.log(response);
				//toastr["success"]("Product is added successfully! Please check the cart for details.");
				alert("Success: Product is added successfully! Please check the cart for details.");
			},
			error: function(jqXHR, textStatus, errorThrown){					
				console.log("jqXHR: " + jqXHR);
			    console.log("textStatus: " + textStatus);
			    console.log("errorThrown: " + errorThrown);
			    //toastr["error"]("Error: Product is not added.");
			    alert("Error: Product is not added.");
			}
		});
	});
});