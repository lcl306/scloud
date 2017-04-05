/////////////////////////////////////////////////////////////////marker
var markers = [];
	
var carIcon = new BMap.Icon("../../img/car.png", new BMap.Size(34, 24), {});  
var stationIcon = new BMap.Icon("../../img/station.png", new BMap.Size(26, 24), {});  
	
function addMarker(lng,lat,icon,win){
	var marker = new BMap.Marker(new BMap.Point(lng,lat),{icon:icon});
	if(win) markerWin(marker, win);
	map.addOverlay(marker);
	markers.push(marker);
	return marker;
}
	
function delMarkers(){
	$(markers).each(function(){
		map.removeOverlay(this);
	});
	markers.length = 0;  
}
	
function markerWin(marker, win){
	var winHandler = function(e){
		if(e.target===e.currentTarget){
			marker.openInfoWindow(win);
			e.domEvent.stopPropagation();  
		}
	};
	marker.addEventListener("click", winHandler);
}

////////////////////////////////////////////////////////point
var points = [];
	
function addPoint(lng,lat){
	var p = new BMap.Point(lng,lat);
	map.addOverlay(p);
	points.push(p);
	return p;
}
	
function delPoints(){
	$(points).each(function(){
		map.removeOverlay(this);
	});
	points.length = 0;  
}

/////////////////////////////////////////////////////////route
function Driving(map){
	this.drv = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});;
	this.route = function(start, end, ways){
		var waypoints = [];
		$(ways).each(function(){
			waypoints.push(addPoint(this.lng, this.lat));
		});
		this.drv.search(addPoint(start.lng, start.lat), addPoint(end.lng, end.lat), {waypoints:waypoints});
	};
}