<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noword.bean.message.Message"%>
<%@ page import="com.noword.bean.message.User" %>
<jsp:useBean id="messageBean" class="com.noword.bean.message.MessageBean"/>
<%
//发布在tomcat中可以有多个网站(也称web应用),可通过request.getContextPath()获得当前网站名称
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

User user = null;
if(session.getAttribute("loginUser") != null) {
	user = (User) session.getAttribute("loginUser");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<!--base标签作用是指定当前的根目录  -->
		<base href="<%=basePath%>">
		<title>NOWORD--无言本--</title>
		<!-- 引入外联样式表 -->
		<link href="pages/space.css" rel="stylesheet" type="text/css">
		<!-- javascript -->
		<script type="text/javascript" src="js/ajax.js"></script>
		<script language="javascript">
		
		function checkForm() {
			//控制主题输入长度
			if(document.getElementById("title").value.length>20) {
				alert("输那么多字好看么！主题不要超过20个字！！");
				return false;
			}
			//控制留言信息长度
			if(document.getElementById("leaveWord").value.length>400) {
				alert("你想把我的数据库挤爆啊？说这么多！");
				return false;
			}
		}
		</script>
	</head>
	<body topmargin="0">
	<div class="mainContainer" id="mainContainer">
  		<div class="containerShadowForMessage">
  			<div class="head">
    		</div>
  			
	     		<div class ="fadebutton" id = "myspace">
					<a href="myspace.xhtml" target="_self" style="color:#FFFFFF">我的空间</a>
				</div>
		<div class = "messagetable">
		<table align="center" width="675" border="0" cellpadding="0" 
			cellspacing="0">
			
			<tr>
				<td width="675">
				<!-- form表单,onSubmit提交时调用javascript,action指定处理页面,
				method是参数传递方式(get为直接显示在浏览器地址栏,即url;post为封装式发送)
				 -->
					<form name="messageForm" id="messageForm" method="post" onsubmit="checkForm()"
						action="page/jsp/save.jsp">
						<div align="center">
							<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="3">
											<img src="pages/images/img/frame_tct.gif" width="500" height="140"
												border="0" usemap="#Map"></td>
									</tr>
									<tr>
										<td width="12" background="pages/images/img/frame_tcl.gif"></td>
										<td bgcolor="#ffffff" width="476" align="center">
											<table width="421" border="0" cellpadding="0" cellspacing="0">
												<tbody>
													<tr>
														<td align="center" width="164" valign="middle" height="30">
															<div align="right">
																<img src="pages/images/img/Fi_fu1.gif" width="12" height="12"
																	border="0">
															</div></td>
														<td width="40" valign="middle">
															
														</td>
														<td align="center" width="21" valign="middle">
															<img src="pages/images/img/Fi_fu1.gif" width="12" height="12"
																border="0"></td>
														<td width="40" valign="middle">
															<%if(user != null){
																out.print(user.getUsername() + ",您好!");
															}
															 %></td>
														<td align="center" width="16" valign="middle">
															<img src="pages/images/img/Fi_fu2.gif" width="12" height="12"
																border="0"></td>
														<td width="41" valign="middle">
															<%
															//因为登录成功时已经往session中添加userType属性,如果取出来为空的话,则未登录
															//如果已经登录,显示登出链接;如果还未登录,则显示登录链接
																if(user != null){
															 %>
															
															<%}else{ %>
															
															<%} %>
															
														</td>
														<td align="center" width="15" valign="middle">
															<img src="pages/images/img/Fi_fu1.gif" width="12" height="12"
																border="0"></td>
													</tr>
												</tbody>
											</table>
											<table border="0" cellpadding="0" cellspacing="0">
												<tbody>
													<tr>
														<td valign="middle">
															<table width="288" border="0" cellpadding="0"
																cellspacing="0">
																<tbody>
																	<tr>
																		<script>function showimage(){document.images.tus.src="pages/images/face/"+document.form.p1.options[document.form.p1.selectedIndex].value+""+document.form.p2.options[document.form.p2.selectedIndex].value+".gif";}</script>
																		<td style="color: #999900;">
																			主题:
																		</td>
																		<td width="6"></td>
																		<td>
																			<input name="title" type="text" class="inp_set1" style="backgroundColor:#77fdf7"
																				id="title"
																				onFocus="this.style.backgroundColor='#77fdf7'"
																				onBlur="this.style.backgroundColor='#FFFFFF'"
																				value="路过" size="30" >
																		</td>
																	</tr>

																</tbody>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="3" align="center" height="30" valign="middle">
															<table border="0" cellpadding="0" cellspacing="0">
																<tbody>
																	<tr>
																	
																		<td>
																			&nbsp;
																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
												</tbody>
											</table>
											<textarea name="leaveWord" cols="55" rows=8 wrap="soft"
												class="inp_set1" id="leaveWord"
												onFocus="this.style.backgroundColor='#77fdf7'"
												onBlur="this.style.backgroundColor='#FFFFFF'"></textarea>
											<table cellpadding="0" cellspacing="0">
												<tbody>
													<tr>
														<td valign="middle">
														<!-- 提交按钮,type为submit -->
															<input type="submit" value="我写好咯" class="inp_set1"
																onFocus="this.style.backgroundColor='#77fdf7'"
																onBlur="this.style.backgroundColor='#FFFFFF'"
																name="Submit">
														</td>
														<td width="5"></td>
														<td valign="middle">
														<!-- 重写按钮,type为reset -->
															<input type="reset" value="重写一回" class="inp_set1"
																onFocus="this.style.backgroundColor='#77fdf7'"
																onBlur="this.style.backgroundColor='#FFFFFF'"
																name="reset">
														</td>
													</tr>
												</tbody>
											</table>
											<table border="0" cellpadding="0" cellspacing="0">
												<tbody>
													<tr>
														<td valign="bottom" align="left">
															<img src="pages/images/img/Fi_har.gif" width="28" height="20"
																border="0" align="middle">
															<img src="pages/images/img/Fi_jo.gif" width="37" height="26"
																border="0" align="middle" hspace="3">
														</td>
														<td width="300" height="29" align="center" valign="bottom">
															<table border="0" cellpadding="0" cellspacing="0"
																width="197">

															</table>
														</td>
														<td valign="bottom" align="right">
															<img src="pages/images/img/Fi_hasa.gif" width="24" height="23"
																border="0" hspace="4" align="bottom">
															<img src="pages/images/img/Fi_sc.gif" width="24" height="22"
																border="0" vspace="3" align="top">
														</td>
													</tr>
												</tbody>
											</table>
										</td>
										<td width="12" background="pages/images/img/frame_tcr.gif"></td>
									</tr>
									<tr>
										<td valign="top" colspan="3">
											<img src="pages/images/img/frame_tcb.gif" width="500" height="12"
												border="0">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>

				</td>
			</tr>

			<tr>
				<td align="center">
				<%
					List<Message> list = messageBean.findAll();
					if(list == null || list.size()<=0){
				 %>
				 暂无留言!
				 <%}else {
				 	/*分页实现*/
				 	//总条数
				 	int total = list.size();
				 	//每页显示条数
				 	int pageSize = 5;
				 	//总页数
				 	int totalPage = 0;
				 	if(total % pageSize == 0){
				 		totalPage = total / pageSize;
				 	}else{
				 		totalPage = total / pageSize + 1;
				 	}
				 	//当前页数
				 	int currentPage = 1;
				 	try{
				 		currentPage = Integer.parseInt(request.getParameter("currentPage"));
				 	}catch(Exception e) {
				 		currentPage = 1;
				 	}
				 	if(currentPage < 1) {
				 		currentPage = 1;
				 	}
				 	if(currentPage > totalPage) {
				 		currentPage = totalPage;
				 	}
				 	
				 	int start = (currentPage - 1) * pageSize;
				 	int end = currentPage * pageSize;
				 	if(end > total) {
				 		end = total;
				 	}
				 	
				 	List<Message> currentList = list.subList(start, end);
					 for(Message message : currentList) {	 
				%>
					<table align="center" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td valign="top" colspan="3" align="left">
									<img src="pages/images/img/frame_tgt.gif" width="100%" height="100"
										border="0"></td>
							</tr>
							<tr>
								<td width="12" background="pages/images/img/frame_tgl.gif"></td>
								<td bgcolor="#ffffff" align="center" width="476">
									<table cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
												<td width="103" class="inp_set2">
													<p>
														IP:
														<br>
														<%=message.getIp() %>
													</p>
												</td>
												<td width="3"></td>
												<td>
													<table width="366" border="0" align="right" cellpadding="0"
														cellspacing="0">
														<tbody>
															<tr>
																<td width="82" style="color: #528e31;">
																	时间:<br>
																	<%=message.getCreateTime() %>
																</td>
																<td valign="bottom" width="12">
																	<img src="pages/images/img/Fi_fu1.gif" width="12" height="12"
																		border="0">
																</td>
																<td width="195" align="center" valign="bottom"
																	style="color: #528e31;">
																	主题:<%=message.getTitle() %></td>
																<td valign="bottom" width="36">
																	<img src="pages/images/img/Fi_fu1.gif" width="12" height="12"
																		border="0">
																	<a
																		href="http://wpa.qq.com/msgrd?V=1&Uin=<%=message.getQq()%>&Site=asdfasdf&Menu=yes"
																		target="_blank"><img src="pages/images/pic/qq.gif"
																			alt="点击给我发消息" width="23" height="16" border="0">
																	</a>
																</td>
																<td width="25">

																</td>
																<td width="76" valign="bottom" align="center">
																	<a target="_blank"
																		href="<%=message.getHomepageUrl() %>"><img
																			border="0" src="pages/images/pic/home.gif" alt="偶的竹叶。。。"
																			width="21" height="20"> </a><a
																		href="mailto:<%=message.getEmail() %>"><img
																			border="0" src="pages/images/img/fi_mp.gif" alt="伊妹儿" width="17"
																			height="15"> </a>
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td width="103" align="center">
													<p>
														<img src="pages/images/face/<%=message.getPic() %>" width="65"
															height="110" border="0" align="absmiddle">
													</p>
													<p style="color: #528e31;">
														
														昵称:<%=message.getGuestName() %><br/>
														<%
														//如果用户已经登录,判断是否为管理员,如果是,生成"删除"链接
														if(user != null && user.getUserType() == 0) {
														%>
															<a href="page/jsp/delete.jsp?id=<%=message.getId() %>">删除</a>
														<%} %></p>
												</td>
												<td width="3"></td>
												<td width="368">
													<table cellpadding="5" cellspacing="1" width="100%"
														bgcolor="#c2e4ae">
														<tbody>
															<tr>
																<td bgcolor="#ffffff" align="center" valign="bottom">
																	<br>
																	<table cellpadding="0" cellspacing="0">
																		<tbody>
																			<tr>
																				<td align="center">
																					<table border="0" cellpadding="0" cellspacing="0">
																						<tbody>
																							<tr>
																								<td height="20" align="center" valign="top"
																									style="color: #528e31;">
																									
																									<p align="left"><%=message.getLeaveWord() %>
																									<p align="left">
																										
																									
																								</td>
																							</tr>
																							<tr>
																								<td width="300">
																									<img src="pages/images/img/fi_lg.gif" width="300"
																										height="12" border="0">
																								</td>
																							</tr>
																						</tbody>
																					</table>
																				</td>
																			</tr>
																		</tbody>
																	</table>
																	<br>
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td width="12" background="pages/images/img/frame_tgr.gif">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td valign="top" colspan="3" height="12">
									<img src="pages/images/img/frame_tgb.gif" width="100%" height="12"
										border="0">
								</td>
							</tr>
						</tbody>
					</table>
				<%}%>
				</td>

			</tr>


			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td style="color: #528e31;text-align: center">
				共<span style="color:#000000"><%=total %></span>条留言&nbsp;&nbsp;&nbsp;&nbsp;
					共<span style="color:#000000"><%=totalPage %></span>页&nbsp;&nbsp;&nbsp;&nbsp;
					<%if(currentPage>1){ %>
					<a href="page/jsp/index.jsp?currentPage=<%=currentPage-1 %>">上一页</a>&nbsp;&nbsp;
					<%} %>
					第<span style="color:#000000"><%=currentPage %></span>页&nbsp;&nbsp;
					<%if(currentPage<totalPage){ %>
					<a href="page/jsp/index.jsp?currentPage=<%=currentPage+1 %>">下一页</a>&nbsp;&nbsp;
					<%} %>
				</td>
			</tr>
			
			<%}%>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
	</div>
	</div>
	</body>
</html>

