/**
 * 获取用户的oss信息
 */
$(document).ready(()=>{
    $.ajax({
        type: "POST",
        url: "/user/info",
        contentType: 'application/json',
        async: false,
        success: (data) => {
            if (data.message === "SUCCESS") {
                console.log(data)
                $('#accessId').attr('value',data.data.accessId)
                $('#accessKey').attr('value',data.data.accessKey);
                $('#endpoint').attr('value',data.data.endpoint);
                $('#bucket').attr('value',data.data.bucket);
                $('#dir').attr('value',data.data.dir);
            }
        }
    });
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


$(document).on('focus','#accountPassword',()=>{
    $('#accountPassword').removeClass('is-invalid');
    $('#accountPasswordValid').removeClass('invalid-feedback');
    $('#accountPasswordValid').html("");
})

$(document).on('focus','#newAccountPassword',()=>{
    $('#newAccountPassword').removeClass('is-invalid');
    $('#newAccountPasswordValid').removeClass('invalid-feedback');
    $('#newAccountPasswordValid').html("");
})


$(document).on('focus','#newAccountPasswordAgain',()=>{
    $('#newAccountPasswordAgain').removeClass('is-invalid');
    $('#newAccountPasswordAgainValid').removeClass('invalid-feedback');
    $('#newAccountPasswordAgainValid').html("");
})


/**
 * 更新oss信息
 */
$(document).on('click', '#oss-upload-button', () => {


    let accessId = $('#accessId').val();
    let accessKey = $('#accessKey').val();
    let endpoint = $('#endpoint').val();
    let bucket = $('#bucket').val();
    let dir = $('#dir').val();

    const data = {
        accessId,
        accessKey,
        endpoint,
        bucket,
        dir
    }

    $.ajax({
        type: "POST",
        url: "/user/ossUpdate",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: (data) => {
            if (data.message === "SUCCESS") {
                window.location.reload();
            }
        }
    });

})

/**
 * 修改密码
 */

$(document).on('click','#modify-password-button',()=>{

    let accountPassword = $('#accountPassword');
    let newAccountPassword = $('#newAccountPassword');
    let newAccountPasswordAgain = $('#newAccountPasswordAgain');
    let accountPasswordValidationReg = /^[a-zA-Z]\w{5,17}$/g


    if (accountPassword.val()===null) {
        accountPassword.addClass('is-invalid');
        $('#accountPasswordValid').addClass('invalid-feedback');
        $('#accountPasswordValid').html('密码不能为空!')
        return;
    }

    if (newAccountPassword.val()===null) {
        newAccountPassword.addClass('is-invalid');
        $('#newAccountPasswordValid').addClass('invalid-feedback');
        $('#newAccountPasswordValid').html('新密码不能为空!')
        return;
    }

    if (newAccountPasswordAgain.val()===null) {
        newAccountPasswordAgain.addClass('is-invalid');
        $('#newAccountPasswordAgainValid').addClass('invalid-feedback');
        $('#newAccountPasswordAgainValid').html('二次密码不能为空!')
        return;
    }

    if (newAccountPassword.val()!==newAccountPasswordAgain.val()) {
        newAccountPasswordAgain.addClass('is-invalid');
        $('#newAccountPasswordAgainValid').addClass('invalid-feedback');
        $('#newAccountPasswordAgainValid').html('二次密码不正确!')
        return;
    }

    if (accountPasswordValidationReg.test(newAccountPassword.val())===false) {
        newAccountPassword.addClass('is-invalid');
        $('#newAccountPasswordValid').addClass('invalid-feedback');
        $('#newAccountPasswordValid').html('密码应以字母开头，长度在6~18之间');
        return;
    }

    const data = {
        "accountPassword": accountPassword.val(),
        "newAccountPassword": newAccountPassword.val(),
        "newAccountPasswordAgain": newAccountPasswordAgain.val()
    }


    $.ajax({
        type: "POST",
        url: "/user/passwordUpdate",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(data),
        async: false,
        success: (data) => {
            if (data.message === "SUCCESS") {
                window.location.replace('/index')
            } else {
                accountPassword.addClass('is-invalid');
                $('#accountPasswordValid').addClass('invalid-feedback');
                $('#accountPasswordValid').html('用户密码错误!')
            }
        }
    });
})