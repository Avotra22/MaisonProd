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
    <%@ include file="navbar/navScene.jsp" %>
    <section class="contact-clean">
        <form method="post">
            <h2 class="text-center">Nouveau scène</h2>
            <h5 class="text-center">Acteur</h5><select class="form-select">
                <optgroup label="This is a group">
                    <option value="12" selected="">This is item 1</option>
                    <option value="13">This is item 2</option>
                    <option value="14">This is item 3</option>
                </optgroup>
            </select>
            <div class="mb-3"><small class="form-text text-danger">Please enter a correct email address.</small></div>
            <h5 class="text-center">Humeur</h5><select class="form-select">
                <optgroup label="This is a group">
                    <option value="12" selected="">This is item 1</option>
                    <option value="13">This is item 2</option>
                    <option value="14">This is item 3</option>
                </optgroup>
            </select>
            <div class="mb-3"><small class="form-text text-danger">Please enter a correct email address.</small>
                <h5 class="text-center">Texte</h5>
            </div>
            <div class="mb-3"><textarea class="form-control" name="message" placeholder="Message" rows="14"></textarea></div>
            <div class="text-center mb-3"><button class="btn btn-primary" type="submit">Creer</button></div>
        </form>
    </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>