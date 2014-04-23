<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Random"%>  
<%@ page import="java.io.OutputStream"%>  
<%@ page import="java.awt.Color"%>  
<%@ page import="java.awt.Font"%>  
<%@ page import="java.awt.Graphics"%>  
<%@ page import="java.awt.image.BufferedImage"%>  
<%@ page import="javax.imageio.ImageIO"%> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	int width = 60;
	int height = 22;
	//create the image
	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	// set the background color
	g.setColor(new Color(0xDCDCDC));
	g.fillRect(0, 0, width, height);
	// draw the border
	g.setColor(Color.black);
	g.drawRect(0, 0, width - 1, height - 1);
	// create a random instance to generate the codes
	Random rdm = new Random();
	String hash1 = Integer.toHexString(rdm.nextInt());
	// make some confusion
	for (int i = 0; i < 50; i++) {
		int x = rdm.nextInt(width);
		int y = rdm.nextInt(height);
		g.drawOval(x, y, 0, 0);
	}
	// generate a random code
	String capstr = hash1.substring(0, 4);
	session.setAttribute("key", capstr);
	g.setColor(new Color(0, 100, 0));
	g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	g.drawString(capstr, 11, 17);
	g.dispose();
	response.setContentType("image/jpeg");
	out.clear();
	out = pageContext.pushBody();
	OutputStream strm = response.getOutputStream();
	ImageIO.write(image, "jpeg", strm);
	strm.close();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'validate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
