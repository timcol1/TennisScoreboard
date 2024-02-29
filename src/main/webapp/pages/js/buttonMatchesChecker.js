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

    // Получение кнопки
    var buttonNext = document.getElementById('butt-next');

    // Установка значения атрибута disabled в зависимости от количества элементов
    if (elementsWithClass.length < 5) {
        buttonNext.disabled = true;
    }
});