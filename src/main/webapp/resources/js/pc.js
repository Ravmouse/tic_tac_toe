/**
 * Отправляет на сервер ход пользователя при обычной игре (когда пользователь 1-ый начал игру) и получает ход pc,
 * чтобы отобразить его.
 */
function getPcMove(move) {
    $.ajax({
        url: './play',
        method: 'POST',
        dataType: 'text',
        data: move.toString(),
        success: function (number) {
            drawPcMove(number);
            checkWinOrFull(pc, "COMPUTER WINS!");
        }
    });
}

/**
 * В ячейке появляется ход pc.
 */
function drawPcMove(move) {
    for (let i = 0; i < boxes.length; i++) {
        let box = boxes[i];
        if (box.id === move) {
            box.innerHTML = pc;
        }
    }
}