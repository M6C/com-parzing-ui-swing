/*    HTTP Host:  static.ak.fbcdn.net                                          */
/*    Generated:  September 4th 2009 4:18:07 PM PDT                            */
/*      Machine:  10.16.139.101                                                */
/*       Source:  Backing Store                                                */
/*     Location:  js/41u4hl3y3mg4wwg8.pkg.js h:3e35lvqc                        */
/*       Locale:  nu_ll                                                        */
/*         Path:  js/41u4hl3y3mg4wwg8.pkg.js                                   */


var SocialGraphManager=function(){var _initialized=false;var _classType=-1;var _nodeID=-1;var _dialog=null;var _prepareAsyncData=function(data){if(!_initialized){return null;}
var required_data={'class':_classType,'node_id':_nodeID};copy_properties(data,required_data);return data;};var _send=function(uri,data,handler){new AsyncRequest().setURI(uri).setData(_prepareAsyncData(data)).setHandler(handler?handler:bagofholding).send();return false;};var _showDialog=function(uri,data){if(!(data=_prepareAsyncData(data))){return;}
var async=new AsyncRequest().setMethod('POST').setURI(uri).setData(data);_dialog=new Dialog().setModal(true).setAsync(async).show();return false;};return{init:function(classType,nodeID){_initialized=true;_classType=classType;_nodeID=nodeID;return this;},getCurrentNodeID:function(){return _nodeID;},showInviteDialog:function(){_showDialog('/ajax/social_graph/invite_dialog.php',{});},showAddAdminDialog:function(){_showDialog('/ajax/social_graph/add_admin_dialog.php',{});},showBrowseDialog:function(args){var params=URI.getRequestURI().getQueryData();if('viewas'in params){if(args==null){args={};}
args['viewas']=params['viewas'];}
_showDialog('/ajax/social_graph/dialog/browse.php',args||{});},submitDialog:function(formID,target){if(_dialog){var data=serialize_form($(formID));_dialog.hide();if(data['ids']||data['email_addresses']){_showDialog(target,data);}}},cancelDialog:function(){if(_dialog){_dialog.hide();}},fetch:function(edge_type,meta,page,limit,handler){var uri='/ajax/social_graph/fetch.php';var data={'edge_type':edge_type,'page':page,'limit':limit};var params=URI.getRequestURI().getQueryData();if('viewas'in params){data['viewas']=params['viewas'];}
if(meta){copy_properties(data,meta);}
return _send(uri,data,handler);},search:function(query,meta,handler){var uri='/ajax/social_graph/search.php';var data={'query':query};if(meta){copy_properties(data,meta);}
return _send(uri,data,handler);},add:function(fbids,edge_type,meta,app_id,message,handler){var uri='/ajax/social_graph/add.php';var data={'fbids':fbids,'edge_type':edge_type,'app_id':app_id,'message':message};if(meta){copy_properties(data,meta);}
return _send(uri,data,handler);},remove:function(fbid,edge_type,ban,meta,message,handler){var uri='/ajax/social_graph/remove.php';var data={'fbid':fbid,'edge_type':edge_type,'ban':ban,'message':message};if(meta){copy_properties(data,meta);}
return _send(uri,data,handler);}};}();

