package com.cb.savedProfessionalsService.database.DAO;

import com.cb.savedProfessionalsService.database.models.SavedProfessional;
import com.cb.savedProfessionalsService.database.savedProfessionals.SavedProfessionalMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;
import java.util.Set;

@RegisterMapper(SavedProfessionalMapper.class)
public interface SavedProfessionalsDAO {

    @SqlQuery("SELECT professionals.firstName, professionals.lastName, professionals.workEmail, professionals.id, professionals.company, savedProfessionals.lastEmailed, savedProfessionals.lastCopyUsed, professionals.jobTitle, savedProfessionals.id, savedProfessionals.updatedAt, savedProfessionals.timesEmailed, savedProfessionals.userId FROM savedProfessionals INNER JOIN professionals WHERE professionals.id = savedProfessionals.professionalId AND savedProfessionals.userId = :id")
    public Set<SavedProfessional> getSavedProfessionals(@Bind("id") final int id);

    @SqlUpdate("INSERT INTO savedProfessionals (professionalId, userId) VALUES (:professionalId, :userId)")
    public int insertSavedProfessional(@BindBean SavedProfessional savedProfessional);

    @SqlQuery("SELECT professionals.firstName, professionals.lastName, professionals.workEmail, professionals.id, professionals.company, professionals.jobTitle, savedProfessionals.id, savedProfessionals.updatedAt, savedProfessionals.timesEmailed FROM savedProfessionals INNER JOIN professionals WHERE professionals.id = savedProfessionals.professionalId ORDER BY savedProfessionals.id DESC LIMIT 1")
    public List<SavedProfessional> getLatestSavedProfessional();

    @SqlUpdate("UPDATE savedProfessionals SET lastEmailed=CURRENT_TIMESTAMP(), timesEmailed=timesEmailed + 1, lastCopyUsed=:tid WHERE id=:id")
    public void updateSP(@Bind("id") final int id, @Bind("tid") final int tid);

}
