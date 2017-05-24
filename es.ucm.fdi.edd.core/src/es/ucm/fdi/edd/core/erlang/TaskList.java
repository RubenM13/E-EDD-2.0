package es.ucm.fdi.edd.core.erlang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Field;

import jna.Kernel32;
import jna.W32API;

import com.sun.jna.Pointer;

/**
 * <p>
 * Tasklist:
 * <code>tasklist -?</code>
 * </p>
 * <p>
 * Windows management instrumentation command-line:
 * <code>wmic /?</code>
 * </p>
 * 
 * @see {@link https://technet.microsoft.com/es-es/library/bb491010.aspx}
 * @see {@link http://systemadmin.es/2011/07/wmic-de-windows}
 * 
 * @author Joel
 */
public class TaskList {
	
	public static final String TASKLIST1 = "tasklist";
	public static final String TASKLIST2 = "tasklist /M";
	public static final String TASKLIST3 = "tasklist /V /FO CSV";
	public static final String TASKLIST4 = "tasklist /SVC /FO LIST";
	public static final String TASKLIST5 = "tasklist /APPS /FI \"STATUS eq RUNNING\"";
	public static final String TASKLIST6 = "tasklist /M wbem*";
	public static final String TASKLIST7 = "tasklist /S sistema /FO LIST";
	public static final String TASKLIST8 = "tasklist /S sistema /U LENOVO\\Joel /FO CSV /NH";
	public static final String TASKLIST9 = "tasklist /S sistema /U Joel /P contraseña /FO TABLE /NH";
	public static final String TASKLIST10 = "tasklist /FI \"USERNAME ne NT AUTHORITY\\SYSTEM\" /FI \"STATUS eq running\"";
	
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		TaskList task = new TaskList();
		System.out.println("tasks list: \n");
		task.taskList();
		System.out.println("wmic list: \n");
		task.getPidByComandLine();
	}

	public void taskList() throws IOException {
		BufferedReader input = null;
		try {
	        String line;
//	        Process p = Runtime.getRuntime().exec("ps -e"); // linux
	        Process p = Runtime.getRuntime().exec("tasklist /fo CSV /nh /fi \"imagename eq erl.exe\""); // windows
	        InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
			input = new BufferedReader(inputStreamReader);
			while ((line = input.readLine()) != null) {
				String[] info = line.split(",");
				if (info.length == 5) {
					String nombreImagen = fixData(info[0]);
					String pid = fixData(info[1]);
					String nombreSesion = fixData(info[2]);
					String numSesiones = fixData(info[3]);
					String memory = fixData(info[4]);
					TaskProcess taskProcess = new TaskProcess(nombreImagen,	pid, nombreSesion, numSesiones,	memory);
//					if (taskProcess.getNombreImagen().equalsIgnoreCase("erl.exe")) {
						System.out.println(taskProcess);
//					}
				}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	input.close();
	    }
	}
	
	public String getPidByComandLine() throws IOException {
		BufferedReader input = null;
		try {
        	StringBuffer sbCommand = new StringBuffer();
        	sbCommand.append("-sname ");
        	sbCommand.append(ErlangServer.NODE);
        	sbCommand.append(" -setcookie ");
        	sbCommand.append(Erlang2Java.COOKIE);
        	sbCommand.append(" -run edd_jserver start");
//        	sbCommand.append(" -noshell -s erlang halt");

//	        String wmicCommand1 = "wmic PROCESS where \"name like '%erl.exe%'\" get Processid,Caption,Commandline";
	        String wmicCommand2 = "wmic process where \"name like '%erl.exe%' and commandline like '%"+sbCommand+"%'\" get Processid, Caption,Commandline";
			Process p = Runtime.getRuntime().exec(wmicCommand2);
	        InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
			input = new BufferedReader(inputStreamReader);
			String line;
			while ((line = input.readLine()) != null) {
				if (!line.equals("")) {
//					System.out.println(line);
					String[] info = line.split("\\s{2,}");
					if (info.length == 3) {
						String caption = info[0];
						String commandLine = info[1];
						String processId = info[2];
						InfoProcess infoProcess = new InfoProcess(caption, commandLine, processId);
						if (commandLine.contains(sbCommand)) {
//							System.out.println("Erl PID: " + infoProcess.getProcessId());
							return infoProcess.getProcessId();
						}
					} else if (info.length == 4) {
						String caption = info[0];
						String executable = info[1];
						String commandLine = info[2];
						String processId = info[3];
						InfoProcess infoProcess = new InfoProcess(caption, executable, commandLine, processId);
						if (commandLine.contains(sbCommand)) {
//							System.out.println("Erl PID: " + infoProcess.getProcessId());
							return infoProcess.getProcessId();
						}
					} else {
						for (int i = 0; i < info.length; i++) {
							String data = info[i];
							System.out.print(data + " ");
						}
					}
				}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	input.close();
	    }
		
		return null;
	}
	
	private static String fixData(String data) {
		if (data.startsWith("\"") && data.endsWith("\"")) {
			return data.substring(1, data.length()-1);
		}
		
		return data;
	}
	
	/**
	 * Descartados...
	 * ================================================================================ 
	 */
	protected void killProcess(Process process) throws Exception {
		if (process != null) {
			Integer pid;
			Runtime rt = Runtime.getRuntime();
			if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
				pid = getWindowsPid(process);
				rt.exec("taskkill /F /PID " + pid);
			} else {
				pid = getUnixPid(process);
				rt.exec("kill -9 " + pid);
			}

			process.destroy();
			process.destroyForcibly();
			process = null;
		}
	}

	/**
	 * Determine the pid on windows plattforms.
	 * 
	 * @return the pid.
	 */
	private Integer getWindowsPid(Process process) {
		Class<?> processImpl = process.getClass();
		String processClassName = processImpl.getName();
		if (processClassName.equals("java.lang.Win32Process")
				|| processClassName.equals("java.lang.ProcessImpl")) {
			try {
				Field field = processImpl.getDeclaredField("handle");
				field.setAccessible(true);
				long peer = field.getLong(process);

				Kernel32 kernel = Kernel32.INSTANCE;
				W32API.HANDLE handle = new W32API.HANDLE();
				handle.setPointer(Pointer.createConstant(peer));
				Integer pid = kernel.GetProcessId(handle);
				System.err.println("Windows --> Process handle: " + peer
						+ " \tpid: " + pid);
				// getPid();
				return pid;
			} catch (NoSuchFieldException | IllegalAccessException
					| IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		return -1;
	}

	/**
	 * Gets the PID on unix/linux systems
	 * 
	 * @return the pid.
	 */
	private Integer getUnixPid(Process process) {
		if (process.getClass().getName().equals("java.lang.UNIXProcess")) {
			try {
				Class<?> processImpl = process.getClass();
				Field f = processImpl.getDeclaredField("pid");
				f.setAccessible(true);
				Integer pid = f.getInt(process);
				return pid;
			} catch (NoSuchFieldException | IllegalAccessException
					| IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		return -1;
	}
	
	protected void getPid() {
		try {
			RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

			String jvmName = runtimeMXBean.getName();
			System.out.println("JVM Name = " + jvmName);

			long pid = Long.valueOf(jvmName.split("@")[0]);
			System.out.println("JVM PID  = " + pid);

			ThreadMXBean bean = ManagementFactory.getThreadMXBean();
			int peakThreadCount = bean.getPeakThreadCount();
			System.out.println("Peak Thread Count = " + peakThreadCount);

			// Field jvmField =
			// runtimeMXBean.getClass().getDeclaredField("jvm");
			// jvmField.setAccessible(true);
			// VMManagement vmManagement = (VMManagement)
			// jvmField.get(runtimeMXBean);
			// Method getProcessIdMethod =
			// vmManagement.getClass().getDeclaredMethod("getProcessId");
			// getProcessIdMethod.setAccessible(true);
			// Integer processId = (Integer)
			// getProcessIdMethod.invoke(vmManagement);
			// System.out.println("################    ProcessId = " +
			// processId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class TaskProcess {
	
	private String nombreImagen;
	private String pid;
	private String nombreSesion;
	private String numSesiones;
	private String memory;
	
	public TaskProcess(String nombreImagen, String pid, String nombreSesion, String numSesiones, String memory) {
		this.nombreImagen = nombreImagen;
		this.pid = pid;
		this.nombreSesion = nombreSesion;
		this.numSesiones = numSesiones;
		this.memory = memory;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getNombreSesion() {
		return nombreSesion;
	}

	public void setNombreSesion(String nombreSesion) {
		this.nombreSesion = nombreSesion;
	}

	public String getNumSesiones() {
		return numSesiones;
	}

	public void setNumSesiones(String numSesiones) {
		this.numSesiones = numSesiones;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}
	
	@Override
	public String toString() {
		return String.format("%s : %s : %s : %s : %s", nombreImagen, pid, nombreSesion, numSesiones, memory);
	}
}

class InfoProcess {
	
	private String caption;
	private String executable;
	private String commandLine;
	private String processId;

	public InfoProcess(String caption, String commandLine, String processId) {
		this.caption = caption;
		this.commandLine = commandLine;
		this.processId = processId;
	}
	
	public InfoProcess(String caption, String executable, String commandLine, String processId) {
		this.caption = caption;
		this.executable = executable;
		this.commandLine = commandLine;
		this.processId = processId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public String getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
}