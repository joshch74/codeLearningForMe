$(function(){
	var $window_height = $(window).height();
	//세로값을 받기위한 window의 세로값은 받는 변수
	var $btn = $("#menu li");
	var $top =0;
	//top 값을 받기위한 빈 변수하나

	$("body").css("height",$window_height);
	//body가 세로값을 받지못하기때문에 window 세로값을 body에 넣어준다

	$(window).resize(function(){
		$("body").css("height",$window_height);

		var $click_1 = $btn.eq(0).hasClass("on");
		var $click_2 = $btn.eq(1).hasClass("on");
		var $click_3 = $btn.eq(2).hasClass("on");
		var $click_4 = $btn.eq(3).hasClass("on");

		if($click_2==true){
			var $top = $window_height*-1;
			$(".back").css("top",$top);
		}else if($click_3==true){
			var $top = $window_height*-2;
			$(".back").css("top",$top);
		}else if($click_4==true){
			var $top = $window_height*-3;
			$(".back").css("top",$top);
		}
	});

		$btn.eq(0).click(function(){
			$btn.removeClass("on");
			$(this).addClass("on");
			$(".back").animate({"top":"0"},1000,"swing");
		});

		$btn.eq(1).click(function(){
			$btn.removeClass("on");
			$(this).addClass("on");
			var $top = $window_height*-1;
			$(".back").animate({"top":$top},1000,"swing");
		});

		$btn.eq(2).click(function(){
			$btn.removeClass("on");
			$(this).addClass("on");

			
			var $top = $window_height*-2;
			
			$(".back").animate({"top":$top},1000,"swing");
		});

		$btn.eq(3).click(function(){
			$btn.removeClass("on");
			$(this).addClass("on");

			
			var $top = $window_height*-3;
			
			$(".back").animate({"top":$top},1000,"swing");
		});
	
});