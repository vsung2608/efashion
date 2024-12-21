$(document).ready(function() {
    function formatPrice(number) {
        return number.toLocaleString('de-DE');
    }

    let total = 0;

    $('.price-pro').each(function() {
        let price = parseFloat($(this).val()) || 0;
        total += price;
    });

    $('#total-price').text(formatPrice(total) + ' VNĐ');

    var shippingFee = parseFloat($('#shipping-input').val()) || 0;

    var grandTotal = total + shippingFee;

    $('.total-amount').text(formatPrice(grandTotal) + ' VNĐ');

    const disPrices = document.querySelectorAll(".dis-price");
    const prices = document.querySelectorAll(".price");

    disPrices.forEach((disPrice, index) => {
        const value = disPrice.value;
        const formattedPrice = formatPrice(value);
        if (prices[index]) {
            prices[index].textContent = formattedPrice + ' ₫';
        }
    });

    $('#payCart').click(function () {
        Swal.fire({
            title: "Bạn có chắc muốn thanh toán toàn bộ sản phẩm trong giỏ?",
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: "Thanh toán",
            denyButtonText: `Hủy bỏ`
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '/efashion/web/carts',
                    type: 'POST',
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (response) {
                        Swal.fire({
                            position: "top-end",
                            icon: "success",
                            title: response.message,
                            showConfirmButton: false,
                            timer: 1500
                        });
                        setTimeout(() => {
                            window.location.href = "/efashion/web/carts"
                        }, 1500);
                    },
                    error: function (xhr) {
                    }
                })
            } else if (result.isDenied) {
                Swal.fire("Đã hủy", "", "info");
            }
        });
    })

    $('#deleteAllBtn').click(function () {
        Swal.fire({
            title: "Bạn có chắc muốn xóa toàn bộ sản phẩm trong giỏ?",
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: "Xóa ",
            denyButtonText: `Hủy bỏ`
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '/efashion/web/carts',
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (response) {
                        Swal.fire({
                            position: "top-end",
                            icon: "success",
                            title: response.message,
                            showConfirmButton: false,
                            timer: 1500
                        });
                        setTimeout(() => {
                            window.location.href = "/efashion/web/carts"
                        }, 1500);

                    },
                    error: function (xhr) {
                    }
                })
            } else if (result.isDenied) {
                Swal.fire("Đã hủy", "", "info");
            }
        });
    })

    $('#deleteBtn').click(function () {
        Swal.fire({
            title: "Bạn có chắc muốn xóa toàn bộ sản phẩm trong giỏ?",
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: "Xóa ",
            denyButtonText: `Hủy bỏ`
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '/efashion/web/carts/id=',
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (response) {
                        Swal.fire({
                            position: "top-end",
                            icon: "success",
                            title: response.message,
                            showConfirmButton: false,
                            timer: 1500
                        });
                        setTimeout(() => {
                            window.location.href = "/efashion/web/carts"
                        }, 1500);

                    },
                    error: function (xhr) {
                    }
                })
            } else if (result.isDenied) {
                Swal.fire("Đã hủy", "", "info");
            }
        });
    })

});