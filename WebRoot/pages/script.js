
$(document).ready(function(){

	/* Converting the #box div into a bounceBox: */
	$('#loginbox').bounceBox();

	/* Listening for the click event and toggling the box: */
	$('div.login').click(function(e){

		$('#loginbox').bounceBoxToggle();
		e.preventDefault();
	});
	
	/* When the box is clicked, hide it: */
	$('img.loginimg').click(function(){
		$('#loginbox').bounceBoxHide();
	});
});
