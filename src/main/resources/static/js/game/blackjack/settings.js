
const PREFERENCE_NAME = "blackjackSettingsPreference";

import { buttonFactory, clearChildren } from "../../util.js"

export function run(){

    const gameFrag = clearChildren("game");

    const settingsDiv = document.getElementById("settings");

    let preference = JSON.parse(localStorage.getItem(PREFERENCE_NAME));

    settingsDiv.append(getPlayerCount(), getInsuranceMechanic());

    if(preference){
        console.log(preference);
        for(let id in preference){
            document.getElementById(id).checked = true;
        }
    }


    settingsDiv.append(buttonFactory("Confirm", "click", ()=>{
        preference = {};
        for(let checkedElement of
            document.querySelectorAll("input:checked")
        ) preference[checkedElement.id] = checkedElement.value;
        localStorage.setItem(PREFERENCE_NAME, JSON.stringify(preference));
        console.log(localStorage.getItem(PREFERENCE_NAME));
        settingsDiv.replaceChildren();
        settingsDiv.className = "";
        document.getElementById("game").append(gameFrag);
    }));
}

function getPlayerCount(){
    const playerCountDiv = document.createElement("div");
    const p = document.createElement("p");
    p.textContent = "Number of player in a room:";
    playerCountDiv.append(p);

    for(let i = 0; i < 1; i++){
        const radio = document.createElement("input");
        radio.id = "playerCount"+(i+1);
        radio.type = "radio";
        radio.name = "playerCount";
        radio.value = i+1;

        const label = document.createElement("label");
        label.htmlFor = radio.id;
        label.textContent = i+1;
        label.append(radio);
        playerCountDiv.append(label);
    }

    return playerCountDiv;
}

function getInsuranceMechanic(){
    const insuranceMechanics = [ "DEFAULT_INSURANCE", "NO_INSURANCE"];
    const insuranceMechanicNames = [ "Default Insurance", "No Insurance" ];

    const insuranceMechanicDiv = document.createElement("div");
    const p = document.createElement("p");
    p.textContent = "Insurance: ";
    insuranceMechanicDiv.append(p);

    for(let i=0; i<insuranceMechanics.length;i++){
        const radio = document.createElement("input");
        radio.id = insuranceMechanics[i];
        radio.type = "radio";
        radio.name = "insuranceMechanic";
        radio.value = insuranceMechanics[i];

        const label = document.createElement("label");
        label.for = radio.id;
        label.textContent = insuranceMechanicNames[i];
        label.append(radio);
        insuranceMechanicDiv.append(label);
    }

    return insuranceMechanicDiv;
}

export function matchConditionFrom(preference){
    const fields = [ "playerCount", "insrType" ];
    let i = 0;

    const result = {};
    for(let id in preference){
        result[fields[i]] = preference[id];
        i++;
    }

    console.log(result);
    return result;
}

export { PREFERENCE_NAME }