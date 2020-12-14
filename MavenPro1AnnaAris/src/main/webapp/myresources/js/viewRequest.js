function buildTable() {
	var rows = "";
	if (currentEmp.title == "ben_co") {
		for (var i = 0; i < requests.length; i++) {
			empId = requests[i].employeeId;
			type = requests[i].eventType;
			sup = requests[i].supStatus;
			head = requests[i].headStatus;
			ben = requests[i].benStatus;
			awarded = requests[i].awarded;

			rows += "<tr>";
			rows += "<td>" + empId + "</td>";
			rows += "<td>" + type + "</td>";
			rows += "<td>" + sup + "</td>";
			rows += "<td>" + head + "</td>";
			rows += "<td>" + ben + "</td>";
			rows += "<td>" + awarded + "</td>";
			rows += "<td><button onclick()='function() { var req = " + request[i] + "; localStorage.setItem('request', req); window.location.href='changeRequest.html';}'>view</button></td>";
			rows += "</tr>";
		}
		document.getElementById("list").innerHTML = rows;
		return;
	}
	for (var i = 0; i < requests.length; i++) {

		rows += "<tr>";
		rows += "<td>" + requests[i].employeeId + "</td>";
		rows += "<td>" + requests[i].eventType + "</td>";
		rows += "<td>" + requests[i].supStatus + "</td>";
		rows += "<td>" + requests[i].headStatus + "</td>";
		rows += "<td>" + requests[i].benStatus + "</td>";
		rows += "<td>" + requests[i].awarded + "</td>";
		rows += "<td><button onclick()='function() { var req = " + requests[i] + "; localStorage.setItem('request', req); window.location.href='approveRequest.html';}'>view</button></td>";
		rows += "</tr>";
	}
	document.getElementById("list").innerHTML = rows;
}

function viewDetails() {
	document.getElementById("employee").innerHTML = "Name: " + requests[i].firstName + " " + requests[i].lastName + " Id:" + emp;
	document.getElementById("id").value = requests[i].requestId;
	document.getElementById("edate").innerHTML = requests[i].eventDate;
	document.getElementById("etype").innerHTML = requests[i].eventType;
	document.getElementById("etime").innerHTML = requests[i].eventTime;
	document.getElementById("ecost").innerHTML = requests[i].cost;
	document.getElementById("est").innerHTML = requests[i].estimatedAward;
	document.getElementById("award").innerHTML = requests[i].amountAwarded;
	document.getElementById("estreet").innerHTML = requests[i].street;
	document.getElementById("ecity").innerHTML = requests[i].city;
	document.getElementById("estate").innerHTML = requests[i].state;
	document.getElementById("ezip").innerHTML = requests[i].zip;
	document.getElementById("edescription").innerHTML = requests[i].description;
}

function getRequests() {
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			requests = JSON.parse(xhr.responseText);
			buildTable();
		}
	}
	xhr.open("GET", "http://localhost:8080/pro1AnnaAris/requests.json");

	xhr.send();
}

function getCurrentEmp() {
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			currentEmp = JSON.parse(xhr.responseText);
		}
	}
	xhr.open("GET", "http://localhost:8080/pro1AnnaAris/currentEmployee.json");

	xhr.send();

	document.getElementById('view').addEventListener("click", viewDetails);
}

window.onload = function() {
	getRequests;
	getCurrentEmp;
	viewDetails;
}

