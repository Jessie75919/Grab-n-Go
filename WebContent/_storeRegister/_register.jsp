<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Welcome! Sign up as Restaurant</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="../css/Signup.css" type="text/css"> 
</head>

<body>
  <div class="py-5 section text-center">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-gray-dark">Logo</h1>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 ">
          <form class="">
            <button type="submit" class="btn btn-block my-2 text-gray-dark btn-primary">Sign up with Google</button>
            <div class="form-group my-1"><label>Name&nbsp;</label>
              <input type="text" class="form-control" placeholder="">
            </div>
            <div class="form-group my-1"> <label>Email address</label>
              <input type="email" class="form-control" placeholder="example@gmail.com"> </div>
            <div class="form-group my-1"><label>Address<br></label>
              <input type="text" class="form-control" placeholder="">
            </div>
            <div class="form-group my-1"><label>Phone</label>
              <input type="text" class="form-control" placeholder="">
            </div>
            <div class="form-group my-1"><label>Username</label>
              <input type="text" class="form-control" placeholder="">
            </div>
            <div class="form-group my-1"> <label>Password</label>
              <input type="password" class="form-control" placeholder="Password"> </div>
            <div class="form-group w-75 my-1"><label class="w-75">I am not Robot! Enter the text:</label>
              <input type="text" class="form-control" placeholder="Text">
            </div>
            <button type="submit" class="btn btn-block my-2 text-white btn-success">Sign up</button>
          </form>
        </div>
        <div class="col-md-3 "></div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
</body>

</html>