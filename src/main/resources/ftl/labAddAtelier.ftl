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
					<#include "headerLabs.ftl">
					<div class="content" id="corpus">
						<h1>
					    	Ajout d'un atelier
						</h1>
					
						<p>
							Veuillez remplir le formulaire ci-dessous afin d'ajouter votre atelier
							<br/>
						</p>
						<form action="/labs/addAtelier" id="LabAddAtelierForm" method="post" accept-charset="utf-8">
							<div style="display:none;">
								<input type="hidden" name="_method" value="POST"/>
							</div>
							
							<h4 class="center">Titre</h4>
							<div class="form-group">
									<input name="data[Atelier][titre]" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Titre" required="required"/>
							</div>
							<br/>
							<h4 class="center">Disciplines</h4>
							<div class="form-group"><input type="hidden" name="topics" value="" id="Topics_"/>
								<select name="data[Atelier][topics]" role="form" class="form-control input-xxlarge" multiple="multiple" id="Topics" required="required">
									<option value="01">Anthropologie</option>
									<option value="02">Archéologie</option>
									<option value="03">Chimie</option>
									<option value="04">Environnement</option>
									<option value="05">Géographie</option>
									<option value="06">Histoire</option>
									<option value="07">Informatique</option>
									<option value="08">Mathématiques</option>
									<option value="09">Médecine - Santé</option>
									<option value="10">Philosophie</option>
									<option value="11">Physique</option>
									<option value="12">Physique - Chimie</option>
									<option value="13">Sciences de la Terre</option>
									<option value="14">Sciences de la vie</option>
									<option value="15">Sciences du numérique</option>
									<option value="16">Sciences économiques et sociales</option>
								</select>
							</div>
							<br/>
							<h4 class="center">Type</h4>
							<div class="form-group">
									<input type="hidden" name="type" value="" id="Type_"/>
									<select name="data[Atelier][type]" role="form" class="form-control" id="Type" required="required">
									<option value="01">Atelier Scientifique</option>
									<option value="02">Exposition</option>
									<option value="03">Conférence</option>
									<option value="04">Visite</option>
									</select>
							</div>
							<br/>				
							
							<h4 class="center">Seances</h4>
							<div class="form-group"><input type="hidden" name="seances" value="" id="Seances"/>
								<select name="data[Atelier][seances]" role="form" class="form-control input-xxlarge" multiple="multiple" id="Seances" required="required">
									<option value="01">Lundi Matin</option>
									<option value="02">Lundi Aprèm</option>
									<option value="03">Mardi Matin</option>
									<option value="04">Mardi Aprèm</option>
									<option value="05">Mercredi Matin</option>
									<option value="06">Mercredi Aprèm</option>
									<option value="07">Jeudi Matin</option>
									<option value="08">Jeudi Aprèm</option>
									<option value="09">Vendredi Matin</option>
									<option value="10">Vendredi Aprèm</option>
								</select>
							</div>
							<br/>
							
							<h4 class="center">Lieu</h4>
							<div class="form-group">
									<input name="data[Atelier][lieu]" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Lieu" required="required"/>
							</div>
							<br/>
							
							<h4 class="center">Durée</h4>
							<div class="form-group">
									<input type="number" step="1" min="10" name="data[Atelier][duree]" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Duree" required="required"/>
							</div>
							<span class="help-block">renseigner la durée en minutes</span>
							<br/>	
							
							<h4 class="center">Capacité</h4>
							<div class="form-group">
									<input type="number" step="1" min="5" name="data[Atelier][capacite]" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Capacite" required="required"/>
							</div>
							<br/>
							
							<h4 class="center">Résumé</h4>
							<div class="form-group">
									<input name="data[Atelier][resume]" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Resume" required="required"/>
							</div>
							<br/>				
							
							<h4 class="center">Public visé</h4>
							<div class="form-group"><input type="hidden" name="publics" value="" id="Publics_"/>
								<select name="data[Atelier][public]" role="form" class="form-control input-xxlarge" multiple="multiple" id="Publics" required="required">
									<option value="01">Primaires</option>
									<option value="02">6èmes et 5èmes</option>
									<option value="03">4èmes et 3èmes</option>
									<option value="04">Secondes</option>
									<option value="05">Premières</option>
									<option value="06">Terminales</option>
									<option value="07">Classes préparatoires</option>
									<option value="08">Universitaires</option>
								</select>
							</div>
							<br/>
							
							<h4 class="center">Animateurs</h4>
							<div class="form-group">
									<input name="data[Atelier][animateurs]" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Animateurs" required="required"/>
							</div>
							<span class="help-block">Séparer les noms des animateurs par une virgule</span>
							<br/>
																										
							<div class="form-group">
								<div class="center">
								<input  class="btn btn-default" title="Ajouter atelier" type="submit" value="Ajouter atelier"/>
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
