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
					    	Modification d'un atelier
						</h1>
					

						<form action="/labs/editAtelier" id="LabEditAtelierForm" method="post" accept-charset="utf-8">
							<div style="display:none;">
								<input type="hidden" name="_method" value="POST"/>
								<input type="hidden" name="atelierId" value="${atelier.id}"/>
							</div>
							
							<h4 class="center">Titre</h4>
							<div class="form-group">
									<input name="data[Atelier][titre]" value="${atelier.titre}" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Titre" required="required"/>
							</div>
							<br/>
							<h4 class="center">Disciplines</h4>
							<div class="form-group"><input type="hidden" name="topics" value="" id="Topics_"/>
								<select name="data[Atelier][topics]" role="form" class="form-control input-xxlarge" multiple="multiple" id="Topics" required="required">

								<#if atelier.disciplines?seq_contains("Anthropologie")> 
									<option value="01" selected>Anthropologie</option>
								<#else>
									<option value="01">Anthropologie</option>
								</#if>
								<#if atelier.disciplines?seq_contains("Archéologie")> 
									<option value="02" selected>Archéologie</option>
								<#else>
									<option value="02">Archéologie</option>
								</#if>
								<#if atelier.disciplines?seq_contains("Chimie")> 
									<option value="03" selected>Chimie</option>
								<#else>
									<option value="03">Chimie</option>
								</#if>	
								<#if atelier.disciplines?seq_contains("Environnement")> 
									<option value="04" selected>Environnement</option>
								<#else>
									<option value="04">Environnement</option>
								</#if>
								<#if atelier.disciplines?seq_contains("Géographie")> 
									<option value="05" selected>Géographie</option>
								<#else>
									<option value="05">Géographie</option>
								</#if>
								<#if atelier.disciplines?seq_contains("Histoire")> 
									<option value="06" selected>Histoire</option>
								<#else>
									<option value="06">Histoire</option>
								</#if>		
								<#if atelier.disciplines?seq_contains("Informatique")> 
									<option value="07" selected>Informatique</option>
								<#else>
									<option value="07">Informatique</option>
								</#if>		
								<#if atelier.disciplines?seq_contains("Mathématiques")> 
									<option value="08" selected>Mathématiques</option>
								<#else>
									<option value="08">Mathématiques</option>
								</#if>		
								<#if atelier.disciplines?seq_contains("Médecine - Santé")> 
									<option value="09" selected>Médecine - Santé</option>
								<#else>
									<option value="09">Médecine - Santé</option>
								</#if>							
								<#if atelier.disciplines?seq_contains("Philosophie")> 
									<option value="10" selected>Philosophie</option>
								<#else>
									<option value="10">Philosophie</option>
								</#if>				
								<#if atelier.disciplines?seq_contains("Physique")> 
									<option value="11" selected>Physique</option>
								<#else>
									<option value="11">Physique</option>
								</#if>			
								<#if atelier.disciplines?seq_contains("Physique - Chimie")> 
									<option value="12" selected>Physique - Chimie</option>
								<#else>
									<option value="12">Physique - Chimie</option>
								</#if>					
								<#if atelier.disciplines?seq_contains("Sciences de la Terre")> 
									<option value="13" selected>Sciences de la Terre</option>
								<#else>
									<option value="13">Sciences de la Terre</option>
								</#if>			
								<#if atelier.disciplines?seq_contains("Sciences de la vie")> 
									<option value="14" selected>Sciences de la vie</option>
								<#else>
									<option value="14">Sciences de la vie</option>
								</#if>		
								<#if atelier.disciplines?seq_contains("Sciences du numérique")> 
									<option value="15" selected>Sciences du numérique</option>
								<#else>
									<option value="15">Sciences du numérique</option>
								</#if>		
								<#if atelier.disciplines?seq_contains("Sciences économiques et sociales")> 
									<option value="16" selected>Sciences économiques et sociales</option>
								<#else>
									<option value="16">Sciences économiques et sociales</option>
								</#if>	
								</select>
							</div>
							<br/>
							<h4 class="center">Type</h4>
							<div class="form-group">
									<input type="hidden" name="type" value="" id="Type_"/>
									<select name="data[Atelier][type]" role="form" class="form-control" id="Type" required="required">
									<#if atelier.type=="Atelier Scientifique">
										<option value="01" selected>Atelier Scientifique</option>
									<#else>
										<option value="01">Atelier Scientifique</option>
									</#if>
									<#if atelier.type=="Exposition">
										<option value="02" selected>Exposition</option>
									<#else>
										<option value="02">Exposition</option>
									</#if>
									<#if atelier.type=="Conférence">
										<option value="03" selected>Conférence</option>
									<#else>
										<option value="03">Conférence</option>
									</#if>
									<#if atelier.type=="Visite">
										<option value="04" selected>Visite</option>
									<#else>
										<option value="04">Visite</option>
									</#if>																		
									</select>
							</div>
							<br/>				
							
							<h4 class="center">Seances</h4>
							<div class="form-group"><input type="hidden" name="seances" value="" id="Seances"/>
								<select name="data[Atelier][seances]" role="form" class="form-control input-xxlarge" multiple="multiple" id="Seances" required="required">
								<#assign x = []>
								<#list atelier.seances as seance>
									<#assign x = x+[seance.nom]>
								</#list>
								<#if x?seq_contains("Lundi matin")> 
									<option value="01" selected>Lundi matin</option>
								<#else>
									<option value="01">Lundi matin</option>
								</#if>
								<#if x?seq_contains("Lundi après-midi")> 
									<option value="02" selected>Lundi après-midi</option>
								<#else>
									<option value="02">Lundi après-midi</option>
								</#if>
								<#if x?seq_contains("Mardi matin")> 
									<option value="03" selected>Mardi matin</option>
								<#else>
									<option value="03">Mardi matin</option>
								</#if>
								<#if x?seq_contains("Mardi après-midi")> 
									<option value="04" selected>Mardi après-midi</option>
								<#else>
									<option value="04">Mardi après-midi</option>
								</#if>
								<#if x?seq_contains("Mercredi matin")> 
									<option value="05" selected>Mercredi matin</option>
								<#else>
									<option value="05">Mercredi matin</option>
								</#if>
								<#if x?seq_contains("Mercredi après-midi")> 
									<option value="06" selected>Mercredi après-midi</option>
								<#else>
									<option value="06">Mercredi après-midi</option>
								</#if>
								<#if x?seq_contains("Jeudi matin")> 
									<option value="07" selected>Jeudi matin</option>
								<#else>
									<option value="07">Jeudi matin</option>
								</#if>
								<#if x?seq_contains("Jeudi après-midi")> 
									<option value="08" selected>Jeudi après-midi</option>
								<#else>
									<option value="08">Jeudi après-midi</option>
								</#if>
								<#if x?seq_contains("Vendredi matin")> 
									<option value="09" selected>Vendredi matin</option>
								<#else>
									<option value="09">Vendredi matin</option>
								</#if>
								<#if x?seq_contains("Vendredi après-midi")> 
									<option value="10" selected>Vendredi après-midi</option>
								<#else>
									<option value="10">Vendredi après-midi</option>
								</#if>
								</select>
							</div>
							<br/>
							
							<h4 class="center">Lieu</h4>
							<div class="form-group">
									<input name="data[Atelier][lieu]" value="${atelier.lieu}" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Lieu" required="required"/>
							</div>
							<br/>
							
							<h4 class="center">Durée</h4>
							<div class="form-group">
									<input type="number" step="1" min="10" name="data[Atelier][duree]" value="${atelier.duree}" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Duree" required="required"/>
							</div>
							<span class="help-block">renseigner la durée en minutes</span>
							<br/>	
							
							<h4 class="center">Capacité</h4>
							<div class="form-group">
									<input type="number" step="1" min="5" name="data[Atelier][capacite]" value="${atelier.capacite}" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Capacite" required="required"/>
							</div>
							<br/>
							
							<h4 class="center">Résumé</h4>
							<div class="form-group">
									<input name="data[Atelier][resume]" value="${atelier.resume}" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Resume" required="required"/>
							</div>
							<br/>				
							
							<h4 class="center">Public visé</h4>
							<div class="form-group"><input type="hidden" name="publics" value="" id="Publics_"/>
								<select name="data[Atelier][public]" role="form" class="form-control input-xxlarge" multiple="multiple" id="Publics" required="required">
								<#if atelier.publics?seq_contains("Primaires")> 
									<option value="01" selected>Primaires</option>
								<#else>
									<option value="01">Primaires</option>
								</#if>
								<#if atelier.publics?seq_contains("6èmes et 5èmes")> 
									<option value="02" selected>6èmes et 5èmes</option>
								<#else>
									<option value="02">6èmes et 5èmes</option>
								</#if>
								<#if atelier.publics?seq_contains("4èmes et 3èmes")> 
									<option value="03" selected>4èmes et 3èmes</option>
								<#else>
									<option value="03">4èmes et 3èmes</option>
								</#if>
								<#if atelier.publics?seq_contains("Secondes")> 
									<option value="04" selected>Secondes</option>
								<#else>
									<option value="04">Secondes</option>
								</#if>
								<#if atelier.publics?seq_contains("Premières")> 
									<option value="05" selected>Premières</option>
								<#else>
									<option value="05">Premières</option>
								</#if>
								<#if atelier.publics?seq_contains("Terminales")> 
									<option value="06" selected>Terminales</option>
								<#else>
									<option value="06">Terminales</option>
								</#if>
								<#if atelier.publics?seq_contains("Classes préparatoires")> 
									<option value="07" selected>Classes préparatoires</option>
								<#else>
									<option value="07">Classes préparatoires</option>
								</#if>
								<#if atelier.publics?seq_contains("Universitaires")> 
									<option value="08" selected>Universitaires</option>
								<#else>
									<option value="08">Universitaires</option>
								</#if>
								</select>
							</div>
							<br/>
							
							<h4 class="center">Animateurs</h4>
							<div class="form-group">
								<#assign s = "">
								<#list atelier.animateurs as animateur>
									<#assign s = s+animateur+",">
								</#list>
									<input name="data[Atelier][animateurs]" value="${s}" role="form" class="form-control input-xxlarge"  maxlength="100" type="text" id="Animateurs" required="required"/>
							</div>
							<span class="help-block">Séparer les noms des animateurs par une virgule</span>
							<br/>
																										
							<div class="form-group">
								<div class="center">
								<input  class="btn btn-default" title="Modifier l'atelier" type="submit" value="Modifier l'atelier"/>
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
