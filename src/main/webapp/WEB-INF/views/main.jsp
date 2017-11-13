<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UMS-用户管理系统</title>

<!-- 引入 jQuery 库 -->
<script src="${ctx }/plugins/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="${ctx }/plugins/jquery-form/jquery.form.min.js"></script>
<!-- 引入自定义样式文件 -->
<link rel="stylesheet" href="${ctx }/css/style.css">
<link type="text/css" rel="stylesheet" href="${ctx }/plugins/bootstrap.min.css" />
<!-- 引入日期选择器插件 -->
<link href="${ctx }/plugins/air-datepicker/css/datepicker.min.css"
	rel="stylesheet" type="text/css">
<script src="${ctx }/plugins/air-datepicker/js/datepicker.min.js"></script>
<!-- 日期选择器引入中文语言 -->
<script src="${ctx }/plugins/air-datepicker/js/i18n/datepicker.zh.js"></script>
<script src="${ctx }/plugins/jqPaginator.js" type="text/javascript"></script>
</head>
<body>
	<header>
	<h2>用户管理系统</h2>
	<nav> <a href="#">帮助</a> <a href="logoutUser">退出</a> </nav> </header>
	<div class="content">
		<aside id="left" class="left">
		<div class="menu">
			<a href="${ctx}/user/list" class="menu-item">用户管理</a> 
			<a href="${ctx}/profession/list" class="menu-item">职业管理</a>
			<a href="${ctx}/hobby/list" class="menu-item">爱好管理</a>
		</div>
		<div class="head-avatar">
			<img src="${ctx }/file/${userInfo.avatar}" alt="头像" id="image">
			<form action="" method="post" enctype="multipart/form-data" style="display: none;">
	<input type="file" name="imageFile" accept="image/*"
	onchange="document.all['image'].src=getImgURL(this);">
			</form>
			<h5>当前登录用户(${userInfo.name })</h5>
		</div>
		</aside>
		<div id="right" class="right">
		</div>
	</div>
	
	<script type="text/javascript">
	function getImgURL(node) {
		var imgURL = "";
		try {
			var file = null;
			if (node.files && node.files[0]) {
				file = node.files[0];
			} else if (node.files && node.files.item(0)) {
				file = node.files.item(0);
			}
			try {
				imgURL = window.URL.createObjectURL(file);
			} catch (e) {
				imgRUL = window.URL.createObjectURL(file);
			}
		} catch (e) {

			if (node.files && node.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					imgURL = e.target.result;
				};
				reader.readAsDataURL(node.files[0]);
			}
		}
		return imgURL;
	}
</script>
<script type="text/javascript">

var g_rootPath = "${ctx}";
var $rightContentPanel=$("#right");

$("#image").on("click",function(){
	if(confirm("要更改头像吗？")){
		$("form").find("input[type='file']").trigger("click");
		/* $("form").submit(); */
	}
	
});

/* $("#left").find(".menu").find("a").first().trigger("click"); */
$("#left").find(".menu").find("a").on("click",function(e){
	var $this=$(this);
	
	//高亮处理
	$("#left").find(".menu").find("a").removeClass("active");
	$this.addClass("active");
	e.preventDefault();
	//得到a标签href属性的值
	var pageRef = $this.prop("href");
	/* alert(pageRef); */
	$rightContentPanel.load(pageRef);
}).first().click();

//加载主页面内容
function loadMainContent(url, callbacks, params) {
	$rightContentPanel.load(g_rootPath + url, params, callbacks);
}

// 显示验证提示
function showInputTip($input, value) {
	var $parent = $input.parent();
	
	$parent.addClass("error-group");
	$parent.find(".input-label span").remove();
	$parent.find(".input-label").append("<span>" + value + "</span>");
	$input.focus();
	
	return false;
}

// 隐藏验证提示
function hideInputTip($input) {
	var $parent = $input.parent();
	
	$parent.removeClass("error-group");
	$parent.find(".input-label span").remove();
}

// 触发当前菜单点击
function triggerCurrentMenu() {
	$("#left").find(".menu").find("a.active").trigger("click");
}

//通用异步请求
function doAction(url, params, callback, message) {
	$.ajax({
		url: g_rootPath + url,
		type: "POST",
		dataType: "TEXT",
		data: params,
		success: function (data) {
			// 判断回调函数是否是一个方法
			if (typeof callback === "function") {
				// call()用于触发指定方法
				// 传入的参数将直接应用于具体方法上
				callback.call(data);
			}
			
			// 判断信息是否有效
			if (message === undefined || $.trim(message) === "") {
				return false;
			}
			
			alert(message);
		}
	})
}
</script>
</body>
</html>