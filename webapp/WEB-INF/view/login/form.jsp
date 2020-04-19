<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/Message.tld" prefix="Message"%>

<div id="DIV_ABSTRACT_MESSAGE">
	<span id="SPAN_ABSTRACT_TITLE"></span>
	<span id="SPAN_ABSTRACT_MESSAGE"></span>
</div>
<form id="loginForm" method="post">
	<div class="login-box">
		<div class="login-logo">
			<a href="../../index2.html"><b>Dron Control Mananagement System</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg"><Message:get msgCode="LGN0000007" /></p>
				
				<div class="input-group mb-3">
					<input type="email" class="form-control" placeholder="<Message:get msgCode="LGN0000005" />" name="memberId" autofocus>
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-envelope"></span>
						</div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="password" class="form-control" placeholder="<Message:get msgCode="LGN0000006" />" name="pwd" id="pwd" >
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-8">
						<div class="icheck-primary">
							<input name="saveCookieId" type="checkbox" value="Y">
							<label for="remember"><Message:get msgCode="LGN0000004" /></label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-4">
						<a href="#" id="btnLogin" class="btn btn-primary btn-block">SignIn</a>
					</div>
					<!-- /.col -->
				</div>
				<p class="mb-1"><a href="forgot-password.html">패스워드 찾기</a></p>
				<p class="mb-0"><a href="register.html" class="text-center">회원가입</a></p>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->
</form>

<script type="text/javascript" src="/resources/js/cms/login.js"></script>