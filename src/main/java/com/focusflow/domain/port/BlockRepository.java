package com.focusflow.domain.port;

import com.focusflow.domain.model.TimeBlock;
import java.util.*;

public interface BlockRepository {
    TimeBlock save(TimeBlock block);
    Optional<TimeBlock> findById(UUID id);
    List<TimeBlock> findByUserId(String userId);
}
