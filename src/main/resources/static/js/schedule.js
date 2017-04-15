$(document).ready(function(){
	
	$("#addNewSchedule").click(function(){
		$('#newScheduleModal').modal('show');
	});
	
	$(".timepicker-default").timepicker({
        autoclose: !0,
        showSeconds: !0,
        minuteStep: 1
    })
	
});