package com.ibm.clm.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(Constants.Profiles.TEST)
class ProxyApplicationTestIT {

    @Test
    void contextLoads() {
    }


}
