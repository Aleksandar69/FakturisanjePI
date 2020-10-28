$(document).ready(function(){
$("#navigation").load("navigation.html");


var preduzece ={};
var tabelaCjenovnika = $("#tblCenovniciPreduzeca");
var mjesto;

ucitajPreduzece();
ucitajMjesta();
ucitajCjenovnike();


c={};
var deleteCjenovnik = $("#delete_cenovnik");
var deleteContent = $("#delete_content");

var addCenovnik = $("#add_cenovnik");
buttons = {
        add: $("#buttonAdd_cenovnik"),
        edit: $("#edit_cenovnik"),
};

$("#message-dialog").load("dialog/message_dialog.html", function () {
    message = $("#message");
});

function ucitajPreduzece(){
    $.ajax({url: 'api/preduzece/1',
        dataType: "json",
        contentType: "application/json",
        success: function(data) {

            $.ajax({
                url: 'api/mjesto/'+data.mjesto,
                type: 'GET',
                async: false,
                success: function (data) { mjesto = data}
            });

            preduzece = data;
            

            $("#nazivPreduzeca").text(data.naziv);
            $("#adresaPreduzeca").text(data.adresaPreduzeca +" - " + mjesto.naziv);
            $("#telefonPreduzeca").text(data.telefon);
            $("#tekuciRacunPreduzeca").text(data.tekuciRacun.slice(0, 3) + "-" + data.tekuciRacun.slice(3, 15) + "-" + data.tekuciRacun.slice(15));
            $("#emailPreduzeca").text(data.email);
            $("#pib").text(data.pib);
            $("#logoPreduzeca").attr("src", data.logo);
        }
    });
}


function ucitajCjenovnike(){
    tabelaCjenovnika.empty();
    $.ajax({
        url:"api/preduzece/1/cjenovnici",
        type: 'GET',
        success: function(data) {
            data.forEach(function(value) {
                red = $("<tr></tr>");
                red.append("<td>"+new Date(value.datumVazenja).toLocaleString()+"</td>");
                red.append("<td></td>");
                red.append("<td class='text-right'><a href='cjenovnik.html?id="+value.id+"' class='btn  btn-outline-primary'>Pristupi</a></td>");
                red.append("<td style='width: 25px;'><button cjenovnik_id='"+value.id+"' class='btn btn-outline-danger delete_cenovnik ' >Obrisi</button></td>");
                red.append("<td><button cjenovnik_id='"+value.id+"' class=' btn btn-outline-success update_cenovnik '>Izmijeni</button></td>");
                tabelaCjenovnika.append(red);
            })
        }
    })
}

function ucitajMjesta(){
    $.ajax({
        url:"api/mjesto",
        type: 'GET',
        success: function(data){
            data.forEach(function(value){
                $("#mestoDropdown").append('<option value="' + value.id + '">' + value.naziv + '</option>');
            })
        }
    })
}

$("#updatePreduzece").on("click", function(){
    event.preventDefault();
     $("#naziv").val(preduzece.naziv);
     $("#adresa").val(preduzece.adresaPreduzeca);
     $("#telefon").val(preduzece.telefon);
     $("#email").val(preduzece.email);
     $("#tekuci_racun").val(preduzece.tekuciRacun);
     $("#pibUpdate").val(preduzece.pib);
     $("#logo").val(preduzece.logo);
     $("#mestoDropdown").val(preduzece.mesto);
     $("#update_preduzece_modal").modal("show");
 });

$("#update_confirm").on("click", function (event){
    event.preventDefault();
    $("#update_preduzece_modal").modal("hide");
    preduzece.naziv = $("#naziv").val();
    preduzece.adresaPreduzeca = $("#adresa").val();
    preduzece.telefon = $("#telefon").val();
    preduzece.email = $("#email").val();
    preduzece.tekuciRacun = $("#tekuci_racun").val();
    preduzece.pib = $("#pibUpdate").val();
    preduzece.logo = $("#logo").val();
    preduzece.mesto = $("#mestoDropdown").val();

    $.ajax({
        url: "/api/preduzece/1",
        type: 'PUT',
        data: JSON.stringify(preduzece),
        contentType: "application/json"
    }).done(function(){
        message.find("div.modal-body").text("Izmjene uspjesno obavljene");
        message.modal("show");
        ucitajPreduzece();
    });
});

$("#add_new_cenovnik").on("click", function (event) {
    event.preventDefault();
    addCenovnik.modal("show");
    buttons.add.show();
    buttons.edit.hide();
});

buttons.add.on("click", function (event) {
    c = {};
    event.preventDefault();
    var datum =  $("#datum");
    c.datumVazenja = datum.val();

    $.ajax({
        url: 'api/cjenovnik?preduzece=true&id=1' ,
        type: 'POST',
        data: JSON.stringify(c),
        contentType:"application/json"
    }).done(function () {
        ucitajCjenovnike();
        message.modal("show");
        message.find("div.modal-body").text("Uspesno ste dodali cjenovnik!")
    });
    addCenovnik.modal("hide");
    c={};
});

tabelaCjenovnika.on("click","button.delete_cenovnik", function (event) {
    event.preventDefault();
    var cenovnikID = $(this).attr("cjenovnik_id");
    deleteContent.text("Da li ste sigurni da želite da obrišete cenovnik koji ima id="+cenovnikID+" ?");
    deleteCjenovnik.modal("show");
    $("#delete_confirm").on("click",function (event) {
        event.preventDefault();
        $.ajax({
            url: 'api/cjenovnik/'+cenovnikID,
            type: 'DELETE',
            contentType:"application/json"
        }).done(function () {
            ucitajCjenovnike();
            message.modal("show");
            message.find("div.modal-body").text("Uspesno ste obrisali cjenovnik!");
        });
        deleteCjenovnik.modal("hide");
    });
});


tabelaCjenovnika.on("click", "button.update_cenovnik", function (event) {
    event.preventDefault();
    var cjenovnikID = $(this).attr("cjenovnik_id");
    c.id = cjenovnikID;
    $.ajax({
        url: 'api/cjenovnik/'+cjenovnikID,
        type: 'GET',
        success: function(data) {
            addCenovnik.modal("show");
            buttons.edit.show();
            buttons.add.hide();
            var datum =  $("#datum");
            var date = new Date(data.datumVazenja);
            var dateStr = date.getFullYear()+"-"+(date.getMonth()<9? "0":"")+(date.getMonth()+1)+"-"+(date.getDate()<10? "0":"")+date.getDate();
            datum.val(dateStr);
        }
    });
});

buttons.edit.on("click", function (event) {
    event.preventDefault();

    var datum =  $("#datum");
    c.datumVazenja =  datum.val();
    $.ajax({
        url: 'api/cjenovnik/'+c.id,
        type: 'PUT',
        data: JSON.stringify(c),
        contentType:"application/json"
    }).done(function () {
        ucitajCjenovnike();
        message.modal("show");
        message.find("div.modal-body").text("Uspesno ste izmjenili cjenovnik!")
    });
    addCenovnik.modal("hide");
    c={};
});

});