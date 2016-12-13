/**
*地理院オーバーレイ用
*/
var head=document.getElementsByTagName('head')[0];
var insertBefore=head.insertBefore;
head.insertBefore=function(newElement,referenceElement){
if(newElement.href && newElement.href.indexOf('https://fonts.googleapis.com/css?family=Roboto') === 0){return}
insertBefore.call(head,newElement,referenceElement);
};
var GSI_NORMAL_ID = '地理院';
var GSI_GAZO_OLD = '写真61-64';
var GSI_GAZO_I = '国土画像74-78';
var GSI_Color = '色別標高図';
var GSI_SAT = '地理院写真';
var geocoder;

/**
*位置予測計算用
*/
var check;
var Radius_long = 6378137.0;
var Henpei = 1/298.257222101;
var Radius_short = Radius_long * (1 - Henpei); // 6356752.314 
var estlat;
var estlng;
function doRad(a){
  return a/180* Math.PI;
}
function radDo(a){
  return a*180/ Math.PI;
}
function xy(x,y){
  return Math.pow(x,y);
}

/**
*Vincentyの式計算
*現在の緯度経度、速さ向きから予測した緯度経度を計算する。
*/
function vincenty(lat1,lng1,alpha12,length){
  var U1 = Math.atan((1 - Henpei) * Math.tan(lat1));
  var sigma1 = Math.atan( Math.tan(U1) / Math.cos(alpha12) );
  var alpha = Math.asin( Math.cos(U1) * Math.sin(alpha12) );
  var u2 =  xy( Math.cos(alpha),2) * (xy(Radius_long,2) -xy(Radius_short,2)) / xy(Radius_short,2);
  var A = 1 + (u2/16384)*(4096 + u2 * (-768 + u2 * (320 - 175 * u2)));
  var B = (u2 / 1024) * (256 + u2 * (-128 + u2 * (74 - 47 * u2)));
  var sigma = length / Radius_short / A;
  do {
    var sigma0 = sigma;
    var dm2 = 2 * sigma1 + sigma;
    var x = Math.cos(sigma) * ( -1 + 2 * xy(Math.cos(dm2),2) ) - B / 6 * Math.cos(dm2) * ( -3 + 4 * xy(Math.sin(dm2),2)) * ( -3 + 4 * xy(Math.cos(dm2),2));
    var dSigma = B * Math.sin(sigma) * ( Math.cos(dm2) + B / 4 * x);
    sigma = length / Radius_short / A + dSigma;
  } while ( Math.abs(sigma0 - sigma)>1e-9 );
 
  var x = Math.sin(U1) * Math.cos(sigma) + Math.cos(U1) * Math.sin(sigma) * Math.cos(alpha12)
  var y = (1 - Henpei) * xy ( xy( Math.sin(alpha),2) + xy( Math.sin(U1) * Math.sin(sigma) - Math.cos(U1) * Math.cos(sigma) * Math.cos(alpha12) ,2) , 1 / 2);
  var lamda = Math.sin(sigma) * Math.sin(alpha12) / (Math.cos(U1) * Math.cos(sigma) - Math.sin(U1) * Math.sin(sigma) * Math.cos(alpha12));
  lamda = Math.atan(lamda);
  var C = (Henpei / 16) * xy(Math.cos(alpha),2) * (4 + Henpei * (4 - 3 * xy(Math.cos(alpha),2)));
  var z = Math.cos(dm2) + C * Math.cos(sigma) * (-1 + 2 * xy(Math.cos(dm2),2) );
  var omega = lamda - (1 - C) * Henpei * Math.sin(alpha) * (sigma + C * Math.sin(sigma) * z);
  estlat = radDo(Math.atan(x / y));
  estlng = radDo(lng1 + omega);
 return ;
}

/**
*Vincentyの式呼び出し
*ラジアン化。速さをm/sに変換。
*/
function calcVincenty(lat1,lng1,alpha12,length){
  lat1 = doRad(lat1);
  lng1=doRad(lng1);
  alpha12=doRad(alpha12);
// xmlの読み込み速度に合わせ3秒更新なので1200で割る。（1秒なら3600）
  length=length*1852/1200;
  
  
  vincenty(lat1,lng1,alpha12,length); 
  return ;
}

