<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 用户登录检查页面 -->
<jsp:useBean id="user" class="com.noword.bean.message.User"></jsp:useBean>
<jsp:useBean id="userBean" class="com.noword.bean.message.UserBean"></jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
String username = new String(request.getParameter("username").getBytes("iso8859-1"),"utf-8");
String password = new String(request.getParameter("password").getBytes("iso8859-1"),"utf-8");

if(userBean.checkUser(username, password) != null) {
user = userBean.checkUser(username, password);

session.setAttribute("loginUser",user);

 %>
<script type="text/javascript">
<!--
alert("登录成功!");
window.location = "<%=basePath%>page/jsp/index.jsp";
//-->
</script>
<%
}else{
//若不存在,则返回登录页面
 %>
<script type="text/javascript">
<!--
alert("登录失败!");
window.location = "<%=basePath%>page/jsp/login.jsp";
//-->
</script>
 <%} %>
