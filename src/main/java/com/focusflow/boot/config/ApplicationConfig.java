package com.focusflow.boot.config;

import com.focusflow.application.BlockApplicationService;
import com.focusflow.domain.port.ActiveBlockStore;
import com.focusflow.domain.port.BlockRepository;
import com.focusflow.domain.port.EventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public BlockApplicationService blockApplicationService(BlockRepository repo,
                                                           ActiveBlockStore activeStore,
                                                           EventPublisher publisher
                                                           ) {
        return new BlockApplicationService(repo, activeStore, publisher );
    }
}
