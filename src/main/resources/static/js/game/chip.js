
import { buttonFactory, createRequest } from "../util.js"



let chip = 0;
let bet = 0;

const uiLayer = document.getElementById("ui");
//const chipUI = document.getElementById("chip");
//const betUI = document.getElementById("bet");

export function renderChip(){
    const chipUI = document.createElement("p");
    chipUI.textContent = "Chip: ";
    const span = document.createElement("span");
    span.id = "chip";
    chipUI.append(span);
    uiLayer.append(chipUI);
}

export function renderBet(){
    const betUI = document.createElement("p");
    betUI.textContent = "Bet: ";
    const span = document.createElement("span");
    span.id = "bet";
    betUI.append(span);
    uiLayer.append(betUI);
}

export function clearUI(){
    uiLayer.replaceChildren();
}
/*
const resetChipBtn = buttonFactory("Reset Chip", "click", async ()=>{
    const data = await createRequest()({
        path : "/user/me/chip/reset",
        method : "POST"
    });

    chip = data.chip;
    display();
});
uiLayer.append(resetChipBtn);
*/



export function display(){
    document.getElementById("chip").textContent = chip;
    const betUI = document.getElementById("bet");
    if(betUI)
        betUI.textContent = bet;
}

export async function updateChip(givenChip){
    if(givenChip) chip = givenChip;
    else{
        const data = await createRequest()({ path : "/user/me/chip" });
        chip = data.chip;
    }
    display();
}

function resetBet(){
    chip += bet;
    bet = 0;
    display();
}

function doBet(playerBet){
    if(playerBet>chip){
        alert("Insufficient Bet!");
        return;
    }

    bet += playerBet;
    chip -= playerBet;

    display();
}

const whiteChip = buttonFactory("1", "click", ()=>{ doBet(1); });
const redChip = buttonFactory("5", "click", ()=>{ doBet(5); });
const greenChip = buttonFactory("25", "click", ()=>{ doBet(25); });
const blackChip = buttonFactory("100", "click", ()=>{ doBet(100); });
const blueChip = buttonFactory("500", "click", ()=>{ doBet(500); });

const chips = [ whiteChip, redChip, greenChip, blackChip, blueChip ];
const colors = [ "white", "red", "green", "black", "blue" ];

for(let i = 0; i < chips.length; i++){
    chips[i].classList.add("chip-button");
    chips[i].classList.add(colors[i]+"-chip");
}

const resetBetBtn = buttonFactory("Reset", "click", ()=>{
    chip += bet;
    bet = 0;
    display();
});
const confirmBetBtn = document.createElement("button");
confirmBetBtn.textContent = "Confirm";
//I do not use buttonFactory here, since this button used to confirm the bet does not know
//what game module will be loaded yet.

renderChip();
renderBet();
updateChip();

export { bet, chips, resetBetBtn, confirmBetBtn }