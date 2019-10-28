/**
 * ThaoDao created and edited
 */
$("document").ready(function(){
	
	$("#appliedDate").datepicker({
	    format: "mm-dd-yyyy",
	    startDate: new Date(),
	    autoclose: true
	  });
	
	$(".table .eBtn, #nBtn").click(function(event){
		event.preventDefault();
		var text = $(this).text();
		var href = $(this).attr("href");
        console.log("href: " + href);
        console.log("text: " + text);
        if(text == "Edit")
        {
			$.get(href, function(systemConfig, status){
		        console.log("Edit: " + systemConfig);
				$("#id").val(systemConfig.id);
				//$("#createDate").val(systemConfig.createDateString);
				$("#createDate").val(systemConfig.createDateString);
				//$("#appliedDate").val(systemConfig.appliedDateString);
				$("#appliedDate").datepicker('update', systemConfig.appliedDateString);
				$("#tax").val(systemConfig.tax);
				$("#serviceFee").val(systemConfig.serviceFee);
			});
        }else
        {
        	let date = new Date();
        	let dateString = "";
        	if(date.getMonth() < 10)
        		dateString = "0" + date.getMonth();
        	else
        		dateString = date.getMonth();
        	if(date.getDate() < 10)
        		dateString = dateString + "-0" + date.getDate();
        	else
        		dateString = dateString + "-" + date.getDate();
    		dateString = dateString + "-" + date.getFullYear();
    		console.log(dateString);
        	$("#id").val('0');
			$("#createDate").val(dateString);
			$("#appliedDate").val('');
			$("#tax").val('');
			$("#serviceFee").val('');
        }
		$("#systemConfigModal").modal('show');
	});
	
	$(".table .dBtn").click(function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$("#delRef").attr('href', href);
		$("#confirmDelModal").modal();
	});
		
	$("#createSystemConfig").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        createSystemConfig();
    });
});

function createSystemConfig() {     
    console.log("Ajax: create system config...");
    let systemConfig = {};
    let createDate = $("#createDate").val().substring(6, 10) + "-" + $("#createDate").val().substring(0, 2) + "-" + $("#createDate").val().substring(3, 5);
    let appliedDate = $("#appliedDate").val().substring(6, 10) + "-" + $("#appliedDate").val().substring(0, 2) + "-" + $("#appliedDate").val().substring(3, 5);
    console.log(createDate);
    systemConfig.id = $("#id").val();
    systemConfig.createDate = createDate;
    systemConfig.appliedDate = appliedDate;
    systemConfig.tax = $("#tax").val();
    systemConfig.serviceFee = $("#serviceFee").val();
    console.log(systemConfig);
    
    $.ajax({  
      type: 'PUT',  
      url : "http://localhost:8080/admin/systemconfig/save",
      contentType: "application/json",
      data: JSON.stringify(systemConfig),
      datatype: "json",
      success: function(){
    	setInterval('location.reload()', 2000);
      },  
      error: function(e){          
      	alert('Error: ' + e);  
      }  
    });  
}