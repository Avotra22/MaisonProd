<%@page import="model.Scene"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
    <%
        List<Scene> allScene = (List<Scene>) request.getAttribute("scene");
    %>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Production film</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/Contact-Form-Clean.css">
        <link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
    </head>

    <body>
        <%@ include file="navbar/navAccueil.jsp" %>
        <div style="height: 50px;">
            <h3 class="text-center">confirmation de planification de scene</h3>
        </div>
        <form action="confirmationplanificationController" method="post">
            <div class="table-responsive">

                <table class="table">
                    <thead>
                        <tr>
                            <th>Numero</th>
                            <th>Nom</th>
                            <th>Lieu</th>
                            <th>Date tournage</th>
                            <th>confirmation</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tbody>
                        <% for (Scene scene : allScene) {%>
                        <tr>
                            <td><%= scene.getId()%></td>
                            <td><%= scene.getLieu().getNom()%></td>
                            <td><%= scene.getTemps().getNom()%></td>
                            <td><%= scene.getDebutTournage().toString()%></td>
                            <td class="table-secondary">
                                <input type="checkbox" name="id" value="<%= scene.getId()%>" /> 
                            </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>

            </div>
            <div class="text-center"><button class="btn btn-success btn-sm" type="submit" style="margin: 10px;">Generer tournage</button><button class="btn btn-primary btn-sm" type="button" style="margin: 10px;">Annuler le tournage</button></div>
        </form>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>

</html>