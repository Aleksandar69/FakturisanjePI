$(document).ready(function(){
	$("#navigation").load("navigation.html");
	
    var message;
    $("#message-dialog").load("dialog/message_dialog.html", function () {
        message = $("#message");
    });
    
    var id = getParameters()['id'];
    var tabelaStavki = $("#tabela");
    var deleteStavka = $("#delete_stavka");
    var updateStavka = $("#update_stavke");
    var deleteContent = $("#delete_content");
    var stavkaHtml = {
        robausluga: $("#add_edit_roba"),
        cena: $("#cena-stavke"),
    };
    var page = 0; 
    var stavkePagintaion = $('#stavke-page');
    var nameSearch = $("#naziv-robe-usluge");
    var nmbSelect = $('#nmb-select');
    var numberPerPage = nmbSelect.val();
    
    var stavka = {
            id: 0,
            cijena: 50,
            cjenovnik: id,
            robaUsluga: 1
        };

        buttons = {
            addEdit: $("#add_edit_stavka")
        };

        var cenovnikHtml = {
            datumVazenja: $("#datum-vazenja"),
            preduzece: {
                naziv: $("#naziv-preduzeca"),
                adresa: $("#adresa-preduzeca"),
                pib: $("#pib-preduzeca"),
                telefon: $("#telefon-preduzeca"),
                email: $("#email-preduzeca"),
                link: $("#show-preduzece")
            }
        };
        
        function ucitajStavkeCenovnika() {
            tabelaStavki.empty();
            var naziv = nameSearch.val();
            $.ajax({
                url: `api/cjenovnik/${id}/stavke_cjenovnika?page=${page}&num=${numberPerPage}&naziv=${naziv}`,
                type: 'GET',
                contentType:"application/json",
                success: function(data, textStatus, request) {
                    stavkePagintaion.empty();
                    for(var i=0; i<request.getResponseHeader('total'); i++){
                        stavkePagintaion.append(`<li class="page-item ${page==i? 'active':''}">` +
                            `<${page==i? 'span':'a'} class="page-link bg-primary text-light" page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                    }
                    data.forEach(function (value) {
                        red = $("<tr></tr>");
                        var nazivRobeUsluge;
                        $.ajax({
                            url: 'api/robausluga/' + value.robaUsluga,
                            type: 'GET',
                            async: false, //blocks window close
                            success: function (data) {
                                nazivRobeUsluge = data.nazivRobeUsluge;
                            }
                        });
                        red.append("<td>" + nazivRobeUsluge + "</td>");
                        red.append("<td>" + value.cijena + " din</td>");
                        red.append("<td class='text-center'>" +
                            "<button stavka_id='" + value.id + "' class='btn btn-outline-primary update_stavka mr-3 '>Izmeni</button>" +
                            "<button stavka_id='" + value.id + "' class='btn btn-outline-danger delete_stavka'>Obrisi</button></td>");
                        tabelaStavki.append(red);
                    });
                }
            });
        }
        
        function getParameters(){
            var param = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for(var i = 0; i < hashes.length; i++)
            {
                hash = hashes[i].split('=');
                param.push(hash[0]);
                param[hash[0]] = hash[1];
            }
            return param;
        }
        
        $("#id").text(id);
        $.get( "api/cjenovnik/"+id, function( data ) {
            var date = new Date(data.datumVazenja);
            cenovnikHtml.datumVazenja.text(date.toLocaleDateString());
            ucitajStavkeCenovnika();
        });
        
        $.get( "api/robausluga", function( data ) {
            data.forEach(function (value) {
                stavkaHtml.robausluga.append("<option value='"+value.id+"'>"+value.nazivRobeUsluge+"</option>");
            });
        });
        
        tabelaStavki.on("click","button.delete_stavka", function (event) {
            event.preventDefault();
            stavka.id = $(this).attr("stavka_id");
            deleteContent.text("Da li ste sigurni da želite da obrišete stavku koja ima id="+stavka.id+" ?");
            deleteStavka.modal("show");
        });
        
        tabelaStavki.on("click","button.delete_stavka", function (event) {
            event.preventDefault();
            stavka.id = $(this).attr("stavka_id");
            deleteContent.text("Da li ste sigurni da želite da obrišete stavku koja ima id="+stavka.id+" ?");
            deleteStavka.modal("show");
        });
        
        $("#delete_confirm").on("click",function (event) {
            event.preventDefault();
            $.ajax({
                url: 'api/stavka_cjenovnika/'+stavka.id,
                type: 'DELETE',
                contentType:"application/json"
            }).done(function () {
                ucitajStavkeCenovnika();
                message.modal("show");
                message.find("div.modal-body").text("Uspjesno ste obrisali stavku cjenovnika!");
                stavka.id = 0;
            });
            deleteStavka.modal("hide");
        });
        
        $("#add_new_stavka").on("click", function (event) {
            event.preventDefault();
            updateStavka.modal("show");
        });
        
        tabelaStavki.on("click", "button.update_stavka", function (event) {
            event.preventDefault();
            stavka.id = $(this).attr("stavka_id");
            $.ajax({
                url: 'api/stavka_cjenovnika/'+stavka.id,
                type: 'GET',
                success: function(data) {
                    updateStavka.modal("show");
                    stavkaHtml.cena.val(data.cijena);
                    stavkaHtml.robausluga.val(data.robaUsluga);
                }
            });
        });
        buttons.addEdit.on("click", function (event) {
            event.preventDefault();
            stavka.cijena = stavkaHtml.cena.val();
       	 	console.log(stavka.cijena);
            stavka.robaUsluga = stavkaHtml.robausluga.val();
            console.log("roba usluga id: " + stavka.robaUsluga);
            console.log("cjenovnik id: " + stavka.cenovnik);
            var url = "api/stavka_cjenovnika";
            var method = "POST";
            if(stavka.id!=0){
                url += "/"+stavka.id;
                method = "PUT";
            }
            $.ajax({
                url: url,
                type: method,
                data: JSON.stringify(stavka),
                contentType:"application/json"
            }).done(function () {
                ucitajStavkeCenovnika();
                message.modal("show");
                if(stavka.id==0){
                    message.find("div.modal-body").text("Uspjesno ste dodali stavku cjenovnika!")
                }else {
                    message.find("div.modal-body").text("Uspjesno ste izmijenili stavku cjenovnika!")
                }
                stavka.id = 0;
            });
            updateStavka.modal("hide");
        });
        
        stavkePagintaion.on("click","a.page-link", function (event) {
            event.preventDefault();
            page = $(this).attr("page");
            ucitajStavkeCenovnika();
        });
        
        nmbSelect.on('change',function (event) {
            event.preventDefault();
            numberPerPage = $(this).val();
            ucitajStavkeCenovnika();
        });
        
        nameSearch.on('keyup', function (event) {
            event.preventDefault();
            page = 0;
            ucitajStavkeCenovnika();
        });

});