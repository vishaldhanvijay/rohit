<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="head.jsp" />

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
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>