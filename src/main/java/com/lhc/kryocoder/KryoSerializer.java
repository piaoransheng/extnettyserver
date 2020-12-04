package com.lhc.kryocoder;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 类说明：反序列化/序列化器
 */
public class KryoSerializer {
    private static Kryo kryo = KryoFactory.createKryo();

    public static void serialize(Object object, ByteBuf byteBuf) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, object);
        output.flush();
        output.close();

        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byteBuf.writeBytes(b);
    }

    public static Object deserialize(ByteBuf byteBuf) {
        if (byteBuf == null) {
            return null;
        }
        Input input = new Input(new ByteBufInputStream(byteBuf));
        return kryo.readClassAndObject(input);
    }
}