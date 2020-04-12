'use strict';

$(document).ready(function(){

	$("#book-input-form").submit(function(e){
		e.preventDefault();
	});	

	loadData();
	insertData();
	updateData();
	deleteData();
});
/**
 * getAll Data from db
 */
function getAllData(){
	$.get('ServletDbController',function(response) {
		$("#id-input").val('');
		$("#issue-input").val('');
		$("#assignee-input").val('');
		$("#priority-input").val('Low');
		var table="<tr><th>Issue ID</th><th>Issue Title</th><th>Assignee</th><th>Priority</th></tr>";
		var rowCountDive = document.getElementById("row-count");
		var a = document.getElementById("issue_tracker");
		var obj = JSON.parse(response);
		var rowCount = obj.length;
		rowCountDive.innerHTML = rowCount + " rows";
		for(var i=0;i<obj.length;i++){				
			table += "<tr>" +
			"<td>" +obj[i].issueId +"</td>"+
			"<td>" +obj[i].issueTitle +"</td>"+
			"<td>" +obj[i].assignee +"</td>"+
			"<td>" +obj[i].priority +"</td>"+
			"</tr>";
			document.getElementById("issue_tracker").innerHTML = table;	
		}
		$("#view-btn").attr("disabled",true);	

		$("tr").dblclick(function(e){
			var currentRow = $(this).closest("tr");
			var issueId = currentRow.find("td:eq(0)").text();
			var issueTitle = currentRow.find("td:eq(1)").text();
			var assignee = currentRow.find("td:eq(2)").text();
			var priority = currentRow.find("td:eq(3)").text();
			//alert(issueId+"\n"+issueTitle+"\n"+assignee+"\n"+priority);
			$("#id-input").val(issueId).attr("disabled",true);
			$("#issue-input").val(issueTitle);
			$("#assignee-input").val(assignee);
			$("#priority-input").val(priority);
		});
	});
}

/**
 * Load records from db
 */
function loadData(){
	$("#load-btn").click(function(e){
		getAllData();
	})
}

/**
 * Insert record in db
 */
function insertData(){
	$("#add-btn").click(function(e){
		var url="ServletDbController";	
		var id=$("#id-input").val();
		var issue = $("#issue-input").val();
		var assignee = $("#assignee-input").val();
		var priority = $("#priority-input").val();
		var query = "insert";
		var param = {
				idInput : id,
				issueInput : issue,
				assigneeInput : assignee,
				priorityInput : priority,
				operationType : query
		};
		$.post(url,param,function(){
			getAllData();
		}
		);
	});
}

/**
 * update record in db
 */
function updateData() {
	$("#upd-btn").click(function(e){
		var url = "ServletDbController";
		var id=$("#id-input").val();
		var issue = $("#issue-input").val();
		var assignee = $("#assignee-input").val();
		var priority = $("#priority-input").val();
		var query = "update";
		var param = {
				idInput : id,
				issueInput : issue,
				assigneeInput : assignee,
				priorityInput : priority,
				operationType : query
		};
		$.post(url,param,function(){
			getAllData();
			$("#id-input").val('').attr("disabled",false);
		});
	});
}

/**
 * delete record from DB
 * 
 */
function deleteData(){
	$("#del-btn").click(function(e){
		var url = "ServletDbController";
		var id = $("#id-input").val();
		var query = "delete";
		var param = {
				idInput : id,
				operationType : query
		};
		$.post(url,param,function(){
			getAllData();
			$("#id-input").val('').attr("disabled",false);
		});

	})
}