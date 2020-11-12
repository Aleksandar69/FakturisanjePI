$(document).ready(function(){
	
    $("#navigation").load("navigation.html");
    
	var params = getParameters();
	var obrisano=false;
	var stavka;
	var deleteContent = $("#delete_content");
	var deleteStavka = $("#delete_stavka");
    
	function addStavke(data){
		for (var i = 0; i < data.length; i++){
		if (!data[i].obrisano) {
					var robaUsluga;
        			var grupaRobe;
        			$.ajax({url: 'api/robausluga/'+data[i].robaUsluga,
        				dataType: "json",
        				contentType: "application/json",
        				async: false,
        				success: function(data){
        					robaUsluga=data;
        				}});
        			$.ajax({url: 'api/grupa_robe/'+robaUsluga.grupaRobe,
        				dataType: "json",
        				contentType: "application/json",
        				async: false,
        				success: function(data){
        					grupaRobe=data.nazivGrupe;
						}});
					if (!obrisano) {
        				$("#stavkeTable tbody").append("<tr><td>"+(i+1)+"</td><td>"+robaUsluga.nazivRobeUsluge+"</td><td>"+grupaRobe+"</td><td>"+
        					robaUsluga.jedinicaMjere+"</td><td>"+data[i].kolicina+"</td><td> <button stavka_id='" + data[i].id + "' class='btn btn-outline-danger delete_stavka'>Obrisi</button></td></tr>")

        			}else{
						$("#stavkeTable tbody").append("<tr><td>"+(i+1)+"</td><td>"+robaUsluga.nazivRobeUsluge+"</td><td>"+grupaRobe+"</td><td>"+
        					robaUsluga.jedinicaMjere+"</td><td>"+data[i].kolicina+"</td></tr>")
					}
		    }

		};
	}

		
	function getPoslovniPartner(id){
		$.ajax({url: 'api/poslovni_partneri/'+id,
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log(data);
				$("#nazivPP").text(data.nazivPartnera);
				var racun = data.tekuciRacun.slice(0, 3) + "-" + data.tekuciRacun.slice(3, 15) + "-" + data.tekuciRacun.slice(15);
				$("#tekuciRacunPP").text(racun);
				$("#adresaIMestoPP").text(data.adresa);
				getMesto(data.mjesto);
			}});
	}
	
	function getGrupaRobe(id){		
		$("#robaUsluga").empty();
		$.ajax({url: 'api/narudzbenice/'+params["id"]+'/robaCjenovnika',
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log(data);
				for(var i = 0; i < data.length; i++){
					$("#robaUsluga").append("<option value='"+data[i].id+"'>"+data[i].nazivRobeUsluge+"</option>");
				}
				$("#robaUsluga").val(0);
			}});
	}
	
	function getMesto(id){
		$.ajax({url: 'api/mjesto/'+id,
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log(data);
				$("#adresaIMestoPP").text($("#adresaIMestoPP").text() + ", " + data.postanskiBroj + " " + data.naziv+", "+data.drzava);
			}});
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
	
	$.ajax({url: 'api/narudzbenice/' +params["id"],
		dataType: "json",
		contentType: "application/json",
		success: function(data){
            var poslovnaGodina;
			$.ajax({
                url: 'api/poslovne_godine/'+data.poslovnaGodina,
                type: 'GET',
                async: false,
                success: function(data) { 
					poslovnaGodina = data.godina; 
				}
			});
//			if(data.obrisano==true || data.tipNarudzbenice==true){
//				document.getElementById('addStavkaNarudzbenica').style.visibility='hidden';
//			}
            obrisano = data.obrisano
			$("#addStavka").show();
			$("#brojRacuna").text(data.brojNarudzbenice+"/"+poslovnaGodina);
			$("#datumIzdavanja").text(new Date(data.datumNarudzbenice).toLocaleString());
			
			getGrupaRobe(data.preduzece);
			getPoslovniPartner(data.poslovniPartner);
		}});
	
	$.ajax({url: 'api/narudzbenice/'+params["id"]+'/stavkeNarudzbenice',
		dataType: "json",
		contentType: "application/json",
		success: function(data){
			console.log(data);
			addStavke(data);
			
		}
		
	});
	
	$("#addStavkaNarudzbenica").click(function(e){
		$("#addModal").modal("show");
		$("#confirmSave").on("click",function(e) {
			var kolicina = $("#kolicina").val();
			var robaUsluga = $("#robaUsluga").val();
			var robaUslugaNaziv;
			var jedinicaM;
			if (!robaUsluga || !kolicina || kolicina<=0 )
				return;
			$.ajax({url: 'api/robausluga/'+robaUsluga,
				dataType: "json",
				contentType: "application/json",
				async: false,
				success: function(data){
					robaUslugaNaziv=data.nazivRobeUsluge;
					jedinicaM=data.jedinicaMjere;
			}});

			var stavkeNarudzbenice = {
				jedinicaMjere: jedinicaM,
				kolicina: kolicina,
				opisRobe: robaUslugaNaziv,
				robaUsluga:robaUsluga,
				narudzbenica: params["id"]
			}
			$.ajax({url: 'api/stavkanarudzbenice',
				dataType: 'json',
				contentType:'application/json',
				type:'POST',
				data: JSON.stringify(stavkeNarudzbenice),
				complete: function(data){
					window.location.reload();
		                }
				});
		    	$("#addModal").modal("hide");
		   	});
	})
	
	$("#stavkeTable").on("click","button.delete_stavka", function (event) {
		event.preventDefault();
           stavka = $(this).attr("stavka_id");
		   deleteContent.text("Da li ste sigurni da želite da obrišete stavku?");
		   deleteStavka.modal("show");
		   console.log("stavka id: " + stavka);
		$("#delete_confirm").on("click",function (event) {
		event.preventDefault();
    	    $.ajax({url: 'api/stavkanarudzbenice/'+stavka,
        	    type: 'DELETE',
				dataType: "json",
				contentType: "application/json",
				async: false,
				success: function(data){
					window.location.reload();
				}
			});
		});

    });
	
});