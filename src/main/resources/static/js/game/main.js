
import { buttonFactory, createRequest, games } from "../util.js"
import { chips, resetBetBtn, confirmBetBtn } from "./chip.js";

const gameDiv = document.getElementById("game");

const startBtn = buttonFactory("Start", "click", async () => {
    const gameChoices = await createRequest()({ path : "/gameChoices" });

    const choiceDiv = document.createElement("div");

    for(let i = 0; i < gameChoices.length; i++){
        const gameChoiceBtn = buttonFactory(gameChoices[i], "click", async ()=>{
            if(chip <= 0){
                alert("Insufficient chip!");
                return;
            }

            const path = "./"+games[i]+"/";

            gameDiv.className = "";
            choiceDiv.classList.add("bet");
            choiceDiv.classList.remove("menu1");

            const chipBtnFrag = document.createDocumentFragment();
            for(let chip of chips)
                chipBtnFrag.append(chip);

            confirmBetBtn.addEventListener("click", async ()=>{
                const gameModule = await import(path+games[i]+".js");
                console.log(gameModule);
                gameDiv.replaceChildren();
                gameModule.run();
            });

            const settingsBtn = buttonFactory("Settings", "click", async ()=>{
                document.getElementById("settings").classList.add("settings");
                const settingsModule = await import(path+"settings.js");
                settingsModule.run();
            });

            const buttonsDiv = document.createElement("div");
            buttonsDiv.append(
                resetBetBtn,
                confirmBetBtn,
                settingsBtn
            );
            buttonsDiv.classList.add("menu1");

            choiceDiv.replaceChildren(
                chipBtnFrag,
                buttonsDiv,
            );
        });

        choiceDiv.append(gameChoiceBtn);
    }

    choiceDiv.classList.add("menu1");

    gameDiv.replaceChildren(choiceDiv);
});

gameDiv.append(startBtn);