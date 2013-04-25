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
package com.szanata.cordova.plugins;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

public class ModalProgressDialog extends CordovaPlugin {
    
	private Dialog spinnerDialog;
	
	@Override
	public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
		if ("activityStart".equals(action)) {
			String title = args.getString(0); 
			String message = args.getString(1);
			boolean cancelable = (args.getString(2) == "false") ? true : false;
			this.activityStart(title, message, cancelable, callbackContext);
			return true;
		}else if("activityStop".equals(action)){
			this.activityStop(callbackContext);
			return true;
		}
		return false;
	}

	private void activityStart(final String title, final String message, final boolean cancelable, final CallbackContext callbackContext) {
		activityStop(null);
		Runnable runnable = new Runnable() {
			public void run() {
				spinnerDialog = ProgressDialog.show(cordova.getActivity(), title, message, true, cancelable,
					new DialogInterface.OnCancelListener() {
						public void onCancel(DialogInterface dialog) {
							spinnerDialog = null;
						}
					});
				callbackContext.success();
			}
		};
		this.cordova.getActivity().runOnUiThread(runnable);
	}
	
	private void activityStop(final CallbackContext callbackContext){
		if (this.spinnerDialog != null) {
			this.spinnerDialog.cancel();
			this.spinnerDialog = null;
			if (callbackContext != null){
				callbackContext.success();
			}
		}
	}
}