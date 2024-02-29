window.addEventListener('pageshow', function (event) {

    var currentUrl = window.location.href;

    var url = new URL(currentUrl);


    var parameterValue = url.searchParams.get('page');
    if (parameterValue == null || parameterValue <= 1) {

        var buttonPrevious = document.getElementById("butt-prev");

        buttonPrevious.disabled = true;
    }
});