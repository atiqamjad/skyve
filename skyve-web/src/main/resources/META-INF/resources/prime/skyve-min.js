SKYVE={};SKYVE.Util=function(){var a=window.location+"";a=a.substring(0,a.lastIndexOf("/")+1);var b=null;return{customer:null,v:null,googleMapsV3ApiKey:null,ckEditorConfigFileUrl:null,CONTEXT_URL:a,loadJS:function(d,f){var c=document.createElement("SCRIPT");c.type="text/javascript";c.src=d;if(f!=null){if(c.readyState){c.onreadystatechange=function(){if(c.readyState=="loaded"||c.readyState=="complete"){c.onreadystatechange=null;f()}}}else{c.onload=f}}var e=document.getElementsByTagName("HEAD");if(e[0]!=null){e[0].appendChild(c)}},scatterGMap:function(u,C,r,g){if(!b){b=new Wkt.Wkt()}var n=C.items;if(g){for(var k in u._objects){if(!n.containsProperty("bizId",k)){var A=u._objects[k];for(var w=0,s=A.overlays.length;w<s;w++){A.overlays[w].setMap(null);A.overlays[w]=null}delete A.overlays;delete u._objects[k]}}}else{for(var k in u._objects){var A=u._objects[k];for(var w=0,s=A.overlays.length;w<s;w++){A.overlays[w].setMap(null);A.overlays[w]=null}delete A.overlays;delete u._objects[k]}}for(var w=0,s=n.length;w<s;w++){var z=n[w];var D=u._objects[z.bizId];if(D){var t=(D.overlays.length==z.features.length);if(t){for(var v=0,q=D.overlays.length;v<q;v++){if(D.overlays[v].geometry!==z.features[v].geometry){t=false;break}}}if(!t){for(var v=0,q=D.overlays.length;v<q;v++){D.overlays[v].setMap(null);D.overlays[v]=null}delete D.overlays;delete u._objects[k];D=null}}if(D){}else{D={overlays:[]};for(var v=0,q=z.features.length;v<q;v++){var f=z.features[v];try{b.read(f.geometry)}catch(y){if(y.name==="WKTError"){alert(f.geometry+" is invalid WKT.");continue}}var d={editable:f.editable};if(f.strokeColour){d.strokeColor=f.strokeColour}if(f.fillColour){d.fillColor=f.fillColour}if(f.fillOpacity){d.fillOpacity=f.fillOpacity}if(f.iconDynamicImageName){d.icon={url:SKYVE.Util.CONTEXT_URL+"resources?_n="+f.iconDynamicImageName+"&_doc="+C._doc};if(f.iconAnchorX&&f.iconAnchorY){d.icon.anchor=new google.maps.Point(f.iconAnchorX,f.iconAnchorY);d.icon.origin=new google.maps.Point(0,0)}}var x=b.toObject(d);D.overlays.push(x);x.setMap(u.webmap);if(f.zoomable){x.bizId=z.bizId;x.geometry=f.geometry;x.fromTimestamp=z.fromTimestamp;x.toTimestamp=z.toTimestamp;x.mod=z.moduleName;x.doc=z.documentName;x.infoMarkup=z.infoMarkup;google.maps.event.addListener(x,"click",function(e){u.click(this,e)})}}u._objects[z.bizId]=D}}if(r){var h=new google.maps.LatLngBounds();var c=false;for(var p in u._objects){c=true;var D=u._objects[p];var B=D.overlays;for(var w=0,s=B.length;w<s;w++){var x=B[w];if(x.getPath){var o=x.getPath();for(var v=0,q=o.getLength();v<q;v++){h.extend(o.getAt(v))}}else{if(x.getPosition){h.extend(x.getPosition())}}}}if(c){if(h.getNorthEast().equals(h.getSouthWest())){if(u.webmap.getZoom()<15){u.webmap.setZoom(15)}if(x.getPosition!==undefined&&typeof x.getPosition==="function"){u.webmap.setCenter(h.getNorthEast())}}else{u.webmap.fitBounds(h)}}}},scatterGMapValue:function(m,g){if(!b){b=new Wkt.Wkt()}if(!g){return}try{b.read(g)}catch(k){if(k.name==="WKTError"){alert("The WKT string is invalid.");return}}var j=b.toObject(m.webmap.defaults);if(b.type==="polygon"||b.type==="linestring"){}else{if(j.setEditable){j.setEditable(false)}}if(Wkt.isArray(j)){for(d in j){if(j.hasOwnProperty(d)&&(!Wkt.isArray(j[d]))){j[d].setMap(m.webmap);m._overlays.push(j[d])}}}else{j.setMap(m.webmap);m._overlays.push(j)}if(j.getBounds!==undefined&&typeof j.getBounds==="function"){m.webmap.fitBounds(j.getBounds())}else{if(j.getPath!==undefined&&typeof j.getPath==="function"){var f=new google.maps.LatLngBounds();var h=j.getPath();for(var d=0,c=h.getLength();d<c;d++){f.extend(h.getAt(d))}m.webmap.fitBounds(f)}else{if(m.webmap.getZoom()<15){m.webmap.setZoom(15)}if(j.getPosition!==undefined&&typeof j.getPosition==="function"){m.webmap.setCenter(j.getPosition())}}}},clearGMap:function(e){for(var d=0,c=e._overlays.length;d<c;d++){e._overlays[d].setMap(null)}e._overlays.length=0},gmapDrawingModes:function(d){var c=null;if(d=="point"){c=[google.maps.drawing.OverlayType.MARKER]}else{if(d=="line"){c=[google.maps.drawing.OverlayType.POLYLINE]}else{if(d=="polygon"){c=[google.maps.drawing.OverlayType.POLYGON,google.maps.drawing.OverlayType.RECTANGLE]}else{if(d=="pointAndLine"){c=[google.maps.drawing.OverlayType.MARKER,google.maps.drawing.OverlayType.POLYLINE]}else{if(d=="pointAndPolygon"){c=[google.maps.drawing.OverlayType.MARKER,google.maps.drawing.OverlayType.POLYGON,google.maps.drawing.OverlayType.RECTANGLE]}else{if(d=="lineAndPolygon"){c=[google.maps.drawing.OverlayType.POLYLINE,google.maps.drawing.OverlayType.POLYGON,google.maps.drawing.OverlayType.RECTANGLE]}else{c=[google.maps.drawing.OverlayType.MARKER,google.maps.drawing.OverlayType.POLYLINE,google.maps.drawing.OverlayType.POLYGON,google.maps.drawing.OverlayType.RECTANGLE]}}}}}}return c}}}();SKYVE.PF=function(){var a=false;return{getById:function(b){return $(PrimeFaces.escapeClientId(b))},getByIdEndsWith:function(b){return $('[id$="'+b+'"]')},contentOverlayOnShow:function(c,b){SKYVE.PF.getById(c+"_iframe").attr("src",b)},contentOverlayOnHide:function(b){SKYVE.PF.getById(b+"_iframe").attr("src","")},afterContentUpload:function(c,f,e,d){top.$('[id$="_'+c+'"]').val(f);var b="content?_n="+f+"&_doc="+e+"&_b="+c.replace(/\_/g,".");top.$('[id$="_'+c+'_link"]').attr("href",b).text(d);top.$('[id$="_'+c+'_image"]').attr("src",b);top.PF(c+"Overlay").hide()},clearContentImage:function(b){$('[id$="_'+b+'"]').val("");$('[id$="_'+b+'_image"]').attr("src","images/blank.gif")},clearContentLink:function(b){$('[id$="_'+b+'"]').val("");$('[id$="_'+b+'_link"]').attr("href","javascript:void(0)").text("<Empty>")},getTextElement:function(b){return SKYVE.PF.getById(b)},getTextValue:function(b){return SKYVE.PF.getTextElement(b).val()},setTextValue:function(c,b){SKYVE.PF.getTextElement(c).val(b)},getPasswordElement:function(b){return SKYVE.PF.getById(b+"password")},getPasswordValue:function(b){return SKYVE.PF.getPasswordElement(b).val()},setPasswordValue:function(c,b){SKYVE.PF.getPasswordElement(c).val(b)},getComboElement:function(b){return SKYVE.PF.getById(b)},getLookupElement:function(b){return SKYVE.PF.getById(b)},getLookupValue:function(b){return SKYVE.PF.getById(b+"_hinput").val()},setLookupValue:function(c,b){SKYVE.PF.getById(c+"_hinput").val(b)},getLookupDescription:function(b){return SKYVE.PF.getById(b+"_input").val()},setLookupDescription:function(c,b){SKYVE.PF.getById(c+"_input").val(b)},getCheckboxElement:function(b){return SKYVE.PF.getById(b)},getCheckboxValue:function(c){var b=SKYVE.PF.getById(c+"_input").val();if(b=="0"){return null}else{if(b=="1"){return true}else{if(b=="2"){return false}else{return SKYVE.PF.getById(c+"_input").is(":checked")}}}},setCheckboxValue:function(f,b){SKYVE.PF.getById(f+"_input").prop("checked",b);var e=SKYVE.PF.getById(f);var d=e.find(".ui-chkbox-box");var c=d.find(".ui-chkbox-icon");if(b){d.addClass("ui-state-active");c.addClass("ui-icon ui-icon-check")}else{d.removeClass("ui-state-active");c.removeClass("ui-icon ui-icon-check")}},toggleFilters:function(b){var e="hiddenFilter";var d=$('[id$="'+b+'"]');if(d!=null){var c=function(){var f=$(this);if(f.hasClass(e)){f.removeClass(e)}else{f.addClass(e)}};d.find(".ui-filter-column").each(c);d.find(".ui-column-customfilter").each(c)}},onPushMessage:function(d){var f=[];for(var e=0,c=d.length;e<c;e++){var b=d[e];if(b.type=="g"){f.push({severity:b.severity,summary:b.message})}else{if(b.type=="m"){alert(b.message)}else{if(b.type=="r"){pushRerender()}else{if(b.type=="j"){window[b.method](b.argument)}}}}}if(f.length>0){PrimeFaces.cw("Growl","pushGrowl",{id:"pushGrowl",widgetVar:"pushGrowl",life:6000,sticky:false,msgs:f})}},gmap:function(b){if(a){setTimeout(function(){SKYVE.PF.gmap(b)},100)}else{if(window.google&&window.google.maps&&window.SKYVE.BizMapPicker){if(b.queryName||b.modelName){return SKYVE.BizMap.create(b)}return SKYVE.BizMapPicker.create(b)}else{a=true;SKYVE.Util.loadJS("wicket/wicket.js?v="+SKYVE.Util.v,function(){SKYVE.Util.loadJS("wicket/wicket-gmap3.js?v="+SKYVE.Util.v,function(){var c="https://maps.googleapis.com/maps/api/js?v=3&libraries=drawing";if(SKYVE.Util.googleMapsV3ApiKey){c+="&key="+SKYVE.Util.googleMapsV3ApiKey}SKYVE.Util.loadJS(c,function(){SKYVE.Util.loadJS("prime/skyve-gmap-min.js?v="+SKYVE.Util.v,function(){a=false;if(b.queryName||b.modelName){return SKYVE.BizMap.create(b)}return SKYVE.BizMapPicker.create(b)})})})})}}}}}();