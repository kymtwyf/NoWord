/**
 * @param contentpart �����ʵ�һ����
 * @param questionpart �����ʵڶ�����
 */


$(document).ready(function(){
	
	//�رհ�ť
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