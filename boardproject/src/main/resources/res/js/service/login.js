(function () {
    "use strict"
    let buttonJoin = $('#button_join');
    let buttonLogin = $('#button_login');

    buttonJoin.on("click", function (){
        onJoin()
    });
    buttonLogin.on("click", function (){
        onLogin()
    });

    function onLogin() {
        let formData = objectifyForm($('#loginForm').serializeArray());
        $.ajax({
            type: "POST",
            data_type: "json",
            url: '/account/login',
            contextType: "application/json; charset=utf-8",
            data: {
                memberEmail: formData["memberEmail"],
                memberPassword: formData["memberPassword"],
                useCookie:$("input:checkbox[id='useCookie']").is(":checked")
            },
            success: function (response) {
                if(response != ""){
                    location.href = '/';
                }
                else{
                    $("#message").html("<p style='color:red'>아이디 또는 비밀번호가 잘못되었습니다.</p>");
                }
            }
            ,error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }

    function onJoin() {
        location.href = "/account/register";
    }

    function objectifyForm(formArray) {
        let returnArray = {};
        for (let i = 0; i < formArray.length; i++){
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }
})();

// function secureObjectifyForm(formArray) {
//     let returnArray = {};
//     for (let i = 0; i < formArray.length; i++){
//         returnArray[formArray[i]['name']] = sha256(formArray[i]['value']);
//     }
//     return returnArray;
// }



