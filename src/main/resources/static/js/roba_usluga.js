$(document).ready(function(){
	$("#navigation").load("navigation.html");
	
	var params = getParameters();
	var grupa = "";
	if (params["grupa"]!=null){
		$("#inputGrupa").hide();
		grupa = "?grupa="+params["grupa"];
	}
	var grupaFilter = $("#grupa-robe-filter");

    var page = 0; var robaPagintaion = $('#roba-pagination');
    var nameSearch = $("#naziv-robe-usluge");
    var nmbSelect = $('#nmb-select');
    var numberPerPage = nmbSelect.val();

    var robaTable = $("#robaTable tbody");
	getGrupaRobe();
	getRobaUsluga();
	
	function getRobaUsluga() {
		robaTable.empty();
		var naziv = nameSearch.val();
		var grupa = grupaFilter.val();
        $.ajax({url: `api/robausluga?page=${page}&num=${numberPerPage}&naziv=${naziv}&grupa=${grupa}`,
            type: 'GET',
            dataType: "json",
            contentType: "application/json",
            success: function(data, textStatus, request){
                robaPagintaion.empty();
                console.log(request.getResponseHeader('total'));
                for(var i=0; i<request.getResponseHeader('total'); i++){
                    robaPagintaion.append(`<li class="page-item ${page==i? 'active':''}">` +
                        `<${page==i? 'span':'a'} class="page-link" page="${i}">${i+1}</${page==i? 'span':'a'}></li>`);
                }
                for (i=0 ; i < data.length ; i++)
                    addRoba(data[i]);
            }
        });
	}
	
	function addRoba(data){
		var grupaRobe;
		$.ajax({
            url: 'api/grupa_robe/'+data.grupaRobe,
            type: 'GET',
            async: false,
            success: function(data) { grupaRobe = data.nazivGrupe; }
        });
		$("#robaTable tbody").append("<tr><td>"+data.nazivRobeUsluge+"</td><td>"+data.jedinicaMjere+"</td><td>"+
				grupaRobe+"</td><td class='text-right'><button value='"+data.id+"' class='btn btn-outline-primary edit'>Izmijeni</button></td><td>"+
				"<button value='"+data.id+"' class='btn btn-outline-danger delete'>Obrisi</button></td></tr>");
	}

	function getGrupaRobe(){
        $.ajax({url: 'api/grupa_robe',
            dataType: "json",
            contentType: "application/json",
            success: function(data){
                for (i=0 ; i < data.length ; i++){
                    $("#grupaRobe").append("<option value='"+data[i].id+"'>"+data[i].nazivGrupe+"</option>");
                    grupaFilter.append("<option value='"+data[i].id+"'>"+data[i].nazivGrupe+"</option>");
				}
                if (params["grupa"]==null)
                    $("#grupaRobe").selectedIndex = 0;
                else $("#grupaRobe").val(params["grupa"]);
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
	
	$("#addRoba").click(function(e){
		$("#title").text("Dodavanje robe");
		$("#naziv").val("");
		$("#jedinicaMere").val("");
		$("#addEditModal").modal("show");
		$("#confirmSave").on("click",function(e) {
            e.preventDefault();
            var naziv = $("#naziv").val();
            var jedinicaMjere = $("#jedinicaMere").val();
            var grupaRobe = $("#grupaRobe").val();
            $.ajax({url: 'api/robausluga',
                type: 'POST',
                data: '{"nazivRobeUsluge":"'+naziv+'", "jedinicaMjere":"'+jedinicaMjere+'", "grupaRobe":"'+grupaRobe+'"}',
                contentType:"application/json",
                complete: function(data){
                	window.location.reload();
                }});
            $("#addEditModal").modal("hide");
        });
	});
	
	$(document).on("click",".edit",function(e){
		var id = $(this).val();
		console.log(id);
		$("#title").text("Izmjena robe");
		$.ajax({url: 'api/robausluga/'+id,
			type: 'GET',
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				$("#naziv").val(data.nazivRobeUsluge);
				$("#jedinicaMere").val(data.jedinicaMjere);
				$("#grupaRobe").val(data.grupaRobe);
			}});
		$("#addEditModal").modal("show");
		$("#confirmSave").on("click",function(e) {
            e.preventDefault();
            var naziv = $("#naziv").val();
            var jedinicaMere = $("#jedinicaMere").val();
            var grupaRobe = $("#grupaRobe").val();
            $.ajax({url: 'api/robausluga/'+id,
                type: 'PUT',
                data: '{"id":'+id+',"nazivRobeUsluge":"'+naziv+'", "jedinicaMjere":"'+jedinicaMere+'", "grupaRobe":'+grupaRobe+'}',
                contentType:"application/json",
                complete: function(data){
                	window.location.reload();
                }});
            $("#addEditModal").modal("hide");
        });
	});
	
	$(document).on("click",".delete",function(e){
		e.preventDefault();
		var id = $(this).val();
		$("#deleteModal").modal("show");
		$("#confirm").on("click",function(e) {
            e.preventDefault();
            $.ajax({url: 'api/robausluga/'+id,
                type: 'DELETE',
                contentType:"application/json",
                complete: function(data){
                	window.location.reload();
                }});
            $("#deleteModal").modal("hide");
        });
	});
		
	
    nameSearch.on('keyup', function (event) {
        event.preventDefault();
        page = 0;
        getRobaUsluga();
    });
    nmbSelect.on('change',function (event) {
        event.preventDefault();
        numberPerPage = $(this).val();
        getRobaUsluga();
    });
    grupaFilter.on('change',function (event) {
        event.preventDefault();
        getRobaUsluga();
    });

    robaPagintaion.on("click","a.page-link", function (event) {
        event.preventDefault();
        page = $(this).attr("page");
        getRobaUsluga();
    });
});