<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<%@page import="java.util.regex.Pattern"%>


<script type="text/javascript">
$(document).ready(function() {
	
	//페이지 접속 시 아이디 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//취소 버튼 클릭 시 뒤로가기
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	var duplCheck1= false;
	$("#btnDuplicate_userid").click(function() {
		$.ajax({
			type: "get"
			, url: "/member/duplicate_userid"
			, data: {
				user_id: $("#user_id").val()
			}
			, dataType: "json"
			, success: function( res ) {
				console.log("AJAX 성공")
				console.log( res )
				
				if( res.dupl ) {
					console.log("중복O chk_dupl_id")
					$("#duplMsg_id").css("color", "red").html("사용이 불가능한 아이디")
					duplCheck1 = false;
				} else {
					console.log("중복X chk_dupl_id")
					$("#duplMsg_id").css("color", "blue").html("사용 가능한 아이디")
					duplCheck1 = true;
				}
			}
			, error: function() {
				console.log("AJAX 실패")
			}
		});
	});
	
	var duplCheck2= false;
	$("#btnDuplicate_usernick").click(function() {
		$.ajax({
			type: "get"
			, url: "/member/duplicate_usernick"
			, data: {
				user_nick: $("#user_nick").val()
			}
			, dataType: "json"
			, success: function( res ) {
				console.log("AJAX 성공")
				console.log( res )
				
				if( res.dupl ) {
					console.log("중복 duplCheck2")
					$("#duplMsg_nick").css("color", "red").html("사용 불가능한 닉네임")
					duplCheck2 = false;
				} else {
					console.log("중복 아님 duplCheck2")
					$("#duplMsg_nick").css("color", "blue").html("사용 가능한 닉네임")
					duplCheck2 = true;
				}
			}
			, error: function() {
				console.log("AJAX 실패")
			}
		});
	});
	
	var duplCheck3= false;
	$("#btnDuplicate_useremail").click(function() {
		$.ajax({
			type: "get"
			, url: "/member/duplicate_useremail"
			, data: {
				user_email: $("#user_email").val()
			}
			, dataType: "json"
			, success: function( res ) {
				console.log("AJAX 성공")
				console.log( res )
				
				if( res.dupl ) {
					console.log("중복 duplCheck3")
					$("#duplMsg_email").css("color", "red").html("사용 불가능한 이메일")
					duplCheck3 = false;
				} else {
					console.log("중복 아님 duplCheck3")
					$("#duplMsg_email").css("color", "blue").html("사용 가능한 이메일")
					duplCheck3 = true;
				}
			}
			, error: function() {
				console.log("AJAX 실패")
			}
		});
	});
	
	function telValidator(args) {
	    const msg = '유효하지 않는 전화번호';

	    if (/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(args)) {
	        return false;
	    }
		else if (/^[0-9]{2,3}[0-9]{3,4}[0-9]{4}/.test(args)) {
	        return false;
	    }	

	    alert(msg);
	    return true;
	}

	function dateValidator(args) {
	    const msg = '유효하지 않는 숫자';

	    if (/^[0-9]/.test(args)) {
	        return false;
	    }
		
	    alert(msg);
	    return true;
	}

	$('#user_id').focusout(function() {
		if($("#user_id").val().length < 3 || $("#user_id").val().length > 10) {
			alert("아이디는 3글자 이상 10글자 이하입니다");
	  	}
	});
	
	$('#user_pw_check').focusout(function() {
		if( $("#user_pw").val() != $("#user_pw_check").val() ) {
           alert("비밀번호를 확인하세요");
	  	}
	});
	
	$("#btnJoin").click(function() {
		if($("#user_id").val().length == 0) {
	           alert("아이디를 입력하세요");
	           $("#user_id").focus();
	           return false;
	    }
		
		if($("#user_id").val().length < 3 || $("#user_id").val().length > 10) {
	           alert("아이디는 3글자 이상 10글자 이하입니다");
	           $("#user_id").focus();
	           return false;
	    }       
		
		if( !duplCheck1 ) {
			alert("아이디 중복확인을 해주세요");
	        return false;
	    } else if( !duplCheck1 ) {
        	return true;
	    } 
		
		if($("#user_pw").val().length == 0) {
	           alert("비밀번호를 입력하세요");
	           $("#user_pw").focus();
	           return false;
        }
		
        if($("#user_pw").val().length < 3 || $("#user_pw").val().length > 15 ) {
           alert("비밀번호를 3자리 이상 15자리 이하로 입력하세요");
           $("#user_pw").focus();
           return false;
        }
        
        if($("#user_pw_check").val().length == 0) {
           alert("비밀번호를 확인하세요");
           $("#user_pw_check").focus();
           return false;
        }
        
        if( $("#user_pw").val() != $("#user_pw_check").val() ) {
           alert("비밀번호 확인이 다릅니다");
           $("#user_pw_check").focus();
           return false;
        }
		
        if($("#user_nick").val().length == 0) {
            alert("닉네임을 입력하세요");
            $("#user_nick").focus();
            return false;
         }
		
        if($("#user_nick").val().length < 3 || $("#user_nick").val().length > 10 ) {
            alert("닉네임을 3자리 이상 15자리 이하로 입력하세요");
            $("#user_nick").focus();
            return false;
         }
        
        if( !duplCheck2 ) {
        	alert("닉네임 중복확인을 해주세요");
            return false;
        } else if( !duplCheck2 ) {
        	return true;
        }

        if($("#user_email").val().length == 0) {
            alert("이메일을 입력하세요");
            $("#user_email").focus();
            return false;
         }
        
	    if ( !duplCheck3 ) {
	    	alert("이메일 중복확인을 해주세요");
	    	return false;
        } else if ( !duplCheck3 ) {
        	return true;
        }
		
	    if( $("#user_phone").val().length != 0 && telValidator($("#user_phone").val()) ) {
	    
            $("#user_phone").focus();
           
            return false;
	    }
		
        if($("#user_year").val().length == 0 && $("#user_month").val().length == 0 && $("#user_day").val().length == 0) {
            alert("생년월일을 입력하세요");
            $("#user_birth").focus();
            return false;
        }
        
        
        if( $("#user_year").val() == 0 ) {
            alert("태어난 년도를 입력하세요");
            console.log("#user_year")
            $("#user_year").focus();
            return false;
        }
       
        if( dateValidator($("#user_year").val()) ) {
    	    
            $("#user_year").focus();
           
            return false;
	    }
        
        if( $("#user_year").val() < 1900 || $("#user_year").val() > 2022 ) {
            alert("태어난 년도를 정확히 입력하세요");
            $("#user_year").focus();
            return false;
        }
        
        if( $("#user_month").val() == 0) {
            alert("태어난 달을 입력하세요");
            $("#user_month").focus();
            return false;
        }
        
		if( dateValidator($("#user_month").val()) ) {
    	    
            $("#user_month").focus();
           
            return false;
	    }
        
        if($("#user_month").val() < 1 || $("#user_month").val() > 12 ) {
            alert("태어난 달을 정확히 입력하세요");
            $("#user_month").focus();
            return false;
        }
        
        if( $("#user_day").val() == 0) {
            alert("태어난 일을 입력하세요");
            $("#user_day").focus();
            return false;
        }
        
		if( dateValidator($("#user_day").val()) ) {
    	    
            $("#user_day").focus();
           
            return false;
	    }
        
        if($("#user_day").val() < 1 || $("#user_day").val() > 31 ) {
            alert("태어난 일을 정확히 입력하세요");
            $("#user_day").focus();
            return false;
        }
        
        joinform.submit();
        
  	});
			
	
});
</script>

