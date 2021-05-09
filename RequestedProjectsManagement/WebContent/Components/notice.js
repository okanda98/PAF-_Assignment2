$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidItemIDSave").val(
					$(this).closest("tr").find('#hidItemIDUpdate').val());
			$("#name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#feild").val($(this).closest("tr").find('td:eq(1)').text());
			$("#description").val($(this).closest("tr").find('td:eq(2)').text());
			$("#submission_link").val($(this).closest("tr").find('td:eq(3)').text());
			$("#fundingbody_id").val($(this).closest("tr").find('td:eq(4)').text());
		});

// CLIENT-MODEL================================================================
function validateItemForm() {
	// CODE
	if ($("#name").val().trim() == "") {
		return "Insert Item Code.";
	}
	// NAME
	if ($("#feild").val().trim() == "") {
		return "Insert Item Name.";
	}
	// PRICE-------------------------------
	if ($("#description").val().trim() == "") {
		return "Insert Item Price.";
	}
	// is numerical value
	if ($("#submission_link").val().trim() == "") {
		return "Insert Item Price.";
	}
	// DESCRIPTION------------------------
	if ($("#fundingbody_id").val().trim() == "") {
		return "Insert Item Description.";
	}
	return true;
}

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	console.log(type);
	$.ajax({
		url : "NoticeAPI",
		type : type,
		data : $("#formNotice").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});
});

$(document).on("click", ".btnRemove", function(event)
		{ 
	console.log("Remove Button Clicked");
		 $.ajax( 
		 { 
		 url : "ItemsAPI", 
		 type : "DELETE", 
		 data : "itemID=" + $(this).data("itemid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onItemDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});

function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while Deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}