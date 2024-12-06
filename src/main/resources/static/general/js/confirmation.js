$(document).ready(function () {
    $('.code-input').on('input', function () {
        const currentInput = $(this);

        const value = currentInput.val().replace(/[^0-9]/g, '');
        currentInput.val(value);

        if (value.length === 1) {
            const nextInput = currentInput.next('.code-input');
            if (nextInput.length > 0) {
                nextInput.focus();
            }
        }
    });

    $('#confirmForm').on('submit', function (event) {
        event.preventDefault();

        const code = $('.code-input')
            .map(function () {
                return $(this).val();
            })
            .get()
            .join('');

        if (code.length === 6) {
            $.ajax({
                url: '/efashion/auth/activate',
                method: 'POST',
                contentType: 'text/plain',
                data: code,
                success: function () {
                    swal("Chúc mừng", "Xác thực tài khoản thành công!", "success").then(function () {
                        window.location.href = "/efashion/general/login";
                    });
                }
                ,
                error: function (){
                    swal("Oh không!", "Xác thực tài khoản thất bại!", "error");
                }
            })
        } else {
            alert('Vui lòng nhập đầy đủ mã xác nhận.');
        }
    });
});
