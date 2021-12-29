package pem.demo.util.constant;

public enum FilePathMessage {
    LINUX_OS_BASE_PATH("/home/PEM/jenkins/workspace/devOps/clusterPython/"),
    WINDOW_OS_BASE_PATH("D:/코딩/자바/pemDB/clusterPython/"),
    RAW_DATA_PATH("rawdata/"),
    LIST_PATH("list/"),
    CLUSTER_RESULT_PATH("result/"),
    CLUSTER_RESULT_DETAIL_PATH("/results/integratedJSON/"),

    WINDOW_OS_RAW_DATA_PATH("D:/코딩/LAB/em/em/rawdata/"),
    ;

    private String path;

    FilePathMessage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
