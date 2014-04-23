/**
 * @param centerwin 中间背单词窗口
 * @param left 左边笔记窗口
 * @param rightwin 右边小广告窗口
 */

//注意：没添加IE6和IE8支持，已添加Opera,Safari,Chrome和Firefox支持。

$(document).ready(function(){
	
	//关闭按钮
	/*$(".title img").click(function(){
		$(this).parent().parent().hide("slow");
	});*/
	
	//中间背单词窗口
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
		if($.browser.safari){//对safari和Chrome
			rleft -= 15;
		}
		if($.browser.opera){//对opera
			rleft += 15;
		}
		//15s淡出
		rightwin.mywin({left:"right", top:"bottom"},function(){
			rightwin.hide();
		}, {left:rleft ,top: scrollTop + browserheight}).fadeOut(15000).dequeue();
	},500);
});

//插件，窗口左中右
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
		
		//计算浏览器可视区域的宽和高，滚动条左边界和上边界的值
		function getBrowserDim(){
			browserwidth = windowobj.width();
			browserheight = windowobj.height();
			scrollLeft = windowobj.scrollLeft();
			scrollTop = windowobj.scrollTop();
		}	
		
		//计算窗口真实左边界值
		function calLeft(positionleft,scrollLeft,browserwidth,cwinwidth){
			if(positionleft && typeof positionleft == "string"){
				if(positionleft == "center"){
					left = scrollLeft + (browserwidth - cwinwidth) / 2;	
				}else if(positionleft == "left"){
					left = scrollLeft;
				}else if(positionleft == "right"){
					left = scrollLeft + browserwidth - cwinwidth;
					if($.browser.safari){//对safari和Chrome
						left -=15;
					}
					if($.browser.opera){//对opera
						left += 15;
					}
				}else{
					//默认center
					left = scrollLeft + (browserwidth - cwinwidth) / 2;	
				}
			}else if(positionleft && typeof positionleft == "number"){
				left = positionleft;
			}else{
				left = 0;
			}
		}
		
		//计算窗口真实上边界值
		function calTop(positiontop,scrollTop,browserheight,cwinheight){
			if(positiontop && typeof positiontop == "string"){
				if(positiontop == "center"){
					top = scrollTop + (browserheight - cwinheight) / 2;
				}else if(positiontop == "top"){
					top = scrollTop;
				}else if(positiontop == "bottom"){
					top = scrollTop + browserheight - cwinheight;
					if($.browser.opera){//对opera
						rleft -= 25;
					}
				}else{
					//默认center
					top = scrollTop + (browserheight - cwinheight) / 2;
				}
			}else if(positiontop && typeof positiontop == "number"){
				top = positiontop;
			}else{
				top = 0;
			}
		}
		
		//移动窗口位置
		function moveWin(){
			calLeft(currentwin.data("positionleft"),scrollLeft,browserwidth,cwinwidth);
			calTop(currentwin.data("positiontop"),scrollTop,browserheight,cwinheight);
			//currentwin.css("left",left).css("top",top);	
			currentwin.animate({
				left: left,
				top: top
			},600);
		}

		//关闭
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
	//滚动条滚动时移动窗口位置
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
	//浏览器大小改变时移动窗口位置
	$(window).resize(function(){
		if(!currentwin.is(":visible")){
			return;
		}
		getBrowserDim();
		moveWin();
	});
	
	return currentwin;
}