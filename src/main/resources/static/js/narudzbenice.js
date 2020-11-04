$(document).ready(function(){
	$("#navigation").load("navigation.html");
	
	var newNarudzbenicaHtml;
    var tabelaNarudzbenica = $("#tabelaNarudzbenica");
    var message;
    var page = 0; 
    var narudzbenicaPagination = $("#narudzbenica-page");

    $("#message-dialog").load("dialog/message_dialog.html", function () {
        message = $("#message");
    });

    var nmbSelect = $('#nmb-select');
    var nameSearch = $('#naziv-poslovnog-partnera');
    var numberPerPage = nmbSelect.val();
    
    $("#add_new_narudzbenica").load("dialog/add_narudzbenica.html",function () {
        newNarudzbenicaHtml = {
            poslovniPartner: $("#narudzbenica-poslovni-partner"),
            add: $("#add-narudzbenica")
        };
    
    ucitajNarudzbenice();
    ucitajPoslovneGodine();
    ucitajPartnere();
    
    function ucitajPartnere(){
        $.get("api/poslovni_partneri", function(data) {
            newNarudzbenicaHtml.poslovniPartner.empty();
            var optKupci = $('<optgroup label="Kupci"></optgroup>');
            var optProdavci = $('<optgroup label="Prodavci"></optgroup>');
            data.forEach(function (value) {
                if(value.vrstaPartnera===1){
//                    newFakturaHtml.poslovniPartner.append("<option value='"+value.id+"'>"+value.nazivPartnera+"</option>");
                    optProdavci.append("<option value='"+value.id+"'>"+value.nazivPartnera+"</option>");
                }else{
                    optKupci.append("<option value='"+value.id+"'>"+value.nazivPartnera+"</option>");
                }
            });
            newNarudzbenicaHtml.poslovniPartner.append(optKupci);
            newNarudzbenicaHtml.poslovniPartner.append(optProdavci);
        });
    }
    
    
    function ucitajPoslovneGodine(){
        $.ajax({
            url: "api/poslovne_godine",
            type: 'get',
            success: function(data){
                //var select = document.getElementById("godinaDropdown");
                data.forEach(function(value){
                    $("#poslovneGodineDropdown").append('<option value="' + value.id + '">' + value.godina + '</option>');
                   // select.options[select.options.length] = new Option(value.godina, value.godina);
                })
            }
        })
    }
    
	    newNarudzbenicaHtml.add.on("click", function (event) {
	        event.preventDefault();
	        var novaNarudzbeinca = {
	            preduzece: 1,
	            poslovniPartner: newNarudzbenicaHtml.poslovniPartner.val()
	        };
	        $.ajax({
	            url: 'api/narudzbenice',
	            type: 'POST',
	            data: JSON.stringify(novaNarudzbeinca),
	            contentType:"application/json",
	            success: function(data) {
	                location.replace("narudzbenica.html?id="+data.id);
	            },
	            error: function (xhr, ajaxOptions, thrownError) {
	                console.log(novaNarudzbeinca.poslovniPartner);
	                console.log(novaNarudzbeinca.poslovnaGodina);
	              }
	        });
	    });
	});

    nmbSelect.on('change',function (event) {
        event.preventDefault();
        numberPerPage = $(this).val();
        ucitajNarudzbenice();
    });

    narudzbenicaPagination.on("click","a.page-link", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        ucitajNarudzbenice();
    });
    
    $("#poslovneGodineDropdown").on('change', function (e) {
        ucitajNarudzbenice();
    });
    
    nameSearch.on('keyup', function (event) {
        event.preventDefault();
        page = 0;
        ucitajNarudzbenice();
    });
    
    tabelaNarudzbenica.on("click", "button.napravi_otpremnicu",function (event) {
        event.preventDefault();
        var narudzbenica = $(this).attr("narudzbenica_otpremnica_id");
        $.ajax({
            url: 'api/narudzbenice/'+narudzbenica+'/napraviOtpremnicu',
            type: 'POST',
            contentType:"application/json",
            success: function() {
                message.find("div.modal-body").text("Generisanje otpremnice uspelo!");
                message.modal("show");
                ucitajNarudzbenice();
            }
        });
    });
    
    tabelaNarudzbenica.on("click", "button.napravi_fakturu",function (event) {
        event.preventDefault();
        var narudzbenica = $(this).attr("narudzbenica_id");
        $.ajax({
            url: 'api/narudzbenice/'+narudzbenica+'/napraviFakturu',
            type: 'POST',
            contentType:"application/json",
            success: function() {
                message.find("div.modal-body").text("Generisanje fakture uspelo!");
                message.modal("show");
                ucitajNarudzbenice()
            }
        });
    });
    
    function ucitajNarudzbenice() {
        tabelaNarudzbenica.empty();
        
        var godina = $("#poslovneGodineDropdown").val();
        var naziv = nameSearch.val();
        
        $.ajax({
            url: `api/narudzbenice/?godina=${godina}&page=${page}&num=${numberPerPage}&naziv=${naziv}`,
            type: 'GET',
            contentType:"application/json",
            success: function(data, textStatus, request) {
                narudzbenicaPagination.empty();
                for(var i=0; i<request.getResponseHeader('total'); i++){
                    narudzbenicaPagination.append(`<li class="page-item text-light ${page==i? 'active':''}">` +
                        `<${page==i? 'span':'a'} class="page-link bg-roda text-light" page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                }
                data.forEach(function (value) {
                    
                    var poslovnaGodina;
                    var poslovniPartner;
                    var mjesto;
                    console.log(value.poslovnaGodina);
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
                        success: function(data) { mjesto = data; }
                    });
                    red = $("<tr></tr>");
                    red.append("<td>"+value.brojNarudzbenice+"/"+poslovnaGodina+"</td>");

                    red.append("<td>"+poslovniPartner.nazivPartnera+"</br>"+mjesto.naziv+"</td>");
                    red.append("<td></td>")
                    red.append("<td></td>")
                    red.append("<td class='text-right'><a href='narudzbenica.html?id="+value.id+"' class='btn btn-outline-primary'>Pregledaj</a></td>");

                    if (poslovniPartner.vrstaPartnera == 0 && value.tipNarudzbenice==true) {
                      red.append("<td class='center-left'><button narudzbenica_otpremnica_id='" + value.id + "' class='btn btn-outline-dark napravi_otpremnicu'>Napravi otpremnicu</a></td>");
                    
                    } else if(value.obrisano!=true) {
                      red.append("<td class='center-left'><button narudzbenica_id='" + value.id + "' class='btn btn-outline-dark napravi_fakturu'>Napravi fakturu</a></td>");
                    
                    }


                    tabelaNarudzbenica.append(red);
                });
            }
        });
    }

    
});