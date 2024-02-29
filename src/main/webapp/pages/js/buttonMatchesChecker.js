window.addEventListener('pageshow', function (event) {

    var currentUrl = window.location.href;

    var url = new URL(currentUrl);


    var parameterValue = url.searchParams.get('page');
    if (parameterValue == null || parameterValue <= 1) {

        var buttonPrevious = document.getElementById("butt-prev");

        buttonPrevious.disabled = true;
    }
});

window.addEventListener('pageshow', function (event) {

    var elementsWithClass = document.querySelectorAll('.id');

    var buttonNext = document.getElementById('butt-next');

    if (elementsWithClass.length <= 5) {
        buttonNext.disabled = true;
    }
});