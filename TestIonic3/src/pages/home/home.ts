import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

declare let cordova: any;
@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  constructor(public navCtrl: NavController
  ) {

  }

  ionViewDidEnter() {
    this.callTestPlugininitBroadcast();
    console.log("3.0 ionViewDidEnter 当进入页面时触发");
  }

  ionViewDidLeave() {
    this.callTestPluginCloseBroadcast();
    console.log("5.0 ionViewDidLeave 离开页面时触发");
  }

  callTestPlugininitBroadcast() {
    cordova.plugins.TestPlugin2.initBroadcast("com.example.chinaautoid", result => this.returnBroadcast(result), error => alert(error));
  }

  callTestPluginCloseBroadcast() {
    cordova.plugins.TestPlugin2.plus(result => alert(result), error => alert(error));
  }

  returnBroadcast(msg) {
    alert(msg);
  }

}
