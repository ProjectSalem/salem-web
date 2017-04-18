$(document).ready(function(){
	
	$("#addNewSchedule").click(function(){
		$('#newScheduleModal').modal('show');
	});
	
	$(".timepicker-default").timepicker({
        autoclose: !0,
        showSeconds: !0,
        minuteStep: 1
    });
	
	$(".edit").click(function(){
		
	});
	
	$(".delete").click(function(e){
		var id = $(this).attr("id");
		$.ajax({
		  type: "POST",
		  dataType: 'json',
		  data: { id : id },
		  url: "deleteSchedule",
		});
	});
	

	$("#saveButton").click(function(e){
		var day = $("#modalDay").val();
		var time = $("#modalTime").val();
		$.ajax({
		  type: "POST",
		  dataType: 'json',
		  data: { day : day, time : time },
		  url: "addSchedule",
		});
	});
	
});