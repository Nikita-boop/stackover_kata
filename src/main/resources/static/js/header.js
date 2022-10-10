const header = document.createElement("header");
const container = document.querySelector(".page-header")
header.innerHTML = `
    <div class="d-flex justify-content-center">
        <nav class="navbar navbar-expand-lg navbar-light">

            <img src="/images/logo.svg" width="60" height="60" class="d-inline-block align-top">
            <a class="navbar-brand ml-3" href="#">stack <b>overflow</b></a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Пользователи</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Вопросы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Теги</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Компании</a>
                    </li>
                </ul>
                <div class="input-group mb-0">
                    <input type="text" class="form-control" placeholder="Поиск" aria-label="Поиск"
                           aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="button-addon2">Поиск</button>
                    </div>
                </div>
            </div>
            <div class="container w-auto ml-0">
                <a class="btn btn-primary  mr-1" href="#" role="button">Log in</a>
                <a class="btn btn-info  mr-1" href="#" role="button">Sign up</a>
            </div>
        </nav>
    </div>`
container.appendChild(header)