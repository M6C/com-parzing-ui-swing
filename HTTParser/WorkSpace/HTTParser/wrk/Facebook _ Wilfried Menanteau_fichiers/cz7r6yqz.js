/*    HTTP Host:  b.static.ak.fbcdn.net                                        */
/*    Generated:  September 9th 2009 11:41:42 AM PDT                           */
/*      Machine:  10.16.140.110                                                */
/*       Source:  Global Cache                                                 */
/*     Location:  js/7x508ljv85k4kk88.pkg.js h:cz7r6yqz                        */
/*       Locale:  nu_ll                                                        */
/*         Path:  js/7x508ljv85k4kk88.pkg.js                                   */


var message_dialog={_registered:false,register:function(){if(!message_dialog._registered){LinkController.registerHandler(_message_composer_link_handler);message_dialog._registered=true;}},show:function(to_uid,subj,message,ref){subj=subj?subj:'';message=message?message:'';var data={'id':to_uid,'subject':subj,'message':message};var async=new AsyncRequest().setMethod('POST').setData(data).setContextData('clicktype','Send a Message',ref==="profile_others").setURI('/ajax/inbox/composer.php');new Dialog().setStackable(true).setContentWidth(500).setAsync(async).show();return false;},send:function(button){var data=serialize_form(button.form);data['action']='compose';new AsyncRequest().setURI('/ajax/inbox/ajax.php').setData(data).setHandler(bind(null,message_dialog.send_handle,button)).setErrorHandler(bind(null,message_dialog.send_error,button)).send();},send_handle:function(button,r){var payload=r.getPayload();var dialog=Dialog.getCurrent();if(dialog){dialog.setTitle(payload.title).setBody(payload.content).setButtons([Dialog.OK]).clearHandler().show();if(!payload.error){dialog.setAutohide(2000);}}},send_error:function(button,r){var dialog=Dialog.getCurrent();if(dialog){if(r.getError()==kError_Inbox_DuplicateMessages){dialog.hide();return;}
dialog.setTitle(_tx("Erreur")).setBody(_tx("D\u00e9sol\u00e9, une erreur inconnue s'est produite")).setButtons([Dialog.OK]).clearHandler().show().setAutohide(1100);}}};function _message_composer_link_handler(link){if(typeof(GigaboxxLinkHandler)!="undefined"&&GigaboxxLinkHandler._registered){return true;}
var current_uri=URI.getRequestURI();if(current_uri.getPath().match(/^\/inbox\//)){return true;}
var uri=new URI(link.href);if(uri.getPath()=='/inbox/'){var query=uri.getQueryData();if((typeof query.compose!="undefined")&&(typeof query.id!="undefined")&&(query.id<2200000000)){message_dialog.show(query.id);return false;}}
return true;}

function Megaboxx(){this._endpoint='/ajax/inbox/ajax.php';}
copy_properties(Megaboxx,{_instance:null,getInstance:function(){if(!Megaboxx._instance){Megaboxx._instance=new Megaboxx();}
return Megaboxx._instance;}});Megaboxx.prototype.select_dropdown_onchange=function(obj){if(obj.value=='^_^'){return false;}
var status=obj.value?this['STATUS_'+obj.value.toUpperCase()]:this.STATUS_NONE;this.set_selection(status);}
Megaboxx.prototype.status_menu_onclick=function(obj,action,threads){threads=threads?threads:this.get_selected_threads();if(!threads.length||(typeof obj=='object'&&obj.parentNode.className.indexOf('disabled')!=-1)){this.update_status_buttons();return false;}
this.reload_needed=false;var post={action:action,ids:threads,folder:megaboxx_data.folder,time:megaboxx_data.time};if(action=='delete'){if(typeof obj=='boolean'&&obj){var boxx=ge('megaboxx');for(var i=0;i<threads.length;i++){var row=document.getElementById('thread_'+threads[i]);row.parentNode.removeChild(row);}
post.page=megaboxx_data.page;post.nav_base=megaboxx_data.nav_base;post.slice=boxx.getElementsByTagName('tr').length;var loading=ge('loading_boxx');if(!boxx.getElementsByTagName('tr').length){this.reload_needed=true;}
if(!loading){var loading=document.createElement('div');loading.innerHTML=['<table id="loading_boxx" class="',boxx.className,'"><tr><td></td></tr></table>'].join('');loading=loading.getElementsByTagName('table')[0];boxx.parentNode.insertBefore(loading,boxx.nextSibling);}else{loading.style.display='';}}else{var title;if(threads.length==1){title=_tx("Souhaitez-vous vraiment supprimer ce fil de discussion ?");}else{title=_tx("Souhaitez-vous vraiment supprimer ces fils de discussion ?");}
var dialog=new ContextualDialog().setContext(obj).setTitle(title).setBody(_tx("Ceci supprimera tous les messages attach\u00e9s.")).setButtons([Dialog.DELETE,Dialog.CANCEL]).setHandler(function(){this.status_menu_onclick(true,action,threads);dialog.hide();}.bind(this)).show();return false;}}else{for(var i=0;i<threads.length;i++){var row=document.getElementById('thread_'+threads[i]);var status=this.get_status(row);if(action=='toggle_read'){if(status==this.STATUS_UNREAD){action='mark_read';}else{action='mark_unread';}
post.action=action;}
if(action=='mark_read'){CSS.removeClass(row,'new_message');}else{CSS.setClass(row,trim(row.className.replace(/ ?new_message ?|$/,' new_message ')));}}}
new AsyncRequest().setHandler(this.ajax_callback.bind(this)).setData(post).setURI(this._endpoint).send();this.update_status_buttons();var message_selector=ge('message_selector');message_selector?message_selector.selectedIndex=0:false;return false;}
Megaboxx.prototype.ajax_callback=function(response){var result=response.getPayload();if(typeof result=='undefined'){return;}
if(result=='refresh'||this.reload_needed){document.location.reload();return;}
if(typeof(result.nav)!='undefined'&&(megabox_pager=ge('megabox_pager'))){$('megaboxx_pager').innerHTML=result.nav;}
if(typeof(result.rows)!='undefined'){if(result.rows){var tbody=document.createElement('tbody');var boxx=ge('megaboxx');boxx.appendChild(tbody);tbody.innerHTML=result.rows;var rows=tbody.getElementsByTagName('tr');var first_tbody=$('megaboxx').getElementsByTagName('tbody')[0];while(rows.length){first_tbody.appendChild(rows[0]);}
tbody.parentNode.removeChild(tbody);}
$('loading_boxx').style.display='none';}
if(typeof(result.top_nav)!='undefined'){$('nav_inbox').setContent(HTML(result.top_nav));}
if(typeof(result.dropdown)!='undefined'){$('fb_menu_inbox_dropdown').setContent(HTML(result.dropdown));}}
Megaboxx.prototype.selection_onchange=function(obj){this.update_status_buttons();}
Megaboxx.prototype.get_status=function(row){if(row.className.indexOf('new_message')!=-1){return this.STATUS_UNREAD;}else{return this.STATUS_READ;}}
Megaboxx.prototype.get_thread_id=function(row){return/thread_(\d+)/.exec(row.id)[1];}
Megaboxx.prototype.is_selected=function(row){var inputs=row.getElementsByTagName('input');return inputs.length&&inputs[0].checked;}
Megaboxx.prototype.get_selected_threads=function(){var rows=$('megaboxx').getElementsByTagName('tr');var threads=[];for(var i=0;i<rows.length;i++){if(this.is_selected(rows[i])){threads.push(this.get_thread_id(rows[i]));}}
return threads;}
Megaboxx.prototype.set_selection=function(status){var rows=$('megaboxx').getElementsByTagName('tr');var threads=[];for(var i=0;i<rows.length;i++){if(!status||this.get_status(rows[i])==status){threads.push(this.get_thread_id(rows[i]));}}
this.select_threads(threads,true);}
Megaboxx.prototype.select_threads=function(threads,set){var rows=$('megaboxx').getElementsByTagName('tr');for(var i=0;i<rows.length;i++){if(threads.indexOf(this.get_thread_id(rows[i]))!=-1){rows[i].getElementsByTagName('input')[0].checked=true;}else if(set){rows[i].getElementsByTagName('input')[0].checked=false;}}
this.update_status_buttons();}
Megaboxx.prototype.update_status_buttons=function(){var buttons=ge('inbox_status_buttons');if(!buttons){return;}
var threads=this.get_selected_threads();var unread_disabled=true;var read_disabled=true;for(var i=0;i<threads.length;i++){var status=this.get_status(ge('thread_'+threads[i]));if(status==this.STATUS_UNREAD){read_disabled=false;}else{unread_disabled=false;}}
var message_selector=ge('message_selector');if(!threads.length&&message_selector){message_selector.selectedIndex=0;}
var delete_disabled=read_disabled&&unread_disabled;var li=buttons.getElementsByTagName('li');var loop=[{l:li[0],d:unread_disabled},{l:li[1],d:read_disabled},{l:li[2],d:delete_disabled}];for(var i=0;i<loop.length;i++){if(loop[i].l){CSS.setClass(loop[i].l,trim(loop[i].l.className.replace('menu_disabled',''))+(loop[i].d?' menu_disabled':''));}}}
Megaboxx.prototype.submit_prehook=function(obj,inline,captcha_input){var form=ge('compose_message');var length=trim(form.message.value).length;var error_text=null;var ids=ge('ids');var is_update=ge('fbpage_update');var empty_ok=inboxAttachments.attachment_added||(form.subject&&trim(form.subject.value).length);if(length==0&&!empty_ok){error_text=_tx("Vous ne pouvez pas envoyer un message vide.");}else if(length>10000){error_text=_tx("Votre message est trop long. Raccourcissez-le et essayez de le renvoyer.");}else if(!is_update&&ids&&tokenizer.is_empty(ids)){error_text=_tx("Veuillez sp\u00e9cifier au moins un destinataire pour ce message.");}
if(error_text){var error=ge('error');if(error){error.parentNode.removeChild(error);}
error=document.createElement('div');error.id='error';error.innerHTML='<h2>'+error_text+'</h2>';form.insertBefore(error,form.firstChild);var conf=ge('conf');if(conf){conf.parentNode.removeChild(conf);}
return false;}
if(form.rand_id.value==0){form.rand_id.value=Math.floor((Math.random()*100000000));}
if(inboxAttachments){inboxAttachments.fix_app_inputs_on_send();}
if(inline){var form=obj.form;var post={action:'send_reply',id:form.thread.value,message:form.message.value};if(captcha_input){for(var i=0;i<captcha_input.length;i++){post[captcha_input[i].name]=captcha_input[i].value;}}
var attachment=ge(inboxAttachments.view_id);var inputs=[];if(attachment){inputs=attachment.getElementsByTagName('input');for(var i=0;i<inputs.length;i++){post[inputs[i].name]=inputs[i].value;}}
if(form.extra&&form.extra.checked){post['extra']=form.extra.value;}
new AsyncRequest().setURI(this._endpoint).setData(post).setStatusElement('message_post_status').setHandler(this.sendMessageHandler.bind(this,form.thread.value)).setErrorHandler(this.sendMessageErrorHandler.bind(this)).setFinallyHandler(function(response){get_all_form_inputs(form).each(function(obj){obj.disabled=false;});}).send();inputs=[form.getElementsByTagName('textarea')[0]];var form_inputs=form.getElementsByTagName('input');for(var i=0;i<form_inputs.length;i++){if(form_inputs[i].type=='button'){inputs.push(form_inputs[i]);}}
for(var i=0;i<inputs.length;i++){inputs[i].disabled=true;}}else if(captcha_input){var form=ge('compose_message');var span=document.createElement('span');var html=[];for(var i=0;i<captcha_input.length;i++){html.push('<input type="hidden" name="',captcha_input[i].name,'" value="',htmlspecialchars(captcha_input[i].value),'" />');}
span.innerHTML=html.join('');form.appendChild(span);form.onsubmit=null;form.submit();}
return!inline;}
Megaboxx.prototype.sendMessageHandler=function(thread_id,response){var form=ge('compose_message');if(form&&form.thread&&form.thread.value==thread_id){var text=response.getPayload();var thread=ge('messages');var msg=document.createElement('div');thread.appendChild(msg);set_inner_html(msg,text);form.getElementsByTagName('textarea')[0].value='';var i=0;var msg=null;while(msg=ge('msg_'+(i++))){CSS.removeClass(msg,'unread');}
inboxAttachments.remove_attachment_view();this.enable_all_attachment_forms();var hidden_count=ge('hidden_count');if(hidden_count){hidden_count.innerHTML=parseInt(hidden_count.innerHTML,10)+1;var total_count=ge('total_count');total_count.innerHTML=parseInt(total_count.innerHTML,10)+1;}
var error=ge('error');if(error){error.parentNode.removeChild(error);}}}
Megaboxx.prototype.sendMessageErrorHandler=function(response){var error=ge('error');if(error){error.parentNode.removeChild(error);}
var status=DOM.scry($('content'),'#conf .status');if(status.length>0){status[0].parentNode.removeChild(status[0]);}
var thread=ge('messages');var payload=response.getPayload();var error_summary=response.getErrorSummary();var error_description=response.getErrorDescription();if(thread){var content='';var id='';var cl='';if(payload){content=payload;}else if(error_summary){id='error';cl='error';content='<h2 id="standard_error">'+error_summary+'</h2>'
+'<p id="standard_explanation">'+error_description+'</p>';}
var msg=$N('div',{className:cl},HTML(content));if(id){msg.id=id;}
thread.appendChild(msg);}}
Megaboxx.prototype.submit_delete=function(obj){new Dialog().setTitle(_tx("Supprimer la discussion")).setBody(_tx("Souhaitez-vous vraiment supprimer ce fil de discussion ?")).setButtons([Dialog.DELETE,Dialog.CANCEL]).setHandler(function(){var markup='<input name="delete" type="hidden" value="1" />'+'<input name="folder" type="hidden" value="'+megaboxx_data.folder+'" />'+'<input name="time" type="hidden" value="'+megaboxx_data.time+'" />';var span=$N('span',{},HTML(markup));obj.appendChild(span);var form=obj.getElementsByTagName('input')[0].form;if(megaboxx_data.folder){form.action+='?f=1';}
form.submit();}).show();return false;}
Megaboxx.prototype.toggle_all_attachment_forms=function(value){(value?hide:show)(ge('dd_attachment'));(value?hide:show)(ge('dt_attachment'));}
Megaboxx.prototype.enable_all_attachment_forms=function(){this.toggle_all_attachment_forms(false);}
Megaboxx.prototype.disable_all_attachment_forms=function(){this.toggle_all_attachment_forms(true);}
Megaboxx.prototype.reset_rand_id=function(){var compose_form=ge('compose_message');if(!compose_form||!compose_form.rand_id){return;}
compose_form.rand_id.value=0;}
Megaboxx.prototype.fetch_thread_history=function(count,notice){notice.innerHTML='<span class="loading"><span>'+_tx("Chargement...")+'</span></span>';new AsyncRequest().setURI(this._endpoint).setData({id:megaboxx_data.thread_id,action:'history',count:count,folder:megaboxx_data.folder}).setHandler(function(response){var messages=document.createElement('div');notice.parentNode.insertBefore(messages,notice);hide(notice);set_inner_html(messages,response.getPayload());}).send();}
Megaboxx.prototype.STATUS_ALL=0;Megaboxx.prototype.STATUS_READ=1;Megaboxx.prototype.STATUS_UNREAD=2;Megaboxx.prototype.STATUS_NONE=-1;if(typeof tokenizer!='undefined'){tokenizer.prototype.onselect=function(){Megaboxx.getInstance().reset_rand_id();}}
function inbox_search_onsubmit(friend){if(friend){goURI(friend.u);return false;}}
function inbox_search_note_close(){AsyncRequest.pingURI('/ajax/inbox/search_note_close.php',{},false);hide(ge('inbox_search_note'));}

function external_friend_add(uid,email,name,success_handler,from_invite){var handler=(function(resp){success_handler(resp.getPayload());});new AsyncRequest().setURI('/friends/ajax/external.php').setData({action:'add',uid:uid,email:email,name:name,allow_nameless:true,ok:true,no_success_dialog:true,from_invite:from_invite}).setHandler(handler).send();}
function accept_external_friend_suggestion(uid,name){external_friend_add(uid,null,name,function(){FriendSuggesterDialog.show(uid,true,function(){handle_request_click('friend_suggestion',uid,'accept');});},false);}
function show_external_friend_dialog(uid,email,action,success_handler){success_handler=success_handler||bagofholding;var async=new AsyncRequest().setMethod('GET').setData({action:action,uid:uid,email:email}).setReadOnly(true).setURI('/friends/ajax/external.php');new Dialog().setAsync(async).setCloseHandler(function(){var id=this.getUserData();if(id){external_friend_hide_link(id);success_handler(id);}}).show();}
function external_friend_hide_link(uid){var add_link=ge('add_as_friend_'+uid);if(add_link){hide(add_link);}}

if (window.Bootloader) { Bootloader.done(["js\/7x508ljv85k4kk88.pkg.js"]); }