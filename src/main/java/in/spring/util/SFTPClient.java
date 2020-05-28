package in.spring.util;

import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URLEncoder;

@Component
public class SFTPClient {

    @Value("${sftp-host}")
    private String host;

    @Value("${sftp-username}")
    private String username;

    @Value("${sftp-password}")
    private String password;

    private Session session = null;

    public void connect() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession("ticketing", "10.1.19.65");
        session.setPassword(URLEncoder.encode("Ticket@3456"));
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }

    public void upload(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.put(source, destination);
        sftpChannel.exit();
    }

    public InputStream download(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        InputStream stream = sftpChannel.get(source);
        sftpChannel.get(source, "/home/jay/ticketing-tool-api/packageMapping.csv");
        sftpChannel.exit();
        return stream;
    }

    public void disconnect() {
        if (session != null) {
            session.disconnect();
        }
    }

}
