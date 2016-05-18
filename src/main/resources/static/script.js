function Dir(name, parent) {
    this.name = name;
    this.parent = parent;
}
var currentDir = new Dir(null, null);

function createImageElement(name){
    var img = document.createElement("img");
    img.src = "i?path=" + name;
    img.className = "mini";

    var a = document.createElement("a");
    a.rel = "group";
    a.href = "i?path=" + name;
    a.title = name;
    a.className = "fancybox";
    a.appendChild(img);
    return a;
}

function createParentDirElement(){
    var div = document.createElement("div");
    div.innerText = "..";
    div.addEventListener("click", cdUp);
    return div;
}

function createDirElement(name){
    var div = document.createElement("div");
    div.innerText = name.substring(name.lastIndexOf("/") + 1);
    div.title = name;
    div.addEventListener("click", cdDown);
    return div;
}


function cdDown(event){
    currentDir = new Dir(event.target.title, currentDir);
    cd()
}


function cdUp(event){
    currentDir = currentDir.parent;
    cd()
}

function cd(){
    var data  = new Object();
    data.path = currentDir.name;

    $.ajax({
        url: "d",
        data: data
    }).done(displayDir);
}

function displayDir(dirDesc) {
    $("#images").empty();
    $("#dirs").empty();

    if (currentDir.parent != null) {
        $("#dirs").append(createParentDirElement());
    }
    for (n in dirDesc.dirs){
        var dir = dirDesc.dirs[n];
        $("#dirs").append(createDirElement(dir))
    }

    for (n in dirDesc.images) {
        var image = dirDesc.images[n];
        var imageElement = createImageElement(image);
        $("#images").append(imageElement);
        $( imageElement ).hide(0);
        $( imageElement ).delay(250 * n).fadeIn(600);
    }

    $(".fancybox").fancybox();
}

$( document ).ready(function() {
    $.ajax({
        url: "root"
    }).done(function(root){$("#header").append(root)});
    cd();
});