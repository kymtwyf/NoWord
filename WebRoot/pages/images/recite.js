/**
 * @param contentpart 背单词第一部分
 * @param questionpart 背单词第二部分
 */


$(document).ready(function(){
	
	//关闭按钮
	/*$(".title img").click(function(){
		$(this).parent().parent().hide("slow");
	});*/
	
	//var contentpart = $("#centercontent");
	var contentpart = document.getElementById("centercontent");
	var questionpart = document.getElementById("questionDiv");
	
	$("#noWordsButton").click(function(){
		contentpart.style.display="none";
		questionpart.style.display="block";
	});
});