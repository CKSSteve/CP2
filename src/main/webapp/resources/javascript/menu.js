$(function(){
	// 先取得相關選單元素及高度
	var $menuWrapper = $('#menu-wrapper'), 
		$subMenuWwrapper = $menuWrapper.find('.sub-menu-wrapper').add($menuWrapper.find('.sub-menu')), 
		_height = $subMenuWwrapper.height(), 
		_animateSpeed = 200;
	
	// 先把 $subMenuWwrapper 的高度歸 0
	// 並把 .sub-menu ul 先往上移動隱藏
	var $subMenu = $subMenuWwrapper.height(0).find('.sub-menu ul').css({
		top: _height * -1
	});
	
	// 當滑鼠移入到 .main-menu ul li a 上時
	//$('.main-menu ul li a').mouseover(function(){
	$('.main-menu ul li a').click(function(){
		// 先取出被滑鼠移入的選單
		// 並取得該選單中第一個 span 的文字顏色
		var $this = $(this), 
			$color = $this.find('span').css('color'), 
			_no = $this.parent().index();
			
		$('iframe').css('padding-top', 142);	
		
		// 改變 $subMenuWwrapper 的顏色為 $color 並展開高度
		$subMenuWwrapper.css({
			backgroundColor: $color, 
			borderTopColor: $color
		}).stop().animate({
			height: _height
		}, _animateSpeed);
		
		// 移動相對應的子選單
		$subMenu.eq(_no).stop().animate({
			top: 0
		}, _animateSpeed).siblings().stop().animate({
			top: _height * -1
		}, _animateSpeed);

		
		// 讓被滑鼠移入的選單加上指定的效果
		$this.addClass('selected').parent().siblings().find('a.selected').removeClass('selected');

		return false;
	});
	
	// 當滑鼠移出 $menuWrapper 後把 $subMenuWwrapper 的高度歸 0
   //$menuWrapper.mouseleave(function(){
   $(".sub-menu a").click(function(){
    $subMenuWwrapper.stop().animate({
    height: 0
    }, _animateSpeed);
				$('iframe').css('padding-top', 90);	

   });
	
	$('.tolist').on('click',CLOSECLOSE);

	function CLOSECLOSE(){
		$subMenuWwrapper.stop().animate({
		height: 0
		}, _animateSpeed);
		
		$('.main-menu ul li a').removeClass('selected');
		$('iframe').css('padding-top', 90);	

	}
});