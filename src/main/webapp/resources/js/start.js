/**
 * Отправляет на сервер символ пользователя.
 * Получает от сервера строку выигрышных комбинаций.
 * Проверяет, кто начинает игру первым: пользователь или pc. Если pc, то получает от сервера 1-ый ход pc.
 */
function start() {
    isStarted = true;

    if (document.getElementById("ch1").checked) {
        player = "O";
        pc = "X";
    } else {
        player = "X";
        pc = "O";
    }

    $.ajax({
        url: './init',
        method: 'POST',
        data: player,
    });

    $.get('./winner', function(data) {
        winners = Array.from(data);
    });

    if (document.getElementById("ch2").checked) {
        $.ajax({
            url: './comp',
            method: 'POST',
            dataType: 'text',
            success: function (number) {
                drawPcMove(number);
            }
        });
    }
    let input = $("input:checkbox");
    input.prop("disabled", true);
    input.css('background-color', '#f3f3f3');
    $("#but1").prop("disabled", true);
}