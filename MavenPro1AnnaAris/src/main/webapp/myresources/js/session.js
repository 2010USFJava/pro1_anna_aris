var emp;
function getEmployee() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		console.log("the ready state has changed");
		if(xhttp.readyState==4 && xhttp.status==200){
			emp = JSON.parse(xhttp.responseText);
		}
	}
	xhttp.open("GET","http://localhost:8080/pro1AnnaAris/currentEmployee.json");
	
	xhttp.send();

}
function navbarDisplay() {
	var anchors = "";
	
	if(emp==null) {
		anchors += "<a class='navItem' href='home.html'>Home</a>";
		anchors += "<a id='log' class='navItem'	href='login.html'>Login</a>";
		document.getElementById("nav").innerHTMl = anchors;
		return; 
	}
	if(emp.title=="manager") {
		anchors += "<a class='navItem' href='landingPage.html'>Home</a>"; 
		anchors += "<a class='navItem' id='request' href='request.html'>Make Request</a>";
    	anchors += "<a class='navItem' href='approve_grade.html'>Approve Grade</a>";
    	anchors += "<a class='navItem' href='home.html'>Logout</a>";
		document.getElementById("nav").innerHTMl = anchors;
		return;
	}
	if(emp.title=="employee") {
		anchors += "<a class='navItem' href='landingPage.html'>Home</a>"; 
		anchors += "<a class='navItem' id='request' href='request.html'>Make Request</a>";
		anchors += "<a class='navItem' href='document_upload.html'>Document Upload</a>";
    	anchors += "<a class='navItem' href='home.html'>Logout</a>";
		document.getElementById("nav").innerHTMl = anchors;
		return;
	}
	
	anchors += "<a class='navItem' href='landingPage.html'>Home</a>"; 
	anchors += "<a class='navItem' id='request' href='request.html'>Make Request</a>";
    anchors += "<a id='all' class='navItem' href='viewAll'> View Requests </a>";
    anchors += "<a class='navItem' href='home.html'>Logout</a>";
	document.getElementById("nav").innerHTMl = anchors;

}
window.onload = function() {

	getEmployee;
}