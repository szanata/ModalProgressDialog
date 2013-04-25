/**
 *
 * ModalProgressDialog cordava plugin (Android)
 *
 * Provides a native ProgressDialog that is modal by default
 *
 *  @author Stéfano Zanata
 * 	@see http://szanata.com
 *  @reference https://github.com/madeinstefano/ModalProgressDialog
 * 	@license MIT <http://szanata.com/MIT.txt>
 * 	@license GNU <http://szanata.com/GNU.txt>
*/ 
window.plugins = window.plugins || {};
window.plugins.activityStart = function(title, message, callback, modal) {
  if (modal !== false){modal = true;}
  cordova.exec(callback ? callback : function (){}, function() {}, 'ModalProgressDialog', 'activityStart', [title, message, modal]);
};

window.plugins.activityStop = function(callback) {
  cordova.exec(callback ? callback : function (){}, function() {}, 'ModalProgressDialog', 'activityStop', []);
};