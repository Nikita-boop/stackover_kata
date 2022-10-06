const sidebar = document.createElement("sidebar");
sidebar.innerHTML = "<div class='nav flex-column'>" +
    "<a href='#'>About</a>" +
    "<a href='#'>Services</a>" +
    "<a href='#'>Clients</a>" +
    "<a href='#'>Contact</a>" +
    "</div>";
document.body.prepend(sidebar);
