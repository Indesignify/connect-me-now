package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.MeetingParticipantEntity;
import org.springframework.data.repository.CrudRepository;

public interface MeetingParticipantRepository extends CrudRepository<MeetingParticipantEntity, Long> {

}
