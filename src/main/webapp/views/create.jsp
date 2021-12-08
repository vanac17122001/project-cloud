<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="header.jsp"%>
</head>

<body>
	<!-- ============================================================== -->
	<!-- Preloader - style you can find in spinners.css -->
	<!-- ============================================================== -->
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->
		<%@ include file="homeleftbar.jsp"%>
		<!-- ============================================================== -->
		<!-- End Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<div class="page-breadcrumb bg-white">
				<div class="row align-items-center">
					<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						<h4 class="page-title">Tạo Container</h4>
					</div>
					<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12"></div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- ============================================================== -->
			<!-- End Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid ">
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<!-- Row -->
				<!-- Column -->
				<!-- Column -->
				<div class="col-lg-8 col-xlg-9 col-md-12">
					<div class="card">
						<div class="card-body">
							<form class="form-horizontal form-material"action="<c:url value="/create"/>" method="POST">
								<div class="form-group mb-4">
									<label class="col-sm-12">Chọn hệ điều hành</label>
									<div class="col-sm-12 border-bottom">
										<select
											class="form-select shadow-none p-0 border-0 form-control-line" name="os">
											<option>Ubuntu</option>
											<option>Centos</option>
										</select>
									</div>
								</div>
								<div class="form-group mb-4">
									<label class="col-sm-12">Chọn dung lượng RAM</label>
									<div class="col-sm-12 border-bottom">
										<select
											class="form-select shadow-none p-0 border-0 form-control-line" name="ram">
											<option>250MB</option>
											<option>500MB</option>
											<option>1G</option>
											<option>2G</option>
											<option>3G</option>
											<option>4G</option>
										</select>
									</div>
								</div>
								<div class="form-group mb-4">
									<label class="col-sm-12">Chọn số CPU</label>
									<div class="col-sm-12 border-bottom">
										<select
											class="form-select shadow-none p-0 border-0 form-control-line" name="cpu">
											<option>0.2</option>
											<option>0.4</option>
											<option>0.6</option>
											<option>0.8</option>
											<option>0.9</option>
											<option>1.0</option>
										</select>
									</div>
								</div>
								<div class="form-group mb-4">
									<label class="col-sm-12">Chọn Server</label>
									<div class="col-sm-12 border-bottom">
										<select
											class="form-select shadow-none p-0 border-0 form-control-line" name="server">
											<option value="1">Server 1</option>
											<option value="2">Server 2</option>
											<option value="3">Server 3</option>
										</select>
									</div>
								</div>
								<div class="form-group mb-4">
									<div class="col-sm-12">
										<button class="btn btn-success">Tạo Container</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- Column -->
			</div>
			<!-- Row -->
			<!-- ============================================================== -->
			<!-- End PAge Content -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- Right sidebar -->
			<!-- ============================================================== -->
			<!-- .right-sidebar -->
			<!-- ============================================================== -->
			<!-- End Right sidebar -->
			<!-- ============================================================== -->
		</div>
		</div>
		<!-- ============================================================== -->
		<!-- End Page wrapper  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- End Wrapper -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- All Jquery -->
		<!-- ============================================================== -->
		<script
			src="<c:url value= "/templates/home/plugins/bower_components/jquery/dist/jquery.min.js"/>"></script>
		<!-- Bootstrap tether Core JavaScript -->
		<script
			src="<c:url value= "/templates/home/bootstrap/dist/js/bootstrap.bundle.min.js"/>"></script>
		<script src="js/app-style-switcher.js"></script>
		<!--Wave Effects -->
		<script src="<c:url value= "/templates/home/js/waves.js"/>"></script>
		<!--Menu sidebar -->
		<script src="<c:url value= "/templates/home/js/sidebarmenu.js"/>"></script>
		<!--Custom JavaScript -->
		<script src="<c:url value= "/templates/home/js/custom.js"/>"></script>
</body>

</html>