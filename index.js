// import express from "express";
// import cors from "cors";
// import user from "../config.js";


const express = require('express');
const cors = require('cors');
const user = require('./config');

const app = express();
app.use(express.json());
app.use(cors());


app.get('/', (req, res) => {
    res.json({
        "name": "aaa",
        "test": 2323
    })
})

app.post('/create', async (req, res) => {
    const data = req.body;
    await user.add(data)
    // console.log("data of users ", data);
    res.send({ msg:"user added" });
})

app.listen(4000, () => {
    console.log("up n running *4000")
})