/**
*Marker用
*/
var gmarkers = [];
var sidebarList = [];
var sidebarList2;
var gmark = [];
var map;
var kesuhairetu = [];
var hantei = [];
var infoWindow = new google.maps.InfoWindow();
var modeSArray = [];
var marker = [];
var mark = [];
var html = [];

/**
*航空機のSVGファイル
*/
var icon = {
	    path: "M48.049 36.31c.523.169.951-.142.951-.692v-3.494c0-.55-.387-1.23-.859-1.512l-18.282-10.895c-.472-.281-.859-.962-.859-1.511v-12.206c0-.55-.168-1.417-.374-1.928 0 0-1.091-2.708-3-3.01-.204-.036-.411-.062-.619-.062h-.01c-.241-.002-.479.028-.713.072l-.216.048-.328.102c-1.588.53-2.406 2.835-2.406 2.835-.184.519-.334 1.393-.334 1.943v12.206c0 .55-.387 1.23-.859 1.512l-18.282 10.894c-.472.282-.859.962-.859 1.512v3.494c0 .55.428.861.951.691l18.098-5.875c.523-.169.951.142.951.692v9.533c0 .55-.36 1.271-.8 1.601l-2.4 1.802c-.44.33-.8 1.051-.8 1.601v2.337c0 .55.433.876.961.724l6.075-1.745c.528-.152 1.394-.152 1.922 0l6.081 1.745c.528.152.961-.174.961-.724v-2.338c0-.55-.36-1.271-.8-1.601l-2.4-1.802c-.439-.33-.8-1.051-.8-1.601v-9.533c0-.55.428-.861.951-.691l18.098 5.876z",
	    fillColor: '#FFFF00',
	    fillOpacity: 1,
	    anchor: new google.maps.Point(30,30),
	    strokeOpacity:0.8,
	    strokeColor:'#000000',
	    strokeWeight: 1,
	    scale: 0.55,
	    rotation:0,
	}



/**
*タグ作成用
*/
/** google.maps.OverlayViewを継承 */
HelloMarker.prototype = new google.maps.OverlayView();

/* HelloMarkerのコンストラクタ。緯度、軽度をメンバ変数に設定する。 */
function HelloMarker(map, lat, lng ,alt,velocity,callsign) {
  this.lat_ = lat;
  this.lng_ = lng;
  this.altitude = alt;
  this.h_velocity = velocity;
  this.callsign = callsign;
  this.setMap(map);
}
      /* setPositionを呼び出す */
function markerMoveByLatlng( marker, lat, lng ) {
 //       var latlng = marker.getPosition();
        marker.setPosition( lat, lng );
      }

      /* 現在座標で位置を設定する。受け取った座標はfromLatLngToDivPixelでPixelに変換してDivのスタイルに設定。 */
      HelloMarker.prototype.setPosition = function(lat, lng) {
        this.lat_ = lat;
        this.lng_ = lng;
        var point = this.getProjection().fromLatLngToDivPixel( new google.maps.LatLng( this.lat_, this.lng_ ) );
  this.div_.style.left = point.x +20+ 'px';
  this.div_.style.top = point.y -30+ 'px';
      }
      /* 現在座標をLatLng型で返す */
      HelloMarker.prototype.getPosition = function() {
        return new google.maps.LatLng( this.lat_, this.lng_ );
      }



