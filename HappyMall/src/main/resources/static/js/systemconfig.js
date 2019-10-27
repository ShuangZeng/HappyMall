/**
 * 
 */
$("document").ready(function(){
	
	$("#createDate, #appliedDate").datepicker({
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
				$("#createDate").datepicker('update', systemConfig.createDateString);
				//$("#appliedDate").val(systemConfig.appliedDateString);
				$("#appliedDate").datepicker('update', systemConfig.appliedDateString);
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