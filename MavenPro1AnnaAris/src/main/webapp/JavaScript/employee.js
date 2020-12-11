window.onload=function(){

    console.log("in onload");
    // document.getElementById("digisubmit").addEventListener("click",getDigimon, false);
    document.getElementById("json_fname").innerHTML="HelloWorld";
    getEmployee();


}


function getEmployee() {
    console.log("in employee");
    // let fName=document.getElementById("json_fname").value;
    // Step 1
    var xhr = new XMLHttpRequest();
    // Step 2
    xhr.onreadystatechange = function() {
        console.log("in ORSC");
        if (xhr.readyState==4&&xhr.status==200) {
            console.log(xhr.responseText);
            var employee = JSON.parse(xhr.responseText);
            loadEmployee(employee);
        }
    }
    // Step 3
    xhr.open("POST", "employeeJsonString",true);
    // Step 4
    xhr.send();

}

function loadEmployee(employee){
    document.getElementById("json_fname").innerHTML="Goodbye Pie!";
    
    // document.getElementById("json_fname").innerHTML="HelloWorld";
    // document.getElementById("digiImage").src = digimon[0].img;
    // document.getElementById("digiLevel").innerHTML = "Level: "+digimon[0].level;
}

function clickMe(){
    document.getElementById("json_fname").innerHTML=employee[0];
    // getEmployee();
}