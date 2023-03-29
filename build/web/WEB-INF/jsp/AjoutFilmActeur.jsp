<!DOCTYPE html>
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
    <%@ include file="navbar/navAccueil.jsp" %>
    <section class="contact-clean">
        <form class="text-center" method="post">
            <h2 class="text-center">Ajout d'Acteur</h2><label class="form-label">Acteurs</label>
            <div class="mb-3"></div><select class="form-select">
                <optgroup label="This is a group">
                    <option value="12" selected="">This is item 1</option>
                    <option value="13">This is item 2</option>
                    <option value="14">This is item 3</option>
                </optgroup>
            </select>
            <div class="mb-3"></div>
            <div class="mb-3"><button class="btn btn-primary" type="submit">Inserer</button></div>
        </form>
    </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>