<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section class="main-panel">
	<c:if test="${empty user }">
		<h4 class="form-title">新增用户信息</h4>
	</c:if>
	<c:if test="${!empty user }">
		<h4 class="form-title">编辑用户信息</h4>
	</c:if>
	<form name="userForm" action="${ctx }/user/save" method="post"
		class="form" enctype="multipart/form-data">
		<div class="form-input">
			<div class="form-left">
				<div class="input-group ">
					<label class="input-label">用户名称：</label> <input id="username"
						name="name" type="text" class="input" placeholder="用户名称..." value="${user.name }">
				</div>
				<div class="input-group ">
					<label class="input-label">登录密码：</label> <input id="password"
						name="password" type="password" class="input"
						placeholder="请输入您的密码！" value="${user.password }">
				</div>
				<div class="input-group ">
					<label class="input-label">重复密码：</label> <input id="repeatPassword"
						name="passwordAgain" type="password" class="input"
						placeholder="请输入您的确认密码！">
				</div>
				<div class="input-group ">
					<label class="input-label">电子邮箱：</label> <input id="email"
						name="email" type="email" class="input" placeholder="请输入您的邮箱！" value="${user.email }">
				</div>

				<div class="input-group ">
					<label class="input-label">出生日期：</label> <input id="birthday"
						name="birth" type="text" class="input" data-language="zh"
						placeholder="请选择您的出生日期！" value="${user.birth }"/>
				</div>
				<div class="input-group">
					<label class="radio-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<div class="radio-group">
					<c:choose>
						<c:when test="${user.gender eq '男' }">
						<input type="radio" name="gender" id="male" value="男"
							checked="checked" required><label for="male">男</label> <input
							type="radio" name="gender" id="female" value="女"><label
							for="female">女</label>
						</c:when>
						<c:otherwise>
						<input type="radio" name="gender" id="male" value="男"
							><label for="male">男</label> <input
							type="radio" name="gender" id="female" value="女" checked="checked" required><label
							for="female">女</label>
						</c:otherwise>
					</c:choose>
						
					</div>
				</div>
				<div class="input-group clr">
					<label class="">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</label>
					<select class="select" name="profession.id">
						<c:forEach items="${professions }" var="profession">
							<c:choose>
								<c:when test="${user.profession.name eq profession.name }">
								<option value="${profession.id}">${profession.name }</option>
								</c:when>
								<c:otherwise>
								<option value="${profession.id}">${profession.name }</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
						<!-- <option value="teacher">老师</option>
						<option value="coder">程序猿</option>
						<option value="engineer">攻城狮</option>
						<option value="ui">UI设计</option> -->
					</select>
				</div>
				<div class="input-group">
					<label class="input-label">爱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;好：</label>
					<select class="multiple-select" name="hobbies.id"
						multiple="multiple">
						<c:forEach items="${hobbies }" var="hobby">
							<option value="${hobby.id}">${hobby.name }</option>
						</c:forEach>
						<!-- <option value="书法">书法</option>
						<option value="乐器">乐器</option>
						<option value="运动">运动</option>
						<option value="编程">编程</option> -->
					</select>
				</div>
			</div>
			<div class="form-right">
				<div class="upload-avatar ">
					<c:choose>
					<c:when test="${empty user.avatar }">
					<img src="${ctx }/images/avatar.png" name="avatar" id="image1" />
					</c:when>
					<c:otherwise>
					<img src="${ctx }/file/${user.avatar}" name="avatar" id="image1" />
					<input type="hidden" name="avatar" value="${user.avatar}">
					</c:otherwise>
					</c:choose>
					<%-- <img src="${ctx }/file/${user.avatar}" name="avatar" id="image1" /> --%>
					<p>
						<button type="button" class="upload-avatar-btn">上传头像</button>
					</p>
					<input type="file" name="imageFile" accept="image/*"
						onchange="document.all['image1'].src=getImgURL(this);"
						style="display: none;">
				</div>
			</div>
		</div>
		<div class="form-control">
			<hr>
			<button type="submit" id="saveUserBtn" class="button-green">保存</button>
			<button type="reset" class="button-blue">重置</button>
			<button type="button" id="returnUserListBtn" class="button-gray">返回</button>
		</div>
		<input name="id" value="${user.id}" type="hidden">
	</form>

	<%-- <form action="${ctx }/user/upload" method="post"
				class="form1" enctype="multipart/form-data" style="display: none;">
				<input type="file" name="imageFile" accept="image/*"
								onchange="document.all['image1'].src=getImgURL(this);"
								>
				</form> --%>

</section>
<footer id="footer"> Copyright © 2017.轻实训版权所有 </footer>
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
<script>
	$(function() {
		var $mainPanel = $(".main-panel");
		var $userForm = $mainPanel.find("form.form");
		var $uploadForm = $mainPanel.find("form.form1");
		
		//上传头像
		$userForm.find(".upload-avatar-btn").on("click", function() {
			$userForm.find("input[type='file']").trigger("click");

		});
		/* 
		 $uploadForm.find("input[type='file']").on("change",function(){
		
		 $uploadForm.trigger("submit");
		 });
		
		 // 初始化表单提交
		 $uploadForm.on("submit", function(e) {
		
		 // 阻止表单默认提交事件
		 e.preventDefault();
		 var $this = $(this);

		 // jquery-form的异步提交方式
		 $this.ajaxSubmit({
		 beforeSubmit : function(data, $form) {
		
		 alert(8888);
		 var file = $form.find("input[type='file']");
		 // 非空验证

		 if ($.trim(file.val()) === "") {
		 return showInputTip(file, "请输入名称");
		 } else {
		 hideInputTip(file);
		 }

		 },
		 success : function() {
		 // 刷新页面
		 // triggerCurrentMenu();
		 alert("上传成功")
		 }
		 });
		 }); */

		//日期插件
		$("#birthday").datepicker({
			autoClose : true
		});

		// 初始化表单提交
		$userForm.on("submit", function(e) {
			// 阻止表单默认提交事件
			e.preventDefault();
			var $this = $(this);

			// jquery-form的异步提交方式
			$this.ajaxSubmit({
				beforeSubmit : function(data, $form) {
					var name = $form.find("input[name='name']");
					var password=$form.find("input[name='password']");
					var passwordAgain=$form.find("input[name='passwordAgain']");
					var email=$form.find("input[name='email']");
					var birth=$form.find("input[name='birth']");
					// 非空验证
					if ($.trim(name.val()) === "") {
						return showInputTip(name, "请输入名称");
					} else {
						hideInputTip(name);
					}
					if ($.trim(password.val()) === "") {
						return showInputTip(password, "请输入密码");
					} else {
						hideInputTip(password);
					}
					
					if ($.trim(passwordAgain.val()) === "") {
						return showInputTip(passwordAgain, "请输入确认密码");
					} else {
						hideInputTip(passwordAgain);
					}
					
					if (passwordAgain.val() !== password.val()) {
						return showInputTip(passwordAgain, "两次输入的密码不一致");
					} else {
						hideInputTip(passwordAgain);
					}
					
					if ($.trim(email.val()) === "") {
						return showInputTip(email, "请输入邮箱");
					} else {
						hideInputTip(email);
					}
					
					if ($.trim(birth.val()) === "") {
						return showInputTip(birth, "请输入生日日期");
					} else {
						hideInputTip(birth);
					}

				},
				success : function() {
					// 刷新页面
					triggerCurrentMenu();
				}
			});
		});

	});
</script>