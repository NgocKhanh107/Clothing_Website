<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Admin - Index</title>
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link rel="stylesheet"
	href="/manager/vendor/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="/manager/css/sb-admin-2.min.css">
</head>
</head>
<body id="page-top">
	<div id="wrapper">
		<%@include file="slidebar.jsp"%>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<%@include file="navbar.jsp"%>
				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">Form Danh Mục</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Cập Nhật</h6>
						</div>
						<div class="card-body">
							<%--@elvariable id="categoryForm" type=""--%>
							<form:form action="/admin/categoryForm/form" method="post"
								modelAttribute="categoryForm" enctype="multipart/form-data">
								<div class="form-group">
									<label for="inputEmail4">Tên danh mục</label>
									<form:input path="name" cssClass="form-control"
										cssErrorClass="form-control is-invalid" id="inputEmail4" />
									<form:errors path="name" cssClass="invalid-feedback"></form:errors>
								</div>

								<div class="form-group">
									<label for="inputEmail4">Chọn hình</label>
									<div class="custom-file mb-3 ">
										<form:input path="image" type="file"
											cssClass="custom-file-input"
											cssErrorClass="custom-file-input is-invalid"
											id="validatedCustomFile" />
										<label class="custom-file-label" for="validatedCustomFile">Choose
											file...</label>
										<form:errors path="image" cssClass="invalid-feedback"></form:errors>
									</div>
								</div>

								<div class="form-group d-flex justify-content-center">
									<img src="/upload/${sessionScope.nameImage}"
										class="w-50 h-50 form-control" alt="">
								</div>
								<c:if test="${!edit}">
									<button type="submit" class="btn btn-primary">
										<i class="fas fa-pen"></i> Lưu
									</button>
								</c:if>

								<c:if test="${edit}">
									<button
										formaction="/admin/categoryForm/form/edit/${sessionScope.id}"
										class="btn btn-primary">
										<i class="fas fa-pen"></i> Cập nhật
									</button>
								</c:if>

								<button formaction="/admin/userForm/refresh"
									class="btn btn-info">
									<i class="fas fa-redo-alt"></i> Làm mới
								</button>
								<button formaction="/admin/categoryForm/list" formmethod="get"
									class="btn btn-danger">
									<i class="fas fa-list"></i> Danh sách
								</button>
							</form:form>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->



			<%@include file="footer.jsp"%>
		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Thông Báo!</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Vui lòng nhấn nút "Đăng xuất" bên dưới
					nếu bạn muốn đăng xuất tài khoản.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Thoát</button>
					<a class="btn btn-primary" href="/logout">Đăng xuất</a>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="/manager/vendor/jquery/jquery.min.js"></script>
<script src="/manager/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/manager/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/manager/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="/manager/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="/manager/js/demo/chart-area-demo.js"></script>
<script src="/manager/js/demo/chart-pie-demo.js"></script>
</html>