function getScenicName(){
	var name = $("#playListLocation").find("option:selected").text();
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/TravelSystem/jsp/back/play.do",
		data: "method=ajaxquery&queryScenicLocation="+name,
		success: function(msg){
			$("#playListScenicName").empty();
			$("#playListScenicName").prepend("<option value=''>请选择</option>");
			
			for(var i = 0; i< msg.length; i++){
				console.log("scenicNumber:"+msg[i].scenicNumber+", scenicName:"+msg[i].scenicName);	
				$("#playListScenicName").append("<option value='"+msg[i].scenicNumber+"'>"+msg[i].scenicName+"</option>");
			}
		}
	});
}

function getScenicNameAdd(){
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/TravelSystem/jsp/back/play.do",
		data: "method=ajaxquery&queryScenicLocation="+"",
		success: function(msg){
			$("#scenicNumber").empty();
			
			for(var i = 0; i< msg.length; i++){
				console.log("scenicNumber:"+msg[i].scenicNumber+", scenicName:"+msg[i].scenicName);	
				$("#scenicNumber").append("<option value='"+msg[i].scenicNumber+"'>"+msg[i].scenicName+"</option>");
			}
		}
	});
}