<%@page import="model.Scene"%>
<%@page import="model.Acteur"%>
<%@page import="model.FilmActeur"%>
<%@page import="model.Film"%>
<!DOCTYPE html>
<html lang="en">
<%
Film film=(Film)request.getAttribute("film");
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Production film</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Contact-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body class="text-start">
    <%@ include file="navbar/navScene.jsp" %>
    <div class="d-inline" style="height: 400px;">
        <div style="width: 450px;height: 400px;">
            <picture><img style="width: 450px;height: 400px;"></picture>
        </div>
        <div style="width: 450px;height: 400px;margin-top: -400px;margin-left: 470px;padding: 10;">
            <h1 style="padding: 10px;">Titre:<%= film.getTitre() %></h1>
            <h4 style="padding: 10px;">Date Creation: <%= film.getPublication().toLocaleString() %></h4>
            <h4 style="padding: 10px;">Realisateur:<%= film.getRealisateur().getNom() %></h4>
            <h4 style="padding: 10px;">Categorie:Aventure</h4>
            <h4 style="padding: 10px;">Budget:12 000 000 $</h4>
            <h4 style="padding: 10px;">Box office:50 000 000 $</h4>
            <h4 style="padding: 10px;">Statut: En production</h4>
        </div>
    </div>
    <h4>Description</h4>
    <p><%= film.getDescription() %></p>
    
    <h4>Les Acteurs</h4>
    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th>Numero</th>
                    <th>Nom</th>
                    <th>Naissance</th>
                    <th>Nombre film</th>
                    <th>voir</th>
                </tr>
            </thead>
            <tbody>
                <% for(Acteur act:film.getFilmActeur().getActeur()) {
                %>
                <tr>
                    <td><%= act.getId() %></td>
                    <td><%= act.getNom() %></td>
                    <td><%= act.getNaissance().toLocaleString() %></td>
                    <td>11</td>
                    <td class="table-secondary">
                        <form action="ficheActeur" method="get">
                            <input type="hidden" name="id" value="<%= act.getId() %>">
                            <button class="btn btn-success btn-sm" type="submit">Voir</button>
                        </form>
                    
                    </td>
                </tr>
               <%} %>
            </tbody>
        </table>
    </div>
    
    <div class="text-center"><button class="btn btn-success btn-sm text-center" type="button">Ajout Acteur</button></div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>