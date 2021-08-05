$( "#state" ).change(function() {
    let county = $('#county');
    county.empty();

    const url = '/counties/listCountiesByState?idState='+$("#state").val();

    $.getJSON(url, function (data) { 	
        $.each(data, function (key, objeto) {
            county.append($('<option></option>').attr('value', objeto.id).text(objeto.name));
        })
    });
});

$( "#county" ).change(function() {
    let neighborhood = $('#neighborhood');
    neighborhood.empty();

    const url = '/neighborhoods/listNeighborhoodByCounty?idCounty='+$("#county").val();

    $.getJSON(url, function (data) { 	
        $.each(data, function (key, objeto) {
            neighborhood.append($('<option></option>').attr('value', objeto.id).text(objeto.name));
        })
    });
});

