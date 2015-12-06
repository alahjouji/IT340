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
					<#if good??>
						<div class = "alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							${good}
						</div>
					</#if>
					<#if warn??>
						<div class = "alert alert-danger alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							<strong> Erreur: </strong>
							${warn}
						</div>
					</#if>					
					<div class="content" id="corpus">
						<h1>
					    	Feuille de route
						</h1>
					<h3>Mes ateliers</h3>
					
						<table class="table table-hover">
	<thead>
        <tr>
			<th>Titre</th>
			<th>Type</th>
			<th>Disciplines</th>
			<th>Actions</th>
        </tr>
	</thead>
	<tbody>
<#list ateliers as atelier>

				<tr class="info">
					<td><a href="/atelier" onclick="location.href=this.href+'?atelierId='+${atelier.id};return false;">${atelier.titre}</a></td>
					<td>${atelier.type}</td>
					<td>
					<#list atelier.disciplines as discipline>
					${discipline}&nbsp;
					</#list>
					</td>
					<td><a href="/labs/deleteAtelier" onclick="location.href=this.href+'?atelierId='+${atelier.id};return false;">supprimer</a>
						<a href="/labs/editAtelier" onclick="location.href=this.href+'?atelierId='+${atelier.id};return false;">editer</a>
					</td>
				</tr>
</#list>
				
									</tbody>
</table>
					
					<br/>
					<h3>Inscriptions à mes ateliers</h3>
					
						<table class="table table-hover">
	<thead>
        <tr>
        	<th>Enseignant</th>
			<th>Titre</th>
			<th>Seance</th>
			<th>Public</th>
			<th>Participants</th>
			<th>Actions</th>
        </tr>
	</thead>
	<tbody>
<#list inscriptionsV as ins>

				<tr class="info">
					<td>${ins.teacherName}</td>
					<td><a href="/atelier" onclick="location.href=this.href+'?atelierId='+${ins.atelierId};return false;">${ins.atelierName}</a></td>
					<td>${ins.seance}</td>
					<td>${ins.pub}</td>
					<td>${ins.nombre}</td>
					<td>Validé</td>

				</tr>
</#list>
<#list inscriptionsW as ins>

				<tr class="info">
					<td>${ins.teacherName}</td>
					<td><a href="/atelier" onclick="location.href=this.href+'?atelierId='+${ins.atelierId};return false;">${ins.atelierName}</a></td>
					<td>${ins.seance}</td>
					<td>${ins.pub}</td>
					<td>${ins.nombre}</td>
					<td>
						<a href="/labs/validateIns" onclick="location.href=this.href+'?insId='+${ins.id};return false;">valider</a>
						<a href="/labs/declineIns" onclick="location.href=this.href+'?insId='+${ins.id};return false;">decliner</a>
					</td>

				</tr>
</#list>				
									</tbody>
</table>									
					</div>
				</div>
			</div>
		</div>


	</div>

<#include "footer.ftl">

</body>
</html>