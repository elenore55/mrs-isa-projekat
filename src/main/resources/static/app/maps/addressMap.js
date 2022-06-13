Vue.component("address-map", {
    /*data() {
        return {
            street: '',
            city: '',
            country: ''
        }
    },*/

    props: ['my_style', 'street_init', 'city_init', 'country_init'],

    mounted() {
        const map = new ol.Map({ target: "map" });

        map.setView(
            new ol.View({
                center: ol.proj.fromLonLat([19, 45]),
                zoom: 6
            })
        );

        const apiKey = "AAPK183f27d7692c493a86caa4dd5402b70f_wuRtUoWdfhqf7qDyzMLOW8mXf2UB_2J8adyU20EiQ1zlxhgq4li3uMzzsoDRp2q";
        const basemapId = "ArcGIS:Navigation";
        const basemapURL = "https://basemaps-api.arcgis.com/arcgis/rest/services/styles/" + basemapId + "?type=style&token=" + apiKey;
        olms(map, basemapURL);

        const popup = new Popup({closeButton: false, closeOnClick: false, closeOnMove: false});
        map.addOverlay(popup);

        if (this.street_init && this.city_init && this.country_init) {
            const query = this.street_init + ', ' + this.city_init + ', ' + this.country_init;
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
                })
                .then((response) => {
                    const result = response.candidates[0];
                    if (!result === 0) {
                        alert("That query didn't match any geocoding results.");
                        return;
                    }
                    const coords = [result.location.x, result.location.y];
                    popup.show(coords, result.attributes.LongLabel);
                    map.getView().setCenter(coords);
                })
        }


        // popup.show(ol.proj.fromLonLat([151.2093, -33.8688]), "This is a label of mine");

        // document.getElementById("geocode-button").addEventListener("click", () => {


        // });
    },

    template: `
    <div>
        <div id="map" :style="my_style"></div>
    </div>
    `,

    methods: {

    }
});