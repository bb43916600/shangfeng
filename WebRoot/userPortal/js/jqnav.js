$(document).ready(function(){
	lihover();
m_prod_catalog3();
sublist();

});

function lihover(){
	var self = "";
	$(".lihover li").hover(
		function(){
			self = $(this);
			self.addClass("hover").children("div").show();
		},
		function(){
			self = $(this);
			self.children("div").hide();
			self.removeClass("hover");
		}
	);
};
function sublist(){
	var self = "";
	$(".sublist li").click(
		function(){
			
			$(".sublist li").removeClass("hover");
			self = $(this);
			self.addClass("hover");
	
		}
	);
};
function m_prod_catalog3(){
	var self = "";
	$(".mapbox").hover(
		function(){
			$(".mapbox").removeClass("mapbox2");
			self = $(this);
			self.addClass("mapbox2");
			//slideDown
		},
		function(){
			self = $(this);
			//slideUp
			self.removeClass("mapbox2");
		}
	);
};


function SlyarErrors() { 	return true;} 
window.onerror = SlyarErrors;
/*把show();改为fadeIn();是淡入动画效果。把show();改为slideDown();是滑下动画效果。对应的是fadeOut跟slideUp*/