<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />       
    <title>
        Accueil - Circuit Scientifique Bordelais - CNRS
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

                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div id="home1"><img class="img-responsive" style="height: 385px;" src="img/Accueil_CSB.png" alt="" width="365" /></div>
                                <div id="home2">LA <span style="color: #ff2956;">18<sup>&egrave;me</sup> &Eacute;DITION</span><br />AURA LIEU<br />DU LUNDI 5&nbsp;AU VENDREDI<br />9 OCTOBRE 2015</div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6">
                                        <h2><a class="nostyle" href="/teachers/login">R&Eacute;SERVER<br /><span style="color: #feca62;">UN ATELIER<br />SCIENTIFIQUE</span></a></h2>
                                        <hr />
                                        <p>Vous &ecirc;tes enseignant<br />et vous souhaitez<br />participer au cricuit<br />avec vos &eacute;l&egrave;ves</p>
                                        <a href="/teachers/register"><img id="home4" class="img-responsive" src="img/Accueil_Gauche_CSB.png" alt="" /></a>
                                    </div>
                                    <div class="col-xs-12 col-sm-6">
                                        <h2><a class="nostyle" href="/labs/login">CR&Eacute;ER<br /><span style="color: #feca62;">UN ATELIER<br />SCIENTIFIQUE</span></a></h2>
                                        <hr />
                                        <p>Vous &ecirc;tes chercheur ou<br />enseignant-chercheur et<br />vous souhaitez proposer<br />un atelier</p>
                                        <a href="/labs/register"><img id="home6" class="img-responsive" src="img/Accueil_Droite_CSB.png" alt="" /></a>
                                    </div>
                                </div>
                            </div>
                        </div>                      
                    </div>
                </div>
            </div>
        </div>


    </div>

<#include "footer.ftl">

</body>
</html>
