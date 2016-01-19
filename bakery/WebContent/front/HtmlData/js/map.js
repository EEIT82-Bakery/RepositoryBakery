var map;
var markers = [];
function initMap() {
            
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 8,
        center: { lat: 25.033743, lng: 121.543400 }
    });
    var geocoder = new google.maps.Geocoder();
    var infowindow = new google.maps.InfoWindow;

    document.getElementById('submit').addEventListener('click', function () {
        deleteMarkers();
        geocodeAddress(geocoder, map, infowindow);
    });
}
function geocodeAddress(geocoder, map, infowindow) {
            
    var address = document.getElementById("address").value;
    geocoder.geocode({ 'address': address }, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
            map.setZoom(16);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location                     
            });
            markers.push(marker);
            infowindow.setContent(results[0].formatted_address + "(" + Math.round(results[0].geometry.location.lat() * 1000000) / 1000000 + "," + Math.round(results[0].geometry.location.lng() * 1000000) / 1000000 + ")");
            infowindow.open(map, marker);
        } else {
            alert("Geocode was not successful for the following reason: " + status);
        }
    });
}
function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}
function clearMarkers() {
    setMapOnAll(null);
}
function deleteMarkers() {
    clearMarkers();
    markers = [];
}
