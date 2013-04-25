ModalProgressDialog
===================

*developed with cordova 2.6 and tested with Android API level 17*

ModalProgressDialog cordava plugin (Android)

Provides a native ProgressDialog that is modal by default.

# usage:

1. Impor the '.js' file right after the 'cordova.js' file
2. Import the '.java' file to your 'src' folder
3. When you need a modal progress dialog, you do:

    plugins.activityStart('my title','my message');
    
4. When you want to dismiss the dialog:

    plugins.activityStop('my title','my message');

    
# IMPORTANT considerations:

1. When you open a modal progress dialog, you have to dismiss it when the task ends, otherwise the user cannot close the application normally
2. Progress dialog should be used when performing async tasks, like ajax calls. You open the dialog before doing the call, and closes when it responds. Thus, when you use any progress dialog without an async task, YOU MUST provide a callback to both 'activityStart' and 'activityStop', this callbacks are invoked when the android truly opens and dismisses the dialog:

    plugins.activityStart('my title','my message', function (){
      // do things when the dialog really opens
    });
    
    plugins.activityStop(function (){
      // do things when the dialog really closes
    });

3. Sometimes, you don't need a modal dialog, in this case you can use the default 'navigator.notification.activityStart()', but if you prefer my plugin, you can pass a 4th parameter, indicating whether this instance of the dialog is modal or not (default modal):

    plugins.activityStart('my title','my message', function (){}, false /* NOT modal*/);
    
    plugins.activityStart('my title','my message', function (){}, true / * modal */);
    
    plugins.activityStart('my title','my message', function (){}); // modal
    
# changelog

1. First release:
  - Basic support to modal progress dialogs
  - Callback for sync use of progress dialogs