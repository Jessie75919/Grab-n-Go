<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
  <div class="py-5 section text-center">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-primary">Logo</h1>
          <p class="lead"></p>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-lg-4">
          <form class="">
            <button type="submit" class="btn btn-block text-gray-dark btn-default m-0">Sign in with Google&nbsp;
              <br> </button>
            <div class="form-group my-1"> <label>Username / E-mail</label>
              <input type="email" class="form-control" placeholder="Enter username / e-mail"> </div>
            <div class="form-group my-1"> <label>Password</label>
              <input type="password" class="form-control" placeholder="Password"> </div>
            <div class="form-group my-1"><label>I am not Robot! Enter the text:&nbsp;</label>
              <input type="text" class="form-control" placeholder=""> </div>
            <div class="form-check"> <label class="form-check-label"> <input class="form-check-input" type="checkbox" value="">&nbsp;Remember me</label> </div>
            <button type="submit" class="btn btn-primary btn-block my-1">Sign in</button>
            <button type="submit" class="btn btn-primary btn-block my-2" 
            			href='../_01_register/_register.jsp'>Sign up</button>
          </form>
        </div>
        <div class="col-md-4 w-25"></div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
</body>

</html>