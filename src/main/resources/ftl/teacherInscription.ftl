<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />       
    <title>
        Enseignants - Circuit Scientifique Bordelais - CNRS
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/img/favicon.ico" type="image/x-icon" rel="icon" />
    <link href="/img/favicon.ico" type="image/x-icon" rel="shortcut icon" />
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/footer.css" />
    <script type="text/javascript" src="/js/main.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div id="wrap">

        <div class="container" id="main">
            <div class="row">
                <div class="col-xs-12 col-lg-2 visible-lg-block" id="sidebar">
                    <a href="/" id="logo">

                    </a>
                    <div id="discover">
                        Venez découvrir le monde passionnant de la recherche
                    </div>
                </div>
                <div class="col-xs-12 col-lg-10">
					<#include "headerTeachers.ftl">
					<div class="content" id="corpus">
						<h1>
					    	Inscription à l'atelier: ${atelier.titre}
						</h1>
					

						<form action="/teachers/sinscrireAtelier" id="InscrireAtelierForm" method="post" accept-charset="utf-8">
							<div style="display:none;">
								<input type="hidden" name="_method" value="POST"/>
								<input type="hidden" name="atelierId" value="${atelier.id}"/>
							</div>
							

							<h4 class="center">Seance</h4>
							<div class="form-group">
									<input type="hidden" name="sean" value="" id="Seance_"/>
									<select name="seance" role="form" class="form-control" id="seance" required="required">
									<#list atelier.seances as seance>
										<option value="${seance.nom}">${seance.nom}</option>
									</#list>																
									</select>
							</div>
							<br/>				
							
							<h4 class="center">Public</h4>
							<div class="form-group">
									<input type="hidden" name="pub" value="" id="Public_"/>
									<select name="public" role="form" class="form-control" id="public" required="required">
									<#list atelier.publics as public>
										<option value="${public}">${public}</option>
									</#list>																
									</select>
							</div>
							<br/>								
							
							<h4 class="center">Nombre de participants</h4>
							<div class="form-group">
									<input type="number" step="1" name="nombre" min="1" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Nombre" required="required"/>
							</div>
							<br/>
							
							
																										
							<div class="form-group">
								<div class="center">
								<input  class="btn btn-default" title="S'inscrire" type="submit" value="S'inscrire"/>
								</div>
							</div>
						</form>	
						<br/><br/><br/>					
					</div>
				</div>
			</div>
		</div>


	</div>

<#include "footer.ftl">

</body>
</html>
