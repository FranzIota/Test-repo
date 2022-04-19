// import { initializeApp } from "firebase/app";
// import { getAnalytics } from "firebase/analytics";

// import firebase from 'firebase';

const firebase = require('firebase');

const firebaseConfig = {
    apiKey: "AIzaSyBAU9b5HiDpW2C-YOUxE2dUEaEdVckcYHU",
    authDomain: "learning-a54e1.firebaseapp.com",
    projectId: "learning-a54e1",
    storageBucket: "learning-a54e1.appspot.com",
    messagingSenderId: "313993594207",
    appId: "1:313993594207:web:bae88049a41eb3e026e2ea",
    measurementId: "G-ZFGPKRYZK2"
  };

firebase.initializeApp(firebaseConfig);
const db = firebase.firestore();
const user = db.collection("Users");
module.exports = user;