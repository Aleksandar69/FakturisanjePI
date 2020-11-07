$(document).ready(function(){
    $("#navigation").load("navigation.html");

    var params = getParameters();
	var jedinicnaCena = 0;
	var obrisano=false;
	var stavka;
	
	var deleteContent = $("#delete_content");
	var deleteStavka = $("#delete_stavka");

	
	function getGrupaRobe(id){		
		$("#robaUsluga").empty();
		$.ajax({url: 'api/otpremnice/'+params["id"]+'/robaCenovnika',
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
	
	function getMesto(id){
		$.ajax({url: 'api/mjesto/'+id,
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log(data);
				$("#adresaIMestoPP").text($("#adresaIMestoPP").text() + ", " + data.postanskiBroj + " " + data.naziv+", "+data.drzava);
			}});
	}
	
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
					robaUsluga.jedinicaMjere+"</td><td>"+data[i].kolicina+"</td><td>"+data[i].cijena+"</td><td>"+data[i].kolicina*data[i].cijena+"</td><td> <button stavka_id='" + (i+1) + "' class='btn btn-outline-danger delete_stavka'>Obrisi</button></td></tr>")

			}else{
                $("#stavkeTable tbody").append("<tr><td>"+(i+1)+"</td><td>"+robaUsluga.nazivRobeUsluge+"</td><td>"+grupaRobe+"</td><td>"+
					robaUsluga.jedinicaMjere+"</td><td>"+data[i].kolicina+"</td><td>"+data[i].cijena+"</td><td>"+data[i].kolicina*data[i].cijena+"</td></tr>")
				}
			};
		}
	}	
	
	function racunajCenu(){
		var kolicina = $("#kolicina").val();
		console.log(kolicina);
		$("#ukupanIznos").text(jedinicnaCena*kolicina);
	};
	
	$("#stavkeTable").on("click","button.delete_stavka", function (event) {
		event.preventDefault();
        stavka = $(this).attr("stavka_id");
        deleteContent.text("Da li ste sigurni da želite da obrišete stavku "+stavka+" ?");
		deleteStavka.modal("show");
		$("#delete_confirm").on("click",function (event) {
			event.preventDefault();
            $.ajax({url: 'api/stavkaOtpremnice/'+stavka,
                type: 'DELETE',
        		dataType: "json",
        		contentType: "application/json",
        		async: false,
        		success: function(data){
        			 window.location.reload();
        			 $("#ukupanIznosOtpremnice").text("0");
				}
			});
		});
	});
		
	
	$.ajax({url: 'api/otpremnice/' +params["id"],
		dataType: "json",
		contentType: "application/json",
		success: function(data){
            var poslovnaGodina;
			$.ajax({
                url: 'api/poslovne_godine/'+data.poslovnaGodina,
                type: 'GET',
                async: false,
                success: function(data) { poslovnaGodina = data.godina; }
			});

			if(data.obrisano==true){
				document.getElementById('addStavkaOtpremnice').style.visibility='hidden';
			}

            obrisano = data.obrisano
			$("#addStavka").show();
			$("#brojRacuna").text(data.brojOtpremnice+"/"+poslovnaGodina);
			$("#datumIzdavanja").text(new Date(data.datumOtpremnice).toLocaleString());
			$("#ukupanIznosOtpremnice").text(data.iznosZaUplatu);

			getGrupaRobe(data.preduzece);
			getPoslovniPartner(data.poslovniPartner);
		}});
	
	$.ajax({url: 'api/otpremnice/'+params["id"]+'/stavkeOtpremnice',
		dataType: "json",
		contentType: "application/json",
		success: function(data){
			console.log(data);
			addStavke(data);
		}});
	
	$("#addStavkaOtpremnice").click(function(e){
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
	           var stavkeOtpremnice = {
	   				jedinicaMjere: jedinicaM,
	   				kolicina: kolicina,
	   				opisRobe: robaUslugaNaziv,
	   				robaUsluga:robaUsluga,
	                   otpremnica: params["id"],
	                   iznos:jedinicnaCena*kolicina,
	                   cijena: jedinicnaCena
	               }

	   			$.ajax({url: 'api/stavkaOtpremnice',
	   				dataType: 'json',
	   				contentType:'application/json',
	   				type:'POST',
	   				data: JSON.stringify(stavkeOtpremnice),
	   				complete: function(data){
	   					window.location.reload();
	   		                }
	   				});
	   		    	$("#addModal").modal("hide");
	   		   	});
	   	});
	   	
	$("#robaUsluga").change(function(e){
		e.preventDefault();
		var id = $("#robaUsluga").val();
		$.ajax({url: 'api/robausluga/'+id+'/cijena',
			dataType: "json",
			contentType: "application/json",
			async: false,
			success: function(data){
				console.log(data);
				jedinicnaCena = data.cijena;
				$("#jedinicnaCena").text(data.cijena);
				// getPdv();
				 racunajCenu();
			}});
	});
	   $("#kolicina").on("input",function(e){
		e.preventDefault();
		racunajCenu();
	});
	
});