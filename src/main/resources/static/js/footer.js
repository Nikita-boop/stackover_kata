const footer = document.createElement("footer");
const footer_container = document.querySelector(".page-footer");
footer.innerHTML = `
    <div className="d-flex justify-content-start">
       <a href="#">Правила</a>
    </div>`
footer_container.appendChild(footer)