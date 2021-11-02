//刷新验证码
const uploadLoginValidateCode = () => {
    let src = "/api/getVerificationCode?" + new Date().getTime(); //加时间戳，防止浏览器利用缓存
    $('#getVerificationCode').attr("src", src);
}


$(document).on('focus','#accountName',()=>{
    $('#accountName').removeClass('is-invalid');
    $('#accountPasswordValidation').removeClass('invalid-feedback');
    $('#accountPasswordValidation').html("");
})

$(document).on('focus','#accountPassword',()=>{
    $('#accountPassword').removeClass('is-invalid');
    $('#accountPasswordValidation').removeClass('invalid-feedback');
    $('#accountPasswordValidation').html("");
})

$(document).on('focus','#accountPasswordAgain',()=>{
    $('#accountPasswordAgain').removeClass('is-invalid');
    $('#accountPasswordAgainValidation').removeClass('invalid-feedback');
    $('#accountPasswordAgainValidation').html("");
})


$(document).on('focus','#verificationCode',()=>{
    $('#verificationCode').removeClass('is-invalid');
    $('#verificationValidation').removeClass('invalid-feedback');
    $('#verificationValidation').html("");
})


$(document).on('click', '#RegisterButton', () => {

    let valid1, valid2, valid3,valid4 = false;
    let accountName = $('#accountName');
    let accountNameValidation = $('#accountNameValidation');
    let accountPassword = $('#accountPassword');
    let accountPasswordValidation = $('#accountPasswordValidation');
    let accountPasswordAgain = $('#accountPasswordAgain');
    let accountPasswordAgainValidation = $('#accountPasswordAgainValidation');
    let verificationCode = $('#verificationCode');
    let verificationValidation = $('#verificationValidation');
    let accountNameReg = /^[A-Za-z0-9]{5,15}$/g
    let accountPasswordValidationReg = /^[a-zA-Z]\w{5,17}$/g

    let accountNameVal = accountName.val();
    if (accountNameVal === null || accountNameVal === '') {
        accountName.addClass('is-invalid');
        accountNameValidation.addClass('invalid-feedback');
        accountNameValidation.html("用户名不能为空")
    } else if (accountNameReg.test(accountNameVal) === false) {
        accountName.addClass('is-invalid');
        accountNameValidation.addClass('invalid-feedback');
        accountNameValidation.html("用户名应为英文数字6-14位")
    } else {
        valid1 = true;
    }


    let accountPasswordVal = accountPassword.val();
    if (accountPasswordVal === null || accountPasswordVal === '') {
        accountPassword.addClass('is-invalid');
        accountPasswordValidation.addClass('invalid-feedback');
        accountPasswordValidation.html("密码不能为空")
    } else if (accountPasswordValidationReg.test(accountPasswordVal) === false) {
        accountPassword.addClass('is-invalid');
        accountPasswordValidation.addClass('invalid-feedback');
        accountPasswordValidation.html("以字母开头，长度在6~18之间")
    } else {
        valid2 = true;
    }

    let accountPasswordAgainVal = accountPasswordAgain.val();
    if (accountPasswordAgainVal === null || accountPasswordAgainVal === '') {
        accountPasswordAgain.addClass('is-invalid');
        accountPasswordAgainValidation.addClass('invalid-feedback');
        accountPasswordAgainValidation.html("密码不能为空")
    } else if (accountPasswordVal !== accountPasswordAgainVal) {
        accountPasswordAgain.addClass('is-invalid');
        accountPasswordAgainValidation.addClass('invalid-feedback');
        accountPasswordAgainValidation.html("用户9999999999999999999999999999999999999999999999999999999999999999二次密码不相同！")
    } else {
        valid3 = true;
    }



    let verificationCodeVal = verificationCode.val();
    if (verificationCodeVal === null || verificationCodeVal === '') {
        verificationCode.addClass('is-invalid');
        verificationValidation.addClass('invalid-feedback');
        verificationValidation.html("验证码不能为空")
    } else {
        valid4 = true
    }


    const data = {
        'accountName':accountNameVal,
        'accountPassword':accountPasswordVal,
        'accountPasswordAgain': accountPasswordAgainVal,
        'verificationCode': verificationCodeVal
    }

    if (valid1 && valid2 && valid3 && valid4) {
        $.ajax({
            type: "POST",
            url: "/user/register",
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            async: false,
            success: (data) => {
                if (data.message === "SUCCESS") {
                    window.location.replace("/user/login");
                } else {
                    $('#errorMsg').html(data.message);
                    uploadLoginValidateCode();
                }
            }
        });
    } else {
        uploadLoginValidateCode();
    }
})




