# CordovaBroadcastReceiver
Cordova BroadcastReceiver + Ionic3

1 安装 plugman 插件
npm --registry https://registry.npm.taobao.org install -g plugman

2 新建组件
新建一个插件文件夹，进入插件文件夹。例如新建Plugins文件夹,然后执行下面语句
plugman create --name TestPlugin --plugin_id com.plugin.testPlugin --plugin_version 1.0.0
--name TestPlugin //自定义插件名称
--plugin_id com.plugin.testPlugin //自定义插件的包名
--plugin_version 1.0.0 //自定义插件版本

3 生成平台（android/ios）插件代码
进入插件的根目录，然后执行创建android或者ios的平台支持命令
cd TestPlugin
plugman platform add --platform_name android

TestPlugin.java文件 Plugin入口
//参数action是用来判断执行哪个方法，args是json格式的参数，callbackContext响应返回结果。
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }
    
TestPlugin.js的作用是通过js暴露插件的功能给ionic
var exec = require('cordova/exec');
exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'TestPlugin', 'coolMethod', [arg0]);
};
说明：
TestPlugin //插件名称
coolMethod //方法名称

4 初始化package.json
npm init

5 新建Ionic3 项目
1）ionic cordova plugin list 列出所有已安装的插件
2）ionic cordova  plugin remove com.plugin.testPlugin 从ionic3项目中删除插件
3）ionic cordova plugin add 自定义插件路径 安装插件到ionic3项目
====================================================================================
cordova 结果回调重复调用
PluginResult pluginResult = new PluginResult(PluginResult.Status.OK,"中间消息");
pluginResult.setKeepCallback(true);
callbackContext.sendPluginResult(pluginResult);

