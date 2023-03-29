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
    <section class="contact-clean">
        <form class="text-center" method="post" action="/ajouterFilmController">
            <h2 class="text-center">Nouvel Acteur</h2>
            <h5>Nom</h5>
            <div class="mb-3"></div>
            <div class="mb-3">
                <input class="form-control is-invalid" type="text" name="nom" placeholder="Nom">
                <small class="form-text text-danger">Please enter a correct email address.</small>
            </div>
            <div class="mb-3">
                <h5>Naissance</h5>
                <input class="form-control is-invalid" type="date" name="naissance" placeholder="Naissance">
                <small class="form-text text-danger">Please enter a correct email address.</small>
                
                <h5>Description</h5>
                <textarea class="form-control" name="text" placeholder="description" rows="14"></textarea>
                <small class="form-text text-danger">Please enter a correct email address.</small>
            </div>
            <div class="mb-3"><button class="btn btn-primary" type="submit">INSERER</button></div>
        </form>
    </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>