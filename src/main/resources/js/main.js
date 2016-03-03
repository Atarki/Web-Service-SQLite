$(document).ready(function () {

    $('#${user_index+1}').click(function () {
        var trID = $('#${user_index+1}').valueOf();
        var user_id = '${user.id}';
        if ($('${user.id}').length !== 0) {
            user_id = '${user.name}';
        }
        $.ajax({
            type: "DELETE",
            url: '/delete',
            headers: {'id': user_id, name: '${user.name}'},
            success: function (res, status, xhr) {
                $(trID).remove();
                alert(xhr.getResponseHeader("info"));
            }
        });
    });

});
