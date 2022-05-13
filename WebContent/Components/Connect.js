$(document).on("click", "#btnSave", function(event){ 
	
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
 
	 
	// Form validation-------------------
	var status = validateItemForm(); 
	
	// If not valid----------------------
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 } 


	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
		$.ajax( 
		{ 
			 url : "ConnectAPI", 
			 type : type, 
			 data : $("#formItem").serialize(), 
			 dataType : "text", 
			 complete : function(response, status) 
			 { 
				 onItemSaveComplete(response.responseText, status); 
		 	 } 
		 
		}); 
	});

// SAVE =======================================
function onItemSaveComplete(response, status)
{ 
	if (status == "success") 
	{
		
		 var resultSet = JSON.parse(response); 
		 
		 if (resultSet.status.trim() == "success") 
		 { 
			 
			 $("#alertSuccess").text("Successfully Saved."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
			 
		 }else if (resultSet.status.trim() == "error") 
		 {
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	}else if (status == "error") 
	{ 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
		 
	} else
	{ 
		
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	}
	
	//Reset form
	$("#hidItemIDSave").val(""); 
	$("#formItem")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{ 
		$("#hidItemIDSave").val($(this).data("userid")); 
		$("#connectionName").val($(this).closest("tr").find('td:eq(0)').text()); 
		$("#connectionType").val($(this).closest("tr").find('td:eq(1)').text()); 
		$("#description").val($(this).closest("tr").find('td:eq(2)').text()); 
		$("#price").val($(this).closest("tr").find('td:eq(3)').text()); 
		$("#date").val($(this).closest("tr").find('td:eq(4)').text()); 
		// $("#").val($(this).closest("tr").find('td:eq(8)').text()); 
		 
});


// REMOVE=============================================
$(document).on("click", ".btnRemove", function(event) { 
	 $.ajax( 
	 { 
	 	url : "ConnectAPI", 
	 	type : "DELETE", 
	 	data : "connectionId=" + $(this).data("userid"),
	 	dataType : "text", 
	 	complete : function(response, status) 
	 	{ 
	 		onItemDeleteComplete(response.responseText, status); 
	 	} 
	}); 
})
	


function onItemDeleteComplete(response, status)
{
	
	if (status == "success") 
	{
		
		var resultSet = JSON.parse(response); 
		
			if (resultSet.status.trim() == "success")
			{
			
				$("#alertSuccess").text("Deleted Successfully!!."); 
				$("#alertSuccess").show(); 
				$("#divItemsGrid").html(resultSet.data); 
				
			} else if (resultSet.status.trim() == "error") 
			{ 
				
				$("#alertError").text(resultSet.data); 
				$("#alertError").show(); 
			} 
	}else if (status == "error") 
	{ 
		$("#alertError").text("Error while deleting."); 
		$("#alertError").show(); 
	}else 
	{ 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	} 
}

// CLIENT-MODEL================================================================
function validateItemForm()
{
		// CODE
	//CONNECTION-TYPE---------------------------
	if ($("#connectionType").val().trim() == "")
	{
		return "Insert Connection Type.";
	} 

	//DESCRIPTION------------------------------
	if ($("#description").val().trim() == "")
	{
		return "Insert Description.";
	}

	//DATE---------------------------------
	if ($("#date").val().trim() == "")
	{
		return "Insert Date.";
	}
	
	//PRICE-------------------------------
	if ($("#price").val().trim() == "")
	{
		return "Insert Connection Price.";
	}

	// is numerical value
	var tmpPrice = $("#price").val().trim();
	if (!$.isNumeric(tmpPrice))
	{	
		return "Insert a numerical value for price.";
	}
			
	// convert to decimal price
	$("#price").val(parseFloat(tmpPrice).toFixed(2));
	
		return true;
}

