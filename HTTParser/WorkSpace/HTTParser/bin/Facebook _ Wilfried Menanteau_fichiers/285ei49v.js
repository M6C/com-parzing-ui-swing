/*    HTTP Host:  static.ak.fbcdn.net                                          */
/*    Generated:  August 31st 2009 6:59:30 AM PDT                              */
/*      Machine:  10.16.140.108                                                */
/*       Source:  Global Cache                                                 */
/*     Location:  js/adz/base.js h:285ei49v                                    */
/*       Locale:  nu_ll                                                        */
/*         Path:  js/adz/base.js                                               */


function adz_insertTagIntoDiv(tag,div_id){var div=document.getElementById(div_id);if(!div){window.Util&&Util.error('No div has id "'+div_id+'"');return;}
div.innerHTML='<div style="display:none"><br></div>'+tag;var loaded_srcs={};var evaled_js={};var loadNextScript=function(){var all_scripts=div.getElementsByTagName('script');var src_to_load=null;var code_to_eval=null;for(var ii=0;ii<all_scripts.length;ii++){if(all_scripts[ii].src){var candidate_src=all_scripts[ii].src;if(!loaded_srcs[candidate_src]){src_to_load=candidate_src;break;}}else{var candidate_code=all_scripts[ii].innerHTML;if(!evaled_js[candidate_code]){code_to_eval=candidate_code;break;}}}
if(src_to_load){loaded_srcs[src_to_load]=true;adz_stubOutDocumentWriteExec(function(done){loadExternalJavascript(src_to_load,done);},div.id,loadNextScript);}else if(code_to_eval){evaled_js[code_to_eval]=true;adz_stubOutDocumentWriteExec(function(done){if(window.eval_global){eval_global(code_to_eval);}
if(done){done();}},div.id,loadNextScript);}};loadNextScript();}
function adz_stubOutDocumentWriteExec(func,div_id,callback){var div=document.getElementById(div_id);if(!div){window.Util&&Util.error('No div has id "'+div_id+'"');return;}
var tmp=document.write,timeout=0,fired=false;var documentWriteString=div.innerHTML;document.write=function(m){documentWriteString+=m;div.innerHTML=documentWriteString;if(!timeout){timeout=1;setTimeout(done,0);}};var done=function(){if(!fired){document.write=tmp;if(callback){callback();}}
fired=true;};func(done);}

if (window.Bootloader) { Bootloader.done(["js\/adz\/base.js"]); }