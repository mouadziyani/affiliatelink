package ma.youcode.affiliatelink;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTest {
    static ApplicationModules module = ApplicationModules.of(AffiliatelinkApplication.class);

    @Test
    void verifiesModularStructure(){
        module.forEach(System.out::println);
        module.verify();
    }
}
