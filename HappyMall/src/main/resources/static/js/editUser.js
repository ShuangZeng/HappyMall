/**
 * ThaoDao created and edited
 */
$("document").ready(function(){
	$(".table .eBtn").click(function(event){
		event.preventDefault();
		var text = $(this).text;
		var href = $(this).attr('href');
        console.log("href: " + href);
        console.log("text: " + text);
            console.log("js: getAddress edit");
			$.get(href, function(address, status){
				$("#id").val(address.id);
				$("#lineOne").val(address.lineOne);
				$("#lineTwo").val(address.lineTwo);
				$("#city").val(address.city);
				$("#state").val(address.state);
				$("#zipcode").val(address.zipcode);
				$("#default").attr("checked", address.default_addr);
				$("#default_addr").val(address.default_addr);
			});
		$("#addressModal").modal("show");
	});	

	$("#nBtn").click(function(event){
		event.preventDefault();
		var text = $(this).text;
		var href = $(this).attr('href');
        console.log("href: " + href);
        console.log("text: " + text);
            console.log("js: create");
			$("#id").val('0');
			$("#lineOne").val('');
			$("#lineTwo").val('');
			$("#city").val('');
			$("#state").val('');
			$("#zipcode").val('');
			$("#default").attr("checked", false);
			$("#default_addr").val(false);
		$("#addressModal").modal('show');
	});
	
	$(".table .dBtn").click(function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$("#delRef").attr('href', href);
		$("#confirmDelModal").modal();
	});
	
	$("#default").change(function(){
		if($(this).is(":checked"))
			$("#default_addr").val("true");
		else
			$("#default_addr").val("false");
	});
});