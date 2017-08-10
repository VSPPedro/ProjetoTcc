<%@tag description ="Barra de navega��o comum �s p�ginas" body-content="empty" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="container-fluid menu-container">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-inverse navbar-fixed-top">
			  <div class="container">
			  	<div class="navbar-header">
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			      <a class="navbar-brand" href="#">Projeto Est�gio</a>
			    </div>
				<!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li class="active"><a href="#">In�cio <span class="sr-only">(current)</span></a></li>
			        <li><a href="#">Cadastrar Oferta de est�gio</a></li>
			        <li><a href="#">Outros menus...</a></li>
			        
			      </ul>
			      
			      <ul class="nav navbar-nav navbar-right">
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Usu�rio <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="#">Minha Conta</a></li>
			            <li role="separator" class="divider"></li>
			            <li><a href="#">Sair</a></li>
			          </ul>
			        </li>
			      </ul>
			    </div><!-- /.navbar-collapse -->
			  </div>
			</nav>
		</div>
	</div>
</div>
