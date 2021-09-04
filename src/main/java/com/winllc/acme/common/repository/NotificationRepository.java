package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.Notification;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NotificationRepository extends BaseRepository<Notification> {

    List<Notification> findAllByForUserEqualsIgnoreCaseAndNotificationReadAndTaskCompleteOrderByCreationDateDesc(String userName, boolean read, boolean complete);
    Integer countAllByForUserEqualsIgnoreCaseAndNotificationReadAndTaskComplete(String userName, boolean read, boolean complete);

    List<Notification> findAllByTaskObjectIdAndTaskObjectClass(Long id, String clazz);
}