/** drawの実装。div要素を生成 */
HelloMarker.prototype.draw = function() {
  // 何度も呼ばれる可能性があるので、div_が未設定の場合のみ要素生成
  if (!this.div_) {
    // 出力したい要素生成
    this.div_ = document.createElement( "div" );
    this.div_.style.position = "absolute";
    this.div_.style.fontSize = "95%";
    this.div_.style.color = 'red';
    this.div_.style.fontWeight = 'bolder'; 
    this.div_.style.borderBottom = "solid 1px black";
    this.div_.innerHTML = this.callsign+"<BR />"+this.altitude+" "+this.h_velocity;
    // 要素を追加する子を取得
    var panes = this.getPanes();
    // 要素追加
    panes.overlayLayer.appendChild( this.div_ );
  }

  // 緯度、軽度の情報を、Pixel（google.maps.Point）に変換
  var point = this.getProjection().fromLatLngToDivPixel( new google.maps.LatLng( this.lat_, this.lng_ ) );

  // 取得したPixel情報の座標に、要素の位置を設定
  // 指定したピクセルの位置に要素が設定される
  this.div_.style.left = point.x +20+ 'px';
  this.div_.style.top = point.y -30+ 'px';
}

/* 削除処理の実装 */
HelloMarker.prototype.remove = function() {
  if (this.div_) {
    this.div_.parentNode.removeChild(this.div_);
    this.div_ = null;
  }
}


function hokan(i){
    	var pos = marker[i].getPosition();
    	newIcon = marker[i].getIcon();
	var lat1 = pos.lat();
	var lng1 = pos.lng();
	var length = mark[i].h_velocity;
	calcVincenty(lat1,lng1,newIcon.rotation,length);	
}

/**
*modeSArrayに航空機が存在しているか探す
*/
function IsArrayExists(array, value) {
  // 配列の最後までループ
  for (var i =0, len = array.length; i < len; i++) {
    if (value == array[i]) {
      // 存在したらtrueを返す
      return true;
    }
  }
  // 存在しない場合falseを返す
  return false;
}

/**
*航空機のマーカーを作成する
*/
function createMarker(modesaddress,latitude,longitude,altitude,h_velocity,callsign,h_direction,timestamp){
	// タグ用の処理
	if(callsign=="????????"){callsign="unknown";}
	var velocity =   Math.round(h_velocity);
	var alt2 = ( '0000000' + altitude ).slice( -7 );
	var alt = alt2.slice(0, 3);
	var i;
	var onedirection =parseInt(h_direction, 10);
	var len = modeSArray.length;

// modeSArrayに登録されている場合の更新を行う。
  for (i =0, len = modeSArray.length; i < len; i++) {
/**該当機の場合マーカーとタグを更新する*/
    if (modesaddress == modeSArray[i]){
    	newIcon = marker[i].getIcon();
	newIcon.rotation = onedirection;
	marker[i].setIcon(newIcon);
        marker[i].setPosition(new google.maps.LatLng(latitude,longitude));
        mark[i].div_.innerHTML = callsign+"<BR />"+alt+" "+velocity;
	markerMoveByLatlng( mark[i], latitude, longitude) ;
	kesuhairetu[i] = 0;
	html[i] = "TIME:"+timestamp;
	// 該当機でない場合、マーカーとタグを予測位置へ移動させる
    }else if(hantei[i]==1){
    	var pos = marker[i].getPosition();
    	newIcon = marker[i].getIcon();
	var lat1 = pos.lat();
	var lng1 = pos.lng();
	var length = mark[i].h_velocity;
	length = length / modeSArray.length;
    	calcVincenty(lat1,lng1,newIcon.rotation,length);
    	marker[i].setPosition(new google.maps.LatLng(estlat,estlng));
	markerMoveByLatlng( mark[i], estlat, estlng);
    	kesuhairetu[i]++;
    }
/**削除処理。xmlに一定回数連続で存在しない場合削除を行う。*/
    if(kesuhairetu[i]>modeSArray.length*10 ){
    	remove(i);
    	hantei[i]=0;
    }
  }//end of for

/**初めてXMLに入ってきた機体のマーカーを作成する*/
if(! IsArrayExists(modeSArray, modesaddress)) {
marker[i] = new google.maps.Marker({
	map: map, //マーカーを表示する地図名
	position: new google.maps.LatLng(latitude,longitude), //マーカーの表示位置
	icon: icon, //マーカーアイコンの設定
	title: callsign //オンマウスで表示させる文字

});  
	kesuhairetu[i] = 0;
	mark[i] = new HelloMarker( map, latitude,longitude,alt,velocity,callsign );
	gmark.push(mark[i]);
    	var newIcon = marker[i].getIcon();
	newIcon.rotation = onedirection;
	marker[i].setIcon(newIcon);
	
	gmarkers.push(marker[i]);
	modeSArray.push(modesaddress);
	hantei[i] = 1;
//サイドバーに登録するコールサインを作成
	sidebarList[i] = '<a href="javascript:myclick('+ i +')">'+ callsign +'</a><br />';
	  html[i] = "TIME:"+timestamp;
	 google.maps.event.addListener(marker[i], 'click', function() {
		 infoWindow.setContent(html[i]); //情報ウィンドウの内容
		 infoWindow.open(map,marker[i]); //情報ウィンドウを表示
		 map.panTo(marker[i].getPosition()); //マーカーを地図の中心位置に移動
	});
  }


	




}
function myclick(num) { google.maps.event.trigger(marker[num], "click"); }
function zoomInOut(value) { map.setZoom(map.getZoom()+value); }
	
