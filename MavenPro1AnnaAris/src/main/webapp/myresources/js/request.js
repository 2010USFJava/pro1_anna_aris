window.onload = function() {

    document.getElementById('approve').addEventListener("click", function() {
        document.getElementById('answer').value = "Approved";
    });
    document.getElementById('deny').addEventListener("click", function() {
        document.getElementById('answer').value = "Denied";
    });

}