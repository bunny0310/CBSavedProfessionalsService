const {mongoose_connection} = require("../config");
const {Professional} = require("./professional");


const mongoose = mongoose_connection();

const schema = new mongoose.Schema({
    count: {type: Number, required: true},
    lastCopy: {type: Number, required: true},
    lastEmailed: {type: Date, default: null},
    professionalId: {type: mongoose.Schema.Types.ObjectId, ref: 'Professional', required: true},
    updatedAt: {type: Date, default: Date.now()},
    userId: {type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true}
})

const savedProfessional = new mongoose.model('SavedProfessional', schema);

module.exports = {savedProfessional}