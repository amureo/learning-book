/*!
* Start Bootstrap - Simple Sidebar v6.0.6 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// 
// Scripts
// 



// sidebar active
$(function(){
    $('.sidebar1 a').filter(function(e){
		if(location.href.indexOf("problem") != -1 && this.href.indexOf("workbook") != -1) return true;
		return location.href.indexOf(this.href) !== -1
	}).addClass('active').removeClass("link-dark")
})
