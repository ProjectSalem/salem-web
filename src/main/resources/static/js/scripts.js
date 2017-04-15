$("#servo").click(function(e) {
	e.preventDefault();
	$.ajax({
	  type: "GET",
	  url: "servo",
	});
});