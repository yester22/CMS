<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/Message.tld" prefix="Message" %>

	 <form id="loginForm" method="post">
		 <div class="container">
	        <div class="row">
	            <div class="col-md-4 col-md-offset-4">
	                <div class="login-panel panel panel-default">
	                    <div class="panel-heading">
	                        <h3 class="panel-title"><Message:get msgCode="LGN0000007" /></h3>
	                    </div>
	                    <div class="panel-body"> 	
	                        <form role="form">
	                            <fieldset>
	                                <div class="form-group">
	                                    <input class="form-control" placeholder="<Message:get msgCode="LGN0000005" />" name="memberId" type="email" autofocus>
	                                </div>
	                                <div class="form-group">
	                                    <input class="form-control" placeholder="<Message:get msgCode="LGN0000006" />" name="pwd" id="pwd" type="password" value="">
	                                </div>
	                                <div class="checkbox">
	                                    <label>
	                                        <input name="saveCookieId" type="checkbox" value="Y"><Message:get msgCode="LGN0000004" />
	                                    </label>
	                                </div>
	                                <!-- Change this to a button or input when using this as a form -->
	                                <a href="#" id="btnLogin" class="btn btn-lg btn-success btn-block"><Message:get msgCode="LGN0000001" /></a>
	                            </fieldset>
	                        </form>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</form>
	
	<script type="text/javascript" src="/resources/js/cms/login.js"></script>