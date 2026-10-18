
import { createRequest } from "./util.js"

const username = document.getElementById("username");
const password = document.getElementById("password");
const loginBtn = document.getElementById("loginBtn");
const toRegister = document.getElementById("toRegister");

loginBtn.addEventListener("click", async ()=>{
    const data = await createRequest()({
       path : "/login",
       params : {
            username : username.value,
            password : password.value
       },
       method : "POST"
    });

    localStorage.setItem("accessToken", data.accessToken);

    window.location.href = "../html/main.html";
});

toRegister.addEventListener("click", ()=>{ window.location.href = "../html/register.html"; });