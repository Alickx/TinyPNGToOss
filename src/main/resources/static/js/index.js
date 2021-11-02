$("#file-1").fileinput({
    language: 'zh',
    theme: 'fas',
    uploadAsync: false,
    uploadUrl: '/image/uploadImage', // you must set a valid URL here else you will get an error
    allowedFileExtensions: ['jpg', 'png'],
    overwriteInitial: false,
    maxFileSize: 5000,
    maxFilesCount: 20,
});


$(document).on('filebatchuploadsuccess', '#file-1', (event, data, previewId, index) => {
    let response = data.response
    if (response.message === "SUCCESS") {
        for (let i = 0; i < response.data.length; i++) {
            $('#url-list').append('<li>' + response.data[i] + '</li>')
        }
    } else if (response.code === 401) {
        window.location.replace("/user/login");
    } else {
        $('#fail-msg').html(response.message);
    }
})


const logout = () => {
    $.ajax({
        url: '/user/logout',
        type: 'get',
        async: false,
        success: () => {
            window.location.replace("/index");
        }
    })
}