<style type="text/css">
form {
	width: 1140px;
	margin: 0 auto;
}

#btnJoin, #btnCancel {
	border: 0;
	border-radius: 5px;
	background: #FF792A;
	color: white;
	width: 80px;
	height: 30px;
	font-size: 15px;
}
</style>

<div class="container" style="height:100%;">

<br><br><br><br><br><br><br><br><br>
<div class="join_form_div" style="border: 1px solid #ccc; border-radius: 8px; width: 752px; margin: 0 auto; text-aling: center;	">
<br><br>
<h2 style="margin-left: 225px;"><b>커피 한잔?</b></h2>
<br>
<br>
<form action="/member/join" method="post" name="joinform" class="form-horizontal">
	<div class="form-group">
		<label for="user_id" class="control-label col-xs-2" style="maring-left: 70px;">아이디</label><br><br>
		<div class="col-xs-8" style="width: 250px;">
			<input type="text" id="user_id" name="user_id" class="form-control" style="width: 200px; margin-left: 120px;">
			<div style="width: 300px; margin-left: 97px;">
				<span id="duplMsg_id" style="position: relative; width: 300px; top: 10px; right: 100px; margin-left: 130px; margin-bottom: -10px;"></span>
			</div>
		</div>
		<button type="button" class="col-xs-2" id="btnDuplicate_userid" style="margin-left: 110px; width: 120px; height: 33.99px; background: #FF792A; color: white; border: 0; border-radius: 5px;"><b>중복 검사</b></button>
	</div>

	<div class="form-group">
		<label for="user_pw" class="control-label col-xs-2" style="margin-left: 13px;">비밀번호</label><br><br>
		<div class="col-xs-10">
			<input type="password" id="user_pw" name="user_pw" class="form-control" style="width: 346px;  margin-left: 120px;">
		</div>
	</div>
	
	<div class="form-group">
		<label for="user_pw_check" class="control-label col-xs-3" style="margin-left: -52px;">비밀번호 확인</label><br><br>
		<div class="col-xs-9">
			<input type="password" id="user_pw_check" name="user_pw_check" class="form-control" style="width: 346px;  margin-left: 120px;">
		</div>
	</div>

	<div class="form-group">
		<label for="user_nick" class="control-label col-xs-2">닉네임</label><br><br>
		<div class="col-xs-8" style="width: 250px;">
			<input type="text" id="user_nick" name="user_nick" class="form-control" style="width: 200px;  margin-left: 120px;">
			<div style="width: 300px; margin-left: 97px;">
				<span id="duplMsg_nick" style="position: relative; width: 300px; top: 10px; right: 100px; margin-left: 130px; margin-bottom: -10px;"></span>
			</div>
		</div>
			<button type="button" class="col-xs-2" id="btnDuplicate_usernick" style="margin-left: 110px; width: 120px; height: 33.99px; background: #FF792A; color: white; border: 0; border-radius: 5px;"><b>중복 검사</b></button>
	</div>
	
	<div class="form-group">
		<label for="user_gender" class="control-label col-xs-2" style="margin-left: -12px;">성별</label><br><br>
		<div class="col-xs-10">
		<label>
			<span style="margin-left: 119px;">남</span> <input type="radio"  id="user_gender_m" name="user_gender_m" value="M" checked/>
		</label>
		<label>
			<span style="margin-left: 10px;">여 </span><input type="radio"  id="user_gender_f" name="user_gender_f" value="F"/>
		</label>
		</div>
	</div>		
	
	<div class="form-group">
		<label for="user_email" class="control-label col-xs-2">이메일</label><br><br>
		<div class="col-xs-10" style="margin-right: 10px;">
			<input type="email" id="user_email" name="user_email" class="form-control" style="width: 346px;  margin-left: 120px;">
			<div style="width: 300px; margin-left: 97px; display: inline-block;">
				<span id="duplMsg_email" style="position: relative; width: 0px; top: 12px; right: 99px; margin-left: 130px; margin-bottom: -10px;"></span>
			</div>
		</div>
			<button type="button" class="col-xs-2" id="btnDuplicate_useremail" style="display: inline-block; position:relative; right: 480px; width: 120px; height: 33.99px; background: #FF792A; color: white; border: 0; border-radius: 5px;"><b>중복 검사</b></button>
	</div>	
	
	<div class="form-group">
		<label for="user_phone" class="control-label col-xs-2" style="margin-left: 13px;">전화번호</label><br><br>
		<div class="col-xs-10">
			<input type="text" id="user_phone" name="user_phone" class="form-control" style="width: 346px;  margin-left: 120px;">
		</div>
	</div>	
	
	<div class="form-group">
		<label for="user_birth" class="control-label col-xs-2" style="margin-left: 102px;">생년월일(YYYYMMDD)</label><br><br>
		<div class="col-xs-10">
			<input type="text" id="user_year" name="user_year" class="form-control" style="display: inline-block; width: 115px;  margin-left: 120px; margin-right: 2.5px;" placeholder="년">
			<input type="text" id="user_month" name="user_month" class="form-control" style="display: inline-block; width: 115px;  margin-left: 5px; margin-right: 2.5px;" placeholder="월">
			<input type="text" id="user_day" name="user_day" class="form-control" style="display: inline-block; width: 115px;  margin-left: 2.5px; margin-right: 2.5px;" placeholder="일">
		</div>
	</div>	
	<br>
	<div>
		<button type="button" id="btnJoin" style="margin-left: 296px; margin-rigth: 5px;"><b>회원 가입</b></button>
		<button type="button" id="btnCancel" style="margin-left: 5px;"><b>취소</b></button>
	</div>
	<br>
	<br>
	
</form>
</div>

</div><!-- .container -->
<br><br><br><br><br><br><br><br><br><br>

<%@ include file="../layout/footer.jsp" %>


