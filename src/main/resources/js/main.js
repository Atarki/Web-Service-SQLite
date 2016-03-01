$(document).ready(function () {

    $('#${user_index+1}').click(function () {
        var trID = $('#${user_index+1}').valueOf();
        $.ajax({
            type: 'DELETE',
            url: '/delete',
            headers: {'id': '${user.id}'},
            success: function () {
                $(trID).remove();
            }
        });
    });

});
