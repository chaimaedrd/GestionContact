var selectedGroup = document.getElementById("selectedGroup")

var url = new URL(window.location.href)

selectedGroup.addEventListener("change",function (e){
    var selectedGroupValue = selectedGroup.options[selectedGroup.selectedIndex].value;
    console.log(selectedGroupValue)
    window.location.href = url.pathname+`?groupeOption=${selectedGroupValue}`
})