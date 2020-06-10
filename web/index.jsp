  <%@page import="java.util.Map"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login to ECM</title>
<link rel="icon" href="images/dc_logo_icon.png" sizes="16x16" type="image/png">
<link rel="manifest" href="images/fav/site.webmanifest">
<meta name="msapplication-TileColor" content="#da532c">
<meta name="theme-color" content="#ffffff">

<!-- CSS -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.typekit.net/hxv7cob.css">
<link rel="stylesheet" href="css/ecm-style.css">
	<script>
	
    
	</script>
</head>
         
 <body class="bgLogin">
<div class="container">
	<div class="row">
		<div class="col-md-10 outerBlk mx-auto">
			<div class="row">
				<div class="col-6 leftLogoBlk d-flex justify-content-between flex-column">
					<div class="row">
						<div class="col">
							<div class="ssLogo pt-2 pb-1">
								<object type="image/svg+xml" data="images/ss-icon-white.svg" class="logoLogin">SS Logo</object>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<div class="row">
								<div class="col tagline text-center">Be one of the first to explore <br>
							what?s next in B2B Marketing!</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-6 loginBlk d-flex justify-content-between flex-column">
					<div class="row">
						<div class="col-12"><h1 class="pageHead">Login to ECM</h1></div>
						<div class="col-12 loginForm">
							<form method="post" action="loginController" id="loginForm">
								<div class="form-group">
									<label for="usrName" class="sr-only">username</label>
									<input type="email" class="form-control" id="usrName" name="usrName" aria-describedby="usrName" placeholder="username">
								</div>
								<div class="form-group">
									<label for="usrPass" class="sr-only">password</label>
									<input type="password" class="form-control" id="usrPass" name="usrPass" aria-describedby="usrPass" placeholder="password">
								</div>
								<div class="form-group loglinks">
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input" id="rememberMe">
										<label class="custom-control-label" for="rememberMe">Remember Me</label>
									</div>
									<a href="#" title="Forgot your Password?">Forgot Password?</a>
								</div>
								<div class="form-group text-right">
									<button type="submit" class="btn btn-login" id="submitLoginForm" title="Submit">Submit</button>
									<p align="right"><a href="register.html" class="sign" style="font-size: 18px;">Click here to create a new account</a></p>
								</div>
								<div class="form-group" style="height: 17px">
                                                                    
                                                                    <%
                                                                    String created=(String) request.getAttribute("userCreate");
                                                                    if(created!=null)
                                                                    {
                                                                          if(created=="Done")
                                                                    {%>
                                                           <span id="thankyou" class="successMsg">Your Account has Successfully Created</span>
                                                                     <%
                                                                     }
                                                             }
                         
                                                                    String notExit=(String) request.getAttribute("notExit");
                                                                    if(notExit!=null)
                                                                    {
                                                                          if(notExit=="NotExits")
                                                                    {%>
                                                           <span id="thankyou" class="successMsg">EmailId NotExist.Create New Account</span>
                                                                     <%
                                                                     }
                                                             }
                                                                    String session1=(String) request.getAttribute("session");
                                                                    if(session1!=null)
                                                                    {
                                                                          if(session1=="session")
                                                                    {%>
                                                                    <span id="thankyou" class="successMsg">Session Expired.Login Again</span>
                                                                     <%
                                                                     }
                                                             }

                                                                       String password=(String) request.getAttribute("password");
                                                                    if(password!=null)
                                                                    {
                                                                          if(password.equals("false"))
                                                                    {%>
                                                                    <span id="thankyou" class="successMsg">Incorrect Password! Login Again</span>
                                                                     <%
                                                                     }
                                                             }

                                                              %>

                    <span id="errorMsg" class="successMsg" style="display: none;">Please mention correct ID or Password</span>
              </div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col footTxt text-right">&copy; 2020 My Projects | All Rights Reserved</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- JavaScripts -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
<script src="js/ecm-js.js"></script>
</body>
</html>

