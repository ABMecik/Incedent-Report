
$("#show_hide_password_current a").click(function() {
	event.preventDefault();
	console.log("a");
	if($('#show_hide_password_current input').attr("type") == "text"){
		$('#show_hide_password_current input').attr('type', 'password');
		$('#show_hide_password_current i').addClass( "fa-eye-slash" );
		$('#show_hide_password_current i').removeClass( "fa-eye" );
	}else if($('#show_hide_password_current input').attr("type") == "password"){
		$('#show_hide_password_current input').attr('type', 'text');
		$('#show_hide_password_current i').removeClass( "fa-eye-slash" );
		$('#show_hide_password_current i').addClass( "fa-eye" );
	}
});

$("#show_hide_password_new a").click(function() {
	event.preventDefault();
	if($('#show_hide_password_new input').attr("type") == "text"){
		$('#show_hide_password_new input').attr('type', 'password');
		$('#show_hide_password_new i').addClass( "fa-eye-slash" );
		$('#show_hide_password_new i').removeClass( "fa-eye" );
	}else if($('#show_hide_password_new input').attr("type") == "password"){
		$('#show_hide_password_new input').attr('type', 'text');
		$('#show_hide_password_new i').removeClass( "fa-eye-slash" );
		$('#show_hide_password_new i').addClass( "fa-eye" );
	}
});
$("#show_hide_password_confirm_new a").click(function() {
	event.preventDefault();
	if($('#show_hide_password_confirm_new input').attr("type") == "text"){
		$('#show_hide_password_confirm_new input').attr('type', 'password');
		$('#show_hide_password_confirm_new i').addClass( "fa-eye-slash" );
		$('#show_hide_password_confirm_new i').removeClass( "fa-eye" );
	}else if($('#show_hide_password_confirm_new input').attr("type") == "password"){
		$('#show_hide_password_confirm_new input').attr('type', 'text');
		$('#show_hide_password_confirm_new i').removeClass( "fa-eye-slash" );
		$('#show_hide_password_confirm_new i').addClass( "fa-eye" );
	}
});



(function($) {
  "use strict"; // Start of use strict

  // Activate scrollspy to add active class to navbar items on scroll
  $('body').scrollspy({
    target: '#mainNav',
    offset: 56
  });

  // Collapse Navbar
  var navbarCollapse = function() {
    if ($("#mainNav").offset().top > 60) {
      $("#mainNav").addClass("navbar-shrink");
    } else {
      $("#mainNav").removeClass("navbar-shrink");
    }
  };
  // Collapse now if page is not at top
  navbarCollapse();
  // Collapse the navbar when page is scrolled
  $(window).scroll(navbarCollapse);

  // Hide navbar when modals trigger
  $('.portfolio-modal').on('show.bs.modal', function(e) {
    $('.navbar').addClass('d-none');
  })
  $('.portfolio-modal').on('hidden.bs.modal', function(e) {
    $('.navbar').removeClass('d-none');
  })

})(jQuery);



$(function(){
	var hash = window.location.hash;
	hash && $('ul.nav a[href="' + hash + '"]').tab('show');

	$('.nav-pills a').click(function (e) {
		$(this).tab('show');
		var scrollmem = $('body').scrollTop();
		window.location.hash = this.hash;
		$('html,body').scrollTop(scrollmem);
	});
});


$('li.dropdown .select-1').click(function () {
	$('ul.nav a[href="#allTab"]').tab('show');
});
$('li.dropdown a.select-2').click(function () {
	$('ul.nav a[href="#topRatedTab"]').tab('show');
});
$('li.dropdown a.select-3').click(function () {
	$('ul.nav a[href="#actionTab"]').tab('show');
});
$('li.dropdown a.select-4').click(function () {
	$('ul.nav a[href="#dramaTab"]').tab('show');
});
$('li.dropdown a.select-5').click(function () {
	$('ul.nav a[href="#comedyTab"]').tab('show');
});
$('li.dropdown a.select-6').click(function () {
	$('ul.nav a[href="#crimeTab"]').tab('show');
});

function loginValidation()
{
	var username = document.loginForm.username.value;
	var password = document.loginForm.password.value;
	if(username=="")
	{
		alert("Please Enter Your Username");
		document.loginForm.username.focus();
		return false;
	}
	if(password=="")
	{
		alert("Please Enter Your Password");
		document.loginForm.password.focus();
		return false;
	}
}


function searchValidation()
{
	var search = document.search.seav.value;
	if(search=="")
	{
		alert("Search Input Can not be Empty");
		document.search.seav.focus();
		return false;
	}
}



function regValidation()
{
	var name = document.regForm.name.value;
	var username = document.regForm.username.value;
	var email = document.regForm.email.value;
	var password = document.regForm.password.value;
	var confirmPassword = document.regForm.confirmPassword.value;
	
	var regexInput = /^[a-z0-9_-]{5,15}$/i;
	var regexName = /^[a-z]$/i;
	var regexEmail =  /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    
	
	if(name=="")
	{
		alert("Please Enter Your Name");
		document.regForm.name.select();
		return false;
	}
	if(username=="")
	{
		alert("Please Enter Your Username");
		document.regForm.username.focus();
		return false;
	}
	if (!regexInput.test(username))
	{
		alert("Your Username must be 5 to 15 Character");
		document.regForm.username.select();
		return false;
	}
	if(email=="")
	{
		alert("Please Enter Your Email Address");
		document.regForm.email.focus();
		return false;
	}
	if(!regexEmail.test(email))
	{
		alert("Please Enter Your Email Address in Correct Form");
		document.regForm.email.focus();
		return false;
	}
	if(password=="")
	{
		alert("Please Enter Your Password");
		document.regForm.password.focus();
		return false;
	}
	if (!regexInput.test(password))
	{
		alert("Your Password must be 5 to 15 Character");
		document.regForm.password.select();
		return false;
	}
	if (password != confirmPassword)
	{
		alert("Passwords don't match!");
		document.regForm.confirmPassword.select();
		return false;
	}
}


