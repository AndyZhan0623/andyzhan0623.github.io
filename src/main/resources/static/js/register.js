
import { createRequest } from "./util.js"

const username = document.getElementById("username");
const password = document.getElementById("password");
const registerBtn = document.getElementById("registerBtn");
const toLogin = document.getElementById("toLogin");

registerBtn.addEventListener("click", ()=>{
    const data = await createRequest()({
        path : "/register",
        params : {
            username : username.value,
            password : password.value
        },
        method : "POST"
    });

    localStorage.setItem("accessToken", data.accessToken);

    window.location.href = "../html/main.html";
});

toLogin.addEventListener("click", ()=>{ window.location.href = "../index.html" });