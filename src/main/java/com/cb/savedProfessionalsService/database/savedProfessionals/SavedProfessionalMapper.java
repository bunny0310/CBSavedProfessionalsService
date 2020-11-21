package com.cb.savedProfessionalsService.database.savedProfessionals;

import com.cb.savedProfessionalsService.database.models.SavedProfessional;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SavedProfessionalMapper implements ResultSetMapper<SavedProfessional> {
    @Override
    public SavedProfessional map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        SavedProfessional savedProfessional = new SavedProfessional(
                r.getInt("id"),
                r.getTimestamp("updatedAt"),
                r.getString("professionals.firstName") + " " + r.getString("professionals.lastName"),
                r.getString("professionals.company"),
                r.getString("professionals.jobTitle"),
                r.getString("professionals.workEmail"),
                r.getInt("savedProfessionals.timesEmailed"),
                r.getInt("professionals.id"),
                r.getInt("savedProfessionals.userId")
        );

        return savedProfessional;
    }
}
