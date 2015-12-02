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
					<#include "header.ftl">
					<#if warn??>
						<div class="alert alert-danger alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							<strong>Erreur : </strong>
							${warn}
						</div>
					</#if>
					<#if good??>
						<div class = "alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							${good}
						</div>
					</#if>		
					<div class="content" id="corpus">
						<h1>
					    	Connexion Enseignant
						</h1>
					
						<p>
							Vous avez déjà créé votre compte personnel. Vous pouvez y accéder en rentrant vos identifiants.
							Si vous n’avez pas encore créé de compte vous pouvez le faire <a href="/teachers/register">en cliquant ici</a>.
							<br/>
						</p>
						<form action="/teachers/login" id="TeacherLoginForm" method="post" accept-charset="utf-8">
							<div style="display:none;">
								<input type="hidden" name="_method" value="POST"/>
							</div>
							<div class="form-group" style="padding:1.5em 0;">
								<label for="TeacherEmail" class="col-sm-2 control-label">Adresse email</label>
								<div class="col-sm-10">
									<input name="data[Teacher][email]" role="form" class="form-control" maxlength="100" type="email" id="TeacherEmail" required="required"/>
								</div>
							</div>
							<div class="form-group" style="padding:1.5em 0;">
								<label for="TeacherPassword" class="col-sm-2 control-label">Mot de passe</label>
								<div class="col-sm-10 required">
									<input name="data[Teacher][password]" role="form" class="form-control" type="password" id="TeacherPassword" required="required"/>
								</div>
							</div>
							<div class="form-group" style="padding:1.5em 0;">
								<div class="col-sm-offset-2 col-sm-10">
								<input  class="btn btn-default" title="Se connecter" type="submit" value="Se connecter"/>
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
