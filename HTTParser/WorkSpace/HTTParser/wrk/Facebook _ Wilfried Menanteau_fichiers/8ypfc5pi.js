/*    HTTP Host:  static.ak.fbcdn.net                                          */
/*    Generated:  August 31st 2009 6:59:15 AM PDT                              */
/*      Machine:  10.16.140.107                                                */
/*       Source:  Backing Store                                                */
/*     Location:  js/socialmap.js h:8ypfc5pi                                   */
/*       Locale:  nu_ll                                                        */
/*         Path:  js/socialmap.js                                              */


var sTypes;var existing;var ctype;var from;var to;var sConfirmingID;var sConfirmingClose=false;var originalCodedForm;var friend_names=null;var gotopage=null;var confirmUserAdded=null;function urlencode(str){return encodeURIComponent(str);}
function ajaxEndpoint()
{var sAjaxEndpoint=null;if(window.XMLHttpRequest){try{sAjaxEndpoint=new XMLHttpRequest();}
catch(ex){sAjaxEndpoint=null;}}
if(window.ActiveXObject){try{sAjaxEndpoint=new ActiveXObject("Msxml2.XMLHTTP");}
catch(ex){try{sAjaxEndpoint=new ActiveXObject("Microsoft.XMLHTTP");}
catch(ex){sAjaxEndpoint=null;}}}
return sAjaxEndpoint;}
function ajaxEncodeForm(form)
{var codedString='';var ampersand='';var formLength=form.elements.length;for(index=0;index<formLength;index++){var element=form.elements[index];var placeholderValue=element.getAttribute('placeholder');if(placeholderValue!==null&&placeholderValue==element.value)
continue;if(!element.name||element.name==''||element.name=='undefined'||!element.value||element.value==''){continue;}
switch(element.type){case'text':case'hidden':case'password':case'textarea':case'select-one':codedString+=ampersand+element.name+'='+urlencode(element.value);break;case'radio':case'checkbox':if(element.checked)
codedString+=ampersand+element.name+'='+urlencode(element.value);break;}
ampersand='&';}
return codedString;}
function ajaxConfirmDetails(id,close,confirm)
{if(confirm==undefined){confirm=true;}
var cparam=(confirm)?"&lc=1":"&lr=1";var ajax=ajaxEndpoint();var query="id="+id+cparam;if(ge('post_form_id')){query+='&post_form_id='+$('post_form_id').value;}
sConfirmingID=id;sConfirmingClose=close;ajax.onreadystatechange=function(){ajaxConfirmDetailsState(ajax);};ajax.open("POST","/ajax/friends_ajax.php",true);ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");ajax.send(query);return false;}
function ajaxConfirmDetailsState(ajax)
{if(ajax.readyState==4&&ajax.status==200){if(!is_editor_embedded()){ajaxLoadContent(ge('friend_connect_'+sConfirmingID),ajax.responseText);}
if(sConfirmingClose){ajaxCloseEditor();}}
if(ajax.readyState==4&&ajax.status!=200){aiert('Couldn\'t submit form '+ajax.responseText+' '+ajax.status);Util.log(ajax);}}
var LinkEditorLoaded=false;function ajaxLoadEditor(id,name,friendinfo,friendrequest,onDone,mobile_request){confirmUserAdded=(onDone==undefined)?null:onDone;friendinfo=friendinfo||false;friendrequest2=(friendrequest=='toohackedtofix');friendrequest=friendrequest||false;mobile_request=mobile_request||false;var _displayEditor=function(id,name){var ajax=ajaxEndpoint();var query_friends="";if(friend_names==null){var query_friends="&lf=1";}
var query="id="+id+"&le=1"+query_friends;if(ge('post_form_id')){query+='&post_form_id='+$('post_form_id').value;}
to=id;ajax.onreadystatechange=function(){ajaxLoadEditorState(ajax);};ajax.open("POST","/ajax/friends_ajax.php",true);ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");ajax.send(query);ajaxLoadContent(ge('linkeditorname'),name);$('linkeditorid').value=id;if(!is_editor_embedded()){var ph=ge('f'+id);if(ph){var div_position=Vector2.getElementPosition(ph);$('link_editor_div').style.top=(div_position.y+30)+'px';$('link_editor_div').style.left=(div_position.x+30)+'px';}else{ph=ge('friend_connect_'+id);var linksSpanY=Vector2.getElementPosition(ph).y;$('linkeditor').style.top=(linksSpanY-80)+"px";$('linkeditor').style.left="50px";}}
var bottom_muffin=ge('ad_1');if(!bottom_muffin){bottom_muffin=ge('ad_5');}
if(bottom_muffin){bottom_muffin.style.visibility='hidden';}
return false;}
if(LinkEditorLoaded){_displayEditor(id,name);}else{var lec=document.createElement('div');lec.id='link_editor_div';lec.style.position='absolute';var ph=ge('f'+id);if(!ph){ph=ge('link_editor_container');}
lec.style.top=(Vector2.getElementPosition(ph).y)+"px";lec.style.left=(Vector2.getElementPosition(ph).x)+"px";lec.style.zIndex=1000;DOM.getRootElement().appendChild(lec);var _ajax=new AsyncRequest().setURI("/ajax/link_editor_ajax.php").setMethod('GET').setReadOnly(true).setHandler(bind(this,function(lec,id,name,r){ajaxLoadContent(lec,r.getPayload());LinkEditorLoaded=true;_displayEditor(id,name);},lec,id,name)).setErrorHandler(function(){aiert(_tx("Impossible de charger l'\u00e9diteur des circonstances de la rencontre d'un(e) ami(e). V\u00e9rifiez votre connexion Internet et r\u00e9essayez dans un instant."));LinkEditorLoaded=false;});if(mobile_request){gotopage='reqs.php';_ajax.send();}else if(friendinfo){gotopage='reqs.php';_ajax.setData({fi:1}).send();}else if(friendrequest){gotopage=null;_ajax.setData({fr:1}).send();}else{gotopage=null;_ajax.send();}}}
function ajaxLoadEditorEmbedded(id,name){var query_friends="";if(friend_names==null){var query_friends="&lf=1";}
var ajax=ajaxEndpoint();var query="id="+id+"&le=1"+query_friends;if(ge('post_form_id')){query+='&post_form_id='+$('post_form_id').value;}
to=id;ajax.onreadystatechange=function(){ajaxLoadEditorState(ajax);};ajax.open("POST","/ajax/friends_ajax.php",true);ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");ajax.send(query);ajaxLoadContent(ge('linkeditorname'),name);$('linkeditorid').value=id;if(!is_editor_embedded()){var linksSpan=ge('friend_connect_'+id);var linksSpanY=Vector2.getElementPosition(linksSpan).y;$('linkeditor').style.top=(linksSpanY-80)+"px";$('linkeditor').style.left="50px";}
return false;}
function ajaxLoadEditorState(ajax){if(ajax.readyState==4&&ajax.status==200&&ajax.responseText!=""){eval(ajax.responseText);resetEditorFields();for(var key in existing)
setEditorFieldValue(key,existing[key]);var type;for(var key in sTypes){type=sTypes[key];if(existing[type]&&existing[type]=="on"){toggle_option(ge(type));}
if(existing[type+"_1"]&&existing[type+"_1"]=="on"){hide(type+"_more_1");show(type+"_index_1");}
if(existing[type+"_2"]&&existing[type+"_2"]=="on"){hide(type+"_more_2");show(type+"_index_2");}}
for(var key in existing){setEditorFieldValue(key,existing[key]);switch(key){case'hookup':case'summer':case'course':case'thrufacebook':case'metrandom':case'dontknow':var cbox=ge(key+'_checkbox');cbox.style.display='';break;}}
show('linkeditor');originalCodedForm=ajaxEncodeForm(document.forms["linkeditorform"])}
if(ajax.readyState==4&&ajax.status!=200){aiert('Couldn\'t load editor '+ajax.status);Util.log(ajax);hide('linkeditor');}}
function ajaxSubmitEditor(display_type){var ajax=ajaxEndpoint();var codedForm=ajaxEncodeForm(document.forms["linkeditorform"]);if(codedForm==originalCodedForm){if(ctype==0&&from==to){ajaxConfirmDetails(to,true);return false;}
ajaxCloseEditor();return false;}
var query=(codedForm+"&lp=1");if(display_type=='default'){query=query+'&fim=1';}
ajax.onreadystatechange=function(){ajaxSubmitEditorState(ajax,display_type);};ajax.open("POST","/ajax/friends_ajax.php",true);ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");ajax.send(query);return false;}
function ajaxConfirmUserAdded(){if(confirmUserAdded!=null)
confirmUserAdded();}
function ajaxFIEditor(val,display_type){if(val==2){var ajax=ajaxEndpoint();var codedForm=ajaxEncodeForm(document.forms["linkeditorform"]);query=(codedForm+"&fi=2");ajax.onreadystatechange=function(){ajaxSubmitEditorState(ajax,display_type);};ajax.open("POST","/ajax/friends_ajax.php",true);ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");ajax.send(query);}
return false;}
function ajaxSubmitEditorState(ajax,display_type){if(ajax.readyState==4&&ajax.status==200){if(!is_editor_embedded()){hide('linkeditor');if(gotopage){goURI(gotopage);}else if(display_type=='fr'){if(elem=ge('add_details_'+ge('linkeditorid').value)){elem.innerHTML=_tx("D\u00e9tails demand\u00e9s");}}else if(display_type=='fi'){ajaxLoadContent($('friend_connect_'+ge('linkeditorid').value),'');}else{ajaxLoadContent($('friend_connect_'+ge('linkeditorid').value),ajax.responseText);}
ajaxConfirmUserAdded();}else{from_attr=$('linkeditor').getAttribute('from');goURI(from_attr?from_attr:'/home.php');}}
if(ajax.readyState==4&&ajax.status!=200){aiert(_tx("Impossible de soumettre le formulaire :")+" "+ajax.responseText+' '+ajax.status);Util.log(ajax);hide('linkeditor');if(gotopage){goURI(gotopage);}}}
function ajaxCloseEditor(){if(!is_editor_embedded()){existing=undefined;hide('linkeditor');}else{from_attr=$('linkeditor').getAttribute('from');goURI(from_attr?from_attr:'/home.php');}}
function ajaxLoadContent(elem,content){if(navigator.appName=='Netscape'&&document.createRange){rng=document.createRange();rng.setStartBefore(elem);htmlFrag=rng.createContextualFragment(content);while(elem.hasChildNodes()){elem.removeChild(elem.lastChild);}
elem.appendChild(htmlFrag);}else{elem.innerHTML=content;}}
function setEditorFieldValue(field,value)
{if(typeof field=='string')
field=document.forms['linkeditorform'].elements[field];if(!field)
return;switch(field.type){case'radio':field.checked=(field.value==value);return(field.value==value);case'checkbox':field.checked=value;return field.checked;case'text':case'hidden':case'textarea':case'password':field.value=value;placeholderValue=field.getAttribute('placeholder');if(placeholderValue!==null&&placeholderValue!=field.value)
field.style.color='black'
return true;case'select-one':case'select-multiple':var opts=field.options;for(var index=0;index<opts.length;index++){if((opts[index].value==value)||(value===null&&opts[index].value=='')){field.selectedIndex=index;}}
firstto=field.getAttribute('firstto');if(firstto){year_one_update(field);}
return true;}
Util.error("setFieldValue: Field type "+field.type+" not supported "+value);return false;}
function resetEditorFields()
{var editorDiv=ge('linkeditor');var allDivs=editorDiv.getElementsByTagName('div');var allDivsLength=allDivs.length;for(index=0;index<allDivsLength;index++){var divToHide=allDivs[index];if(divToHide.className=="options"||divToHide.className=="index")
divToHide.style.display='none';if(divToHide.className=="indexshow")
divToHide.style.display='';}
var form=document.forms['linkeditorform'];var formLength=form.elements.length;for(index=0;index<formLength;index++){var field=form.elements[index];if(field.name=="id"||field.id=="post_form_id"||field.id=="fi")
continue;switch(field.type){case'radio':case'checkbox':field.checked=false;break;case'text':case'hidden':case'textarea':case'password':field.value='';break;case'select-one':case'select-multiple':field.selectedIndex=0;secondto=field.getAttribute('secondto');if(secondto){field.options.length=0;field.options[0]=new Option("","0");field.options[1]=new Option("Present","9999");field.disabled=true;}
break;}}}
function toggle_option(source)
{if(source.htmlFor){var source=ge(source.htmlFor);source.checked=!source.checked;}
var optionsDivId=source.name+"_options";if(ge(optionsDivId)){if(source.checked){var load_func="load_"+source.name;var load_exists=eval("(window."+load_func+" != null)");if(load_exists){eval(load_func+"()");}
show(optionsDivId);}else{hide(optionsDivId);}}}
function toggle_dontknow(source)
{if(source.htmlFor){var source=ge(source.htmlFor);source.checked=!source.checked;}
var optionsDivId=source.name+"_options";var submitButton=ge('submit');if(ge(optionsDivId)){if(source.checked){submitButton.value=_tx("Supprimer de ma liste d'amis");submitButton.onclick=delete_click;show(optionsDivId);}
else{submitButton.value=_tx("Demander une confirmation");submitButton.onclick=ajaxSubmitEditor;hide(optionsDivId);}}}
function year_one_update(source)
{if(!source)return;var firstto=source.getAttribute('firstto');if(!firstto)return;var year2_select=ge(firstto);if(!year2_select)return;if(source.value==0){year2_select.options.length=0;year2_select.options[0]=new Option("","0");year2_select.options[1]=new Option(_tx("Pour le moment"),"9999");year2_select.disabled=true;}else{var year1Value=source.value;var oldValue=year2_select.value;year2_select.length=0;year2_select.options[0]=new Option(_tx("Pour le moment"),"9999");var now=new Date();for(index=now.getFullYear();index>=year1Value;index--)
year2_select.options[year2_select.options.length]=new Option(index,index);var newValue=(oldValue>=year1Value)?oldValue:year1Value;if(newValue==now.getFullYear()){newValue="9999";}
setEditorFieldValue(year2_select,newValue);year2_select.disabled=false;}
return false;}
function delete_click()
{var id=$('linkeditorid').value;goURI('/friends/remove.php?remove_friend=1&rr=1&friend_id='+id);}
function is_editor_embedded()
{var linkeditordiv=ge('linkeditor');if(linkeditordiv)
var linkeditorclass=linkeditordiv.className;if(linkeditorclass)
return(linkeditorclass=='embedded');return false;}
function load_thrufriend(){var lform=ge('linkeditorform');var sel=lform['thrufriend_0_mod1'];if(sel&&sel.length==1){for(var id in friend_names){var fopt=new Option((friend_names[id].length>25?friend_names[id].substring(0,25)+'...':friend_names[id]),id);sel.options[sel.length]=fopt;}}}
function pic_on(target)
{$("pic"+target).style.borderColor="#3B5998";}
function pic_off(target)
{$("pic"+target).style.borderColor="#eeeeee";}

if (window.Bootloader) { Bootloader.done(["js\/socialmap.js"]); }