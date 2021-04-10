$(function(){
	//기본슬라이드
	/*$('.main').slick({
		dots:true,
		autoplay:true
	});*/

	//기본썸네일
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

		$(".sub_photo").find("a").click(function(){ //클릭 이벤트
		var $main = $(this).find("img").attr("src"); // img를 가져오는 변수

		$(".main_photo").find("img").attr({src:$main}) // 메인이미지 변경 구문
			.hide()
			.fadeIn()

	});

	$('.sub_photo').slick({
	  slidesToShow: 3,
	  slidesToScroll: 1,
	  dots: true,
	  centerMode: true,
	  focusOnSelect: true,
	});
});