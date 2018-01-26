time = ["8:00","8:30","9:00","9:30","10:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00","9:00"];
function createTable() {
	var html = "<table>";
	html+="<tr><td></td>";
	for(var i = 0; i < 7; ++i) {
		html += "<td id=\"day" + i + "\"></td>";
	}
	html+="</tr>";
	for(var i = 0; i < time.length; ++i){
		html+="<tr id=\"time"+i+"\"><td>"+time[i]+"</td>";
		for(var z = 0; z < 7; ++z) {
			html += "<td id=\"day" + z + "time" + i + " class=\"calendardays\"></td>";
		}
		html+="</tr>";
	}
	html+="</table>";
	document.getElementById("calendar").innerHTML = html;
}
function insertEvent(){
	var length = end - start;
	var height = document.getElementById("time"+time).style.height;
	var width = document.getElementById("day"+day).style.width;
	var id = "day" + day + "time" + time;
	var cell = document.getElementById(id);
	var div = document.createElement("div");
	div.style.width = width / n;
	div.style.height = length * height;
	div.innerHTML = text;
}
