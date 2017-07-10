


function readMsg(user) {
    // alert('uesr = ' + user);

    $.getJSON("setReaded.do", {name:user},
        function (data, textStatus, jqXHR) {
            // alert('data=' + data);
            if(data == "Readed"){
                $('#msgCount').css('display','none');
            }
        }
    );

    
}

