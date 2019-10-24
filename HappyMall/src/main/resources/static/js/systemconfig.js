/**
 * 
 */
$("document").ready(function(){
	$(".table .eBtn, #nBtn").click(function(event){
		event.preventDefault();
		var text = $(this).text;
		var href = $(this).attr('href');
        console.log("href: " + href);
        if(text == 'Edit')
        {
			$.get(href, function(systemConfig, status){
				$("#id").val(systemConfig.id);
				$("#createDate").val(systemConfig.createDate);
				$("#appliedDate").val(systemConfig.appliedDate);
				$("#tax").val(systemConfig.tax);
				$("#serviceFee").val(systemConfig.serviceFee);
			});
        }else
        {
        	$("#id").val('0');
			$("#createDate").val('');
			$("#appliedDate").val('');
			$("#tax").val('');
			$("#serviceFee").val('');
        }
		$("#systemConfigModal").modal();
	});
	
	$(".table .dBtn").click(function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$("#delRef").attr('href', href);
		$("#confirmDelModal").modal();
	});
});