<!-- 删除留言信息 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="messageBean" class="com.noword.bean.message.MessageBean"></jsp:useBean>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
//删除id为传递过来的id的留言记录
String idStr = request.getParameter("id");

try{
	int id = Integer.parseInt(idStr);
	messageBean.deleteMessage(id);
}catch(Exception e) {
	e.printStackTrace();
}

 %>
<script type="text/javascript">
<!--
alert("删除成功!");
window.location = "<%=basePath%>" + "page/jsp/index.jsp";
//-->
</script>

