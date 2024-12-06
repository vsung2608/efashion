$(function (){
    $('#updateProfileBtn').click(function (e){
        e.preventDefault()
        var data = {}
        var formData = $('#form-updateProfile').serializeArray()
        formData.forEach(item => data[item.name] = item.value)
        $.ajax({
            url: '/efashion/admin/update-profile',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (response) {
                Swal.fire({
                    position: "top-end", icon: "success", title: response.message, showConfirmButton: false, timer: 1500
                });
            },
            error: function (xhr){
                const errorResponse = xhr.responseJSON;
                if (errorResponse && errorResponse.message) {
                    Swal.fire({
                        position: "top-end", icon: "error", title: errorResponse.message, showConfirmButton: false, timer: 1500
                    });
                } else {
                    console.error("Lỗi không xác định.");
                }
            }
        })
    })

    $('#changePasswordBtn').click(function () {
        e.preventDefault()
        var data = {}
        var formData = $('#form-changePassword').serializeArray()
        formData.forEach(item => data[item.name] = item.value)
        $.ajax({
            url: '/efashion/admin/change-password',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (response) {
                Swal.fire({
                    position: "top-end", icon: "success", title: response.message, showConfirmButton: false, timer: 1500
                });
            },
            error: function (xhr){
                const errorResponse = xhr.responseJSON;
                if (errorResponse && errorResponse.message) {
                    Swal.fire({
                        position: "top-end", icon: "error", title: errorResponse.message, showConfirmButton: false, timer: 1500
                    });
                } else {
                    console.error("Lỗi không xác định.");
                }
            }
        })
    })
})