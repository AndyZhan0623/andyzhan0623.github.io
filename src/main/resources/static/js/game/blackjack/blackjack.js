
import { PREFERENCE_NAME, matchConditionFrom } from "./settings.js"
import { createRequest, buttonFactory, titleLike, insertAt } from "../../util.js"
import { bet, display, clearUI, renderChip, updateChip } from "../chip.js"
import { pokerCardDivOf, getHolePokerCardDiv, handDivOf } from "../gameUtil.js"



export async function run(){

    const preference = JSON.parse(localStorage.getItem(PREFERENCE_NAME));

    const data = await createRequest()({
        path : "/blackjack/match",
        method : "POST",
        body : {
            bet : bet,
            condition : matchConditionFrom(preference)
        }
    });

    console.log(data);

    clearUI();
    renderChip();
    updateChip(data.playerViews[data.seatIndex].chip);
    render(data);

    console.log(data.insurance);
}



function render(gameStateDTO){
    const gameDiv = document.getElementById("game");
    gameDiv.replaceChildren();

    gameDiv.append(
        dealerDivFrom(gameStateDTO.dealerView),
        playerTabOf(
            gameStateDTO.userId,
            gameStateDTO.seatIndex,
            gameStateDTO.playerViews,
            gameStateDTO.currentPlayerView
        ),
        actionMenuFrom(gameStateDTO.availableActions)
    );

    document.getElementById("seat-"+gameStateDTO.seatIndex).click();

    gameStateDTO.insurance = true;

    if(gameStateDTO.insurance){
        const div = document.createElement("div");
        div.classList.add("menu1");
        div.style.position = "fixed";
        div.style.top = "0px";
        div.style.left = "0px";
        div.style.width = "100vw";
        div.style.height = "100vh";
        div.style.backgroundColor = "rgb(204, 204, 204, 50%)";
        const h1 = document.createElement("h1");
        h1.textContent = "Insurance?";
        h1.style.textAlign = "center";
        const yesBtn = buttonFactory("Yes", "click", ()=>{
            createRequest()({
                path : ""
            });
        });

        const noBtn = buttonFactory("No", "click", ()=>{div.remove();});
        div.append(h1, yesBtn, noBtn);
        document.querySelector("body").append(div);
    }
}

function dealerDivFrom(dealerView){
    const dealerDiv = document.createElement("div");

    const p = document.createElement("p");
    p.textContent = "Dealer:";
    p.style.textAlign = "center";
    const hand = handDivOf(dealerView.hand, 1);

    dealerDiv.append(p, hand);
    return dealerDiv;
}

function playerTabOf(userId, seatIndex, playerViews, currentPlayerView){
    const tab = document.createElement("div");
    tab.classList.add("tab");

    const tabHeads = document.createElement("div");
    tabHeads.classList.add("tabHeads");
    tabHeads.classList.add("menu2");
    tabHeads.classList.add("centerContainer");

    tab.append(tabHeads);

    for(let i=0;i<playerViews.length;i++){
        const playerView = playerViews[i];

        const tabHead = document.createElement("input");
        tabHead.type = "radio";
        tabHead.id = "seat-" + i;
        tabHead.name = "tabHead";
        tabHead.style.display = "none";

        const label = document.createElement("label");
        label.htmlFor = tabHead.id;
        label.textContent = playerView.username + " " +
            playerView.hands[playerView.currentHandIndex].handStatus;
        label.style.setProperty("--tab-count", playerViews.length);

        if(i===seatIndex){
            const span = document.createElement("span");
            span.textContent = " Acting";
            span.style.color = "#F92326";
            label.append(span);
        }

        tabHead.addEventListener("change", ()=>{
            tab.replaceChildren();
            tab.append(tabHeads, playerTabBodyFrom(playerView));
        });

        tabHeads.append(tabHead, label);
    }

    return tab;
}

function playerTabBodyFrom(playerView){
    const tabBody = document.createElement("div");
    tabBody.classList.add("tabBody");

    const addContent = frag => tabBody.replaceChildren(frag);

    const getContent = () => {
        playerView.currentHand = playerView.hands[playerView.currentHandIndex];

        const hand = handDivOf(playerView.currentHand);

        const p = document.createElement("p");
        p.style.position = "relative";
        p.style.right = "10vh";
        p.style.margin = "0";
        p.style.textAlign = "end";
        p.style.backgroundColor = "transparent";
        p.textContent = "Bet: ";
        const span = document.createElement("span");
        span.textContent = playerView.currentHand.bet;
        p.append(span);

        const browsingDiv = document.createElement("div");
        browsingDiv.classList.add("menu2");
        browsingDiv.classList.add("spaceEvenlyContainer");

        const previousBtn = buttonFactory("previous", "click", ()=>{
            playerView.currentHandIndex--;
            addContent(getContent());
        });
        browsingDiv.append(previousBtn);
        if(playerView.currentHandIndex <= 0) previousBtn.disabled = true;

        const nextBtn = buttonFactory("next", "click", ()=>{
            playerView.currentHandIndex++;
            addContent(getContent());
        });
        browsingDiv.append(nextBtn);
        if(playerView.currentHandIndex >= playerView.hands.length - 1) nextBtn.disabled = true;

        const frag = document.createDocumentFragment();
        frag.append(p, hand, browsingDiv);
        return frag;
    };

    console.log(getContent());

    addContent(getContent());

    return tabBody;
}

function actionMenuFrom(availableActions){
    const actionMenu = document.createElement("div");
    actionMenu.classList.add("menu2");
    actionMenu.classList.add("flexEndContainer");

    if(availableActions!==null){
        for(let action of availableActions){
            const actionBtn = buttonFactory(titleLike(action), "click", ()=>{

            });
            actionMenu.append(actionBtn);
        }
    }

    return actionMenu;
}