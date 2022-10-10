const sidebar = document.createElement("sidebar");
const sidebar_container = document.querySelector(".page-sidebar");

sidebar.innerHTML = `
    <div class="sidebar">
       <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link" href="#">Главная</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Вопросы</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Участники</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Неотвеченные</a>
          </li>
       </ul>
    </div>`
sidebar_container.append(sidebar)