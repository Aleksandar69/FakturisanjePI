$(document).ready(function(){
    $("#navigation").load("navigation.html");

    var newFakturaHtml;
    var tabelaFaktura = $("#tabelaFaktura");
    var message;
    var tip_fakture; 
    var page = 0; 
    var fakturaPagination = $("#fakture-page");
    $("#message-dialog").load("dialog/message_dialog.html", function () {
        message = $("#message");
    });
    var nmbSelect = $('#nmb-select');
    var nameSearch = $('#naziv-poslovnog-partnera');
    var numberPerPage = nmbSelect.val();
    
    var izvestaji = $("#izvestaj_preduzece");
    var buttons = {
        confirm: $("#confirm-izvestaj"),

    };
    
    
    ucitajPoslovneGodine();
    ucitajFakture();

    
    function ucitajFakture() {
        var tip = $("#tip-fakture input:checked").val();
        if(tip==1){
            $("#poslovni-partner").text("Naziv i mjesto dobavljaca");
        }else{
            $("#poslovni-partner").text("Naziv i mjesto kupca");
        }
        tabelaFaktura.empty();
        var tip_fakture = tip==1? "ulazne":"izlazne";
        var godina = $("#poslovneGodineDropdown").val();
        var naziv = nameSearch.val();
        $.ajax({
            url: `api/fakture/${tip_fakture}?godina=${godina}&page=${page}&num=${numberPerPage}&naziv=${naziv}`,
            type: 'GET',
            contentType:"application/json",
            success: function(data, textStatus, request) {
                fakturaPagination.empty();
                for(var i=0; i<request.getResponseHeader('total'); i++){
                    fakturaPagination.append(`<li class="page-item text-light ${page==i? 'active':''}">` +
                        `<${page==i? 'span':'a'} class="page-link bg-mercator text-light" page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                }
                data.forEach(function (value) {
                    var poslovnaGodina;
                    var poslovniPartner;
                    var mesto;
                    $.ajax({
                        url: 'api/poslovne_godine/'+value.poslovnaGodina,
                        type: 'GET',
                        async: false, //blocks window close
                        success: function(data) { poslovnaGodina = data.godina; }
                    });
                    $.ajax({
                        url: 'api/poslovni_partneri/'+value.poslovniPartner,
                        type: 'GET',
                        async: false, //blocks window close
                        success: function(data) { poslovniPartner = data; }
                    });
                    $.ajax({
                        url: 'api/mjesto/'+poslovniPartner.mjesto,
                        type: 'GET',
                        async: false, //blocks window close
                        success: function(data) { mesto = data; }
                    });
                    red = $("<tr></tr>");
                    red.append("<td>"+value.brFakture+"/"+poslovnaGodina+"</td>");
                    red.append("<td>"+new Date(value.datumFakture).toLocaleString()+"</td>");
                    red.append("<td>"+new Date(value.datumValute).toLocaleString()+"</td>");
                    red.append("<td>"+poslovniPartner.nazivPartnera+"</br>"+mesto.naziv+"</td>");
                    red.append("<td>"+poslovniPartner.adresa+"</td>");
                    red.append("<td>"+value.iznosZaPlacanje+"</td>");
                    
                    red.append("<td><a href='faktura.html?id="+value.id+"' class='btn btn-outline-primary'>Pregledaj</a></td>");
                    
                    red.append("<td><a href='api/fakture/"+value.id+"/report' class='btn btn-outline-light'>PDF Izvjestaj</a></td>");
               tabelaFaktura.append(red);
                });
            }
        });
    }
    
    function ucitajPoslovneGodine(){
        $.ajax({
            url: "api/poslovne_godine",
            type: 'get',
            success: function(data){
                var select = document.getElementById("godinaDropdown");
                data.forEach(function(value){
                    $("#poslovneGodineDropdown").append('<option value="' + value.id + '">' + value.godina + '</option>');
                    select.options[select.options.length] = new Option(value.godina, value.godina);
                })
            }
        })
    }
    
    $("#get_izvestaj").on("click", function (event) {
        event.preventDefault();
        izvestaji.modal("show");
        buttons.confirm.on("click", function (event) {
            event.preventDefault();
            var godina =  $("#godinaDropdown").val();
            var vrsta =  $("#vrstaDropdown").val();

            $.get( `api/preduzece/1/reports/${vrsta}?godina=${godina}`, function() {
                $("#modal-pdf").attr('src',`api/preduzece/1/reports/${vrsta}?godina=${godina}`);
                izvestaji.modal("hide");
                $('#open_pdf').modal('show');
            })
            .fail(function() {
                izvestaji.modal("hide");
                message.find("div.modal-body").text("Izvestaj po datom kriterijumu ne postoji, ili ga je nemoguce generisati");
                message.modal("show");
            });
        });
    });
    
    $("#poslovneGodineDropdown").on('change', function (e) {
        ucitajFakture();
    });
    
    $("#tip-fakture input").on("change", function() {
        ucitajFakture();
    });
    
    nameSearch.on('keyup', function (event) {
        event.preventDefault();
        page = 0;
        ucitajFakture();
    })

    nmbSelect.on('change',function (event) {
        event.preventDefault();
        numberPerPage = $(this).val();
        ucitajFakture();
    });

    fakturaPagination.on("click","a.page-link", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        ucitajFakture();
    });
    
});