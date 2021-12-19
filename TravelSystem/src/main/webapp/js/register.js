var flag1 = false;
var flag2 = false;
var flag3 = false;

$(document).ready(function() {
	$("#username").blur(function() {
		//console.log("in function method ...");
		if ($(this).val().length == 0) {
			//console.log("username....");
			$("#errorname").html("<font color=\"red\">用户名不为空</font>");
			flag1 = false;
		}else{
			var username = $("input#username").val();
			$.ajax({
				type: "GET",
				url: "http://localhost:8080/TravelSystem/register.do?action=username&username="+username,
				success: function(msg){
					if(msg == "false"){
						console.log("用户名可使用");
						$("#errorname").html("<font color=\"green\">该用户名可使用</font>");
						flag1 = true;
					}else{
						console.log("用户名不可使用");
						$("#errorname").html("<font color=\"red\">该用户名已被占用</font>");
						flag1 = false;
					}
				},
				error: function(msg){
					$("#errorname").html("<font color=\"red\">响应出现异常...</font>");
					flag1 = false;
				}
			});
		}
	});

	$("#password").blur(function() {
		if($(this).val().length == 0) {
			$("#errorpassword").html("<font color=\"red\">密码不为空</font>");
			flag2 = false;
		}else{
			var reg = /^[0-9a-zA-Z]{6,16}$/;
            if (!reg.test($(this).val())) {
                $("#errorpassword").html("<font color=\"red\">6-16个英文字母或数字</font>");
                flag2 = false;
            }
            else {
                $("#errorpassword").html("<font color=\"green\">密码可用</font>");
                flag2 = true;
            }
		}
	});
	
	$("#confirm").blur(function() {
		if($(this).val().length == 0) {
			$("#errorconfirm").html("<font color=\"red\">确认密码不为空</font>");
			flag3 = false;
		}else{
			if($(this).val() != $("#password").val()){
				$("#errorconfirm").html("<font color=\"red\">两次密码不一致</font>");
				flag3 = false;
			}else{
				$("#errorconfirm").html("<font color=\"green\">两次密码一致</font>");
				flag3 = true;
			}
		}
	});
	
	$("#submit_btn").click(function(){
		console.log("flag1"+flag1);
		console.log("flag2"+flag2);
		console.log("flag3"+flag3);
		if(flag1 && flag2 && flag3){
			$("form").submit();
		}else{
			alert("有信息填写错误!");
			return false;
		}
	});
	
	$("#reset_btn").click(function(){
		$("#errorname").html("");
		$("#errorpassword").html("");
		$("#errorconfirm").html("");
		flag1,flag2,flag3=false;
	});
});