$(document).ready(function(){
    $("#navigation").load("navigation.html");
	
    var params = getParameters();
	var jedinicnaCena = 0;
	var pdv;
	var vrstaFakture;
	

	
	$.ajax({url: 'api/fakture/'+params["id"],
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
			$("#addStavka").hide();
			//if (!data.placeno) $("#addStavka").show();
			$("#brojRacuna").text(data.brFakture+"/"+poslovnaGodina);
			$("#datumIzdavanja").text(new Date(data.datumFakture).toLocaleString());
			$("#datumValute").text(new Date(data.datumValute).toLocaleString());
			$("#ukupanRabat").text(data.rabat);
			$("#ukupanPDV").text(data.ukupanPdv);
			$("#poreskaOsnovica").text(data.osnovica);
			$("#ukupanIznosFakture").text(Math.round(data.iznosZaPlacanje));
			getGrupaRobe(data.preduzece);
			console.log(data.preduzece);
			getPoslovniPartner(data.poslovniPartner);
			vrstaFakture = data.vrstaFakture;
			console.log("vrsta fakture: " + data.vrstaFakture);
			console.log("vrsta fakture2: " + vrstaFakture);
			if(vrstaFakture === true){
				$("#addStavka").hide();
			}
		}});
	
	$("#addStavka").click(function(e){
		$("#addModal").modal("show");
		$("#confirmSave").on("click",function(e) {
			var kolicina = $("#kolicina").val();
			var rabat = $("#rabat").val();
			var iznosPdv = ((jedinicnaCena*kolicina)-rabat)*pdv/100;
			var iznosBezPdv = ((jedinicnaCena*kolicina)-rabat);
			var ukupanIznos = ((jedinicnaCena*kolicina)-rabat)*(1+(pdv/100));
			var robaUsluga = $("#robaUsluga").val();
			if (!robaUsluga || !kolicina || kolicina<=0 || rabat>(jedinicnaCena*kolicina))
				return;
			$.ajax({url: 'api/stavkafakture',
				dataType: 'json',
				contentType:'application/json',
				type:'POST',
				data: '{"kolicina":'+kolicina+',"cijena":'+jedinicnaCena+',"iznosPdv":'+iznosPdv+',"rabat":'+rabat+',"osnovicaZaPdv":'+iznosBezPdv+',"procenatPdv":'+pdv+',"iznosStavka":'+ukupanIznos+',"faktura":'+params["id"]+',"robaUsluga":'+robaUsluga+'}',
				complete: function(data){
					window.location.reload();
		                }
				});
		    	$("#addModal").modal("hide");
		   	});
	})
	
	function racunajCijenu(){
		var kolicina = $("#kolicina").val();
		var rabat = $("#rabat").val();
		$("#iznosPdv").text(((jedinicnaCena*kolicina)-rabat)*pdv/100);
		$("#iznosBezPdv").text((jedinicnaCena*kolicina)-rabat);
		$("#ukupanIznos").text(Math.round(((jedinicnaCena*kolicina)-rabat)*(1+(pdv/100))));
	};
	
	$("#kolicina, #rabat").on("input",function(e){
		e.preventDefault();
		racunajCijenu();
	})
	
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
			getPdv();
			racunajCijenu();
		}});
	})
	
	function getPdv(){
		var id = $("#robaUsluga").val();
		$.ajax({url: 'api/robausluga/'+id+'/pdv',
			dataType: "json",
			contentType: "application/json",
			async: false,
			success: function(data){
				$.ajax({url: 'api/pdv/'+data.id+'/stopa',
					dataType: "json",
					contentType: "application/json",
					async: false,
					success: function(data){
						pdv = data.procenat;
					}});
			}});
	};
	
	$.ajax({url: 'api/fakture/'+params["id"]+'/stavke',
		dataType: "json",
		contentType: "application/json",
		success: function(data){
			console.log(data);
			addStavke(data);
		}});
	
	
	function addStavke(data){
		for (var i = 0; i < data.length; i++){
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
			$("#stavkeTable tbody").append("<tr><td>"+(i+1)+"</td><td>"+robaUsluga.nazivRobeUsluge+"</td><td>"+grupaRobe+"</td><td>"+
					robaUsluga.jedinicaMjere+"</td><td>"+data[i].kolicina+"</td><td>"+data[i].cijena+"</td><td>"+
					parseFloat((data[i].rabat*100)/(data[i].cijena*data[i].kolicina)).toFixed(2)+"</td><td>"+data[i].osnovicaZaPdv+"</td><td>"+data[i].procenatPdv+"</td><td>"+
					data[i].iznosPdv+"</td><td>"+Math.round(data[i].iznosStavka)+"</td></tr>")
		};
	}
	
	function getPoslovniPartner(id){
		$.ajax({url: 'api/poslovni_partneri/'+id,
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				$("#nazivPP").text(data.nazivPartnera);
				var racun = data.tekuciRacun.slice(0, 3) + "-" + data.tekuciRacun.slice(3, 15) + "-" + data.tekuciRacun.slice(15);
				$("#tekuciRacunPP").text(racun);
				$("#adresaIMestoPP").text(data.adresa);
				getMjesto(data.mjesto);
			}});
	}
	
	function getGrupaRobe(id){		
		$("#robaUsluga").empty();
		$.ajax({url: 'api/fakture/'+params["id"]+'/robaCenovnika',
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
	
	function getMjesto(id){
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
	
});