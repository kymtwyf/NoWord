<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="message" class="com.noword.bean.message.Message"/>
<jsp:useBean id="messageBean" class="com.noword.bean.message.MessageBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%--
为什么会出现乱码?有多种原因
这里的原因:接收参数时如果没指定接收的编码,则默认为iso8859-1
解决方法:设置request接收对象的编码为utf-8

 --%>

<%
request.setCharacterEncoding("utf-8");
message.setTitle(request.getParameter("title"));
message.setGuestName(request.getParameter("guestName"));
message.setQq(request.getParameter("qq"));
message.setEmail(request.getParameter("email"));
message.setHomepageUrl(request.getParameter("homepageUrl"));
message.setLeaveWord(request.getParameter("leaveWord"));
message.setIp(request.getRemoteHost());

String sex = request.getParameter("p1");
message.setSex(sex);

String p2 = request.getParameter("p2");
//构造个人形象图片的字符串
String pic = null;
if ("m".equals(sex)) {
	pic = "m" + p2 + ".gif";
}
if ("w".equals(sex)) {
	pic = "w" + p2 + ".gif";
}
message.setPic(pic);

messageBean.saveMessage(message);

 %>
 
<!-- 这里通过javascript完成页面跳转 -->
<script type="text/javascript">
<!--
alert("留言成功!");
window.location = "<%=basePath%>" + "page/jsp/index.jsp";
//-->
</script>


