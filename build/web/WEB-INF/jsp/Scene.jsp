<%@page import="java.util.List"%>
<%@page import="model.Scene"%>
<%@page import="model.Film"%>
<!DOCTYPE html>
<%
List<Scene> allScene=(List<Scene>)request.getAttribute("scene");
%>
<html lang="en">

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
    <%@ include file="navbar/navScene.jsp" %>
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
                <% for(Scene scene:allScene){%>
                <tr>
                    <td><%= scene.getId() %></td>
                    <td><%= scene.getLieu().getNom() %></td>
                    <td><%= scene.getTemps().getNom() %></td>
                    <td>Cell 2</td>
                    <td class="table-secondary">
                        <form action="" method="">
                            <input type="hidden" name="id" value="<%= scene.getId() %>"/>
                            <button class="btn btn-success btn-sm" type="button">Button</button>
                        </form>
                        
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
            <div class="text-center"><a class="btn btn-success btn-sm" type="button" style="margin: 10px;" href="ajouterScene">Nouvelle Scène</a> <a class="btn btn-success btn-sm" type="button" href="planificationScene">Optimisation du tournage</a></div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>