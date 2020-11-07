$(document).ready(function() {
    $("#navigation").load("navigation.html");
    
    var tabelaPartnera = $("#tabelaPartnera");
    var deleteContent = $('#delete_content');
    var deletePartner = $('#delete_partner');
    var mestoDropdown = $("#mestoDropdown");
    var updatePartnerModal = $("#update_partner");
    var poslovniPartner = {};
    var message;
    $("#message-dialog").load("dialog/message_dialog.html", function () {
        message = $("#message");
    });
    var partnerId;

    var page = 0; var partneriPagintaion = $('#partneri-page');
    var searchFilter = $("#filter");
    var nmbSelect = $('#nmb-select');
    var numberPerPage = nmbSelect.val();
    
    ucitajPartnere();
    ucitajMjesta();
    
    function ucitajPartnere(){
        tabelaPartnera.empty();
        var tip = $("#tip-partnera input:checked").val();
        var filter = searchFilter.val();
        $.ajax({
            url: `api/poslovni_partneri?page=${page}&num=${numberPerPage}&filter=${filter}&tip=${tip}`,
            type: 'GET',
            contentType:"application/json",
            success: function(data, textStatus, request) {
                partneriPagintaion.empty();
                for(var i=0; i<request.getResponseHeader('total'); i++){
                    partneriPagintaion.append(`<li class="page-item ${page==i? 'active':''}">` +
                        `<${page==i? 'span':'a'} class="page-link " page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                }
                data.forEach(function (value) {
                    var mesto;
                    $.ajax({
                        url: 'api/mjesto/'+value.mjesto,
                        type: 'GET',
                        async: false,
                        success: function (data) {
                            mesto = data

                        }
                    });
                    red = $("<tr></tr>");
                    red.append("<td>"+value.nazivPartnera + "</td>");
                    red.append("<td>"+value.adresa +" - " + mesto.naziv + "</td>");
                    red.append("<td>"+value.tekuciRacun + "</td>");
                    red.append("<td>"+value.pib + "</td>");
                    red.append("<td><button partner_id='"+value.id+"' class='btn btn-outline-primary update_partner'>Izmijeni</button></td>");
                    red.append("<td><button partner_id='"+value.id+"' class='btn btn-outline-danger delete_partner'>Obrisi</button></td>");
                    tabelaPartnera.append(red);
                });
            }
        })
    }
    
    function ucitajMjesta(){
        $.ajax({
            url:"api/mjesto",
            type: 'GET',
            success: function(data){
                data.forEach(function(value){
                    mestoDropdown.append('<option value="' + value.id + '">' + value.naziv + '</option>');
                    $("#mestoDropdownPartnera").append('<option value="' + value.id + '">' + value.naziv + '</option>');
                })
            }
        })
    }
    
    function clearAddModal(){
        $("#nazivPartnera").val('');
        $("#adresaPartnera").val('');
        $("#partnerTip").val('');
        $("#tekuciRacunPartnera").val('');
        $("#pibPartnera").val('');
        $("#mestoDropdownPartnera").val('');
    }
    
    $("#addPartnerConfirm").on("click", function(event){
        event.preventDefault();
        $("#add_partner").modal("hide");
        var newPartner = {};
        newPartner.nazivPartnera = $("#nazivPartnera").val();
        newPartner.adresa = $("#adresaPartnera").val();
        newPartner.vrstaPartnera = $("#partnerTip").val();
        newPartner.tekuciRacun = $("#tekuciRacunPartnera").val();
        newPartner.preduzece = 1;
        newPartner.pib = $("#pibPartnera").val();
        newPartner.mjesto = $("#mestoDropdownPartnera").val();
        clearAddModal();


        $.ajax({
            url: "api/poslovni_partneri",
            type: 'POST',
            data: JSON.stringify(newPartner),
            contentType: "application/json"
        }).done(function(){
            message.find("div.modal-body").text("Uspjesno dodavanje partnera");
            message.modal("show");
            ucitajPartnere();

        })
    });
    
    tabelaPartnera.on("click", "button.update_partner", function(event){
        event.preventDefault();
        partnerId = $(this).attr("partner_id");
        $.ajax({
            url:'api/poslovni_partneri/' + partnerId,
            type: 'GET',
            success: function(data){
                poslovniPartner.id = data.id;
                poslovniPartner.nazivPartnera = data.nazivPartnera;
                poslovniPartner.adresa = data.adresa;
                poslovniPartner.vrstaPartnera = data.vrstaPartnera;
                poslovniPartner.tekuciRacun = data.tekuciRacun;
                poslovniPartner.preduzece = data.preduzece;
                poslovniPartner.mjesto = data.mjesto;
                poslovniPartner.pib = data.pib;
                $("#naziv").val(data.nazivPartnera);
                $("#adresa").val(data.adresa);
                $("#tekuci_racun").val(data.tekuciRacun);
                $("#mestoDropdown").val(data.mjesto);
                $("#pib").val(data.pib);
                updatePartnerModal.modal("show");
            }
        })
    });
    
    $("#update_confirm").on("click", function(event){
        event.preventDefault();
        updatePartnerModal.modal("hide");
        poslovniPartner.nazivPartnera = $("#naziv").val();
        poslovniPartner.adresa = $("#adresa").val();
        poslovniPartner.tekuciRacun = $("#tekuci_racun").val();
        poslovniPartner.pib = $("#pib").val();
        poslovniPartner.mjesto = $("#mestoDropdown").val();

        $.ajax({
            url: "api/poslovni_partneri/"+ partnerId,
            type: 'PUT',
            data: JSON.stringify(poslovniPartner),
            contentType:"application/json"
        }).done(function(){
            message.find("div.modal-body").text("Uspjesno ste izmijenili poslovnog pa rtnera");
            message.modal("show");
            ucitajPartnere();
        });
    });
    
    tabelaPartnera.on("click", "button.delete_partner", function(event){
        event.preventDefault();
        partnerId = $(this).attr("partner_id");
        deleteContent.text("Da li ste sigurni da želite da obrišete partnera koji ima id="+partnerId+" ?");
        deletePartner.modal("show");

    });
    
    $("#delete_confirm").on("click", function(event){
        event.preventDefault();
        $.ajax({
            url: 'api/poslovni_partneri/'+partnerId,
            type: 'DELETE',
            contentType: "application/json"
        }).done(function(){
            deletePartner.modal("hide");
            var tip = $("#tip-partnera input:checked").val();
            page = 0;
            searchFilter.val('');
            ucitajPartnere(tip);
        })
    });
    
    $("#tip-partnera input").on("change", function(){
        page = 0;
        ucitajPartnere();
    });
    
    $("#addPartnerIzlaz").on("click", function(){
        clearAddModal();
    });
    
    searchFilter.on('keyup', function (event) {
        event.preventDefault();
        page = 0;
        ucitajPartnere();
    });
    
    nmbSelect.on('change',function (event) {
        event.preventDefault();
        numberPerPage = $(this).val();
        ucitajPartnere();
    });

    partneriPagintaion.on("click","a.page-link", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        ucitajPartnere();
    });
});