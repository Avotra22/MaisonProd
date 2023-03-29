<%@page import="model.Acteur"%>
<%@page import="model.Film"%>
<!DOCTYPE html>
<html lang="en">
<%
    Acteur acteur=(Acteur)request.getAttribute("acteur");
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

<body>
    <%@ include file="navbar/navAccueil.jsp" %>
    <div class="d-inline" style="height: 400px;">
        <div style="width: 450px;height: 400px;">
            <picture><img style="width: 450px;height: 400px;"></picture>
        </div>
        <div style="width: 450px;height: 400px;margin-top: -400px;margin-left: 470px;padding: 10;">
            <h1 style="padding: 10px;">Nom:<%= acteur.getNom() %></h1>
            <h4 style="padding: 10px;">Naissance: <%= acteur.getNaissance().toLocaleString() %> (38 ans)</h4>
            <h4 style="padding: 10px;">Film Réalisé:3</h4>
        </div>
    </div>
    <h4>Description</h4>
    <p><%= acteur.getDescription() %></p>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>