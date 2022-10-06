const header = document.createElement("header");
header.innerHTML = "<header class='header-outer fixed-top'>" +
    "<div class='container-fluid alert-dark fixed-top'>header content</div><div></div>" +
    "</header>";
document.body.prepend(header);