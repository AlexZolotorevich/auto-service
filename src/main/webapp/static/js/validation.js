



$(function() {
	$("#login_error_message").hide();
	$("#password_error_message").hide();
	$("#phone_error_message").hide();
	$("#email_error_message").hide();
	
	var error_login = false;
	var error_password = false;
	var error_phone = false;
	var error_email = false;
	
	$("#form_userlogin").focusout(function(){
		check_userlogin();
	});
	
	$("#form_userPassword").focusout(function(){
		check_userPassword();
	});
	
	$("#form_userPhone").focusout(function(){
		check_userPhone();
	});
	
	$("#form_userEmail").focusout(function(){
		check_userEmail();
	});
	
	
	function check_userlogin(){
		var userlogin_length = $("#form_userlogin").val().length;
		
		if(userlogin_length < 5 || userlogin_length > 20){
			$("#login_error_message").html("incorrectLoginWeb");
			$("#login_error_message").show();
			error_login = true;
		}else{
			$("#login_error_message").hide();
		}
		
	}
	
	function check_userPassword(){
		var pattern = new RegExp(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}/);
		
		if(pattern.test($("#form_userPassword").val())){
			$("#password_error_message").hide();
		}else{
			$("#password_error_message").html("incorrectPasswordWeb");
			$("#password_error_message").show();
			error_password = true;
		}
		
	}
	
	function check_userPhone(){
		var pattern = new RegExp(/^(\+?375)(29|33)[0-9]{7}$/);
		
		if(pattern.test($("#form_userPhone").val())){
			$("#phone_error_message").hide();
		}else{
			$("#phone_error_message").html("incorrectPhoneWeb");
			$("#phone_error_message").show();
			error_phone = true;
		}
		
	}
	
	function check_userEmail(){
		var pattern = new RegExp(/^([a-zA-Z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/);
		
		if(pattern.test($("#form_userEmail").val())){
			$("#email_error_message").hide();
		}else{
			$("#email_error_message").html("incorrectEmailWeb");
			$("#email_error_message").show();
			error_email = true;
		}
		
	}
	
	
});


