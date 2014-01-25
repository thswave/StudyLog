package changwon.study.java;

import java.io.File;
import java.text.NumberFormat;

public class JVMStatus {
	
	private Runtime runtime;
	
	public JVMStatus(Runtime runtime) {
		super();
		this.runtime = runtime;
	}

	public static void main(String[] args){
		
		JVMStatus status = new JVMStatus(Runtime.getRuntime());
		
		status.getJVMMemoryStatus();
		
		System.out.println(status.Info());
	}

	public void getJVMMemoryStatus() {
		long total = runtime.totalMemory();
		long free = runtime.freeMemory();
		long used  = runtime.totalMemory() - runtime.freeMemory();
		
		//total amount of memory currently available for current and future objects, measured in bytes.
		System.out.println("jvm total memory : " + total / 1024 + " kilo bytes");
		//an approximation to the total amount of memory currently available 
		//for future allocated objects, measured in bytes.
		System.out.println("jvm free memory : " + free / 1024 + " kilo bytes");
		System.out.println("jvm used memory : " + used / 1024 + " kilo bytes");
		
	}
	
	public String Info() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.OsInfo());
        sb.append(this.MemInfo());
        sb.append(this.DiskInfo());
        return sb.toString();
    }

    public String OSname() {
        return System.getProperty("os.name");
    }

    public String OSversion() {
        return System.getProperty("os.version");
    }

    public String OsArch() {
        return System.getProperty("os.arch");
    }

    public long totalMem() {
        return Runtime.getRuntime().totalMemory();
    }

    public long usedMem() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public String MemInfo() {
        NumberFormat format = NumberFormat.getInstance();
        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        sb.append("Free memory: ");
        sb.append(format.format(freeMemory / 1024));
        sb.append("\n"); // <br/>
        sb.append("Allocated memory(KB): ");
        sb.append(format.format(allocatedMemory / 1024));
        sb.append("\n");
        sb.append("Max memory(KB): ");
        sb.append(format.format(maxMemory / 1024));
        sb.append("\n");
        sb.append("Total free memory(KB): ");
        sb.append(format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));
        sb.append("\n");
        return sb.toString();

    }

    public String OsInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("OS: ");
        sb.append(this.OSname());
        sb.append("\n");
        sb.append("Version: ");
        sb.append(this.OSversion());
        sb.append("\n");
        sb.append("Archtecture: ");
        sb.append(this.OsArch());
        sb.append("\n");
        sb.append("Available processors (cores): ");
        sb.append(runtime.availableProcessors());
        sb.append("\n");
        return sb.toString();
    }

    public String DiskInfo() {
        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();
        StringBuilder sb = new StringBuilder();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            sb.append("File system root: ");
            sb.append(root.getAbsolutePath());
            sb.append("\n");
            sb.append("Total space (MB): ");
            sb.append(root.getTotalSpace() / 1024 / 1024);
            sb.append("\n");
            sb.append("Free space (MB): ");
            sb.append(root.getFreeSpace() / 1024 / 1024);
            sb.append("\n");
            sb.append("Usable space (MB): ");
            sb.append(root.getUsableSpace() / 1024 / 1024);
            sb.append("\n");
        }
        return sb.toString();
    }
}
