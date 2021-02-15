const express = require("express");
const router = express.Router();
const {getSavedProfessional, insertSavedProfessional} = require('./controller');

router.get('/list/:id', (req, res) => {
    getSavedProfessional(req.params['id'])
    .then((savedProfessional) => {
        const status = savedProfessional === null ? 400 : 200;
        const msg = status === 200 ? "success" : "error, savedProfessional not found";
        return res.status(status).json({"data": savedProfessional, "msg": msg});
    })
    .catch((err) => {
        return res.status(400).json({"data": null, "msg": err});
    })
});

router.post('/insert', (req, res) => {
    insertSavedProfessional(req.body)
    .then((result) => {
        const status = result.status ? 200 : (result.msg[0] === 'f' ? 400 : 500);
        return res.status(status).json(result);
    })
});

module.exports = router;