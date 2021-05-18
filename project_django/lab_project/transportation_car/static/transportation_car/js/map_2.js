
mapboxgl.accessToken = 'pk.eyJ1Ijoia29zdGlhc2tvcGVjIiwiYSI6ImNrbzNma2VvMTE4dTYydm12OWdqYWV4ZmUifQ.Rr5Kk6gDkwHK5MervsubUg';
  var map = new mapboxgl.Map({
      container: 'mapid',
      style: 'mapbox://styles/mapbox/streets-v11',
      center: [-79.4512, 43.6568],
      zoom: 13
});
  


// var start_location = document.getElementById('start_loc');
// var start_location = document.getElementById('delivery_loc');

var start_location = document.getElementById('start_loc');
var delivery_location = document.getElementById('delivery_loc');
var distance = "0";

var punktA_1 = document.getElementById('punktA_1');
var punktB_1 = document.getElementById('punktB_1');
var punktA_2 = document.getElementById('punktA_2');
var punktB_2 = document.getElementById('punktB_2');

var distanceContainer = document.getElementById('distance');



// $start_location.value("Nowy York");
// $delivery_location.value("Molodzieczno");

var marker2 = new mapboxgl.Marker();
var marker1 = new mapboxgl.Marker();

var canvas = map.getCanvasContainer();
// var start = [-122.662323, 45.523751];

var geocoder = new MapboxGeocoder({
    accessToken: mapboxgl.accessToken,
    marker:{
        color:'green'
    },
    language: 'pl-PL',
    mapboxgl: mapboxgl,
});

var geocoder1 = new MapboxGeocoder({
    accessToken: mapboxgl.accessToken,
    marker: {
        color: 'red'
    },
    language: 'pl-PL',
    mapboxgl: mapboxgl
});



var start = null;
var end = null;

geocoder.on('result', function(r) {
    start = r.result.center;
    start_location = r.result.place_name;
    $('#punktA_1').val(r.result.center[0])
    $('#punktA_2').val(r.result.center[1])
    $('#start_loc').val(start_location);
    f(start,end);

})

geocoder1.on('result', function(r) {
    console.log(r.result.center);
    // console.log(ok1);
    end = r.result.center;
    delivery_location = r.result.place_name;
    $('#punktB_1').val(r.result.center[0])
    $('#punktB_2').val(r.result.center[1])
    $('#delivery_loc').val(delivery_location);
    // fun(ok1,ok2);
   
   
      f(start,end);
    
   
})

function fun(start,end) {
  var coordsObj = end;
  canvas.style.cursor = '';
  var coords = Object.keys(coordsObj).map(function(key) {
    return coordsObj[key];
  });
  // var layer = L.geoJson(getRoute(start,coords)).addTo(map);
  //map.removeLayer(layer);
  getRoute(start,coords);
  getRoute(start,coords);
}


// const mapboxDirections = new MapboxDirections({
//     accessToken: mapboxgl.accessToken,
//     unit: 'metric',
//     profile: 'driving',
//     controls: {
//          inputs: false,
//          instructions: false
//        }

// });

// map.addControl(mapboxDirections);

function f(start1,end1){
  if(start1 != null && end1 !=null){
    // let startPosition = ok1;
    // let finalDestination = ok2;
    // mapboxDirections.setOrigin(startPosition);
    // mapboxDirections.setDestination(finalDestination);
    fun(start1,end1);
    

    // if(geocoder.lastSelected != null){
    //   if(geocoder1.lastSelected != null){
    //     fun(start,end);
    //   }
    //   else{
    //     // fun(start,start);
    //   }
    // }



    
    if(start != null || end !=null){
    map.fitBounds([start,end],{
        padding: 50
    }); 
  }
  }
}
 

document.getElementById('geocoder').appendChild(geocoder.onAdd(map));
document.getElementById('geocoder1').appendChild(geocoder1.onAdd(map));



