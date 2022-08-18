package io.github.ordinarykai;

import io.github.ordinarykai.framework.mybatisplus.core.generator.CodeGenerator;
import io.github.ordinarykai.framework.mybatisplus.core.generator.DatabaseDocGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

    @Resource
    private CodeGenerator codeGenerator;
    @Resource
    private DatabaseDocGenerator databaseDocGenerator;

    @Test
    void contextLoads() {
        codeGenerator.execute();
        databaseDocGenerator.execute();
    }

}
