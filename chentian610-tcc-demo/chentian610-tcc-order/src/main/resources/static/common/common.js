$(function(){
	$("#sideMenu").on("click",">li>a",function(){
		$(this).parent().addClass('on').siblings().removeClass('on').children('ul').height(0);
		var $list = $(this).next("ul"), x = $(">li",$list).size();
		$list.height(40*x);
	});

	var h = $(window).height();
	$("#content-main").height(h - 120);
})

$(window).on("resize",function() {
	var h = $(window).height();
	$("#content-main").height(h - 120);
});