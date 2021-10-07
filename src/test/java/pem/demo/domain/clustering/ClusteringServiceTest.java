package pem.demo.domain.clustering;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ClusteringServiceTest {

    @Test
    @Disabled
    void test() {
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String path = rootPath + "/pemDB/clusterPython/result/";
        File dir = new File(path);
        String files[] = dir.list();

        for (String file : files) {
            System.out.println("file: " + file);
        }
    }

}