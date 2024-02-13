package kotlinassignment.week10.domain.util

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@ConditionalOnMissingBean(name=["jpaAuditingHandler"])
@EnableJpaAuditing
class JpaAuditingNonConflictingDeclaration // https://stackoverflow.com/questions/53787550/jpaauditinghandler-defined-in-null-on-application-startup-using-spring-boot
