package com.tradisys.odyssey.apg.s2w.config;

import com.google.common.io.Files;
import com.tradisys.odyssey.apg.s2w.store.BaseStore;
import com.tradisys.odyssey.apg.s2w.store.leveldb.LevelDBCustomerStore;
import com.tradisys.odyssey.apg.s2w.store.leveldb.LevelDBOrganizationStore;
import com.tradisys.odyssey.apg.s2w.store.leveldb.LevelDBTaskStore;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.File;
import java.io.IOException;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

@Configuration
public class DomainSpringConfig {

    @Bean
    public DB dbStore(){
        DB db = null;
        Options options = new Options();
        options.createIfMissing(true);

        File dbFile = Files.createTempDir();

        try {
            db = factory.open(dbFile, options);
        } catch (IOException e) {
        }
        return db;
    }

    @Bean
    public BaseStore customerStore(){
        return new LevelDBCustomerStore(dbStore());
    }

    @Bean
    public BaseStore organizationStore(){
        return new LevelDBOrganizationStore(dbStore());
    }

    @Bean
    public BaseStore tasksStore(){
        return new LevelDBTaskStore(dbStore());
    }




}
