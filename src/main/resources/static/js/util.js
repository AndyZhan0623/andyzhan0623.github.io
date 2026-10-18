
export function createRequest(){
    let refreshPromise = null;
    const MAX_RETRY = 1;

    return async function request(config){
        const {
            path,
            params = null,
            method = "GET",
            headers = {},
            body = {}
        } = config;

        //Defensive statement.
        //Hope I will never use the method itself in the process of refresh.
        if(path==="/refresh") return res;

        let url = path;
        if(params) url = url + "?" + new URLSearchParams(params).toString();

        let _retry = config._retry ?? 0;

        const accessToken = localStorage.getItem("accessToken");

        //For Test Use
        console.log(accessToken);

        const res = await fetch(url, {
            method : method,
            credentials : "include",
            headers : {
                ...(headers),
                ...(accessToken && {"Authorization" : "Bearer " + accessToken}),
                "Content-type" : "application/json"
            },
            body : (method === "GET"||!body) ? undefined : JSON.stringify(body)
        });

        console.log(res.status);

        if(res.status!==401){
            console.log(res);
            return res.json();
        }

        if(_retry>=MAX_RETRY){
            console.log("Too many attempts.");
            return res;
        }

        if(!refreshPromise){
            refreshPromise = fetch("/refresh", {
                method : "POST",
                credentials : "include",
                header : {
                    "Content-type" : "application/json"
                },
                body : JSON.stringify({})
            })
            .then(res => res.json())
            .then(data => {
                if(data.successful){
                    localStorage.setItem("accessToken", data.accessToken);
                    return;
                }

                throw new Exception("Unexpected condition in transferring token.");
            })
            .finally(()=>{ refreshPromise = null; });
        }

        try{
            await refreshPromise;
        }catch(exp){
            localStorage.removeItem("accessToken");
            return Promise.reject(exp);
        }

        _retry++;

        return request({
            ...(config),
            _retry
        });
    }
}

export function buttonFactory(
    textContent,
    triggeredEvent,
    callBackFunction
){
    const button = document.createElement("button");
    button.textContent = textContent;
    button.addEventListener(triggeredEvent, callBackFunction);
    return button;
}

export function clearChildren(id){
    const node = document.getElementById(id);
    const nodeFrag = document.createDocumentFragment();
    for( let child of node.childNodes )
        nodeFrag.append(child);
    node.replaceChildren();

    return nodeFrag;
}

export function titleLike(s){
    let result = "";
    let lowerS = s.toLowerCase();
    for(let i=1;i<lowerS.length;i++){
        if(lowerS[i]==="_"){
            result = result + " " + lowerS[i+1].toUpperCase();
            i++;
        }
        else result = result + lowerS[i];
    }
    result = lowerS[0].toUpperCase() + result;
    return result;
}

export function insertAt(parent, element, index){
    console.log(element);
    parent.insertBefore(element, parent.children[index] ?? null);
}

const games = [ "blackjack" ];

export { games }

