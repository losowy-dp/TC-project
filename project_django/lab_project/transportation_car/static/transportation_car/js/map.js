



// // Initialize the map and assign it to a variable for later use
// var map = L.map('mapid', {
//     // Set latitude and longitude of the map center (required)
//     center: [37.7833, -122.4167],
//     // Set the initial zoom level, values 0-18, where 0 is most zoomed-out (required)
//     zoom: 10
// });

// L.control.scale().addTo(map);

// // Create a Tile Layer and add it to the map
// //var tiles = new L.tileLayer('http://{s}.tile.stamen.com/watercolor/{z}/{x}/{y}.png').addTo(map);
// L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
//     attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
//   }).addTo(map);

//   var searchControl = new L.esri.Controls.Geosearch().addTo(map);

//   map.addControl( new L.Control.Search({
//     container: "search1",
//     layer: markersLayer,
//     initial: false,
//     collapsed: false
//   }));

//   var results = new L.LayerGroup().addTo(map);

//   searchControl.on('results', function(data){
//     results.clearLayers();
//     for (var i = data.results.length - 1; i >= 0; i--) {
//       results.addLayer(L.marker(data.results[i].latlng));
//     }
//   });

// setTimeout(function(){$('.pointer').fadeOut('slow');},3400);




var map = L.map('mapid', {
  center: [14.599512, 120.984222],
  zoom: 13,
  // minZoom: 1.5,
  //  maxZoom: 1.5
  });
  
  // Start adding controls as follow... L.controlName().addTo(map);
  
  // Control 1: This add the OpenStreetMap background tile
  L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
  attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);
  
  
  // Control 2: This add a scale to the map
  L.control.scale().addTo(map);
  
  // Control 3: This add a Search bar
  var searchControl = new L.esri.Controls.Geosearch().addTo(map);
  // var searchControl2 = new L.esri.Controls.Geosearch().addTo(map);
  
  var markersLayer = new L.LayerGroup();	//layer contain searched elements
	map.addLayer(markersLayer);

  
  
  var results = new L.LayerGroup().addTo(map);
  
  searchControl.on('results', function(data){
      results.clearLayers();
      for (var i = data.results.length - 1; i >= 0; i--) {
      results.addLayer(L.marker(data.results[i].latlng));
      }
  });
