Vue.component('my-map', {
    mounted() {
        var map = new ol.Map({
            target: 'map',
            layers: [
                new ol.layer.Tile({
                    source: new ol.source.OSM()
                })
            ],
            view: new ol.View({
                center: ol.proj.fromLonLat([19.83624, 45.25833]),
                zoom: 6
            })
        });
        var viewHeight = $(window).height();
        var header = $("div[data-role='header']:visible:visible");
        var navbar = $("div[data-role='navbar']:visible:visible");
        var content = $("div[data-role='content']:visible:visible");
        var contentHeight = viewHeight - header.outerHeight() - navbar.outerHeight();
        content.height(contentHeight);
        map.updateSize();

        map.on('click', function(evt){
            var coords = ol.proj.toLonLat(evt.coordinate);
            var lat = coords[1];
            var lon = coords[0];
            var locTxt = "Latitude: " + lat + " Longitude: " + lon;
            // coords is a div in HTML below the map to display
            alert(locTxt);
        });
    },

    template: `
    <div id="map" class="map" style="width: 1000px;height: 800px"></div>
    `
});