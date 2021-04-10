$(function(){
	/* 기본슬라이드
	$('.main').slick({
		dots : true,
		autoplay : true
	});
	*/

	  //slick 썸네일
	 /*$('.main_photo').slick({
	  slidesToShow: 1,
	  slidesToScroll: 1,
	  arrows: false,
	  fade: true,
	  asNavFor: '.sub_photo'
	});
	$('.sub_photo').slick({
	  slidesToShow: 3,
	  slidesToScroll: 1,
	  asNavFor: '.main_photo',
	  dots: true,
	  centerMode: true,
	  focusOnSelect: true
	});*/
	

	$(".sub_photo").find("a").click(function(){
		var $main = $(this).find("img").attr("src");

		$(".main_photo").find("img").attr({src:$main})
			.hide()
			.fadeIn();

			//return false;
	});

	$('.sub_photo').slick({
	  slidesToShow: 3,
	  slidesToScroll: 1,
	  dots: true,
	  centerMode: true,
	  focusOnSelect: true,
	});
});