function ProfileStream(profile_id,viewer_id,max_time,filter,see_more,can_edit,is_page,is_split){this.profile_id=profile_id;this.viewer_id=viewer_id;this.filter=filter;this.can_edit=can_edit;this.is_page=is_page;this.is_split=is_split;this.settings_loaded=false;this.min_time=0;this.max_time=max_time;this.ajax_endpoint_uri='/ajax/stream/profile.php';this.streamElem=$('profile_minifeed');this.streamContainerElem=$('profile_stream_container');this.settingsElem=ge('profile_settings');this.pagerNextElem=ge('profile_pager_container');this.cache={};this.cache[this.filter]={'html':this.streamElem.innerHTML,'max_time':this.max_time,'see_more':see_more}
if(ua.ie()<7){var story=null,container=$('profile_minifeed');var getStory=function(node){while(node!=container&&!CSS.hasClass(node,'UIStory')){node=node.parentNode;}
return CSS.hasClass(node,'UIStory')&&node;};Event.listen(container,'mouseover',function(event){story=getStory(event.getTarget());story&&CSS.addClass(story,'UIStory_hover');});Event.listen(container,'mouseout',function(event){story&&CSS.removeClass(story,'UIStory_hover');});}
if(ProfileStream.instance){delete ProfileStream.instance;}
ProfileStream.instance=this;return this;}
ProfileStream.getInstance=function(){return ProfileStream.instance;}
ProfileStream.animationDuration=300;ProfileStream.dirtyCache=function(){if(stream=ProfileStream.getInstance()){stream.cache={};}}
ProfileStream.FILTER={'ALL_POSTS':1,'POSTS_BY_ME':2,'POSTS_BY_OTHERS':3,'SINGLE_STORY':4,'SETTINGS':-1}
ProfileStream.clearStatus=function(profile_id){new AsyncRequest().setURI('/ajax/updatestatus.php').setData({'profile_id':profile_id,'clear':1}).setHandler(ProfileStream.clearStatusUI).send();}
ProfileStream.clearStatusUI=function(){var ps=$('profile_status');animation(ps).to('opacity',0).duration(200).ondone(function(){CSS.addClass(ps,'hidden_elem');}).go();}
ProfileStream.prototype.setStatus=function(status){if(this.can_edit&&status){var ps=ge('profile_status');if(!ps){return;}
var duration=150;var st=$('status_text');var sti=$('status_time_inner');var callback=function(){st.setContent(HTML(status));sti.setContent(HTML(_tx("il y a quelques instants")));var status_source=ge('status_source');var status_mobile=ge('status_mobile_indicator');if(status_source){CSS.addClass(status_source,'hidden_elem');}
if(status_mobile){CSS.addClass(status_mobile,'hidden_elem');}
CSS.removeClass(ps,'hidden_elem');new animation(ps).to('opacity',1).duration(duration).go();};if(DOM.getText(st).trim()){new animation(ps).to('opacity',0).duration(duration).ondone(callback).go();}else{CSS.setOpacity(ps,0);duration=300;callback();}}}
ProfileStream.prototype.addContent=function(content){var ondone=bagofholding;var hide=false;var message=null;if(this.filter!=ProfileStream.FILTER.ALL_POSTS){if(this.can_edit&&this.filter!=ProfileStream.FILTER.POSTS_BY_ME){hide=true;message=this.is_split?_tx("Cette publication sera d\u00e9plac\u00e9e dans l'onglet Fil d'actualit\u00e9s."):_tx("Cette publication appara\u00eetra dans votre filtre.");}
if(!this.can_edit&&this.filter!=ProfileStream.FILTER.POSTS_BY_OTHERS){hide=true;message=this.is_split?_tx("Cette publication sera d\u00e9plac\u00e9e dans l'onglet Mur."):(this.is_page?_tx("Cette publication appara\u00eetra dans le filtre Fans."):_tx("Cette publication appara\u00eetra dans le filtre Amis."));}}
if(hide){var status=HTML('<div class="UIMessageBox status">'+message+'</div>');content=DOM.create('div',{},[status,content]);ondone=function(){animation(content).duration(6000).checkpoint().to('opacity','0').duration(ProfileStream.animationDuration).ondone(function(){DOM.remove(content);}).go();}}
CSS.setStyle(content,'height','0px');CSS.setOpacity(content,'0');var oldFirst=this.streamElem.find('div.UIIntentionalStory_Profile_First');if(oldFirst){oldFirst.removeClass('UIIntentionalStory_Profile_First');}
CSS.addClass(content,'UIIntentionalStory_Profile_First');$(this.streamElem).prependContent(content);ProfileStream.dirtyCache();new animation(content).to('height','auto').to('opacity','1').duration(ProfileStream.animationDuration).ondone(ondone).go();var empty=this.streamElem.scry('div.empty_wall');if(empty.length){new animation(empty[0]).to('opacity','0').duration(ProfileStream.animationDuration).ondone(function(){DOM.remove(empty[0]);}).go();}}
ProfileStream.prototype.fadeAndSwap=function(e1,e2){animation(e1).to('opacity',0).duration(100).hide().ondone(function(){CSS.setOpacity(e2,0);animation(e2).to('opacity',1).from(0).duration(100).show().go();}).go();return true;}
ProfileStream.prototype.showSettings=function(filter){if(this.filter==ProfileStream.FILTER.SETTINGS){return false;}
this.filter=ProfileStream.FILTER.SETTINGS
var switchToSettings=function(){this.fadeAndSwap(this.streamContainerElem,this.settingsElem);}.bind(this);if(this.settings_loaded){switchToSettings();}else{new AsyncRequest().setMethod('GET').setReadOnly(true).setData({id:this.profile_id}).setHandler(function(response){DOM.setContent($('profile_settings'),HTML(response.getPayload().innards));this.settings_loaded=true;switchToSettings();}.bind(this)).setURI('/ajax/profile/show_settings.php').send();}
return false;}
ProfileStream.prototype.loadStream=function(fade){var apply_content=function(content_html,fade_and_swap){DOM.setContent(this.streamElem,HTML(content_html));if(fade_and_swap){this.fadeAndSwap(this.settingsElem,this.streamContainerElem);}}.bind(this);var request_max_time=this.max_time;if(this.min_time==0&&this.cache[this.filter]){this.max_time=this.cache[this.filter].max_time;if(this.pagerNextElem){CSS.conditionClass(this.pagerNextElem,'UIShowMore_NoMore',!this.cache[this.filter].see_more);}
apply_content(this.cache[this.filter].html,fade);fade=false;}
this.loading=true;new AsyncRequest().setMethod('GET').setReadOnly(true).setURI(this.ajax_endpoint_uri).setData({profile_id:this.profile_id,viewer_id:this.viewer_id,filter:this.filter,max_time:request_max_time}).setContextData('clicktype','Filter Stories or Pagination',this.viewer_id!=this.profile_id).setHandler(function(response){if(this.max_time==0){this.cache[this.filter]={'html':response.payload.stream_html,'max_time':response.payload.max_time,'see_more':response.payload.see_more};}
this.max_time=response.payload.max_time;if(this.pagerNextElem){CSS.conditionClass(this.pagerNextElem,'UIShowMore_NoMore',!response.payload.see_more);}
apply_content(response.payload.stream_html,fade);}.bind(this)).setFinallyHandler(function(){this.loading=false;}.bind(this)).send();}
ProfileStream.prototype.showStream=function(filter){if(this.loading){return false;}
var orig_filter=this.filter;this.filter=filter;this.min_time=0;this.max_time=0;this.loadStream(orig_filter==ProfileStream.FILTER.SETTINGS);return false;}
ProfileStream.prototype.showMore=function(filter,is_scroll_load){this.loading=true;new AsyncRequest().setMethod('GET').setReadOnly(true).setStatusElement(this.pagerNextElem).setURI(this.ajax_endpoint_uri).setData({profile_id:this.profile_id,viewer_id:this.viewer_id,filter:this.filter,is_scroll_load:is_scroll_load,max_time:this.max_time}).setContextData('clicktype','Filter Stories or Pagination',this.viewer_id!=this.profile_id).setHandler(function(response){CSS.conditionClass(this.pagerNextElem,'UIShowMore_NoMore',!response.payload.see_more);DOM.appendContent(this.streamElem,HTML(response.payload.stream_html));this.max_time=response.payload.max_time;if(this.min_time==0){this.cache[this.filter]={'html':this.streamElem.innerHTML,'max_time':this.max_time,'see_more':response.payload.see_more};}}.bind(this)).setFinallyHandler(function(){this.loading=false;}.bind(this)).send();if(!is_scroll_load){var link_data={src:ft.NF_SOURCE_MINIFEED,evt:ft.NF_EVENT_SEE_MORE,actrs:this.profile_id,type:'page'};ft.logData(link_data);}}
ProfileStream.hideStory=function(story_id,profile_id,story_key,story_type,app_link){var delete_fn=function(){var story=$(story_id);var collapse=story;if(CSS.hasClass(story,'UIRecentActivityStory')){var container=story.parentNode;if(CSS.hasClass(container,'UIStream_Chunk')){if(container.childNodes.length==1){collapse=container;container=container.parentNode;if(container.childNodes.length==1){collapse=container.parentNode.parentNode;}}}else{if(container.childNodes.length==1){collapse=container.parentNode.parentNode;}}}else if(CSS.hasClass(story,'UIIntentionalStory_Profile_First')){CSS.removeClass(story,'UIIntentionalStory_Profile_First');if(CSS.hasClass(story.nextSibling,'UIIntentionalStory')){CSS.addClass(story.nextSibling,'UIIntentionalStory_Profile_First');}}
var checkBox=ge('revoke_permission');var revoke_permission=checkBox&&checkBox.checked;new AsyncRequest().setURI('/ajax/minifeed.php').setData({'profile_fbid':profile_id,'ministory_key':story_key,'story_type':story_type,'revoke_permission':revoke_permission}).setHandler(function(response){if(response.getPayload().status_cleared){ProfileStream.clearStatusUI();}
DOM.remove(collapse);}).setErrorHandler(function(response){ErrorDialog.showAsyncError(response);CSS.removeClass(collapse,'hidden_elem');animation(collapse).to('height','auto').duration(300).ease(animation.ease.end).go();}).setFinallyHandler(ProfileStream.dirtyCache).send();animation(collapse).to('height','0px').duration(300).ease(animation.ease.end).ondone(function(){CSS.addClass(collapse,'hidden_elem')}).go();}
ProfileStream.hideConfirmation(delete_fn,app_link);}
ProfileStream.hideConfirmation=function(delete_function,app_link){var body=_tx("Souhaitez-vous vraiment effacer cet \u00e9l\u00e9ment ?");if(app_link){var option=ProfileStream._createDialogOptionField('revoke_permission',_tx("Ne pas autoriser {app_name} \u00e0 publier sans ma permission",{'app_name':app_link}));body+='<br/><br/>'+option;}
Dialog.createConfirmationDialog(body,_tx("Supprimer le message"),Dialog.newButton('delete_story',_tx("Supprimer"),null,delete_function)).show();}
ProfileStream._createDialogOptionField=function(id,message){var markup='<div class="dialog_option">'+'<input type="checkbox" id="'+id+'"/>'+'<span class="dialog_option_text">'+message+'</span>'+'</div>';return markup;}

if (window.Bootloader) { Bootloader.done(["js\/41u4hl3y3mg4wwg8.pkg.js"]); }