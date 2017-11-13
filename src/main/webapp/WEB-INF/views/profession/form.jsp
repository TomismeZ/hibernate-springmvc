<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section class="main-panel">
	<c:choose>
		<c:when test="${empty profession }">
		<h4 class="form-title">新增职业信息</h4>
		<form name="professionForm" action="${ctx }/profession/save" method="post" class="form">
		<div class="form-input">
			<div class="form-left">
				<div class="input-group ">
					<label class="input-label">职业名称：</label> <input id="username"
						name="name" type="text" class="input" placeholder="职业名称...">
				</div>
				
			</div>
			
		</div>
		<div class="form-control">
			<hr>
			<button type="submit" id="saveUserBtn" class="button-green">保存</button>
			<button type="reset" class="button-blue">重置</button>
			<button type="button" id="returnUserListBtn" class="button-gray">返回</button>
		</div>
	</form>
		</c:when>
		<c:otherwise>
		<h4 class="form-title">编辑职业信息</h4>
	<form name="userForm" action="${ctx }/profession/save" method="post" class="form">
		<div class="form-input">
			<div class="form-left">
			
				<div class="input-group ">
					<label class="input-label">职业名称：</label> <input id="username"
						name="name" type="text" class="input" value="${profession.name }">
				</div>
				
			</div>
			
		</div>
		<div class="form-control">
			<hr>
			<button type="submit" id="saveUserBtn" class="button-green">保存</button>
			<button type="reset" class="button-blue">重置</button>
			<button type="button" id="returnUserListBtn" class="button-gray">返回</button>
		</div>
		<input type="hidden" name="id" value="${profession.id }">
	</form>
		</c:otherwise>
	</c:choose>
	
</section>
<footer id="footer"> Copyright © 2017.轻实训版权所有 </footer>

<script>
	$(function(){
		var $mainPanel=$(".main-panel");
		var $professionForm=$mainPanel.find("form");
		
		// 初始化表单提交
		$professionForm.on("submit", function (e) {
			// 阻止表单默认提交事件
			 e.preventDefault();
			var $this = $(this);

			// jquery-form的异步提交方式
			$this.ajaxSubmit({
				beforeSubmit: function (data, $form) {
					var name = $form.find("input[name='name']");
					// 非空验证
					
					if ($.trim(name.val()) === "") {
						return showInputTip(name, "请输入名称");
					} else {
						hideInputTip(name);
					}
					
				},
				success: function () {
					// 刷新页面
					triggerCurrentMenu();
				}
			}); 
		});	
		
		
	});
</script>