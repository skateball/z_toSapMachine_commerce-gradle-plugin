package mpern.sap.commerce.build.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;

public class HashUtil {

    public static String md5Hash(Path path) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (FileChannel ch = FileChannel.open(path, StandardOpenOption.READ)) {
            ByteBuffer bb = ByteBuffer.allocateDirect(256 * 1024);
            while (ch.read(bb) != -1) {
                bb.flip();
                md.update(bb);
                bb.compact();
            }
        }
        BigInteger i = new BigInteger(1, md.digest());
        return String.format("%032x", i);
    }
}
