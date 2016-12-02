0
var gmarkers = [];
var sidebarList = '';
var gmark = [];
var map;
var h_direction = 0;
var infoWindow = new google.maps.InfoWindow();


var icon = {
	    path: "M48.049 36.31c.523.169.951-.142.951-.692v-3.494c0-.55-.387-1.23-.859-1.512l-18.282-10.895c-.472-.281-.859-.962-.859-1.511v-12.206c0-.55-.168-1.417-.374-1.928 0 0-1.091-2.708-3-3.01-.204-.036-.411-.062-.619-.062h-.01c-.241-.002-.479.028-.713.072l-.216.048-.328.102c-1.588.53-2.406 2.835-2.406 2.835-.184.519-.334 1.393-.334 1.943v12.206c0 .55-.387 1.23-.859 1.512l-18.282 10.894c-.472.282-.859.962-.859 1.512v3.494c0 .55.428.861.951.691l18.098-5.875c.523-.169.951.142.951.692v9.533c0 .55-.36 1.271-.8 1.601l-2.4 1.802c-.44.33-.8 1.051-.8 1.601v2.337c0 .55.433.876.961.724l6.075-1.745c.528-.152 1.394-.152 1.922 0l6.081 1.745c.528.152.961-.174.961-.724v-2.338c0-.55-.36-1.271-.8-1.601l-2.4-1.802c-.439-.33-.8-1.051-.8-1.601v-9.533c0-.55.428-.861.951-.691l18.098 5.876z",
	    fillColor: '#FFFF00',
	    fillOpacity: 1,
	    anchor: new google.maps.Point(30,30),
	    strokeOpacity:0.8,
	    strokeColor:'#000000',
	    strokeWeight: 1,
	    scale: 0.55,
	    rotation:h_direction,
	}




/* HelloMarker�̃R���X�g���N�^�B�ܓx�A�y�x�������o�ϐ��ɐݒ肷��B */
function HelloMarker(map, lat, lng ,alt,velocity,callsign) {
  this.lat_ = lat;
  this.lng_ = lng;
  this.altitude = alt;
  this.h_velocity = velocity;
  this.callsign = callsign;
  this.setMap(map);
}

/** google.maps.OverlayView���p�� */
HelloMarker.prototype = new google.maps.OverlayView();

/** draw�̎����Bdiv�v�f�𐶐� */
HelloMarker.prototype.draw = function() {
  // ���x���Ă΂��\��������̂ŁAdiv_�����ݒ�̏ꍇ�̂ݗv�f����
  if (!this.div_) {
    // �o�͂������v�f����
    this.div_ = document.createElement( "div" );
    this.div_.style.position = "absolute";
    this.div_.style.fontSize = "90%";
    this.div_.style.color = 'red';
    this.div_.style.borderBottom = "solid 1px black";
    this.div_.innerHTML = this.callsign+"<BR />"+this.altitude+" "+this.h_velocity;
    // �v�f��ǉ�����q���擾
    var panes = this.getPanes();
    // �v�f�ǉ�
    panes.overlayLayer.appendChild( this.div_ );
  }

  // �ܓx�A�y�x�̏����APixel�igoogle.maps.Point�j�ɕϊ�
  var point = this.getProjection().fromLatLngToDivPixel( new google.maps.LatLng( this.lat_, this.lng_ ) );

  // �擾����Pixel���̍��W�ɁA�v�f�̈ʒu��ݒ�
  // �����35.5, 140.0�̈ʒu������̍��W�Ƃ���ʒu�ɗv�f���ݒ肳���
  this.div_.style.left = point.x +20+ 'px';
  this.div_.style.top = point.y -30+ 'px';
}

/* �폜�����̎��� */
HelloMarker.prototype.remove = function() {
  if (this.div_) {
    this.div_.parentNode.removeChild(this.div_);
    this.div_ = null;
  }
}