/**マップの初期化*/	
function initialize() {
  geocoder = new google.maps.Geocoder();	
	gmark= new google.maps.MVCArray();	
	gmarkers= new google.maps.MVCArray();
    var mapOptions = {
    		zoom: 10,

    		draggable: true,
    		streetViewControl: false,
    		mapTypeControl: true,
    		scaleControl: false,
    		center: new google.maps.LatLng(34.410605,135.295360),
    
    		mapTypeControlOptions: {mapTypeIds: [
    		 'radarmap',
   		 google.maps.MapTypeId.ROADMAP,
   		 google.maps.MapTypeId.TERRAIN,
   		 google.maps.MapTypeId.SATELLITE,
   		 google.maps.MapTypeId.HYBRID,
   		 GSI_NORMAL_ID,
//  		 GSI_GAZO_OLD,
 //  		 GSI_GAZO_I,
    		GSI_Color,
 //  		 GSI_SAT
 		 ]},
    		scrollwheel: true,//マウスホイールでのズーム
	   
              };

	map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);

 /* スタイル付き地図 */
  var styleOptions = [{
 "elementType": "geometry", "stylers": [ { "color": "#1d2c4d" } ] }, { "elementType": "labels", "stylers": [ { "visibility": "off" } ] }, { "elementType": "labels.text.fill", "stylers": [ { "color": "#8ec3b9" } ] }, { "elementType": "labels.text.stroke", "stylers": [ { "color": "#1a3646" } ] }, { "featureType": "administrative", "elementType": "geometry", "stylers": [ { "visibility": "off" } ] }, { "featureType": "administrative.country", "elementType": "geometry.stroke", "stylers": [ { "color": "#4b6878" } ] }, { "featureType": "administrative.land_parcel", "elementType": "labels.text.fill", "stylers": [ { "color": "#64779e" } ] }, { "featureType": "administrative.neighborhood", "stylers": [ { "visibility": "off" } ] }, { "featureType": "administrative.province", "elementType": "geometry.stroke", "stylers": [ { "color": "#4b6878" } ] }, { "featureType": "landscape", "elementType": "geometry", "stylers": [ { "visibility": "off" } ] }, { "featureType": "landscape.man_made", "elementType": "geometry.stroke", "stylers": [ { "color": "#334e87" } ] }, { "featureType": "landscape.natural", "elementType": "geometry", "stylers": [ { "color": "#023e58" }, { "visibility": "simplified" } ] }, { "featureType": "poi", "stylers": [ { "visibility": "off" } ] }, { "featureType": "poi", "elementType": "geometry", "stylers": [ { "color": "#283d6a" } ] }, { "featureType": "poi", "elementType": "labels.text.fill", "stylers": [ { "color": "#6f9ba5" } ] }, { "featureType": "poi", "elementType": "labels.text.stroke", "stylers": [ { "color": "#1d2c4d" } ] }, { "featureType": "poi.park", "elementType": "geometry.fill", "stylers": [ { "color": "#023e58" } ] }, { "featureType": "poi.park", "elementType": "labels.text.fill", "stylers": [ { "color": "#3C7680" } ] }, { "featureType": "road", "stylers": [ { "visibility": "off" } ] }, { "featureType": "road", "elementType": "geometry", "stylers": [ { "color": "#304a7d" } ] }, { "featureType": "road", "elementType": "labels.icon", "stylers": [ { "visibility": "off" } ] }, { "featureType": "road", "elementType": "labels.text.fill", "stylers": [ { "color": "#98a5be" } ] }, { "featureType": "road", "elementType": "labels.text.stroke", "stylers": [ { "color": "#1d2c4d" } ] }, { "featureType": "road.highway", "elementType": "geometry", "stylers": [ { "color": "#2c6675" } ] }, { "featureType": "road.highway", "elementType": "geometry.stroke", "stylers": [ { "color": "#255763" } ] }, { "featureType": "road.highway", "elementType": "labels.text.fill", "stylers": [ { "color": "#b0d5ce" } ] }, { "featureType": "road.highway", "elementType": "labels.text.stroke", "stylers": [ { "color": "#023e58" } ] }, { "featureType": "transit", "stylers": [ { "visibility": "off" } ] }, { "featureType": "transit", "elementType": "labels.text.fill", "stylers": [ { "color": "#98a5be" } ] }, { "featureType": "transit", "elementType": "labels.text.stroke", "stylers": [ { "color": "#1d2c4d" } ] }, { "featureType": "transit.line", "elementType": "geometry.fill", "stylers": [ { "color": "#283d6a" } ] }, { "featureType": "transit.station", "elementType": "geometry", "stylers": [ { "color": "#3a4762" } ] }, { "featureType": "water", "elementType": "geometry", "stylers": [ { "color": "#0e1626" } ] }, { "featureType": "water", "elementType": "labels.text.fill", "stylers": [ { "color": "#4e6d70" } ]
  }];
  var styledMapOptions = { name: 'radar' }
  var lopanType = new google.maps.StyledMapType(styleOptions, styledMapOptions);
  map.mapTypes.set('radarmap', lopanType);
  map.setMapTypeId('radarmap');
            
 map.mapTypes.set(GSI_NORMAL_ID, {name: 'GSI', tileSize: new google.maps.Size(256, 256), minZoom: 2, maxZoom: 18, 
    getTile: function(coord, zoom, own) {
      var tile = own.createElement('img');
      tile.style.width = '256px';
      tile.style.height = '256px';
      var x = (coord.x % Math.pow(2, zoom)).toString();
      var y = coord.y.toString();
      tile.src = 'https://cyberjapandata.gsi.go.jp/xyz/std/' + zoom + '/' + x + '/' + y + '.png';
      return tile;
    }
  });
  map.mapTypes.set(GSI_GAZO_OLD, {name: '61-64', tileSize: new google.maps.Size(256, 256), minZoom: 15, maxZoom: 17, 
    getTile: function(coord, zoom, own) {
      var img = own.createElement('img'); img.style.width = '256px'; img.style.height = '256px';
      var x = (coord.x % Math.pow(2, zoom)).toString(); var y = coord.y.toString();
      img.src = 'https://cyberjapandata.gsi.go.jp/xyz/ort_old10/' + zoom + '/' + x + '/' + y + '.png';
      img.onerror = function() {this.src = 'tile_not_found.png'}
      return img;
    }
  });
  map.mapTypes.set(GSI_GAZO_I, {name: '74-78', tileSize: new google.maps.Size(256, 256), minZoom: 10, maxZoom: 17, 
    getTile: function(coord, zoom, own) {
      var img = own.createElement('img'); img.style.width = '256px'; img.style.height = '256px';
      var x = (coord.x % Math.pow(2, zoom)).toString(); var y = coord.y.toString();
      img.src = 'https://cyberjapandata.gsi.go.jp/xyz/gazo1/' + zoom + '/' + x + '/' + y + '.jpg';
      img.onerror = function() {this.src = 'tile_not_found.png'}
      return img;
    }
  });
  map.mapTypes.set(GSI_Color, {name: 'color', tileSize: new google.maps.Size(256, 256), minZoom: 5, maxZoom: 15, 
    getTile: function(coord, zoom, own) {
      var img = own.createElement('img'); img.style.width = '256px'; img.style.height = '256px';
      var x = (coord.x % Math.pow(2, zoom)).toString(); var y = coord.y.toString();
      img.src = 'https://cyberjapandata.gsi.go.jp/xyz/relief/' + zoom + '/' + x + '/' + y + '.png';
      img.onerror = function() {this.src = 'tile_not_found.png'}
      return img;
    }
  });
  map.mapTypes.set(GSI_SAT, {name: 'gsi picture', tileSize: new google.maps.Size(256, 256), minZoom: 13, maxZoom: 18, 
    getTile: function(coord, zoom, own) {
      var img = own.createElement('img'); img.style.width = '256px'; img.style.height = '256px';
      var x = (coord.x % Math.pow(2, zoom)).toString(); var y = coord.y.toString();
      img.src = 'https://cyberjapandata.gsi.go.jp/xyz/ort/' + zoom + '/' + x + '/' + y + '.jpg';
      img.onerror = function() {this.src = 'tile_not_found.png'}
      return img;
    }
  });
