
const ROW_FROM_SUIT = {
    "♥" : 0,
    "♠" : 1,
    "♣" : 2,
    "♦" : 3
}

const COLUMN_FROM_RANK = {
    "A" : 0,
    "2" : 1,
    "3" : 2,
    "4" : 3,
    "5" : 4,
    "6" : 5,
    "7" : 6,
    "8" : 7,
    "9" : 8,
    "10" : 9,
    "J" : 10,
    "Q" : 11,
    "K" : 12
}

export function pokerCardDivOf(pokerCard, scale){
    const cardDiv = document.createElement("div");
    cardDiv.classList.add("pokerCard");
    cardDiv.style.setProperty("--x", COLUMN_FROM_RANK[pokerCard.rank]);
    cardDiv.style.setProperty("--y", ROW_FROM_SUIT[pokerCard.suit]);
    if(scale)
        cardDiv.style.setProperty("--scale", scale);
    return cardDiv;
}

export function getHolePokerCardDiv(scale){
    const holeCardDiv = document.createElement("div");
    holeCardDiv.classList.add("pokerCard");
    holeCardDiv.style.setProperty("--x", 2);
    holeCardDiv.style.setProperty("--y", 4);
    if(scale)
        holeCardDiv.style.setProperty("--scale", scale);
    return holeCardDiv;
}

export function handDivOf(hand, firstUpCardIndex){
    const handDiv = document.createElement("div");

    if(firstUpCardIndex===undefined) firstUpCardIndex = 0;

    handDiv.classList.add("hand");
    for(let i=0;i<firstUpCardIndex;i++)
        handDiv.append(getHolePokerCardDiv());

    const cards = hand.cards;
    for(let i=firstUpCardIndex;i<cards.length;i++)
        handDiv.append(pokerCardDivOf(cards[i]));

    return handDiv;
}
