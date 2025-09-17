document.getElementById('generateBtn').addEventListener('click', function() {
    let inputText = document.getElementById('inputText').value;
    if (inputText) {
        document.getElementById('qrcode').innerHTML = '';
        new QRCode(document.getElementById('qrcode'), {
            text: inputText,
            width: 200,
            height: 200,
            colorDark: "#000000",
            colorLight: "#ffffff",
            
        });
    }
});
