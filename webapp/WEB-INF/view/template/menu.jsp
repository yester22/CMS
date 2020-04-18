<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Brand Logo -->
<a href="#" class="brand-link"> 
	<img src="/resources/bootstrap/adminite/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
	<span class="brand-text font-weight-light">ADDRON 3</span>
</a>

<!-- Sidebar -->
<div class="sidebar">
	<!-- Sidebar user panel (optional) -->
	<!-- 
	<div class="user-panel mt-3 pb-3 mb-3 d-flex">
		<div class="image">
			<img src="/resources/bootstrap/adminite/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
		</div>
		<div class="info">
			<a href="#" class="d-block">Alexander Pierce</a>
		</div>
	</div>
 	-->
	<!-- Sidebar Menu -->
	<nav class="mt-2">
		<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
			<li class="nav-item has-treeview menu-open">
				<a href="#" class="nav-link active"> 
					<i class="nav-icon fas fa-tachometer-alt"></i>
					<p>Dashboard <i class="right fas fa-angle-left"></i></p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="#" onclick="javascript:pageMove.goPost('dashboard')" class="nav-link"> 
							<i class="far fa-circle nav-icon"></i>
							<p>Dashboard v1</p>
						</a>
					</li>
				</ul>
			</li>
			<li class="nav-item has-treeview">
				<a href="#" class="nav-link">
					<i class="nav-icon fas fa-chart-pie"></i>
					<p>	Data 관리 <i class="right fas fa-angle-left"></i></p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="#" onclick="javascript:pageMove.goPost('excel/excelUpload')" class="nav-link"> 
							<i class="far fa-circle nav-icon"></i>
							<p>데이터 업로드</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="#" onclick="javascript:pageMove.goPost('excel/excelList')" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>데이터 조회</p>
						</a>
					</li>
				</ul>
			</li>
			<li class="nav-item has-treeview">
				<a href="#" class="nav-link">
					<i class="nav-icon fas fa-th"></i>
					<p>	Map Data 조회 <i class="right fas fa-angle-left"></i></p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="#" onclick="javascript:pageMove.goPost('map/view')" class="nav-link"> 
							<i class="far fa-circle nav-icon"></i>
							<p>Map Data 조회</p>
						</a>
					</li>
				</ul>
			</li>
			<li class="nav-item has-treeview">
				<a href="#" class="nav-link">
					<i class="nav-icon fas fa-th"></i>
					<p>	공통코드 <i class="right fas fa-angle-left"></i></p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="#" onclick="javascript:pageMove.goPost('board/boardMng')" class="nav-link"> 
							<i class="far fa-circle nav-icon"></i>
							<p>게시판 관리</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="#" onclick="javascript:pageMove.goPost('board/board')" class="nav-link"> 
							<i class="far fa-circle nav-icon"></i>
							<p>게시물 관리</p>
						</a>
					</li>
				</ul>
			</li>
			<!-- 
			<li class="nav-item">z
				<a href="/resources/bootstrap/adminite/pages/widgets.html" class="nav-link">
					<i class="nav-icon fas fa-th"></i>
					<p>Widgets <span class="right badge badge-danger">New</span>
					</p>
				</a>
			</li>
			<li class="nav-item has-treeview">
				<a href="#" class="nav-link">
					<i class="nav-icon fas fa-copy"></i>
					<p>Layout Options <i class="fas fa-angle-left right"></i> <span class="badge badge-info right">6</span>
					</p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/layout/top-nav.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Top Navigation</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/layout/top-nav-sidebar.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Top Navigation + Sidebar</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/layout/boxed.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Boxed</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/layout/fixed-sidebar.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Fixed Sidebar</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/layout/fixed-topnav.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Fixed Navbar</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/layout/fixed-footer.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Fixed Footer</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/layout/collapsed-sidebar.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Collapsed Sidebar</p>
					</a></li>
				</ul>
			</li>
			<li class="nav-item has-treeview">
				<a href="#" class="nav-link">
					<i class="nav-icon fas fa-chart-pie"></i>
					<p>	Charts <i class="right fas fa-angle-left"></i></p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/charts/chartjs.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>ChartJS</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/charts/flot.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Flot</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/charts/inline.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Inline</p>
					</a></li>
				</ul>
			</li>
			<li class="nav-item has-treeview"><a href="#" class="nav-link">
					<i class="nav-icon fas fa-tree"></i>
					<p>
						UI Elements <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/UI/general.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>General</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/UI/icons.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Icons</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/UI/buttons.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Buttons</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/UI/sliders.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Sliders</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/UI/modals.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Modals & Alerts</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/UI/navbar.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Navbar & Tabs</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/UI/timeline.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Timeline</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/UI/ribbons.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Ribbons</p>
					</a></li>
				</ul></li>
			<li class="nav-item has-treeview"><a href="#" class="nav-link">
					<i class="nav-icon fas fa-edit"></i>
					<p>
						Forms <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/forms/general.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>General Elements</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/forms/advanced.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Advanced Elements</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/forms/editors.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Editors</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/forms/validation.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Validation</p>
					</a></li>
				</ul></li>
			<li class="nav-item has-treeview"><a href="#" class="nav-link">
					<i class="nav-icon fas fa-table"></i>
					<p>
						Tables <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/tables/simple.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Simple Tables</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/tables/data.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>DataTables</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/tables/jsgrid.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>jsGrid</p>
					</a></li>
				</ul></li>
			<li class="nav-header">EXAMPLES</li>
			<li class="nav-item"><a
				href="/resources/bootstrap/adminite/pages/calendar.html"
				class="nav-link"> <i class="nav-icon fas fa-calendar-alt"></i>
					<p>
						Calendar <span class="badge badge-info right">2</span>
					</p>
			</a></li>
			<li class="nav-item"><a
				href="/resources/bootstrap/adminite/pages/gallery.html"
				class="nav-link"> <i class="nav-icon far fa-image"></i>
					<p>Gallery</p>
			</a></li>
			<li class="nav-item has-treeview"><a href="#" class="nav-link">
					<i class="nav-icon far fa-envelope"></i>
					<p>
						Mailbox <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/mailbox/mailbox.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Inbox</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/mailbox/compose.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Compose</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/mailbox/read-mail.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Read</p>
					</a></li>
				</ul></li>
			<li class="nav-item has-treeview"><a href="#" class="nav-link">
					<i class="nav-icon fas fa-book"></i>
					<p>
						Pages <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/invoice.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Invoice</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/profile.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Profile</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/e-commerce.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>E-commerce</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/projects.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Projects</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/project-add.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Project Add</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/project-edit.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Project Edit</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/project-detail.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Project Detail</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/contacts.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Contacts</p>
					</a></li>
				</ul></li>
			<li class="nav-item has-treeview"><a href="#" class="nav-link">
					<i class="nav-icon far fa-plus-square"></i>
					<p>
						Extras <i class="fas fa-angle-left right"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/login.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Login</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/register.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Register</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/forgot-password.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Forgot Password</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/recover-password.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Recover Password</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/lockscreen.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Lockscreen</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/legacy-user-menu.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Legacy User Menu</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/language-menu.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Language Menu</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/404.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Error 404</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/500.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Error 500</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/pace.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Pace</p>
					</a></li>
					<li class="nav-item"><a
						href="/resources/bootstrap/adminite/pages/examples/blank.html"
						class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>Blank Page</p>
					</a></li>
					<li class="nav-item"><a href="starter.html" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>Starter Page</p>
					</a></li>
				</ul></li>
			<li class="nav-header">MISCELLANEOUS</li>
			<li class="nav-item"><a href="https://adminlte.io/docs/3.0"
				class="nav-link"> <i class="nav-icon fas fa-file"></i>
					<p>Documentation</p>
			</a></li>
			<li class="nav-header">MULTI LEVEL EXAMPLE</li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="fas fa-circle nav-icon"></i>
					<p>Level 1</p>
			</a></li>
			<li class="nav-item has-treeview"><a href="#" class="nav-link">
					<i class="nav-icon fas fa-circle"></i>
					<p>
						Level 1 <i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>Level 2</p>
					</a></li>
					<li class="nav-item has-treeview"><a href="#" class="nav-link">
							<i class="far fa-circle nav-icon"></i>
							<p>
								Level 2 <i class="right fas fa-angle-left"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a href="#" class="nav-link"> <i
									class="far fa-dot-circle nav-icon"></i>
									<p>Level 3</p>
							</a></li>
							<li class="nav-item"><a href="#" class="nav-link"> <i
									class="far fa-dot-circle nav-icon"></i>
									<p>Level 3</p>
							</a></li>
							<li class="nav-item"><a href="#" class="nav-link"> <i
									class="far fa-dot-circle nav-icon"></i>
									<p>Level 3</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-circle nav-icon"></i>
							<p>Level 2</p>
					</a></li>
				</ul></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="fas fa-circle nav-icon"></i>
					<p>Level 1</p>
			</a></li>
			<li class="nav-header">LABELS</li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon far fa-circle text-danger"></i>
					<p class="text">Important</p>
			</a></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon far fa-circle text-warning"></i>
					<p>Warning</p>
			</a></li>
			<li class="nav-item"><a href="#" class="nav-link"> <i
					class="nav-icon far fa-circle text-info"></i>
					<p>Informational</p>
			</a></li>
			 -->
		</ul>
	</nav>
	<!-- /.sidebar-menu -->
</div>
<!-- /.sidebar -->
<!-- /.navbar-static-side -->
<form id="formGo" action="/go" method="post">
	<input type="hidden" name="go" id="hidGo">
</form>