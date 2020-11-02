$(document).ready(function(){
    $("#navigation").load("navigation.html");
    var message;
    $("#message-dialog").load("dialog/message_dialog.html", function () {
        message = $("#message");
    });
    
    var tabela = $("#tabela");
    var deleteContent = $("#delete_content");
    var deleteStopa = $("#delete_stopa");
    var updateStopa = $("#update_stopa");
    var buttons = {
        addEdit: $("#add_edit_stopa")
    };
    var stopa = {
        id: 0,
        procenat:0,
        datumVazenja: new Date(),
        pdv: 1
    };
    var stopaHtml = {
        procenat: $("#procenat"),
        datumVazenja: $("#datum-vazenja"),
        pdv: $("#add_edit_pdv")
    };
   
    ucitajStope();
    appendPDV();
    
    function ucitajStope() {
        tabela.empty();
        $.get( "api/stopa_pdv", function( data ) {
            data.forEach(function (value) {
                red = $("<tr></tr>");
                red.append("<td>"+value.procenat+"%"+"</td>");
                red.append("<td>"+new Date(value.datumVazenja).toLocaleString()+"</td>");
                $.ajax({url: "api/pdv/"+value.pdv,
                    dataType: "json",
                    contentType: "application/json",
                    async:false,
                    success: function(data){
                        red.append("<td>"+data.nazivPdva+"</td>");
                    }});
                red.append("<td class='text-right'>" +
                    "<button stopa_id='"+value.id+"' class='btn btn-outline-primary update_stopa mr-2'>Izmijeni</button>"+
                    "<button stopa_id='"+value.id+"' class='btn btn-outline-danger delete_stopa'>Obrisi</button></td>");
                tabela.append(red);
            });
        });
    }
    
    function appendPDV() {
        $.get( "api/pdv", function( data ) {
            data.forEach(function (value) {
                stopaHtml.pdv.append("<option value='"+value.id+"'>"+value.nazivPdva+"</option>");
            });
        });
    }
    
    $("#add_new_stopa").on("click", function (event) {
        event.preventDefault();
        updateStopa.modal("show");
        stopa.id = 0;
    });
    
    buttons.addEdit.on("click", function (event) {
        event.preventDefault();
        stopa.procenat = stopaHtml.procenat.val();
        stopa.datumVazenja = stopaHtml.datumVazenja.val();
        stopa.pdv = stopaHtml.pdv.val();
        var url = "api/stopa_pdv";
        var method = "POST";
        if(stopa.id!=0){
            url += "/"+stopa.id;
            method = "PUT";
        }
        $.ajax({
            url: url,
            type: method,
            data: JSON.stringify(stopa),
            contentType:"application/json"
        }).done(function () {
            ucitajStope();
            message.modal("show");
            if(stopa.id==0){
                message.find("div.modal-body").text("Uspjesno ste dodali stopu pdv-a!");
            }else{
                message.find("div.modal-body").text("Uspjesno ste izmijenili stop pdv-a!")
            }
            stopa.id = 0;
        });
        updateStopa.modal("hide");
    });
    
    tabela.on("click", "button.update_stopa", function (event) {
        event.preventDefault();
        stopa.id = $(this).attr("stopa_id");
        $.ajax({
            url: 'api/stopa_pdv/'+stopa.id,
            type: 'GET',
            success: function(data) {
                updateStopa.modal("show");
                stopaHtml.procenat.val(data.procenat);
                var date = new Date(data.datumVazenja);
                var dateStr = date.getFullYear()+"-"+(date.getMonth()<9? "0":"")+(date.getMonth()+1)+"-"+(date.getDate()<10? "0":"")+date.getDate();
                stopaHtml.datumVazenja.val(dateStr);
                stopaHtml.pdv.val(data.pdv);
            }
        });
    });
    
    tabela.on("click", "button.delete_stopa",function (event) {
        event.preventDefault();
        stopa.id = $(this).attr("stopa_id");
        deleteContent.text("Da li ste sigurni da želite da obrišete stopu koja ima id="+stopa.id+" ?");
        deleteStopa.modal("show");
    });
    
    $("#delete_confirm").on("click",function (event) {
        event.preventDefault();
        $.ajax({
            url: 'api/stopa_pdv/'+stopa.id,
            type: 'DELETE',
            contentType:"application/json"
        }).done(function () {
            ucitajStope();
            message.modal("show");
            message.find("div.modal-body").text("Uspjesno ste obrisali stopu pdv-a!")
            stopa.id = 0;
        });
        deleteStopa.modal("hide");
    });
    
});