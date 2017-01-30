$(document).ready(function(){

$("input.phoneNumbers").on("change", function() {
    $("input.phoneNumbers").not(this).prop("checked", false);
})

});
