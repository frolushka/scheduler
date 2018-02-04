window.onload = function() {
	createTable();
}
var time = ["8:00","8:30","9:00","9:30","10:00"];
function createTable() {
	var html = "<table>";
	html+="<tr><td></td>";
	for(var i = 0; i < 7; ++i) {
		html += "<td id=\"day" + i + "\">day"+i+"</td>";
	}
	html+="</tr>";
	for(var i = 0; i < time.length; ++i){
		html+="<tr id=\"time"+i+"\"><td class=\"time\">"+time[i]+"</td>";
		for(var z = 0; z < 7; ++z) {
			html += "<td class=\"calendardays\" id=\"day" + z + "time" + i + "\">" + z + " " + i +"</td>";
		}
		html+="</tr>";
	}
	html+="</table>";
	document.getElementById("main-calendar").innerHTML = html;
}

function insertEvent(end,start,time,day,text,n){
	var length = end - start;
	var height = document.getElementById("time"+time).style.height;
	var width = document.getElementById("day"+day).style.width;
	var id = "day" + day + "time" + time;
	var cell = document.getElementById(id);
	var div = document.createElement("div");
	div.style.position = "absolute";
	div.style.left = cell.style.left;
	div.style.top = cell.style.top;

	div.style.backgroundColor = "#444";
	div.style.overflow = "visible";
	div.style.width = "100px";//width / n + "px";
	div.style.height = length*30 + "px";//length * height + "px";
	div.innerHTML = text;
	document.getElementById("day"+day+"time"+time).appendChild(div);
}
