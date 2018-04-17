package io.connectmenow.connect.repository;

import io.connectmenow.connect.model.entities.MeetingsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MeetingsRepository extends PagingAndSortingRepository<MeetingsEntity, Long> {

}
