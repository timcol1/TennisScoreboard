window.addEventListener('pageshow', function (event) {
    var currentUrl = window.location.href;

    var url = new URL(currentUrl);

// Получаем значение параметра по его имени
    var parameterValue = url.searchParams.get('page');
    if (parameterValue == null || parameterValue === 1) {
        parameterValue = 1;

        var buttonPrevious = document.getElementById("butt-prev");

        buttonPrevious.disabled = true;
    }
});