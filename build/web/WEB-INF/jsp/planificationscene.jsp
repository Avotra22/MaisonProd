<%@page import="model.Scene"%>
<%@page import="java.util.List"%>
<%
    List<Scene> allScene = (List<Scene>) request.getAttribute("scene");
%>

<!DOCTYPE html>
<html lang="en">

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
        <form action="planificationController" method="post">

            <div style="height: 180px;">
                <h3 class="text-center">planification de scene</h3>
                <div class="text-center" style="height: 50px;margin: 10px;"><label class="form-label" style="margin: 10px;">Debut</label><input type="date" name="debut"><label class="form-label" style="margin: 10px;">Fin</label><input type="date" name="fin"></div>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Numero</th>
                            <th>Lieu</th>
                            <th>Temps</th>
                            <th>Statut</th>
                            <th>Voir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Scene scene : allScene) {%>
                        <tr>
                            <td><%= scene.getId()%></td>
                            <td><%= scene.getLieu().getNom()%></td>
                            <td><%= scene.getTemps().getNom()%></td>
                            <td>Cell 2</td>
                            <td class="table-secondary">
                                <input type="checkbox" name="id" value="<%= scene.getId()%>" /> 
                            </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
            <div class="text-center"><button class="btn btn-success btn-sm" type="submit">Generer tournage</button></div>
        </form>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>

</html>