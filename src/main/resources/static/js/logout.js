let logoutButton = document.getElementById("logout");
let logoutHandler = function () {
    fetch("/api/login/outLogin?" + new URLSearchParams({
        username: 'admin',
    }))
        .then(res => res.json())
        .then(resMsg => {
            if (resMsg.success) {
                window.location.href = "/login";
            }
        })
}

logoutButton.addEventListener("click", logoutHandler);