//地理院出典作成
var marca = document.createElement('div');
  marca.style.fontSize = '11px';
  marca.style.color = '#666';
  marca.style.paddingTop = '2px';
  marca.style.paddingRight = '5px';
  marca.style.paddingBottom = '2px';
  marca.style.paddingLeft = '5px';
  marca.style.marginBottom = '4px';
  marca.style.backgroundColor = '#fff';
  map.controls[google.maps.ControlPosition.BOTTOM_LEFT].push(marca);
  marca.innerHTML = '<a href="http://maps.gsi.go.jp/development/ichiran.html" target="_blank">国土地理院</a>';

  google.maps.event.addListener(map, 'zoom_changed', function() {
    zoomLevel = map.getZoom();
    document.getElementById('zoomer').innerHTML = zoomLevel;
  });	


google.maps.event.addListener(map, "click", function(){ infoWindow.close(); });
window.onload = readXML();
}

/**
*XMLの読み込み
*/
function readXML(){

    var filename = "./Aircraft.xml"; //ファイル名
    filename += "?" + Math.random(); //乱数を付加
	downloadUrl(filename, function(data){

		var xmlDoc = xmlParse(data);
		var markers = xmlDoc.getElementsByTagName("aircraft");
		for (var i = 0; i < markers.length; i++){
			var modesaddress = markers[i].getAttribute("modeSaddress");
			var latitude = markers[i].getAttribute("latitude");
			var longitude = markers[i].getAttribute("longitude");
			var altitude = markers[i].getAttribute("altitude");
			var h_velocity = markers[i].getAttribute("h_velo");
			var callsign2 = markers[i].getAttribute("callsign");
			var timestamp = markers[i].getAttribute("timestamp");
			var h_direction = markers[i].getAttribute("h_dir");
			var callsign = callsign2.replace( /_/g , "" ) ;	
			createMarker(modesaddress,latitude,longitude,altitude,h_velocity,callsign,h_direction,timestamp);
			

		} //end of for

		for ( var i = 0; i < sidebarList.length; i ++ ) {
			if(sidebarList[i]!=null){sidebarList2 += sidebarList[i];}
		}
		document.getElementById("side_bar").innerHTML = sidebarList2;
		sidebarList2 = '';

	}); //end of downloadUrl

//setTimeout(removeMarkers,300);
setTimeout(readXML,3000);
}

/**
*削除の実装
*/
function remove(i){
mark[i]= null;
marker[i]=null;
modeSArray[i]=null;
sidebarList[i]=null;
}

google.maps.event.addDomListener(window, 'load', initialize);

