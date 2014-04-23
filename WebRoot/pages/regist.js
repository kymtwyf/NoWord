  //<![CDATA[	
 function   validateCode() {    
       document.getElementById('getCode').src="validateCode.jsp?"+Math.ceil(Math.random()*1000);    
     }   
  function userNameTip(){
  	var userName  = document.getElementById("userNameField").value;
   	if(userName.length ==0)
   	{
   		document.getElementById("checkUserNameField").value="5-25个字符，一个汉字为两个字符";
  		return false;
   	}else if(userName.length<5||userName.length>25)
   	{
  		document.getElementById("checkUserNameField").value="5-25个字符";
  		return false;
  	}
  	else
  	{
  		document.getElementById("checkUserNameField").value="";
    	return true;
  	}
  }
   function checkUserName(){
    	var userName  = document.getElementById("userNameField").value;
    	if(userName.length==0)
    	{
    		document.getElementById("checkUserNameField").value="不能为空!"; 
    		return false;
    	}
    	else if(userName.length<5||userName.length>25)
    	{
	    	document.getElementById("checkUserNameField").value="5-25个字符"; 
	    	return false;
    	}
    	else
    	{
    		document.getElementById("checkUserNameField").value="";
    		return true;
    	}
    }
    
    function codeTip(){
    	var code = document.getElementById("codeField").value;
    	if(code.length<5 || code.length>15){
    		document.getElementById("checkCodeField").value="5-15个字符";
    		document.getElementById("checkCodeField").style.left="470px";
    		return false;
    	}
    	else{
    		document.getElementById("checkCodeField").value="";
    		return true;
    	}
    }
    
    function codeBlurTip(){
    	var code = document.getElementById("codeField").value;
    	if(code.length==0){
    		document.getElementById("checkCodeField").value="不能为空";
    		document.getElementById("checkCodeField").style.left="300px";
    		return false;
    	}
    	else if(code.length<5 || code.length>15){
    		document.getElementById("checkCodeField").value="5-15个字符";
    		document.getElementById("checkCodeField").style.left="470px";
    		return false;
    	}
    	else{
    		document.getElementById("checkCodeField").value="";
    		return true;
    	}
    }
    	
    function checkCode(){
      var code = document.getElementById("codeField").value;
      var reCode = document.getElementById("reCodeField").value;
      if(code!=reCode){    
      	document.getElementById("checkReCodeField").value="两次输入的密码不一致";  
      	return false;
     }
     else
     {
     	document.getElementById("checkReCodeField").value="";
     	return true;
     }
    }
    
    function emailTip()
    {
    	var email = document.getElementById("emailField").value;
    	if(email.length==0)
    	{
    		document.getElementById("checkEmail").value="常用邮箱，用于找回密码";  
      		return false;
    	}
    }
    
    function emailBlurTip()
    {
    	var email = document.getElementById("emailField").value;
    	if(email.length==0)
    	{
    		document.getElementById("checkEmail").value="不能为空";  
      		return false;
    	}
    	else
    	{
    		document.getElementById("checkEmail").value="";  
      		return true;
    	}
    }
    //]]>  