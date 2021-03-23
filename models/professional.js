const {mongoose_connection} = require("../config");

const mongoose = mongoose_connection();

const schema = new mongoose.Schema({
    firstName: {type: String, required: true},
    lastName: {type: String, required: true},
    workEmail: {type: String, required: true},
    schoolEmail: String,
    schoolName: String,
    company: {type: String, required: true},
    createdAt: {type: Date, default: Date.now()},
    updatedAt: {type: Date, default: Date.now()},
    jobTitle: {type: String, required: true}
})

const Professional = new mongoose.model('Professional', schema);

module.exports = {Professional}