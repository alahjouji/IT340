<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />       
    <title>
        Circuit Scientifique Bordelais - CNRS
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
	<font size="7"><b>${atelier.labName}</b></font>
<h1>"${atelier.titre}"<small>${atelier.type}</small></h1>
<hr/>
<p>
	<strong>Thèmes disciplinaires : </strong> 
						<#list atelier.disciplines as discipline>
					${discipline}&nbsp;
					</#list>
	<br/>
</p>
<legend>Accès</legend>
<address>
	<strong>Adresse :</strong>
	<p style="padding-left: 1em; ">
		${atelier.lieu}
		<br />
	</p>
</address>
	<strong>Géolocalisation :</strong> 
	<div id="map" style="width: 100%; height: 400px"></div>
	<script type="text/javascript">
		window.addEventListener('load', function () {
			var startLat = parseFloat("${latitude}");
			var startLng = parseFloat("${longitude}");
			var optionsGmaps = {
				center: new google.maps.LatLng(startLat, startLng),
				mapTypeId: google.maps.MapTypeId.ROADMAP,
				zoom: 17
			};
			window.map = new google.maps.Map(document.getElementById("map"), optionsGmaps);
			window.geocoder = new google.maps.Geocoder();
			window.marker = new google.maps.Marker({position: new google.maps.LatLng(startLat, startLng), map: window.map});
		});</script>
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js"></script>
	<br/>
	<legend>Informations</legend>
<p>
	<strong>Durée : </strong> ${atelier.duree} minutes<br/>
	<strong>Capacité : </strong> ${atelier.capacite} personnes<br/>
</p>

<h4>Résumé :</h4>
<p style="padding-left: 1em;">${atelier.resume}
			<br />
</p>

<h4>Animateur(s) / Conférencier(s) :</h4>
<p style="padding-left: 1em;">						
	<#list atelier.animateurs as animateur>
					${animateur}<br/>
					</#list>
<br />
</p>


<legend>Public visé</legend>
<p style="padding-left: 1em;">
	<strong>
								<#list atelier.publics as public>
					${public}&nbsp;
					</#list></strong>
</p>
<br/>
<h2>Plages horaires</h2>
<hr/>
<table class="table table-hover">
	<thead>
        <tr>
			<th>Séance</th>
			<th style="text-align: center">Durée</th>
			<th style="text-align: center">Capacité</th>
			<th style="text-align: center">Inscrits</th>
			        </tr>
	</thead>
	<tbody>
<#list atelier.seances as seance>

				<tr>
					<td>${seance.nom}</td>
					<td style="text-align: center">${atelier.duree}</td>					
					<td style="text-align: center">${atelier.capacite}</td>					
					<td style="text-align: center">${seance.inscrit}</td>
				</tr>
</#list>
				</tbody>
</table>
<br/>
						</div>
					</div>
				</div>
			</div>


		</div>


		<#include "footer.ftl">
	</html>
</html>