<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="mainPanel" class="main-panel">
	<section class="toolbar">
		<button type="button">新增爱好</button>
	</section>
	<section>
		<table class="table">
			<thead>
				<tr>
					<td>序号</td>
					<td>爱好名称</td>
					<td>创建时间</td>
					<td align="center">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="hobby" varStatus="status">

			<tr data-id="${hobby.id }">
				<td>${status.count }</td>
			  	<td>${hobby.name }</td>
			  	<td>${hobby.creatTime }</td>
				<td><a href="${ctx }/hobby/edit" data-id="${hobby.id }">编辑</a> <a
						href="${ctx }/hobby/delete" data-id="${hobby.id }">删除</a></td>
			</tr>
		</c:forEach>
			</tbody>
		</table>
	</section>
</div>
<footer id="footer"> Copyright © 2017.轻实训版权所有 </footer>
<script type="text/javascript">
	$(function(){
		var $mainPanel=$("#mainPanel");
		
		var $table=$mainPanel.find("table");
		var $operator = $table.find("tbody").find("tr");
		
		//删除一条记录
		$operator.find("a:last").on("click", function(e) {
			var $this = $(this);
			//阻止a标签原有的默认事件
			e.preventDefault();
			var id = $this.data("id");
			//得到a标签href属性的值
			var pageRef = $this.prop("href");
			if (confirm("确定要删除选中项？")) {
				$.ajax({
					cache : true,
					type : "GET",
					url : pageRef,
					data : {
						id : id
					},
					error : function(request) {
						alert("Connection error");
					},
					success : function(data) {
						if (data.success) {
							// 刷新页面
							triggerCurrentMenu();
						} else {
							alert("删除失败！");
						}
					}

				});
			}
		});

		//编辑信息
		$operator.find("a:first").on("click", function(e) {
		var $this = $(this);	
		//阻止a标签原有的默认事件
		e.preventDefault();
		var id=$this.data("id");
		//得到a标签href属性的值
		var pageRef = $this.prop("href");

		
		/* $rightContentPanel.load(pageRef, {
			//左边是Action里的值，右边是js定义的变量
			id: id
		}); */
		
		$.ajax({
			cache : true,
			type : "GET",
			url:pageRef,
			data :{id:id},
			error : function(request) {
				alert("Connection error");
			},
			success : function(data) {
				$rightContentPanel.html(data);
			}
		});
		
	});
		
		$mainPanel.find(".toolbar").find("button").on("click",function(){
			loadMainContent("/hobby/add");
		});
		
		
	});
</script>
