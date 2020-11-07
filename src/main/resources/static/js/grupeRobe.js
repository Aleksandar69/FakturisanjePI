$(document).ready(function(){
	$("#navigation").load("navigation.html"); 
	
	$("#preduzece").text("Preduzeće");
	var tabela = $("#tblGrupeRobe");
    var message = $("#message");
    var deleteGrupa = $("#delete_grupa");
    var updateGrupa = $("#update_grupa");
    var deleteContent = $("#delete_content");

    var page = 0; var grupePagintaion = $('#grupe-page');
    var nameSearch = $("#naziv-grupe");
    var nmbSelect = $('#nmb-select');
    var numberPerPage = nmbSelect.val();
    
    buttons = {
            add: $("#add_grupa"),
            edit: $("#edit_grupa")
        };
    
    popunjavanjeTabele();
    
    function popunjavanjeTabele() {
   	 tabela.empty();
        var naziv = nameSearch.val();
        $.ajax({
            url: `api/grupa_robe?page=${page}&num=${numberPerPage}&naziv=${naziv}`,
            type: 'GET',
            contentType:"application/json",
            success: function(data, textStatus, request) {
                grupePagintaion.empty();
                for(var i=0; i<request.getResponseHeader('total'); i++){
                    grupePagintaion.append(`<li class="page-item ${page==i? 'active':''}">` +
                        `<${page==i? 'span':'a'} class="page-link" page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                }
                getPreduzeca();
                getPdv();
                data.forEach(function (value) {
                    var preduzece;
                    var pdv;

                    $.ajax({
                        url: 'api/preduzece/' + value.preduzece,
                        type: 'GET',
                        async: false, //blocks window close
                        success: function (data) {
                            preduzece = data;
                        }
                    });
                    $.ajax({
                        url: 'api/pdv/' + value.pdv,
                        type: 'GET',
                        async: false, //blocks window close
                        success: function (data) {
                            pdv = data;
                        }
                    });

                    red = $("<tr></tr>");
                    red.append("<td>" + value.nazivGrupe + "</br></td>");
                    red.append("<td>" + preduzece.naziv + "</br></td>");
                    red.append("<td>" + pdv.nazivPdva + "</br></td>");
                    red.append("<td><a href='roba_usluga.html?grupa=" + value.id + "' class='btn btn-outline-primary'>Pregledaj</a></td>");
                    red.append("<td><button grupa_id='" + value.id + "' class='btn btn-outline-warning update_grupa'>Izmijeni</button> </td>");
                    red.append("<td> <button grupa_id='" + value.id + "' class='btn btn-outline-danger delete_grupa'>Obrisi</button></td>")
                    tabela.append(red);
                });
            }
        });
    }
    
    function getPreduzeca() {
  	  $.ajax({
           url: 'api/preduzece/',
           type: 'GET',
           success: function(data) {
        	   $("#selectPreduzece").empty();
         	  preduzeca = data;
         	  var select = document.getElementById("selectPreduzece");
       			for(var i = 0; i  < preduzeca.length; i++) {
       				select.options[select.options.length] = new Option(preduzeca[i].naziv, preduzeca[i].id);
       				}  
               }          									  	  
           });
       }
    
    function getPdv() {
  	  $.ajax({
         url: 'api/pdv/',
         type: 'GET',
         success: function(data) {
        	 $("#selectPDV").empty();
      	   PDV = data;
       	  var select = document.getElementById("selectPDV");
     			for(var i = 0; i  < PDV.length; i++) {
     				select.options[select.options.length] = new Option(PDV[i].nazivPdva, PDV[i].id);
     				}  
             }          									  	  
         });
     }
    
    $("#add_new_grupa").on("click", function (event) {
        event.preventDefault();
        updateGrupa.modal("show");
        buttons.add.show();
        buttons.edit.hide();
        buttons.add.on("click", function (event) {
            event.preventDefault();
        	var grupa = {};
            var preduz =  $("#selectPreduzece"); 
            var naziv =  $("#naziv"); 
            var pdv = $("#selectPDV");
            grupa.nazivGrupe = naziv.val();
            grupa.preduzece =  preduz.val();
            grupa.pdv = pdv.val();

            $.ajax({
                url: 'api/grupa_robe',
                type: 'POST',
                data: JSON.stringify(grupa),
                contentType:"application/json"
            }).done(function () {
                popunjavanjeTabele();
                message.modal("show");
                message.find("div.modal-body").text("Uspjesno ste dodali grupu robe!")
            });
            updateGrupa.modal("hide");
        });
    });
    
    tabela.on("click", "button.update_grupa", function (event) {
        event.preventDefault();
        var grupaId = $(this).attr("grupa_id");
        $.ajax({
            url: 'api/grupa_robe/'+grupaId,
            type: 'GET',
            success: function(data) {
                updateGrupa.modal("show");
                buttons.edit.show();
                buttons.add.hide();
                
                var preduz =  $("#selectPreduzece"); 
                var naziv =  $("#naziv"); 
                var pdv = $("#selectPDV");
                naziv.val(data.nazivGrupe);
                preduz.val(data.preduzece);
                pdv.val(data.pdv);
                buttons.edit.on("click", function (event) {
                	var grupa= {};
                    event.preventDefault();
                    grupa.nazivGrupe = naziv.val();
                    grupa.preduzece = preduz.val(); 
                    grupa.pdv = pdv.val();
                    grupa.id = grupaId;
                    $.ajax({
                        url: 'api/grupa_robe/'+grupaId,
                        type: 'PUT',
                        data: JSON.stringify(grupa),
                        contentType:"application/json"
                    }).done(function () {
                    	$("#tblGrupeRobe").empty();
                        popunjavanjeTabele();
                        message.modal("show");
                        message.find("div.modal-body").text("Uspjesno ste izmjenili grupu robe!")
                    });
                    updateGrupa.modal("hide");
                    grupa=null;
                });
            }
        });
    });
    
    tabela.on("click","button.delete_grupa", function (event) {
    	console.log("delete clicked");
        event.preventDefault();
        var grupaId = $(this).attr("grupa_id");
        deleteContent.text("Da li ste sigurni da želite da obrišete grupu koje ima id="+grupaId+" ?");
        deleteGrupa.modal("show");
        $("#delete_confirm").on("click",function (event) {
            event.preventDefault();
            $.ajax({
                url: 'api/grupa_robe/'+grupaId,
                type: 'DELETE',
                contentType:"application/json"
            }).done(function () {
                popunjavanjeTabele();
                message.modal("show");
                message.find("div.modal-body").text("Uspjesno ste obrisali!");
            });
            deleteGrupa.modal("hide");
        });
    });

    
    nameSearch.on('keyup', function (event) {
        event.preventDefault();
        page = 0;
        popunjavanjeTabele();
    });
    nmbSelect.on('change',function (event) {
        event.preventDefault();
        numberPerPage = $(this).val();
        popunjavanjeTabele();
    });

    grupePagintaion.on("click","a.page-link", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        popunjavanjeTabele();
    });
	    
});