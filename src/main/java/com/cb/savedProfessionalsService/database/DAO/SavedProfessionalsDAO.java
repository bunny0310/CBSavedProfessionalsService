package com.cb.savedProfessionalsService.database.DAO;

import com.cb.savedProfessionalsService.database.models.SavedProfessional;
import com.cb.savedProfessionalsService.database.savedProfessionals.SavedProfessionalMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(SavedProfessionalMapper.class)
public interface SavedProfessionalsDAO {

    @SqlQuery("SELECT professionals.firstName, professionals.lastName, professionals.workEmail, professionals.id, professionals.company, professionals.jobTitle, savedProfessionals.id, savedProfessionals.updatedAt, savedProfessionals.timesEmailed FROM savedProfessionals INNER JOIN professionals WHERE professionals.id = savedProfessionals.professionalId AND savedProfessionals.userId = :id")
    public List<SavedProfessional> getSavedProfessionals(@Bind("id") final int id);

    @SqlUpdate("INSERT INTO savedProfessionals (professionalId, userId) VALUES (:professionalId, :userId)")
    public void insertSavedProfessional(@BindBean SavedProfessional savedProfessional);

}
