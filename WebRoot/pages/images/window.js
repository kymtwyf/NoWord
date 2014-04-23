/**
 * @param centerwin �м䱳���ʴ���
 * @param left ��߱ʼǴ���
 * @param rightwin �ұ�С��洰��
 */

//ע�⣺û���IE6��IE8֧�֣������Opera,Safari,Chrome��Firefox֧�֡�

$(document).ready(function(){
	
	//�رհ�ť
	/*$(".title img").click(function(){
		$(this).parent().parent().hide("slow");
	});*/
	
	//�м䱳���ʴ���
	var centerwin = $("#center");
	var leftwin = $("#left");
	var rightwin = $("#right");
	/*
	$("#mynote").click(function(){
		leftwin.slideDown("slow");
	});
	$("#recite").click(function(){
		var contentpart = document.getElementById("centercontent");
		var answer = document.getElementById("answerDiv");
		var questionpart = document.getElementById("questionDiv");
		centerwin.show("slow");
		questionpart.style.display="none";
		answer.style.display="none";
		contentpart.style.display="block";
		
	});*/
	setTimeout(function(){
		centerwin.mywin({left:"center", top:"center"});
		leftwin.mywin({left:"left", top:"bottom"},function(){
			leftwin.slideUp("slow");
		});
		var windowobj = $(window);
		var cwinwidth = rightwin.outerWidth(true);
		var cwinheight = rightwin.outerHeight(true);
		var browserwidth = windowobj.width();
		var browserheight = windowobj.height();
		var scrollLeft = windowobj.scrollLeft();
		var scrollTop = windowobj.scrollTop();
		var rleft = scrollLeft + browserwidth - cwinwidth;
		if($.browser.safari){//��safari��Chrome
			rleft -= 15;
		}
		if($.browser.opera){//��opera
			rleft += 15;
		}
		//15s����
		rightwin.mywin({left:"right", top:"bottom"},function(){
			rightwin.hide();
		}, {left:rleft ,top: scrollTop + browserheight}).fadeOut(15000).dequeue();
	},500);
});

//���������������
$.fn.mywin = function(position, hidefunc,initPos){
	if(position && position instanceof Object){
	
		var positionleft = position.left;
		var positiontop = position.top;
		var left;
		var top;
		var windowobj = $(window);
		var currentwin = this;
		var cwinwidth = currentwin.outerWidth(true);
		var cwinheight = currentwin.outerHeight(true);
		var browserwidth;
		var browserheight;
		var scrollLeft;
		var scrollTop;
		
		//�����������������Ŀ�͸ߣ���������߽���ϱ߽��ֵ
		function getBrowserDim(){
			browserwidth = windowobj.width();
			browserheight = windowobj.height();
			scrollLeft = windowobj.scrollLeft();
			scrollTop = windowobj.scrollTop();
		}	
		
		//���㴰����ʵ��߽�ֵ
		function calLeft(positionleft,scrollLeft,browserwidth,cwinwidth){
			if(positionleft && typeof positionleft == "string"){
				if(positionleft == "center"){
					left = scrollLeft + (browserwidth - cwinwidth) / 2;	
				}else if(positionleft == "left"){
					left = scrollLeft;
				}else if(positionleft == "right"){
					left = scrollLeft + browserwidth - cwinwidth;
					if($.browser.safari){//��safari��Chrome
						left -=15;
					}
					if($.browser.opera){//��opera
						left += 15;
					}
				}else{
					//Ĭ��center
					left = scrollLeft + (browserwidth - cwinwidth) / 2;	
				}
			}else if(positionleft && typeof positionleft == "number"){
				left = positionleft;
			}else{
				left = 0;
			}
		}
		
		//���㴰����ʵ�ϱ߽�ֵ
		function calTop(positiontop,scrollTop,browserheight,cwinheight){
			if(positiontop && typeof positiontop == "string"){
				if(positiontop == "center"){
					top = scrollTop + (browserheight - cwinheight) / 2;
				}else if(positiontop == "top"){
					top = scrollTop;
				}else if(positiontop == "bottom"){
					top = scrollTop + browserheight - cwinheight;
					if($.browser.opera){//��opera
						rleft -= 25;
					}
				}else{
					//Ĭ��center
					top = scrollTop + (browserheight - cwinheight) / 2;
				}
			}else if(positiontop && typeof positiontop == "number"){
				top = positiontop;
			}else{
				top = 0;
			}
		}
		
		//�ƶ�����λ��
		function moveWin(){
			calLeft(currentwin.data("positionleft"),scrollLeft,browserwidth,cwinwidth);
			calTop(currentwin.data("positiontop"),scrollTop,browserheight,cwinheight);
			//currentwin.css("left",left).css("top",top);	
			currentwin.animate({
				left: left,
				top: top
			},600);
		}

		//�ر�
		currentwin.css("left",left).css("top",top);	
		currentwin.children(".title").children("img").click(function(){
			if(!hidefunc){
				currentwin.hide("slow");
			}else{
				hidefunc();
			}
		});
	}
	
	if(initPos &&initPos instanceof Object){
		var initLeft = initPos.left;
		var initTop = initPos.top;
		if(initLeft && typeof initLeft == "number"){
			currentwin.css("left",initLeft);
		}else{
			currentwin.css("left",0);
		}
		if(initTop && typeof initTop == "number"){
			currentwin.css("top",initTop);
		}else{
			currentwin.css("top",0);
		}
		currentwin.show();
	}
	
	currentwin.data("positionleft",positionleft);
	currentwin.data("positiontop",positiontop);
	getBrowserDim();
	moveWin();
		
	var scrollTimeout;
	//����������ʱ�ƶ�����λ��
	$(window).scroll(function(){
		if(!currentwin.is(":visible")){
			return;
		}
		clearTimeout(scrollTimeout);
		scrollTimeout = setTimeout(function(){
			getBrowserDim();
			moveWin();
		},300);
	});
	//�������С�ı�ʱ�ƶ�����λ��
	$(window).resize(function(){
		if(!currentwin.is(":visible")){
			return;
		}
		getBrowserDim();
		moveWin();
	});
	
	return currentwin;
}