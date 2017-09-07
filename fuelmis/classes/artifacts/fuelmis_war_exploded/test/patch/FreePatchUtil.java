package patch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FreePatchUtil {
	public static String patchFile = "E:\\projectForIdea\\gddlds\\New_changelist.patch";// 补丁文件,由eclipse svn// plugin生成

	public static String projectPath = "";// 项目文件夹路径不用设置

	public static String webContent = "WebContent";// web应用文件夹名

	public static String classPath = "";// class存放路径//不用设置

	public static String desPath = "update_pkg";// 补丁文件包存放路径

    public static String realversion = "fuelmis";// .java补丁文件包存放路径
	public static String version = "gddlds补丁";// .classes补丁版本

    public static String projectName="fuelmis";//工程名称
	public static void setPath() {
		// 以上代码可以得到指定类的绝对地址，如果想得到工程地址，只要把后面的字符串剪掉。

		// 3、下面的代码

		// String packageName = this.getClass().getResource("").getPath();
		String packageName = FreePatchUtil.class.getResource("/").getPath();
		System.out.println(packageName.indexOf(projectName));
		String path=packageName.substring(1, packageName.indexOf(projectName));
		projectPath=path+projectPath;
		classPath=path+classPath;
		System.out.println(projectPath);
		System.out.println(classPath);
//		packageName = packageName.replace("/", "\\");
		// 可以得到所在类的地址，但是在插件开发中得到的并非是绝对地址。在插件开发中可以结合2和3的代码得到当前工程的绝对地址： view
		// sourceprint?01
//		 String packageName = this.getClass().getResource("").getPath();

//		packageName = packageName.replace("/", "\\");

		System.out.println("包名：" + packageName);
//
//		String projectPath = null;
//
//		try {
//
//			String packageFullName = GetPath.getPathFromClass(FreePatchUtil.class);
//
//			projectPath = packageFullName.substring(0, packageFullName.indexOf(packageName) + 1);
//
//			System.out.println("工程路径：" + projectPath);
//
//		} catch (IOException e1) {
//
//			projectPath = null;
//
//			e1.printStackTrace();
//
//		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		setPath();
		//copyFiles(getPatchFileList());//.classes
        copyRealFilses(getPatchFileList());//.java
	}

    /**
     * 读取补丁文件
     * @return
     * @throws Exception
     */
	public static List<String> getPatchFileList() throws Exception {
		List<String> fileList = new ArrayList<String>();
		FileInputStream f = new FileInputStream(patchFile);
		BufferedReader dr = new BufferedReader(new InputStreamReader(f, "utf-8"));
		String line;
		while ((line = dr.readLine()) != null) {
			if (line.indexOf("Index:") != -1) {
				line = line.replaceAll(" ", "");
				line = line.substring(line.indexOf(":") + 1, line.length());
				fileList.add(line);
			}
		}
		return fileList;
	}

    /**
     * 替换文件中的字符串
     * @param fullFileName
     * @param o
     * @param n
     * @throws Exception
     */
    public static void replaceFile(String fullFileName,String o,String n) throws Exception {
        ReadWriteFile.setFileFullName(fullFileName);
        ReadWriteFile.replaceTxtByStr(o, n);
    }

    /**
     * 抽取java文件
     * @param list
     */
    public static void copyRealFilses(List<String> list) {
        if(realversion.equals("fuelmis")){
            for (String fullFileName : list) {
                String fileName = fullFileName;
                fileName= fileName.replace("patch", "fuelmis/dc");
                String tempDesPath = fileName.substring(0, fileName.lastIndexOf("/"));
                String desFilePathStr = desPath + "/" + realversion + "/"+tempDesPath;
                String desFileNameStr = desPath + "/" + realversion + "/"+ fileName;
                File desFilePath = new File(desFilePathStr);
                if (!desFilePath.exists()) {
                    desFilePath.mkdirs();
                }
                copyFile(fullFileName, desFileNameStr);
                System.out.println(fullFileName + "复制完成");
                try{
                    replaceFile(desFileNameStr, "patch","fuelmis.dc");
                    System.out.println(desFileNameStr + "替换完成");
                }catch (Exception e){
                    System.out.println(desFileNameStr + "替换失败");
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 抽取类文件
     * @param list
     */
	public static void copyFiles(List<String> list) {

		for (String fullFileName : list) {
			if (fullFileName.indexOf("src/") != -1) {// 对源文件目录下的文件处理
				String fileName = fullFileName.replace("src", "");
				fullFileName = classPath + fileName;
				if (fileName.endsWith(".java")) {
					fileName = fileName.replace(".java", ".class");
					fullFileName = fullFileName.replace(".java", ".class");
				}
				String tempDesPath = fileName.substring(0, fileName.lastIndexOf("/"));
				String desFilePathStr = desPath + "/" + version + "/WEB-INF/classes" + tempDesPath;
				String desFileNameStr = desPath + "/" + version + "/WEB-INF/classes" + fileName;
				File desFilePath = new File(desFilePathStr);
				if (!desFilePath.exists()) {
					desFilePath.mkdirs();
				}
				copyFile(fullFileName, desFileNameStr);
				System.out.println(fullFileName + "复制完成");
			} else {// 对普通目录的处理
				String desFileName = fullFileName.replaceAll(webContent, "");
				fullFileName = projectPath + "/" + fullFileName;// 将要复制的文件全路径
				String fullDesFileNameStr = desPath + "/" + version + desFileName;
				String desFilePathStr = fullDesFileNameStr.substring(0, fullDesFileNameStr.lastIndexOf("/"));
				File desFilePath = new File(desFilePathStr);
				if (!desFilePath.exists()) {
					desFilePath.mkdirs();
				}
				copyFile(fullFileName, fullDesFileNameStr);
				System.out.println(fullDesFileNameStr + "复制完成");
			}
		}
	}

	private static void copyFile(String sourceFileNameStr, String desFileNameStr) {
		File srcFile = new File(sourceFileNameStr);
		File desFile = new File(desFileNameStr);
		try {
			copyFile(srcFile, desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

}