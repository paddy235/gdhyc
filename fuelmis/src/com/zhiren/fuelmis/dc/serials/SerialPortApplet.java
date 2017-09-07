package com.zhiren.fuelmis.dc.serials;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommDriver;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.JApplet;

public class SerialPortApplet extends JApplet implements Runnable,
		SerialPortEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CommPortIdentifier portId;

	private StringBuilder barcode = new StringBuilder();

	private InputStream is;

	private boolean over = false; // 退出线程的标志

	private SerialPort serialPort;
	private String driverName = "com.sun.comm.Win32Driver";
	private static final String LIB_PATH_SUFFIX = "system32";

	private static final String DLL_FILE = "win32com.dll";

	public void init() {
		try {

			// 获取载入库时搜索的路径列表

			String dirs = System.getProperty("java.library.path");

			String[] libs = dirs.split(";");

			String libPath = "";

			for (String lib : libs) {

				if (lib.toLowerCase().endsWith(LIB_PATH_SUFFIX)) {

					libPath = lib;

					break;

				}

			}

			File dll = new File(libPath, DLL_FILE);

			if (!dll.exists()) {

				URL url = new URL(super.getCodeBase() + DLL_FILE);

				InputStream is = url.openConnection().getInputStream();

				FileOutputStream fos = new FileOutputStream(dll);

				byte[] buf = new byte[256]; // 读取缓存

				int len = 0;

				while ((len = is.read(buf)) != -1) {

					fos.write(buf, 0, len);

				}

				fos.flush();

				fos.close();

				is.close();

				System.out.println(" 创建文件完毕 [" + dll + "].");

			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		try {

			System.loadLibrary("win32com");

			CommDriver driver = (CommDriver) Class.forName(driverName)
					.newInstance();

			driver.initialize();

		} catch (Exception e) {

			System.err.println(e);

		}

	}

	static {

		System.setSecurityManager(null); // 禁用安全管理器 ( 必须写 )

	}

	public void start() {
		@SuppressWarnings("rawtypes")
		Enumeration ports=null;

		ports = CommPortIdentifier.getPortIdentifiers();



		while (ports.hasMoreElements()) {

			portId = (CommPortIdentifier) ports.nextElement();

			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) { // 是串口

				if (portId.getName().equals("COM3")) {

					break;

				}

			}

		}

		try {

			serialPort = (SerialPort) portId.open("COM3", 2000); // 打开port

			is = serialPort.getInputStream();

			serialPort.addEventListener(this); // 注冊监听器

			serialPort.notifyOnDataAvailable(true); // 数据达到时发出通知

			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); // 设置port參数

		} catch (PortInUseException e) {

			System.err.println(e);

		} catch (IOException e) {

			System.err.println(e);

		} catch (TooManyListenersException e) {

			System.err.println(e);

		} catch (UnsupportedCommOperationException e) {

			System.err.println(e);

		}

		// 启动线程

		Thread t = new Thread(this);

		t.start();

		try {

			t.join();// 等待线程结束

		} catch (InterruptedException e) {

		}

		System.out.println("barcode[" + barcode + "]");

	}

	/* 线程接口的实现和监听器接口的实现代码例如以下所看到的： */

	public void run() {

		while (!over) {

		}

		try {

			if (is != null) {

				is.close();

			}

			if (serialPort != null) {

				serialPort.close();

			}

		} catch (IOException e) {

			System.out.println(e);

		}

	}

	public void destroy() {

		try {

			if (is != null) {

				is.close();

			}

			if (serialPort != null) {

				serialPort.close();

			}

		} catch (IOException e) {

			System.out.println(e);

		}

	}

	@Override
	public void serialEvent(SerialPortEvent event) {

		switch (event.getEventType()) {

		case SerialPortEvent.DATA_AVAILABLE: // 数据到达时运行

			try {

				while (true) {

					int b = is.read(); // 假设读取不到数据则会堵塞

					if (b == 10 || b == 13) { // 假设读到回车或换行则表示读取完毕

						over = true;

						break;

					} else {

						barcode.append(new String(new byte[] { (byte) b }));

					}

				}

			} catch (IOException e) {

				System.err.println(e);

			}

		}

	}

}