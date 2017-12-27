
function openDropdown(id){
    document.getElementById(id).classList.toggle("show");
}
function showAbroad(show){
   if(show){
        document.getElementById("abroadCredits").style.display = "block";
   }
   else{
        document.getElementById("abroadCredits").style.display = "none";
   }
}

function uploadTranscript(){
    var formData = new FormData();
    var x = document.getElementById("transcript");
    formData.append("transcript", x)
    var xhr  =  new XMLHttpRequest();
    xhr.open("POST", "/storeHistory", true);
    xhr.send(formData);
}

function storeInterest(division){
    var xhr  =  new XMLHttpRequest();
    xhr.open("POST", "/storeInterest", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        value: division
    }));
}

function storeClass(year){
    var xhr  =  new XMLHttpRequest();
    xhr.open("POST", "/storeClass", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        value: year
    }));
}

function storeCourse(subject, type){
    //type is major or minor and subject is field of study
    var xhr  =  new XMLHttpRequest();
    xhr.open("POST", "/storeCourse", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        field: subject,
        level: type
    }));
}

function abroadSemester(semester){
    if(semester != "NONE"){
        showAbroad(true);
    }
    else{
        showAbroad(false);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/storeCourse", true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            semes: semester
        }));
    }
}

//function submit(){
//    var xhr = new XMLHttpRequest();
//       xhr.open("GET", "/submit", true);
//       xhr.setRequestHeader('Content-Type', 'application/json');
//       xhr.send(JSON.stringify({
//           submitting: "true"
//       }));
//       //window.location.assign(xhr.response);
////        var xhr2 = new XMLHttpRequest();
////        xhr2.open("GET", "/result", true);
////        xhr2.send(null);
//       // window.location.assign("/result");
//     //TODO handle response to show result
//}

window.onclick = function(event){
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
          var openDropdown = dropdowns[i];
          if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
          }
        }
    }
}