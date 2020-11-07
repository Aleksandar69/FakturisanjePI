$(document).ready(function(){
    $("#navigation").load("navigation.html");
    
    var newOtpremnicaHtml;
    var tabelaOtpremnica = $("#tabelaOtpremnica");
    var message;
    var page = 0; 
    var otpremnicaPagination = $("#otpremnica-page");

    $("#message-dialog").load("dialog/message_dialog.html", function () {
        message = $("#message");
    });

    var nmbSelect = $('#nmb-select');
    var nameSearch = $('#naziv-poslovnog-partnera');
    var numberPerPage = nmbSelect.val();
    ucitajPoslovneGodine();
    
    ucitajOtpremnice();
    
function ucitajOtpremnice() {
        
        
        tabelaOtpremnica.empty();
        
        var godina = $("#poslovneGodineDropdown").val();
        var naziv = nameSearch.val();
        $.ajax({
            url: `api/otpremnice/?godina=${godina}&page=${page}&num=${numberPerPage}&naziv=${naziv}`,
            type: 'GET',
            contentType:"application/json",
            success: function(data, textStatus, request) {
                otpremnicaPagination.empty();
                for(var i=0; i<request.getResponseHeader('total'); i++){
                    otpremnicaPagination.append(`<li class="page-item text-light ${page==i? 'active':''}">` +
                        `<${page==i? 'span':'a'} class="page-link bg-mercator text-light" page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                }
                data.forEach(function (value) {
                    var poslovnaGodina;
                    var poslovniPartner;
                    var mjesto;
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
                    red.append("<td>"+value.brojOtpremnice+"/"+poslovnaGodina+"</td>");
        
                    red.append("<td>"+poslovniPartner.nazivPartnera+"</br>"+mjesto.naziv+"</td>");
                    red.append("<td>"+poslovniPartner.adresa+"</td>");
                  
                    red.append("<td><a href='otpremnica.html?id="+value.id+"' class='btn btn-outline-primary'>Pregledaj</a></td>");

                    if(value.obrisano!=true){
                        red.append("<td><button otpremnica_id=' " + value.id + "' class='btn btn-outline-light napravi_fakturu'>Napravi fakturu</a></td>");
                    }
                    
                    red.append("<td><button otpremnica_id='" + value.id + "' class='btn btn-outline-light pdf_izvestaj'>PDF izvjestaj</a></td>");

                    tabelaOtpremnica.append(red);
                });
            }
        });
    }

	function ucitajPoslovneGodine(){
	    $.ajax({
	        url: "api/poslovne_godine",
	        type: 'get',
	        success: function(data){
	           // var select = document.getElementById("godinaDropdown");
	            data.forEach(function(value){
	                $("#poslovneGodineDropdown").append('<option value="' + value.id + '">' + value.godina + '</option>');
	                // select.options[select.options.length] = new Option(value.godina, value.godina);
	            })
	        }
	    })
	}

    function ucitajPartnere(){
        $.get("api/poslovni_partneri", function(data) {
            newOtpremnicaHtml.poslovniPartner.empty();
            var optKupci = $('<optgroup label="Kupci"></optgroup>');
            
            data.forEach(function (value) {
                if(value.vrstaPartnera===0){
                    optKupci.append("<option value='"+value.id+"'>"+value.nazivPartnera+"</option>");
                }
            });
            newOtpremnicaHtml.poslovniPartner.append(optKupci);
            
        });

    }
    
    
    $("#add_new_otpremnica").load("dialog/add_otpremnica.html",function () {
        newOtpremnicaHtml = {
            poslovniPartner: $("#otpremnica-poslovni-partner"),
            add: $("#add-otpremnica")
        };
        ucitajPartnere();
        newOtpremnicaHtml.add.on("click", function (event) {
            event.preventDefault();
            var novaOtpremnica = {
                preduzece: 1,
                poslovniPartner: newOtpremnicaHtml.poslovniPartner.val()
            };
            $.ajax({
                url: 'api/otpremnice',
                type: 'POST',
                data: JSON.stringify(novaOtpremnica),
                contentType:"application/json",
                success: function(data) {
                    location.replace("otpremnica.html?id="+data.id);
                }
            });
        });
    });
    
    tabelaOtpremnica.on("click", "button.napravi_fakturu",function (event) {
        event.preventDefault();
        var otpremnica = $(this).attr("otpremnica_id");
        $.ajax({
            url: 'api/otpremnice/'+otpremnica+'/napraviFakturu',
            type: 'POST',
            contentType:"application/json",
            success: function() {
                message.find("div.modal-body").text("Generisanje fakture uspelo!");
                message.modal("show");
                ucitajOtpremnice();
            }
        });
    });
    
    tabelaOtpremnica.on("click","button.pdf_izvestaj", function (event) {
        event.preventDefault();
        var otpremnicaId = $(this).attr("otpremnica_id");
        $.get( `api/otpremnice/${otpremnicaId}/report`, function() {
            $("#modal-pdf").attr('src', `api/otpremnice/${otpremnicaId}/report`);
            $('#open_pdf').modal('show');
        })
        .fail(function() {
            message.find("div.modal-body").text("Izvestaj ne postoji, ili ga je trenutno nemoguce generisati");
            message.modal("show");
        });
    });

    
    $("#poslovneGodineDropdown").on('change', function (e) {
        ucitajOtpremnice();
    });

    nameSearch.on('keyup', function (event) {
        event.preventDefault();
        page = 0;
        ucitajOtpremnice();
    })
    
    nmbSelect.on('change',function (event) {
        event.preventDefault();
        numberPerPage = $(this).val();
        ucitajOtpremnice();
    });

    otpremnicaPagination.on("click","a.page-link", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        ucitajOtpremnice();
    });
});