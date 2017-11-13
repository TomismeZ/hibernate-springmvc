<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="mainPanel" class="main-panel">
	<section class="toolbar">
		<button class="add">新增用户</button>
		<button class="delete">批量删除</button>
		<label for="">用户名称：</label> <input type="text" name="userName" />
		<button type="button">查询</button>
		<button>重置</button>
	</section>
	<section>
		<table class="table">
			<thead>
				<tr>
					<td>用户名称</td>
					<td>职业</td>
					<td>邮箱</td>
					<td>性别</td>
					<td>生日</td>
					<td>爱好</td>
					<td>创建时间</td>
					<td align="center">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.list}" var="user">

			<tr data-id="${user.id }">
				<td> <input type="checkbox" name="checkbox" data-id="${user.id }" />${user.name }</td>
				<td>${user.profession.name }</td>
				<td>${user.email }</td>
				<td>${user.gender }</td>
			  	<td>${user.birth }</td>
			  	<td>
			  	<%-- <c:forEach items="${user.hobbies}" var="hobby">
			  	${hobby.name }
			  	</c:forEach> --%>
			 
			  	</td>
			  	<td>${user.creatTime }</td>
				<td><a href="${ctx}/user/edit" data-id="${user.id }">编辑</a> <a
						href="${ctx}/user/delete" data-id="${user.id }">删除</a></td>
			</tr>
		</c:forEach>
			</tbody>
		</table>
	</section>
	
	<div class="footer" style="float: right; margin-right: 15px;">
<input type="hidden"
			data-currentpage="${pageBean.currentPage }"> <input
			type="hidden" value="${pageBean.allRows }"> <input
			type="hidden" data-totalpages="${pageBean.totalPage }">

		<ul class="pagination" id="pagination1"></ul>
	</div>
</div>
<footer id="footer"> Copyright © 2017.轻实训版权所有 </footer>
<script type="text/javascript">
	$(function(){
		var $mainPanel = $("#mainPanel");

		var $table = $mainPanel.find("table");
		var $operator = $table.find("tbody").find("tr");

		/**
		 * 实现全选功能
		 */
		$table.find("thead").find("td:first").on("click", function() {
			// 获取列表中所有的checkbox
			var checkboxItems = $table.find("input[name='checkbox']");

			// 获取列表中选中的checkBox
			var checkedboxItems = $table.find("input[name='checkbox']:checked");

			// 如果两个列表的数量相等，表示所有的checkbox都被选中，则反选
			// 反之，则全选
			var isChecked = checkboxItems.length === checkedboxItems.length;

			// 去两个列表长度判断的反值
			checkboxItems.prop("checked", !isChecked);
		});
		/**
		 * 选中一行就可以选中复选框，除了第一个和最后一个td
		 */
		$table.find("tbody").find("tr td:not(:first-child,:last-child)").on(
				"click", function() {
					var $this = $(this).parent();
					//高亮处理
					$table.find("tbody").find("tr").removeClass("active");
					$this.addClass("active");
					var nowItem = $this.find("input[name='checkbox']");
					// 获取当前行CheckBox的状态值
					var isChecked = nowItem.prop("checked");

					// 最新的状态值只需要与当前状态值相反即可
					nowItem.prop("checked", !isChecked);
				});
		//删除一条记录
		$operator.find("a:last").on("click", function(e) {
			var $this = $(this);
			//阻止a标签原有的默认事件
			e.preventDefault();
			var id = $this.data("id");
			//得到a标签href属性的值
			var pageRef = $this.prop("href");
			if (!confirm("确定要删除选中项？")) {
				return false;
			}
			
			doAction("/user/delete", {
				id : id
			}, function(data) {
				// 刷新列表
				triggerCurrentMenu();
			}, "删除成功！");
			
			/* $.ajax({
				cache : true,
				type : "POST",
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

			}); */
		});

		//编辑信息
		$operator.find("a:first").on("click", function(e) {
			var $this = $(this);
			//阻止a标签原有的默认事件
			e.preventDefault();
			var id = $this.data("id");
			//得到a标签href属性的值
			var pageRef = $this.prop("href");

			/* $rightContentPanel.load(pageRef, {
				//左边是Action里的值，右边是js定义的变量
				id: id
			}); */

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
					$rightContentPanel.html(data);
				}
			});

		});
		
		//跳转到登录界面
		$mainPanel.find(".toolbar").find(".add").on("click", function() {
			loadMainContent("/user/add");
		});
		
		//批量删除
		$mainPanel.find(".toolbar").find(".delete").on("click",function(){
			var ckbs = $operator.find("input[name='checkbox']:checked");
			
			var userIds=new Array();
			if (ckbs.size() == 0) {
				alert("请选择待删除用户！");
				return false;
			} 
			
			if (!confirm("确定要删除选中项？")) {
				return false;
			}
			
			// 获取所有被选中项的id列表
			ckbs.each(function(i) {
				userIds[i] = $(this).data("id");
			});
			
			console.log(userIds);
			
			
			$.ajax({
				  traditional: true,
			        cache: true,
			        type: "POST",
			        url:"${ctx}/user/deletes",
			        data:{
			        	userIds : userIds
			        },
			        async: true,
			        error: function(request) {
			        	alert("Connection error");
			        },
			        success: function(data) {
			        	
			        	if(data.success){
			        		triggerCurrentMenu();
			        		alert("删除成功！")
			        	}
			        }
			    });
			
		});
		//Jquery分页插件用到时的变量
		var $inputFirst = $mainPanel.find(".footer").find("input:first");
		var $inputLast = $mainPanel.find(".footer").find("input:last");
		var $inputallRows = $mainPanel.find(".footer").find(
				"input:not(:first,:last)");
		var totalPages = $inputLast.data("totalpages");
		var currentPage = $inputFirst.data("currentpage");
		var allRows = $inputallRows.val();

		
		if (totalPages == 0) {
			totalPages = 1;
		}
		/**
		 * jQuery 分页插件
		 */

		$.jqPaginator(
						'#pagination1',
						{
							totalPages : totalPages,
							visiblePages : 8,
							currentPage : currentPage,
							first : '<li class="first"><a href="javascript:;">首页</a></li>',
							prev : '<li class="prev"><a href="javascript:;" data-page="${pageBean.currentPage - 1 }">上一页</a></li>',
							next : '<li class="next"><a href="javascript:;" data-page="${pageBean.currentPage + 1 }">下一页</a></li>',
							last : '<li class="last"><a href="javascript:;" data-page="${pageBean.totalPage }">尾页</a></li>',
							page : '<li class="page"><a href="javascript:;" data-page="{{page}}">{{page}}</a></li>',
						/* onPageChange : function(num, type) {
							$('font').text(type + '：' + num);
						} */
						});
		
		/**
		实现分页（超链接跳转功能）模块
		**/
		$mainPanel.find(".footer").find("a").on(
				"click", function(e) {
					
					var $this = $(this);
					var mythis=$(this);
					
					var page = $this.data("page");

					console.log(page);
					//阻止a标签原有的默认事件
					e.preventDefault();
					//得到a标签href属性的值
					/* var pageRef = $this.prop("href"); */
					var pageRef=g_rootPath+"/user/list";
					$rightContentPanel.load(pageRef, {
						page : page
					});

				});
	});
	
	
	
</script>