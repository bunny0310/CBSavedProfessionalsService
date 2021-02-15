const {asyncQueryMethod} = require("./config");
const {savedProfessional} = require("./models/savedProfessional");
const {mongoose_connection} = require("./config");
const axios = require("axios");

const mongoose = mongoose_connection();

asyncQueryMethod("SELECT * FROM savedProfessionals")
.then((res) => {
    for (const prof of res) {
        const count = prof.timesEmailed;
        const userId = prof.userId;
        const lastCopy = prof.lastCopyUsed;
        const lastEmailed = prof.lastEmailed;
        const updatedAt = prof.updatedAt;

        asyncQueryMethod("SELECT * FROM professionals WHERE id = " + prof.professionalId)
        .then((res) => {
            axios({
                method: 'post',
                url: 'http://localhost:3000/findOne',
                data: {
                    email: res[0].workEmail
                }
            })
            .then((res) => {
                const professionalId = mongoose.Types.ObjectId(res.data.data._id);
                const sp = new savedProfessional({count, lastCopy, lastEmailed, professionalId, updatedAt, userId});
                sp.save((err, p) => {
                    if(err) {
                        console.log(err);
                    } else {
                        console.log(sp.professionalId + 'saved!');
                    }
                })
            })
            .catch((err) => {
                console.log(err);
            })
        })
        .catch((err) => {
            console.log(err);
        });
    }
})
.catch((err) => {
    console.log(err);
})