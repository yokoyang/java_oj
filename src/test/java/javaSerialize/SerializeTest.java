package javaSerialize;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

public class SerializeTest {

    @Test
    public void testUserInfo() throws IOException {
        // TODO Auto-generated method stub
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("The jdk serializable length is:" + b.length);
        bos.close();
        System.out.println("---------------------------------------------");
        System.out.println("The byte serializable length is:" + info.codeC().length);
    }
}
