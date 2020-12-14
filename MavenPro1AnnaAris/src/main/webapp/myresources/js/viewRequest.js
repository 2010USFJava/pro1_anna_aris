function buildTable() {
	var rows = "";
	console.log(currentEmp.title)
	if (currentEmp.title == "benCo") {
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
			rows += "<td><a id='view' href='changeRequest.html' class='btn btn-primary'>view</a></td>";
			rows += "</tr>";
		}
		document.getElementById("list").innerHTML = rows;
		return;
	}
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
		rows += "<td><a id='view' href='approveRequest.html' class='btn btn-primary'>view</a></td>";
		rows += "</tr>";
	}
	document.getElementById("list").innerHTML = rows;
}

function viewDetails() {
	document.getElementById("employee").innerHTML = "Name: " + requests[i].firstName + " " + requests[i].lastName + " Id:" + emp;
	document.getElementById("edate").innerHTML = requests[i].eventdate;
	document.getElementById("etype").innerHTML = type;
	document.getElementById("etime").innerHTML = requests[i].eventTime;
	document.getElementById("ecost").innerHTML = requests[i].cost;
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
	// document.getElementById('view').addEventListener("click", viewDetails);
}
