/**
 * Created by pablo.ramthun on 19/05/2017.
 */

$('#exemploJson').on('shown.bs.modal', function () {
    $('#myInput').focus()
});

$('#deletarUsuario').on('shown.bs.modal', function () {
    $('#myInput').focus()
});

function data(id){
    debugger;
    $('#btnDeletarUsuario').attr('href', 'pessoa/delete/' + id);
}
