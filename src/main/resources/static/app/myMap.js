Vue.component('my-map', {
    data() {
        return {
        }
    },

    mounted() {
        ol.proj.useGeographic();
        const place = [-110, 45];
        const point = new ol.geom.Point(place);
        const map = new ol.Map({
            target: 'map',
            view: new ol.View({
                center: place,
                zoom: 8
            }),
            layers: [
                new ol.layer.Tile({
                    source: new ol.source.OSM()
                }),

                /*new ol.layer.Vector({
                    source: new ol.source.Vector({
                        features: [new ol.Feature(point)],
                    }),
                    style: new ol.style.Style({
                        image: new ol.style.Circle({
                            radius: 9,
                            fill: new ol.style.Fill({color: 'red'}),
                        }),
                    }),
                }),*/
            ]
        });

        const apiKey = "AAPK183f27d7692c493a86caa4dd5402b70f_wuRtUoWdfhqf7qDyzMLOW8mXf2UB_2J8adyU20EiQ1zlxhgq4li3uMzzsoDRp2q";
        const basemapId = "ArcGIS:Navigation";
        const basemapURL = "https://basemaps-api.arcgis.com/arcgis/rest/services/styles/" + basemapId + "?type=style&token=" + apiKey;
        olms(map, basemapURL);
        const popup2 = new Popup();
        map.addOverlay(popup2);

        document.getElementById("geocode-button").addEventListener("click", () => {
            const query = document.getElementById("geocode-input").value;
            const authentication = arcgisRest.ApiKeyManager.fromKey(apiKey);
            const center = ol.proj.transform(map.getView().getCenter(), "EPSG:3857", "EPSG:4326");
            arcgisRest
                .geocode({
                    singleLine: query,
                    authentication,

                    params: {
                        outFields: "*",
                        location: center.join(","),
                        outSR: 3857 // Request coordinates in Web Mercator to simplify displaying
                    }
                }).then((response) => {
                const result = response.candidates[0];
                if (!result === 0) {
                    alert("That query didn't match any geocoding results.");
                    return;
                }

                const coords = [result.location.x, result.location.y];
                popup2.show(coords, result.attributes.LongLabel);
                map.getView().setCenter(coords);
                const latLon = ol.proj.toLonLat(coords);
                const point = new ol.geom.Point(latLon);
                }).catch((error) => {
                alert("There was a problem using the geocoder. See the console for details.");
                console.error(error);
                });
            });

        /*map.on('click', function(evt){
            var coords = ol.proj.toLonLat(evt.coordinate);
            var lat = coords[1];
            var lon = coords[0];
            var locTxt = "Latitude: " + lat + " Longitude: " + lon;
            // coords is a div in HTML below the map to display
            alert(locTxt);
        });*/

        /*const element = document.getElementById('popup');
        const popup = new ol.Overlay({
            element: element,
            positioning: 'bottom-center',
            stopEvent: false,
            offset: [0, -10],
        });
        map.addOverlay(popup);

        function formatCoordinate(coordinate) {
            return `
                <table>
                  <tbody>
                    <tr><th>lon</th><td>${coordinate[0].toFixed(2)}</td></tr>
                    <tr><th>lat</th><td>${coordinate[1].toFixed(2)}</td></tr>
                  </tbody>
                </table>`;
        }

        const info = document.getElementById('info');
        map.on('moveend', function () {
            const view = map.getView();
            const center = view.getCenter();
            info.innerHTML = formatCoordinate(center);
        });

        map.on('click', function (event) {
            $(element).popover('dispose');

            const feature = map.getFeaturesAtPixel(event.pixel)[0];
            if (feature) {
                const coordinate = feature.getGeometry().getCoordinates();
                popup.setPosition([
                    coordinate[0] + Math.round(event.coordinate[0] / 360) * 360,
                    coordinate[1],
                ]);
                $(element).popover({
                    container: element.parentElement,
                    html: true,
                    sanitize: false,
                    content: formatCoordinate(coordinate),
                    placement: 'top',
                });
                $(element).popover('show');
            }
        });

        map.on('pointermove', function (event) {
            if (map.hasFeatureAtPixel(event.pixel)) {
                map.getViewport().style.cursor = 'pointer';
            } else {
                map.getViewport().style.cursor = 'inherit';
            }
        });*/
    },

    template: `
    <div>
        <div id="map" class="map" style="width: 1000px;height: 800px">
            <div id="popup"></div>
        </div>
        <div id="info"></div>
        <div class="search">
          <input id="geocode-input" type="text" placeholder="Enter an address or place e.g. 1 York St" size="50" />
          <button id="geocode-button">Geocode</button>
        </div>
    </div>
    `
});