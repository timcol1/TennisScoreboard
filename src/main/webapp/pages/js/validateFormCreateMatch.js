function validateForm() {
    var input1Value = document.getElementById('Player1').value;
    var input2Value = document.getElementById('Player2').value;

    if (input1Value.trim() === '' || input1Value.length > 16) {
        alert('Name Player 1 should not be empty and should be at most 16 characters long.');
        event.preventDefault();
        return;
    }

    if (input2Value.trim() === '' || input2Value.length > 16) {
        alert('Name Player 2 should not be empty and should be at most 16 characters long.');
        event.preventDefault();
        return;
    }
}