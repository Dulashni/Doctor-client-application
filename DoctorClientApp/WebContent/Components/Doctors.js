//hide the divisions used to show the status messages on the page load.
$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//LAB 09 
//implementing the save button click handler
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------

	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// LAB 10
	// If valid------------------------

	var type = ($("#hiddocIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "DoctorsAPI",
		type : type,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onDoctorSaveComplete(response.responseText, status);
		}
	});

});


function onDoctorSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divDoctorsGrid").html(resultSet.data);
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
	$("#hiddocIDSave").val("");
	$("#formDoctor")[0].reset();
}



//implementing the update button click handler
$(document).on("click", ".btnUpdate", function(event)
	{
		$("#hiddocIDSave").val($(this).closest("tr").find('#hiddocIDUpdate').val());
		$("#docName").val($(this).closest("tr").find('td:eq(0)').text());
		$("#docSpec").val($(this).closest("tr").find('td:eq(1)').text());
		$("#docHosp").val($(this).closest("tr").find('td:eq(2)').text());
		$("#docContact").val($(this).closest("tr").find('td:eq(3)').text());
		$("#docEmail").val($(this).closest("tr").find('td:eq(4)').text());
		$("#docStat").val($(this).closest("tr").find('td:eq(5)').text());
	});



//implementing the delete button click handler
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "DoctorsAPI",
		type : "DELETE",
		data : "docID=" + $(this).data("docID"),
		dataType : "text",
		complete : function(response, status) {
			onDoctorDeleteComplete(response.responseText, status);
		}
	});
});


function onDoctorDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divDoctorsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// validating the form
function validateDoctorForm(){
	

	// Doctor name
	if ($("#docName").val().trim() == "") {
		return "Insert Doctor Name.";
	}
	
	//Doctor Specialization
	if ($("#docSpec").val().trim() == "") {
		return "Insert Doctor Specialization.";
	}
	
	//Doctor Hospital
	if ($("#docHosp").val().trim() == "") {
		return "Insert Doctor Hospital.";
	}
	
	//Doctor Contact Number
	if ($("#docContact").val().trim() == "") {
		return "Insert Doctor Contact number.";
	}
	
	//checking if Contact number is numerical
	var tmpContact = $("#docContact").val().trim();
	if (!$.isNumeric(tmpContact)) {
		return "Insert a numerical value for Contact no.";
	}

	//Doctor Email
	if ($("#docEmail").val().trim() == "") {
		return "Insert Doctor Email Address.";
	}
	
	//Checking if a valid email Address
	var tmpEmail = $("#docEmail").val().trim();
	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
	if (! re.test(tmpEmail)){
		return "Insert a valid email address";
	}
	
	
	//Doctor Status
	if ($("#docStat").val().trim() == "") {
		return "Insert Doctor Availability status.";
	}
	
	
	
	
}