function createMarker(modesaddress,latitude,longitude,altitude,h_velocity,callsign,h_direction){
	var velocity =   Math.round(h_velocity);
	var alt2 = ( '00000' + altitude ).slice( -5 );
	var alt = alt2.slice(0, 3);
var marker = new google.maps.Marker({
	map: map, //�}�[�J�[��\������n�}��
	position: new google.maps.LatLng(latitude,longitude), //�}�[�J�[�̕\���ʒu
	icon: icon, //�}�[�J�[�A�C�R���̐ݒ�
	title: callsign //�I���}�E�X�ŕ\�������镶��
});

	gmarkers.push(marker);
	
	var newIcon = marker.getIcon()
	newIcon.rotation =Math.round(h_direction) ;
	marker.setIcon(newIcon);

	 var html = "CALL SIGN:"+callsign+"<BR />ALTITUDE:"+altitude+"<BR />VELOCITY"+h_velocity;
	 google.maps.event.addListener(marker, 'click', function() {
		 infoWindow.setContent(html); //���E�B���h�E�̓��e
		 infoWindow.open(map,marker); //���E�B���h�E��\��
		 map.panTo(point); //�}�[�J�[��n�}�̒��S�ʒu�Ɉړ�
	});
		var mark = new HelloMarker( map, latitude,longitude,alt,velocity,callsign );
		gmark.push(mark);
}
function myclick(num) { google.maps.event.trigger(gmarkers[num], "click"); }
function zoomInOut(value) { map.setZoom(map.getZoom()+value); }
	
	
function initialize() {
	
	gmarkers= new google.maps.MVCArray();
    var mapOptions = {
    		zoom: 10,

    		draggable: true,
    		streetViewControl: false,
    		mapTypeControl: false,
    		scaleControl: false,
    		center: new google.maps.LatLng(34.410605,135.295360),
    		mapTypeId: google.maps.MapTypeId.ROADMAP,
    		scrollwheel: true,//�}�E�X�z�C�[���ł̃Y�[��

/*
   	styles: [
    	         {
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#1d2c4d"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "elementType": "labels",
    	        	    "stylers": [
    	        	      {
    	        	        "visibility": "off"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "elementType": "labels.text.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#8ec3b9"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "elementType": "labels.text.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#1a3646"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "administrative",
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "visibility": "off"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "administrative.country",
    	        	    "elementType": "geometry.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#4b6878"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "administrative.land_parcel",
    	        	    "elementType": "labels.text.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#64779e"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "administrative.neighborhood",
    	        	    "stylers": [
    	        	      {
    	        	        "visibility": "off"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "administrative.province",
    	        	    "elementType": "geometry.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#4b6878"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "landscape",
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "visibility": "off"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "landscape.man_made",
    	        	    "elementType": "geometry.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#334e87"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "landscape.natural",
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#023e58"
    	        	      },
    	        	      {
    	        	        "visibility": "simplified"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "poi",
    	        	    "stylers": [
    	        	      {
    	        	        "visibility": "off"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "poi",
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#283d6a"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "poi",
    	        	    "elementType": "labels.text.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#6f9ba5"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "poi",
    	        	    "elementType": "labels.text.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#1d2c4d"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "poi.park",
    	        	    "elementType": "geometry.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#023e58"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "poi.park",
    	        	    "elementType": "labels.text.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#3C7680"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road",
    	        	    "stylers": [
    	        	      {
    	        	        "visibility": "off"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road",
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#304a7d"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road",
    	        	    "elementType": "labels.icon",
    	        	    "stylers": [
    	        	      {
    	        	        "visibility": "off"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road",
    	        	    "elementType": "labels.text.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#98a5be"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road",
    	        	    "elementType": "labels.text.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#1d2c4d"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road.highway",
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#2c6675"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road.highway",
    	        	    "elementType": "geometry.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#255763"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road.highway",
    	        	    "elementType": "labels.text.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#b0d5ce"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "road.highway",
    	        	    "elementType": "labels.text.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#023e58"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "transit",
    	        	    "stylers": [
    	        	      {
    	        	        "visibility": "off"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "transit",
    	        	    "elementType": "labels.text.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#98a5be"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "transit",
    	        	    "elementType": "labels.text.stroke",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#1d2c4d"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "transit.line",
    	        	    "elementType": "geometry.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#283d6a"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "transit.station",
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#3a4762"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "water",
    	        	    "elementType": "geometry",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#0e1626"
    	        	      }
    	        	    ]
    	        	  },
    	        	  {
    	        	    "featureType": "water",
    	        	    "elementType": "labels.text.fill",
    	        	    "stylers": [
    	        	      {
    	        	        "color": "#4e6d70"
    	        	      }
    	        	    ]
    	        	  }
    	        	]
    	      */


              };

	map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
            
	


google.maps.event.addListener(map, "click", function(){ infoWindow.close(); });
window.onload = readXML();
}
function readXML(){

    var filename = "./Aircraft.xml"; //�t�@�C����
    filename += "?" + Math.random(); //������t��
	downloadUrl(filename, function(data){

		var xmlDoc = xmlParse(data);
		var markers = xmlDoc.getElementsByTagName("aircraft");
		for (var i = 0; i < markers.length; i++){
			var modesaddress = markers[i].getAttribute("modeSaddress");
			var latitude = markers[i].getAttribute("latitude");
			var longitude = markers[i].getAttribute("longitude");
			var altitude = markers[i].getAttribute("altitude");
			var h_velocity = markers[i].getAttribute("h_velo");
			var callsign = markers[i].getAttribute("callsign");
			h_direction = markers[i].getAttribute("h_dir");
			
			createMarker(modesaddress,latitude,longitude,altitude,h_velocity,callsign,h_direction);

			sidebarList += '<a href="javascript:myclick('+ i +')">'+ callsign +'</a><br />';
		} //end of for


		document.getElementById("side_bar").innerHTML = sidebarList;


	}); //end of downloadUrl

setTimeout(removeMarkers,5000);
setTimeout(readXML,5000);
}

function removeMarkers(){
	gmarkers.forEach(function(marker, idx){
 	marker.setMap(null);
	});
	gmark.forEach(function(marker, idx){
 	marker.setMap(null);
	});
	sidebarList = '';
}

google.maps.event.addDomListener(window, 'load', initialize);

