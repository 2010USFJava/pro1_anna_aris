window.onload=function(){

    console.log("in onload");
    // document.getElementById("digisubmit").addEventListener("click",getDigimon, false);
    document.getElementById("json_fname").innerHTML="HelloWorld";
    // getEmployee();
    console.log(emp);
    
    if(sessionStorage.getItem("successful commit")==true){
        
    }


}




function loadEmployee(employee){
    document.getElementById("json_fname").innerHTML="Goodbye Pie!";
    
    // document.getElementById("json_fname").innerHTML="HelloWorld";
    // document.getElementById("digiImage").src = digimon[0].img;
    // document.getElementById("digiLevel").innerHTML = "Level: "+digimon[0].level;
}

function clickMe(){
    document.getElementById("json_fname").innerHTML=emp[0];
    // getEmployee();
}