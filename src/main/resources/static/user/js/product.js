$(document).ready(function () {

    function formatPrice(value) {
        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }

    const disPrices = document.querySelectorAll(".dis-price");
    const prices = document.querySelectorAll(".price");

    disPrices.forEach((disPrice, index) => {
        const value = disPrice.value;
        const formattedPrice = formatPrice(value);
        if (prices[index]) {
            prices[index].textContent = formattedPrice + ' ₫';
        }
    });
    $('#searchBtn').click(function () {
        var keyword = $('#searchField').val();
        if (!keyword.trim()) {
            alert('Vui lòng nhập từ khóa tìm kiếm.');
            return;
        }

        window.location.href = "/efashion/web/products/" + keyword
    });

    $('.add-in-cart').on('click', function (event){
        event.preventDefault()

        var par = $(this).closest('.pro-d')
        var productId = par.data('id');

        Swal.fire({
            title: "Bạn có chắc là muốn thêm sản phẩm này vòa giỏ không?",
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: "Thêm",
            denyButtonText: `Hủy`
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '/efashion/web/carts/add-item?id=' + productId + '&quantity=1',
                    type: 'POST',
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (response) {
                        Swal.fire("Đã thêm!", "", response.message);
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
