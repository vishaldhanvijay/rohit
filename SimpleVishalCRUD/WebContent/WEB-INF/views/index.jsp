<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="head.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		$("#submitStudent").click(function() {
			var name = $("#StudentName").val();
			var age = $("#StudentAge").val();
			var phone = $("#StudentPhone").val();

			$('#NameError').text("");
			$("#AgeError").text("");
			$("#PhoneError").text("");

			if (name == "" || name == null) {
				$('#NameError').text("Please Enter Name");
			} else if (age == "" || age == null) {
				$('#AgeError').text("Please Enter age");
			} else if (phone == "" || phone == null) {
				$('#PhoneError').text("Please Enter phone");
			} else {
				return true;
			}
			return false;
		});
	});

	$(document)
			.ready(
					function() {
						$(function() {
							$('#StudentName')
									.keydown(
											function(e) {
												if (e.shiftKey || e.ctrlKey
														|| e.altKey) {
													e.preventDefault();
												} else {
													var key = e.keyCode;

													if (!((key == 8)
															|| (key == 32)
															|| (key == 46)
															|| (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
														e.preventDefault();
														$("#NameError")
																.text(
																		"Only Alphabate Is Allowed")
													} else {
														$("NameError").text("");
														return true;
													}
												}
												;
											});
						});

					});

	$(document).ready(function() {
		$("#StudentAge").keypress(function(e) {
			if (e.which != 8 && (e.which<48||e.which>57)) {
				$("#AgeError").text("Only Numbers Allowed");
			} else {
				$("#AgeError").text("");
				return true;
			}
			return false;
		});
	});

	$(document).ready(function() {
		$("#StudentPhone").keypress(function(e) {
			if (e.which != 8 && (e.which<48||e.which>57)) {
				$("#PhoneError").text("Only Numbers Allowed");
			} else {
				$("#PhoneError").text("");
				return true;
			}
			return false;
		});
	});
</script>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<jsp:include page="menu.jsp" />

	<div id="page" class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="sidemenu.jsp" />
			</div>
			<div class="col-md-10">
				<h3>Student Registration Form</h3>
				<h3>${msg}</h3>
				<div class="col-md-6">
					<form action="${FormAction}" method="post">
						<div class="row">
							<input type="hidden" name="id" value="${StudentId}" id="Id" />
							<div class="col-md-5">Name</div>
							<div class="col-md-7">
								<input type="text" name="name" class="form-control"
									value="${NameValue}" id="StudentName" />
								<div style="color: red;" id="NameError"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">Age</div>
							<div class="col-md-7">
								<input type="text" name="age" class="form-control"
									value="${AgeValue}" id="StudentAge" />
								<div style="color: red;" id="AgeError"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">Phone</div>
							<div class="col-md-7">
								<input type="text" name="phone" class="form-control"
									value="${PhoneValue}" id="StudentPhone" />
								<div style="color: red;" id="PhoneError"></div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3"></div>
							<div class="col-md-5">
								<input type="submit" id="submitStudent"
									class="btn btn-secondary" value="${Button}" />
								<!--here id="submitCity" is related with jquery only-->
								<input type="button" class="btn btn-secondary" value="RESET" />
							</div>
						</div>
					</form>
				</div>


				<div class="col-md-6">

					<table class="table table-stripped" border="2px">
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Age</th>
							<th>Phone</th>
							<th>Action</th>
						</tr>
						<c:if test="${not empty StudentList }">
							<!-- checking-->
							<c:forEach var="student" items="${StudentList}">
								<tr>
									<td>${student.id}</td>
									<td>${student.name}</td>
									<td>${student.age}</td>
									<td>${student.phone}</td>
									<td><a href="GetUpdateStudent?id=${student.id}">Update</a>||<a
										href="GetDeleteStudent?id=${student.id}">Delete</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>

	<%-- 
	<h1>${title}</h1>
	<form action="${FormAction}" method="post">
	<input type="hidden" name="id" value="${StudentId}" id="Id" /> 
	Enter Name<font style="color: red">*</font>
		<input type="text" name="name" value="${NameValue}" id="Name"/>
	<div id="NameError" style="color: red"></div>
		
		<br> <br> 
	Enter Age<font style="color: red">*</font>
	<input type="text" name="age" value="${AgeValue}" id="Age"/>
	<div id="AgeError" style="color: red"></div>
	
		<br> <br>
	Enter Phone<font style="color: red">*</font>
	<input type="text" name="phone" value="${PhoneValue}" id="Phone"/>
	<div id="PhoneError" style="color: red"></div>
	
	<br> <br> 
	<input type="submit" id="submitStudent" value="${Button}" />
		<h3>${msg}</h3>
	</form>


	<table border="1">
		<tr>

			<th>Id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Phone</th>
			<th>Action</th>
		</tr>

		<c:if test="${not empty StudentList }">
			<c:forEach var="student" items="${StudentList}">
				<tr>

					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.age}</td>
					<td>${student.phone}</td>
					<td><a href="GetUpdateStudent?id=${student.id}">Update</a>||<a
						href="GetDeleteStudent?id=${student.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table> --%>

	<jsp:include page="footer.jsp" />

</body>

</html>