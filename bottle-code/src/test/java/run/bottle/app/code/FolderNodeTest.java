package run.bottle.app.code;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import run.bottle.app.code.model.dto.FolderNode;
import run.bottle.app.code.service.FolderService;

import java.util.List;

@SpringBootTest
public class FolderNodeTest {

    @Autowired
    private FolderService folderService;

    @Test
    public void folderTest(){
        List<FolderNode> folderNode = folderService.getFolderNode();
        System.out.println(folderNode);
    }
}
