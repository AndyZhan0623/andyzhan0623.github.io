
let x = 1;

function printMessage(){
	alert("你刚刚按了按钮，对吧？");
}
function change(){
	let y;
	x++;
	if(x%2==0){
		y = "Who the heck called me!";
	}
	else{
		y = "";
	}
	document.getElementById("out").innerHTML = y;
}
	