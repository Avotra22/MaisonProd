<%@page import="model.Temps"%>
<%@page import="model.Lieu"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<%
    List<Lieu> lieu=(List<Lieu>)request.getAttribute("lieu");  
    List<Temps> temps=(List<Temps>)request.getAttribute("temps");
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
    <%@ include file="navbar/navScene.jsp" %>
    <section class="contact-clean">
        <form method="post" action="">
            <h2 class="text-center">Nouveau scène</h2><label class="form-label"></label>
            <div class="mb-3"></div>
           
            <div class="mb-3">
                 <h5 class="text-center">Nom</h5>
                <input class="form-control is-invalid" type="email" name="email" placeholder="Email">
                <small class="form-text text-danger">Please enter a correct email address.</small>
                <h5 class="text-center">Lieu</h5><select class="form-select">
                    <optgroup label="This is a group">
                       <% for(Lieu l:lieu) {%>
                        <option value="<%= l.getId() %>" ><%= l.getNom() %></option>
                        <% } %>
                    </optgroup>
                </select>
                <small class="form-text text-danger">Please enter a correct email address.</small>
                <h5 class="text-center">Temps</h5><select class="form-select">
                    <optgroup label="This is a group">
                         <% for(Temps t:temps) {%>
                        <option value="<%= t.getId() %>" ><%= t.getNom() %></option>
                        <% } %>
                    </optgroup>
                </select>
                <small class="form-text text-danger">Please enter a correct email address.</small>
            </div>
            <h5 class="text-center">Description</h5><textarea class="form-control">
            </textarea><small class="form-text text-danger">Please enter a correct email address.</small>
            <div class="mb-3"></div>
            <div class="text-center mb-3"><button class="btn btn-primary" type="submit">Creer</button></div>
        </form>
    </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>