let boxes = $('.container .box');
let player;
let pc;
let isStarted = false;
let winners = [];
// let winners = Array.from("012345678036147258048246");
let win;
let full;

for (let i = 0; i < boxes.length; i++) {
    boxes[i].onclick = function () {
        if (isStarted && isEmptyCell(this)) {
            this.innerHTML = player;

            checkWinOrFull(player, "YOU WIN!");

            if (!win && !full) {
                getPcMove(i);
            }
        }
    }
}

/**
 * Появляется диалоговое окно.
 * При этом isStarted выставляется false, чтобы не дать пользователю продолжить игру при отрицательном ответе.
 */
function question(text) {
    isStarted = false;
    return confirm(text);
}

/**
 * Проверяется символ на предмет того, выиграл или является последним символом на поле.
 */
function checkWinOrFull(symbol, message) {
    win = isWinner(symbol);
    full = isFull();
    if (win && question(message + "\nStart a new game?")) { //Если символ является одновременно и выигрышным и последним, то
        playAgain();                                        //сначала появляется сообщение о выигрыше, а, если ответить нет,
    } else if (full && question("The board is filled...\nStart a new game?")) { //то появится сообщение, что поле заполнено.
        playAgain();
    }
}

/**
 * Если текущая ячейка не пуста, то возвращает false с предупреждающим сообщением.
 */
function isEmptyCell(obj) {
    let rsl = true;
    if (obj.innerHTML !== "") {
        rsl = false;
        alert('The cell is occupied!');
    }
    return rsl;
}

/**
 * POST-запрос на RestartServlet для обновления страницы и очищения поля с ячейками.
 * Задержка на 1 сек.
 */
function playAgain() {
    setTimeout(function () {
        $.post( "./restart", function() {
            window.location.reload();
        })
    }, 1000)
}

/**
 * Перебирается строка, полученная от сервера (012 345 678 036 147 258 048 246).
 * Итерация через каждые 3 элемента.
 */
function isWinner(symbol) {
    let rsl = false;
    for (let i = 0; i < winners.length; i += 3) {
        if (isValid(boxes[winners[i]], boxes[winners[i + 1]], boxes[winners[i + 2]], symbol)) {
            colorWin(boxes[winners[i]], boxes[winners[i + 1]], boxes[winners[i + 2]]);
            rsl = true;
        }
    }
    return rsl;
}

/**
 * Проверяется, что 3 ячейки содержат один и тот же символ.
 */
function isValid(obj1, obj2, obj3, symbol) {
    let rsl = false;
    if (obj1.innerHTML === symbol && obj2.innerHTML === symbol && obj3.innerHTML === symbol) {
        rsl = true;
    }
    return rsl;
}

/**
 * Выделяются цветом 3 ячейки.
 */
function colorWin(obj1, obj2, obj3) {
    obj1.style.background = '#91ff4f';
    obj2.style.background = '#91ff4f';
    obj3.style.background = '#91ff4f';
}

/**
 * Если на поле все ячейки заняты, то вернуть true. А иначе - false.
 */
function isFull() {
    let j = -1;
    for (let i = 0; i < boxes.length; i++) {
        if (boxes[i].innerHTML !== "") {
            j++;
        }
    }
    return j === 8;
}