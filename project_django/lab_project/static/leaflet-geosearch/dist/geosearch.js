var t,e=(t=require("leaflet"))&&"object"==typeof t&&"default"in t?t.default:t;function r(){return(r=Object.assign||function(t){for(var e=1;e<arguments.length;e++){var r=arguments[e];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(t[n]=r[n])}return t}).apply(this,arguments)}function n(t,e){t.prototype=Object.create(e.prototype),t.prototype.constructor=t,t.__proto__=e}function o(t,e){return(o=Object.setPrototypeOf||function(t,e){return t.__proto__=e,t})(t,e)}function i(){if("undefined"==typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"==typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],function(){})),!0}catch(t){return!1}}function s(t,e,r){return(s=i()?Reflect.construct:function(t,e,r){var n=[null];n.push.apply(n,e);var i=new(Function.bind.apply(t,n));return r&&o(i,r.prototype),i}).apply(null,arguments)}function a(t,e,r,n){void 0===e&&(e=""),void 0===n&&(n={});var o=document.createElement(t);return e&&(o.className=e),Object.keys(n).forEach(function(t){if("function"==typeof n[t]){var e=0===t.indexOf("on")?t.substr(2).toLowerCase():t;o.addEventListener(e,n[t])}else"html"===t?o.innerHTML=n[t]:"text"===t?o.innerText=n[t]:o.setAttribute(t,n[t])}),r&&r.appendChild(o),o}function u(t){t.preventDefault(),t.stopPropagation()}var c=function(){for(var t=arguments.length,e=new Array(t),r=0;r<t;r++)e[r]=arguments[r];return e.filter(Boolean).join(" ").trim()};function l(t,e){t&&t.classList&&(Array.isArray(e)?e:[e]).forEach(function(e){t.classList.contains(e)||t.classList.add(e)})}function h(t,e){t&&t.classList&&(Array.isArray(e)?e:[e]).forEach(function(e){t.classList.contains(e)&&t.classList.remove(e)})}var p,f=13,d=40,m=38,v=[f,27,d,m,37,39],y=function(){function t(t){var e=this,r=t.handleSubmit,n=t.searchLabel,o=t.classNames,i=void 0===o?{}:o;this.hasError=!1,this.container=a("div",c("geosearch",i.container)),this.form=a("form",["",i.form].join(" "),this.container,{autocomplete:"none",onClick:u,onDblClick:u,touchStart:u,touchEnd:u}),this.input=a("input",["glass",i.input].join(" "),this.form,{type:"text",placeholder:n||"search",onInput:this.onInput,onKeyUp:function(t){return e.onKeyUp(t)},onKeyPress:function(t){return e.onKeyPress(t)},onFocus:this.onFocus,onBlur:this.onBlur,onClick:function(){e.input.focus(),e.input.dispatchEvent(new Event("focus"))}}),this.handleSubmit=r}var e=t.prototype;return e.onFocus=function(){l(this.form,"active")},e.onBlur=function(){h(this.form,"active")},e.onSubmit=function(t){try{var e=this;return u(t),h(r=e.container,"error"),l(r,"pending"),Promise.resolve(e.handleSubmit({query:e.input.value})).then(function(){h(e.container,"pending")})}catch(t){return Promise.reject(t)}var r},e.onInput=function(){this.hasError&&(h(this.container,"error"),this.hasError=!1)},e.onKeyUp=function(t){27===t.keyCode&&(h(this.container,["pending","active"]),this.input.value="",document.body.focus(),document.body.blur())},e.onKeyPress=function(t){t.keyCode===f&&this.onSubmit(t)},e.setQuery=function(t){this.input.value=t},t}(),g=function(){function t(t){var e=this,r=t.handleClick,n=t.classNames,o=void 0===n?{}:n;this.selected=-1,this.results=[],this.onClick=function(t){if("function"==typeof e.handleClick){var r=t.target;if(r&&e.container.contains(r)&&r.hasAttribute("data-key")){var n=Number(r.getAttribute("data-key"));e.clear(),e.handleClick({result:e.results[n]})}}},this.handleClick=r,this.container=a("div",c("results",o.container)),this.container.addEventListener("click",this.onClick,!0),this.resultItem=a("div",c(o.item))}var e=t.prototype;return e.render=function(t,e){var r=this;void 0===t&&(t=[]),this.clear(),t.forEach(function(t,n){var o=r.resultItem.cloneNode(!0);o.setAttribute("data-key",""+n),o.innerHTML=e({result:t}),r.container.appendChild(o)}),t.length>0&&(l(this.container.parentElement,"open"),l(this.container,"active")),this.results=t},e.select=function(t){return Array.from(this.container.children).forEach(function(e,r){return r===t?l(e,"active"):h(e,"active")}),this.selected=t,this.results[t]},e.count=function(){return this.results?this.results.length:0},e.clear=function(){for(this.selected=-1;this.container.lastChild;)this.container.removeChild(this.container.lastChild);h(this.container.parentElement,"open"),h(this.container,"active")},t}(),b={position:"topleft",style:"button",showMarker:!0,showPopup:!1,popupFormat:function(t){return""+t.result.label},resultFormat:function(t){return""+t.result.label},marker:{icon:e&&e.Icon?new e.Icon.Default:void 0,draggable:!1},maxMarkers:1,maxSuggestions:5,retainZoomLevel:!1,animateZoom:!0,searchLabel:"Enter address",notFoundMessage:"Sorry, that address could not be found.",messageHideDelay:3e3,zoomLevel:18,classNames:{container:"leaflet-bar leaflet-control leaflet-control-geosearch",button:"leaflet-bar-part leaflet-bar-part-single",resetButton:"reset",msgbox:"leaflet-bar message",form:"",input:""},autoComplete:!0,autoCompleteDelay:250,autoClose:!1,keepResult:!1,updateMap:!0},x="Leaflet must be loaded before instantiating the GeoSearch control",E={options:r({},b),classNames:r({},b.classNames),initialize:function(t){var n,o,i,s,u=this;if(!e)throw new Error(x);if(!t.provider)throw new Error("Provider is missing from options");this.options=r({},this.options,{},t),this.classNames=r({},this.classNames,{},t.classNames),this.markers=new e.FeatureGroup,this.classNames.container+=" leaflet-geosearch-"+this.options.style,this.searchElement=new y({searchLabel:this.options.searchLabel,classNames:{container:this.classNames.container,form:this.classNames.form,input:this.classNames.input},handleSubmit:function(t){return u.onSubmit(t)}}),this.button=a("a",this.classNames.button,this.searchElement.container,{title:this.options.searchLabel,href:"#",onClick:function(t){return u.onClick(t)}}),e.DomEvent.disableClickPropagation(this.button),this.resetButton=a("a",this.classNames.resetButton,this.searchElement.form,{text:"×",href:"#",onClick:function(){return u.clearResults(null,!0)}}),e.DomEvent.disableClickPropagation(this.resetButton),this.options.autoComplete&&(this.resultList=new g({handleClick:function(t){var e=t.result;u.searchElement.input.value=e.label,u.onSubmit({query:e.label,data:e})}}),this.searchElement.form.appendChild(this.resultList.container),this.searchElement.input.addEventListener("keyup",(n=function(t){return u.autoSearch(t)},void 0===(o=this.options.autoCompleteDelay)&&(o=250),void 0===i&&(i=!1),function(){for(var t=arguments.length,e=new Array(t),r=0;r<t;r++)e[r]=arguments[r];s&&clearTimeout(s),s=setTimeout(function(){s=null,i||n.apply(void 0,e)},o),i&&!s&&n.apply(void 0,e)}),!0),this.searchElement.input.addEventListener("keydown",function(t){return u.selectResult(t)},!0),this.searchElement.input.addEventListener("keydown",function(t){return u.clearResults(t,!0)},!0)),this.searchElement.form.addEventListener("click",function(t){t.preventDefault()},!1)},onAdd:function(t){var r=this.options,n=r.showMarker,o=r.style;if(this.map=t,n&&this.markers.addTo(t),"bar"===o){var i=t.getContainer().querySelector(".leaflet-control-container");this.container=a("div","leaflet-control-geosearch leaflet-geosearch-bar"),this.container.appendChild(this.searchElement.form),i.appendChild(this.container)}return e.DomEvent.disableClickPropagation(this.searchElement.form),this.searchElement.container},onRemove:function(){var t;return null==(t=this.container)||t.remove(),this},onClick:function(t){t.preventDefault(),t.stopPropagation();var e=this.searchElement,r=e.container,n=e.input;r.classList.contains("active")?(h(r,"active"),this.clearResults()):(l(r,"active"),n.focus())},selectResult:function(t){if(-1!==[f,d,m].indexOf(t.keyCode))if(t.preventDefault(),t.keyCode!==f){var e=this.resultList.count()-1;if(!(e<0)){var r=this.resultList.selected,n=t.keyCode===d?r+1:r-1,o=this.resultList.select(n<0?e:n>e?0:n);this.searchElement.input.value=o.label}}else{var i=this.resultList.select(this.resultList.selected);this.onSubmit({query:this.searchElement.input.value,data:i})}},clearResults:function(t,e){if(void 0===e&&(e=!1),!t||27===t.keyCode){var r=this.options,n=r.autoComplete;!e&&r.keepResult||(this.searchElement.input.value="",this.markers.clearLayers()),n&&this.resultList.clear()}},autoSearch:function(t){try{var e=this;if(v.indexOf(t.keyCode)>-1)return Promise.resolve();var r=t.target.value,n=e.options.provider,o=function(){if(r.length)return Promise.resolve(n.search({query:r})).then(function(t){t=t.slice(0,e.options.maxSuggestions),e.resultList.render(t,e.options.resultFormat)});e.resultList.clear()}();return Promise.resolve(o&&o.then?o.then(function(){}):void 0)}catch(t){return Promise.reject(t)}},onSubmit:function(t){try{var e=this;return Promise.resolve(e.options.provider.search(t)).then(function(r){r&&r.length>0&&e.showResult(r[0],t)})}catch(t){return Promise.reject(t)}},showResult:function(t,e){var r=this.options,n=r.autoClose,o=r.updateMap,i=this.markers.getLayers();i.length>=this.options.maxMarkers&&this.markers.removeLayer(i[0]);var s=this.addMarker(t,e);o&&this.centerMap(t),this.map.fireEvent("geosearch/showlocation",{location:t,marker:s}),n&&this.closeResults()},closeResults:function(){var t=this.searchElement.container;t.classList.contains("active")&&h(t,"active"),this.clearResults()},addMarker:function(t,r){var n=this,o=this.options,i=o.marker,s=o.showPopup,a=o.popupFormat,u=new e.Marker([t.y,t.x],i),c=t.label;return"function"==typeof a&&(c=a({query:r,result:t})),u.bindPopup(c),this.markers.addLayer(u),s&&u.openPopup(),i.draggable&&u.on("dragend",function(t){n.map.fireEvent("geosearch/marker/dragend",{location:u.getLatLng(),event:t})}),u},centerMap:function(t){var r=this.options,n=r.retainZoomLevel,o=r.animateZoom,i=t.bounds?new e.LatLngBounds(t.bounds):new e.LatLng(t.y,t.x).toBounds(10),s=i.isValid()?i:this.markers.getBounds();!n&&i.isValid()&&!t.bounds||n||!i.isValid()?this.map.setView(s.getCenter(),this.getZoom(),{animate:o}):this.map.fitBounds(s,{animate:o})},getZoom:function(){var t=this.options,e=t.zoomLevel;return t.retainZoomLevel?this.map.getZoom():e}};function L(){if(!e)throw new Error(x);for(var t=e.Control.extend(E),r=arguments.length,n=new Array(r),o=0;o<r;o++)n[o]=arguments[o];return s(t,n)}!function(t){t[t.SEARCH=0]="SEARCH",t[t.REVERSE=1]="REVERSE"}(p||(p={}));var k=function(){function t(t){void 0===t&&(t={}),this.options=t}var e=t.prototype;return e.getParamString=function(t){void 0===t&&(t={});var e=r({},this.options.params,{},t);return Object.keys(e).map(function(t){return encodeURIComponent(t)+"="+encodeURIComponent(e[t])}).join("&")},e.getUrl=function(t,e){return t+"?"+this.getParamString(e)},e.search=function(t){try{var e=this,r=e.endpoint({query:t.query,type:p.SEARCH});return Promise.resolve(fetch(r)).then(function(t){return Promise.resolve(t.json()).then(function(t){return e.parse({data:t})})})}catch(t){return Promise.reject(t)}},t}(),w=function(t){function e(){return t.apply(this,arguments)||this}n(e,t);var o=e.prototype;return o.endpoint=function(){return"https://places-dsn.algolia.net/1/places/query"},o.findBestMatchLevelIndex=function(t){var e=t.find(function(t){return"full"===t.matchLevel})||t.find(function(t){return"partial"===t.matchLevel});return e?t.indexOf(e):0},o.getLabel=function(t){var e,r,n,o;return[null==(e=t.locale_names)?void 0:e.default[this.findBestMatchLevelIndex(t._highlightResult.locale_names.default)],null==(r=t.city)?void 0:r.default[this.findBestMatchLevelIndex(t._highlightResult.city.default)],t.administrative[this.findBestMatchLevelIndex(t._highlightResult.administrative)],null==(n=t.postcode)?void 0:n[this.findBestMatchLevelIndex(t._highlightResult.postcode)],null==(o=t.country)?void 0:o.default].filter(Boolean).join(", ")},o.parse=function(t){var e=this;return t.data.hits.map(function(t){return{x:t._geoloc.lng,y:t._geoloc.lat,label:e.getLabel(t),bounds:null,raw:t}})},o.search=function(t){var e=t.query;try{var n=this,o="string"==typeof e?{query:e}:e;return Promise.resolve(fetch(n.endpoint(),{method:"POST",body:JSON.stringify(r({},n.options.params,{},o))})).then(function(t){return Promise.resolve(t.json()).then(function(t){return n.parse({data:t})})})}catch(t){return Promise.reject(t)}},e}(k),C=function(t){function e(){var e;return(e=t.apply(this,arguments)||this).searchUrl="https://dev.virtualearth.net/REST/v1/Locations",e}n(e,t);var r=e.prototype;return r.endpoint=function(t){var e=t.query,r="string"==typeof e?{q:e}:e;return r.jsonp=t.jsonp,this.getUrl(this.searchUrl,r)},r.parse=function(t){return 0===t.data.resourceSets.length?[]:t.data.resourceSets[0].resources.map(function(t){return{x:t.point.coordinates[1],y:t.point.coordinates[0],label:t.address.formattedAddress,bounds:[[t.bbox[0],t.bbox[1]],[t.bbox[2],t.bbox[3]]],raw:t}})},r.search=function(t){var e,r,n,o=t.query;try{var i=this,s="BING_JSONP_CB_"+Date.now();return Promise.resolve((e=i.endpoint({query:o,jsonp:s}),r=s,n=a("script",null,document.body),n.setAttribute("type","text/javascript"),new Promise(function(t){window[r]=function(e){n.remove(),delete window[r],t(e)},n.setAttribute("src",e)}))).then(function(t){return i.parse({data:t})})}catch(t){return Promise.reject(t)}},e}(k),P=function(t){function e(){var e;return(e=t.apply(this,arguments)||this).searchUrl="https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/find",e}n(e,t);var r=e.prototype;return r.endpoint=function(t){var e=t.query,r="string"==typeof e?{text:e}:e;return r.f="json",this.getUrl(this.searchUrl,r)},r.parse=function(t){return t.data.locations.map(function(t){return{x:t.feature.geometry.x,y:t.feature.geometry.y,label:t.name,bounds:[[t.extent.ymin,t.extent.xmin],[t.extent.ymax,t.extent.xmax]],raw:t}})},e}(k),S=function(t){function e(){var e;return(e=t.apply(this,arguments)||this).searchUrl="https://maps.googleapis.com/maps/api/geocode/json",e}n(e,t);var r=e.prototype;return r.endpoint=function(t){var e=t.query;return this.getUrl(this.searchUrl,"string"==typeof e?{address:e}:e)},r.parse=function(t){return t.data.results.map(function(t){return{x:t.geometry.location.lng,y:t.geometry.location.lat,label:t.formatted_address,bounds:[[t.geometry.viewport.southwest.lat,t.geometry.viewport.southwest.lng],[t.geometry.viewport.northeast.lat,t.geometry.viewport.northeast.lng]],raw:t}})},e}(k),R=function(t){function e(){var e;return(e=t.apply(this,arguments)||this).searchUrl="https://geocode.search.hereapi.com/v1/geocode",e}n(e,t);var r=e.prototype;return r.endpoint=function(t){var e=t.query;return this.getUrl(this.searchUrl,"string"==typeof e?{q:e}:e)},r.parse=function(t){return t.data.items.map(function(t){return{x:t.position.lng,y:t.position.lat,label:t.address.label,bounds:null,raw:t}})},e}(k),U=function(t){function e(e){var r;void 0===e&&(e={});var n="https://nominatim.openstreetmap.org";return(r=t.call(this,e)||this).searchUrl=e.searchUrl||n+"/search",r.reverseUrl=e.reverseUrl||n+"/reverse",r}n(e,t);var r=e.prototype;return r.endpoint=function(t){var e=t.query,r=t.type,n="string"==typeof e?{q:e}:e;switch(n.format="json",r){case p.REVERSE:return this.getUrl(this.reverseUrl,n);default:return this.getUrl(this.searchUrl,n)}},r.parse=function(t){return(Array.isArray(t.data)?t.data:[t.data]).map(function(t){return{x:Number(t.lon),y:Number(t.lat),label:t.display_name,bounds:[[parseFloat(t.boundingbox[0]),parseFloat(t.boundingbox[2])],[parseFloat(t.boundingbox[1]),parseFloat(t.boundingbox[3])]],raw:t}})},e}(k),j=function(t){function e(e){return t.call(this,r({},e,{searchUrl:"https://locationiq.org/v1/search.php",reverseUrl:"https://locationiq.org/v1/reverse.php"}))||this}return n(e,t),e}(U),q=function(t){function e(){var e;return(e=t.apply(this,arguments)||this).searchUrl="https://api.opencagedata.com/geocode/v1/json",e}n(e,t);var r=e.prototype;return r.endpoint=function(t){var e=t.query,r="string"==typeof e?{q:e}:e;return r.format="json",this.getUrl(this.searchUrl,r)},r.parse=function(t){return t.data.results.map(function(t){return{x:t.geometry.lng,y:t.geometry.lat,label:t.formatted,bounds:[[t.bounds.southwest.lat,t.bounds.southwest.lng],[t.bounds.northeast.lat,t.bounds.northeast.lng]],raw:t}})},r.search=function(e){try{return Promise.resolve(e.query.length<2?[]:t.prototype.search.call(this,e))}catch(t){return Promise.reject(t)}},e}(k),N=function(t){function e(e){var r;return void 0===e&&(e={}),(r=t.call(this,e)||this).searchUrl=e.searchUrl||"https://a.tiles.mapbox.com/v4/geocode/mapbox.places/",r}n(e,t);var r=e.prototype;return r.endpoint=function(t){return this.getUrl(""+this.searchUrl+t.query+".json")},r.parse=function(t){return(Array.isArray(t.data.features)?t.data.features:[]).map(function(t){var e=null;return t.bbox&&(e=[[parseFloat(t.bbox[1]),parseFloat(t.bbox[0])],[parseFloat(t.bbox[3]),parseFloat(t.bbox[2])]]),{x:Number(t.center[0]),y:Number(t.center[1]),label:t.place_name?t.place_name:t.text,bounds:e,raw:t}})},e}(k);exports.AlgoliaProvider=w,exports.BingProvider=C,exports.EsriProvider=P,exports.GeoSearchControl=L,exports.GoogleProvider=S,exports.HereProvider=R,exports.JsonProvider=k,exports.LocationIQProvider=j,exports.MapBoxProvider=N,exports.OpenCageProvider=q,exports.OpenStreetMapProvider=U,exports.SearchControl=L,exports.SearchElement=y;
//# sourceMappingURL=geosearch.js.map
