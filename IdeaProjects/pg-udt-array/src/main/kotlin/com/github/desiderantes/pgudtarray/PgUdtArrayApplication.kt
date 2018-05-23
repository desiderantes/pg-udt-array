package com.github.desiderantes.pgudtarray

import com.github.desiderantes.pgudtarray.jooq.Tables.CUSTOM_TABLE
import org.jooq.*
import org.jooq.impl.DefaultConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import javax.inject.Inject
import com.github.desiderantes.pgudtarray.jooq.udt.records.CustomTypeRecord
import java.util.logging.Level
import java.util.logging.Logger


@SpringBootApplication
class PgUdtArrayApplication {
    @Bean
    @Primary
    fun jooqConfig(connectionProvider: ConnectionProvider,
                   transactionProvider: TransactionProvider, executeListenerProvider: ExecuteListenerProvider): org.jooq.Configuration {

        return DefaultConfiguration()
                .derive(connectionProvider)
                .derive(transactionProvider)
                .derive(executeListenerProvider)
                .derive(SQLDialect.POSTGRES)

    }

}

fun main(args: Array<String>) {
    runApplication<PgUdtArrayApplication>(*args)
}

@Component
class Persistence @Inject constructor(val dsl : DSLContext) {
    /**
     *
     * main entry point
     *
     * @param args
     */
    @EventListener
    fun onApplicationEvent(event: ContextRefreshedEvent) {
        val arr = arrayOf(CustomTypeRecord("first", "second"))
        val query = dsl.insertInto(CUSTOM_TABLE).columns(CUSTOM_TABLE.TABLE_NAME, CUSTOM_TABLE.TABLE_ARR).values("example", arr)
        Logger.getAnonymousLogger().log(Level.INFO, query.toString())
        Logger.getAnonymousLogger().log(Level.INFO, query.execute().toString())
    }
}
