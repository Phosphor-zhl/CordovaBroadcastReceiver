var exec = require('cordova/exec');

var testAPI = {}

testAPI.initBroadcast = function(arg0,success, error) {
    exec(success, error, "TestPlugin2", "initBroadcast",[arg0]);
};

testAPI.closeBroadcast=function(success,error){
    exec(success,error,"TestPlugin2", "closeBroadcast")
}
module.exports = testAPI;