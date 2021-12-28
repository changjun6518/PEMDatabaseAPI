package pem.demo.util.constant;

public enum FileMessage {
    WINDOW_OS_BASE_PATH("D:/코딩/자바/pemDB/clusterPython/"),
    LINUX_OS_BASE_PATH("/home/PEM/jenkins/workspace/devOps/clusterPython/")
    ;

    private String path;

    FileMessage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
