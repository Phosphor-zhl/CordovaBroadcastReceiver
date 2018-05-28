package com.plugin.testPlugin2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class TestPlugin2 extends CordovaPlugin {

    private CallbackContext mCallbackContext;
    private MyReceiver receiver;
    public static final String EVENTNAME_ERROR = "event name null or empty.";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        
        this.mCallbackContext = callbackContext;
        if(action.equals("initBroadcast")){
            try{
                final String eventName = args.getString(0);
                if (eventName == null || eventName.isEmpty()) {
                    callbackContext.error(EVENTNAME_ERROR);
                    return false;
                }
                receiver = new MyReceiver();
                registerReceiver(receiver, new IntentFilter(eventName));

            }catch(Exception ex){
                ex.printStackTrace();  
                callbackContext.error("init broadcast error");
                return false;
            }
            return true;
        }else if(action.equals("closeBroadcast")){
            try{
                unregisterReceiver(receiver);
                callbackContext.success("success");
            }catch(Exception ex){
                ex.printStackTrace();
                callbackContext.error("failed");
            }
            
        }    
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            cordova.getActivity().getApplicationContext().unregisterReceiver(receiver);
        }
    }

    protected void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        
        cordova.getActivity().getApplicationContext().registerReceiver(receiver,filter);
    }

    protected void unregisterReceiver(BroadcastReceiver receiver) {
        cordova.getActivity().getApplicationContext().unregisterReceiver(receiver);
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("scannerdata");
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK,msg);
            pluginResult.setKeepCallback(true);
            mCallbackContext.sendPluginResult(pluginResult); 
        }

    }
}
