package kotlinassignment.week10.testConfig

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["kotlinassignment.week10.infra.jwt", "kotlinassignment.week10.infra.security"])
@TestConfiguration
class ControllerTestSecurityConfig
