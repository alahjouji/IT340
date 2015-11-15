<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />       
    <title>
        Laboratoires - Circuit Scientifique Bordelais - CNRS
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
					<#include "header.ftl">
					<div class="content" id="corpus">
						<h1>
					    	Inscription Laboratoire
						</h1>
					
						<p>
							Vous êtes un laboratoire et vous souhaitez proposer un ou plusieurs ateliers scientifiques dans le cadre du circuit scientifique bordelais.
							<br/>
							Veuillez remplir le formulaire ci-dessous afin de créer votre espace personnel.
							<br/>
						</p>
						<form action="/labs/register" id="LabRegisterForm" method="post" accept-charset="utf-8">
							<div style="display:none;">
								<input type="hidden" name="_method" value="POST"/>
							</div>
							<div class="form-group" style="padding:1.5em 0;">
								<label for="LabNom" class="col-sm-2 control-label">Nom du laboratoire</label>
								<div class="col-sm-10">
									<input name="data[Lab][nom]" role="form" class="form-control" maxlength="100" type="text" id="LabNom" required="required"/>
								</div>
							</div>
							<div class="form-group" style="padding:1.5em 0;">
								<label for="LabRespo" class="col-sm-2 control-label">Nom du Responsable</label>
								<div class="col-sm-10">
									<input name="data[Lab][respo]" role="form" class="form-control" maxlength="100" type="text" id="LabRespo" required="required"/>
								</div>
							</div>							<div class="form-group" style="padding:1.5em 0;">
								<label for="LabTel" class="col-sm-2 control-label">Téléphone</label>
								<div class="col-sm-10">
									<input name="data[Lab][tel]" role="form" class="form-control" maxlength="100" type="text" id="LabTel" required="required"/>
								</div>
							</div>							<div class="form-group" style="padding:1.5em 0;">
								<label for="LabEmail" class="col-sm-2 control-label">Adresse email</label>
								<div class="col-sm-10">
									<input name="data[Lab][email]" role="form" class="form-control" maxlength="100" type="email" id="LabEmail" required="required"/>
								</div>
							</div>							
							<div class="form-group" style="padding:1.5em 0;">
								<label for="LabPassword" class="col-sm-2 control-label">Mot de passe</label>
								<div class="col-sm-10 required">
									<input name="data[Lab][password]" role="form" class="form-control" type="password" id="LabPassword" required="required"/>
								</div>
							</div>
							<div class="form-group" style="padding:1.5em 0;">
								<label for="LabPassword2" class="col-sm-2 control-label">confirmer le Mot de passe</label>
								<div class="col-sm-10 required">
									<input name="data[Lab][password2]" role="form" class="form-control" type="password" id="LabPassword2" required="required"/>
								</div>
							</div>
							<div class="form-group" style="padding:1.5em 0;">
								<div class="col-sm-offset-2 col-sm-10">
								<input  class="btn btn-default" title="S'inscrire" type="submit" value="S'inscrire"/>
								</div>
							</div>
						</form>						
					</div>
				</div>
			</div>
		</div>


	</div>

<#include "footer.ftl">

</body>
</html>