function getRoute(start,end) {
   
    console.log("yes");

    // var start = ok1;
    var url = 'https://api.mapbox.com/directions/v5/mapbox/driving-traffic/' + start[0] + ',' + start[1] + ';' + end[0] + ',' + end[1] + '?steps=true&geometries=geojson&access_token=' + mapboxgl.accessToken;
  
    // make an XHR request https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest
    var req = new XMLHttpRequest();
    req.open('GET', url, true);
    req.onload = function() {
      var json = JSON.parse(req.response);
      var data = json.routes[0];
      var route = data.geometry.coordinates;
      var geojson = {
        type: 'Feature',
        properties: {},
        geometry: {
          type: 'LineString',
          coordinates: route
        }
      };

      distance = turf.length(geojson).toLocaleString();
      console.log(distance);
      // distanceContainer.appendChild("value");
      $("#distance").text("Dystans: " + distance + "km");


      // if the route already exists on the map, reset it using setData
      if (map.getSource('route')) {
        map.getSource('route').setData(geojson);
      } else { // otherwise, make a new request
        map.addLayer({
          id: 'route',
          type: 'line',
          source: {
            type: 'geojson',
            data: {
              type: 'Feature',
              properties: {},
              geometry: {
                type: 'LineString',
                coordinates: geojson
              }
            }
          },
          layout: {
            'line-join': 'round',
            'line-cap': 'round'
          },
          paint: {
            'line-color': '#3887be',
            'line-width': 5,
            'line-opacity': 0.75
          }
        });


    //  var json = [{
    //       id: 'route',
    //       type: 'line',
    //       source: {
    //         type: 'geojson',
    //         data: {
    //           type: 'Feature',
    //           properties: {},
    //           geometry: {
    //             type: 'LineString',
    //             coordinates: geojson
    //           }
    //         }
    //       },
    //       layout: {
    //         'line-join': 'round',
    //         'line-cap': 'round'
    //       },
    //       paint: {
    //         'line-color': '#3887be',
    //         'line-width': 5,
    //         'line-opacity': 0.75
    //       }
    //     }];

    //     L.geoJson(json).addTo(map);
      }
      // add turn instructions here at the end
    };
    req.send();
  
  }
  
  // map.on('load', function() {
  //   // make an initial directions request that
  //   // starts and ends at the same location
  //   getRoute(start);
  
  //   // Add starting point to the map
  //   map.addLayer({
  //     id: 'point',
  //     type: 'circle',
  //     source: {
  //       type: 'geojson',
  //       data: {
  //         type: 'FeatureCollection',
  //         features: [{
  //           type: 'Feature',
  //           properties: {},
  //           geometry: {
  //             type: 'Point',
  //             coordinates: start
  //           }
  //         }
  //         ]
  //       }
  //     },
  //     paint: {
  //       'circle-radius': 10,
  //       'circle-color': '#3887be'
  //     }
  //   });
  //   // this is where the code from the next step will go
  // });

  

  // map.on('click', function(e) {
  //   var coordsObj = ok2;
  //   canvas.style.cursor = '';
  //   var coords = Object.keys(coordsObj).map(function(key) {
  //     return coordsObj[key];
  //   });
  //   getRoute(coords);
  // });


  
// var buttom = $(".mapboxgl-ctrl-geocoder--button");

// buttom.onclick = function(){
//   console.log("ddddddddddddddddddd");
// }

$(".mapboxgl-ctrl-geocoder--button").click(function(){
  if(geocoder1.lastSelected == null){
    f(start,start);
    end = null;
  }
  if(geocoder.lastSelected == null){
    f(end,end);
    start = null;
  }
});

// $(".mapboxgl-ctrl-geocoder--input").change(function(){
//   if(geocoder1.lastSelected == null){
//     f(start,start);
//     end = null;
//   }
//   if(geocoder.lastSelected == null){
//     f(end,end);
//     start = null;
//   }
// });



$("#sub").click(function(){
  console.log("spacex");
  $('#start_loc').val(start_location);
  $('#delivery_loc').val(delivery_location);
});

