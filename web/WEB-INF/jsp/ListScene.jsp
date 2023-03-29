<%@page import="model.Film"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%
List<Film> allFilm=(List<Film>)request.getAttribute("allFilm");
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

<body class="text-center text-success">
    <nav class="navbar navbar-light navbar-expand-lg navigation-clean-button">
        <div class="container"><a class="navbar-brand" href="#">Maison de Prod</a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link active" href="#">Mes Films</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Creer Film</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Gestion Acteur</a></li>
                </ul><span class="navbar-text actions"> <a class="btn btn-light action-button" role="button" href="#">Log out</a></span>
            </div>
        </div>
    </nav>
    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th>Numero</th>
                    <th>Titre</th>
                    <th>Publication</th>
                    <th>Realisateur</th>
                    <th>Voir</th>
                </tr>
            </thead>
            <tbody>
                <% for(Film film:allFilm){%>
                <tr>
                    <td><%= film.getId() %></td>
                    <td><%= film.getTitre() %></td>
                    <td><%= film.getPublication().toLocaleString() %></td>
                    <td></td>
                    <td class="table-secondary">
                        <form action="ficheFilm" method="get">
                            <input type="hidden" name="idFilm" value="<%= film.getId() %>">
                            <button class="btn btn-success btn-sm" type="submit">Button</button>
                        </form>
                    
                    </td>
                    
                </tr>
               <%} %>
            </tbody>
        </table>
    </div><button class="btn btn-success" type="button">Nouvel Acteur</button>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>