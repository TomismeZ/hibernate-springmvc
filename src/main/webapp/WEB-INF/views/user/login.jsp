<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UMS</title>
<link type="text/css" rel="stylesheet" href="${ctx }/css/login.css" />

</head>

<body>

	<div id="header-panel">
		<div class="logo-panel">
			<div class="logo-content-panel"></div>
			<h5>用户管理系统</h5>
		</div>
		<div class="menu-panel">
			<a href="javascript:;">首页</a>
		</div>
	</div>
	<!--SIGN UP-->
	<h1>用户登录</h1>
	<div class="login-form">
		<div class="close"></div>
		<div class="head-info">
			<label class="lbl-1"> </label> <label class="lbl-2"> </label> <label
				class="lbl-3"> </label>
		</div>

		<div class="avtar">
			<img src="${ctx }/images/avtar.png" />
		</div>
		<form action="${ctx }/user/loginResultUser" method="post">
			<input type="text" class="text" name="name"
				placeholder="Username">
			<div class="key">
				<input type="password" name="password"
					placeholder="password">
			</div>
		</form>
		<div class="clear"></div>
		<div class="signin">
			<button>Login</button>
		</div>
		<div class="bottom">
			<button  data-id="1">注册一个账户</button>
			<button data-id="2">忘记密码</button>
		</div>

	</div>
	<div id="footer"></div>
	<div id="bg"></div>

	<script src="${ctx }/plugins/jquery-2.2.4.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/plugins/jquery-form/jquery.form.min.js"></script>
	<script type="text/javascript">
		var $loginForm = $(".login-form");
		var $form = $loginForm.find("form");
		var $headInfo = $loginForm.find(".head-info");
		var $bottom = $loginForm.find(".bottom");
		var $clear = $loginForm.find(".clear");
		var $avtar = $loginForm.find(".avtar");
		var $footer=$("#footer");
		var ctx="${ctx }";
		
		//点击登录按钮，就会触发表单提交事件
		$loginForm.find(".signin").find("button").on("click", function() {
			/*  $form.trigger("submit"); */
			 var userName = $form.find("input[type='text']").val();
			var password = $form.find("input[type='password']").val();

			if (!$.trim(userName) || !$.trim(password)) {
				$clear.text("用户名或者密码不能为空！");
			} else {
		/* 		alert("post");
				
				$.post(
					ctx+ "/user/validateLogin", $form.serialize(),
						function(data) {
						alert(data);
						console.log(data);
							 if (data.success) {
								 alert("success");
								$form.submit();
							} else {
								$clear.text("用户名或者密码错误！");
								$clear.css("background-color", "#fff");
								$clear.css("color", "red");
							}
							

						
					
				});
				return; */
		 		$.ajax({
					cache : true,
					type : "POST",
					url :ctx+ "/user/validateLogin",
					dataType:"json",//没有这个，将把后台放会的json解析成字符串 
					data : $form.serialize(),// 你的formid
					async : false,
					error : function(request) {
						console.log(request);
						alert("Connection error");
					},
					success : function(data) {
						 if (data.success) {
							 alert("success");
							$form.submit();
						} else {
							$clear.text("用户名或者密码错误！");
							$clear.css("background-color", "#fff");
							$clear.css("color", "red");
						}
						

					}
				}); 
			} 

		});
		$headInfo.find("label").on(
				"click",
				function() {
					var $this = $(this);
					/* alert($this.css("background-color")); */
					$headInfo.css("background-color", $this
							.css("background-color"));
					$bottom.find("button").css("background-color",
							$this.css("background-color"));
				});
		//按钮点击事件，根据Id来判断需要跳转到哪个界面
		$bottom.find("button").on("click", function() {
			var $this=$(this);
			var id=$this.data("id");
			$("body").css("overflow","auto");
			$("#bg").css("display","block");
			$("#footer").css("display","block");
			$("#footer").load("toRegister",{id:id},function(){
				/* document.getElementById("bg").style.display = 'block'; */
				$registContent.find(".topPanel").find("button").on("click", function() {
					$registContent.remove();
					$("#bg").css("display","none");
					$("body").css("overflow","hidden");
				});
			});
		});

		
	</script>
</body>

</html>