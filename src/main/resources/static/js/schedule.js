$(document).ready(function(){
	var days = {
	    MONDAY: 1,
	    TUESDAY: 2,
	    WEDNESDAY: 3,
	    THURSDAY: 4,
	    FRIDAY: 5,
	    SATURDAY: 6,
	    SUNDAY: 7
	};

	$("#addNewSchedule").click(function(){
		$("#scheduleID").val("");
		$('#newScheduleModal').modal('show');
	});
	
	$(".timepicker-24").timepicker({
		autoclose: !0,
        minuteStep: 1,
        showSeconds: !0,
        showMeridian: !1
    });
	
	$(".edit").click(function(){
		var id = $(this).attr("id");
		var timeOld = $(this).parent().parent().parent().find(".time").text();
		var dayOld =  days[$(this).closest(".panel").attr('id').substring(4)];
		
		$("#modalTime").timepicker('setTime', timeOld);
		$("#modalDay").val(dayOld);
		$('#newScheduleModal').modal('show');
		
		$("#scheduleID").val(id);
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
		var id = $("#scheduleID").val();
		var day = $("#modalDay").val();
		var time = $("#modalTime").val();
		$.ajax({
		  type: "POST",
		  dataType: 'json',
		  data: { id : id , day : day , time : time },
		  url: "addSchedule",
		});
	});
	
});