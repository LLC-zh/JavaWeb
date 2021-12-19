$("#in_submit").click(function(){
	var address = "http://localhost:8080/TravelSystem/jsp/back/delicacy.do";
	
	$.post(address,{method: "query", queryDelicacyName: $("#queryDelicacyName").val()},
			function(data){
				$("body").html(data);
			});
})