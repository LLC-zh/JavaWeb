var flag1 = false;

function getRepushId(id){
	
	var url = "http://localhost:8080/TravelSystem/jsp/back/scenic.do?" 
	
	$.ajax({
		type: 'GET',
		url: url,
		data: {"id":id,"method": "repush"},
		dataType: "JSON",
		success: function(msg){
			//var obj = JSON.parse(msg);
			console.log(msg.id);
			$('#repushId').val(msg.id);
		},
		error: function(msg){
			alert("获取信息出现错误");
		}
	});
}

$("#scenic-number").blur(function(){
	var no = $("#scenic-number").val();
	if(no.length == 0){
		$("#no_tip").html("<font color='red'>编号不能为空</font>");
		flag1 = false;
	}else{
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/TravelSystem/jsp/back/scenic.do",
			data: "method=ajaxquery&no="+no,
			success: function(msg){
				if(msg == "false"){
					$("#no_tip").html("<font color='red'>该编号已占用</font>");
					flag1 = false;
				}else{
					$("#no_tip").html("<font color='green'>该编号可使用</font>");
					flag1 = true;
				}
			},
			error: function(msg){
				$("#no_tip").html("<font color=\"red\">响应出现异常...</font>");
				flag1 = false;
			}
		});
	}
});

$("#scenic_add").click(function(){
	if(flag1){
		$("#demo-form2").submit();
	}else{
		alert("有信息填写错误");
		return false;
	}
});

$("#scenic_reset").click(function(){
	$("#no_tip").html("");
	flag1 = false;
});