$(function(){
	$('.main').slick({
		dots : true,  //하단버튼
		autoplaySpeed: 2000,//오토슬라이드의 속도
		autoplay : true,
		fade : true
	});

	
	$('.slide').bxSlider({
				slideWidth: 960,
				auto : true,
				autoDelay:2000,//오토슬라이드 속도
				minSlides : 2,
				maxSlides : 2,
	});

	

		$(".sub_photo").find("div").click(function(){
		var $main = $(this).find("img").attr("src"); //sub_photo img src 값을 받아오는 변수로 선언

		$(".main_photo").find("img").attr({src:$main}) //.sub_photo img src값을 받아서 변경하겠다
			.hide()
			.fadeIn();
	});

	$('.sub_photo').slick({
	  slidesToShow: 3,
	  slidesToScroll: 1,
	  dots: true,
	  centerMode: true,
	  focusOnSelect: true,
		  autoplay : true
	});


});