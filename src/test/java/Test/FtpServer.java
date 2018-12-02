package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpServer {

	public static void main(String[] args) {

		FTPClient client = new FTPClient();
		try {
			client.connect("192.168.199.124");
			client.login("ftpuser", "root");
			System.out.print("连接响应：" + client.getReplyCode());
			if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
				System.out.println("未连接到FTP，用户名或密码错误。");
			}
			client.setControlEncoding("GBK");
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			client.setFileType(FTP.BINARY_FILE_TYPE);
			File file = new File("F:\\123.jpg");
			client.changeWorkingDirectory("/home/ftpuser/picture/www/images");
			FileInputStream in = new FileInputStream(file);
			FTPFile[] fs = client.listFiles(); // 得到目录的相应文件列表
			System.out.println(fs.length);
			client.enterLocalPassiveMode();
			client.storeFile("111.jpg", in);
			in.close();
			client.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
