(function () {
    "use strict"
    let buttonRegister = $('#button_register');

    buttonRegister.on("click", function (){
        onRegister()
    });

    function onRegister() {
        let formData = objectifyForm($('#registerForm').serializeArray());

        $.ajax({
            type: "POST",
            data_type: "json",
            url: '/account/register',
            contextType: "application/json; charset=UTF-8",
            data: {
                memberName:formData["memberName"],
                memberEmail:formData["memberEmail"],
                memberPassword:formData["memberPassword"]
            },
            success: function (response) {
                if(response != ""){
                    alert("회원가입이 완료되었습니다. 로그인페이지로 이동합니다.");
                    location.href = '/account/login';
                }
            }
            ,error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }

    function objectifyForm(formArray) {
        let returnArray = {};
        for (let i = 0; i < formArray.length; i++){
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }
})();
