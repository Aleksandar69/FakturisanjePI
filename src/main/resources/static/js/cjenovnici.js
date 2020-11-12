$(document).ready(function(){
	$("#navigation").load("navigation.html"); 
	$("#preduzece").text("Preduzeće");
    var tabela = $("#tblCenovnici");
    var message = $("#message");
    var deleteCenovnik = $("#delete_cenovnik");
    var updateCenovnik = $("#update_cenovnik");
    var copyCenovnik = $("#kopiraj_cenovnik_modal");
    var deleteContent = $("#delete_content");
    var page = 0; 
    var cenovnikPagintaion = $('#cenovnik-page');
    var nmbSelect = $('#nmb-select');
    var numberPerPage = nmbSelect.val();
    var buttons = {
        add: $("#add_cenovnik")
    };
    
    getPreduzeca();
    popunjavanjeTabele();
    
    function popunjavanjeTabele() {
   	 tabela.empty();
        $.ajax({
            url: `api/cjenovnik?page=${page}&num=${numberPerPage}`,
            type: 'GET',
            contentType:"application/json",
            success: function(data, textStatus, request) {
                cenovnikPagintaion.empty();
                for(var i=0; i<request.getResponseHeader('total'); i++){
                    cenovnikPagintaion.append(`<li class="page-item  ${page==i? 'active':''}">` +
                        `<${page==i? 'span':'a'} class="page-link" page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                }
                data.forEach(function (value) {
                    var partner;
                    $.ajax({
                        url: 'api/cjenovnik/'+value.id+'/poslovni_partner',
                        type: 'GET',
                        async: false, //blocks window close
                        success: function(data) {
                            partner = data;
                            red = $("<tr></tr>");
                            red.append("<td class='text-center'>"+partner.nazivPartnera+"</br></td>");
                            red.append("<td class='text-center'>"+new Date(value.datumVazenjaOd).toLocaleString()+"</td>");
                            red.append("<td class='text-center'>"+new Date(value.datumVazenjaDo).toLocaleString()+"</td>");

                            red.append("<td class='text-right'><a href='cjenovnik.html?id="+value.id+"' class='btn btn-outline-primary'>Pregledaj</a></td>");
                            red.append("<td>" +
                                "<button cenovnik_id='"+value.id+"' class='btn btn-outline-danger delete_cenovnik'>Obrisi</button></td>");
                            red.append("<td>" +
                                                "<button cenovnik_kopiraj='"+value.id+"' class='btn btn-outline-primary kopiraj_cenovnik'>Kopiraj stvake drugog cjenovnika</button></td>");
                            tabela.append(red);
                        }, error: function (error) {
                            $.get("api/preduzece/1", function (data) {
                                red = $("<tr></tr>");
                                red.append("<td class='text-center'>"+data.naziv+"</br></td>");
                                red.append("<td class='text-center'>"+new Date(value.datumVazenjaOd).toLocaleString()+"</td>");
                                red.append("<td class='text-center'>"+new Date(value.datumVazenjaDo).toLocaleString()+"</td>");
                                red.append("<td class='text-right'><a href='cjenovnik.html?id="+value.id+"' class='btn btn-outline-primary'>Pregledaj</a></td>");
                                red.append("<td>" +
                                    "<button cenovnik_id='"+value.id+"' class='btn btn-outline-danger delete_cenovnik'>Obrisi</button></td>");
                                red.append("<td>" +
                                                   "<button cenovnik_kopiraj='"+value.id+"' class='btn btn-outline-primary kopiraj_cenovnik'>Kopiraj stvake drugog cjenovnika</button></td>");
                                tabela.append(red);

                            });
                            //nothing
                        }
                    });

                });
            }
        });
    }
    
    function getPreduzeca() {
  	  $.ajax({
            url: 'api/poslovni_partneri/',
            type: 'GET',
            success: function(data) {
          	  preduzeca = data;
          	  var select = document.getElementById("selectPreduzece");
        			for(var i = 0; i  < preduzeca.length; i++) {
        				select.options[select.options.length] = new Option(preduzeca[i].nazivPartnera, preduzeca[i].id);
        				}  
                }          									  	  
            });
        }
	
	$("#add_new_cenovnik").on("click", function (event) {
	    event.preventDefault();
	    updateCenovnik.modal("show");
	    buttons.add.show();
	});
	
	buttons.add.on("click", function (event) {
	    var c = {};
	    event.preventDefault();
	    var preduz =  $("#selectPreduzece");
	    var datumOd =  $("#datumOd");
	    var datumDo = $("#datumDo")
	    c.datumVazenjaOd = datumOd.val();
	    c.datumVazenjaDo = datumDo.val();
	    c.poslovniPartnerId = preduz.val();
	    c.preduzeceId = null;
	
	    $.ajax({
	        url: 'api/cjenovnik?preduzece=false&id='+preduz.val(),
	        type: 'POST',
	        data: JSON.stringify(c),
	        contentType:"application/json"
	    }).done(function () {
	        popunjavanjeTabele();
	        message.modal("show");
	        message.find("div.modal-body").text("Uspjesno ste dodali cjenovnik!")
	    });
	    updateCenovnik.modal("hide");
	    c=null;
	});
	
	 tabela.on("click","button.delete_cenovnik", function (event) {
         event.preventDefault();
         var cenovnikID = $(this).attr("cenovnik_id");
         deleteContent.text("Da li ste sigurni da želite da obrišete cenovnik?");
         deleteCenovnik.modal("show");
         $("#delete_confirm").on("click",function (event) {
             event.preventDefault();
             $.ajax({
                 url: 'api/cjenovnik/'+cenovnikID,
                 type: 'DELETE',
                 contentType:"application/json"
             }).done(function () {
                 popunjavanjeTabele();
                 message.modal("show");
                 message.find("div.modal-body").text("Uspjesno ste obrisali cjenovnik!");
             });
             deleteCenovnik.modal("hide");
         });
     });
	 
	 tabela.on("click","button.kopiraj_cenovnik", function (event) {
         event.preventDefault();
         var cenovnikCopy = $(this).attr("cenovnik_kopiraj");
         copyCenovnik.modal("show");
         $.ajax({
//           --povuci sve sem tog nadjenog
             url: "api/cjenovnik/"+cenovnikCopy+"/bezIzabranog",
             type: 'GET',
             success: function(data){
                var select = document.getElementById("cenovnikDropdown");
                $("#cenovnikDropdown").empty();
                data.forEach(function(value){
                       $("#cenovnikDropdown").append('<option value="' + value.id + '">' + new Date(value.datumVazenjaOd).toLocaleString() + " - " +  new Date(value.datumVazenjaDo).toLocaleString() + '</option>');
                })
                $("#kopi_cen").on("click", function (event) {
                  var ciljaniId = $("#cenovnikDropdown").val();
                  $.ajax({
                      url: "api/cjenovnik/"+cenovnikCopy+"/kopirajIzCjenovnika/"+ciljaniId,
                      type: 'PUT',
                      success: function(data){
                        copyCenovnik.modal("hide");
                        message.modal("show");
                        message.find("div.modal-body").text("Uspjesno ste kopirali stavke cjenovnika!");
                      }
                  });
                });
             }
         });
  });
	
	
	   nmbSelect.on('change',function (event) {
	        event.preventDefault();
	        numberPerPage = $(this).val();
	        popunjavanjeTabele();
	    });
	   
	    cenovnikPagintaion.on("click","a.page-link", function (event) {
	        event.preventDefault();
	        page = $(this).attr("page");
	        popunjavanjeTabele();
	    });


});