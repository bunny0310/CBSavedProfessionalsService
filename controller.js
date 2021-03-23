const {savedProfessional} = require("./models/savedProfessional");
const request = require('request');
const axios = require('axios');

const getSavedProfessional = async (id) => {
    try {
        const savedProf = await savedProfessional.find({}).populate('professionalId');
        if(savedProf === undefined || savedProf === null) {
            return null;
        }
        const pId = savedProf.professionalId;
        const prof = await axios.get('https://ramen-professionals-service.herokuapp.com/api/v1/professionals/' + pId);
        
        const finalResult = {};
        finalResult['savedProfessional'] = savedProf;
        finalResult['professional'] = prof.data.data;
        // .then((res) => {
        //     console.log(res);
        //     savedProf['professional'] = res.data.data;
        // })
        // .catch((err) => {
        //     console.log('err: ' + err);
        //     return {'msg': 'error: ' + err};
        // })
        return finalResult;
    }
    catch(err) {
        console.log('err: ' + err);
        return null;
    }
}

const insertSavedProfessional = async (obj) => {
    const keys = Object.keys(obj);
    if(
        keys.indexOf('count') === -1 || 
        keys.indexOf('lastCopy') === -1 ||
        keys.indexOf('professionalId') === -1 ||
        keys.indexOf('userId') === -1
    ) {
        return {
            "msg": "fields missing!",
            "status": false,
            "data": null
        }
    }
    let result = {};
    const newSP = new savedProfessional({count: obj.count, lastCopy: obj.lastCopy, professionalId: obj.professionalId, userId: obj.userId});
    await newSP.save()
    .then((data) => {
        result = {
            "msg": "success",
            "status": true,
            "data": data
        };  
    })
    .catch((err) => {
        console.log('inside catch');
        result = {
            "msg": "error: " + err,
            "status": false,
            "data": null
        };  
    })
    return result;
};

module.exports = {getSavedProfessional, insertSavedProfessional};