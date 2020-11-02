$(document).ready(function(){
 
	$("#navigation").load("navigation.html"); 
    var tabela = $("#tblMesta");
    var message = $("#message");
    var deleteMesto = $("#delete_mesto");
    var updateMesto = $("#update_mesto");
    var deleteContent = $("#delete_content");
    var deleteMesto = $("#delete_mjesto");
    $("#postanskiBroj").keyup(proveraPB);
    var buttons = {
            add: $("#add_mesto"),
            edit: $("#edit_mesto")
        };
    var page = 0; var mestaPagintaion = $('#mesta-page');
    var nameSearch = $("#naziv-filter");
    var nmbSelect = $('#nmb-select');
    var numberPerPage = nmbSelect.val();
    popunjavanjeTabele();

    var mestoHtml = {
        naziv: $("#nazivMesta"),
        pb: $("#postanskiBroj"),
        drzava: $("#drzava")

        };
    
    function popunjavanjeTabele() {
   	 tabela.empty();
        var nazivFilter = nameSearch.val();
        $.ajax({
            url: `api/mjesto?page=${page}&num=${numberPerPage}&naziv=${nazivFilter}`,
            type: 'GET',
            contentType:"application/json",
            success: function(data, textStatus, request) {
                mestaPagintaion.empty();
                for(var i=0; i<request.getResponseHeader('total'); i++){
                    mestaPagintaion.append(`<li class="page-item ${page==i? 'active':''}">` +
                        `<${page==i? 'span':'a'} class="page-link bg-primary text-light" page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                }
                data.forEach(function (value) {
                    red = $("<tr></tr>");
                    red.append("<td>"+value.naziv+"</br></td>");
                    red.append("<td>"+value.postanskiBroj+"</br></td>");
                    red.append("<td>"+value.drzava+"</br></td>");
                    
                    red.append("<td class='text-right'>" +
                        "<button mesto_id='"+value.id+"' class='btn btn-outline-warning update_mesto'>Izmijeni</button>");
                    red.append("<td class='text-left'>" +
                            "<button mesto_id='"+value.id+"' class='btn btn-outline-danger delete_mesto'>Obrisi</button>");
                    tabela.append(red);
                });
            }
        });
    }    
    
    function proveraPB() {
	    var pb = $("#postanskiBroj").val();
	    
	    if (parseInt(pb) > 10000){
	    	document.getElementById("add_mesto").disabled= false;
	    }else{
	    	document.getElementById("add_mesto").disabled= true;

	    }
		 if (parseInt(pb) > 10000){
		 	document.getElementById("edit_mesto").disabled= false;
		 }else{
		 	document.getElementById("edit_mesto").disabled= true;
		}
    }
    
    $("#add_new_mesto").on("click", function (event) {
        event.preventDefault();
        updateMesto.modal("show");
        buttons.add.show();
        buttons.edit.hide();
        buttons.add.on("click", function (event) {
        	var mesto = {};
            event.preventDefault();
            mesto.naziv = mestoHtml.naziv.val();
            mesto.postanskiBroj =  mestoHtml.pb.val();
            mesto.drzava =  mestoHtml.drzava.val();
            $.ajax({
                url: 'api/mjesto',
                type: 'POST',
                data: JSON.stringify(mesto),
                contentType:"application/json"
            }).done(function () {
                popunjavanjeTabele();
                message.modal("show");
                message.find("div.modal-body").text("Uspjesno ste dodali mjesto!")
            });
            updateMesto.modal("hide");
        });
    });
    
    tabela.on("click", "button.update_mesto", function (event) {
        event.preventDefault();
        var mestoID = $(this).attr("mesto_id");
        $.ajax({
            url: 'api/mjesto/'+mestoID,
            type: 'GET',
            success: function(data) {
                updateMesto.modal("show");
                buttons.edit.show();
                buttons.add.hide();
                mestoHtml.naziv.val(data.naziv);
                mestoHtml.pb.val(data.postanskiBroj);
                mestoHtml.drzava.val(data.drzava);
                buttons.edit.on("click", function (event) {
                	var mesto= {};
                    event.preventDefault();
                    mesto.naziv = mestoHtml.naziv.val();
                    mesto.postanskiBroj =  mestoHtml.pb.val();
                    mesto.drzava =  mestoHtml.drzava.val();
                    mesto.id = mestoID;
                    $.ajax({
                        url: 'api/mjesto/'+mestoID,
                        type: 'PUT',
                        data: JSON.stringify(mesto),
                        contentType:"application/json"
                    }).done(function () {
                        popunjavanjeTabele();
                        message.modal("show");
                        message.find("div.modal-body").text("Uspjesno ste izmijenili mjesto!")
                    });
                    updateMesto.modal("hide");
                    mesto=null;
                });
            }
        });
    });
    
    tabela.on("click","button.delete_mesto", function (event) {
    	console.log("kliknuto delte dugme");
    event.preventDefault();
    var mestoID = $(this).attr("mesto_id");
    console.log("posle mestoid");
    deleteContent.text("Da li ste sigurni da želite da obrišete mesto koje ima id="+mestoID+" ?");
    console.log("posle deleteContent");
    deleteMesto.modal("show");
    console.log("posle pokazivanja modal-a");
    $("#delete_confirm").on("click",function (event) {
        event.preventDefault();
        $.ajax({
            url: 'api/mjesto/'+mestoID,
            type: 'DELETE',
            contentType:"application/json"
        }).done(function () {
            popunjavanjeTabele();
            message.modal("show");
            message.find("div.modal-body").text("Uspjesno ste obrisali mjesto!");
        });
        deleteMesto.modal("hide");
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

    mestaPagintaion.on("click","a.page-link", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        popunjavanjeTabele();
    });
});